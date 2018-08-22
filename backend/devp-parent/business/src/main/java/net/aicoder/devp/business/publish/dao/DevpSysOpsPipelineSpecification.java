package net.aicoder.devp.business.publish.dao;

import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineCondition;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeline;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysOpsPipelineSpecification implements Specification<DevpSysOpsPipeline>{

	private DevpSysOpsPipelineCondition condition;

	public DevpSysOpsPipelineSpecification(DevpSysOpsPipelineCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysOpsPipeline> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddStereotypePredicate(predicateList, root, cb);
		tryAddScopePredicate(predicateList, root, cb);
		tryAddPhasePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddSchemeRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddDefaultPipelinePredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCreateUnamePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);
		tryAddModifyUnamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeline.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeline.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeline.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeline.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeline.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeline.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddSubTypePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubType())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_SUB_TYPE).as(String.class), "%"+condition.getSubType()+"%"));
		}
	}
	private void tryAddStereotypePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStereotype())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_STEREOTYPE).as(String.class), "%"+condition.getStereotype()+"%"));
		}
	}
	private void tryAddScopePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScope())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_SCOPE).as(String.class), "%"+condition.getScope()+"%"));
		}
	}
	private void tryAddPhasePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPhase())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_PHASE).as(String.class), "%"+condition.getPhase()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){

		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeline.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}

		if (null != condition.getPrdRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeline.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMax()));
		}

		if (null != condition.getPrdRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeline.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMin()));
		}
	}
	private void tryAddSchemeRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){

		if (null != condition.getSchemeRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeline.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRid()));
		}

		if (null != condition.getSchemeRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeline.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRidMax()));
		}

		if (null != condition.getSchemeRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeline.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRidMin()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeline.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeline.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeline.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddDefaultPipelinePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){

		if (null != condition.getDefaultPipeline() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeline.PROPERTY_DEFAULT_PIPELINE).as(Integer.class), condition.getDefaultPipeline()));
		}

		if (null != condition.getDefaultPipelineMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeline.PROPERTY_DEFAULT_PIPELINE).as(Integer.class), condition.getDefaultPipelineMax()));
		}

		if (null != condition.getDefaultPipelineMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeline.PROPERTY_DEFAULT_PIPELINE).as(Integer.class), condition.getDefaultPipelineMin()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeline> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeline.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


