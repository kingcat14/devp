package net.aicoder.devp.business.publish.dao;

import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeNode;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DevpSysOpsPipeNodeSpecification implements Specification<DevpSysOpsPipeNode>{

	private DevpSysOpsPipeNodeCondition condition;

	public DevpSysOpsPipeNodeSpecification(DevpSysOpsPipeNodeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysOpsPipeNode> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddExecModelPredicate(predicateList, root, cb);
		tryAddExecSeqPredicate(predicateList, root, cb);
		tryAddReturnCodePredicate(predicateList, root, cb);
		tryAddExecStatusPredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddSchemeRidPredicate(predicateList, root, cb);
		tryAddPipelineRidPredicate(predicateList, root, cb);
		tryAddCmpRidPredicate(predicateList, root, cb);
		tryAddTaskRidPredicate(predicateList, root, cb);
		tryAddParentRidPredicate(predicateList, root, cb);
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


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeNode.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeNode.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeNode.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeNode.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddSubTypePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubType())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_SUB_TYPE).as(String.class), "%"+condition.getSubType()+"%"));
		}
	}
	private void tryAddExecModelPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getExecModel())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_EXEC_MODEL).as(String.class), "%"+condition.getExecModel()+"%"));
		}
	}
	private void tryAddExecSeqPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getExecSeq())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_EXEC_SEQ).as(String.class), "%"+condition.getExecSeq()+"%"));
		}
	}
	private void tryAddReturnCodePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getReturnCode())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_RETURN_CODE).as(String.class), "%"+condition.getReturnCode()+"%"));
		}
	}
	private void tryAddExecStatusPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getExecStatus())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_EXEC_STATUS).as(String.class), "%"+condition.getExecStatus()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){

		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeNode.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}

		if (null != condition.getPrdRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeNode.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMax()));
		}

		if (null != condition.getPrdRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeNode.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMin()));
		}
	}
	private void tryAddSchemeRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){

		if (null != condition.getSchemeRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeNode.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRid()));
		}

		if (null != condition.getSchemeRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeNode.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRidMax()));
		}

		if (null != condition.getSchemeRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeNode.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRidMin()));
		}
	}
	private void tryAddPipelineRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){

		if (null != condition.getPipelineRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeNode.PROPERTY_PIPELINE_RID).as(Long.class), condition.getPipelineRid()));
		}

		if (null != condition.getPipelineRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeNode.PROPERTY_PIPELINE_RID).as(Long.class), condition.getPipelineRidMax()));
		}

		if (null != condition.getPipelineRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeNode.PROPERTY_PIPELINE_RID).as(Long.class), condition.getPipelineRidMin()));
		}
	}
	private void tryAddCmpRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){

		if (null != condition.getCmpRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeNode.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRid()));
		}

		if (null != condition.getCmpRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeNode.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRidMax()));
		}

		if (null != condition.getCmpRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeNode.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRidMin()));
		}
	}
	private void tryAddTaskRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){

		if (null != condition.getTaskRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeNode.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRid()));
		}

		if (null != condition.getTaskRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeNode.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRidMax()));
		}

		if (null != condition.getTaskRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeNode.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRidMin()));
		}
	}
	private void tryAddParentRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){

		if (null != condition.getParentRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeNode.PROPERTY_PARENT_RID).as(Long.class), condition.getParentRid()));
		}

		if (null != condition.getParentRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeNode.PROPERTY_PARENT_RID).as(Long.class), condition.getParentRidMax()));
		}

		if (null != condition.getParentRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeNode.PROPERTY_PARENT_RID).as(Long.class), condition.getParentRidMin()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeNode.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeNode.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeNode.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddDefaultPipelinePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){

		if (null != condition.getDefaultPipeline() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsPipeNode.PROPERTY_DEFAULT_PIPELINE).as(Integer.class), condition.getDefaultPipeline()));
		}

		if (null != condition.getDefaultPipelineMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsPipeNode.PROPERTY_DEFAULT_PIPELINE).as(Integer.class), condition.getDefaultPipelineMax()));
		}

		if (null != condition.getDefaultPipelineMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsPipeNode.PROPERTY_DEFAULT_PIPELINE).as(Integer.class), condition.getDefaultPipelineMin()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpSysOpsPipeNode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpSysOpsPipeNode.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


