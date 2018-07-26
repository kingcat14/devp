package net.aicoder.devp.product.business.sys.dao;

import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoCondition;
import net.aicoder.devp.product.business.sys.domain.DevpSysElementInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysElementInfoSpecification implements Specification<DevpSysElementInfo>{

	private DevpSysElementInfoCondition condition;

	public DevpSysElementInfoSpecification(DevpSysElementInfoCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysElementInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddObjRidPredicate(predicateList, root, cb);
		tryAddDataTypePredicate(predicateList, root, cb);
		tryAddInfoValuePredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElementInfo.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpSysElementInfo.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddObjRidPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if (null != condition.getObjRid() ) {
			predicateList.add(cb.equal(root.get(DevpSysElementInfo.PROPERTY_OBJ_RID).as(Long.class), condition.getObjRid()));
		}
	}
	private void tryAddDataTypePredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDataType())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_DATA_TYPE).as(String.class), "%"+condition.getDataType()+"%"));
		}
	}
	private void tryAddInfoValuePredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getInfoValue())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_INFO_VALUE).as(String.class), "%"+condition.getInfoValue()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpSysElementInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpSysElementInfo.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
}


