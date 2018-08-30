package net.aicoder.speedcloud.business.app.dao;

import net.aicoder.speedcloud.business.app.dto.SecurityConfigCondition;
import net.aicoder.speedcloud.business.app.domain.SecurityConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class SecurityConfigSpecification implements Specification<SecurityConfig>{

	private SecurityConfigCondition condition;

	public SecurityConfigSpecification(SecurityConfigCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<SecurityConfig> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddAppPredicate(predicateList, root, cb);
		tryAddItemNamePredicate(predicateList, root, cb);
		tryAddItemValuePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<SecurityConfig> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(SecurityConfig.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(SecurityConfig.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(SecurityConfig.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddAppPredicate(List<Predicate> predicateList, Root<SecurityConfig> root, CriteriaBuilder cb){
	    if (null != condition.getApp() ) {
            predicateList.add(cb.equal(root.get(SecurityConfig.PROPERTY_APP).as(Long.class), condition.getApp()));
        }
	}
	private void tryAddItemNamePredicate(List<Predicate> predicateList, Root<SecurityConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getItemName())){
			predicateList.add(cb.like(root.get(SecurityConfig.PROPERTY_ITEM_NAME).as(String.class), "%"+condition.getItemName()+"%"));
		}
	}
	private void tryAddItemValuePredicate(List<Predicate> predicateList, Root<SecurityConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getItemValue())){
			predicateList.add(cb.like(root.get(SecurityConfig.PROPERTY_ITEM_VALUE).as(String.class), "%"+condition.getItemValue()+"%"));
		}
	}
}


