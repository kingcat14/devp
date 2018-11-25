package net.aicoder.devp.business.publish.dao;

import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskDocker;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DevpSysOpsTaskDockerSpecification implements Specification<DevpSysOpsTaskDocker>{

	private DevpSysOpsTaskDockerCondition condition;

	public DevpSysOpsTaskDockerSpecification(DevpSysOpsTaskDockerCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysOpsTaskDocker> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddGroupPredicate(predicateList, root, cb);
		tryAddNamespacePredicate(predicateList, root, cb);
		tryAddPublishGroupPredicate(predicateList, root, cb);
		tryAddPublishArtifactPredicate(predicateList, root, cb);
		tryAddPublishVersionPredicate(predicateList, root, cb);
		tryAddPublishFilePredicate(predicateList, root, cb);
		tryAddInstancesNumPredicate(predicateList, root, cb);
		tryAddCpuPredicate(predicateList, root, cb);
		tryAddMemoryPredicate(predicateList, root, cb);
		tryAddServiceablePredicate(predicateList, root, cb);
		tryAddLbStrategyPredicate(predicateList, root, cb);
		tryAddAccessTypePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddSchemeRidPredicate(predicateList, root, cb);
		tryAddCmpRidPredicate(predicateList, root, cb);
		tryAddTaskRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCreateUnamePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);
		tryAddModifyUnamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskDocker.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskDocker.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskDocker.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskDocker.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddSubTypePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubType())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_SUB_TYPE).as(String.class), "%"+condition.getSubType()+"%"));
		}
	}
	private void tryAddGroupPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getGroup())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_GROUP).as(String.class), "%"+condition.getGroup()+"%"));
		}
	}
	private void tryAddNamespacePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNamespace())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_NAMESPACE).as(String.class), "%"+condition.getNamespace()+"%"));
		}
	}
	private void tryAddPublishGroupPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPublishGroup())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_PUBLISH_GROUP).as(String.class), "%"+condition.getPublishGroup()+"%"));
		}
	}
	private void tryAddPublishArtifactPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPublishArtifact())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_PUBLISH_ARTIFACT).as(String.class), "%"+condition.getPublishArtifact()+"%"));
		}
	}
	private void tryAddPublishVersionPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPublishVersion())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_PUBLISH_VERSION).as(String.class), "%"+condition.getPublishVersion()+"%"));
		}
	}
	private void tryAddPublishFilePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPublishFile())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_PUBLISH_FILE).as(String.class), "%"+condition.getPublishFile()+"%"));
		}
	}
	private void tryAddInstancesNumPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){

		if (null != condition.getInstancesNum() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskDocker.PROPERTY_INSTANCES_NUM).as(Integer.class), condition.getInstancesNum()));
		}

		if (null != condition.getInstancesNumMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskDocker.PROPERTY_INSTANCES_NUM).as(Integer.class), condition.getInstancesNumMax()));
		}

		if (null != condition.getInstancesNumMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskDocker.PROPERTY_INSTANCES_NUM).as(Integer.class), condition.getInstancesNumMin()));
		}
	}
	private void tryAddCpuPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){

		if (null != condition.getCpu() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskDocker.PROPERTY_CPU).as(Integer.class), condition.getCpu()));
		}

		if (null != condition.getCpuMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskDocker.PROPERTY_CPU).as(Integer.class), condition.getCpuMax()));
		}

		if (null != condition.getCpuMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskDocker.PROPERTY_CPU).as(Integer.class), condition.getCpuMin()));
		}
	}
	private void tryAddMemoryPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){

		if (null != condition.getMemory() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskDocker.PROPERTY_MEMORY).as(Double.class), condition.getMemory()));
		}

		if (null != condition.getMemoryMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskDocker.PROPERTY_MEMORY).as(Double.class), condition.getMemoryMax()));
		}

		if (null != condition.getMemoryMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskDocker.PROPERTY_MEMORY).as(Double.class), condition.getMemoryMin()));
		}
	}
	private void tryAddServiceablePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){

		if (null != condition.getServiceable() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskDocker.PROPERTY_SERVICEABLE).as(Integer.class), condition.getServiceable()));
		}

		if (null != condition.getServiceableMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskDocker.PROPERTY_SERVICEABLE).as(Integer.class), condition.getServiceableMax()));
		}

		if (null != condition.getServiceableMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskDocker.PROPERTY_SERVICEABLE).as(Integer.class), condition.getServiceableMin()));
		}
	}
	private void tryAddLbStrategyPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getLbStrategy())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_LB_STRATEGY).as(String.class), "%"+condition.getLbStrategy()+"%"));
		}
	}
	private void tryAddAccessTypePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAccessType())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_ACCESS_TYPE).as(String.class), "%"+condition.getAccessType()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){

		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskDocker.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}

		if (null != condition.getPrdRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskDocker.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMax()));
		}

		if (null != condition.getPrdRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskDocker.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMin()));
		}
	}
	private void tryAddSchemeRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){

		if (null != condition.getSchemeRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskDocker.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRid()));
		}

		if (null != condition.getSchemeRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskDocker.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRidMax()));
		}

		if (null != condition.getSchemeRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskDocker.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRidMin()));
		}
	}
	private void tryAddCmpRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){

		if (null != condition.getCmpRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskDocker.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRid()));
		}

		if (null != condition.getCmpRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskDocker.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRidMax()));
		}

		if (null != condition.getCmpRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskDocker.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRidMin()));
		}
	}
	private void tryAddTaskRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){

		if (null != condition.getTaskRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskDocker.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRid()));
		}

		if (null != condition.getTaskRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskDocker.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRidMax()));
		}

		if (null != condition.getTaskRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskDocker.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRidMin()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskDocker.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskDocker.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskDocker.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskDocker> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskDocker.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


