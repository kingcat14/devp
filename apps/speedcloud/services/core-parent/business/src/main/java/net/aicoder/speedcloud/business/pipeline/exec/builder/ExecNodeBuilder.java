package net.aicoder.speedcloud.business.pipeline.exec.builder;

import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;

public interface ExecNodeBuilder {

    /**
     * 给一个节点创建执行节点
     * @param parentNode
     * @param nodeId
     * @return
     */
    PipelineExecNode createExecNode(PipelineExecNode parentNode, Long nodeId, boolean createSubNode);

    void register();
}
