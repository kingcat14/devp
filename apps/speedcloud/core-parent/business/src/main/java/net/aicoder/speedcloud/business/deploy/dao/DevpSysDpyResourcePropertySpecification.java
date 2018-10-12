package net.aicoder.speedcloud.business.deploy.dao;

import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcePropertyCondition;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysDpyResourcePropertySpecification implements Specification<DevpSysDpyResourceProperty>{

	private DevpSysDpyResourcePropertyCondition condition;

	public DevpSysDpyResourcePropertySpecification(DevpSysDpyResourcePropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysDpyResourceProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddResourcePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddValuePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysDpyResourceProperty> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyResourceProperty.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysDpyResourceProperty.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysDpyResourceProperty.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddResourcePredicate(List<Predicate> predicateList, Root<DevpSysDpyResourceProperty> root, CriteriaBuilder cb){
	    if (null != condition.getResource() ) {
            predicateList.add(cb.equal(root.get(DevpSysDpyResourceProperty.PROPERTY_RESOURCE).as(Long.class), condition.getResource()));
        }
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysDpyResourceProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysDpyResourceProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysDpyResourceProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysDpyResourceProperty.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddValuePredicate(List<Predicate> predicateList, Root<DevpSysDpyResourceProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getValue())){
			predicateList.add(cb.like(root.get(DevpSysDpyResourceProperty.PROPERTY_VALUE).as(String.class), "%"+condition.getValue()+"%"));
		}
	}
}


