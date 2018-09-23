package net.aicoder.speedcloud.business.pipeline.exec.executor;

import net.aicoder.speedcloud.business.pipeline.constant.ExecInstanceNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.ExecMode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class StageNodeExecutor implements NodeExecutor {

    @Autowired()@Qualifier("pipelineExecInstanceNodeService")
    private PipelineExecInstanceNodeService execNodeService;

    @Autowired
    private NodeExecutorCenter nodeExecutorCenter;

    @Override
    public void execute(PipelineExecInstanceNode node){
        node.setStatus(PipelineExecInstanceNodeStatus.RUNNING);
        execNodeService.merge(node);

        if(ExecMode.PARALLEL.equals(node.getExecMode())){
            runAllChildNode(node.getId());
        }else{
            PipelineExecInstanceNode nextWaitingNode = execNodeService.findNextWaitingChildNode(node.getId());
            //这里默认stage子任务是task,

            if(nextWaitingNode == null){
                return;
            }

            runNode(nextWaitingNode);
        }
    }

    private void runAllChildNode(Long parentNode){
        List<PipelineExecInstanceNode> nodeList = execNodeService.findChildNode(parentNode);

        for(PipelineExecInstanceNode node : nodeList){
            runNode(node);
        }

    }

    private void runNode(PipelineExecInstanceNode node){
        nodeExecutorCenter.execute(node);
    }

    @Override
    public void register(NodeExecutorCenter nodeExecutorCenter) {
        nodeExecutorCenter.register(ExecInstanceNodeType.STAGE, this);
        this.nodeExecutorCenter = nodeExecutorCenter;
    }


}
