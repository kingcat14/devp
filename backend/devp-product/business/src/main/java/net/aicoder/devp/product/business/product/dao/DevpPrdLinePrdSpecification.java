package net.aicoder.devp.product.business.product.dao;

import net.aicoder.devp.product.business.product.dto.DevpPrdLinePrdCondition;
import net.aicoder.devp.product.business.product.domain.DevpPrdLinePrd;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpPrdLinePrdSpecification implements Specification<DevpPrdLinePrd>{

	DevpPrdLinePrdCondition condition;

	public DevpPrdLinePrdSpecification(DevpPrdLinePrdCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpPrdLinePrd> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddAliasPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddLineRidPredicate(predicateList, root, cb);
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddStereotypePredicate(predicateList, root, cb);
		tryAddScopePredicate(predicateList, root, cb);
		tryAddRecordStatePredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCmodifyUcodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdLinePrd.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpPrdLinePrd.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpPrdLinePrd.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpPrdLinePrd.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpPrdLinePrd.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddLineRidPredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if (null != condition.getLineRid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdLinePrd.PROPERTY_LINE_RID).as(Long.class), condition.getLineRid()));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdLinePrd.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(DevpPrdLinePrd.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpPrdLinePrd.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddStereotypePredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStereotype())){
			predicateList.add(cb.like(root.get(DevpPrdLinePrd.PROPERTY_STEREOTYPE).as(String.class), "%"+condition.getStereotype()+"%"));
		}
	}
	private void tryAddScopePredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScope())){
			predicateList.add(cb.like(root.get(DevpPrdLinePrd.PROPERTY_SCOPE).as(String.class), "%"+condition.getScope()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpPrdLinePrd.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpPrdLinePrd.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCmodifyUcodePredicate(List<Predicate> predicateList, Root<DevpPrdLinePrd> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCmodifyUcode())){
			predicateList.add(cb.like(root.get(DevpPrdLinePrd.PROPERTY_CMODIFY_UCODE).as(String.class), "%"+condition.getCmodifyUcode()+"%"));
		}
	}
}


