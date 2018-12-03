package com.yunkang.saas.platform.authentication.business.platform.security.dao;

import com.yunkang.saas.platform.authentication.business.platform.security.domain.Account;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AccountSpecification implements Specification<Account>{

	private AccountCondition condition;

	public AccountSpecification(AccountCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNickNamePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddAccountNamePredicate(predicateList, root, cb);
		tryAddAccountPasswordPredicate(predicateList, root, cb);
		tryAddMobilePredicate(predicateList, root, cb);
		tryAddEmailPredicate(predicateList, root, cb);
		tryAddEnablePredicate(predicateList, root, cb);
		tryAddExpiredPredicate(predicateList, root, cb);
		tryAddLockedPredicate(predicateList, root, cb);
		tryAddDeletedPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(Account.PROPERTY_TID).as(Long.class), condition.getTid()));
        }  
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
	private void tryAddAccountPasswordPredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAccountPassword())){
			predicateList.add(cb.like(root.get(Account.PROPERTY_ACCOUNT_PASSWORD).as(String.class), "%"+condition.getAccountPassword()+"%"));
		}
	}
	private void tryAddMobilePredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMobile())){
			predicateList.add(cb.like(root.get(Account.PROPERTY_MOBILE).as(String.class), "%"+condition.getMobile()+"%"));
		}
	}
	private void tryAddEmailPredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEmail())){
			predicateList.add(cb.like(root.get(Account.PROPERTY_EMAIL).as(String.class), "%"+condition.getEmail()+"%"));
		}
	}
	private void tryAddEnablePredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if (null != condition.getEnable() ) {
			predicateList.add(cb.equal(root.get(Account.PROPERTY_ENABLE).as(Boolean.class), condition.getEnable()));
		}
	}
	private void tryAddExpiredPredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if (null != condition.getExpired() ) {
			predicateList.add(cb.equal(root.get(Account.PROPERTY_EXPIRED).as(Boolean.class), condition.getExpired()));
		}
	}
	private void tryAddLockedPredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if (null != condition.getLocked() ) {
			predicateList.add(cb.equal(root.get(Account.PROPERTY_LOCKED).as(Boolean.class), condition.getLocked()));
		}
	}
	private void tryAddDeletedPredicate(List<Predicate> predicateList, Root<Account> root, CriteriaBuilder cb){
		if (null != condition.getDeleted() ) {
			predicateList.add(cb.equal(root.get(Account.PROPERTY_DELETED).as(Boolean.class), condition.getDeleted()));
		}
	}
}


