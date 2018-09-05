package net.aicoder.speedcloud.business.pipeline.task.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.command.domain.PipelineJobCommand;
import net.aicoder.speedcloud.business.pipeline.command.service.PipelineJobCommandService;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskDao;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskSpecification;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskAction;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service("pipelineTaskService")
public class PipelineTaskService  extends GenericCrudService<PipelineTask, Long, PipelineTaskCondition, PipelineTaskDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskService.class);

	@Autowired
	private PipelineJobCommandService pipelineJobCommandService;

	@Autowired
	private PipelineTaskActionService pipelineTaskActionService;

	@Autowired
	private PipelineTaskParamService pipelineTaskParamService;


	@Transactional
	public void create(PipelineTask t, List<PipelineTaskAction> pipelineTaskActionList, List<PipelineTaskParam> pipelineTaskParamList){

		/*
		 * 1.新增task
		 * 2.新增task步骤
		 * 3.新增task参数
		 */
		this.add(t);

		for(PipelineTaskAction action:pipelineTaskActionList){
			action.setTask(t.getId());
		}
		pipelineTaskActionService.add(pipelineTaskActionList);

		for(PipelineTaskParam param:pipelineTaskParamList){
			param.setTask(t.getId());
		}
		pipelineTaskParamService.add(pipelineTaskParamList);
	}

	@Transactional
	public void delete(Long id){

		PipelineTask task = this.find(id);

		pipelineTaskActionService.deleteByTaskId(id);
		pipelineTaskParamService.deleteByTaskId(id);

		PipelineJobCommand pipelineJobCommand = new PipelineJobCommand();
		pipelineJobCommand.setStatus("WAIT");
		pipelineJobCommand.setCreateTime(new Date());
		pipelineJobCommand.setTask(task.getId());
		pipelineJobCommand.setType("DELETE");
		pipelineJobCommand.setTid(task.getTid());

		//创建一个指令
		pipelineJobCommandService.add(pipelineJobCommand);

		dao.delete(id);
	}

	@Transactional
	public void update(PipelineTask t, List<PipelineTaskAction> pipelineTaskActionList, List<PipelineTaskParam> pipelineTaskParamList){
		/*
		 * 1.更新task
		 * 2.删掉task的所有步骤,再新增步骤
		 * 3.删掉task的所有参数,再新增参数
		 */

		//1.
		this.merge(t);

		//2.
		pipelineTaskActionService.deleteByTaskId(t.getId());
		for(PipelineTaskAction action:pipelineTaskActionList){
			action.setTask(t.getId());
		}
		pipelineTaskActionService.add(pipelineTaskActionList);

		//3.
		pipelineTaskParamService.deleteByTaskId(t.getId());
		for(PipelineTaskParam param:pipelineTaskParamList){
			param.setTask(t.getId());
		}
		pipelineTaskParamService.add(pipelineTaskParamList);
	}

	@Transactional
	public void add(PipelineTask t){
		super.add(t);

		PipelineJobCommand pipelineJobCommand = new PipelineJobCommand();
		pipelineJobCommand.setStatus("WAIT");
		pipelineJobCommand.setCreateTime(new Date());
		pipelineJobCommand.setTask(t.getId());
		pipelineJobCommand.setType("CREATE");
		pipelineJobCommand.setTid(t.getTid());

		//创建一个指令
		pipelineJobCommandService.add(pipelineJobCommand);


	}

	@Transactional
	public void merge(PipelineTask t){

		super.merge(t);

		PipelineJobCommand pipelineJobCommand = new PipelineJobCommand();
		pipelineJobCommand.setStatus("WAIT");
		pipelineJobCommand.setCreateTime(new Date());
		pipelineJobCommand.setTask(t.getId());
		pipelineJobCommand.setType("UPDATE");
		pipelineJobCommand.setTid(t.getTid());

		//创建一个指令
		pipelineJobCommandService.add(pipelineJobCommand);


	}

	@Override
	public Specification<PipelineTask> getSpecification(PipelineTaskCondition condition) {
		return new PipelineTaskSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTask.PROPERTY_NAME);
		return sort;
	}
}