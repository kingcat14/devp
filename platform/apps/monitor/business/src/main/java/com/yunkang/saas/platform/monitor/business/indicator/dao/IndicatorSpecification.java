package com.yunkang.saas.platform.monitor.business.indicator.dao;

import com.yunkang.saas.platform.monitor.business.indicator.dto.IndicatorCondition;
import com.yunkang.saas.platform.monitor.business.indicator.domain.Indicator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class IndicatorSpecification implements Specification<Indicator>{

	private IndicatorCondition condition;

	public IndicatorSpecification(IndicatorCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Indicator> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Indicator> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Indicator.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Indicator> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Indicator.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<Indicator> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(Indicator.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
}


