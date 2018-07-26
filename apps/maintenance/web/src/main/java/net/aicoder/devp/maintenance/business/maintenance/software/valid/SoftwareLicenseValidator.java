package net.aicoder.devp.maintenance.business.maintenance.software.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.maintenance.business.software.dto.SoftwareLicenseAddDto;
import net.aicoder.devp.maintenance.business.software.dto.SoftwareLicenseEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SoftwareLicenseValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(SoftwareLicenseAddDto.class.equals(aClass))
			return true;
		if(SoftwareLicenseEditDto.class.equals(aClass))
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
	    if(obj instanceof SoftwareLicenseAddDto){
            this.validateAddDto((SoftwareLicenseAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param softwareLicense 服务许可
     * @param errors
     */
	public void validateAddDto(SoftwareLicenseAddDto softwareLicense, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == softwareLicense.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(softwareLicense.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(softwareLicense.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "名称不能为空");
		}
       

		//验证长度
		if(StringUtils.length(softwareLicense.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getAlias()) > 255){
			errors.rejectValue("alias", null, "别名最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getDescription()) > 255){
			errors.rejectValue("description", null, "描述最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getTypeCode()) > 255){
			errors.rejectValue("typeCode", null, "类型代码最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getTypeName()) > 255){
			errors.rejectValue("typeName", null, "类型名称最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getScope()) > 255){
			errors.rejectValue("scope", null, "范围最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getHardwareModel()) > 255){
			errors.rejectValue("hardwareModel", null, "硬件型号最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getSoftwareModel()) > 255){
			errors.rejectValue("softwareModel", null, "软件型号最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getVersion()) > 255){
			errors.rejectValue("version", null, "版本最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getAssetProject()) > 255){
			errors.rejectValue("assetProject", null, "所属项目最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getAssetArea()) > 255){
			errors.rejectValue("assetArea", null, "所属区域最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getAssetLocation()) > 255){
			errors.rejectValue("assetLocation", null, "资产位置最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getIntAccessAddr()) > 255){
			errors.rejectValue("intAccessAddr", null, "内部访问地址最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getExtAccessAddr()) > 255){
			errors.rejectValue("extAccessAddr", null, "外部访问地址最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getAcquisitionMode()) > 255){
			errors.rejectValue("acquisitionMode", null, "获取方式最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getAcquisitionDesc()) > 255){
			errors.rejectValue("acquisitionDesc", null, "获取方式说明最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getAssetDept()) > 255){
			errors.rejectValue("assetDept", null, "归属部门最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getAssetManager()) > 255){
			errors.rejectValue("assetManager", null, "资产负责人最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getUseDept()) > 255){
			errors.rejectValue("useDept", null, "使用部门最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getUseManager()) > 255){
			errors.rejectValue("useManager", null, "使用负责人最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getOpsDept()) > 255){
			errors.rejectValue("opsDept", null, "维护部门最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getOpsManager()) > 255){
			errors.rejectValue("opsManager", null, "维护负责人最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getBizLine()) > 255){
			errors.rejectValue("bizLine", null, "业务线最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getBizManager()) > 255){
			errors.rejectValue("bizManager", null, "业务代表最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getMajorCust()) > 255){
			errors.rejectValue("majorCust", null, "主要客户最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getCustManager()) > 255){
			errors.rejectValue("custManager", null, "客户代表最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getCustUsage()) > 255){
			errors.rejectValue("custUsage", null, "使用情况最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(softwareLicense.getParasCode()) > 255){
			errors.rejectValue("parasCode", null, "参数定义标识最长255个字符");
		}
	}
}