package net.aicoder.speedcloud.icode.business.domain.dao;

import net.aicoder.speedcloud.icode.business.domain.domain.EntityViewProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EntityViewPropertySpecification implements Specification<EntityViewProperty>{

	private EntityViewPropertyCondition condition;

	public EntityViewPropertySpecification(EntityViewPropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<EntityViewProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddEntityPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddAddRequiredPredicate(predicateList, root, cb);
		tryAddAddViewablePredicate(predicateList, root, cb);
		tryAddAddablePredicate(predicateList, root, cb);
		tryAddEditRequiredPredicate(predicateList, root, cb);
		tryAddEditViewablePredicate(predicateList, root, cb);
		tryAddEditablePredicate(predicateList, root, cb);
		tryAddGirdColumnPredicate(predicateList, root, cb);
		tryAddSearchConditionPredicate(predicateList, root, cb);
		tryAddSimpleSearchConditionPredicate(predicateList, root, cb);
		tryAddIdxPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_TID).as(Long.class), condition.getTid()));
        }  
	}
	private void tryAddEntityPredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
	    if (null != condition.getEntity() ) {
            predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_ENTITY).as(String.class), condition.getEntity()));
        }
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(EntityViewProperty.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(EntityViewProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAddRequiredPredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getAddRequired() ) {
			predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_ADD_REQUIRED).as(Boolean.class), condition.getAddRequired()));
		}
	}
	private void tryAddAddViewablePredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getAddViewable() ) {
			predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_ADD_VIEWABLE).as(Boolean.class), condition.getAddViewable()));
		}
	}
	private void tryAddAddablePredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getAddable() ) {
			predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_ADDABLE).as(Boolean.class), condition.getAddable()));
		}
	}
	private void tryAddEditRequiredPredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getEditRequired() ) {
			predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_EDIT_REQUIRED).as(Boolean.class), condition.getEditRequired()));
		}
	}
	private void tryAddEditViewablePredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getEditViewable() ) {
			predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_EDIT_VIEWABLE).as(Boolean.class), condition.getEditViewable()));
		}
	}
	private void tryAddEditablePredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getEditable() ) {
			predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_EDITABLE).as(Boolean.class), condition.getEditable()));
		}
	}
	private void tryAddGirdColumnPredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getGirdColumn() ) {
			predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_GIRD_COLUMN).as(Boolean.class), condition.getGirdColumn()));
		}
	}
	private void tryAddSearchConditionPredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getSearchCondition() ) {
			predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_SEARCH_CONDITION).as(Boolean.class), condition.getSearchCondition()));
		}
	}
	private void tryAddSimpleSearchConditionPredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){
		if (null != condition.getSimpleSearchCondition() ) {
			predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_SIMPLE_SEARCH_CONDITION).as(Boolean.class), condition.getSimpleSearchCondition()));
		}
	}
	private void tryAddIdxPredicate(List<Predicate> predicateList, Root<EntityViewProperty> root, CriteriaBuilder cb){

		if (null != condition.getIdx() ) {
			predicateList.add(cb.equal(root.get(EntityViewProperty.PROPERTY_IDX).as(Integer.class), condition.getIdx()));
		}

		if (null != condition.getIdxMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(EntityViewProperty.PROPERTY_IDX).as(Integer.class), condition.getIdxMax()));
		}

		if (null != condition.getIdxMin() ) {
			predicateList.add(cb.lessThan(root.get(EntityViewProperty.PROPERTY_IDX).as(Integer.class), condition.getIdxMin()));
		}
	}
}


