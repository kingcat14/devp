package net.aicoder.speedcloud.business.pipeline.dao;

import net.aicoder.speedcloud.business.pipeline.domain.PipelineCodeRepositoryRelation;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationCondition;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PipelineCodeRepositoryRelationSpecification implements Specification<PipelineCodeRepositoryRelation>{

	private PipelineCodeRepositoryRelationCondition condition;

	public PipelineCodeRepositoryRelationSpecification(PipelineCodeRepositoryRelationCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineCodeRepositoryRelation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddCodeRepositoryPredicate(predicateList, root, cb);
		tryAddPipelinePredicate(predicateList, root, cb);
		tryAddAutoStartPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddCodeRepositoryPredicate(List<Predicate> predicateList, Root<PipelineCodeRepositoryRelation> root, CriteriaBuilder cb){
	    if (null != condition.getCodeRepository() ) {
            predicateList.add(cb.equal(root.get(PipelineCodeRepositoryRelation.PROPERTY_CODE_REPOSITORY).as(Long.class), condition.getCodeRepository()));
        }
	}
	private void tryAddPipelinePredicate(List<Predicate> predicateList, Root<PipelineCodeRepositoryRelation> root, CriteriaBuilder cb){
	    if (null != condition.getPipeline() ) {
            predicateList.add(cb.equal(root.get(PipelineCodeRepositoryRelation.PROPERTY_PIPELINE).as(Long.class), condition.getPipeline()));
        }
	}
	private void tryAddAutoStartPredicate(List<Predicate> predicateList, Root<PipelineCodeRepositoryRelation> root, CriteriaBuilder cb){
		if (null != condition.getAutoStart() ) {
			predicateList.add(cb.equal(root.get(PipelineCodeRepositoryRelation.PROPERTY_AUTO_START).as(Boolean.class), condition.getAutoStart()));
		}
	}
}


