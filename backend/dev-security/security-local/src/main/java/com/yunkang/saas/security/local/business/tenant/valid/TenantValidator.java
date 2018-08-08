package com.yunkang.saas.security.local.business.tenant.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.tenant.business.tenant.dto.TenantAddDto;
import com.yunkang.saas.tenant.business.tenant.dto.TenantEditDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class TenantValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(TenantAddDto.class.equals(aClass))
			return true;
		if(TenantEditDto.class.equals(aClass))
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
	    if(obj instanceof TenantAddDto){
            this.validateAddDto((TenantAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param tenant 租户
     * @param errors
     */
	public void validateAddDto(TenantAddDto tenant, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(tenant.getTenantCode())){
			errors.rejectValue("tenantCode", "EMPTY_TENANT_CODE", "租户代号不能为空");
		}
       
		if (null == tenant.getTenantType() ) {
			errors.rejectValue("tenantType", "EMPTY_TENANT_TYPE", "租户类型不能为空");
		}
		if(StringUtils.isEmpty(tenant.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "租户名称不能为空");
		}
       
		if (null == tenant.getStatus() ) {
			errors.rejectValue("status", "EMPTY_STATUS", "状态不能为空");
		}

		//验证长度
		if(StringUtils.length(tenant.getTenantCode()) > 255){
			errors.rejectValue("tenantCode", null, "租户代号最长255个字符");
		}
		if(StringUtils.length(tenant.getName()) > 255){
			errors.rejectValue("name", null, "租户名称最长255个字符");
		}
		if(StringUtils.length(tenant.getCountry()) > 255){
			errors.rejectValue("country", null, "国家最长255个字符");
		}
		if(StringUtils.length(tenant.getProvince()) > 255){
			errors.rejectValue("province", null, "省份最长255个字符");
		}
		if(StringUtils.length(tenant.getCity()) > 255){
			errors.rejectValue("city", null, "市、县最长255个字符");
		}
		if(StringUtils.length(tenant.getAddress()) > 255){
			errors.rejectValue("address", null, "详细地址最长255个字符");
		}
		if(StringUtils.length(tenant.getFax()) > 255){
			errors.rejectValue("fax", null, "传真最长255个字符");
		}
		if(StringUtils.length(tenant.getTelephoneNo()) > 255){
			errors.rejectValue("telephoneNo", null, "联系电话最长255个字符");
		}
		if(StringUtils.length(tenant.getCrmCode()) > 255){
			errors.rejectValue("crmCode", null, "CRM系统代码最长255个字符");
		}
		if(StringUtils.length(tenant.getPrefixDomainName()) > 255){
			errors.rejectValue("prefixDomainName", null, "域名前缀最长255个字符");
		}
	}
}