package net.aicoder.speedcloud.business.pipeline.exec.service;


import net.aicoder.speedcloud.business.pipeline.constant.GlobalLock;
import net.aicoder.speedcloud.business.pipeline.constant.PipelineExecInstanceStatus;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.executor.NodeExecutorCenter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("execNodeAction")
public class ExecNodeAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecNodeAction.class);

	@Autowired
	private NodeExecutorCenter nodeExecutorCenter;

	@Autowired()@Qualifier("pipelineExecInstanceNodeService")
	private PipelineExecInstanceNodeService execNodeService;

	@Autowired
	private PipelineExecInstanceService pipelineExecInstanceService;

	@Autowired
	private PipelineExecInstanceNodeService pipelineExecInstanceNodeService;

	public void execute(PipelineExecInstanceNode node){

		nodeExecutorCenter.execute(node);
	}

	public void finishNode(PipelineExecInstanceNode node){

		//保存node
		node.setStatus("FINISH");

		//TODO 这个全局锁，回头要换成使用redis或其他的跨进程的全局锁;根据所操作的节点父节点ID加锁
		GlobalLock.lock(node.getParentId()) ;

		pipelineExecInstanceNodeService.merge(node);

		//获取父节点
		PipelineExecInstanceNode parentNode = pipelineExecInstanceNodeService.find(node.getParentId());

		//如果有父节点
		if(parentNode != null){
			//子节点结束事件
			childFinish(node);
		}else{
			PipelineExecInstance instance = pipelineExecInstanceService.find(node.getExec());
			instance.setStatus("FINISH");
			pipelineExecInstanceService.merge(instance);
		}



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

		//这里默认stage子任务是task
		if(nextWaitingNode == null){
			return;
		}

		//4.1
		nodeExecutorCenter.execute(nextWaitingNode);

	}

}