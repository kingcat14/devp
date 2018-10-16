package net.aicoder.speedcloud.business.deployscheme.dao;

import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationCondition;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceRelation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ResourceRelationSpecification implements Specification<ResourceRelation>{

	private ResourceRelationCondition condition;

	public ResourceRelationSpecification(ResourceRelationCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ResourceRelation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddResourcePredicate(predicateList, root, cb);
		tryAddRefResourcePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddAliasPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddSchemePredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddDirectionPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<ResourceRelation> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(ResourceRelation.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ResourceRelation.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(ResourceRelation.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddResourcePredicate(List<Predicate> predicateList, Root<ResourceRelation> root, CriteriaBuilder cb){
	    if (null != condition.getResource() ) {
            predicateList.add(cb.equal(root.get(ResourceRelation.PROPERTY_RESOURCE).as(Long.class), condition.getResource()));
        }
	}
	private void tryAddRefResourcePredicate(List<Predicate> predicateList, Root<ResourceRelation> root, CriteriaBuilder cb){
	    if (null != condition.getRefResource() ) {
            predicateList.add(cb.equal(root.get(ResourceRelation.PROPERTY_REF_RESOURCE).as(Long.class), condition.getRefResource()));
        }
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<ResourceRelation> root, CriteriaBuilder cb){
	    if (null != condition.getType() ) {
            predicateList.add(cb.equal(root.get(ResourceRelation.PROPERTY_TYPE).as(Long.class), condition.getType()));
        }
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<ResourceRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(ResourceRelation.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<ResourceRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(ResourceRelation.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<ResourceRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(ResourceRelation.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<ResourceRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(ResourceRelation.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddSchemePredicate(List<Predicate> predicateList, Root<ResourceRelation> root, CriteriaBuilder cb){
	    if (null != condition.getScheme() ) {
            predicateList.add(cb.equal(root.get(ResourceRelation.PROPERTY_SCHEME).as(Long.class), condition.getScheme()));
        }
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<ResourceRelation> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(ResourceRelation.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ResourceRelation.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(ResourceRelation.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddDirectionPredicate(List<Predicate> predicateList, Root<ResourceRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDirection())){
			predicateList.add(cb.like(root.get(ResourceRelation.PROPERTY_DIRECTION).as(String.class), "%"+condition.getDirection()+"%"));
		}
	}
}


