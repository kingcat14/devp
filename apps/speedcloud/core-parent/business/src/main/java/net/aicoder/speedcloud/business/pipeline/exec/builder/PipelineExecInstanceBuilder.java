package net.aicoder.speedcloud.business.pipeline.exec.builder;

import net.aicoder.speedcloud.business.pipeline.constant.ExecInstanceNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.ExecMode;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeStatus;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 创建执行实例
 */
@Component
public class PipelineExecInstanceBuilder {

    private HashMap<String, NodeBuilder> nodeBuilderMap = new HashMap<>();

    public PipelineExecInstanceNode build(PipelineExecInstanceNode parentNode, String type, Long id, int execIndex){

        PipelineExecInstanceNode result = null;
        NodeBuilder nodeBuilder = nodeBuilderMap.get(type);
        if(nodeBuilder != null){
            result = nodeBuilder.createExecNode(parentNode, id, execIndex);
        }

        return result;
    }


    public PipelineExecInstanceNode buildPipelineInstance(PipelineExecInstance instance, Long id){

        PipelineExecInstanceNode fakeParentNode = this.getFakeParentNode(instance);

        return this.build(fakeParentNode, ExecInstanceNodeType.PIPELINE, id, 0);

    }

    public PipelineExecInstanceNode buildTaskInstance(PipelineExecInstance instance, Long id){

        PipelineExecInstanceNode fakeParentNode = this.getFakeParentNode(instance);

        return this.build(fakeParentNode, ExecInstanceNodeType.TASK, id, 0);
    }

    /**
     * 使用instance创建一个fake父节点
     * @param instance
     * @return
     */
    private PipelineExecInstanceNode getFakeParentNode(PipelineExecInstance instance){
        PipelineExecInstanceNode fakeParentNode = new PipelineExecInstanceNode();
        fakeParentNode.setTid(instance.getTid());
        fakeParentNode.setId(-1L);
        fakeParentNode.setExec(instance.getId());
        return fakeParentNode;
    }

    public void register(String type, NodeBuilder nodeBuilder){
        this.nodeBuilderMap.put(type, nodeBuilder);
    }



}
