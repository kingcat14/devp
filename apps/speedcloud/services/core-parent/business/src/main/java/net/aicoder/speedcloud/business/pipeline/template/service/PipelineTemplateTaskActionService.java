package net.aicoder.speedcloud.business.pipeline.template.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.pipeline.template.dao.PipelineTemplateTaskActionDao;
import net.aicoder.speedcloud.business.pipeline.template.dao.PipelineTemplateTaskActionSpecification;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskAction;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pipelineTemplateTaskActionService")
@Slf4j
public class PipelineTemplateTaskActionService  extends GenericCrudService<PipelineTemplateTaskAction, String, PipelineTemplateTaskActionCondition, PipelineTemplateTaskActionDao> {

	public List<PipelineTemplateTaskAction> findByTask(String taskId){
		return dao.findByTask(taskId);
	}

	public  int deleteByTaskId(String taskId){
		return dao.deleteByTask(taskId);
	}

	@Override
	public Specification<PipelineTemplateTaskAction> getSpecification(PipelineTemplateTaskActionCondition condition) {
		return new PipelineTemplateTaskActionSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, PipelineTemplateTaskAction.PROPERTY_MODIFY_AT);

		return sort;
	}
}