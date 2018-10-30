package net.aicoder.speedcloud.business.deployscheme.dao;

import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationCondition;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceInstanceRelation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ResourceInstanceRelationSpecification implements Specification<ResourceInstanceRelation>{

	private ResourceInstanceRelationCondition condition;

	public ResourceInstanceRelationSpecification(ResourceInstanceRelationCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ResourceInstanceRelation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddProjectPredicate(predicateList, root, cb);
		tryAddSchemePredicate(predicateList, root, cb);
		tryAddResourcePredicate(predicateList, root, cb);
		tryAddAssetPredicate(predicateList, root, cb);
		tryAddAssetCategoryCodePredicate(predicateList, root, cb);
		tryAddAssetTypeCodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<ResourceInstanceRelation> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(ResourceInstanceRelation.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ResourceInstanceRelation.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(ResourceInstanceRelation.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<ResourceInstanceRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(ResourceInstanceRelation.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<ResourceInstanceRelation> root, CriteriaBuilder cb){
		if (null != condition.getStatus() ) {
			predicateList.add(cb.equal(root.get(ResourceInstanceRelation.PROPERTY_STATUS).as(Boolean.class), condition.getStatus()));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<ResourceInstanceRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(ResourceInstanceRelation.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<ResourceInstanceRelation> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(ResourceInstanceRelation.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ResourceInstanceRelation.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(ResourceInstanceRelation.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddProjectPredicate(List<Predicate> predicateList, Root<ResourceInstanceRelation> root, CriteriaBuilder cb){

		if (null != condition.getProject() ) {
			predicateList.add(cb.equal(root.get(ResourceInstanceRelation.PROPERTY_PROJECT).as(Long.class), condition.getProject()));
		}

		if (null != condition.getProjectMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ResourceInstanceRelation.PROPERTY_PROJECT).as(Long.class), condition.getProjectMax()));
		}

		if (null != condition.getProjectMin() ) {
			predicateList.add(cb.lessThan(root.get(ResourceInstanceRelation.PROPERTY_PROJECT).as(Long.class), condition.getProjectMin()));
		}
	}
	private void tryAddSchemePredicate(List<Predicate> predicateList, Root<ResourceInstanceRelation> root, CriteriaBuilder cb){
	    if (null != condition.getScheme() ) {
            predicateList.add(cb.equal(root.get(ResourceInstanceRelation.PROPERTY_SCHEME).as(Long.class), condition.getScheme()));
        }
	}
	private void tryAddResourcePredicate(List<Predicate> predicateList, Root<ResourceInstanceRelation> root, CriteriaBuilder cb){
	    if (null != condition.getResource() ) {
            predicateList.add(cb.equal(root.get(ResourceInstanceRelation.PROPERTY_RESOURCE).as(Long.class), condition.getResource()));
        }
	}
	private void tryAddAssetPredicate(List<Predicate> predicateList, Root<ResourceInstanceRelation> root, CriteriaBuilder cb){

		if (null != condition.getAsset() ) {
			predicateList.add(cb.equal(root.get(ResourceInstanceRelation.PROPERTY_ASSET).as(Long.class), condition.getAsset()));
		}

		if (null != condition.getAssetMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ResourceInstanceRelation.PROPERTY_ASSET).as(Long.class), condition.getAssetMax()));
		}

		if (null != condition.getAssetMin() ) {
			predicateList.add(cb.lessThan(root.get(ResourceInstanceRelation.PROPERTY_ASSET).as(Long.class), condition.getAssetMin()));
		}
	}
	private void tryAddAssetCategoryCodePredicate(List<Predicate> predicateList, Root<ResourceInstanceRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetCategoryCode())){
			predicateList.add(cb.like(root.get(ResourceInstanceRelation.PROPERTY_ASSET_CATEGORY_CODE).as(String.class), "%"+condition.getAssetCategoryCode()+"%"));
		}
	}
	private void tryAddAssetTypeCodePredicate(List<Predicate> predicateList, Root<ResourceInstanceRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetTypeCode())){
			predicateList.add(cb.like(root.get(ResourceInstanceRelation.PROPERTY_ASSET_TYPE_CODE).as(String.class), "%"+condition.getAssetTypeCode()+"%"));
		}
	}
}


