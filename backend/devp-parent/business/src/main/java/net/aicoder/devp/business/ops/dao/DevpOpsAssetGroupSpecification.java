package net.aicoder.devp.business.ops.dao;

import net.aicoder.devp.business.ops.domain.DevpOpsAssetGroup;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetGroupCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DevpOpsAssetGroupSpecification implements Specification<DevpOpsAssetGroup>{

	private DevpOpsAssetGroupCondition condition;

	public DevpOpsAssetGroupSpecification(DevpOpsAssetGroupCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpOpsAssetGroup> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddTypeCodePredicate(predicateList, root, cb);
		tryAddTypeNamePredicate(predicateList, root, cb);
		tryAddStereotypePredicate(predicateList, root, cb);
		tryAddScopePredicate(predicateList, root, cb);
		tryAddVersionPredicate(predicateList, root, cb);
		tryAddPhasePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddParentRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddParasCodePredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCreateUnamePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);
		tryAddModifyUnamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAssetGroup.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAssetGroup.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsAssetGroup.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsAssetGroup.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddTypeCodePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeCode())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_TYPE_CODE).as(String.class), "%"+condition.getTypeCode()+"%"));
		}
	}
	private void tryAddTypeNamePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeName())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_TYPE_NAME).as(String.class), "%"+condition.getTypeName()+"%"));
		}
	}
	private void tryAddStereotypePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStereotype())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_STEREOTYPE).as(String.class), "%"+condition.getStereotype()+"%"));
		}
	}
	private void tryAddScopePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScope())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_SCOPE).as(String.class), "%"+condition.getScope()+"%"));
		}
	}
	private void tryAddVersionPredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getVersion())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_VERSION).as(String.class), "%"+condition.getVersion()+"%"));
		}
	}
	private void tryAddPhasePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPhase())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_PHASE).as(String.class), "%"+condition.getPhase()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddParentRidPredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){

		if (null != condition.getParentRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAssetGroup.PROPERTY_PARENT_RID).as(Long.class), condition.getParentRid()));
		}

		if (null != condition.getParentRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsAssetGroup.PROPERTY_PARENT_RID).as(Long.class), condition.getParentRidMax()));
		}

		if (null != condition.getParentRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsAssetGroup.PROPERTY_PARENT_RID).as(Long.class), condition.getParentRidMin()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAssetGroup.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsAssetGroup.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsAssetGroup.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddParasCodePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParasCode())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_PARAS_CODE).as(String.class), "%"+condition.getParasCode()+"%"));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpOpsAssetGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpOpsAssetGroup.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


