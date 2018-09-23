package net.aicoder.speedcloud.business.pipeline.exec.builder;

import net.aicoder.speedcloud.business.pipeline.constant.ExecInstanceNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.ExecMode;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNodeParam;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParam;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeParamService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeStatus;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageNodeParamService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageNodeService;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskParamService;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskNodeBuilder implements NodeBuilder {

    @Autowired
    private PipelineExecInstanceBuilder pipelineExecInstanceBuilder;

    @Autowired
    private PipelineTaskService pipelineTaskService;

    @Autowired
    private PipelineStageNodeService pipelineStageNodeService;


    @Autowired
    private PipelineStageNodeParamService pipelineStageNodeParamService;


    @Autowired()@Qualifier("pipelineExecInstanceNodeService")
    private PipelineExecInstanceNodeService execNodeService;

    @Autowired
    private PipelineExecInstanceNodeParamService pipelineExecInstanceNodeParamService;

    @Override
    public PipelineExecInstanceNode createExecNode(PipelineExecInstanceNode parentNode, Long nodeId, int execIndex) {

        PipelineStageNode pipelineStageNode = pipelineStageNodeService.find(nodeId);

        PipelineTask pipelineTask = pipelineTaskService.find(pipelineStageNode.getNodeId());

        //创建执行节点
        PipelineExecInstanceNode node = new PipelineExecInstanceNode();
        node.setNodeType(ExecInstanceNodeType.TASK);
        node.setAutoStart(true);
        node.setName(pipelineTask.getName());
        node.setParentId(parentNode.getId());
        node.setExec(parentNode.getExec());
        node.setTask(pipelineTask.getId());
        node.setExecIndex(execIndex);
        node.setExecMode(ExecMode.SERIALIZED);
        node.setTid(parentNode.getTid());
        node.setStatus(PipelineExecInstanceNodeStatus.WAIT);

        execNodeService.add(node);

        List<PipelineStageNodeParam> paramList = pipelineStageNodeParamService.findByNodeId(nodeId);

        List<PipelineExecInstanceNodeParam> nodeParamList = new ArrayList<>();

        for(PipelineStageNodeParam param : paramList){
            PipelineExecInstanceNodeParam nodeParam = new PipelineExecInstanceNodeParam();
            nodeParam.setNode(node.getId());
            nodeParam.setName(param.getName());
            nodeParam.setValue(param.getValue());
            nodeParam.setTid(parentNode.getTid());
            nodeParamList.add(nodeParam);
        }

        pipelineExecInstanceNodeParamService.add(nodeParamList);

        return node;
    }

    @Override
    @PostConstruct
    public void register() {
        pipelineExecInstanceBuilder.register(ExecInstanceNodeType.TASK, this);
    }
}
