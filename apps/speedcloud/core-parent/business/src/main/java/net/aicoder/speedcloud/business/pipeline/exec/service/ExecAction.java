package net.aicoder.speedcloud.business.pipeline.exec.service;


import net.aicoder.speedcloud.business.pipeline.constant.ExecNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.PipelineExecInstanceStatus;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.exec.builder.PipelineExecInstanceBuilder;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeCustomAddDto;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
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
	private PipelineExecInstanceService instanceService;


	@Autowired
	private PipelineExecInstanceBuilder pipelineExecInstanceBuilder;



	@Autowired
	private PipelineTaskService pipelineTaskService;

	@Autowired
	private PipelineService pipelineService;



	@Autowired
	private ExecNodeAction execNodeAction;


	/**
	 * 创建一个任务
	 */
	@Transactional
	public PipelineExecInstance createExec(PipelineExecNodeCustomAddDto customAddDto){

		PipelineExecInstance instance = new PipelineExecInstance();
		instance.setTid(customAddDto.getTid());

		instance.setExecuteTargetType(ExecNodeType.getType(customAddDto.getNodeType()));
		instance.setExecuteTargetId(customAddDto.getNodeId());
		instance.setStartTime(new Date());
		instance.setStatus(PipelineExecInstanceStatus.RUNNING);
		instanceService.add(instance);

		PipelineExecNode topNode = pipelineExecInstanceBuilder.buildTopNode(instance, customAddDto.getNodeId(), false);

		List<PipelineExecNodeCustomAddDto> subNode = customAddDto.getSubNodeList();

		if(CollectionUtils.isNotEmpty(subNode)){
			for(int i = 0; i< subNode.size(); i++){
				createNode(topNode, subNode.get(i));
			}
		}

		execNodeAction.execute(topNode);


		return instance;
	}

	private void createNode(PipelineExecNode parentNode, PipelineExecNodeCustomAddDto customAddDto){

		PipelineExecNode currentNode = pipelineExecInstanceBuilder.build(parentNode, customAddDto.getNodeType(), customAddDto.getNodeId(), false);

		List<PipelineExecNodeCustomAddDto> subNode = customAddDto.getSubNodeList();
		if(CollectionUtils.isEmpty(subNode)){
			return ;
		}

		for(int i = 0; i< subNode.size(); i++){
			createNode(currentNode, subNode.get(i));
		}

	}


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
		instance.setExecuteTargetType(ExecNodeType.TASK);
		instance.setExecuteTargetId(pipelineTask.getId());
		instance.setStartTime(new Date());
		instance.setStatus(PipelineExecInstanceStatus.RUNNING);

		instanceService.add(instance);

		PipelineExecNode node = pipelineExecInstanceBuilder.buildTaskInstance(instance, pipelineTask.getId(), true);
		execNodeAction.execute(node);


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

		instance.setExecuteTargetType(ExecNodeType.PIPELINE);
		instance.setExecuteTargetId(pipeline.getId());
		instance.setStartTime(new Date());
		instance.setStatus(PipelineExecInstanceStatus.RUNNING);

		instanceService.add(instance);

		PipelineExecNode node = pipelineExecInstanceBuilder.buildPipelineInstance(instance, pipeline.getId(), true);
		execNodeAction.execute(node);

	}


}