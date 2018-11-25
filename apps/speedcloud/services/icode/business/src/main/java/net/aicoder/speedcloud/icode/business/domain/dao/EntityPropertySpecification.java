package net.aicoder.speedcloud.icode.business.domain.dao;

import net.aicoder.speedcloud.icode.business.domain.domain.EntityProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EntityPropertySpecification implements Specification<EntityProperty>{

	private EntityPropertyCondition condition;

	public EntityPropertySpecification(EntityPropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<EntityProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddEntityPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddRelatedEntityIdPredicate(predicateList, root, cb);
		tryAddRelatedEntityPropertyCodePredicate(predicateList, root, cb);
		tryAddIdxPredicate(predicateList, root, cb);
		tryAddMemoPredicate(predicateList, root, cb);
		tryAddPrimaryKeyPredicate(predicateList, root, cb);
		tryAddSearchPredicate(predicateList, root, cb);
		tryAddEditablePredicate(predicateList, root, cb);
		tryAddNullablePredicate(predicateList, root, cb);
		tryAddVisiblePredicate(predicateList, root, cb);
		tryAddPersistPredicate(predicateList, root, cb);
		tryAddSortablePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(EntityProperty.PROPERTY_TID).as(Long.class), condition.getTid()));
        }
	}
	private void tryAddEntityPredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
	    if (null != condition.getEntity() ) {
            predicateList.add(cb.equal(root.get(EntityProperty.PROPERTY_ENTITY).as(String.class), condition.getEntity()));
        }  
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(EntityProperty.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(EntityProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(EntityProperty.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddRelatedEntityIdPredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRelatedEntityId())){
			predicateList.add(cb.like(root.get(EntityProperty.PROPERTY_RELATED_ENTITY_ID).as(String.class), "%"+condition.getRelatedEntityId()+"%"));
		}
	}
	private void tryAddRelatedEntityPropertyCodePredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRelatedEntityPropertyCode())){
			predicateList.add(cb.like(root.get(EntityProperty.PROPERTY_RELATED_ENTITY_PROPERTY_CODE).as(String.class), "%"+condition.getRelatedEntityPropertyCode()+"%"));
		}
	}
	private void tryAddIdxPredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){

		if (null != condition.getIdx() ) {
			predicateList.add(cb.equal(root.get(EntityProperty.PROPERTY_IDX).as(Integer.class), condition.getIdx()));
		}

		if (null != condition.getIdxMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(EntityProperty.PROPERTY_IDX).as(Integer.class), condition.getIdxMax()));
		}

		if (null != condition.getIdxMin() ) {
			predicateList.add(cb.lessThan(root.get(EntityProperty.PROPERTY_IDX).as(Integer.class), condition.getIdxMin()));
		}
	}
	private void tryAddMemoPredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMemo())){
			predicateList.add(cb.like(root.get(EntityProperty.PROPERTY_MEMO).as(String.class), "%"+condition.getMemo()+"%"));
		}
	}
	private void tryAddPrimaryKeyPredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if (null != condition.getPrimaryKey() ) {
			predicateList.add(cb.equal(root.get(EntityProperty.PROPERTY_PRIMARY_KEY).as(Boolean.class), condition.getPrimaryKey()));
		}
	}
	private void tryAddSearchPredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if (null != condition.getSearch() ) {
			predicateList.add(cb.equal(root.get(EntityProperty.PROPERTY_SEARCH).as(Boolean.class), condition.getSearch()));
		}
	}
	private void tryAddEditablePredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if (null != condition.getEditable() ) {
			predicateList.add(cb.equal(root.get(EntityProperty.PROPERTY_EDITABLE).as(Boolean.class), condition.getEditable()));
		}
	}
	private void tryAddNullablePredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if (null != condition.getNullable() ) {
			predicateList.add(cb.equal(root.get(EntityProperty.PROPERTY_NULLABLE).as(Boolean.class), condition.getNullable()));
		}
	}
	private void tryAddVisiblePredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if (null != condition.getVisible() ) {
			predicateList.add(cb.equal(root.get(EntityProperty.PROPERTY_VISIBLE).as(Boolean.class), condition.getVisible()));
		}
	}
	private void tryAddPersistPredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if (null != condition.getPersist() ) {
			predicateList.add(cb.equal(root.get(EntityProperty.PROPERTY_PERSIST).as(Boolean.class), condition.getPersist()));
		}
	}
	private void tryAddSortablePredicate(List<Predicate> predicateList, Root<EntityProperty> root, CriteriaBuilder cb){
		if (null != condition.getSortable() ) {
			predicateList.add(cb.equal(root.get(EntityProperty.PROPERTY_SORTABLE).as(Boolean.class), condition.getSortable()));
		}
	}
}


