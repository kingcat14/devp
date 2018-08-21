package net.aicoder.devp.product.business.product.dao;

import net.aicoder.devp.product.business.product.domain.DevpPrdProduct;
import net.aicoder.devp.product.business.product.dto.DevpPrdProductCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DevpPrdProductSpecification implements Specification<DevpPrdProduct>{

	DevpPrdProductCondition condition;

	public DevpPrdProductSpecification(DevpPrdProductCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpPrdProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddAliasPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddStereotypePredicate(predicateList, root, cb);
		tryAddScopePredicate(predicateList, root, cb);
		tryAddPrdDeptPredicate(predicateList, root, cb);
		tryAddPrdOwnerPredicate(predicateList, root, cb);
		tryAddDevManagerPredicate(predicateList, root, cb);
		tryAddOpsManagerPredicate(predicateList, root, cb);
		tryAddBizLinePredicate(predicateList, root, cb);
		tryAddBizManagerPredicate(predicateList, root, cb);
		tryAddGolivePredicate(predicateList, root, cb);
		tryAddMajorCustPredicate(predicateList, root, cb);
		tryAddCustManagerPredicate(predicateList, root, cb);
		tryAddCustUsagePredicate(predicateList, root, cb);
		tryAddAcquisitionModePredicate(predicateList, root, cb);
		tryAddAcquisitionDescPredicate(predicateList, root, cb);
		tryAddVersionPredicate(predicateList, root, cb);
		tryAddPhasePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddRecordStatePredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCmodifyUcodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpPrdProduct.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddStereotypePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStereotype())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_STEREOTYPE).as(String.class), "%"+condition.getStereotype()+"%"));
		}
	}
	private void tryAddScopePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScope())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_SCOPE).as(String.class), "%"+condition.getScope()+"%"));
		}
	}
	private void tryAddPrdDeptPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPrdDept())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_PRD_DEPT).as(String.class), "%"+condition.getPrdDept()+"%"));
		}
	}
	private void tryAddPrdOwnerPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPrdOwner())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_PRD_OWNER).as(String.class), "%"+condition.getPrdOwner()+"%"));
		}
	}
	private void tryAddDevManagerPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDevManager())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_DEV_MANAGER).as(String.class), "%"+condition.getDevManager()+"%"));
		}
	}
	private void tryAddOpsManagerPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getOpsManager())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_OPS_MANAGER).as(String.class), "%"+condition.getOpsManager()+"%"));
		}
	}
	private void tryAddBizLinePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBizLine())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_BIZ_LINE).as(String.class), "%"+condition.getBizLine()+"%"));
		}
	}
	private void tryAddBizManagerPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBizManager())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_BIZ_MANAGER).as(String.class), "%"+condition.getBizManager()+"%"));
		}
	}
	private void tryAddGolivePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){

		if (null != condition.getGolive() ) {
			predicateList.add(cb.equal(root.get(DevpPrdProduct.PROPERTY_GOLIVE).as(Date.class), condition.getGolive()));
		}

		if (null != condition.getGoliveStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpPrdProduct.PROPERTY_GOLIVE).as(Date.class), condition.getGoliveStart()));
		}

		if (null != condition.getGoliveEnd() ) {
			predicateList.add(cb.lessThan(root.get(DevpPrdProduct.PROPERTY_GOLIVE).as(Date.class), condition.getGoliveEnd()));
		}

	}
	private void tryAddMajorCustPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMajorCust())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_MAJOR_CUST).as(String.class), "%"+condition.getMajorCust()+"%"));
		}
	}
	private void tryAddCustManagerPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCustManager())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_CUST_MANAGER).as(String.class), "%"+condition.getCustManager()+"%"));
		}
	}
	private void tryAddCustUsagePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCustUsage())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_CUST_USAGE).as(String.class), "%"+condition.getCustUsage()+"%"));
		}
	}
	private void tryAddAcquisitionModePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAcquisitionMode())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_ACQUISITION_MODE).as(String.class), "%"+condition.getAcquisitionMode()+"%"));
		}
	}
	private void tryAddAcquisitionDescPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAcquisitionDesc())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_ACQUISITION_DESC).as(String.class), "%"+condition.getAcquisitionDesc()+"%"));
		}
	}
	private void tryAddVersionPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getVersion())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_VERSION).as(String.class), "%"+condition.getVersion()+"%"));
		}
	}
	private void tryAddPhasePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPhase())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_PHASE).as(String.class), "%"+condition.getPhase()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpPrdProduct.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCmodifyUcodePredicate(List<Predicate> predicateList, Root<DevpPrdProduct> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCmodifyUcode())){
			predicateList.add(cb.like(root.get(DevpPrdProduct.PROPERTY_CMODIFY_UCODE).as(String.class), "%"+condition.getCmodifyUcode()+"%"));
		}
	}
}


