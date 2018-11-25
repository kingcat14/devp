package net.aicoder.maintenance.business.asset.info.dao;

import net.aicoder.maintenance.business.asset.info.domain.AssetCategory;
import net.aicoder.maintenance.business.asset.info.dto.AssetCategoryCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AssetCategorySpecification implements Specification<AssetCategory>{

	private AssetCategoryCondition condition;

	public AssetCategorySpecification(AssetCategoryCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AssetCategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNumPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddViewIndexPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<AssetCategory> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(AssetCategory.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddNumPredicate(List<Predicate> predicateList, Root<AssetCategory> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNum())){
			predicateList.add(cb.like(root.get(AssetCategory.PROPERTY_NUM).as(String.class), "%"+condition.getNum()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<AssetCategory> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(AssetCategory.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<AssetCategory> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(AssetCategory.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<AssetCategory> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getViewIndex())){
			predicateList.add(cb.like(root.get(AssetCategory.PROPERTY_VIEW_INDEX).as(String.class), "%"+condition.getViewIndex()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<AssetCategory> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(AssetCategory.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
}


