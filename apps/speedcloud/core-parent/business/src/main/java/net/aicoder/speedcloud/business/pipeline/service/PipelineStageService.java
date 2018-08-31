package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineStageService")
public class PipelineStageService  extends GenericCrudService<PipelineStage, Long, PipelineStageCondition, PipelineStageDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageService.class);

	@Override
	public Specification<PipelineStage> getSpecification(PipelineStageCondition condition) {
		return new PipelineStageSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineStage.PROPERTY_PIPELINE);
		return sort;
	}
}