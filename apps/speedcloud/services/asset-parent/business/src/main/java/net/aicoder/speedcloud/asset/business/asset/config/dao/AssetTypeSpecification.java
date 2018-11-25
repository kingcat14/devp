package net.aicoder.speedcloud.asset.business.asset.config.dao;

import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetType;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AssetTypeSpecification implements Specification<AssetType>{

	private AssetTypeCondition condition;

	public AssetTypeSpecification(AssetTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AssetType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNumPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddUseMonthPredicate(predicateList, root, cb);
		tryAddViewIndexPredicate(predicateList, root, cb);
		tryAddParentCodePredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddAssetCategoryCodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<AssetType> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(AssetType.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddNumPredicate(List<Predicate> predicateList, Root<AssetType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNum())){
			predicateList.add(cb.like(root.get(AssetType.PROPERTY_NUM).as(String.class), "%"+condition.getNum()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<AssetType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(AssetType.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<AssetType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(AssetType.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddUseMonthPredicate(List<Predicate> predicateList, Root<AssetType> root, CriteriaBuilder cb){

		if (null != condition.getUseMonth() ) {
			predicateList.add(cb.equal(root.get(AssetType.PROPERTY_USE_MONTH).as(Integer.class), condition.getUseMonth()));
		}

		if (null != condition.getUseMonthMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AssetType.PROPERTY_USE_MONTH).as(Integer.class), condition.getUseMonthMax()));
		}

		if (null != condition.getUseMonthMin() ) {
			predicateList.add(cb.lessThan(root.get(AssetType.PROPERTY_USE_MONTH).as(Integer.class), condition.getUseMonthMin()));
		}
	}
	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<AssetType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getViewIndex())){
			predicateList.add(cb.like(root.get(AssetType.PROPERTY_VIEW_INDEX).as(String.class), "%"+condition.getViewIndex()+"%"));
		}
	}
	private void tryAddParentCodePredicate(List<Predicate> predicateList, Root<AssetType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParentCode())){
			predicateList.add(cb.like(root.get(AssetType.PROPERTY_PARENT_CODE).as(String.class), "%"+condition.getParentCode()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<AssetType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(AssetType.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddAssetCategoryCodePredicate(List<Predicate> predicateList, Root<AssetType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetCategoryCode())){
			predicateList.add(cb.like(root.get(AssetType.PROPERTY_ASSET_CATEGORY_CODE).as(String.class), "%"+condition.getAssetCategoryCode()+"%"));
		}
	}
}


