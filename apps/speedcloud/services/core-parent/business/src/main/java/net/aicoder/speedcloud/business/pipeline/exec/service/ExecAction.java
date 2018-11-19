package net.aicoder.speedcloud.business.pipeline.exec.service;


import net.aicoder.speedcloud.business.pipeline.constant.ExecNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.PipelineExecInstanceStatus;
import net.aicoder.speedcloud.business.pipeline.exec.builder.PipelineExecInstanceBuilder;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeCustomAddDto;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
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



	@Transactional
	public PipelineExecInstance createExec(PipelineExecNodeCustomAddDto customAddDto, Boolean createSubNode){

		PipelineExecInstance instance = new PipelineExecInstance();
		instance.setTid(customAddDto.getTid());

		instance.setExecuteTargetType(ExecNodeType.getType(customAddDto.getNodeType()));
		instance.setExecuteTargetId(customAddDto.getNodeId());
		instance.setStartTime(new Date());
		instance.setStatus(PipelineExecInstanceStatus.RUNNING);
		instanceService.add(instance);

		PipelineExecNode topNode = pipelineExecInstanceBuilder.buildTopNode(instance, customAddDto.getNodeId(), createSubNode);

		List<PipelineExecNodeCustomAddDto> subNode = customAddDto.getSubNodeList();

		if(CollectionUtils.isNotEmpty(subNode)){
			for(int i = 0; i< subNode.size(); i++){
				createNode(topNode, subNode.get(i));
			}
		}

		execNodeAction.execute(topNode);


		return instance;
	}

	/**
	 * 创建一个任务
	 */
	@Transactional
	public PipelineExecInstance createExec(PipelineExecNodeCustomAddDto customAddDto){

		return createExec(customAddDto, false);

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







}