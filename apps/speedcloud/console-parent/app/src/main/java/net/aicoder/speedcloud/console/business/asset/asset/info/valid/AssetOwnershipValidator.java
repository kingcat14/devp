package net.aicoder.speedcloud.console.business.asset.asset.info.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AssetOwnershipValidator implements Validator {

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
	    if(obj instanceof AssetOwnershipAddDto){
            this.validateAddDto((AssetOwnershipAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param assetOwnership IT资产归属
     * @param errors
     */
		public void validateAddDto(AssetOwnershipAddDto assetOwnership, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(assetOwnership.getName()) > 255){
			errors.rejectValue("name", null, "资产名称最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getCode()) > 255){
			errors.rejectValue("code", null, "资产代码最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getTypeCode()) > 255){
			errors.rejectValue("typeCode", null, "资产分类代码最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getTypeName()) > 255){
			errors.rejectValue("typeName", null, "资产分类名称最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getAssetDept()) > 255){
			errors.rejectValue("assetDept", null, "资产部门最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getAssetManager()) > 255){
			errors.rejectValue("assetManager", null, "资产负责人最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getUseDept()) > 255){
			errors.rejectValue("useDept", null, "使用部门最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getUseManager()) > 255){
			errors.rejectValue("useManager", null, "使用负责人最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getOpsDept()) > 255){
			errors.rejectValue("opsDept", null, "操作部门最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getOpsManager()) > 255){
			errors.rejectValue("opsManager", null, "操作负责人最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getBizLine()) > 255){
			errors.rejectValue("bizLine", null, "业务线最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getBizManager()) > 255){
			errors.rejectValue("bizManager", null, "业务负责人最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getMajorCust()) > 255){
			errors.rejectValue("majorCust", null, "主要客户最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getCustManager()) > 255){
			errors.rejectValue("custManager", null, "客户负责人最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getCustUsage()) > 255){
			errors.rejectValue("custUsage", null, "客户使用情况最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getAcquisitionProvider()) > 255){
			errors.rejectValue("acquisitionProvider", null, "供应商最长255个字符");
		}
	}
}