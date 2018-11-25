package net.aicoder.speedcloud.asset.business.asset.info.dao;

import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetProperty;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AssetPropertySpecification implements Specification<AssetProperty>{

	private AssetPropertyCondition condition;

	public AssetPropertySpecification(AssetPropertyCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AssetProperty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddValuePredicate(predicateList, root, cb);
		tryAddAssetPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<AssetProperty> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(AssetProperty.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<AssetProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(AssetProperty.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddValuePredicate(List<Predicate> predicateList, Root<AssetProperty> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getValue())){
			predicateList.add(cb.like(root.get(AssetProperty.PROPERTY_VALUE).as(String.class), "%"+condition.getValue()+"%"));
		}
	}
	private void tryAddAssetPredicate(List<Predicate> predicateList, Root<AssetProperty> root, CriteriaBuilder cb){
	    if (null != condition.getAsset() ) {
            predicateList.add(cb.equal(root.get(AssetProperty.PROPERTY_ASSET).as(Long.class), condition.getAsset()));
        }
	}
}


