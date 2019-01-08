package net.aicoder.speedcloud.business.pipeline.exec.executor;

import net.aicoder.speedcloud.business.pipeline.constant.ExecNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.PipelineExecNodeStatus;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 任务节点的执行逻辑
 */
@Component
public class TaskNodeExecutor implements NodeExecutor {

    @Autowired()@Qualifier("pipelineExecNodeService")
    private PipelineExecNodeService execNodeService;

    @Autowired
    private NodeExecutorCenter nodeExecutorCenter;

    @Override
    public void execute(PipelineExecNode node){
        node.setStatus(PipelineExecNodeStatus.PREPARED);
        execNodeService.merge(node);
    }

    @PostConstruct
    public void register() {
        nodeExecutorCenter.register(ExecNodeType.TASK, this);
    }


}
