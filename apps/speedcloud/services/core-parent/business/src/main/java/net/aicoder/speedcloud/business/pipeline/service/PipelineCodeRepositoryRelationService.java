package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineCodeRepositoryRelationDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineCodeRepositoryRelationSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineCodeRepositoryRelation;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineCodeRepositoryRelationService")
public class PipelineCodeRepositoryRelationService  extends GenericCrudService<PipelineCodeRepositoryRelation, Long, PipelineCodeRepositoryRelationCondition, PipelineCodeRepositoryRelationDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineCodeRepositoryRelationService.class);

	@Override
	public Specification<PipelineCodeRepositoryRelation> getSpecification(PipelineCodeRepositoryRelationCondition condition) {
		return new PipelineCodeRepositoryRelationSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineCodeRepositoryRelation.PROPERTY_CODE_REPOSITORY);
		return sort;
	}
}