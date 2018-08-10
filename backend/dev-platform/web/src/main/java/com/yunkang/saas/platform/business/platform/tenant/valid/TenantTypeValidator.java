package com.yunkang.saas.platform.business.platform.tenant.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.platform.tenant.dto.TenantTypeAddDto;
import com.yunkang.saas.platform.business.platform.tenant.dto.TenantTypeEditDto;
import com.yunkang.saas.platform.business.platform.tenant.domain.TenantType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TenantTypeValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(TenantTypeAddDto.class.equals(aClass))
			return true;
		if(TenantTypeEditDto.class.equals(aClass))
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
	    if(obj instanceof TenantTypeAddDto){
            this.validateTenantTypeAddDto((TenantTypeAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param tenantType 租户类型
     * @param errors
     */
	public void validateTenantTypeAddDto(TenantTypeAddDto tenantType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(tenantType.getTenantTypeCode())){
			errors.rejectValue(TenantType.PROPERTY_TENANT_TYPE_CODE, "EMPTY_"+TenantType.PROPERTY_TENANT_TYPE_CODE, "租户类型编码不能为空");
		}
		if(StringUtils.isEmpty(tenantType.getName())){
			errors.rejectValue(TenantType.PROPERTY_NAME, "EMPTY_"+TenantType.PROPERTY_NAME, "租户类型名称不能为空");
		}

		//验证长度
		if(StringUtils.length(tenantType.getTenantTypeCode()) > 255){
			errors.rejectValue(TenantType.PROPERTY_TENANT_TYPE_CODE,null,"租户类型编码最长255个字符");
		}
		if(StringUtils.length(tenantType.getName()) > 255){
			errors.rejectValue(TenantType.PROPERTY_NAME,null,"租户类型名称最长255个字符");
		}
	}
}