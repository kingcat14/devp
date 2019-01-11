package com.yunkang.saas.platform.monitor.business.app.dao;

import com.yunkang.saas.platform.monitor.business.app.dto.UnknownAppCondition;
import com.yunkang.saas.platform.monitor.business.app.domain.UnknownApp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class UnknownAppSpecification implements Specification<UnknownApp>{

	private UnknownAppCondition condition;

	public UnknownAppSpecification(UnknownAppCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<UnknownApp> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddCodePredicate(predicateList, root, cb);
		tryAddRegisterTimePredicate(predicateList, root, cb);
		tryAddAlivePredicate(predicateList, root, cb);
		tryAddAliveCountPredicate(predicateList, root, cb);
		tryAddMaxCountPredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddIgnoredPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddCodePredicate(List<Predicate> predicateList, Root<UnknownApp> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(UnknownApp.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddRegisterTimePredicate(List<Predicate> predicateList, Root<UnknownApp> root, CriteriaBuilder cb){

		if (null != condition.getRegisterTime() ) {
			predicateList.add(cb.equal(root.get(UnknownApp.PROPERTY_REGISTER_TIME).as(Date.class), condition.getRegisterTime()));
		}

		if (null != condition.getRegisterTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(UnknownApp.PROPERTY_REGISTER_TIME).as(Date.class), condition.getRegisterTimeStart()));
		}

		if (null != condition.getRegisterTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(UnknownApp.PROPERTY_REGISTER_TIME).as(Date.class), condition.getRegisterTimeEnd()));
		}
	}
	private void tryAddAlivePredicate(List<Predicate> predicateList, Root<UnknownApp> root, CriteriaBuilder cb){
		if (null != condition.getAlive() ) {
			predicateList.add(cb.equal(root.get(UnknownApp.PROPERTY_ALIVE).as(Boolean.class), condition.getAlive()));
		}
	}
	private void tryAddAliveCountPredicate(List<Predicate> predicateList, Root<UnknownApp> root, CriteriaBuilder cb){

		if (null != condition.getAliveCount() ) {
			predicateList.add(cb.equal(root.get(UnknownApp.PROPERTY_ALIVE_COUNT).as(Integer.class), condition.getAliveCount()));
		}

		if (null != condition.getAliveCountMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(UnknownApp.PROPERTY_ALIVE_COUNT).as(Integer.class), condition.getAliveCountMax()));
		}

		if (null != condition.getAliveCountMin() ) {
			predicateList.add(cb.lessThan(root.get(UnknownApp.PROPERTY_ALIVE_COUNT).as(Integer.class), condition.getAliveCountMin()));
		}
	}
	private void tryAddMaxCountPredicate(List<Predicate> predicateList, Root<UnknownApp> root, CriteriaBuilder cb){

		if (null != condition.getMaxCount() ) {
			predicateList.add(cb.equal(root.get(UnknownApp.PROPERTY_MAX_COUNT).as(Integer.class), condition.getMaxCount()));
		}

		if (null != condition.getMaxCountMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(UnknownApp.PROPERTY_MAX_COUNT).as(Integer.class), condition.getMaxCountMax()));
		}

		if (null != condition.getMaxCountMin() ) {
			predicateList.add(cb.lessThan(root.get(UnknownApp.PROPERTY_MAX_COUNT).as(Integer.class), condition.getMaxCountMin()));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<UnknownApp> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(UnknownApp.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddIgnoredPredicate(List<Predicate> predicateList, Root<UnknownApp> root, CriteriaBuilder cb){
		if (null != condition.getIgnored() ) {
			predicateList.add(cb.equal(root.get(UnknownApp.PROPERTY_IGNORED).as(Boolean.class), condition.getIgnored()));
		}
	}
}


