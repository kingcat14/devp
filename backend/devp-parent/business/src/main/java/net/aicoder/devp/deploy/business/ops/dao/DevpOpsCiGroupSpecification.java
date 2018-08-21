package net.aicoder.devp.deploy.business.ops.dao;

import net.aicoder.devp.deploy.business.ops.dto.DevpOpsCiGroupCondition;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsCiGroup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpOpsCiGroupSpecification implements Specification<DevpOpsCiGroup>{

	private DevpOpsCiGroupCondition condition;

	public DevpOpsCiGroupSpecification(DevpOpsCiGroupCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpOpsCiGroup> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddGroupRidPredicate(predicateList, root, cb);
		tryAddCiRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddParasCodePredicate(predicateList, root, cb);
		tryAddCmodifyUcodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsCiGroup.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpOpsCiGroup.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpOpsCiGroup.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpOpsCiGroup.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpOpsCiGroup.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpOpsCiGroup.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpOpsCiGroup.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddTypeCodePredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeCode())){
			predicateList.add(cb.like(root.get(DevpOpsCiGroup.PROPERTY_TYPE_CODE).as(String.class), "%"+condition.getTypeCode()+"%"));
		}
	}
	private void tryAddTypeNamePredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeName())){
			predicateList.add(cb.like(root.get(DevpOpsCiGroup.PROPERTY_TYPE_NAME).as(String.class), "%"+condition.getTypeName()+"%"));
		}
	}
	private void tryAddGroupRidPredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if (null != condition.getGroupRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsCiGroup.PROPERTY_GROUP_RID).as(Long.class), condition.getGroupRid()));
		}
	}
	private void tryAddCiRidPredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if (null != condition.getCiRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsCiGroup.PROPERTY_CI_RID).as(Long.class), condition.getCiRid()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpOpsCiGroup.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddParasCodePredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParasCode())){
			predicateList.add(cb.like(root.get(DevpOpsCiGroup.PROPERTY_PARAS_CODE).as(String.class), "%"+condition.getParasCode()+"%"));
		}
	}
	private void tryAddCmodifyUcodePredicate(List<Predicate> predicateList, Root<DevpOpsCiGroup> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCmodifyUcode())){
			predicateList.add(cb.like(root.get(DevpOpsCiGroup.PROPERTY_CMODIFY_UCODE).as(String.class), "%"+condition.getCmodifyUcode()+"%"));
		}
	}
}


