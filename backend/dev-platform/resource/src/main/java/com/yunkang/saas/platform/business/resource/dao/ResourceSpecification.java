package com.yunkang.saas.platform.business.resource.dao;


import com.yunkang.saas.platform.business.resource.domain.Resource;
import com.yunkang.saas.platform.business.resource.dto.ResourceCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class ResourceSpecification implements Specification<Resource>{

	ResourceCondition condition;

	public ResourceSpecification(ResourceCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddAppIdPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddUrlPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddParentIdPredicate(predicateList, root, cb);
		tryAddOrderIndexPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddAppIdPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(null != (condition.getAppId())){
			predicateList.add(cb.equal(root.get(Resource.PROPERTY_APP_ID).as(Long.class), condition.getName()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddUrlPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUrl())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_URL).as(String.class), "%"+condition.getUrl()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddParentIdPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if (null != condition.getParentId() ) {
			predicateList.add(cb.equal(root.get(Resource.PROPERTY_PARENT_ID).as(Long.class), condition.getParentId()));
		}
	}
	private void tryAddOrderIndexPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if (null != condition.getOrderIndex() ) {
			predicateList.add(cb.equal(root.get(Resource.PROPERTY_ORDER_INDEX).as(Integer.class), condition.getOrderIndex()));
		}
	}
}


