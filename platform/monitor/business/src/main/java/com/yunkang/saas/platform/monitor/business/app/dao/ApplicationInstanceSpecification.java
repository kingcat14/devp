package com.yunkang.saas.platform.monitor.business.app.dao;

import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationInstanceCondition;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ApplicationInstanceSpecification implements Specification<ApplicationInstance>{

	private ApplicationInstanceCondition condition;

	public ApplicationInstanceSpecification(ApplicationInstanceCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ApplicationInstance> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddAppPredicate(predicateList, root, cb);
		tryAddHostPredicate(predicateList, root, cb);
		tryAddPortPredicate(predicateList, root, cb);
		tryAddAlivePredicate(predicateList, root, cb);
		tryAddAlarmPredicate(predicateList, root, cb);
		tryAddAliveTimePredicate(predicateList, root, cb);
		tryAddDetectionTimePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddAppPredicate(List<Predicate> predicateList, Root<ApplicationInstance> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getApp())){
			predicateList.add(cb.like(root.get(ApplicationInstance.PROPERTY_APP).as(String.class), "%"+condition.getApp()+"%"));
		}
	}
	private void tryAddHostPredicate(List<Predicate> predicateList, Root<ApplicationInstance> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getHost())){
			predicateList.add(cb.like(root.get(ApplicationInstance.PROPERTY_HOST).as(String.class), "%"+condition.getHost()+"%"));
		}
	}
	private void tryAddPortPredicate(List<Predicate> predicateList, Root<ApplicationInstance> root, CriteriaBuilder cb){

		if (null != condition.getPort() ) {
			predicateList.add(cb.equal(root.get(ApplicationInstance.PROPERTY_PORT).as(Integer.class), condition.getPort()));
		}

		if (null != condition.getPortMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ApplicationInstance.PROPERTY_PORT).as(Integer.class), condition.getPortMax()));
		}

		if (null != condition.getPortMin() ) {
			predicateList.add(cb.lessThan(root.get(ApplicationInstance.PROPERTY_PORT).as(Integer.class), condition.getPortMin()));
		}
	}
	private void tryAddAlivePredicate(List<Predicate> predicateList, Root<ApplicationInstance> root, CriteriaBuilder cb){
		if (null != condition.getAlive() ) {
			predicateList.add(cb.equal(root.get(ApplicationInstance.PROPERTY_ALIVE).as(Boolean.class), condition.getAlive()));
		}
	}
	private void tryAddAlarmPredicate(List<Predicate> predicateList, Root<ApplicationInstance> root, CriteriaBuilder cb){
		if (null != condition.getAlarm() ) {
			predicateList.add(cb.equal(root.get(ApplicationInstance.PROPERTY_ALARM).as(Boolean.class), condition.getAlarm()));
		}
	}
	private void tryAddAliveTimePredicate(List<Predicate> predicateList, Root<ApplicationInstance> root, CriteriaBuilder cb){

		if (null != condition.getAliveTime() ) {
			predicateList.add(cb.equal(root.get(ApplicationInstance.PROPERTY_ALIVE_TIME).as(Date.class), condition.getAliveTime()));
		}

		if (null != condition.getAliveTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ApplicationInstance.PROPERTY_ALIVE_TIME).as(Date.class), condition.getAliveTimeStart()));
		}

		if (null != condition.getAliveTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(ApplicationInstance.PROPERTY_ALIVE_TIME).as(Date.class), condition.getAliveTimeEnd()));
		}
	}
	private void tryAddDetectionTimePredicate(List<Predicate> predicateList, Root<ApplicationInstance> root, CriteriaBuilder cb){

		if (null != condition.getDetectionTime() ) {
			predicateList.add(cb.equal(root.get(ApplicationInstance.PROPERTY_DETECTION_TIME).as(Date.class), condition.getDetectionTime()));
		}

		if (null != condition.getDetectionTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ApplicationInstance.PROPERTY_DETECTION_TIME).as(Date.class), condition.getDetectionTimeStart()));
		}

		if (null != condition.getDetectionTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(ApplicationInstance.PROPERTY_DETECTION_TIME).as(Date.class), condition.getDetectionTimeEnd()));
		}
	}
}


