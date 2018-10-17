package net.aicoder.speedcloud.business.pipeline.exec.builder;

import net.aicoder.speedcloud.business.pipeline.constant.ExecNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.ExecMode;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeStatus;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class PipelineBuilder implements ExecNodeBuilder {

    @Autowired()@Qualifier("pipelineExecNodeService")
    private PipelineExecNodeService execNodeService;

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private PipelineStageService pipelineStageService;

    @Autowired
    private PipelineExecInstanceBuilder pipelineExecInstanceBuilder;

    @Override
    public PipelineExecNode createExecNode(PipelineExecNode parentNode, Long id, boolean createSubNode) {

        Pipeline pipeline = pipelineService.find(id);

        //创建执行节点
        PipelineExecNode pipelineExecNode = new PipelineExecNode();
        pipelineExecNode.setNodeType(ExecNodeType.PIPELINE);
        pipelineExecNode.setAutoStart(true);
        pipelineExecNode.setName(pipeline.getName());
        pipelineExecNode.setParentId(parentNode.getId());
        pipelineExecNode.setExec(parentNode.getExec());
        pipelineExecNode.setRelationObjId(pipeline.getId());
        pipelineExecNode.setExecIndex(0);
        pipelineExecNode.setExecMode(ExecMode.SERIALIZED);
        pipelineExecNode.setTid(parentNode.getTid());
        pipelineExecNode.setStatus(PipelineExecNodeStatus.WAIT);
        execNodeService.add(pipelineExecNode);

        if(createSubNode){
            handleSubNode(pipelineExecNode, id, createSubNode);
        }

        return pipelineExecNode;
    }

    private void handleSubNode(PipelineExecNode pipelineExecNode, Long id, boolean createSubNode){
        List<PipelineStage> stageList = pipelineStageService.findForPipeline(id);

        for(int i = 0; CollectionUtils.isNotEmpty(stageList) && (i < CollectionUtils.size(stageList)) ; i++){
            PipelineStage pipelineStage = stageList.get(i);
            pipelineExecInstanceBuilder.build(pipelineExecNode, ExecNodeType.STAGE, pipelineStage.getId(), createSubNode);
        }
    }


    @PostConstruct
    public void register(){
        pipelineExecInstanceBuilder.register(ExecNodeType.PIPELINE, this);
    }
}
