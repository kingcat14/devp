package com.yunkang.saas.platform.business.platform.tenant.dao;

import com.yunkang.saas.platform.business.platform.tenant.dto.TenantCondition;
import com.yunkang.saas.platform.business.platform.tenant.domain.Tenant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class TenantSpecification implements Specification<Tenant>{

	private TenantCondition condition;

	public TenantSpecification(TenantCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Tenant> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTenantCodePredicate(predicateList, root, cb);
		tryAddTenantTypePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddMobilePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTenantCodePredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTenantCode())){
			predicateList.add(cb.like(root.get(Tenant.PROPERTY_TENANT_CODE).as(String.class), "%"+condition.getTenantCode()+"%"));
		}
	}
	private void tryAddTenantTypePredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if (null != condition.getTenantType() ) {
			predicateList.add(cb.equal(root.get(Tenant.PROPERTY_TENANT_TYPE).as(Long.class), condition.getTenantType()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Tenant.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddMobilePredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if (null != condition.getMobile() ) {
			predicateList.add(cb.equal(root.get(Tenant.PROPERTY_MOBILE).as(Long.class), condition.getMobile()));
		}
	}
}


