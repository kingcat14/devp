package net.aicoder.devp.product.business.sys.dao;

import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoCondition;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmInstInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysElmInstInfoSpecification implements Specification<DevpSysElmInstInfo>{

	DevpSysElmInstInfoCondition condition;

	public DevpSysElmInstInfoSpecification(DevpSysElmInstInfoCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysElmInstInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddAliasPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddContRidPredicate(predicateList, root, cb);
		tryAddSprdRidPredicate(predicateList, root, cb);
		tryAddSelmRidPredicate(predicateList, root, cb);
		tryAddDprdRidPredicate(predicateList, root, cb);
		tryAddDelmRidPredicate(predicateList, root, cb);
		tryAddSinstRidPredicate(predicateList, root, cb);
		tryAddDinstRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddInfoValue1Predicate(predicateList, root, cb);
		tryAddInfoValue2Predicate(predicateList, root, cb);
		tryAddInfoValue3Predicate(predicateList, root, cb);
		tryAddInfoValue4Predicate(predicateList, root, cb);
		tryAddInfoValue5Predicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddRecordStatePredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmInstInfo.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmInstInfo.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}
	}
	private void tryAddContRidPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if (null != condition.getContRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmInstInfo.PROPERTY_CONT_RID).as(Long.class), condition.getContRid()));
		}
	}
	private void tryAddSprdRidPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if (null != condition.getSprdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmInstInfo.PROPERTY_SPRD_RID).as(Long.class), condition.getSprdRid()));
		}
	}
	private void tryAddSelmRidPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if (null != condition.getSelmRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmInstInfo.PROPERTY_SELM_RID).as(Long.class), condition.getSelmRid()));
		}
	}
	private void tryAddDprdRidPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if (null != condition.getDprdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmInstInfo.PROPERTY_DPRD_RID).as(Long.class), condition.getDprdRid()));
		}
	}
	private void tryAddDelmRidPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if (null != condition.getDelmRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmInstInfo.PROPERTY_DELM_RID).as(Long.class), condition.getDelmRid()));
		}
	}
	private void tryAddSinstRidPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if (null != condition.getSinstRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmInstInfo.PROPERTY_SINST_RID).as(Long.class), condition.getSinstRid()));
		}
	}
	private void tryAddDinstRidPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if (null != condition.getDinstRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmInstInfo.PROPERTY_DINST_RID).as(Long.class), condition.getDinstRid()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmInstInfo.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddInfoValue1Predicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue1())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_INFO_VALUE1).as(String.class), "%"+condition.getInfoValue1()+"%"));
		}
	}
	private void tryAddInfoValue2Predicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue2())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_INFO_VALUE2).as(String.class), "%"+condition.getInfoValue2()+"%"));
		}
	}
	private void tryAddInfoValue3Predicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue3())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_INFO_VALUE3).as(String.class), "%"+condition.getInfoValue3()+"%"));
		}
	}
	private void tryAddInfoValue4Predicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue4())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_INFO_VALUE4).as(String.class), "%"+condition.getInfoValue4()+"%"));
		}
	}
	private void tryAddInfoValue5Predicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue5())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_INFO_VALUE5).as(String.class), "%"+condition.getInfoValue5()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmInstInfo.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpSysElmInstInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpSysElmInstInfo.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
}


