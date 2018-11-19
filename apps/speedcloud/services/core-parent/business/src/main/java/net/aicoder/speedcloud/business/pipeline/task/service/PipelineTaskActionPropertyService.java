package net.aicoder.speedcloud.business.pipeline.task.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskActionPropertyDao;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskActionPropertySpecification;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionProperty;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineTaskActionPropertyService")
public class PipelineTaskActionPropertyService  extends GenericCrudService<PipelineTaskActionProperty, Long, PipelineTaskActionPropertyCondition, PipelineTaskActionPropertyDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionPropertyService.class);

	@Override
	public Specification<PipelineTaskActionProperty> getSpecification(PipelineTaskActionPropertyCondition condition) {
		return new PipelineTaskActionPropertySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTaskActionProperty.PROPERTY_TASK_TYPE);
		return sort;
	}
}