package net.aicoder.speedcloud.apapter.yunkang;

import net.aicoder.speedcloud.apapter.yunkang.client.Result;
import net.aicoder.speedcloud.apapter.yunkang.client.YunkangClient;
import net.aicoder.speedcloud.apapter.yunkang.client.dto.ExecParam;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParam;
import net.aicoder.speedcloud.business.pipeline.exec.service.ExecNodeAction;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeParamService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行Job
 */
@Service
public class JobRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobRunner.class);

    @Autowired
    private PipelineExecInstanceNodeService pipelineExecInstanceNodeService;

    @Autowired
    private PipelineExecInstanceNodeParamService pipelineExecInstanceNodeParamService;


    @Autowired
    private ExecNodeAction execNodeAction;

    @Autowired
    private YunkangClient yunkangClient;

    @Scheduled(cron = "0/30 * * * * *")

    public void run(){

        List<PipelineExecInstanceNode> nodeList = pipelineExecInstanceNodeService.findPreparedJob(1L);

        for(PipelineExecInstanceNode node : nodeList){
            try{
                runJob(node);
            }catch(Exception e){
                node.setResult("FAIL");
            }finally {
                node.setStatus("FINISH");
                pipelineExecInstanceNodeService.merge(node);
                finishJob(node);
            }
            //runJob(node);
        }

    }

    /**
     *
     * @param node
     */
    private void runJob(PipelineExecInstanceNode node){

        /*
         * 1.得到任务
         * 2.得到任务参数
         * 3.调用执行
         */
        List<PipelineExecInstanceNodeParam> paramList = pipelineExecInstanceNodeParamService.findByInstanceNode(node.getId());

        List<ExecParam> execParamList = new ArrayList<>();

        for(PipelineExecInstanceNodeParam param : paramList){
            ExecParam execParam = new ExecParam();
            execParam.setParamName(param.getName());
            execParam.setParamValue(param.getValue());
            if(StringUtils.isNotEmpty(execParam.getParamValue())) {
                execParamList.add(execParam);
            }
        }

        Result result = yunkangClient.exec(node.getTask()+"", execParamList);
        node.setResult(result.getFlag());
        node.setResultMessage(result.getErrorMsg());

    }

    /**
     * 结束job执行
     * @param node
     */
    @Transactional
    private void finishJob(PipelineExecInstanceNode node){
        //TODO 这里的代码需要改成调用SpeedCloud的接口

        execNodeAction.finishNode(node);

    }
}
