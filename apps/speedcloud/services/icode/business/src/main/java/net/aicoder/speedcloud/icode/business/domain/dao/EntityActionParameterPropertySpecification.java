package net.aicoder.speedcloud.icode.business.domain.dao;

import net.aicoder.speedcloud.icode.business.domain.domain.EntityActionParameterProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EntityActionParameterPropertySpecification implements Specification<EntityActionParameterProperty>{

	private EntityActionParameterPropertyCondition condition;

	public EntityActionParameterPropertySpecification(EntityActionParameterPropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<EntityActionParameterProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddActionParameterPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddWrapperTypePredicate(predicateList, root, cb);
		tryAddIdxPredicate(predicateList, root, cb);
		tryAddMemoPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<EntityActionParameterProperty> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(EntityActionParameterProperty.PROPERTY_TID).as(Long.class), condition.getTid()));
        }
	}
	private void tryAddActionParameterPredicate(List<Predicate> predicateList, Root<EntityActionParameterProperty> root, CriteriaBuilder cb){
	    if (null != condition.getActionParameter() ) {
            predicateList.add(cb.equal(root.get(EntityActionParameterProperty.PROPERTY_ACTION_PARAMETER).as(String.class), condition.getActionParameter()));
        }  
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<EntityActionParameterProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(EntityActionParameterProperty.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<EntityActionParameterProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(EntityActionParameterProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<EntityActionParameterProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(EntityActionParameterProperty.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddWrapperTypePredicate(List<Predicate> predicateList, Root<EntityActionParameterProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getWrapperType())){
			predicateList.add(cb.like(root.get(EntityActionParameterProperty.PROPERTY_WRAPPER_TYPE).as(String.class), "%"+condition.getWrapperType()+"%"));
		}
	}
	private void tryAddIdxPredicate(List<Predicate> predicateList, Root<EntityActionParameterProperty> root, CriteriaBuilder cb){

		if (null != condition.getIdx() ) {
			predicateList.add(cb.equal(root.get(EntityActionParameterProperty.PROPERTY_IDX).as(Integer.class), condition.getIdx()));
		}

		if (null != condition.getIdxMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(EntityActionParameterProperty.PROPERTY_IDX).as(Integer.class), condition.getIdxMax()));
		}

		if (null != condition.getIdxMin() ) {
			predicateList.add(cb.lessThan(root.get(EntityActionParameterProperty.PROPERTY_IDX).as(Integer.class), condition.getIdxMin()));
		}
	}
	private void tryAddMemoPredicate(List<Predicate> predicateList, Root<EntityActionParameterProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMemo())){
			predicateList.add(cb.like(root.get(EntityActionParameterProperty.PROPERTY_MEMO).as(String.class), "%"+condition.getMemo()+"%"));
		}
	}
}


