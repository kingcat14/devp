package com.yunkang.saas.workstation.business.application.application.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.application.business.application.dto.AppAddDto;
import com.yunkang.saas.application.business.application.dto.AppEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service("workstationAppValidator")
public class AppValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(AppAddDto.class.equals(aClass))
			return true;
		if(AppEditDto.class.equals(aClass))
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
	    if(obj instanceof AppAddDto){
            this.validateAddDto((AppAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param app 应用
     * @param errors
     */
	public void validateAddDto(AppAddDto app, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(app.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "应用名称不能为空");
		}
       
		if(StringUtils.isEmpty(app.getCode())){
			errors.rejectValue("code", "EMPTY_CODE", "应用代码不能为空");
		}
       
		if (null == app.getTenantId() ) {
			errors.rejectValue("tenantId", "EMPTY_TENANT_ID", "所属租户不能为空");
		}

		//验证长度
		if(StringUtils.length(app.getName()) > 255){
			errors.rejectValue("name", null, "应用名称最长255个字符");
		}
		if(StringUtils.length(app.getCode()) > 255){
			errors.rejectValue("code", null, "应用代码最长255个字符");
		}
		if(StringUtils.length(app.getAppCategoryCode()) > 255){
			errors.rejectValue("appCategoryCode", null, "应用类别类别代码最长255个字符");
		}
		if(StringUtils.length(app.getUrl()) > 255){
			errors.rejectValue("url", null, "应用链接最长255个字符");
		}
	}
}