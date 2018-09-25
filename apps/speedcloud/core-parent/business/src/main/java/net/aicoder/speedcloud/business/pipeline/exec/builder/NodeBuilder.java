package net.aicoder.speedcloud.business.pipeline.exec.builder;

import net.aicoder.speedcloud.business.pipeline.constant.ExecNodeType;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;

public interface NodeBuilder {

    /**
     * 给一个节点创建执行节点
     * @param parentNode
     * @param id
     * @param execIndex
     * @return
     */
    PipelineExecNode createExecNode(PipelineExecNode parentNode, Long id, int execIndex, boolean createSubNode);

    void register();
}
