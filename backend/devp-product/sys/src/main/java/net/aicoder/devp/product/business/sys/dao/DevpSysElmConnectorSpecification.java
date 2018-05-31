package net.aicoder.devp.product.business.sys.dao;

import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorCondition;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmConnector;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysElmConnectorSpecification implements Specification<DevpSysElmConnector>{

	DevpSysElmConnectorCondition condition;

	public DevpSysElmConnectorSpecification(DevpSysElmConnectorCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysElmConnector> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddAliasPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddSprdRidPredicate(predicateList, root, cb);
		tryAddSelmRidPredicate(predicateList, root, cb);
		tryAddDprdRidPredicate(predicateList, root, cb);
		tryAddDelmRidPredicate(predicateList, root, cb);
		tryAddSinstRidPredicate(predicateList, root, cb);
		tryAddDinstRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddSubTypePredicate(predicateList, root, cb);
		tryAddStereotypePredicate(predicateList, root, cb);
		tryAddScopePredicate(predicateList, root, cb);
		tryAddDirectionPredicate(predicateList, root, cb);
		tryAddSrcMultiPredicate(predicateList, root, cb);
		tryAddSrcRolePredicate(predicateList, root, cb);
		tryAddSrcRoleTypePredicate(predicateList, root, cb);
		tryAddDestMultiPredicate(predicateList, root, cb);
		tryAddDestRolePredicate(predicateList, root, cb);
		tryAddDestRoleTypePredicate(predicateList, root, cb);
		tryAddAttrRelationPredicate(predicateList, root, cb);
		tryAddRecordStatePredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmConnector.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddSprdRidPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if (null != condition.getSprdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmConnector.PROPERTY_SPRD_RID).as(Long.class), condition.getSprdRid()));
		}
	}
	private void tryAddSelmRidPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if (null != condition.getSelmRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmConnector.PROPERTY_SELM_RID).as(Long.class), condition.getSelmRid()));
		}
	}
	private void tryAddDprdRidPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if (null != condition.getDprdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmConnector.PROPERTY_DPRD_RID).as(Long.class), condition.getDprdRid()));
		}
	}
	private void tryAddDelmRidPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if (null != condition.getDelmRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmConnector.PROPERTY_DELM_RID).as(Long.class), condition.getDelmRid()));
		}
	}
	private void tryAddSinstRidPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if (null != condition.getSinstRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmConnector.PROPERTY_SINST_RID).as(Long.class), condition.getSinstRid()));
		}
	}
	private void tryAddDinstRidPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if (null != condition.getDinstRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmConnector.PROPERTY_DINST_RID).as(Long.class), condition.getDinstRid()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmConnector.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddSubTypePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubType())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_SUB_TYPE).as(String.class), "%"+condition.getSubType()+"%"));
		}
	}
	private void tryAddStereotypePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStereotype())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_STEREOTYPE).as(String.class), "%"+condition.getStereotype()+"%"));
		}
	}
	private void tryAddScopePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScope())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_SCOPE).as(String.class), "%"+condition.getScope()+"%"));
		}
	}
	private void tryAddDirectionPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDirection())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_DIRECTION).as(String.class), "%"+condition.getDirection()+"%"));
		}
	}
	private void tryAddSrcMultiPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSrcMulti())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_SRC_MULTI).as(String.class), "%"+condition.getSrcMulti()+"%"));
		}
	}
	private void tryAddSrcRolePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSrcRole())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_SRC_ROLE).as(String.class), "%"+condition.getSrcRole()+"%"));
		}
	}
	private void tryAddSrcRoleTypePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSrcRoleType())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_SRC_ROLE_TYPE).as(String.class), "%"+condition.getSrcRoleType()+"%"));
		}
	}
	private void tryAddDestMultiPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDestMulti())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_DEST_MULTI).as(String.class), "%"+condition.getDestMulti()+"%"));
		}
	}
	private void tryAddDestRolePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDestRole())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_DEST_ROLE).as(String.class), "%"+condition.getDestRole()+"%"));
		}
	}
	private void tryAddDestRoleTypePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDestRoleType())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_DEST_ROLE_TYPE).as(String.class), "%"+condition.getDestRoleType()+"%"));
		}
	}
	private void tryAddAttrRelationPredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAttrRelation())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_ATTR_RELATION).as(String.class), "%"+condition.getAttrRelation()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysElmConnector.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpSysElmConnector> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpSysElmConnector.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
}


