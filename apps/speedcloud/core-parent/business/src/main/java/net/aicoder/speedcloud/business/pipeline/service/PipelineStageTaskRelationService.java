package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageTaskRelationDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageTaskRelationSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageTaskRelation;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineStageTaskRelationService")
public class PipelineStageTaskRelationService  extends GenericCrudService<PipelineStageTaskRelation, Long, PipelineStageTaskRelationCondition, PipelineStageTaskRelationDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageTaskRelationService.class);

	@Override
	public Specification<PipelineStageTaskRelation> getSpecification(PipelineStageTaskRelationCondition condition) {
		return new PipelineStageTaskRelationSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineStageTaskRelation.PROPERTY_STAGE);
		return sort;
	}
}