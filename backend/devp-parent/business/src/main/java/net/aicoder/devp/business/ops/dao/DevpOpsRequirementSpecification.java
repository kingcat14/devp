package net.aicoder.devp.business.ops.dao;

import net.aicoder.devp.business.ops.domain.DevpOpsRequirement;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementCondition;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpOpsRequirementSpecification implements Specification<DevpOpsRequirement>{

	private DevpOpsRequirementCondition condition;

	public DevpOpsRequirementSpecification(DevpOpsRequirementCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpOpsRequirement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddTypeCodePredicate(predicateList, root, cb);
		tryAddTypeNamePredicate(predicateList, root, cb);
		tryAddContentPredicate(predicateList, root, cb);
		tryAddHasAttachmentPredicate(predicateList, root, cb);
		tryAddStereotypePredicate(predicateList, root, cb);
		tryAddScopePredicate(predicateList, root, cb);
		tryAddVersionPredicate(predicateList, root, cb);
		tryAddPhasePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddRecordStatePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsRequirement.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddNexusTypePredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNexusType())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_NEXUS_TYPE).as(String.class), "%"+condition.getNexusType()+"%"));
		}
	}
	private void tryAddNexusRidPredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if (null != condition.getNexusRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsRequirement.PROPERTY_NEXUS_RID).as(Long.class), condition.getNexusRid()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpOpsRequirement.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddTypeCodePredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeCode())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_TYPE_CODE).as(String.class), "%"+condition.getTypeCode()+"%"));
		}
	}
	private void tryAddTypeNamePredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeName())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_TYPE_NAME).as(String.class), "%"+condition.getTypeName()+"%"));
		}
	}
	private void tryAddContentPredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getContent())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_CONTENT).as(String.class), "%"+condition.getContent()+"%"));
		}
	}
	private void tryAddHasAttachmentPredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if (null != condition.getHasAttachment() ) {
			predicateList.add(cb.equal(root.get(DevpOpsRequirement.PROPERTY_HAS_ATTACHMENT).as(Integer.class), condition.getHasAttachment()));
		}
	}
	private void tryAddStereotypePredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStereotype())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_STEREOTYPE).as(String.class), "%"+condition.getStereotype()+"%"));
		}
	}
	private void tryAddScopePredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScope())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_SCOPE).as(String.class), "%"+condition.getScope()+"%"));
		}
	}
	private void tryAddVersionPredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getVersion())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_VERSION).as(String.class), "%"+condition.getVersion()+"%"));
		}
	}
	private void tryAddPhasePredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPhase())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_PHASE).as(String.class), "%"+condition.getPhase()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(DevpOpsRequirement.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpOpsRequirement> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpOpsRequirement.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
}


