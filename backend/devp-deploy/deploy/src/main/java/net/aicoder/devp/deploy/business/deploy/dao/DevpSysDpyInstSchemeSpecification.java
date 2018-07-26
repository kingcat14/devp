package net.aicoder.devp.deploy.business.deploy.dao;

import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeCondition;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyInstScheme;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysDpyInstSchemeSpecification implements Specification<DevpSysDpyInstScheme>{

	private DevpSysDpyInstSchemeCondition condition;

	public DevpSysDpyInstSchemeSpecification(DevpSysDpyInstSchemeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysDpyInstScheme> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddStereotypePredicate(predicateList, root, cb);
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddResRidPredicate(predicateList, root, cb);
		tryAddInstRidPredicate(predicateList, root, cb);
		tryAddSchemeRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyInstScheme.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysDpyInstScheme.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysDpyInstScheme.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysDpyInstScheme.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysDpyInstScheme.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysDpyInstScheme.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyInstScheme.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysDpyInstScheme.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddSubTypePredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSubType())){
			predicateList.add(cb.like(root.get(DevpSysDpyInstScheme.PROPERTY_SUB_TYPE).as(String.class), "%"+condition.getSubType()+"%"));
		}
	}
	private void tryAddStereotypePredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStereotype())){
			predicateList.add(cb.like(root.get(DevpSysDpyInstScheme.PROPERTY_STEREOTYPE).as(String.class), "%"+condition.getStereotype()+"%"));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyInstScheme.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}
	}
	private void tryAddResRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if (null != condition.getResRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyInstScheme.PROPERTY_RES_RID).as(Long.class), condition.getResRid()));
		}
	}
	private void tryAddInstRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if (null != condition.getInstRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyInstScheme.PROPERTY_INST_RID).as(Long.class), condition.getInstRid()));
		}
	}
	private void tryAddSchemeRidPredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if (null != condition.getSchemeRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyInstScheme.PROPERTY_SCHEME_RID).as(Long.class), condition.getSchemeRid()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpSysDpyInstScheme> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyInstScheme.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
}


