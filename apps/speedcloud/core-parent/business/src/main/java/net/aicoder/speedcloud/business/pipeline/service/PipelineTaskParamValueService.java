package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineTaskParamValueDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineTaskParamValueSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTaskParamValue;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskParamValueCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineTaskParamValueService")
public class PipelineTaskParamValueService  extends GenericCrudService<PipelineTaskParamValue, Long, PipelineTaskParamValueCondition, PipelineTaskParamValueDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskParamValueService.class);

	@Override
	public Specification<PipelineTaskParamValue> getSpecification(PipelineTaskParamValueCondition condition) {
		return new PipelineTaskParamValueSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTaskParamValue.PROPERTY_TASK_TYPE);
		return sort;
	}
}