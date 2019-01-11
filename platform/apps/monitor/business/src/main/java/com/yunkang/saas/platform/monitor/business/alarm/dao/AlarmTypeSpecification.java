package com.yunkang.saas.platform.monitor.business.alarm.dao;

import com.yunkang.saas.platform.monitor.business.alarm.domain.AlarmType;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AlarmTypeSpecification implements Specification<AlarmType>{

	private AlarmTypeCondition condition;

	public AlarmTypeSpecification(AlarmTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AlarmType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddCodePredicate(List<Predicate> predicateList, Root<AlarmType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(AlarmType.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<AlarmType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(AlarmType.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
}


