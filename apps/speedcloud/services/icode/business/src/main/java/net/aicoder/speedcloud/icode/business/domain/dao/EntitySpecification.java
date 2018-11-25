package net.aicoder.speedcloud.icode.business.domain.dao;

import net.aicoder.speedcloud.icode.business.domain.domain.Entity;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EntitySpecification implements Specification<Entity>{

	private EntityCondition condition;

	public EntitySpecification(EntityCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddViewIndexPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddParentEntityPredicate(predicateList, root, cb);
		tryAddAggregatePredicate(predicateList, root, cb);
		tryAddDomainPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<Entity> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(Entity.PROPERTY_TID).as(Long.class), condition.getTid()));
        }
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Entity> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Entity.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Entity> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Entity.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<Entity> root, CriteriaBuilder cb){

		if (null != condition.getViewIndex() ) {
			predicateList.add(cb.equal(root.get(Entity.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndex()));
		}

		if (null != condition.getViewIndexMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Entity.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndexMax()));
		}

		if (null != condition.getViewIndexMin() ) {
			predicateList.add(cb.lessThan(root.get(Entity.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndexMin()));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<Entity> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(Entity.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddParentEntityPredicate(List<Predicate> predicateList, Root<Entity> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParentEntity())){
			predicateList.add(cb.like(root.get(Entity.PROPERTY_PARENT_ENTITY).as(String.class), "%"+condition.getParentEntity()+"%"));
		}
	}
	private void tryAddAggregatePredicate(List<Predicate> predicateList, Root<Entity> root, CriteriaBuilder cb){
	    if (null != condition.getAggregate() ) {
            predicateList.add(cb.equal(root.get(Entity.PROPERTY_AGGREGATE).as(String.class), condition.getAggregate()));
        }  
	}
	private void tryAddDomainPredicate(List<Predicate> predicateList, Root<Entity> root, CriteriaBuilder cb){
	    if (null != condition.getDomain() ) {
            predicateList.add(cb.equal(root.get(Entity.PROPERTY_DOMAIN).as(String.class), condition.getDomain()));
        }  
	}
}


