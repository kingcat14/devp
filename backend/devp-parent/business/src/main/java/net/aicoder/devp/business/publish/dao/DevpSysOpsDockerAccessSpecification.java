package net.aicoder.devp.business.publish.dao;

import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerAccess;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DevpSysOpsDockerAccessSpecification implements Specification<DevpSysOpsDockerAccess>{

	private DevpSysOpsDockerAccessCondition condition;

	public DevpSysOpsDockerAccessSpecification(DevpSysOpsDockerAccessCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysOpsDockerAccess> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddServicesPortPredicate(predicateList, root, cb);
		tryAddDockerPortPredicate(predicateList, root, cb);
		tryAddProtocolPredicate(predicateList, root, cb);
		tryAddAutoAssignPredicate(predicateList, root, cb);
		tryAddHostPortPredicate(predicateList, root, cb);
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddSchemeRidPredicate(predicateList, root, cb);
		tryAddCmpRidPredicate(predicateList, root, cb);
		tryAddTaskRidPredicate(predicateList, root, cb);
		tryAddDockerRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCreateUnamePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);
		tryAddModifyUnamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsDockerAccess.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsDockerAccess.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddServicesPortPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getServicesPort() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_SERVICES_PORT).as(Integer.class), condition.getServicesPort()));
		}

		if (null != condition.getServicesPortMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsDockerAccess.PROPERTY_SERVICES_PORT).as(Integer.class), condition.getServicesPortMax()));
		}

		if (null != condition.getServicesPortMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsDockerAccess.PROPERTY_SERVICES_PORT).as(Integer.class), condition.getServicesPortMin()));
		}
	}
	private void tryAddDockerPortPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getDockerPort() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_DOCKER_PORT).as(Integer.class), condition.getDockerPort()));
		}

		if (null != condition.getDockerPortMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsDockerAccess.PROPERTY_DOCKER_PORT).as(Integer.class), condition.getDockerPortMax()));
		}

		if (null != condition.getDockerPortMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsDockerAccess.PROPERTY_DOCKER_PORT).as(Integer.class), condition.getDockerPortMin()));
		}
	}
	private void tryAddProtocolPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProtocol())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_PROTOCOL).as(String.class), "%"+condition.getProtocol()+"%"));
		}
	}
	private void tryAddAutoAssignPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getAutoAssign() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_AUTO_ASSIGN).as(Integer.class), condition.getAutoAssign()));
		}

		if (null != condition.getAutoAssignMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsDockerAccess.PROPERTY_AUTO_ASSIGN).as(Integer.class), condition.getAutoAssignMax()));
		}

		if (null != condition.getAutoAssignMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsDockerAccess.PROPERTY_AUTO_ASSIGN).as(Integer.class), condition.getAutoAssignMin()));
		}
	}
	private void tryAddHostPortPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getHostPort() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_HOST_PORT).as(Integer.class), condition.getHostPort()));
		}

		if (null != condition.getHostPortMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsDockerAccess.PROPERTY_HOST_PORT).as(Integer.class), condition.getHostPortMax()));
		}

		if (null != condition.getHostPortMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsDockerAccess.PROPERTY_HOST_PORT).as(Integer.class), condition.getHostPortMin()));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}

		if (null != condition.getPrdRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsDockerAccess.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMax()));
		}

		if (null != condition.getPrdRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsDockerAccess.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMin()));
		}
	}
	private void tryAddSchemeRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getSchemeRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRid()));
		}

		if (null != condition.getSchemeRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsDockerAccess.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRidMax()));
		}

		if (null != condition.getSchemeRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsDockerAccess.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRidMin()));
		}
	}
	private void tryAddCmpRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getCmpRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRid()));
		}

		if (null != condition.getCmpRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsDockerAccess.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRidMax()));
		}

		if (null != condition.getCmpRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsDockerAccess.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRidMin()));
		}
	}
	private void tryAddTaskRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getTaskRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRid()));
		}

		if (null != condition.getTaskRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsDockerAccess.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRidMax()));
		}

		if (null != condition.getTaskRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsDockerAccess.PROPERTY_TASK_RID).as(Long.class), condition.getTaskRidMin()));
		}
	}
	private void tryAddDockerRidPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getDockerRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_DOCKER_RID).as(Long.class), condition.getDockerRid()));
		}

		if (null != condition.getDockerRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsDockerAccess.PROPERTY_DOCKER_RID).as(Long.class), condition.getDockerRidMax()));
		}

		if (null != condition.getDockerRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsDockerAccess.PROPERTY_DOCKER_RID).as(Long.class), condition.getDockerRidMin()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysOpsDockerAccess.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysOpsDockerAccess.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysOpsDockerAccess.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpSysOpsDockerAccess> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpSysOpsDockerAccess.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


