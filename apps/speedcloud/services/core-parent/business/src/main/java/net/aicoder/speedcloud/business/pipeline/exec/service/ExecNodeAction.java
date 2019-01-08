package net.aicoder.speedcloud.business.pipeline.exec.service;


import net.aicoder.speedcloud.business.pipeline.constant.GlobalLock;
import net.aicoder.speedcloud.business.pipeline.constant.PipelineExecNodeResult;
import net.aicoder.speedcloud.business.pipeline.constant.PipelineExecNodeStatus;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.executor.NodeExecutorCenter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("execNodeAction")
public class ExecNodeAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecNodeAction.class);

	@Autowired
	private NodeExecutorCenter nodeExecutorCenter;

	@Autowired()@Qualifier("pipelineExecNodeService")
	private PipelineExecNodeService execNodeService;

	@Autowired
	private PipelineExecInstanceService pipelineExecInstanceService;

	@Autowired
	private PipelineExecNodeService pipelineExecNodeService;

	public void execute(PipelineExecNode node){

		nodeExecutorCenter.execute(node);
	}

	@Transactional
	public void finishNode(PipelineExecNode node){

		//保存node
		node.setStatus("FINISH");
		node.setEndTime(new Date());
		try {
			node.setMillisecondsCost(node.getEndTime().getTime() - node.getStartTime().getTime());

		}catch (Exception e){
			LOGGER.error("CAN NOT calculate time cost", e);
			LOGGER.error(node.toString());
		}

		//TODO 这个全局锁，回头要换成使用redis或其他的跨进程的全局锁;根据所操作的节点父节点ID加锁
		GlobalLock.lock(node.getParentId()) ;

		pipelineExecNodeService.merge(node);

		//获取父节点
		PipelineExecNode parentNode = pipelineExecNodeService.find(node.getParentId());

		//如果有父节点
		if(parentNode != null){
			//子节点结束事件
			childFinish(node);
		}else{
			PipelineExecInstance instance = pipelineExecInstanceService.find(node.getExec());
			instance.setStatus("FINISH");
			instance.setResult(node.getResult());
			instance.setFinishTime(new Date());
			pipelineExecInstanceService.merge(instance);
		}



		GlobalLock.unlock(node.getParentId());
	}

	/**
	 * 子节点结束
	 * @param childNode
	 */
	public void childFinish(PipelineExecNode childNode){

		/*
		 * 1.找到子节点的父节点（作为当前节点）
		 * 1.1如果子节点失败, 则把当前节点的父节点的所有子节点都标注为失败
		 * 2.找到改父节点下所有子节点
		 * 3.如果所有子节点已结束，则结束改节点，通知当前节点的父节点当前节点结束
		 * 4.如果有子节点未结束，则尝试找到改节点的某一个在等待的子节点
		 * 4.1如果找到等待的子节点，则开始下一个节点
		 */

		//1.获取父节点
		PipelineExecNode currentNode = pipelineExecNodeService.find(childNode.getParentId());
		if(!childNode.success()){
			currentNode.setResult(PipelineExecNodeResult.FAILURE);
			cancelAllChild(currentNode);
			finishNode(currentNode);
		}


		//2.找到所有子节点
		List<PipelineExecNode> nodeList = execNodeService.findChildNode(currentNode.getId());
		boolean currentNodeFinish = true;
		boolean currentNodeFinishSuccess = true;

		for(PipelineExecNode node : nodeList){
			currentNodeFinish = currentNodeFinish && StringUtils.equalsIgnoreCase(PipelineExecNodeStatus.FINISH, node.getStatus());
			if(currentNodeFinish){
				currentNodeFinishSuccess = currentNodeFinishSuccess && StringUtils.equals(PipelineExecNodeResult.SUCCESS, node.getResult());
			}
		}

		//3
		if(currentNodeFinish){
			currentNode.setResult(currentNodeFinishSuccess ? "SUCCESS" : "FAILURE" );
			finishNode(currentNode);
			return;
		}

		//4
		PipelineExecNode nextWaitingNode = execNodeService.findNextWaitingChildNode(currentNode.getId());

		//这里默认stage子任务是task
		if(nextWaitingNode == null){
			return;
		}

		//4.1
		nodeExecutorCenter.execute(nextWaitingNode);

	}

	/**
	 * 让当前节点的所有子节点都取消
	 * @param currentNode
	 */
	private void cancelAllChild(PipelineExecNode currentNode){

		//2.找到所有子节点
		List<PipelineExecNode> nodeList = execNodeService.findChildNode(currentNode.getId());

		for(PipelineExecNode node:nodeList){
			if(PipelineExecNodeStatus.isStart(node.getStatus())){
				continue;
			}
			if(StringUtils.isEmpty(node.getResult())){
				node.setStatus(PipelineExecNodeStatus.FINISH);
				node.setResult(PipelineExecNodeStatus.CANCEL);
				node.setResultMessage("STOP BY PARENT NODE");
				execNodeService.merge(node);
				cancelAllChild(node);
			}
		}
	}

}