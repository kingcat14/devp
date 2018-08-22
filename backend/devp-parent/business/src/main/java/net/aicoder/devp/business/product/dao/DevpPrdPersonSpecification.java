package net.aicoder.devp.business.product.dao;

import net.aicoder.devp.business.product.dto.DevpPrdPersonCondition;
import net.aicoder.devp.business.product.domain.DevpPrdPerson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpPrdPersonSpecification implements Specification<DevpPrdPerson>{

	private DevpPrdPersonCondition condition;

	public DevpPrdPersonSpecification(DevpPrdPersonCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpPrdPerson> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddNexusTypePredicate(predicateList, root, cb);
		tryAddNexusRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddUidPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddRolePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddUserTidPredicate(predicateList, root, cb);
		tryAddOrgRidPredicate(predicateList, root, cb);
		tryAddOrgNamePredicate(predicateList, root, cb);
		tryAddRecordStatePredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCreateUnamePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);
		tryAddModifyUnamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdPerson.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpPrdPerson.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpPrdPerson.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddNexusTypePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNexusType())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_NEXUS_TYPE).as(String.class), "%"+condition.getNexusType()+"%"));
		}
	}
	private void tryAddNexusRidPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){

		if (null != condition.getNexusRid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdPerson.PROPERTY_NEXUS_RID).as(Long.class), condition.getNexusRid()));
		}

		if (null != condition.getNexusRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpPrdPerson.PROPERTY_NEXUS_RID).as(Long.class), condition.getNexusRidMax()));
		}

		if (null != condition.getNexusRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpPrdPerson.PROPERTY_NEXUS_RID).as(Long.class), condition.getNexusRidMin()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpPrdPerson.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpPrdPerson.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpPrdPerson.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddUidPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){

		if (null != condition.getUid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdPerson.PROPERTY_UID).as(Long.class), condition.getUid()));
		}

		if (null != condition.getUidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpPrdPerson.PROPERTY_UID).as(Long.class), condition.getUidMax()));
		}

		if (null != condition.getUidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpPrdPerson.PROPERTY_UID).as(Long.class), condition.getUidMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddRolePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRole())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_ROLE).as(String.class), "%"+condition.getRole()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddUserTidPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){

		if (null != condition.getUserTid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdPerson.PROPERTY_USER_TID).as(Long.class), condition.getUserTid()));
		}

		if (null != condition.getUserTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpPrdPerson.PROPERTY_USER_TID).as(Long.class), condition.getUserTidMax()));
		}

		if (null != condition.getUserTidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpPrdPerson.PROPERTY_USER_TID).as(Long.class), condition.getUserTidMin()));
		}
	}
	private void tryAddOrgRidPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){

		if (null != condition.getOrgRid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdPerson.PROPERTY_ORG_RID).as(Long.class), condition.getOrgRid()));
		}

		if (null != condition.getOrgRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpPrdPerson.PROPERTY_ORG_RID).as(Long.class), condition.getOrgRidMax()));
		}

		if (null != condition.getOrgRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpPrdPerson.PROPERTY_ORG_RID).as(Long.class), condition.getOrgRidMin()));
		}
	}
	private void tryAddOrgNamePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getOrgName())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_ORG_NAME).as(String.class), "%"+condition.getOrgName()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpPrdPerson.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpPrdPerson.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpPrdPerson.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


