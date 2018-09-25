package net.aicoder.speedcloud.business.pipeline.exec.executor;

import net.aicoder.speedcloud.business.pipeline.constant.ExecNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.ExecMode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 阶段节点的执行逻辑
 */
@Component
public class StageNodeExecutor implements NodeExecutor {

    @Autowired()@Qualifier("pipelineExecNodeService")
    private PipelineExecNodeService execNodeService;

    @Autowired
    private NodeExecutorCenter nodeExecutorCenter;

    @Override
    public void execute(PipelineExecNode node){

        //如果不是自定开始,则设置为已准备好
        if(!node.getAutoStart()){
            node.setStatus(PipelineExecNodeStatus.PREPARED);
            return ;
        }

        run(node);
    }

    /**
     * 真正执行Stage
     * @param node
     */
    public void run(PipelineExecNode node){
        node.setStatus(PipelineExecNodeStatus.RUNNING);
        execNodeService.merge(node);

        if(ExecMode.PARALLEL.equals(node.getExecMode())){
            runAllChildNode(node.getId());
        }else{

            PipelineExecNode nextWaitingNode = execNodeService.findNextWaitingChildNode(node.getId());

            //这里默认stage子任务是task
            if(nextWaitingNode == null){
                return;
            }

            runNode(nextWaitingNode);
        }
    }

    private void runAllChildNode(Long parentNode){
        List<PipelineExecNode> nodeList = execNodeService.findChildNode(parentNode);

        for(PipelineExecNode node : nodeList){
            runNode(node);
        }

    }

    private void runNode(PipelineExecNode node){
        nodeExecutorCenter.execute(node);
    }

    @PostConstruct
    public void register() {
        nodeExecutorCenter.register(ExecNodeType.STAGE, this);
    }


}
