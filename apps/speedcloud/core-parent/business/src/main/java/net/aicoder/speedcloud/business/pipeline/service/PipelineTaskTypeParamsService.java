package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineTaskTypeParamsDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineTaskTypeParamsSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTaskTypeParams;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineTaskTypeParamsService")
public class PipelineTaskTypeParamsService  extends GenericCrudService<PipelineTaskTypeParams, Long, PipelineTaskTypeParamsCondition, PipelineTaskTypeParamsDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeParamsService.class);

	@Override
	public Specification<PipelineTaskTypeParams> getSpecification(PipelineTaskTypeParamsCondition condition) {
		return new PipelineTaskTypeParamsSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTaskTypeParams.PROPERTY_TASK_TYPE);
		return sort;
	}
}