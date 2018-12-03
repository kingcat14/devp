package com.yunkang.saas.platform.authentication.business.platform.tenant.dao;

import com.yunkang.saas.platform.authentication.business.platform.tenant.domain.AppTenantRelation;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.AppTenantRelationCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppTenantRelationSpecification implements Specification<AppTenantRelation>{

	private AppTenantRelationCondition condition;

	public AppTenantRelationSpecification(AppTenantRelationCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AppTenantRelation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddAppPredicate(predicateList, root, cb);
		tryAddAccountPredicate(predicateList, root, cb);
		tryAddStartDatePredicate(predicateList, root, cb);
		tryAddEndDatePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddAppPredicate(List<Predicate> predicateList, Root<AppTenantRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getApp())){
			predicateList.add(cb.like(root.get(AppTenantRelation.PROPERTY_APP).as(String.class), "%"+condition.getApp()+"%"));
		}
	}
	private void tryAddAccountPredicate(List<Predicate> predicateList, Root<AppTenantRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAccount())){
			predicateList.add(cb.like(root.get(AppTenantRelation.PROPERTY_ACCOUNT).as(String.class), "%"+condition.getAccount()+"%"));
		}
	}
	private void tryAddStartDatePredicate(List<Predicate> predicateList, Root<AppTenantRelation> root, CriteriaBuilder cb){

		if (null != condition.getStartDate() ) {
			predicateList.add(cb.equal(root.get(AppTenantRelation.PROPERTY_START_DATE).as(Date.class), condition.getStartDate()));
		}

		if (null != condition.getStartDateStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AppTenantRelation.PROPERTY_START_DATE).as(Date.class), condition.getStartDateStart()));
		}

		if (null != condition.getStartDateEnd() ) {
			predicateList.add(cb.lessThan(root.get(AppTenantRelation.PROPERTY_START_DATE).as(Date.class), condition.getStartDateEnd()));
		}
	}
	private void tryAddEndDatePredicate(List<Predicate> predicateList, Root<AppTenantRelation> root, CriteriaBuilder cb){

		if (null != condition.getEndDate() ) {
			predicateList.add(cb.equal(root.get(AppTenantRelation.PROPERTY_END_DATE).as(Date.class), condition.getEndDate()));
		}

		if (null != condition.getEndDateStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AppTenantRelation.PROPERTY_END_DATE).as(Date.class), condition.getEndDateStart()));
		}

		if (null != condition.getEndDateEnd() ) {
			predicateList.add(cb.lessThan(root.get(AppTenantRelation.PROPERTY_END_DATE).as(Date.class), condition.getEndDateEnd()));
		}
	}
}


