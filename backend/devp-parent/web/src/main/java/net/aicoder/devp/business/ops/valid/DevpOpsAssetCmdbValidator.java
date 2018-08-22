package net.aicoder.devp.business.ops.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.devp.business.ops.domain.DevpOpsAssetCmdb;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbEditDto;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsAssetCmdbValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpOpsAssetCmdbAddDto.class.equals(aClass))
			return true;
		if(DevpOpsAssetCmdbEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return false;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpOpsAssetCmdbAddDto){
            this.validateDevpOpsAssetCmdbAddDto((DevpOpsAssetCmdbAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpOpsAssetCmdb 资产定义
     * @param errors
     */
	public void validateDevpOpsAssetCmdbAddDto(DevpOpsAssetCmdbAddDto devpOpsAssetCmdb, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpOpsAssetCmdb.getTid() ) {
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_TID, "EMPTY_"+DevpOpsAssetCmdb.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpOpsAssetCmdb.getEtype())){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ETYPE, "EMPTY_"+DevpOpsAssetCmdb.PROPERTY_ETYPE, "元素类型不能为空");
		}
		if(StringUtils.isEmpty(devpOpsAssetCmdb.getName())){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_NAME, "EMPTY_"+DevpOpsAssetCmdb.PROPERTY_NAME, "名称不能为空");
		}

		//验证长度
		if(StringUtils.length(devpOpsAssetCmdb.getEtype()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getName()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getCode()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAlias()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ALIAS,null,"别名最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getDescription()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_DESCRIPTION,null,"描述最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getTypeCode()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_TYPE_CODE,null,"类型代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getTypeName()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_TYPE_NAME,null,"类型名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getStereotype()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getScope()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getHardwareModel()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_HARDWARE_MODEL,null,"硬件型号最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getSoftwareModel()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_SOFTWARE_MODEL,null,"软件型号最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getVersion()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getStatus()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetProject()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ASSET_PROJECT,null,"所属项目最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetArea()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ASSET_AREA,null,"所属区域最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetLocation()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ASSET_LOCATION,null,"资产位置最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getIntAccessAddr()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_INT_ACCESS_ADDR,null,"内部访问地址最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getExtAccessAddr()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_EXT_ACCESS_ADDR,null,"外部访问地址最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAcquisitionMode()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ACQUISITION_MODE,null,"获取方式最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAcquisitionDesc()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ACQUISITION_DESC,null,"获取方式说明最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetDept()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ASSET_DEPT,null,"归属部门最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetManager()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ASSET_MANAGER,null,"资产负责人最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getUseDept()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_USE_DEPT,null,"使用部门最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getUseManager()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_USE_MANAGER,null,"使用负责人最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getOpsDept()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_OPS_DEPT,null,"维护部门最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getOpsManager()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_OPS_MANAGER,null,"维护负责人最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getBizLine()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_BIZ_LINE,null,"业务线最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getBizManager()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_BIZ_MANAGER,null,"业务代表最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getMajorCust()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_MAJOR_CUST,null,"主要客户最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getCustManager()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_CUST_MANAGER,null,"客户代表最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getCustUsage()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_CUST_USAGE,null,"使用情况最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getNotes()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getParasCode()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_PARAS_CODE,null,"参数定义标识最长255个字符");
		}
	}
}