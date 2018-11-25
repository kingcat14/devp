package net.aicoder.devp.business.sys.dao;

import net.aicoder.devp.business.sys.domain.DevpSysIdeRef;
import net.aicoder.devp.business.sys.dto.DevpSysIdeRefCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DevpSysIdeRefSpecification implements Specification<DevpSysIdeRef>{

	private DevpSysIdeRefCondition condition;

	public DevpSysIdeRefSpecification(DevpSysIdeRefCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysIdeRef> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddVersionPredicate(predicateList, root, cb);
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddIdeRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddNexusEtypePredicate(predicateList, root, cb);
		tryAddRefPrdRidPredicate(predicateList, root, cb);
		tryAddRefElmRidPredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCreateUnamePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);
		tryAddModifyUnamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeRef.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeRef.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysIdeRef.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysIdeRef.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddSubTypePredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubType())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_SUB_TYPE).as(String.class), "%"+condition.getSubType()+"%"));
		}
	}
	private void tryAddVersionPredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getVersion())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_VERSION).as(String.class), "%"+condition.getVersion()+"%"));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){

		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeRef.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}

		if (null != condition.getPrdRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysIdeRef.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMax()));
		}

		if (null != condition.getPrdRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysIdeRef.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMin()));
		}
	}
	private void tryAddIdeRidPredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){

		if (null != condition.getIdeRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeRef.PROPERTY_IDE_RID).as(Long.class), condition.getIdeRid()));
		}

		if (null != condition.getIdeRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysIdeRef.PROPERTY_IDE_RID).as(Long.class), condition.getIdeRidMax()));
		}

		if (null != condition.getIdeRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysIdeRef.PROPERTY_IDE_RID).as(Long.class), condition.getIdeRidMin()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeRef.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysIdeRef.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysIdeRef.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddNexusEtypePredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNexusEtype())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_NEXUS_ETYPE).as(String.class), "%"+condition.getNexusEtype()+"%"));
		}
	}
	private void tryAddRefPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){

		if (null != condition.getRefPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeRef.PROPERTY_REF_PRD_RID).as(Long.class), condition.getRefPrdRid()));
		}

		if (null != condition.getRefPrdRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysIdeRef.PROPERTY_REF_PRD_RID).as(Long.class), condition.getRefPrdRidMax()));
		}

		if (null != condition.getRefPrdRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysIdeRef.PROPERTY_REF_PRD_RID).as(Long.class), condition.getRefPrdRidMin()));
		}
	}
	private void tryAddRefElmRidPredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){

		if (null != condition.getRefElmRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeRef.PROPERTY_REF_ELM_RID).as(Long.class), condition.getRefElmRid()));
		}

		if (null != condition.getRefElmRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysIdeRef.PROPERTY_REF_ELM_RID).as(Long.class), condition.getRefElmRidMax()));
		}

		if (null != condition.getRefElmRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysIdeRef.PROPERTY_REF_ELM_RID).as(Long.class), condition.getRefElmRidMin()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpSysIdeRef> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpSysIdeRef.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


