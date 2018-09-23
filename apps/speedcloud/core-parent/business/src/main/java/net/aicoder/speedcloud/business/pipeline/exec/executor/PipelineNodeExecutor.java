package net.aicoder.speedcloud.business.pipeline.exec.executor;

import net.aicoder.speedcloud.business.pipeline.constant.ExecInstanceNodeType;
import net.aicoder.speedcloud.business.pipeline.exec.builder.NodeBuilder;
import net.aicoder.speedcloud.business.pipeline.exec.builder.PipelineExecInstanceBuilder;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

/**
 * 流水线节点的执行逻辑
 */
@Component
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

    @PostConstruct
    public void register() {
        nodeExecutorCenter.register(ExecInstanceNodeType.PIPELINE, this);
    }


}
