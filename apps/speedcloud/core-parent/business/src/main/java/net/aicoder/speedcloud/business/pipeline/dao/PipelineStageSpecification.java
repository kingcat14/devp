package net.aicoder.speedcloud.business.pipeline.dao;

import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageCondition;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class PipelineStageSpecification implements Specification<PipelineStage>{

	private PipelineStageCondition condition;

	public PipelineStageSpecification(PipelineStageCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineStage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddPipelinePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddFlowTypePredicate(predicateList, root, cb);
		tryAddExecModePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineStage> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(PipelineStage.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineStage.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineStage.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddPipelinePredicate(List<Predicate> predicateList, Root<PipelineStage> root, CriteriaBuilder cb){
	    if (null != condition.getPipeline() ) {
            predicateList.add(cb.equal(root.get(PipelineStage.PROPERTY_PIPELINE).as(Long.class), condition.getPipeline()));
        }
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineStage> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineStage.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddFlowTypePredicate(List<Predicate> predicateList, Root<PipelineStage> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFlowType())){
			predicateList.add(cb.equal(root.get(PipelineStage.PROPERTY_FLOW_TYPE).as(String.class), condition.getFlowType()));
		}
	}
	private void tryAddExecModePredicate(List<Predicate> predicateList, Root<PipelineStage> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getExecMode())){
			predicateList.add(cb.equal(root.get(PipelineStage.PROPERTY_EXEC_MODE).as(String.class), condition.getExecMode()));
		}
	}
}


