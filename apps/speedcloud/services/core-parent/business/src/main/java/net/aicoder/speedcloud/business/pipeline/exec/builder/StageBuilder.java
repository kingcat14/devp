package net.aicoder.speedcloud.business.pipeline.exec.builder;

import net.aicoder.speedcloud.business.pipeline.constant.ExecFlowType;
import net.aicoder.speedcloud.business.pipeline.constant.ExecNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.PipelineExecNodeStatus;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageNodeService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class StageBuilder implements ExecNodeBuilder {


    @Autowired
    private PipelineExecInstanceBuilder pipelineExecInstanceBuilder;

    @Autowired
    private PipelineStageService pipelineStageService;

    @Autowired
    private PipelineStageNodeService pipelineStageNodeService;

    @Autowired()@Qualifier("pipelineExecNodeService")
    private PipelineExecNodeService execNodeService;


    @Override
    public PipelineExecNode createExecNode(PipelineExecNode parentNode, Long stageId, boolean createSubNode) {

        PipelineStage pipelineStage = pipelineStageService.find(stageId);

        //创建执行节点
        PipelineExecNode stageExecNode = new PipelineExecNode();
        stageExecNode.setNodeType(ExecNodeType.STAGE);
        stageExecNode.setAutoStart(!StringUtils.equalsIgnoreCase(ExecFlowType.MANUAL, pipelineStage.getFlowType()));
        stageExecNode.setName(pipelineStage.getName());
        stageExecNode.setParentId(parentNode.getId());
        stageExecNode.setExec(parentNode.getExec());
        stageExecNode.setRelationObjId(pipelineStage.getId());
//        stageExecNode.setExecIndex(execIndex);
        stageExecNode.setExecIndex(pipelineStage.getExecOrder());
        stageExecNode.setExecMode(pipelineStage.getExecMode());
        stageExecNode.setTid(parentNode.getTid());
        stageExecNode.setStatus(PipelineExecNodeStatus.WAIT);

        execNodeService.add(stageExecNode);

        if(createSubNode) {
            handleSubNode(stageId, createSubNode, stageExecNode);
        }
        return stageExecNode;
    }

    private void handleSubNode(Long stageId, boolean createSubNode, PipelineExecNode stageExecNode) {
        //开始处理stage关联的节点
        List<PipelineStageNode> stageNodeList = pipelineStageNodeService.findByStage(stageId);
        for(int i = 0; i < CollectionUtils.size(stageNodeList); i++){
            PipelineStageNode stageNode =  stageNodeList.get(i);
            pipelineExecInstanceBuilder.build(stageExecNode, stageNode.getObjType(), stageNode.getId(), createSubNode);
        }
    }


    @PostConstruct
    public void register(){
        pipelineExecInstanceBuilder.register(ExecNodeType.STAGE, this);
    }

}
