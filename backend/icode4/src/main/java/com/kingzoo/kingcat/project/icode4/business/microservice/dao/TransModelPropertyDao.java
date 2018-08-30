package com.kingzoo.kingcat.project.icode4.business.microservice.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModelProperty;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelPropertyCondition;
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
 * 传输对象属性的数据库操作
 * @author icode
 */
@Repository("transModelPropertyDao")
public class TransModelPropertyDao extends SimpleJpaRepository<TransModelProperty, String, TransModelPropertyCondition> {

	@Override
	public Specification<TransModelProperty> buildQuery(final TransModelPropertyCondition condition){

		return new TransModelPropertySpecification(condition);
	}
}

class TransModelPropertySpecification implements Specification<TransModelProperty>{

	TransModelPropertyCondition condition;

	public TransModelPropertySpecification(TransModelPropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<TransModelProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddTransModelPredicate(predicateList, root, cb);
		tryAddEditablePredicate(predicateList, root, cb);
		tryAddNullablePredicate(predicateList, root, cb);
		tryAddMemoPredicate(predicateList, root, cb);
		tryAddCoreRelationPredicate(predicateList, root, cb);
		tryAddDomainModelPredicate(predicateList, root, cb);
		tryAddDomainModelPropertyPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<TransModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(TransModelProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<TransModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(TransModelProperty.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<TransModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(TransModelProperty.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddTransModelPredicate(List<Predicate> predicateList, Root<TransModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTransModelId())){
			predicateList.add(cb.equal(root.get(TransModelProperty.PROPERTY_TRANS_MODEL_ID).as(String.class), condition.getTransModelId()));
		}
	}
	private void tryAddEditablePredicate(List<Predicate> predicateList, Root<TransModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getEditable() ) {
			predicateList.add(cb.equal(root.get(TransModelProperty.PROPERTY_EDITABLE).as(Boolean.class), condition.getEditable()));
		}
	}
	private void tryAddNullablePredicate(List<Predicate> predicateList, Root<TransModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getNullable() ) {
			predicateList.add(cb.equal(root.get(TransModelProperty.PROPERTY_NULLABLE).as(Boolean.class), condition.getNullable()));
		}
	}
	private void tryAddMemoPredicate(List<Predicate> predicateList, Root<TransModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMemo())){
			predicateList.add(cb.like(root.get(TransModelProperty.PROPERTY_MEMO).as(String.class), "%"+condition.getMemo()+"%"));
		}
	}
	private void tryAddCoreRelationPredicate(List<Predicate> predicateList, Root<TransModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getCoreRelation() ) {
			predicateList.add(cb.equal(root.get(TransModelProperty.PROPERTY_CORE_RELATION).as(Boolean.class), condition.getCoreRelation()));
		}
	}
	private void tryAddDomainModelPredicate(List<Predicate> predicateList, Root<TransModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDomainModelId())){
			predicateList.add(cb.equal(root.get(TransModelProperty.PROPERTY_DOMAIN_MODEL_ID).as(String.class), condition.getDomainModelId()));
		}
	}
	private void tryAddDomainModelPropertyPredicate(List<Predicate> predicateList, Root<TransModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDomainModelPropertyId())){
			predicateList.add(cb.equal(root.get(TransModelProperty.PROPERTY_DOMAIN_MODEL_PROPERTY_ID).as(String.class), condition.getDomainModelPropertyId()));
		}
	}
}

