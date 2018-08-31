package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineTaskTypeDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineTaskTypeSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTaskType;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineTaskTypeService")
public class PipelineTaskTypeService  extends GenericCrudService<PipelineTaskType, Long, PipelineTaskTypeCondition, PipelineTaskTypeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeService.class);

	@Override
	public Specification<PipelineTaskType> getSpecification(PipelineTaskTypeCondition condition) {
		return new PipelineTaskTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTaskType.PROPERTY_CODE);
		return sort;
	}
}