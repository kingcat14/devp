package net.aicoder.devp.deploy.business.ops.dao;

import net.aicoder.devp.deploy.business.ops.dto.DevpOpsIssueCondition;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsIssue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpOpsIssueSpecification implements Specification<DevpOpsIssue>{

	private DevpOpsIssueCondition condition;

	public DevpOpsIssueSpecification(DevpOpsIssueCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpOpsIssue> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddTypePredicate(predicateList, root, cb);
		tryAddIssuePredicate(predicateList, root, cb);
		tryAddReplyPredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddHasAttachmentPredicate(predicateList, root, cb);
		tryAddNexusTypePredicate(predicateList, root, cb);
		tryAddNexusRidPredicate(predicateList, root, cb);
		tryAddNexusVersionPredicate(predicateList, root, cb);
		tryAddNexusPhasePredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddParasCodePredicate(predicateList, root, cb);
		tryAddCmodifyUcodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsIssue.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpOpsIssue.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddIssuePredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getIssue())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_ISSUE).as(String.class), "%"+condition.getIssue()+"%"));
		}
	}
	private void tryAddReplyPredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getReply())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_REPLY).as(String.class), "%"+condition.getReply()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddHasAttachmentPredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if (null != condition.getHasAttachment() ) {
			predicateList.add(cb.equal(root.get(DevpOpsIssue.PROPERTY_HAS_ATTACHMENT).as(Integer.class), condition.getHasAttachment()));
		}
	}
	private void tryAddNexusTypePredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNexusType())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_NEXUS_TYPE).as(String.class), "%"+condition.getNexusType()+"%"));
		}
	}
	private void tryAddNexusRidPredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if (null != condition.getNexusRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsIssue.PROPERTY_NEXUS_RID).as(Long.class), condition.getNexusRid()));
		}
	}
	private void tryAddNexusVersionPredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNexusVersion())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_NEXUS_VERSION).as(String.class), "%"+condition.getNexusVersion()+"%"));
		}
	}
	private void tryAddNexusPhasePredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNexusPhase())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_NEXUS_PHASE).as(String.class), "%"+condition.getNexusPhase()+"%"));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpOpsIssue.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddParasCodePredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParasCode())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_PARAS_CODE).as(String.class), "%"+condition.getParasCode()+"%"));
		}
	}
	private void tryAddCmodifyUcodePredicate(List<Predicate> predicateList, Root<DevpOpsIssue> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCmodifyUcode())){
			predicateList.add(cb.like(root.get(DevpOpsIssue.PROPERTY_CMODIFY_UCODE).as(String.class), "%"+condition.getCmodifyUcode()+"%"));
		}
	}
}


