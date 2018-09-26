package net.aicoder.speedcloud.business.pipeline.exec.builder;

import net.aicoder.speedcloud.business.pipeline.constant.ExecMode;
import net.aicoder.speedcloud.business.pipeline.constant.ExecNodeType;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNodeParam;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeParam;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeParamService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeStatus;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageNodeParamService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageNodeService;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
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


    @Autowired()@Qualifier("pipelineExecNodeService")
    private PipelineExecNodeService execNodeService;

    @Autowired
    private PipelineExecNodeParamService pipelineExecNodeParamService;

    @Override
    public PipelineExecNode createExecNode(PipelineExecNode parentNode, Long nodeId, int execIndex, boolean createSubNode) {

        PipelineStageNode pipelineStageNode = pipelineStageNodeService.find(nodeId);

        PipelineTask pipelineTask = pipelineTaskService.find(pipelineStageNode.getObjId());

        //创建执行节点
        PipelineExecNode node = new PipelineExecNode();
        node.setNodeType(ExecNodeType.TASK);
        node.setAutoStart(true);
        node.setName(pipelineTask.getName());
        node.setParentId(parentNode.getId());
        node.setExec(parentNode.getExec());
        node.setRelationObjId(pipelineTask.getId());
        node.setStageNode(pipelineStageNode.getId());
        node.setExecIndex(execIndex);
        node.setExecMode(ExecMode.SERIALIZED);
        node.setTid(parentNode.getTid());
        node.setStatus(PipelineExecNodeStatus.WAIT);

        execNodeService.add(node);

        List<PipelineStageNodeParam> paramList = pipelineStageNodeParamService.findByNodeId(nodeId);

        List<PipelineExecNodeParam> nodeParamList = new ArrayList<>();

        for(PipelineStageNodeParam param : paramList){
            PipelineExecNodeParam nodeParam = new PipelineExecNodeParam();
            nodeParam.setNode(node.getId());
            nodeParam.setName(param.getName());
            nodeParam.setValue(param.getValue());
            nodeParam.setTid(parentNode.getTid());
            nodeParamList.add(nodeParam);
        }

        pipelineExecNodeParamService.add(nodeParamList);

        return node;
    }

    @Override
    @PostConstruct
    public void register() {
        pipelineExecInstanceBuilder.register(ExecNodeType.TASK, this);
        pipelineExecInstanceBuilder.register(ExecNodeType.TASK_COMPILE, this);
        pipelineExecInstanceBuilder.register(ExecNodeType.TASK_DEPLOY, this);
    }
}
