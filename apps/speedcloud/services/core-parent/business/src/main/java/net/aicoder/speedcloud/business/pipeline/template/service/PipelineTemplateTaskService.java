package net.aicoder.speedcloud.business.pipeline.template.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.pipeline.template.dao.PipelineTemplateTaskDao;
import net.aicoder.speedcloud.business.pipeline.template.dao.PipelineTemplateTaskSpecification;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTask;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskAction;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskParam;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("pipelineTemplateTaskService")
@Slf4j
public class PipelineTemplateTaskService  extends GenericCrudService<PipelineTemplateTask, String, PipelineTemplateTaskCondition, PipelineTemplateTaskDao> {

	@Autowired
	private PipelineTemplateTaskActionService pipelineTaskActionService;

	@Autowired
	private PipelineTemplateTaskParamService pipelineTaskParamService;

	@Override
	public Specification<PipelineTemplateTask> getSpecification(PipelineTemplateTaskCondition condition) {
		return new PipelineTemplateTaskSpecification(condition);
	}

	@Transactional
	public void create(PipelineTemplateTask t, List<PipelineTemplateTaskAction> pipelineTaskActionList, List<PipelineTemplateTaskParam> pipelineTaskParamList){

		/*
		 * 1.新增task
		 * 2.新增task步骤
		 * 3.新增task参数
		 */
		super.add(t);

		for(PipelineTemplateTaskAction action:pipelineTaskActionList){
			action.setTask(t.getId());
		}
		pipelineTaskActionService.add(pipelineTaskActionList);

		for(PipelineTemplateTaskParam param:pipelineTaskParamList){
			param.setTask(t.getId());
		}
		pipelineTaskParamService.add(pipelineTaskParamList);
	}

	@Transactional
	public void delete(String id){

		pipelineTaskActionService.deleteByTaskId(id);
		pipelineTaskParamService.deleteByTaskId(id);

		dao.delete(id);
	}

	@Transactional
	public void update(PipelineTemplateTask t, List<PipelineTemplateTaskAction> pipelineTaskActionList, List<PipelineTemplateTaskParam> pipelineTaskParamList){
		/*
		 * 1.更新task
		 * 2.删掉task的所有步骤,再新增步骤
		 * 3.删掉task的所有参数,再新增参数
		 */

		//1.
		this.merge(t);

		//2.
		pipelineTaskActionService.deleteByTaskId(t.getId());
		for(PipelineTemplateTaskAction action:pipelineTaskActionList){
			action.setTask(t.getId());
		}
		pipelineTaskActionService.add(pipelineTaskActionList);

		//3.
		pipelineTaskParamService.deleteByTaskId(t.getId());
		for(PipelineTemplateTaskParam param:pipelineTaskParamList){
			param.setTask(t.getId());
		}
		pipelineTaskParamService.add(pipelineTaskParamList);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, PipelineTemplateTask.PROPERTY_MODIFY_AT);

		return sort;
	}
}