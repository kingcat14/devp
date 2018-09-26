package net.aicoder.speedcloud.business.pipeline.exec.builder;

import net.aicoder.speedcloud.business.pipeline.constant.ExecNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.ExecMode;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeStatus;
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

    public PipelineExecNode build(PipelineExecNode parentNode, String type, Long id, boolean createSubNode){

        PipelineExecNode result = null;
        NodeBuilder nodeBuilder = nodeBuilderMap.get(type);
        if(nodeBuilder != null){
            result = nodeBuilder.createExecNode(parentNode, id, createSubNode);
        }

        return result;
    }


    public PipelineExecNode buildTopNode(PipelineExecInstance instance, Long id, boolean createSubNode){

        PipelineExecNode fakeParentNode = this.getFakeParentNode(instance);

        return this.build(fakeParentNode, instance.getExecuteTargetType(), id, createSubNode);

    }

    public PipelineExecNode buildPipelineInstance(PipelineExecInstance instance, Long id, boolean createSubNode){

        PipelineExecNode fakeParentNode = this.getFakeParentNode(instance);

        return this.build(fakeParentNode, ExecNodeType.PIPELINE, id, createSubNode);

    }

    public PipelineExecNode buildTaskInstance(PipelineExecInstance instance, Long id, boolean createSubNode){

        PipelineExecNode fakeParentNode = this.getFakeParentNode(instance);

        return this.build(fakeParentNode, ExecNodeType.TASK, id, createSubNode);
    }

    /**
     * 使用instance创建一个fake父节点
     * @param instance
     * @return
     */
    private PipelineExecNode getFakeParentNode(PipelineExecInstance instance){
        PipelineExecNode fakeParentNode = new PipelineExecNode();
        fakeParentNode.setTid(instance.getTid());
        fakeParentNode.setId(-1L);
        fakeParentNode.setExec(instance.getId());
        return fakeParentNode;
    }

    public void register(String type, NodeBuilder nodeBuilder){
        this.nodeBuilderMap.put(type, nodeBuilder);
    }



}
