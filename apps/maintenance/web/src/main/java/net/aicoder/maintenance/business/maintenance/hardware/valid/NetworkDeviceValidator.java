package net.aicoder.maintenance.business.maintenance.hardware.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.maintenance.business.hardware.dto.NetworkDeviceAddDto;
import net.aicoder.maintenance.business.hardware.dto.NetworkDeviceEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class NetworkDeviceValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof NetworkDeviceAddDto){
            this.validateAddDto((NetworkDeviceAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param networkDevice 网络设备
     * @param errors
     */
	public void validateAddDto(NetworkDeviceAddDto networkDevice, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == networkDevice.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户id不能为空");
		}
		if(StringUtils.isEmpty(networkDevice.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "名称不能为空");
		}
       

		//验证长度
		if(StringUtils.length(networkDevice.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(networkDevice.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(networkDevice.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(networkDevice.getAlias()) > 255){
			errors.rejectValue("alias", null, "别名最长255个字符");
		}
		if(StringUtils.length(networkDevice.getTypeCode()) > 255){
			errors.rejectValue("typeCode", null, "类型最长255个字符");
		}
		if(StringUtils.length(networkDevice.getHardwareModel()) > 255){
			errors.rejectValue("hardwareModel", null, "硬件型号最长255个字符");
		}
		if(StringUtils.length(networkDevice.getSoftwareModel()) > 255){
			errors.rejectValue("softwareModel", null, "软件型号最长255个字符");
		}
		if(StringUtils.length(networkDevice.getAssetProject()) > 255){
			errors.rejectValue("assetProject", null, "所属项目最长255个字符");
		}
		if(StringUtils.length(networkDevice.getAssetArea()) > 255){
			errors.rejectValue("assetArea", null, "所属区域最长255个字符");
		}
		if(StringUtils.length(networkDevice.getAssetLocation()) > 255){
			errors.rejectValue("assetLocation", null, "资产位置最长255个字符");
		}
		if(StringUtils.length(networkDevice.getIntAccessAddr()) > 255){
			errors.rejectValue("intAccessAddr", null, "内部访问地址最长255个字符");
		}
		if(StringUtils.length(networkDevice.getExtAccessAddr()) > 255){
			errors.rejectValue("extAccessAddr", null, "外部访问地址最长255个字符");
		}
		if(StringUtils.length(networkDevice.getAcquisitionMode()) > 255){
			errors.rejectValue("acquisitionMode", null, "获取方式最长255个字符");
		}
		if(StringUtils.length(networkDevice.getAcquisitionDesc()) > 255){
			errors.rejectValue("acquisitionDesc", null, "获取方式说明最长255个字符");
		}
		if(StringUtils.length(networkDevice.getAssetDept()) > 255){
			errors.rejectValue("assetDept", null, "归属部门最长255个字符");
		}
		if(StringUtils.length(networkDevice.getAssetManager()) > 255){
			errors.rejectValue("assetManager", null, "资产负责人最长255个字符");
		}
		if(StringUtils.length(networkDevice.getUseDept()) > 255){
			errors.rejectValue("useDept", null, "使用部门最长255个字符");
		}
		if(StringUtils.length(networkDevice.getUseManager()) > 255){
			errors.rejectValue("useManager", null, "使用负责人最长255个字符");
		}
		if(StringUtils.length(networkDevice.getOpsDept()) > 255){
			errors.rejectValue("opsDept", null, "维护部门最长255个字符");
		}
		if(StringUtils.length(networkDevice.getOpsManager()) > 255){
			errors.rejectValue("opsManager", null, "维护负责人最长255个字符");
		}
		if(StringUtils.length(networkDevice.getBizLine()) > 255){
			errors.rejectValue("bizLine", null, "业务线最长255个字符");
		}
		if(StringUtils.length(networkDevice.getBizManager()) > 255){
			errors.rejectValue("bizManager", null, "业务代表最长255个字符");
		}
		if(StringUtils.length(networkDevice.getMajorCust()) > 255){
			errors.rejectValue("majorCust", null, "主要客户最长255个字符");
		}
		if(StringUtils.length(networkDevice.getCustManager()) > 255){
			errors.rejectValue("custManager", null, "客户代表最长255个字符");
		}
		if(StringUtils.length(networkDevice.getCustUsage()) > 255){
			errors.rejectValue("custUsage", null, "使用情况最长255个字符");
		}
		if(StringUtils.length(networkDevice.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(networkDevice.getParasCode()) > 255){
			errors.rejectValue("parasCode", null, "参数定义标识最长255个字符");
		}
		if(StringUtils.length(networkDevice.getAcquisitionProvider()) > 255){
			errors.rejectValue("acquisitionProvider", null, "供应商最长255个字符");
		}
	}
}