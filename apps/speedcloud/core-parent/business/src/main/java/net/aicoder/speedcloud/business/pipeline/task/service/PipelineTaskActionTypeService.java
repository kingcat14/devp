package net.aicoder.speedcloud.business.pipeline.task.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskActionTypeDao;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskActionTypeSpecification;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionType;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineTaskActionTypeService")
public class PipelineTaskActionTypeService  extends GenericCrudService<PipelineTaskActionType, Long, PipelineTaskActionTypeCondition, PipelineTaskActionTypeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionTypeService.class);

	@Override
	public Specification<PipelineTaskActionType> getSpecification(PipelineTaskActionTypeCondition condition) {
		return new PipelineTaskActionTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTaskActionType.PROPERTY_CODE);
		return sort;
	}
}