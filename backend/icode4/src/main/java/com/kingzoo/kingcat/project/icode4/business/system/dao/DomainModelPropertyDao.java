package com.kingzoo.kingcat.project.icode4.business.system.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelPropertyCondition;
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
 * 领域对象属性的数据库操作
 * @author icode
 */
@Repository("domainModelPropertyDao")
public class DomainModelPropertyDao extends SimpleJpaRepository<DomainModelProperty, String, DomainModelPropertyCondition> {

	@Override
	public Specification<DomainModelProperty> buildQuery(final DomainModelPropertyCondition condition){

		return new DomainModelPropertySpecification(condition);
	}
}

class DomainModelPropertySpecification implements Specification<DomainModelProperty>{

	DomainModelPropertyCondition condition;

	public DomainModelPropertySpecification(DomainModelPropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DomainModelProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddDomainModelPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddPersistPredicate(predicateList, root, cb);
		tryAddEditablePredicate(predicateList, root, cb);
		tryAddNullablePredicate(predicateList, root, cb);
		tryAddMemoPredicate(predicateList, root, cb);
		tryAddRelatedDomainModelPredicate(predicateList, root, cb);
		tryAddRelatedDomainModelPropertyPredicate(predicateList, root, cb);
		tryAddViewIndexPredicate(predicateList, root, cb);
		tryPrimaryKeyPredicate(predicateList, root, cb);

		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddDomainModelPredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDomainModelId())){
			predicateList.add(cb.equal(root.get(DomainModelProperty.PROPERTY_DOMAIN_MODEL_ID).as(String.class), condition.getDomainModelId()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DomainModelProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DomainModelProperty.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DomainModelProperty.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddPersistPredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getPersist() ) {
			predicateList.add(cb.equal(root.get(DomainModelProperty.PROPERTY_PERSIST).as(Boolean.class), condition.getPersist()));
		}
	}
	private void tryAddEditablePredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getEditable() ) {
			predicateList.add(cb.equal(root.get(DomainModelProperty.PROPERTY_EDITABLE).as(Boolean.class), condition.getEditable()));
		}
	}
	private void tryAddNullablePredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getNullable() ) {
			predicateList.add(cb.equal(root.get(DomainModelProperty.PROPERTY_NULLABLE).as(Boolean.class), condition.getNullable()));
		}
	}
	private void tryAddMemoPredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMemo())){
			predicateList.add(cb.like(root.get(DomainModelProperty.PROPERTY_MEMO).as(String.class), "%"+condition.getMemo()+"%"));
		}
	}
	private void tryAddRelatedDomainModelPredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRelatedDomainModelId())){
			predicateList.add(cb.equal(root.get(DomainModelProperty.PROPERTY_RELATED_DOMAIN_MODEL_ID).as(String.class), condition.getRelatedDomainModelId()));
		}
	}
	private void tryAddRelatedDomainModelPropertyPredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRelatedDomainModelPropertyId())){
			predicateList.add(cb.equal(root.get(DomainModelProperty.PROPERTY_RELATED_DOMAIN_MODEL_PROPERTY_ID).as(String.class), condition.getRelatedDomainModelPropertyId()));
		}
	}
	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getViewIndex() ) {
			predicateList.add(cb.equal(root.get(DomainModelProperty.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndex()));
		}
	}
	private void tryPrimaryKeyPredicate(List<Predicate> predicateList, Root<DomainModelProperty> root, CriteriaBuilder cb){
		if (null != condition.getPrimaryKey() ) {
			predicateList.add(cb.equal(root.get(DomainModelProperty.PROPERTY_PRIMARY_KEY).as(Boolean.class), condition.getPrimaryKey()));
		}
	}
}

