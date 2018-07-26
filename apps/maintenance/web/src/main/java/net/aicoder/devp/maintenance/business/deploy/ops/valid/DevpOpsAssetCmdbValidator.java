package net.aicoder.devp.maintenance.business.deploy.ops.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetCmdbAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetCmdbEditDto;
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
            this.validateAddDto((DevpOpsAssetCmdbAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpOpsAssetCmdb 资产定义
     * @param errors
     */
	public void validateAddDto(DevpOpsAssetCmdbAddDto devpOpsAssetCmdb, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpOpsAssetCmdb.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpOpsAssetCmdb.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpOpsAssetCmdb.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "名称不能为空");
		}
       

		//验证长度
		if(StringUtils.length(devpOpsAssetCmdb.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAlias()) > 255){
			errors.rejectValue("alias", null, "别名最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getDescription()) > 255){
			errors.rejectValue("description", null, "描述最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getTypeCode()) > 255){
			errors.rejectValue("typeCode", null, "类型代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getTypeName()) > 255){
			errors.rejectValue("typeName", null, "类型名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getScope()) > 255){
			errors.rejectValue("scope", null, "范围最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getHardwareModel()) > 255){
			errors.rejectValue("hardwareModel", null, "硬件型号最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getSoftwareModel()) > 255){
			errors.rejectValue("softwareModel", null, "软件型号最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getVersion()) > 255){
			errors.rejectValue("version", null, "版本最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetProject()) > 255){
			errors.rejectValue("assetProject", null, "所属项目最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetArea()) > 255){
			errors.rejectValue("assetArea", null, "所属区域最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetLocation()) > 255){
			errors.rejectValue("assetLocation", null, "资产位置最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getIntAccessAddr()) > 255){
			errors.rejectValue("intAccessAddr", null, "内部访问地址最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getExtAccessAddr()) > 255){
			errors.rejectValue("extAccessAddr", null, "外部访问地址最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAcquisitionMode()) > 255){
			errors.rejectValue("acquisitionMode", null, "获取方式最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAcquisitionDesc()) > 255){
			errors.rejectValue("acquisitionDesc", null, "获取方式说明最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetDept()) > 255){
			errors.rejectValue("assetDept", null, "归属部门最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetManager()) > 255){
			errors.rejectValue("assetManager", null, "资产负责人最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getUseDept()) > 255){
			errors.rejectValue("useDept", null, "使用部门最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getUseManager()) > 255){
			errors.rejectValue("useManager", null, "使用负责人最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getOpsDept()) > 255){
			errors.rejectValue("opsDept", null, "维护部门最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getOpsManager()) > 255){
			errors.rejectValue("opsManager", null, "维护负责人最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getBizLine()) > 255){
			errors.rejectValue("bizLine", null, "业务线最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getBizManager()) > 255){
			errors.rejectValue("bizManager", null, "业务代表最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getMajorCust()) > 255){
			errors.rejectValue("majorCust", null, "主要客户最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getCustManager()) > 255){
			errors.rejectValue("custManager", null, "客户代表最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getCustUsage()) > 255){
			errors.rejectValue("custUsage", null, "使用情况最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getParasCode()) > 255){
			errors.rejectValue("parasCode", null, "参数定义标识最长255个字符");
		}
	}
}