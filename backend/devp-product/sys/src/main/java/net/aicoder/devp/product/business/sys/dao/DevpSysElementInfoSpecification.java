package net.aicoder.devp.product.business.sys.dao;

import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoCondition;
import net.aicoder.devp.product.business.sys.domain.DevpSysElementInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysElementInfoSpecification implements Specification<DevpSysElementInfo>{

	DevpSysElementInfoCondition condition;

	public DevpSysElementInfoSpecification(DevpSysElementInfoCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysElementInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddElmRidPredicate(predicateList, root, cb);
		tryAddInstRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
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


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElementInfo.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElementInfo.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}
	}
	private void tryAddElmRidPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getElmRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElementInfo.PROPERTY_ELM_RID).as(Long.class), condition.getElmRid()));
		}
	}
	private void tryAddInstRidPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getInstRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElementInfo.PROPERTY_INST_RID).as(Long.class), condition.getInstRid()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysElementInfo.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddInfoValue1Predicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue1())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_INFO_VALUE1).as(String.class), "%"+condition.getInfoValue1()+"%"));
		}
	}
	private void tryAddInfoValue2Predicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue2())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_INFO_VALUE2).as(String.class), "%"+condition.getInfoValue2()+"%"));
		}
	}
	private void tryAddInfoValue3Predicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue3())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_INFO_VALUE3).as(String.class), "%"+condition.getInfoValue3()+"%"));
		}
	}
	private void tryAddInfoValue4Predicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue4())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_INFO_VALUE4).as(String.class), "%"+condition.getInfoValue4()+"%"));
		}
	}
	private void tryAddInfoValue5Predicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue5())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_INFO_VALUE5).as(String.class), "%"+condition.getInfoValue5()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysElementInfo.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
}


