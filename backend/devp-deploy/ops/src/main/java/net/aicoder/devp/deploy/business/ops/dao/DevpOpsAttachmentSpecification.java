package net.aicoder.devp.deploy.business.ops.dao;

import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAttachmentCondition;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsAttachment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpOpsAttachmentSpecification implements Specification<DevpOpsAttachment>{

	private DevpOpsAttachmentCondition condition;

	public DevpOpsAttachmentSpecification(DevpOpsAttachmentCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpOpsAttachment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddTypeCodePredicate(predicateList, root, cb);
		tryAddTypeNamePredicate(predicateList, root, cb);
		tryAddFileTypePredicate(predicateList, root, cb);
		tryAddAccessTypePredicate(predicateList, root, cb);
		tryAddDomainPredicate(predicateList, root, cb);
		tryAddAddressPredicate(predicateList, root, cb);
		tryAddFileVersionPredicate(predicateList, root, cb);
		tryAddNexusTypePredicate(predicateList, root, cb);
		tryAddNexusRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddCmodifyUcodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAttachment.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAttachment.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddTypeCodePredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeCode())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_TYPE_CODE).as(String.class), "%"+condition.getTypeCode()+"%"));
		}
	}
	private void tryAddTypeNamePredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeName())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_TYPE_NAME).as(String.class), "%"+condition.getTypeName()+"%"));
		}
	}
	private void tryAddFileTypePredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFileType())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_FILE_TYPE).as(String.class), "%"+condition.getFileType()+"%"));
		}
	}
	private void tryAddAccessTypePredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAccessType())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_ACCESS_TYPE).as(String.class), "%"+condition.getAccessType()+"%"));
		}
	}
	private void tryAddDomainPredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDomain())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_DOMAIN).as(String.class), "%"+condition.getDomain()+"%"));
		}
	}
	private void tryAddAddressPredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAddress())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_ADDRESS).as(String.class), "%"+condition.getAddress()+"%"));
		}
	}
	private void tryAddFileVersionPredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFileVersion())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_FILE_VERSION).as(String.class), "%"+condition.getFileVersion()+"%"));
		}
	}
	private void tryAddNexusTypePredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNexusType())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_NEXUS_TYPE).as(String.class), "%"+condition.getNexusType()+"%"));
		}
	}
	private void tryAddNexusRidPredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if (null != condition.getNexusRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAttachment.PROPERTY_NEXUS_RID).as(Long.class), condition.getNexusRid()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAttachment.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddCmodifyUcodePredicate(List<Predicate> predicateList, Root<DevpOpsAttachment> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCmodifyUcode())){
			predicateList.add(cb.like(root.get(DevpOpsAttachment.PROPERTY_CMODIFY_UCODE).as(String.class), "%"+condition.getCmodifyUcode()+"%"));
		}
	}
}


