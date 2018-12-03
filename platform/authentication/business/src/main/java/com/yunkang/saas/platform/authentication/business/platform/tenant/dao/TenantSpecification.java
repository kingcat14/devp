package com.yunkang.saas.platform.authentication.business.platform.tenant.dao;

import com.yunkang.saas.platform.authentication.business.platform.tenant.domain.Tenant;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
		tryAddCountryPredicate(predicateList, root, cb);
		tryAddProvincePredicate(predicateList, root, cb);
		tryAddCityPredicate(predicateList, root, cb);
		tryAddAddressPredicate(predicateList, root, cb);
		tryAddFaxPredicate(predicateList, root, cb);
		tryAddTelephoneNoPredicate(predicateList, root, cb);
		tryAddCrmCodePredicate(predicateList, root, cb);
		tryAddPrefixDomainNamePredicate(predicateList, root, cb);
		tryAddMobilePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);


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
	private void tryAddCountryPredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCountry())){
			predicateList.add(cb.like(root.get(Tenant.PROPERTY_COUNTRY).as(String.class), "%"+condition.getCountry()+"%"));
		}
	}
	private void tryAddProvincePredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProvince())){
			predicateList.add(cb.like(root.get(Tenant.PROPERTY_PROVINCE).as(String.class), "%"+condition.getProvince()+"%"));
		}
	}
	private void tryAddCityPredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCity())){
			predicateList.add(cb.like(root.get(Tenant.PROPERTY_CITY).as(String.class), "%"+condition.getCity()+"%"));
		}
	}
	private void tryAddAddressPredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAddress())){
			predicateList.add(cb.like(root.get(Tenant.PROPERTY_ADDRESS).as(String.class), "%"+condition.getAddress()+"%"));
		}
	}
	private void tryAddFaxPredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFax())){
			predicateList.add(cb.like(root.get(Tenant.PROPERTY_FAX).as(String.class), "%"+condition.getFax()+"%"));
		}
	}
	private void tryAddTelephoneNoPredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTelephoneNo())){
			predicateList.add(cb.like(root.get(Tenant.PROPERTY_TELEPHONE_NO).as(String.class), "%"+condition.getTelephoneNo()+"%"));
		}
	}
	private void tryAddCrmCodePredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCrmCode())){
			predicateList.add(cb.like(root.get(Tenant.PROPERTY_CRM_CODE).as(String.class), "%"+condition.getCrmCode()+"%"));
		}
	}
	private void tryAddPrefixDomainNamePredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPrefixDomainName())){
			predicateList.add(cb.like(root.get(Tenant.PROPERTY_PREFIX_DOMAIN_NAME).as(String.class), "%"+condition.getPrefixDomainName()+"%"));
		}
	}
	private void tryAddMobilePredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){

		if (null != condition.getMobile() ) {
			predicateList.add(cb.equal(root.get(Tenant.PROPERTY_MOBILE).as(Long.class), condition.getMobile()));
		}

		if (null != condition.getMobileMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Tenant.PROPERTY_MOBILE).as(Long.class), condition.getMobileMax()));
		}

		if (null != condition.getMobileMin() ) {
			predicateList.add(cb.lessThan(root.get(Tenant.PROPERTY_MOBILE).as(Long.class), condition.getMobileMin()));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<Tenant> root, CriteriaBuilder cb){
		if (null != condition.getStatus() ) {
			predicateList.add(cb.equal(root.get(Tenant.PROPERTY_STATUS).as(Boolean.class), condition.getStatus()));
		}
	}
}


