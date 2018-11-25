package net.aicoder.speedcloud.icode.business.domain.dao;

import net.aicoder.speedcloud.icode.business.domain.domain.EntityAction;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EntityActionSpecification implements Specification<EntityAction>{

	private EntityActionCondition condition;

	public EntityActionSpecification(EntityActionCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<EntityAction> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddMemoPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddRequestPredicate(predicateList, root, cb);
		tryAddResponsePredicate(predicateList, root, cb);
		tryAddEntityPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<EntityAction> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(EntityAction.PROPERTY_TID).as(Long.class), condition.getTid()));
        }
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<EntityAction> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(EntityAction.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<EntityAction> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(EntityAction.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddMemoPredicate(List<Predicate> predicateList, Root<EntityAction> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMemo())){
			predicateList.add(cb.like(root.get(EntityAction.PROPERTY_MEMO).as(String.class), "%"+condition.getMemo()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<EntityAction> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(EntityAction.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRequestPredicate(List<Predicate> predicateList, Root<EntityAction> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRequest())){
			predicateList.add(cb.like(root.get(EntityAction.PROPERTY_REQUEST).as(String.class), "%"+condition.getRequest()+"%"));
		}
	}
	private void tryAddResponsePredicate(List<Predicate> predicateList, Root<EntityAction> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getResponse())){
			predicateList.add(cb.like(root.get(EntityAction.PROPERTY_RESPONSE).as(String.class), "%"+condition.getResponse()+"%"));
		}
	}
	private void tryAddEntityPredicate(List<Predicate> predicateList, Root<EntityAction> root, CriteriaBuilder cb){
	    if (null != condition.getEntity() ) {
            predicateList.add(cb.equal(root.get(EntityAction.PROPERTY_ENTITY).as(String.class), condition.getEntity()));
        }  
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<EntityAction> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(EntityAction.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
}


