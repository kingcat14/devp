package net.aicoder.speedcloud.business.pipeline.exec.executor;

import net.aicoder.speedcloud.business.pipeline.constant.ExecInstanceNodeType;
import net.aicoder.speedcloud.business.pipeline.exec.builder.NodeBuilder;
import net.aicoder.speedcloud.business.pipeline.exec.builder.PipelineExecInstanceBuilder;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.Executor;

public class PipelineNodeExecutor implements NodeExecutor {

    @Autowired()@Qualifier("pipelineExecInstanceNodeService")
    private PipelineExecInstanceNodeService execNodeService;

    @Autowired
    private NodeExecutorCenter nodeExecutorCenter;

    @Override
    public void execute(PipelineExecInstanceNode node){
        node.setStatus(PipelineExecInstanceNodeStatus.RUNNING);
        execNodeService.merge(node);

        PipelineExecInstanceNode nextWaitingNode = execNodeService.findNextWaitingChildNode(node.getId());
        nodeExecutorCenter.execute(nextWaitingNode);
    }

    @Override
    public void register(NodeExecutorCenter nodeExecutorCenter) {
        this.nodeExecutorCenter = nodeExecutorCenter;
        nodeExecutorCenter.register(ExecInstanceNodeType.PIPELINE, this);
    }


}
