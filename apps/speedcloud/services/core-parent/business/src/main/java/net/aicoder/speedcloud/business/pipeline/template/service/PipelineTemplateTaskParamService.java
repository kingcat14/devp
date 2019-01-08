package net.aicoder.speedcloud.business.pipeline.template.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.pipeline.template.dao.PipelineTemplateTaskParamDao;
import net.aicoder.speedcloud.business.pipeline.template.dao.PipelineTemplateTaskParamSpecification;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskParam;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pipelineTemplateTaskParamService")
@Slf4j
public class PipelineTemplateTaskParamService  extends GenericCrudService<PipelineTemplateTaskParam, String, PipelineTemplateTaskParamCondition, PipelineTemplateTaskParamDao> {

	public List<PipelineTemplateTaskParam> findByTask(String taskId){
		return dao.findByTask(taskId);
	}

	public  int deleteByTaskId(String taskId){
		return dao.deleteByTask(taskId);
	}


	@Override
	public Specification<PipelineTemplateTaskParam> getSpecification(PipelineTemplateTaskParamCondition condition) {
		return new PipelineTemplateTaskParamSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, PipelineTemplateTaskParam.PROPERTY_MODIFY_AT);

		return sort;
	}
}