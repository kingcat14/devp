package com.yunkang.saas.bootstrap.business.platform.security.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.bootstrap.business.platform.security.domain.AccountPassword;
import com.yunkang.saas.bootstrap.business.platform.security.dto.AccountPasswordAddDto;
import com.yunkang.saas.bootstrap.business.platform.security.dto.AccountPasswordEditDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AccountPasswordValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(AccountPasswordAddDto.class.equals(aClass))
			return true;
		if(AccountPasswordEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return AccountPassword.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof AccountPassword){
            this.validateAccountPassword((AccountPassword)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param accountPassword 账号密码
     * @param errors
     */
	public void validateAccountPassword(AccountPassword accountPassword, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度

		if(StringUtils.length(accountPassword.getAccountName()) > 255){
			errors.rejectValue(AccountPassword.PROPERTY_ACCOUNT_NAME,null,"账号账号最长255个字符");
		}
		if(StringUtils.length(accountPassword.getPassword()) > 255){
			errors.rejectValue(AccountPassword.PROPERTY_PASSWORD,null,"密码最长255个字符");
		}
	}
}