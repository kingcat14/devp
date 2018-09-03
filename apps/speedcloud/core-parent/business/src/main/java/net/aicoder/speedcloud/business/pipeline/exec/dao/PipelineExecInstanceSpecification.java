package net.aicoder.speedcloud.business.pipeline.exec.dao;

import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceCondition;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class PipelineExecInstanceSpecification implements Specification<PipelineExecInstance>{

	private PipelineExecInstanceCondition condition;

	public PipelineExecInstanceSpecification(PipelineExecInstanceCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineExecInstance> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddExecuteTargetIdPredicate(predicateList, root, cb);
		tryAddExecuteTargetTypePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddResultPredicate(predicateList, root, cb);
		tryAddStartTimePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineExecInstance> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(PipelineExecInstance.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineExecInstance.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineExecInstance.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<PipelineExecInstance> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(PipelineExecInstance.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddExecuteTargetIdPredicate(List<Predicate> predicateList, Root<PipelineExecInstance> root, CriteriaBuilder cb){

		if (null != condition.getExecuteTargetId() ) {
			predicateList.add(cb.equal(root.get(PipelineExecInstance.PROPERTY_EXECUTE_TARGET_ID).as(Long.class), condition.getExecuteTargetId()));
		}

		if (null != condition.getExecuteTargetIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineExecInstance.PROPERTY_EXECUTE_TARGET_ID).as(Long.class), condition.getExecuteTargetIdMax()));
		}

		if (null != condition.getExecuteTargetIdMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineExecInstance.PROPERTY_EXECUTE_TARGET_ID).as(Long.class), condition.getExecuteTargetIdMin()));
		}
	}
	private void tryAddExecuteTargetTypePredicate(List<Predicate> predicateList, Root<PipelineExecInstance> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getExecuteTargetType())){
			predicateList.add(cb.like(root.get(PipelineExecInstance.PROPERTY_EXECUTE_TARGET_TYPE).as(String.class), "%"+condition.getExecuteTargetType()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<PipelineExecInstance> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(PipelineExecInstance.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddResultPredicate(List<Predicate> predicateList, Root<PipelineExecInstance> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getResult())){
			predicateList.add(cb.like(root.get(PipelineExecInstance.PROPERTY_RESULT).as(String.class), "%"+condition.getResult()+"%"));
		}
	}
	private void tryAddStartTimePredicate(List<Predicate> predicateList, Root<PipelineExecInstance> root, CriteriaBuilder cb){

		if (null != condition.getStartTime() ) {
			predicateList.add(cb.equal(root.get(PipelineExecInstance.PROPERTY_START_TIME).as(Date.class), condition.getStartTime()));
		}

		if (null != condition.getStartTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineExecInstance.PROPERTY_START_TIME).as(Date.class), condition.getStartTimeStart()));
		}

		if (null != condition.getStartTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(PipelineExecInstance.PROPERTY_START_TIME).as(Date.class), condition.getStartTimeEnd()));
		}
	}
}


