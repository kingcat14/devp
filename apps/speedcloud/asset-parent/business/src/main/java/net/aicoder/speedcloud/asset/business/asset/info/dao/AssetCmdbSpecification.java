package net.aicoder.speedcloud.asset.business.asset.info.dao;

import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbCondition;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetCmdb;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class AssetCmdbSpecification implements Specification<AssetCmdb>{

	private AssetCmdbCondition condition;

	public AssetCmdbSpecification(AssetCmdbCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AssetCmdb> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddBarcodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddAliasPredicate(predicateList, root, cb);
		tryAddCategoryPredicate(predicateList, root, cb);
		tryAddCategoryCodePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddTypeCodePredicate(predicateList, root, cb);
		tryAddUnitPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddCreateDatePredicate(predicateList, root, cb);
		tryAddExpireDatePredicate(predicateList, root, cb);
		tryAddAssetAreaPredicate(predicateList, root, cb);
		tryAddAssetLocationPredicate(predicateList, root, cb);
		tryAddAcquisitionModePredicate(predicateList, root, cb);
		tryAddAcquisitionDescPredicate(predicateList, root, cb);
		tryAddGoliveDatePredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(AssetCmdb.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AssetCmdb.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(AssetCmdb.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddBarcodePredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBarcode())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_BARCODE).as(String.class), "%"+condition.getBarcode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddCategoryPredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
	    if (null != condition.getCategory() ) {
            predicateList.add(cb.equal(root.get(AssetCmdb.PROPERTY_CATEGORY).as(Long.class), condition.getCategory()));
        }
	}
	private void tryAddCategoryCodePredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCategoryCode())){
			predicateList.add(cb.equal(root.get(AssetCmdb.PROPERTY_CATEGORY_CODE).as(String.class), condition.getCategoryCode()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
	    if (null != condition.getType() ) {
            predicateList.add(cb.equal(root.get(AssetCmdb.PROPERTY_TYPE).as(Long.class), condition.getType()));
        }
	}
	private void tryAddTypeCodePredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeCode())){
			predicateList.add(cb.equal(root.get(AssetCmdb.PROPERTY_TYPE_CODE).as(String.class), condition.getTypeCode()));
		}
	}
	private void tryAddUnitPredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUnit())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_UNIT).as(String.class), "%"+condition.getUnit()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddCreateDatePredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){

		if (null != condition.getCreateDate() ) {
			predicateList.add(cb.equal(root.get(AssetCmdb.PROPERTY_CREATE_DATE).as(Date.class), condition.getCreateDate()));
		}

		if (null != condition.getCreateDateStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AssetCmdb.PROPERTY_CREATE_DATE).as(Date.class), condition.getCreateDateStart()));
		}

		if (null != condition.getCreateDateEnd() ) {
			predicateList.add(cb.lessThan(root.get(AssetCmdb.PROPERTY_CREATE_DATE).as(Date.class), condition.getCreateDateEnd()));
		}
	}
	private void tryAddExpireDatePredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){

		if (null != condition.getExpireDate() ) {
			predicateList.add(cb.equal(root.get(AssetCmdb.PROPERTY_EXPIRE_DATE).as(Date.class), condition.getExpireDate()));
		}

		if (null != condition.getExpireDateStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AssetCmdb.PROPERTY_EXPIRE_DATE).as(Date.class), condition.getExpireDateStart()));
		}

		if (null != condition.getExpireDateEnd() ) {
			predicateList.add(cb.lessThan(root.get(AssetCmdb.PROPERTY_EXPIRE_DATE).as(Date.class), condition.getExpireDateEnd()));
		}
	}
	private void tryAddAssetAreaPredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetArea())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_ASSET_AREA).as(String.class), "%"+condition.getAssetArea()+"%"));
		}
	}
	private void tryAddAssetLocationPredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetLocation())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_ASSET_LOCATION).as(String.class), "%"+condition.getAssetLocation()+"%"));
		}
	}
	private void tryAddAcquisitionModePredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAcquisitionMode())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_ACQUISITION_MODE).as(String.class), "%"+condition.getAcquisitionMode()+"%"));
		}
	}
	private void tryAddAcquisitionDescPredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAcquisitionDesc())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_ACQUISITION_DESC).as(String.class), "%"+condition.getAcquisitionDesc()+"%"));
		}
	}
	private void tryAddGoliveDatePredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){

		if (null != condition.getGoliveDate() ) {
			predicateList.add(cb.equal(root.get(AssetCmdb.PROPERTY_GOLIVE_DATE).as(Date.class), condition.getGoliveDate()));
		}

		if (null != condition.getGoliveDateStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AssetCmdb.PROPERTY_GOLIVE_DATE).as(Date.class), condition.getGoliveDateStart()));
		}

		if (null != condition.getGoliveDateEnd() ) {
			predicateList.add(cb.lessThan(root.get(AssetCmdb.PROPERTY_GOLIVE_DATE).as(Date.class), condition.getGoliveDateEnd()));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<AssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(AssetCmdb.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
}


