package net.aicoder.devp.product.business.product.dao;

import net.aicoder.devp.product.business.product.domain.DevpPrdPerson;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DevpPrdPersonSpecification implements Specification<DevpPrdPerson>{

	DevpPrdPersonCondition condition;

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
		tryAddModifyUcodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdPerson.PROPERTY_TID).as(Long.class), condition.getTid()));
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
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpPrdPerson.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddUidPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if (null != condition.getUid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdPerson.PROPERTY_UID).as(Long.class), condition.getUid()));
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
	}
	private void tryAddOrgRidPredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if (null != condition.getOrgRid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdPerson.PROPERTY_ORG_RID).as(Long.class), condition.getOrgRid()));
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
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpPrdPerson> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpPrdPerson.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
}


