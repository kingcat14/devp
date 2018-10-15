package com.yunkang.saas.bootstrap.business.platform.tenant.dao;

import com.yunkang.saas.bootstrap.business.platform.tenant.domain.TenantType;
import com.yunkang.saas.bootstrap.business.platform.tenant.dto.TenantTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TenantTypeSpecification implements Specification<TenantType>{

	private TenantTypeCondition condition;

	public TenantTypeSpecification(TenantTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<TenantType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTenantTypeCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTenantTypeCodePredicate(List<Predicate> predicateList, Root<TenantType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTenantTypeCode())){
			predicateList.add(cb.like(root.get(TenantType.PROPERTY_TENANT_TYPE_CODE).as(String.class), "%"+condition.getTenantTypeCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<TenantType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(TenantType.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
}


