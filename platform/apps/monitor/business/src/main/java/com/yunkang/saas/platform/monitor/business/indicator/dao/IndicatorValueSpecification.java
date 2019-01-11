package com.yunkang.saas.platform.monitor.business.indicator.dao;

import com.yunkang.saas.platform.monitor.business.indicator.domain.IndicatorValue;
import com.yunkang.saas.platform.monitor.business.indicator.dto.IndicatorValueCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IndicatorValueSpecification implements Specification<IndicatorValue>{

	private IndicatorValueCondition condition;

	public IndicatorValueSpecification(IndicatorValueCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<IndicatorValue> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddIndicatorPredicate(predicateList, root, cb);
		tryAddTargetPredicate(predicateList, root, cb);
		tryAddTargetTypePredicate(predicateList, root, cb);
		tryAddValuePredicate(predicateList, root, cb);
		tryAddCollectTimePredicate(predicateList, root, cb);
		tryAddSaveTimePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddIndicatorPredicate(List<Predicate> predicateList, Root<IndicatorValue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getIndicator())){
			predicateList.add(cb.like(root.get(IndicatorValue.PROPERTY_INDICATOR).as(String.class), "%"+condition.getIndicator()+"%"));
		}
	}
	private void tryAddTargetPredicate(List<Predicate> predicateList, Root<IndicatorValue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTarget())){
			predicateList.add(cb.like(root.get(IndicatorValue.PROPERTY_TARGET).as(String.class), "%"+condition.getTarget()+"%"));
		}
	}
	private void tryAddTargetTypePredicate(List<Predicate> predicateList, Root<IndicatorValue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTargetType())){
			predicateList.add(cb.like(root.get(IndicatorValue.PROPERTY_TARGET_TYPE).as(String.class), "%"+condition.getTargetType()+"%"));
		}
	}
	private void tryAddValuePredicate(List<Predicate> predicateList, Root<IndicatorValue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getValue())){
			predicateList.add(cb.like(root.get(IndicatorValue.PROPERTY_VALUE).as(String.class), "%"+condition.getValue()+"%"));
		}
	}
	private void tryAddCollectTimePredicate(List<Predicate> predicateList, Root<IndicatorValue> root, CriteriaBuilder cb){

		if (null != condition.getCollectTime() ) {
			predicateList.add(cb.equal(root.get(IndicatorValue.PROPERTY_COLLECT_TIME).as(Date.class), condition.getCollectTime()));
		}

		if (null != condition.getCollectTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(IndicatorValue.PROPERTY_COLLECT_TIME).as(Date.class), condition.getCollectTimeStart()));
		}

		if (null != condition.getCollectTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(IndicatorValue.PROPERTY_COLLECT_TIME).as(Date.class), condition.getCollectTimeEnd()));
		}
	}
	private void tryAddSaveTimePredicate(List<Predicate> predicateList, Root<IndicatorValue> root, CriteriaBuilder cb){

		if (null != condition.getSaveTime() ) {
			predicateList.add(cb.equal(root.get(IndicatorValue.PROPERTY_SAVE_TIME).as(Date.class), condition.getSaveTime()));
		}

		if (null != condition.getSaveTimeStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(IndicatorValue.PROPERTY_SAVE_TIME).as(Date.class), condition.getSaveTimeStart()));
		}

		if (null != condition.getSaveTimeEnd() ) {
			predicateList.add(cb.lessThan(root.get(IndicatorValue.PROPERTY_SAVE_TIME).as(Date.class), condition.getSaveTimeEnd()));
		}
	}
}


