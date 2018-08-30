package com.kingzoo.kingcat.project.icode4.business.system.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.system.vo.SystemCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统的数据库操作
 * @author icode
 */
@Repository("systemDao")
public class SystemDao extends SimpleJpaRepository<System, String, SystemCondition> {

	@Override
	public Specification<System> buildQuery(final SystemCondition condition){

		return new SystemSpecification(condition);
	}
}

class SystemSpecification implements Specification<System>{

	SystemCondition condition;

	public SystemSpecification(SystemCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<System> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
		tryAddProductIdPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddBasePackagePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}

	private void tryAddProductIdPredicate(List<Predicate> predicateList, Root<System> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProductId())){
			predicateList.add(cb.equal(root.get(System.PROPERTY_PRODUCT_ID).as(String.class), condition.getProductId()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<System> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(System.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<System> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.equal(root.get(System.PROPERTY_CODE).as(String.class), condition.getCode()));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<System> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(System.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddBasePackagePredicate(List<Predicate> predicateList, Root<System> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBasePackage())){
			predicateList.add(cb.like(root.get(System.PROPERTY_BASE_PACKAGE).as(String.class), "%"+condition.getBasePackage()+"%"));
		}
	}
}

