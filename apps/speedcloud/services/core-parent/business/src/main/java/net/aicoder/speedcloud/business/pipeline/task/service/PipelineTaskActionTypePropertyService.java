package net.aicoder.speedcloud.business.pipeline.task.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskActionTypePropertyDao;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskActionTypePropertySpecification;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionTypeProperty;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineTaskActionTypePropertyService")
public class PipelineTaskActionTypePropertyService  extends GenericCrudService<PipelineTaskActionTypeProperty, Long, PipelineTaskActionTypePropertyCondition, PipelineTaskActionTypePropertyDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionTypePropertyService.class);

	@Override
	public Specification<PipelineTaskActionTypeProperty> getSpecification(PipelineTaskActionTypePropertyCondition condition) {
		return new PipelineTaskActionTypePropertySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTaskActionTypeProperty.PROPERTY_TASK_TYPE);
		return sort;
	}
}