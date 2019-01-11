package com.yunkang.saas.platform.authentication.business.platform.tenant.dao;

import com.yunkang.saas.platform.authentication.business.platform.tenant.domain.TenantAdminAccount;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantAdminAccountCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TenantAdminAccountSpecification implements Specification<TenantAdminAccount>{

	private TenantAdminAccountCondition condition;

	public TenantAdminAccountSpecification(TenantAdminAccountCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<TenantAdminAccount> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddAccountNamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<TenantAdminAccount> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(TenantAdminAccount.PROPERTY_TID).as(Long.class), condition.getTid()));
        }  
	}
	private void tryAddAccountNamePredicate(List<Predicate> predicateList, Root<TenantAdminAccount> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAccountName())){
			predicateList.add(cb.like(root.get(TenantAdminAccount.PROPERTY_ACCOUNT_NAME).as(String.class), "%"+condition.getAccountName()+"%"));
		}
	}
}


