package net.aicoder.speedcloud.business.pipeline.exec.builder;

import net.aicoder.speedcloud.business.pipeline.constant.ExecInstanceNodeType;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeStatus;
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
public class StageNodeBuilder implements NodeBuilder {


    @Autowired
    private PipelineExecInstanceBuilder pipelineExecInstanceBuilder;

    @Autowired
    private PipelineStageService pipelineStageService;

    @Autowired
    private PipelineStageNodeService pipelineStageNodeService;

    @Autowired()@Qualifier("pipelineExecInstanceNodeService")
    private PipelineExecInstanceNodeService execNodeService;


    @Override
    public PipelineExecInstanceNode createExecNode(PipelineExecInstanceNode parentNode, Long stageId, int execIndex) {

        PipelineStage pipelineStage = pipelineStageService.find(stageId);

        //创建执行节点
        PipelineExecInstanceNode stageExecNode = new PipelineExecInstanceNode();
        stageExecNode.setNodeType(ExecInstanceNodeType.STAGE);
        stageExecNode.setAutoStart(StringUtils.equalsIgnoreCase("AUTO", pipelineStage.getFlowType()));
        stageExecNode.setName(pipelineStage.getName());
        stageExecNode.setParentId(parentNode.getId());
        stageExecNode.setExec(parentNode.getExec());
        stageExecNode.setTask(pipelineStage.getId());
        stageExecNode.setExecIndex(execIndex);
        stageExecNode.setExecMode(pipelineStage.getExecMode());
        stageExecNode.setTid(parentNode.getTid());
        stageExecNode.setStatus(PipelineExecInstanceNodeStatus.WAIT);
        execNodeService.add(stageExecNode);

        //开始处理stage关联的节点
        List<PipelineStageNode> stageNodeList = pipelineStageNodeService.findByStage(stageId);
        for(int i = 0; i < CollectionUtils.size(stageNodeList); i++){
            PipelineStageNode stageNode =  stageNodeList.get(i);
            pipelineExecInstanceBuilder.build(stageExecNode, stageNode.getNodeType(), stageNode.getId(), i);
        }

        return stageExecNode;
    }


    @PostConstruct
    public void register(){
        pipelineExecInstanceBuilder.register(ExecInstanceNodeType.STAGE, this);
    }

}
