package net.aicoder.speedcloud.business.pipeline.exec.service;


import net.aicoder.speedcloud.business.pipeline.constant.ExecuteTargetType;
import net.aicoder.speedcloud.business.pipeline.constant.PipelineExecInstanceStatus;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageService;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 执行的自定义操作
 */
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
	private PipelineStageService pipelineStageService;


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
		if("PIPELINE".equals(instance.getExecuteTargetType())){
			createPipelineExec(instance);
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
		 * 3.开始这个节点
		 */
		instance.setExecuteTargetType(ExecuteTargetType.TASK);
		instance.setExecuteTargetId(pipelineTask.getId());
		instance.setStartTime(new Date());
		instance.setStatus(PipelineExecInstanceStatus.RUNNING);

		execService.add(instance);

		PipelineExecInstanceNode node = execNodeAction.createTaskExecNode(instance, pipelineTask.getId(), 1);
		execNodeAction.runNode(node);


	}

	/**
	 * 给一个流水线创建执行请求
	 * @param instance
	 */
	private void createPipelineExec(PipelineExecInstance instance){

		/*
		 * 1.找到流水线
		 * 2.找到流水线参数
		 * 3.设置执行实例参数
		 * 4.创建执行实例节点
		 */

		Pipeline pipeline = pipelineService.find(instance.getExecuteTargetId());

		instance.setExecuteTargetType(ExecuteTargetType.PIPELINE);
		instance.setExecuteTargetId(pipeline.getId());
		instance.setStartTime(new Date());
		instance.setStatus(PipelineExecInstanceStatus.RUNNING);

		execService.add(instance);

		List<PipelineStage> stageList = pipelineStageService.findForPipeline(instance.getExecuteTargetId());

		PipelineExecInstanceNode firstStageNode = null;

        PipelineExecInstanceNode tempNode = execNodeAction.createPipelineExecNode(instance, pipeline.getId());
        execNodeAction.runNode(firstStageNode);

		for(int i = 0; CollectionUtils.isNotEmpty(stageList) && (i < CollectionUtils.size(stageList)) ; i++){
			PipelineStage pipelineStage = stageList.get(i);

			if(firstStageNode == null){
				firstStageNode = tempNode;
			}
		}
		execNodeAction.runNode(firstStageNode);

	}


}