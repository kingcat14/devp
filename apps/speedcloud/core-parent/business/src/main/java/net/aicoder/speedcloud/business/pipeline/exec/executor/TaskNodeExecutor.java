package net.aicoder.speedcloud.business.pipeline.exec.executor;

import net.aicoder.speedcloud.business.pipeline.constant.ExecInstanceNodeType;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TaskNodeExecutor implements NodeExecutor {

    @Autowired()@Qualifier("pipelineExecInstanceNodeService")
    private PipelineExecInstanceNodeService execNodeService;

    @Autowired
    private NodeExecutorCenter nodeExecutorCenter;

    @Override
    public void execute(PipelineExecInstanceNode node){
        node.setStatus(PipelineExecInstanceNodeStatus.PREPARED);
        execNodeService.merge(node);
    }

    @Override
    public void register(NodeExecutorCenter nodeExecutorCenter) {
        this.nodeExecutorCenter = nodeExecutorCenter;
        nodeExecutorCenter.register(ExecInstanceNodeType.TASK, this);
    }


}
