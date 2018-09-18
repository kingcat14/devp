package net.aicoder.speedcloud.business.pipeline.exec.service;


import net.aicoder.speedcloud.business.pipeline.constant.ExecInstanceNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.ExecMode;
import net.aicoder.speedcloud.business.pipeline.constant.GlobalLock;
import net.aicoder.speedcloud.business.pipeline.constant.PipelineExecInstanceStatus;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.exec.builder.PipelineExecInstanceBuilder;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParam;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageNodeService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageService;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskParamService;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service("execNodeAction")
public class ExecNodeAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecNodeAction.class);

	@Autowired
	private PipelineExecInstanceBuilder pipelineExecInstanceBuilder;

	@Autowired()@Qualifier("pipelineExecInstanceNodeService")
	private PipelineExecInstanceNodeService execNodeService;

	@Autowired
	private PipelineExecInstanceNodeService pipelineExecInstanceNodeService;

	@Autowired
	private PipelineExecInstanceNodeParamService pipelineExecInstanceNodeParamService;

	@Autowired
	private PipelineTaskService pipelineTaskService;

	@Autowired
	private PipelineTaskParamService pipelineTaskParamService;

	@Autowired
	private PipelineService pipelineService;

	@Autowired
	private PipelineStageService pipelineStageService;

	@Autowired
	private PipelineStageNodeService pipelineStageNodeService;


	/**
	 * 给一个Pipeline创建执行节点
	 */
	public PipelineExecInstanceNode createPipelineExecNode(PipelineExecInstance instance, Long pipelineId) {

		Pipeline pipeline = pipelineService.find(pipelineId);

		//创建执行节点
		PipelineExecInstanceNode pipelineExecNode = new PipelineExecInstanceNode();
		pipelineExecNode.setNodeType(ExecInstanceNodeType.PIPELINE);
		pipelineExecNode.setAutoStart(true);
		pipelineExecNode.setName(pipeline.getName());
		pipelineExecNode.setParentId(-1L);
		pipelineExecNode.setExec(instance.getId());
		pipelineExecNode.setTask(pipeline.getId());
		pipelineExecNode.setExecIndex(0);
		pipelineExecNode.setExecMode(ExecMode.SERIALIZED);
		pipelineExecNode.setTid(instance.getTid());
		pipelineExecNode.setStatus(PipelineExecInstanceNodeStatus.WAIT);
		execNodeService.add(pipelineExecNode);

		List<PipelineStage> stageList = pipelineStageService.findForPipeline(pipelineId);

		for(int i = 0; CollectionUtils.isNotEmpty(stageList) && (i < CollectionUtils.size(stageList)) ; i++){
			PipelineStage pipelineStage = stageList.get(i);
			this.createStageExecNode(pipelineExecNode, pipelineStage.getId(), i);
		}

		return pipelineExecNode;

	}

	/**
	 * 给一个stage创建执行节点
	 */
	public PipelineExecInstanceNode createStageExecNode(PipelineExecInstanceNode execNode, Long stageId, int execIndex){

		PipelineStage pipelineStage = pipelineStageService.find(stageId);

		//创建执行节点
		PipelineExecInstanceNode stageExecNode = new PipelineExecInstanceNode();
		stageExecNode.setNodeType(ExecInstanceNodeType.STAGE);
		stageExecNode.setAutoStart(StringUtils.equalsIgnoreCase("AUTO", pipelineStage.getFlowType()));
		stageExecNode.setName(pipelineStage.getName());
		stageExecNode.setParentId(-1L);
		stageExecNode.setExec(execNode.getExec());
		stageExecNode.setTask(pipelineStage.getId());
		stageExecNode.setExecIndex(execIndex);
		stageExecNode.setExecMode(pipelineStage.getExecMode());
		stageExecNode.setTid(execNode.getTid());
		stageExecNode.setStatus(PipelineExecInstanceNodeStatus.WAIT);
		execNodeService.add(stageExecNode);

		//开始处理stage关联的节点
		List<PipelineStageNode> stageNodeList = pipelineStageNodeService.findByStage(stageId);
		for(int i = 0; i < CollectionUtils.size(stageNodeList); i++){
			PipelineStageNode stageNode =  stageNodeList.get(i);
			this.createTaskExecNode(stageExecNode, stageNode.getNodeId(), i);
		}


		return stageExecNode;
	}

	/**
	 * 给一个任务创建执行任务
	 * @param instance
	 * @param taskId
	 */
	public PipelineExecInstanceNode createTaskExecNode(PipelineExecInstance instance, Long taskId, int execIndex){

		PipelineTask pipelineTask = pipelineTaskService.find(taskId);

		//创建执行节点
		PipelineExecInstanceNode node = new PipelineExecInstanceNode();
		node.setNodeType(ExecInstanceNodeType.TASK);
		node.setAutoStart(true);
		node.setName(pipelineTask.getName());
		node.setParentId(-1L);
		node.setExec(instance.getId());
		node.setTask(instance.getExecuteTargetId());
		node.setExecIndex(execIndex);
		node.setExecMode(ExecMode.SERIALIZED);
		node.setTid(instance.getTid());
		node.setStatus(PipelineExecInstanceNodeStatus.WAIT);

		execNodeService.add(node);

		List<PipelineTaskParam> paramList = pipelineTaskParamService.findByTask(taskId);

		List<PipelineExecInstanceNodeParam> nodeParamList = new ArrayList<>();

		for(PipelineTaskParam param : paramList){
			PipelineExecInstanceNodeParam nodeParam = new PipelineExecInstanceNodeParam();
			nodeParam.setNode(node.getId());
			nodeParam.setName(param.getName());
			nodeParam.setValue(param.getValue());
			nodeParam.setTid(instance.getTid());
			nodeParamList.add(nodeParam);
		}

		pipelineExecInstanceNodeParamService.add(nodeParamList);

		return node;
	}


	/**
	 *
	 * @param parentNode
	 * @param taskId
	 * @param execIndex
	 * @return
	 */
	public PipelineExecInstanceNode createTaskExecNode(PipelineExecInstanceNode parentNode, Long taskId, int execIndex){

		PipelineTask pipelineTask = pipelineTaskService.find(taskId);

		//创建执行节点
		PipelineExecInstanceNode node = new PipelineExecInstanceNode();
		node.setNodeType(ExecInstanceNodeType.TASK);
		node.setAutoStart(true);
		node.setName(pipelineTask.getName());
		node.setParentId(parentNode.getId());
		node.setExec(parentNode.getExec());
		node.setTask(taskId);
		node.setExecIndex(execIndex);
		node.setExecMode(ExecMode.SERIALIZED);
		node.setTid(parentNode.getTid());
		node.setStatus(PipelineExecInstanceNodeStatus.WAIT);

		execNodeService.add(node);

		List<PipelineTaskParam> paramList = pipelineTaskParamService.findByTask(taskId);

		List<PipelineExecInstanceNodeParam> nodeParamList = new ArrayList<>();

		for(PipelineTaskParam param : paramList){
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



	/**
	 * 开始执行一个任务节点
	 * @param node
	 */
	public void runTaskNode(PipelineExecInstanceNode node){
		node.setStatus(PipelineExecInstanceNodeStatus.PREPARED);
		execNodeService.merge(node);
	}


	/**
	 * 开始执行一个Stage节点
	 * @param node
	 */
	public void runStageNode(PipelineExecInstanceNode node){
		node.setStatus(PipelineExecInstanceNodeStatus.RUNNING);
		execNodeService.merge(node);

		if(ExecMode.PARALLEL.equals(node.getExecMode())){
            runAllChildNode(node.getId());
		}else{
			PipelineExecInstanceNode nextWaitingNode = execNodeService.findNextWaitingChildNode(node.getId());
			//这里默认stage子任务是task,

			if(nextWaitingNode == null){
				return;
			}

			runNode(nextWaitingNode);
		}
	}

	/**
	 * 开始执行一个pipeline跟节点
	 * @param node
	 */
	public void runPipelineNode(PipelineExecInstanceNode node){
		node.setStatus(PipelineExecInstanceNodeStatus.RUNNING);

		PipelineExecInstanceNode nextWaitingNode = execNodeService.findNextWaitingChildNode(node.getId());
		runNode(nextWaitingNode);
	}

	/**
	 * 启动所有的子节点
	 * @param parentNode
	 */
	private void runAllChildNode(Long parentNode){
		List<PipelineExecInstanceNode> nodeList = execNodeService.findChildNode(parentNode);

		for(PipelineExecInstanceNode node : nodeList){
			runNode(node);
		}

	}

	public void runNode(PipelineExecInstanceNode node){

		if(node == null){
			LOGGER.warn("try start null node. return.");
			return;
		}

		if(StringUtils.equalsIgnoreCase("TASK", node.getNodeType())){
			runTaskNode(node);
		}
		else if(StringUtils.equalsIgnoreCase(ExecInstanceNodeType.PIPELINE, node.getNodeType())){
			runPipelineNode(node);
		}
		else if(StringUtils.equalsIgnoreCase("STAGE", node.getNodeType())){
			runStageNode(node);
		}else{
			LOGGER.error("try start unknown node type: {}", node.getNodeType());
			LOGGER.error(node.toString());
		}
	}

	public void finishNode(PipelineExecInstanceNode node){

		//保存node
		node.setStatus("FINISH");

		//TODO 这个全局锁，回头要换成使用redis或其他的真全局锁;根据所操作的节点父节点ID加锁
		GlobalLock.lock(node.getParentId()) ;

		pipelineExecInstanceNodeService.merge(node);

		//子节点结束事件
		childFinish(node);

		GlobalLock.unlock(node.getParentId());
	}

	/**
	 * 子节点结束
	 * @param childNode
	 */
	public void childFinish(PipelineExecInstanceNode childNode){

		/*
		 * 1.找到子节点的父节点（作为当前节点）
		 * 2.找到改父节点下所有子节点
		 * 3.如果所有子节点已结束，则结束改节点，通知当前节点的父节点当前节点结束
		 * 4.如果有子节点未结束，则尝试找到改节点的某一个在等待的子节点
		 * 4.1如果找到等待的子节点，则开始下一个节点
		 */

		//获取父节点
		PipelineExecInstanceNode currentNode = pipelineExecInstanceNodeService.find(childNode.getParentId());


		//找到所有子节点
		List<PipelineExecInstanceNode> nodeList = execNodeService.findChildNode(currentNode.getId());
		boolean currentNodeFinish = true;
		for(PipelineExecInstanceNode node:nodeList){
			currentNodeFinish = currentNodeFinish && StringUtils.equalsIgnoreCase(PipelineExecInstanceStatus.FINISH, node.getStatus());
		}

		//3
		if(currentNodeFinish){
			finishNode(currentNode);
			return;
		}

		//4
		PipelineExecInstanceNode nextWaitingNode = execNodeService.findNextWaitingChildNode(currentNode.getId());
		//这里默认stage子任务是task,

		if(nextWaitingNode == null){
			return;
		}
		//4.1
		runNode(nextWaitingNode);

	}

}