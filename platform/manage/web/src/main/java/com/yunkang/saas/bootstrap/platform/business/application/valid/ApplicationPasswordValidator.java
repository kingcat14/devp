package com.yunkang.saas.bootstrap.platform.business.application.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.bootstrap.platform.business.application.dto.ApplicationPasswordAddDto;
import com.yunkang.saas.bootstrap.platform.business.application.dto.ApplicationPasswordEditDto;
import com.yunkang.saas.bootstrap.platform.business.application.domain.ApplicationPassword;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ApplicationPasswordValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(ApplicationPasswordAddDto.class.equals(aClass))
			return true;
		if(ApplicationPasswordEditDto.class.equals(aClass))
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
	    if(obj instanceof ApplicationPasswordAddDto){
            this.validateApplicationPasswordAddDto((ApplicationPasswordAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param applicationPassword 应用密码
     * @param errors
     */
	public void validateApplicationPasswordAddDto(ApplicationPasswordAddDto applicationPassword, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(applicationPassword.getAccessId())){
			errors.rejectValue(ApplicationPassword.PROPERTY_ACCESS_ID, "EMPTY_"+ApplicationPassword.PROPERTY_ACCESS_ID, "访问ID不能为空");
		}
		if(StringUtils.isEmpty(applicationPassword.getAccessKey())){
			errors.rejectValue(ApplicationPassword.PROPERTY_ACCESS_KEY, "EMPTY_"+ApplicationPassword.PROPERTY_ACCESS_KEY, "访问密码不能为空");
		}

		//验证长度
		if(StringUtils.length(applicationPassword.getAccessId()) > 255){
			errors.rejectValue(ApplicationPassword.PROPERTY_ACCESS_ID,null,"访问ID最长255个字符");
		}
		if(StringUtils.length(applicationPassword.getAccessKey()) > 255){
			errors.rejectValue(ApplicationPassword.PROPERTY_ACCESS_KEY,null,"访问密码最长255个字符");
		}
	}
}