package net.aicoder.devp.deploy.business.deploy.dao;

import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstCondition;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyResInst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysDpyResInstSpecification implements Specification<DevpSysDpyResInst>{

	private DevpSysDpyResInstCondition condition;

	public DevpSysDpyResInstSpecification(DevpSysDpyResInstCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysDpyResInst> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddEtypePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddAliasPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddRecordStatePredicate(predicateList, root, cb);
		tryAddFlagPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddSubTypePredicate(predicateList, root, cb);
		tryAddDpyModelPredicate(predicateList, root, cb);
		tryAddDpyDescriptionPredicate(predicateList, root, cb);
		tryAddAccessAddrPredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddSchemeRidPredicate(predicateList, root, cb);
		tryAddResRidPredicate(predicateList, root, cb);
		tryAddParentRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddAssetRidPredicate(predicateList, root, cb);
		tryAddAssetEtypePredicate(predicateList, root, cb);
		tryAddAssetTypeCodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyResInst.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyResInst.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddFlagPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFlag())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_FLAG).as(String.class), "%"+condition.getFlag()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddSubTypePredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubType())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_SUB_TYPE).as(String.class), "%"+condition.getSubType()+"%"));
		}
	}
	private void tryAddDpyModelPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDpyModel())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_DPY_MODEL).as(String.class), "%"+condition.getDpyModel()+"%"));
		}
	}
	private void tryAddDpyDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDpyDescription())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_DPY_DESCRIPTION).as(String.class), "%"+condition.getDpyDescription()+"%"));
		}
	}
	private void tryAddAccessAddrPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAccessAddr())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_ACCESS_ADDR).as(String.class), "%"+condition.getAccessAddr()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyResInst.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}
	}
	private void tryAddSchemeRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if (null != condition.getSchemeRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyResInst.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRid()));
		}
	}
	private void tryAddResRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if (null != condition.getResRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyResInst.PROPERTY_RES_RID).as(Long.class), condition.getResRid()));
		}
	}
	private void tryAddParentRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if (null != condition.getParentRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyResInst.PROPERTY_PARENT_RID).as(Long.class), condition.getParentRid()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyResInst.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddAssetRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if (null != condition.getAssetRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyResInst.PROPERTY_ASSET_RID).as(Long.class), condition.getAssetRid()));
		}
	}
	private void tryAddAssetEtypePredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetEtype())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_ASSET_ETYPE).as(String.class), "%"+condition.getAssetEtype()+"%"));
		}
	}
	private void tryAddAssetTypeCodePredicate(List<Predicate> predicateList, Root<DevpSysDpyResInst> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetTypeCode())){
			predicateList.add(cb.like(root.get(DevpSysDpyResInst.PROPERTY_ASSET_TYPE_CODE).as(String.class), "%"+condition.getAssetTypeCode()+"%"));
		}
	}
}


