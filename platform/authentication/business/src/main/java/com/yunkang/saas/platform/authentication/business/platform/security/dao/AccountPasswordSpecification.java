package com.yunkang.saas.platform.authentication.business.platform.security.dao;

import com.yunkang.saas.platform.authentication.business.platform.security.domain.AccountPassword;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountPasswordCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AccountPasswordSpecification implements Specification<AccountPassword>{

	private AccountPasswordCondition condition;

	public AccountPasswordSpecification(AccountPasswordCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AccountPassword> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddAccountPredicate(predicateList, root, cb);
		tryAddAccountNamePredicate(predicateList, root, cb);
		tryAddPasswordPredicate(predicateList, root, cb);
		tryAddWrongCountPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddAccountPredicate(List<Predicate> predicateList, Root<AccountPassword> root, CriteriaBuilder cb){
	    if (null != condition.getAccount() ) {
            predicateList.add(cb.equal(root.get(AccountPassword.PROPERTY_ACCOUNT).as(Long.class), condition.getAccount()));
        }
	}
	private void tryAddAccountNamePredicate(List<Predicate> predicateList, Root<AccountPassword> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAccountName())){
			predicateList.add(cb.like(root.get(AccountPassword.PROPERTY_ACCOUNT_NAME).as(String.class), "%"+condition.getAccountName()+"%"));
		}
	}
	private void tryAddPasswordPredicate(List<Predicate> predicateList, Root<AccountPassword> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPassword())){
			predicateList.add(cb.like(root.get(AccountPassword.PROPERTY_PASSWORD).as(String.class), "%"+condition.getPassword()+"%"));
		}
	}
	private void tryAddWrongCountPredicate(List<Predicate> predicateList, Root<AccountPassword> root, CriteriaBuilder cb){

		if (null != condition.getWrongCount() ) {
			predicateList.add(cb.equal(root.get(AccountPassword.PROPERTY_WRONG_COUNT).as(Integer.class), condition.getWrongCount()));
		}

		if (null != condition.getWrongCountMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AccountPassword.PROPERTY_WRONG_COUNT).as(Integer.class), condition.getWrongCountMax()));
		}

		if (null != condition.getWrongCountMin() ) {
			predicateList.add(cb.lessThan(root.get(AccountPassword.PROPERTY_WRONG_COUNT).as(Integer.class), condition.getWrongCountMin()));
		}
	}
}


