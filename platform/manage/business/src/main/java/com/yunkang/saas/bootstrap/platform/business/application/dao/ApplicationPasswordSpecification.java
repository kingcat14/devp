package com.yunkang.saas.bootstrap.platform.business.application.dao;

import com.yunkang.saas.bootstrap.platform.business.application.domain.ApplicationPassword;
import com.yunkang.saas.bootstrap.platform.business.application.dto.ApplicationPasswordCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ApplicationPasswordSpecification implements Specification<ApplicationPassword>{

	private ApplicationPasswordCondition condition;

	public ApplicationPasswordSpecification(ApplicationPasswordCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ApplicationPassword> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddAccessIdPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddAccessIdPredicate(List<Predicate> predicateList, Root<ApplicationPassword> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAccessId())){
			predicateList.add(cb.like(root.get(ApplicationPassword.PROPERTY_ACCESS_ID).as(String.class), "%"+condition.getAccessId()+"%"));
		}
	}
}


