package net.aicoder.speedcloud.business.pipeline.exec.executor;

import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;

/**
 * 节点执行器
 */
public interface NodeExecutor {

    void execute(PipelineExecInstanceNode node);

    void register();
}
