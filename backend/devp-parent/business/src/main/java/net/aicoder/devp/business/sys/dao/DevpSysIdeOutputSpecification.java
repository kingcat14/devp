package net.aicoder.devp.business.sys.dao;

import net.aicoder.devp.business.sys.domain.DevpSysIdeOutput;
import net.aicoder.devp.business.sys.dto.DevpSysIdeOutputCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DevpSysIdeOutputSpecification implements Specification<DevpSysIdeOutput>{

	private DevpSysIdeOutputCondition condition;

	public DevpSysIdeOutputSpecification(DevpSysIdeOutputCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysIdeOutput> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddIdeRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddOutCmpRidPredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCreateUnamePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);
		tryAddModifyUnamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeOutput.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysIdeOutput.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysIdeOutput.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysIdeOutput.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysIdeOutput.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysIdeOutput.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeOutput.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysIdeOutput.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysIdeOutput.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysIdeOutput.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddSubTypePredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubType())){
			predicateList.add(cb.like(root.get(DevpSysIdeOutput.PROPERTY_SUB_TYPE).as(String.class), "%"+condition.getSubType()+"%"));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){

		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeOutput.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}

		if (null != condition.getPrdRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysIdeOutput.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMax()));
		}

		if (null != condition.getPrdRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysIdeOutput.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMin()));
		}
	}
	private void tryAddIdeRidPredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){

		if (null != condition.getIdeRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeOutput.PROPERTY_IDE_RID).as(Long.class), condition.getIdeRid()));
		}

		if (null != condition.getIdeRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysIdeOutput.PROPERTY_IDE_RID).as(Long.class), condition.getIdeRidMax()));
		}

		if (null != condition.getIdeRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysIdeOutput.PROPERTY_IDE_RID).as(Long.class), condition.getIdeRidMin()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeOutput.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysIdeOutput.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysIdeOutput.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddOutCmpRidPredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){

		if (null != condition.getOutCmpRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysIdeOutput.PROPERTY_OUT_CMP_RID).as(Long.class), condition.getOutCmpRid()));
		}

		if (null != condition.getOutCmpRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysIdeOutput.PROPERTY_OUT_CMP_RID).as(Long.class), condition.getOutCmpRidMax()));
		}

		if (null != condition.getOutCmpRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysIdeOutput.PROPERTY_OUT_CMP_RID).as(Long.class), condition.getOutCmpRidMin()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpSysIdeOutput.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpSysIdeOutput.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpSysIdeOutput.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpSysIdeOutput> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpSysIdeOutput.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


