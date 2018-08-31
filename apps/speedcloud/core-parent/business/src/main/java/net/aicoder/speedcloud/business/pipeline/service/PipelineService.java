package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineService")
public class PipelineService  extends GenericCrudService<Pipeline, Long, PipelineCondition, PipelineDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineService.class);

	@Override
	public Specification<Pipeline> getSpecification(PipelineCondition condition) {
		return new PipelineSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Pipeline.PROPERTY_NAME);
		return sort;
	}
}