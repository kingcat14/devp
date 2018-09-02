package net.aicoder.speedcloud.business.pipeline.exec.dao;

import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecCondition;
import net.aicoder.speedcloud.business.pipeline.exec.domain.Exec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ExecSpecification implements Specification<Exec>{

	private ExecCondition condition;

	public ExecSpecification(ExecCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Exec> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddRunnerIdPredicate(predicateList, root, cb);
		tryAddRunnerTypePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddResultPredicate(predicateList, root, cb);
		tryAddStartTimePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<Exec> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(Exec.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Exec.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(Exec.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Exec> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Exec.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddRunnerIdPredicate(List<Predicate> predicateList, Root<Exec> root, CriteriaBuilder cb){

		if (null != condition.getRunnerId() ) {
			predicateList.add(cb.equal(root.get(Exec.PROPERTY_RUNNER_ID).as(Long.class), condition.getRunnerId()));
		}

		if (null != condition.getRunnerIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Exec.PROPERTY_RUNNER_ID).as(Long.class), condition.getRunnerIdMax()));
		}

		if (null != condition.getRunnerIdMin() ) {
			predicateList.add(cb.lessThan(root.get(Exec.PROPERTY_RUNNER_ID).as(Long.class), condition.getRunnerIdMin()));
		}
	}
	private void tryAddRunnerTypePredicate(List<Predicate> predicateList, Root<Exec> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRunnerType())){
			predicateList.add(cb.like(root.get(Exec.PROPERTY_RUNNER_TYPE).as(String.class), "%"+condition.getRunnerType()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<Exec> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(Exec.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddResultPredicate(List<Predicate> predicateList, Root<Exec> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getResult())){
			predicateList.add(cb.like(root.get(Exec.PROPERTY_RESULT).as(String.class), "%"+condition.getResult()+"%"));
		}
	}
	private void tryAddStartTimePredicate(List<Predicate> predicateList, Root<Exec> root, CriteriaBuilder cb){

		if (null != condition.getStartTime() ) {
			predicateList.add(cb.equal(root.get(Exec.PROPERTY_START_TIME).as(Date.class), condition.getStartTime()));
		}

		if (null != condition.getStartTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Exec.PROPERTY_START_TIME).as(Date.class), condition.getStartTimeStart()));
		}

		if (null != condition.getStartTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(Exec.PROPERTY_START_TIME).as(Date.class), condition.getStartTimeEnd()));
		}
	}
}


