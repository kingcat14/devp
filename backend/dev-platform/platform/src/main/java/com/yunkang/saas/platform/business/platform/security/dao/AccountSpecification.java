package com.yunkang.saas.platform.business.platform.security.dao;

import com.yunkang.saas.platform.business.platform.security.domain.Account;
import com.yunkang.saas.platform.business.platform.security.domain.Role;
import com.yunkang.saas.platform.business.platform.security.dto.AccountCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AccountSpecification implements Specification<Account>{

	AccountCondition condition;

	public AccountSpecification(AccountCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNickNamePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddAccountNamePredicate(predicateList, root, cb);
		tryAddMobilePredicate(predicateList, root, cb);
		tryAddEmailPredicate(predicateList, root, cb);
		tryAddEnablePredicate(predicateList, root, cb);
		tryAddTenantIdPredicate(predicateList, root, cb);
		tryAddAppIdPredicate(predicateList, root, cb);

		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);

		return cb.and(pre);
	}


	private void tryAddNickNamePredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNickName())){
			predicateList.add(cb.like(root.get(Account.PROPERTY_NICK_NAME).as(String.class), "%"+condition.getNickName()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Account.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAccountNamePredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAccountName())){
			predicateList.add(cb.equal(root.get(Account.PROPERTY_ACCOUNT_NAME).as(String.class), condition.getAccountName()));
		}
	}
	private void tryAddMobilePredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMobile())){
			predicateList.add(cb.equal(root.get(Account.PROPERTY_MOBILE).as(String.class), condition.getMobile()));
		}
	}
	private void tryAddEmailPredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEmail())){
			predicateList.add(cb.equal(root.get(Account.PROPERTY_EMAIL).as(String.class), condition.getEmail()));
		}
	}
	private void tryAddEnablePredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if(null != condition.getEnable()){
			predicateList.add(cb.equal(root.get(Account.PROPERTY_ENABLE).as(String.class), condition.getEnable()));
		}
	}
	private void tryAddTenantIdPredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if(null != condition.getTenantId()){
			predicateList.add(cb.equal(root.get(Account.PROPERTY_TENANT_ID).as(Long.class), condition.getTenantId()));
		}
	}
	private void tryAddAppIdPredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if(null != condition.getAppId()){
			predicateList.add(cb.equal(root.get(Account.PROPERTY_APP_ID).as(Long.class), condition.getAppId()));
		}
	}
}


