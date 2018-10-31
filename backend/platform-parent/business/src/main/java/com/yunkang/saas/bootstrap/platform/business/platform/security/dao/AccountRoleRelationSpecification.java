package com.yunkang.saas.bootstrap.platform.business.platform.security.dao;


import com.yunkang.saas.bootstrap.platform.business.platform.security.domain.AccountRoleRelation;
import com.yunkang.saas.bootstrap.platform.business.platform.security.dto.AccountRoleRelationCondition;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AccountRoleRelationSpecification implements Specification<AccountRoleRelation>{

	AccountRoleRelationCondition condition;

	public AccountRoleRelationSpecification(AccountRoleRelationCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AccountRoleRelation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddAccountIdPredicate(predicateList, root, cb);
		tryAddRoleIdPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddAccountIdPredicate(List<Predicate> predicateList, Root<AccountRoleRelation> root, CriteriaBuilder cb){
		if (null != condition.getAccountId() ) {
			predicateList.add(cb.equal(root.get(AccountRoleRelation.PROPERTY_ACCOUNT_ID).as(Long.class), condition.getAccountId()));
		}
	}
	private void tryAddRoleIdPredicate(List<Predicate> predicateList, Root<AccountRoleRelation> root, CriteriaBuilder cb){
		if (null != condition.getRoleId() ) {
			predicateList.add(cb.equal(root.get(AccountRoleRelation.PROPERTY_ROLE_ID).as(Long.class), condition.getRoleId()));
		}
	}
}


