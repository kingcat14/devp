package net.aicoder.speedcloud.business.pipeline.exec.executor;

import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;

/**
 * 节点执行器
 */
public interface NodeExecutor {

    /**执行节点*/
    void execute(PipelineExecNode node);

    void register();
}
