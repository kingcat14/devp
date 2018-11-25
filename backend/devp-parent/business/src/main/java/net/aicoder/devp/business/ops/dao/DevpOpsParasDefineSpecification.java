package net.aicoder.devp.business.ops.dao;

import net.aicoder.devp.business.ops.domain.DevpOpsParasDefine;
import net.aicoder.devp.business.ops.dto.DevpOpsParasDefineCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DevpOpsParasDefineSpecification implements Specification<DevpOpsParasDefine>{

	private DevpOpsParasDefineCondition condition;

	public DevpOpsParasDefineSpecification(DevpOpsParasDefineCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpOpsParasDefine> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddContentPredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCreateUnamePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);
		tryAddModifyUnamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsParasDefine.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpOpsParasDefine.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpOpsParasDefine.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpOpsParasDefine.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpOpsParasDefine.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpOpsParasDefine.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpOpsParasDefine.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsParasDefine.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsParasDefine.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddContentPredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getContent())){
			predicateList.add(cb.like(root.get(DevpOpsParasDefine.PROPERTY_CONTENT).as(String.class), "%"+condition.getContent()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpOpsParasDefine.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpOpsParasDefine.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpOpsParasDefine.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpOpsParasDefine.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpOpsParasDefine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpOpsParasDefine.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
}


