package net.aicoder.speedcloud.asset.business.asset.info.dao;

import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetOwnership;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssetOwnershipSpecification implements Specification<AssetOwnership>{

	private AssetOwnershipCondition condition;

	public AssetOwnershipSpecification(AssetOwnershipCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AssetOwnership> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddCategoryPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddTypeCodePredicate(predicateList, root, cb);
		tryAddTypeNamePredicate(predicateList, root, cb);
		tryAddAssetDeptPredicate(predicateList, root, cb);
		tryAddAssetManagerPredicate(predicateList, root, cb);
		tryAddUseDeptPredicate(predicateList, root, cb);
		tryAddUseManagerPredicate(predicateList, root, cb);
		tryAddOpsDeptPredicate(predicateList, root, cb);
		tryAddOpsManagerPredicate(predicateList, root, cb);
		tryAddBizLinePredicate(predicateList, root, cb);
		tryAddBizManagerPredicate(predicateList, root, cb);
		tryAddGoliveDatePredicate(predicateList, root, cb);
		tryAddMajorCustPredicate(predicateList, root, cb);
		tryAddCustManagerPredicate(predicateList, root, cb);
		tryAddCustUsagePredicate(predicateList, root, cb);
		tryAddAcquisitionProviderPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(AssetOwnership.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AssetOwnership.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(AssetOwnership.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddCategoryPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
	    if (null != condition.getCategory() ) {
            predicateList.add(cb.equal(root.get(AssetOwnership.PROPERTY_CATEGORY).as(Long.class), condition.getCategory()));
        }
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
	    if (null != condition.getType() ) {
            predicateList.add(cb.equal(root.get(AssetOwnership.PROPERTY_TYPE).as(Long.class), condition.getType()));
        }
	}
	private void tryAddTypeCodePredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeCode())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_TYPE_CODE).as(String.class), "%"+condition.getTypeCode()+"%"));
		}
	}
	private void tryAddTypeNamePredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeName())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_TYPE_NAME).as(String.class), "%"+condition.getTypeName()+"%"));
		}
	}
	private void tryAddAssetDeptPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetDept())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_ASSET_DEPT).as(String.class), "%"+condition.getAssetDept()+"%"));
		}
	}
	private void tryAddAssetManagerPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetManager())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_ASSET_MANAGER).as(String.class), "%"+condition.getAssetManager()+"%"));
		}
	}
	private void tryAddUseDeptPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUseDept())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_USE_DEPT).as(String.class), "%"+condition.getUseDept()+"%"));
		}
	}
	private void tryAddUseManagerPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUseManager())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_USE_MANAGER).as(String.class), "%"+condition.getUseManager()+"%"));
		}
	}
	private void tryAddOpsDeptPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getOpsDept())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_OPS_DEPT).as(String.class), "%"+condition.getOpsDept()+"%"));
		}
	}
	private void tryAddOpsManagerPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getOpsManager())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_OPS_MANAGER).as(String.class), "%"+condition.getOpsManager()+"%"));
		}
	}
	private void tryAddBizLinePredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBizLine())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_BIZ_LINE).as(String.class), "%"+condition.getBizLine()+"%"));
		}
	}
	private void tryAddBizManagerPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBizManager())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_BIZ_MANAGER).as(String.class), "%"+condition.getBizManager()+"%"));
		}
	}
	private void tryAddGoliveDatePredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){

		if (null != condition.getGoliveDate() ) {
			predicateList.add(cb.equal(root.get(AssetOwnership.PROPERTY_GOLIVE_DATE).as(Date.class), condition.getGoliveDate()));
		}

		if (null != condition.getGoliveDateStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AssetOwnership.PROPERTY_GOLIVE_DATE).as(Date.class), condition.getGoliveDateStart()));
		}

		if (null != condition.getGoliveDateEnd() ) {
			predicateList.add(cb.lessThan(root.get(AssetOwnership.PROPERTY_GOLIVE_DATE).as(Date.class), condition.getGoliveDateEnd()));
		}
	}
	private void tryAddMajorCustPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMajorCust())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_MAJOR_CUST).as(String.class), "%"+condition.getMajorCust()+"%"));
		}
	}
	private void tryAddCustManagerPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCustManager())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_CUST_MANAGER).as(String.class), "%"+condition.getCustManager()+"%"));
		}
	}
	private void tryAddCustUsagePredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCustUsage())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_CUST_USAGE).as(String.class), "%"+condition.getCustUsage()+"%"));
		}
	}
	private void tryAddAcquisitionProviderPredicate(List<Predicate> predicateList, Root<AssetOwnership> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAcquisitionProvider())){
			predicateList.add(cb.like(root.get(AssetOwnership.PROPERTY_ACQUISITION_PROVIDER).as(String.class), "%"+condition.getAcquisitionProvider()+"%"));
		}
	}
}


