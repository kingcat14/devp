package net.aicoder.devp.business.publish.dao;

import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileCondition;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskCompile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysOpsTaskCompileSpecification implements Specification<DevpSysOpsTaskCompile>{

	private DevpSysOpsTaskCompileCondition condition;

	public DevpSysOpsTaskCompileSpecification(DevpSysOpsTaskCompileCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysOpsTaskCompile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddCmSameasPredicate(predicateList, root, cb);
		tryAddCmTypePredicate(predicateList, root, cb);
		tryAddCmRepositoryPredicate(predicateList, root, cb);
		tryAddCmTagPredicate(predicateList, root, cb);
		tryAddCmUserPredicate(predicateList, root, cb);
		tryAddCmPasswordPredicate(predicateList, root, cb);
		tryAddSubDirPredicate(predicateList, root, cb);
		tryAddBaselinePredicate(predicateList, root, cb);
		tryAddSvnCodeUrlPredicate(predicateList, root, cb);
		tryAddBuildToolsPredicate(predicateList, root, cb);
		tryAddEnvVersionPredicate(predicateList, root, cb);
		tryAddToolVersionPredicate(predicateList, root, cb);
		tryAddPreActionPredicate(predicateList, root, cb);
		tryAddBuildActionPredicate(predicateList, root, cb);
		tryAddPostActionPredicate(predicateList, root, cb);
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


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskCompile.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskCompile.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskCompile.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskCompile.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskCompile.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskCompile.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddSubTypePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubType())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_SUB_TYPE).as(String.class), "%"+condition.getSubType()+"%"));
		}
	}
	private void tryAddCmSameasPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){

		if (null != condition.getCmSameas() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskCompile.PROPERTY_CM_SAMEAS).as(Integer.class), condition.getCmSameas()));
		}

		if (null != condition.getCmSameasMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskCompile.PROPERTY_CM_SAMEAS).as(Integer.class), condition.getCmSameasMax()));
		}

		if (null != condition.getCmSameasMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskCompile.PROPERTY_CM_SAMEAS).as(Integer.class), condition.getCmSameasMin()));
		}
	}
	private void tryAddCmTypePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCmType())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_CM_TYPE).as(String.class), "%"+condition.getCmType()+"%"));
		}
	}
	private void tryAddCmRepositoryPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCmRepository())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_CM_REPOSITORY).as(String.class), "%"+condition.getCmRepository()+"%"));
		}
	}
	private void tryAddCmTagPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCmTag())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_CM_TAG).as(String.class), "%"+condition.getCmTag()+"%"));
		}
	}
	private void tryAddCmUserPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCmUser())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_CM_USER).as(String.class), "%"+condition.getCmUser()+"%"));
		}
	}
	private void tryAddCmPasswordPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCmPassword())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_CM_PASSWORD).as(String.class), "%"+condition.getCmPassword()+"%"));
		}
	}
	private void tryAddSubDirPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubDir())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_SUB_DIR).as(String.class), "%"+condition.getSubDir()+"%"));
		}
	}
	private void tryAddBaselinePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBaseline())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_BASELINE).as(String.class), "%"+condition.getBaseline()+"%"));
		}
	}
	private void tryAddSvnCodeUrlPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSvnCodeUrl())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_SVN_CODE_URL).as(String.class), "%"+condition.getSvnCodeUrl()+"%"));
		}
	}
	private void tryAddBuildToolsPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBuildTools())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_BUILD_TOOLS).as(String.class), "%"+condition.getBuildTools()+"%"));
		}
	}
	private void tryAddEnvVersionPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEnvVersion())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_ENV_VERSION).as(String.class), "%"+condition.getEnvVersion()+"%"));
		}
	}
	private void tryAddToolVersionPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getToolVersion())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_TOOL_VERSION).as(String.class), "%"+condition.getToolVersion()+"%"));
		}
	}
	private void tryAddPreActionPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPreAction())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_PRE_ACTION).as(String.class), "%"+condition.getPreAction()+"%"));
		}
	}
	private void tryAddBuildActionPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBuildAction())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_BUILD_ACTION).as(String.class), "%"+condition.getBuildAction()+"%"));
		}
	}
	private void tryAddPostActionPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPostAction())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_POST_ACTION).as(String.class), "%"+condition.getPostAction()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){

		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskCompile.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}

		if (null != condition.getPrdRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskCompile.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMax()));
		}

		if (null != condition.getPrdRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskCompile.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMin()));
		}
	}
	private void tryAddSchemeRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){

		if (null != condition.getSchemeRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskCompile.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRid()));
		}

		if (null != condition.getSchemeRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskCompile.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRidMax()));
		}

		if (null != condition.getSchemeRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskCompile.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRidMin()));
		}
	}
	private void tryAddCmpRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){

		if (null != condition.getCmpRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskCompile.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRid()));
		}

		if (null != condition.getCmpRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskCompile.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRidMax()));
		}

		if (null != condition.getCmpRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskCompile.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRidMin()));
		}
	}
	private void tryAddTaskRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){

		if (null != condition.getTaskRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskCompile.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRid()));
		}

		if (null != condition.getTaskRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskCompile.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRidMax()));
		}

		if (null != condition.getTaskRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskCompile.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRidMin()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsTaskCompile.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsTaskCompile.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsTaskCompile.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpSysOpsTaskCompile> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpSysOpsTaskCompile.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


