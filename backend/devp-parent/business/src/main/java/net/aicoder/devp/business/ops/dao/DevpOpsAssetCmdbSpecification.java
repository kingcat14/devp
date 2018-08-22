package net.aicoder.devp.business.ops.dao;

import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbCondition;
import net.aicoder.devp.business.ops.domain.DevpOpsAssetCmdb;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpOpsAssetCmdbSpecification implements Specification<DevpOpsAssetCmdb>{

	private DevpOpsAssetCmdbCondition condition;

	public DevpOpsAssetCmdbSpecification(DevpOpsAssetCmdbCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpOpsAssetCmdb> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		tryAddHardwareModelPredicate(predicateList, root, cb);
		tryAddSoftwareModelPredicate(predicateList, root, cb);
		tryAddVersionPredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddCreateDatePredicate(predicateList, root, cb);
		tryAddExpireDatePredicate(predicateList, root, cb);
		tryAddAssetProjectPredicate(predicateList, root, cb);
		tryAddAssetAreaPredicate(predicateList, root, cb);
		tryAddAssetLocationPredicate(predicateList, root, cb);
		tryAddIntAccessAddrPredicate(predicateList, root, cb);
		tryAddExtAccessAddrPredicate(predicateList, root, cb);
		tryAddAcquisitionModePredicate(predicateList, root, cb);
		tryAddAcquisitionDescPredicate(predicateList, root, cb);
		tryAddAssetDeptPredicate(predicateList, root, cb);
		tryAddAssetManagerPredicate(predicateList, root, cb);
		tryAddUseDeptPredicate(predicateList, root, cb);
		tryAddUseManagerPredicate(predicateList, root, cb);
		tryAddOpsDeptPredicate(predicateList, root, cb);
		tryAddOpsManagerPredicate(predicateList, root, cb);
		tryAddBizLinePredicate(predicateList, root, cb);
		tryAddBizManagerPredicate(predicateList, root, cb);
		tryAddGoliveDatePredicate(predicateList, root, cb);
		tryAddMajorCustPredicate(predicateList, root, cb);
		tryAddCustManagerPredicate(predicateList, root, cb);
		tryAddCustUsagePredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddPrdTidPredicate(predicateList, root, cb);
		tryAddPrdRidPredicate(predicateList, root, cb);
		tryAddParasCodePredicate(predicateList, root, cb);
		tryAddCreateUcodePredicate(predicateList, root, cb);
		tryAddCreateUnamePredicate(predicateList, root, cb);
		tryAddModifyUcodePredicate(predicateList, root, cb);
		tryAddModifyUnamePredicate(predicateList, root, cb);
		tryAddAcquisitionProviderPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAssetCmdb.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsAssetCmdb.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsAssetCmdb.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddEtypePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEtype())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_ETYPE).as(String.class), "%"+condition.getEtype()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRecordStatePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){

		if (null != condition.getRecordState() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAssetCmdb.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordState()));
		}

		if (null != condition.getRecordStateMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsAssetCmdb.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMax()));
		}

		if (null != condition.getRecordStateMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsAssetCmdb.PROPERTY_RECORD_STATE).as(Integer.class), condition.getRecordStateMin()));
		}
	}
	private void tryAddTypeCodePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeCode())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_TYPE_CODE).as(String.class), "%"+condition.getTypeCode()+"%"));
		}
	}
	private void tryAddTypeNamePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTypeName())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_TYPE_NAME).as(String.class), "%"+condition.getTypeName()+"%"));
		}
	}
	private void tryAddStereotypePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStereotype())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_STEREOTYPE).as(String.class), "%"+condition.getStereotype()+"%"));
		}
	}
	private void tryAddScopePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getScope())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_SCOPE).as(String.class), "%"+condition.getScope()+"%"));
		}
	}
	private void tryAddHardwareModelPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getHardwareModel())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_HARDWARE_MODEL).as(String.class), "%"+condition.getHardwareModel()+"%"));
		}
	}
	private void tryAddSoftwareModelPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSoftwareModel())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_SOFTWARE_MODEL).as(String.class), "%"+condition.getSoftwareModel()+"%"));
		}
	}
	private void tryAddVersionPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getVersion())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_VERSION).as(String.class), "%"+condition.getVersion()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddCreateDatePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){

		if (null != condition.getCreateDate() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAssetCmdb.PROPERTY_CREATE_DATE).as(Date.class), condition.getCreateDate()));
		}

		if (null != condition.getCreateDateStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsAssetCmdb.PROPERTY_CREATE_DATE).as(Date.class), condition.getCreateDateStart()));
		}

		if (null != condition.getCreateDateEnd() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsAssetCmdb.PROPERTY_CREATE_DATE).as(Date.class), condition.getCreateDateEnd()));
		}
	}
	private void tryAddExpireDatePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){

		if (null != condition.getExpireDate() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAssetCmdb.PROPERTY_EXPIRE_DATE).as(Date.class), condition.getExpireDate()));
		}

		if (null != condition.getExpireDateStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsAssetCmdb.PROPERTY_EXPIRE_DATE).as(Date.class), condition.getExpireDateStart()));
		}

		if (null != condition.getExpireDateEnd() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsAssetCmdb.PROPERTY_EXPIRE_DATE).as(Date.class), condition.getExpireDateEnd()));
		}
	}
	private void tryAddAssetProjectPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetProject())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_ASSET_PROJECT).as(String.class), "%"+condition.getAssetProject()+"%"));
		}
	}
	private void tryAddAssetAreaPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetArea())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_ASSET_AREA).as(String.class), "%"+condition.getAssetArea()+"%"));
		}
	}
	private void tryAddAssetLocationPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetLocation())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_ASSET_LOCATION).as(String.class), "%"+condition.getAssetLocation()+"%"));
		}
	}
	private void tryAddIntAccessAddrPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getIntAccessAddr())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_INT_ACCESS_ADDR).as(String.class), "%"+condition.getIntAccessAddr()+"%"));
		}
	}
	private void tryAddExtAccessAddrPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getExtAccessAddr())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_EXT_ACCESS_ADDR).as(String.class), "%"+condition.getExtAccessAddr()+"%"));
		}
	}
	private void tryAddAcquisitionModePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAcquisitionMode())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_ACQUISITION_MODE).as(String.class), "%"+condition.getAcquisitionMode()+"%"));
		}
	}
	private void tryAddAcquisitionDescPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAcquisitionDesc())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_ACQUISITION_DESC).as(String.class), "%"+condition.getAcquisitionDesc()+"%"));
		}
	}
	private void tryAddAssetDeptPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetDept())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_ASSET_DEPT).as(String.class), "%"+condition.getAssetDept()+"%"));
		}
	}
	private void tryAddAssetManagerPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAssetManager())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_ASSET_MANAGER).as(String.class), "%"+condition.getAssetManager()+"%"));
		}
	}
	private void tryAddUseDeptPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUseDept())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_USE_DEPT).as(String.class), "%"+condition.getUseDept()+"%"));
		}
	}
	private void tryAddUseManagerPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUseManager())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_USE_MANAGER).as(String.class), "%"+condition.getUseManager()+"%"));
		}
	}
	private void tryAddOpsDeptPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getOpsDept())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_OPS_DEPT).as(String.class), "%"+condition.getOpsDept()+"%"));
		}
	}
	private void tryAddOpsManagerPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getOpsManager())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_OPS_MANAGER).as(String.class), "%"+condition.getOpsManager()+"%"));
		}
	}
	private void tryAddBizLinePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBizLine())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_BIZ_LINE).as(String.class), "%"+condition.getBizLine()+"%"));
		}
	}
	private void tryAddBizManagerPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getBizManager())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_BIZ_MANAGER).as(String.class), "%"+condition.getBizManager()+"%"));
		}
	}
	private void tryAddGoliveDatePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){

		if (null != condition.getGoliveDate() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAssetCmdb.PROPERTY_GOLIVE_DATE).as(Date.class), condition.getGoliveDate()));
		}

		if (null != condition.getGoliveDateStart() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsAssetCmdb.PROPERTY_GOLIVE_DATE).as(Date.class), condition.getGoliveDateStart()));
		}

		if (null != condition.getGoliveDateEnd() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsAssetCmdb.PROPERTY_GOLIVE_DATE).as(Date.class), condition.getGoliveDateEnd()));
		}
	}
	private void tryAddMajorCustPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMajorCust())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_MAJOR_CUST).as(String.class), "%"+condition.getMajorCust()+"%"));
		}
	}
	private void tryAddCustManagerPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCustManager())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_CUST_MANAGER).as(String.class), "%"+condition.getCustManager()+"%"));
		}
	}
	private void tryAddCustUsagePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCustUsage())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_CUST_USAGE).as(String.class), "%"+condition.getCustUsage()+"%"));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddPrdTidPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){

		if (null != condition.getPrdTid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAssetCmdb.PROPERTY_PRD_TID).as(Long.class), condition.getPrdTid()));
		}

		if (null != condition.getPrdTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsAssetCmdb.PROPERTY_PRD_TID).as(Long.class), condition.getPrdTidMax()));
		}

		if (null != condition.getPrdTidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsAssetCmdb.PROPERTY_PRD_TID).as(Long.class), condition.getPrdTidMin()));
		}
	}
	private void tryAddPrdRidPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){

		if (null != condition.getPrdRid() ) {
			predicateList.add(cb.equal(root.get(DevpOpsAssetCmdb.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRid()));
		}

		if (null != condition.getPrdRidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpOpsAssetCmdb.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMax()));
		}

		if (null != condition.getPrdRidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpOpsAssetCmdb.PROPERTY_PRD_RID).as(Long.class), condition.getPrdRidMin()));
		}
	}
	private void tryAddParasCodePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParasCode())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_PARAS_CODE).as(String.class), "%"+condition.getParasCode()+"%"));
		}
	}
	private void tryAddCreateUcodePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUcode())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_CREATE_UCODE).as(String.class), "%"+condition.getCreateUcode()+"%"));
		}
	}
	private void tryAddCreateUnamePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCreateUname())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_CREATE_UNAME).as(String.class), "%"+condition.getCreateUname()+"%"));
		}
	}
	private void tryAddModifyUcodePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUcode())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_MODIFY_UCODE).as(String.class), "%"+condition.getModifyUcode()+"%"));
		}
	}
	private void tryAddModifyUnamePredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModifyUname())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_MODIFY_UNAME).as(String.class), "%"+condition.getModifyUname()+"%"));
		}
	}
	private void tryAddAcquisitionProviderPredicate(List<Predicate> predicateList, Root<DevpOpsAssetCmdb> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAcquisitionProvider())){
			predicateList.add(cb.like(root.get(DevpOpsAssetCmdb.PROPERTY_ACQUISITION_PROVIDER).as(String.class), "%"+condition.getAcquisitionProvider()+"%"));
		}
	}
}


