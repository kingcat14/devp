package net.aicoder.speedcloud.business.deployscheme.dao;

import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeCondition;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceRelationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ResourceRelationTypeSpecification implements Specification<ResourceRelationType>{

	private ResourceRelationTypeCondition condition;

	public ResourceRelationTypeSpecification(ResourceRelationTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ResourceRelationType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddIconPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<ResourceRelationType> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(ResourceRelationType.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ResourceRelationType.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(ResourceRelationType.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<ResourceRelationType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(ResourceRelationType.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<ResourceRelationType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(ResourceRelationType.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddIconPredicate(List<Predicate> predicateList, Root<ResourceRelationType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getIcon())){
			predicateList.add(cb.like(root.get(ResourceRelationType.PROPERTY_ICON).as(String.class), "%"+condition.getIcon()+"%"));
		}
	}
}


