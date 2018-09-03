package net.aicoder.speedcloud.business.pipeline.exec.service;


import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service("execAction")
public class ExecAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecAction.class);

	@Autowired
	@Qualifier("pipelineExecInstanceService")
	private PipelineExecInstanceService execService;

	@Autowired
	private PipelineExecInstanceNodeService pi;

	@Autowired
	private PipelineTaskService pipelineTaskService;

	@Autowired
	private PipelineService pipelineService;


	@Autowired
	private ExecNodeAction execNodeAction;

	/**
	 * 创建一个执行任务
	 * @param instance
	 */
	@Transactional
	public void createExec(PipelineExecInstance instance){
		/*
		 * 1.判断是任务还是流水线
		 * 2.根据类型创建对应的任务
		 */

		//execService.add(instance);

		if("TASK".equals(instance.getExecuteTargetType())){
			createTaskExec(instance);
		}

	}

	/**
	 * 给一个Task创建执行请求
	 * @param instance
	 */
	private void createTaskExec(PipelineExecInstance instance){

		PipelineTask pipelineTask = pipelineTaskService.find(instance.getExecuteTargetId());

		/*
		 * 1.得到当前任务所使用的参数，取默认值
		 * 2.生成一个任务节点
		 */
		instance.setExecuteTargetType("TASK");
		instance.setExecuteTargetId(pipelineTask.getId());
		instance.setStartTime(new Date());
		instance.setStatus("RUNNING");

		execService.add(instance);

		execNodeAction.createTaskExecNode(instance, instance.getExecuteTargetId(), 1);


	}


	private void createPipelineExec(Long pipeline){

	}


}