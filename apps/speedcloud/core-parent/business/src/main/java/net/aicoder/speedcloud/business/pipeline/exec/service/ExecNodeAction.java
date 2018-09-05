package net.aicoder.speedcloud.business.pipeline.exec.service;


import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParam;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskParamService;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("execNodeAction")
public class ExecNodeAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecNodeAction.class);

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



	/**
	 * 给一个任务创建执行任务
	 * @param instance
	 * @param taskId
	 */
	public PipelineExecInstanceNode createTaskExecNode(PipelineExecInstance instance, Long taskId, int execIndex){

		PipelineTask pipelineTask = pipelineTaskService.find(taskId);

		//创建执行节点
		PipelineExecInstanceNode node = new PipelineExecInstanceNode();
		node.setNodeType("TASK");
		node.setAutoStart(true);
		node.setName(pipelineTask.getName());
		node.setParentId("-1");
		node.setExec(instance.getId());
		node.setTask(taskId);
		node.setExecIndex(execIndex);
		node.setExecMode("SERIALIZED");
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
	 * 开始执行一个节点
	 * @param node
	 */
	public void start(PipelineExecInstanceNode node){
		node.setStatus(PipelineExecInstanceNodeStatus.PREPARED);
		execNodeService.merge(node);
	}


}