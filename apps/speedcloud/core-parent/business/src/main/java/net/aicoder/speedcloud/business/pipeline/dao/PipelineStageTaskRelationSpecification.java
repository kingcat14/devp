package net.aicoder.speedcloud.business.pipeline.dao;

import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationCondition;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageTaskRelation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class PipelineStageTaskRelationSpecification implements Specification<PipelineStageTaskRelation>{

	private PipelineStageTaskRelationCondition condition;

	public PipelineStageTaskRelationSpecification(PipelineStageTaskRelationCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineStageTaskRelation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddStagePredicate(predicateList, root, cb);
		tryAddTaskPredicate(predicateList, root, cb);
		tryAddExecOrderPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineStageTaskRelation> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(PipelineStageTaskRelation.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineStageTaskRelation.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineStageTaskRelation.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddStagePredicate(List<Predicate> predicateList, Root<PipelineStageTaskRelation> root, CriteriaBuilder cb){
	    if (null != condition.getStage() ) {
            predicateList.add(cb.equal(root.get(PipelineStageTaskRelation.PROPERTY_STAGE).as(Long.class), condition.getStage()));
        }
	}
	private void tryAddTaskPredicate(List<Predicate> predicateList, Root<PipelineStageTaskRelation> root, CriteriaBuilder cb){
	    if (null != condition.getTask() ) {
            predicateList.add(cb.equal(root.get(PipelineStageTaskRelation.PROPERTY_TASK).as(Long.class), condition.getTask()));
        }
	}
	private void tryAddExecOrderPredicate(List<Predicate> predicateList, Root<PipelineStageTaskRelation> root, CriteriaBuilder cb){

		if (null != condition.getExecOrder() ) {
			predicateList.add(cb.equal(root.get(PipelineStageTaskRelation.PROPERTY_EXEC_ORDER).as(Integer.class), condition.getExecOrder()));
		}

		if (null != condition.getExecOrderMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineStageTaskRelation.PROPERTY_EXEC_ORDER).as(Integer.class), condition.getExecOrderMax()));
		}

		if (null != condition.getExecOrderMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineStageTaskRelation.PROPERTY_EXEC_ORDER).as(Integer.class), condition.getExecOrderMin()));
		}
	}
}


