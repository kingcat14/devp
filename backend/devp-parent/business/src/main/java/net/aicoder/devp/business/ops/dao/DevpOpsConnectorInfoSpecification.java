package net.aicoder.devp.business.ops.dao;

import net.aicoder.devp.business.ops.domain.DevpOpsConnectorInfo;
import net.aicoder.devp.business.ops.dto.DevpOpsConnectorInfoCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DevpOpsConnectorInfoSpecification implements Specification<DevpOpsConnectorInfo>{

	private DevpOpsConnectorInfoCondition condition;

	public DevpOpsConnectorInfoSpecification(DevpOpsConnectorInfoCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpOpsConnectorInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddContRidPredicate(predicateList, root, cb);
		tryAddSrcElmRidPredicate(predicateList, root, cb);
		tryAddSrcInstRidPredicate(predicateList, root, cb);
		tryAddDestElmRidPredicate(predicateList, root, cb);
		tryAddDestInstRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddTypeCodePredicate(predicateList, root, cb);
		tryAddTypeNamePredicate(predicateList, root, cb);
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
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCreateUnamePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);
		tryAddModifyUnamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsConnectorInfo.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpOpsConnectorInfo.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsConnectorInfo.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsConnectorInfo.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddContRidPredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){

		if (null != condition.getContRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsConnectorInfo.PROPERTY_CONT_RID).as(Long.class), condition.getContRid()));
		}

		if (null != condition.getContRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsConnectorInfo.PROPERTY_CONT_RID).as(Long.class), condition.getContRidMax()));
		}

		if (null != condition.getContRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsConnectorInfo.PROPERTY_CONT_RID).as(Long.class), condition.getContRidMin()));
		}
	}
	private void tryAddSrcElmRidPredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){

		if (null != condition.getSrcElmRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsConnectorInfo.PROPERTY_SRC_ELM_RID).as(Long.class), condition.getSrcElmRid()));
		}

		if (null != condition.getSrcElmRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsConnectorInfo.PROPERTY_SRC_ELM_RID).as(Long.class), condition.getSrcElmRidMax()));
		}

		if (null != condition.getSrcElmRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsConnectorInfo.PROPERTY_SRC_ELM_RID).as(Long.class), condition.getSrcElmRidMin()));
		}
	}
	private void tryAddSrcInstRidPredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){

		if (null != condition.getSrcInstRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsConnectorInfo.PROPERTY_SRC_INST_RID).as(Long.class), condition.getSrcInstRid()));
		}

		if (null != condition.getSrcInstRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsConnectorInfo.PROPERTY_SRC_INST_RID).as(Long.class), condition.getSrcInstRidMax()));
		}

		if (null != condition.getSrcInstRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsConnectorInfo.PROPERTY_SRC_INST_RID).as(Long.class), condition.getSrcInstRidMin()));
		}
	}
	private void tryAddDestElmRidPredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){

		if (null != condition.getDestElmRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsConnectorInfo.PROPERTY_DEST_ELM_RID).as(Long.class), condition.getDestElmRid()));
		}

		if (null != condition.getDestElmRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsConnectorInfo.PROPERTY_DEST_ELM_RID).as(Long.class), condition.getDestElmRidMax()));
		}

		if (null != condition.getDestElmRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsConnectorInfo.PROPERTY_DEST_ELM_RID).as(Long.class), condition.getDestElmRidMin()));
		}
	}
	private void tryAddDestInstRidPredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){

		if (null != condition.getDestInstRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsConnectorInfo.PROPERTY_DEST_INST_RID).as(Long.class), condition.getDestInstRid()));
		}

		if (null != condition.getDestInstRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsConnectorInfo.PROPERTY_DEST_INST_RID).as(Long.class), condition.getDestInstRidMax()));
		}

		if (null != condition.getDestInstRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsConnectorInfo.PROPERTY_DEST_INST_RID).as(Long.class), condition.getDestInstRidMin()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpOpsConnectorInfo.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsConnectorInfo.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsConnectorInfo.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddTypeCodePredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeCode())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_TYPE_CODE).as(String.class), "%"+condition.getTypeCode()+"%"));
		}
	}
	private void tryAddTypeNamePredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeName())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_TYPE_NAME).as(String.class), "%"+condition.getTypeName()+"%"));
		}
	}
	private void tryAddInfoCode1Predicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoCode1())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_INFO_CODE1).as(String.class), "%"+condition.getInfoCode1()+"%"));
		}
	}
	private void tryAddInfoValue1Predicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue1())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_INFO_VALUE1).as(String.class), "%"+condition.getInfoValue1()+"%"));
		}
	}
	private void tryAddInfoCode2Predicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoCode2())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_INFO_CODE2).as(String.class), "%"+condition.getInfoCode2()+"%"));
		}
	}
	private void tryAddInfoValue2Predicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue2())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_INFO_VALUE2).as(String.class), "%"+condition.getInfoValue2()+"%"));
		}
	}
	private void tryAddInfoCode3Predicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoCode3())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_INFO_CODE3).as(String.class), "%"+condition.getInfoCode3()+"%"));
		}
	}
	private void tryAddInfoValue3Predicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue3())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_INFO_VALUE3).as(String.class), "%"+condition.getInfoValue3()+"%"));
		}
	}
	private void tryAddInfoCode4Predicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoCode4())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_INFO_CODE4).as(String.class), "%"+condition.getInfoCode4()+"%"));
		}
	}
	private void tryAddInfoValue4Predicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue4())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_INFO_VALUE4).as(String.class), "%"+condition.getInfoValue4()+"%"));
		}
	}
	private void tryAddInfoCode5Predicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoCode5())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_INFO_CODE5).as(String.class), "%"+condition.getInfoCode5()+"%"));
		}
	}
	private void tryAddInfoValue5Predicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue5())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_INFO_VALUE5).as(String.class), "%"+condition.getInfoValue5()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddParasCodePredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParasCode())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_PARAS_CODE).as(String.class), "%"+condition.getParasCode()+"%"));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpOpsConnectorInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpOpsConnectorInfo.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


