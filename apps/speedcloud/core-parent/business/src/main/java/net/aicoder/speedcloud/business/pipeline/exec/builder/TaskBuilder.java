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
public class TaskBuilder implements ExecNodeBuilder {

    @Autowired
    private PipelineExecInstanceBuilder pipelineExecInstanceBuilder;

    @Autowired
    private PipelineTaskService pipelineTaskService;

    @Autowired
    private PipelineTaskParamService pipelineTaskParamService;

    @Autowired
    private PipelineStageNodeService pipelineStageNodeService;


    @Autowired
    private PipelineStageNodeParamService pipelineStageNodeParamService;


    @Autowired()@Qualifier("pipelineExecNodeService")
    private PipelineExecNodeService execNodeService;

    @Autowired
    private PipelineExecNodeParamService pipelineExecNodeParamService;

    @Override
    public PipelineExecNode createExecNode(PipelineExecNode parentNode, Long nodeId, boolean createSubNode) {

        PipelineStageNode pipelineStageNode = pipelineStageNodeService.find(nodeId);

        PipelineExecNode pipelineExecNode;

        //如果不是阶段节点,则有可能是执行执行任务
        if(pipelineStageNode == null){
            pipelineExecNode = getTaskExecNode(parentNode, nodeId);
        }
        else if(pipelineStageNode != null && ExecNodeType.PIPELINE.equals(pipelineStageNode.getObjType())){
           //如果节点类型是流水线
           pipelineExecNode = getPipelineExecNode(parentNode, pipelineStageNode, true);
        }else{
            pipelineExecNode = getTaskExecNode(parentNode, nodeId, pipelineStageNode);
        }

        return pipelineExecNode;
    }

    private PipelineExecNode getPipelineExecNode(PipelineExecNode parentNode, PipelineStageNode pipelineStageNode, boolean createSubNode) {
        PipelineExecNode pipelineExecNode = pipelineExecInstanceBuilder.build(parentNode, ExecNodeType.PIPELINE, pipelineStageNode.getObjId(), createSubNode);
        pipelineExecNode.setExecIndex(pipelineExecNode.getExecIndex());
        execNodeService.merge(pipelineExecNode);
        return pipelineExecNode;
    }

    //独立执行任务时，创建任务执行节点
    private PipelineExecNode getTaskExecNode(PipelineExecNode parentNode, Long nodeId){
        PipelineTask pipelineTask = pipelineTaskService.find(nodeId);
        //创建执行节点
        PipelineExecNode node = getPipelineExecNode(pipelineTask);
        node.setParentId(parentNode.getId());
        node.setExec(parentNode.getExec());
        node.setExecIndex(0);
        node.setTid(parentNode.getTid());

        execNodeService.add(node);

        List<PipelineTaskParam> pipelineTaskParamList = pipelineTaskParamService.findByTask(nodeId);

        List<PipelineExecNodeParam> nodeParamList = new ArrayList<>();

        for(PipelineTaskParam param : pipelineTaskParamList){
            PipelineExecNodeParam nodeParam = new PipelineExecNodeParam();
            nodeParam.setNode(node.getId());
            nodeParam.setName(param.getName());
            nodeParam.setValue(param.getDefaultValue());
            nodeParam.setTid(parentNode.getTid());
            nodeParamList.add(nodeParam);
        }

        pipelineExecNodeParamService.add(nodeParamList);

        return node;
    }

    private PipelineExecNode getTaskExecNode(PipelineExecNode parentNode, Long nodeId, PipelineStageNode pipelineStageNode) {

        PipelineTask pipelineTask = pipelineTaskService.find(pipelineStageNode.getObjId());

        //创建执行节点
        PipelineExecNode node = getPipelineExecNode(pipelineTask);

        node.setParentId(parentNode.getId());
        node.setExec(parentNode.getExec());
        node.setStageNode(pipelineStageNode.getId());
        node.setExecIndex(pipelineStageNode.getExecOrder());

        node.setTid(parentNode.getTid());

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

    private PipelineExecNode getPipelineExecNode(PipelineTask pipelineTask) {
        PipelineExecNode node = new PipelineExecNode();
        node.setNodeType(ExecNodeType.TASK);
        node.setAutoStart(true);
        node.setName(pipelineTask.getName());
        node.setRelationObjId(pipelineTask.getId());
        node.setStatus(PipelineExecNodeStatus.WAIT);
        node.setExecMode(ExecMode.SERIALIZED);
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
