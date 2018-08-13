package com.yunkang.saas.platform.business.platform.application.dao;

import com.yunkang.saas.platform.business.platform.application.dto.ConfigAppCategoryCondition;
import com.yunkang.saas.platform.business.platform.application.domain.ConfigAppCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ConfigAppCategorySpecification implements Specification<ConfigAppCategory>{

	private ConfigAppCategoryCondition condition;

	public ConfigAppCategorySpecification(ConfigAppCategoryCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ConfigAppCategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<ConfigAppCategory> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(ConfigAppCategory.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<ConfigAppCategory> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(ConfigAppCategory.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
}


