package net.aicoder.speedcloud.business.pipeline.exec.builder;

import net.aicoder.speedcloud.business.pipeline.constant.ExecInstanceNodeType;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;

public interface NodeBuilder {

    PipelineExecInstanceNode createExecNode(PipelineExecInstanceNode parentNode, Long id, int execIndex);

    void register();
}
