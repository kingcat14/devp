package com.yunkang.saas.platform.monitor.business.app.dao;

import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationCondition;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ApplicationSpecification implements Specification<Application>{

	private ApplicationCondition condition;

	public ApplicationSpecification(ApplicationCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Application> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddTotalCountPredicate(predicateList, root, cb);
		tryAddAliveCountPredicate(predicateList, root, cb);
		tryAddAlarmPredicate(predicateList, root, cb);
		tryAddEnablePredicate(predicateList, root, cb);
		tryAddThresholdValuePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Application> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Application.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Application> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Application.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddTotalCountPredicate(List<Predicate> predicateList, Root<Application> root, CriteriaBuilder cb){

		if (null != condition.getTotalCount() ) {
			predicateList.add(cb.equal(root.get(Application.PROPERTY_TOTAL_COUNT).as(Integer.class), condition.getTotalCount()));
		}

		if (null != condition.getTotalCountMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Application.PROPERTY_TOTAL_COUNT).as(Integer.class), condition.getTotalCountMax()));
		}

		if (null != condition.getTotalCountMin() ) {
			predicateList.add(cb.lessThan(root.get(Application.PROPERTY_TOTAL_COUNT).as(Integer.class), condition.getTotalCountMin()));
		}
	}
	private void tryAddAliveCountPredicate(List<Predicate> predicateList, Root<Application> root, CriteriaBuilder cb){

		if (null != condition.getAliveCount() ) {
			predicateList.add(cb.equal(root.get(Application.PROPERTY_ALIVE_COUNT).as(Integer.class), condition.getAliveCount()));
		}

		if (null != condition.getAliveCountMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Application.PROPERTY_ALIVE_COUNT).as(Integer.class), condition.getAliveCountMax()));
		}

		if (null != condition.getAliveCountMin() ) {
			predicateList.add(cb.lessThan(root.get(Application.PROPERTY_ALIVE_COUNT).as(Integer.class), condition.getAliveCountMin()));
		}
	}
	private void tryAddAlarmPredicate(List<Predicate> predicateList, Root<Application> root, CriteriaBuilder cb){
		if (null != condition.getAlarm() ) {
			predicateList.add(cb.equal(root.get(Application.PROPERTY_ALARM).as(Boolean.class), condition.getAlarm()));
		}
	}
	private void tryAddEnablePredicate(List<Predicate> predicateList, Root<Application> root, CriteriaBuilder cb){
		if (null != condition.getEnable() ) {
			predicateList.add(cb.equal(root.get(Application.PROPERTY_ENABLE).as(Boolean.class), condition.getEnable()));
		}
	}
	private void tryAddThresholdValuePredicate(List<Predicate> predicateList, Root<Application> root, CriteriaBuilder cb){

		if (null != condition.getThresholdValue() ) {
			predicateList.add(cb.equal(root.get(Application.PROPERTY_THRESHOLD_VALUE).as(Integer.class), condition.getThresholdValue()));
		}

		if (null != condition.getThresholdValueMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Application.PROPERTY_THRESHOLD_VALUE).as(Integer.class), condition.getThresholdValueMax()));
		}

		if (null != condition.getThresholdValueMin() ) {
			predicateList.add(cb.lessThan(root.get(Application.PROPERTY_THRESHOLD_VALUE).as(Integer.class), condition.getThresholdValueMin()));
		}
	}
}


