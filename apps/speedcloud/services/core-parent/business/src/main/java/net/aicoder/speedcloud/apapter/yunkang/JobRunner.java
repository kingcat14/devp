package net.aicoder.speedcloud.apapter.yunkang;

import net.aicoder.speedcloud.apapter.yunkang.client.ExecResult;
import net.aicoder.speedcloud.apapter.yunkang.client.YunkangClient;
import net.aicoder.speedcloud.apapter.yunkang.client.dto.ExecParam;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecNodeLogDao;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeLog;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeParam;
import net.aicoder.speedcloud.business.pipeline.exec.service.ExecNodeAction;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeParamService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineBuildLogVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 执行Job
 */
@Service
public class JobRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobRunner.class);

    @Autowired
    private PipelineExecNodeService pipelineExecNodeService;

    @Autowired
    private PipelineExecNodeLogDao pipelineExecNodeLogDao;

    @Autowired
    private PipelineExecNodeParamService pipelineExecNodeParamService;

    @Autowired
    private ExecNodeAction execNodeAction;

    @Autowired
    private YunkangClient yunkangClient;

    Executor executor = Executors.newFixedThreadPool(5);

    @Scheduled(cron = "*/10 * * * * *")
    @Transactional
    public void run(){

        List<PipelineExecNode> nodeList = pipelineExecNodeService.findPreparedJob(1L);

        for(PipelineExecNode node : nodeList){
            try{
                //调度执行JOB, 这里还没执行完就会返回
                runJob(node);
                pipelineExecNodeService.merge(node);
                executor.execute(()->{
                    int count = 0;
                    boolean flag = true;
                    while(flag){
                        count++;
                        flag = !watchNode(node);
                        flag = flag && (count <= 300);
                    }
                    finishJob(node);
                });
            }catch(Throwable e){
                node.setResult("FAILURE");
                node.setStatus("FINISH");
                node.setResultMessage(e.getMessage());
                pipelineExecNodeService.merge(node);
            }
            //runJob(node);
        }

    }

    /**
     * @param node
     */
    private void runJob(PipelineExecNode node){

        /*
         * 1.得到任务
         * 2.得到任务参数
         * 3.调用执行
         */
        List<PipelineExecNodeParam> paramList = pipelineExecNodeParamService.findByNode(node.getId());

        List<ExecParam> execParamList = new ArrayList<>();

        for(PipelineExecNodeParam param : paramList){
            ExecParam execParam = new ExecParam();
            execParam.setParamName(param.getName());
            execParam.setParamValue(param.getValue());
            if(StringUtils.isNotEmpty(execParam.getParamValue())) {
                execParamList.add(execParam);
            }
        }

        node.setStartTime(new Date());
        ExecResult result = yunkangClient.exec(node.getRelationObjId()+"", execParamList);
        node.setStatus("RUNNING");
//        node.setStartTime(new Date());
//        node.setResult(result.getFlag());
//        node.setResultMessage(result.getErrorMsg());
        node.setResultRelationObj(result.getData().getCurrentBuildNumber());

    }

    /**
     * 监控节点是否执行完了
     * @param node
     * @return true 执行完毕
     */
    private boolean watchNode(PipelineExecNode node){

        PipelineBuildLogVO buildInfo = yunkangClient.pipelineBuildInfo(node.getRelationObjId()+"", node.getResultRelationObj());

        if(buildInfo == null){
            return false;
        }

        boolean finish = StringUtils.isNotEmpty(buildInfo.getResult()) && !StringUtils.equals("BUILDING", buildInfo.getResult());

        node.setResult(buildInfo.getResult());
        if(finish){
            PipelineExecNodeLog nodeLog = new PipelineExecNodeLog();
            nodeLog.setLog(buildInfo.getConsoleOutputText());
            nodeLog.setId(node.getId()+"");
            pipelineExecNodeLogDao.save(nodeLog);
        }

        return finish;
    }

    /**
     * 结束job执行
     * @param node
     */
    private void finishJob(PipelineExecNode node){
        //TODO 这里的代码需要改成调用SpeedCloud的接口
        execNodeAction.finishNode(node);

    }
}
