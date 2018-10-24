package net.aicoder.speedcloud.asset.business.asset.config.dao;

import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyCondition;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetTypeProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class AssetTypePropertySpecification implements Specification<AssetTypeProperty>{

	private AssetTypePropertyCondition condition;

	public AssetTypePropertySpecification(AssetTypePropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AssetTypeProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddAssetTypePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddRequiredPredicate(predicateList, root, cb);
		tryAddOptionValuesPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<AssetTypeProperty> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(AssetTypeProperty.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AssetTypeProperty.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(AssetTypeProperty.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddAssetTypePredicate(List<Predicate> predicateList, Root<AssetTypeProperty> root, CriteriaBuilder cb){
	    if (null != condition.getAssetType() ) {
            predicateList.add(cb.equal(root.get(AssetTypeProperty.PROPERTY_ASSET_TYPE).as(Long.class), condition.getAssetType()));
        }
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<AssetTypeProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(AssetTypeProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<AssetTypeProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(AssetTypeProperty.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<AssetTypeProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(AssetTypeProperty.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddRequiredPredicate(List<Predicate> predicateList, Root<AssetTypeProperty> root, CriteriaBuilder cb){
		if (null != condition.getRequired() ) {
			predicateList.add(cb.equal(root.get(AssetTypeProperty.PROPERTY_REQUIRED).as(Boolean.class), condition.getRequired()));
		}
	}
	private void tryAddOptionValuesPredicate(List<Predicate> predicateList, Root<AssetTypeProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getOptionValues())){
			predicateList.add(cb.like(root.get(AssetTypeProperty.PROPERTY_OPTION_VALUES).as(String.class), "%"+condition.getOptionValues()+"%"));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<AssetTypeProperty> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(AssetTypeProperty.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AssetTypeProperty.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(AssetTypeProperty.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
}


