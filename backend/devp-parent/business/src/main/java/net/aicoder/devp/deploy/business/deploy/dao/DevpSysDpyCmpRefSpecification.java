package net.aicoder.devp.deploy.business.deploy.dao;

import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyCmpRefCondition;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyCmpRef;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysDpyCmpRefSpecification implements Specification<DevpSysDpyCmpRef>{

	private DevpSysDpyCmpRefCondition condition;

	public DevpSysDpyCmpRefSpecification(DevpSysDpyCmpRefCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysDpyCmpRef> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddSchemeRidPredicate(predicateList, root, cb);
		tryAddCmpRidPredicate(predicateList, root, cb);
		tryAddRefEtypePredicate(predicateList, root, cb);
		tryAddRefPrdRidPredicate(predicateList, root, cb);
		tryAddRefElmRidPredicate(predicateList, root, cb);
		tryAddRefInstRidPredicate(predicateList, root, cb);
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


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyCmpRef.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyCmpRef.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyCmpRef.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}
	}
	private void tryAddSchemeRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if (null != condition.getSchemeRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyCmpRef.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRid()));
		}
	}
	private void tryAddCmpRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if (null != condition.getCmpRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyCmpRef.PROPERTY_CMP_RID).as(Long.class), condition.getCmpRid()));
		}
	}
	private void tryAddRefEtypePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if (null != condition.getRefEtype() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyCmpRef.PROPERTY_REF_ETYPE).as(Long.class), condition.getRefEtype()));
		}
	}
	private void tryAddRefPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if (null != condition.getRefPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyCmpRef.PROPERTY_REF_PRD_RID).as(Long.class), condition.getRefPrdRid()));
		}
	}
	private void tryAddRefElmRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if (null != condition.getRefElmRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyCmpRef.PROPERTY_REF_ELM_RID).as(Long.class), condition.getRefElmRid()));
		}
	}
	private void tryAddRefInstRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if (null != condition.getRefInstRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyCmpRef.PROPERTY_REF_INST_RID).as(Long.class), condition.getRefInstRid()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyCmpRef.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddSubTypePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubType())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_SUB_TYPE).as(String.class), "%"+condition.getSubType()+"%"));
		}
	}
	private void tryAddStereotypePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStereotype())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_STEREOTYPE).as(String.class), "%"+condition.getStereotype()+"%"));
		}
	}
	private void tryAddScopePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScope())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_SCOPE).as(String.class), "%"+condition.getScope()+"%"));
		}
	}
	private void tryAddDirectionPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDirection())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_DIRECTION).as(String.class), "%"+condition.getDirection()+"%"));
		}
	}
	private void tryAddSrcMultiPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSrcMulti())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_SRC_MULTI).as(String.class), "%"+condition.getSrcMulti()+"%"));
		}
	}
	private void tryAddSrcRolePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSrcRole())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_SRC_ROLE).as(String.class), "%"+condition.getSrcRole()+"%"));
		}
	}
	private void tryAddSrcRoleTypePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSrcRoleType())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_SRC_ROLE_TYPE).as(String.class), "%"+condition.getSrcRoleType()+"%"));
		}
	}
	private void tryAddDestMultiPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDestMulti())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_DEST_MULTI).as(String.class), "%"+condition.getDestMulti()+"%"));
		}
	}
	private void tryAddDestRolePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDestRole())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_DEST_ROLE).as(String.class), "%"+condition.getDestRole()+"%"));
		}
	}
	private void tryAddDestRoleTypePredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDestRoleType())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_DEST_ROLE_TYPE).as(String.class), "%"+condition.getDestRoleType()+"%"));
		}
	}
	private void tryAddAttrRelationPredicate(List<Predicate> predicateList, Root<DevpSysDpyCmpRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAttrRelation())){
			predicateList.add(cb.like(root.get(DevpSysDpyCmpRef.PROPERTY_ATTR_RELATION).as(String.class), "%"+condition.getAttrRelation()+"%"));
		}
	}
}


