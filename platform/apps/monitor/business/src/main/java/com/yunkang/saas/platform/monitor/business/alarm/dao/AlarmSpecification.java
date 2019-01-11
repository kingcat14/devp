package com.yunkang.saas.platform.monitor.business.alarm.dao;

import com.yunkang.saas.platform.monitor.business.alarm.domain.Alarm;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarmSpecification implements Specification<Alarm>{

	private AlarmCondition condition;

	public AlarmSpecification(AlarmCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Alarm> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddAppPredicate(predicateList, root, cb);
		tryAddCounterPredicate(predicateList, root, cb);
		tryAddValuePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddOccurTimePredicate(predicateList, root, cb);
		tryAddLastOccurTimePredicate(predicateList, root, cb);
		tryAddDisappearTimePredicate(predicateList, root, cb);
		tryAddOccurCountPredicate(predicateList, root, cb);
		tryAddContentPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Alarm.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Alarm.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){
	    if (null != condition.getType() ) {
            predicateList.add(cb.equal(root.get(Alarm.PROPERTY_TYPE).as(String.class), condition.getType()));
        }
	}
	private void tryAddAppPredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){
	    if (null != condition.getApp() ) {
            predicateList.add(cb.equal(root.get(Alarm.PROPERTY_APP).as(String.class), condition.getApp()));
        }
	}
	private void tryAddCounterPredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){
	    if (null != condition.getCounter() ) {
            predicateList.add(cb.equal(root.get(Alarm.PROPERTY_COUNTER).as(String.class), condition.getCounter()));
        }
	}
	private void tryAddValuePredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getValue())){
			predicateList.add(cb.like(root.get(Alarm.PROPERTY_VALUE).as(String.class), "%"+condition.getValue()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(Alarm.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddOccurTimePredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){

		if (null != condition.getOccurTime() ) {
			predicateList.add(cb.equal(root.get(Alarm.PROPERTY_OCCUR_TIME).as(Date.class), condition.getOccurTime()));
		}

		if (null != condition.getOccurTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Alarm.PROPERTY_OCCUR_TIME).as(Date.class), condition.getOccurTimeStart()));
		}

		if (null != condition.getOccurTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(Alarm.PROPERTY_OCCUR_TIME).as(Date.class), condition.getOccurTimeEnd()));
		}
	}
	private void tryAddLastOccurTimePredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){

		if (null != condition.getLastOccurTime() ) {
			predicateList.add(cb.equal(root.get(Alarm.PROPERTY_LAST_OCCUR_TIME).as(Date.class), condition.getLastOccurTime()));
		}

		if (null != condition.getLastOccurTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Alarm.PROPERTY_LAST_OCCUR_TIME).as(Date.class), condition.getLastOccurTimeStart()));
		}

		if (null != condition.getLastOccurTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(Alarm.PROPERTY_LAST_OCCUR_TIME).as(Date.class), condition.getLastOccurTimeEnd()));
		}
	}
	private void tryAddDisappearTimePredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){

		if (null != condition.getDisappearTime() ) {
			predicateList.add(cb.equal(root.get(Alarm.PROPERTY_DISAPPEAR_TIME).as(Date.class), condition.getDisappearTime()));
		}

		if (null != condition.getDisappearTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Alarm.PROPERTY_DISAPPEAR_TIME).as(Date.class), condition.getDisappearTimeStart()));
		}

		if (null != condition.getDisappearTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(Alarm.PROPERTY_DISAPPEAR_TIME).as(Date.class), condition.getDisappearTimeEnd()));
		}
	}
	private void tryAddOccurCountPredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){

		if (null != condition.getOccurCount() ) {
			predicateList.add(cb.equal(root.get(Alarm.PROPERTY_OCCUR_COUNT).as(Integer.class), condition.getOccurCount()));
		}

		if (null != condition.getOccurCountMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Alarm.PROPERTY_OCCUR_COUNT).as(Integer.class), condition.getOccurCountMax()));
		}

		if (null != condition.getOccurCountMin() ) {
			predicateList.add(cb.lessThan(root.get(Alarm.PROPERTY_OCCUR_COUNT).as(Integer.class), condition.getOccurCountMin()));
		}
	}
	private void tryAddContentPredicate(List<Predicate> predicateList, Root<Alarm> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getContent())){
			predicateList.add(cb.like(root.get(Alarm.PROPERTY_CONTENT).as(String.class), "%"+condition.getContent()+"%"));
		}
	}
}


