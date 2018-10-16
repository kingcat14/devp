package net.aicoder.speedcloud.business.deployscheme.dao;

import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyCondition;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ResourcePropertySpecification implements Specification<ResourceProperty>{

	private ResourcePropertyCondition condition;

	public ResourcePropertySpecification(ResourcePropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ResourceProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<ResourceProperty> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(ResourceProperty.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ResourceProperty.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(ResourceProperty.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddResourcePredicate(List<Predicate> predicateList, Root<ResourceProperty> root, CriteriaBuilder cb){
	    if (null != condition.getResource() ) {
            predicateList.add(cb.equal(root.get(ResourceProperty.PROPERTY_RESOURCE).as(Long.class), condition.getResource()));
        }
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<ResourceProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(ResourceProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<ResourceProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(ResourceProperty.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddValuePredicate(List<Predicate> predicateList, Root<ResourceProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getValue())){
			predicateList.add(cb.like(root.get(ResourceProperty.PROPERTY_VALUE).as(String.class), "%"+condition.getValue()+"%"));
		}
	}
}


