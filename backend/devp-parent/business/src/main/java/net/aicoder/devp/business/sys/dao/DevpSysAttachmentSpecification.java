package net.aicoder.devp.business.sys.dao;

import net.aicoder.devp.business.sys.dto.DevpSysAttachmentCondition;
import net.aicoder.devp.business.sys.domain.DevpSysAttachment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysAttachmentSpecification implements Specification<DevpSysAttachment>{

	private DevpSysAttachmentCondition condition;

	public DevpSysAttachmentSpecification(DevpSysAttachmentCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysAttachment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddTypePredicate(predicateList, root, cb);
		tryAddSubTypePredicate(predicateList, root, cb);
		tryAddFileTypePredicate(predicateList, root, cb);
		tryAddAccessTypePredicate(predicateList, root, cb);
		tryAddWorkspacePredicate(predicateList, root, cb);
		tryAddPathPredicate(predicateList, root, cb);
		tryAddFileNamePredicate(predicateList, root, cb);
		tryAddFileVersionPredicate(predicateList, root, cb);
		tryAddObjRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddTplRidPredicate(predicateList, root, cb);
		tryAddFileCreaterPredicate(predicateList, root, cb);
		tryAddFileEditorPredicate(predicateList, root, cb);
		tryAddRevisabilityPredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCreateUnamePredicate(predicateList, root, cb);
		tryAddCmodifyUcodePredicate(predicateList, root, cb);
		tryAddModifyUnamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysAttachment.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysAttachment.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysAttachment.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysAttachment.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysAttachment.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysAttachment.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddSubTypePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubType())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_SUB_TYPE).as(String.class), "%"+condition.getSubType()+"%"));
		}
	}
	private void tryAddFileTypePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFileType())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_FILE_TYPE).as(String.class), "%"+condition.getFileType()+"%"));
		}
	}
	private void tryAddAccessTypePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAccessType())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_ACCESS_TYPE).as(String.class), "%"+condition.getAccessType()+"%"));
		}
	}
	private void tryAddWorkspacePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getWorkspace())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_WORKSPACE).as(String.class), "%"+condition.getWorkspace()+"%"));
		}
	}
	private void tryAddPathPredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPath())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_PATH).as(String.class), "%"+condition.getPath()+"%"));
		}
	}
	private void tryAddFileNamePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFileName())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_FILE_NAME).as(String.class), "%"+condition.getFileName()+"%"));
		}
	}
	private void tryAddFileVersionPredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFileVersion())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_FILE_VERSION).as(String.class), "%"+condition.getFileVersion()+"%"));
		}
	}
	private void tryAddObjRidPredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){

		if (null != condition.getObjRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysAttachment.PROPERTY_OBJ_RID).as(Long.class), condition.getObjRid()));
		}

		if (null != condition.getObjRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysAttachment.PROPERTY_OBJ_RID).as(Long.class), condition.getObjRidMax()));
		}

		if (null != condition.getObjRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysAttachment.PROPERTY_OBJ_RID).as(Long.class), condition.getObjRidMin()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysAttachment.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysAttachment.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysAttachment.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddTplRidPredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){

		if (null != condition.getTplRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysAttachment.PROPERTY_TPL_RID).as(Long.class), condition.getTplRid()));
		}

		if (null != condition.getTplRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysAttachment.PROPERTY_TPL_RID).as(Long.class), condition.getTplRidMax()));
		}

		if (null != condition.getTplRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysAttachment.PROPERTY_TPL_RID).as(Long.class), condition.getTplRidMin()));
		}
	}
	private void tryAddFileCreaterPredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFileCreater())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_FILE_CREATER).as(String.class), "%"+condition.getFileCreater()+"%"));
		}
	}
	private void tryAddFileEditorPredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFileEditor())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_FILE_EDITOR).as(String.class), "%"+condition.getFileEditor()+"%"));
		}
	}
	private void tryAddRevisabilityPredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){

		if (null != condition.getRevisability() ) {
			predicateList.add(cb.equal(root.get(DevpSysAttachment.PROPERTY_REVISABILITY).as(Integer.class), condition.getRevisability()));
		}

		if (null != condition.getRevisabilityMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysAttachment.PROPERTY_REVISABILITY).as(Integer.class), condition.getRevisabilityMax()));
		}

		if (null != condition.getRevisabilityMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysAttachment.PROPERTY_REVISABILITY).as(Integer.class), condition.getRevisabilityMin()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddCmodifyUcodePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCmodifyUcode())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_CMODIFY_UCODE).as(String.class), "%"+condition.getCmodifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpSysAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpSysAttachment.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


