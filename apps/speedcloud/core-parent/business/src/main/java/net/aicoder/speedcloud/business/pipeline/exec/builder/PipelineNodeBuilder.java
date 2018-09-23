package net.aicoder.speedcloud.business.pipeline.exec.builder;

import net.aicoder.speedcloud.business.pipeline.constant.ExecInstanceNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.ExecMode;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeStatus;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class PipelineNodeBuilder implements NodeBuilder {

    @Autowired()@Qualifier("pipelineExecInstanceNodeService")
    private PipelineExecInstanceNodeService execNodeService;

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private PipelineStageService pipelineStageService;

    @Autowired
    private PipelineExecInstanceBuilder pipelineExecInstanceBuilder;

    @Override
    public PipelineExecInstanceNode createExecNode(PipelineExecInstanceNode parentNode, Long id, int execIndex) {
        Pipeline pipeline = pipelineService.find(id);

        //创建执行节点
        PipelineExecInstanceNode pipelineExecNode = new PipelineExecInstanceNode();
        pipelineExecNode.setNodeType(ExecInstanceNodeType.PIPELINE);
        pipelineExecNode.setAutoStart(true);
        pipelineExecNode.setName(pipeline.getName());
        pipelineExecNode.setParentId(parentNode.getId());
        pipelineExecNode.setExec(parentNode.getExec());
        pipelineExecNode.setTask(pipeline.getId());
        pipelineExecNode.setExecIndex(0);
        pipelineExecNode.setExecMode(ExecMode.SERIALIZED);
        pipelineExecNode.setTid(parentNode.getTid());
        pipelineExecNode.setStatus(PipelineExecInstanceNodeStatus.WAIT);
        execNodeService.add(pipelineExecNode);

        List<PipelineStage> stageList = pipelineStageService.findForPipeline(id);

        for(int i = 0; CollectionUtils.isNotEmpty(stageList) && (i < CollectionUtils.size(stageList)) ; i++){
            PipelineStage pipelineStage = stageList.get(i);
            pipelineExecInstanceBuilder.build(pipelineExecNode, ExecInstanceNodeType.STAGE, pipelineStage.getId(), i);
        }

        return pipelineExecNode;
    }


    @PostConstruct
    public void register(){
        pipelineExecInstanceBuilder.register(ExecInstanceNodeType.PIPELINE, this);
    }
}
