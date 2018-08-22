package net.aicoder.devp.business.ops.dao;

import net.aicoder.devp.business.ops.domain.DevpOpsElementInfo;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoCondition;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpOpsElementInfoSpecification implements Specification<DevpOpsElementInfo>{

	private DevpOpsElementInfoCondition condition;

	public DevpOpsElementInfoSpecification(DevpOpsElementInfoCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpOpsElementInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddEtypePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddAliasPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddRecordStatePredicate(predicateList, root, cb);
		tryAddElmRidPredicate(predicateList, root, cb);
		tryAddInstRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddInfoCode1Predicate(predicateList, root, cb);
		tryAddInfoValue1Predicate(predicateList, root, cb);
		tryAddInfoCode2Predicate(predicateList, root, cb);
		tryAddInfoValue2Predicate(predicateList, root, cb);
		tryAddInfoCode3Predicate(predicateList, root, cb);
		tryAddInfoValue3Predicate(predicateList, root, cb);
		tryAddInfoCode4Predicate(predicateList, root, cb);
		tryAddInfoValue4Predicate(predicateList, root, cb);
		tryAddInfoCode5Predicate(predicateList, root, cb);
		tryAddInfoValue5Predicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddParasCodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsElementInfo.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpOpsElementInfo.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddElmRidPredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getElmRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsElementInfo.PROPERTY_ELM_RID).as(Long.class), condition.getElmRid()));
		}
	}
	private void tryAddInstRidPredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getInstRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsElementInfo.PROPERTY_INST_RID).as(Long.class), condition.getInstRid()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpOpsElementInfo.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddInfoCode1Predicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoCode1())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_INFO_CODE1).as(String.class), "%"+condition.getInfoCode1()+"%"));
		}
	}
	private void tryAddInfoValue1Predicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue1())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_INFO_VALUE1).as(String.class), "%"+condition.getInfoValue1()+"%"));
		}
	}
	private void tryAddInfoCode2Predicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoCode2())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_INFO_CODE2).as(String.class), "%"+condition.getInfoCode2()+"%"));
		}
	}
	private void tryAddInfoValue2Predicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue2())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_INFO_VALUE2).as(String.class), "%"+condition.getInfoValue2()+"%"));
		}
	}
	private void tryAddInfoCode3Predicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoCode3())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_INFO_CODE3).as(String.class), "%"+condition.getInfoCode3()+"%"));
		}
	}
	private void tryAddInfoValue3Predicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue3())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_INFO_VALUE3).as(String.class), "%"+condition.getInfoValue3()+"%"));
		}
	}
	private void tryAddInfoCode4Predicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoCode4())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_INFO_CODE4).as(String.class), "%"+condition.getInfoCode4()+"%"));
		}
	}
	private void tryAddInfoValue4Predicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue4())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_INFO_VALUE4).as(String.class), "%"+condition.getInfoValue4()+"%"));
		}
	}
	private void tryAddInfoCode5Predicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoCode5())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_INFO_CODE5).as(String.class), "%"+condition.getInfoCode5()+"%"));
		}
	}
	private void tryAddInfoValue5Predicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue5())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_INFO_VALUE5).as(String.class), "%"+condition.getInfoValue5()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddParasCodePredicate(List<Predicate> predicateList, Root<DevpOpsElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParasCode())){
			predicateList.add(cb.like(root.get(DevpOpsElementInfo.PROPERTY_PARAS_CODE).as(String.class), "%"+condition.getParasCode()+"%"));
		}
	}
}


