package com.yunkang.saas.bootstrap.application.business.security.valid;

import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import com.yunkang.saas.bootstrap.platform.business.account.dto.AccountAddDto;
import com.yunkang.saas.bootstrap.platform.business.account.dto.AccountEditDto;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AccountValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(AccountAddDto.class.equals(aClass))
			return true;
		if(AccountEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return Account.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof Account){
            this.validateAccount((Account)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param account 账号
     * @param errors
     */
	public void validateAccount(Account account, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(account.getNickName())){
			errors.rejectValue(Account.PROPERTY_NICK_NAME, "EMPTY_"+Account.PROPERTY_NICK_NAME, "昵称不能为空");
		}
		if(StringUtils.isEmpty(account.getName())){
			errors.rejectValue(Account.PROPERTY_NAME, "EMPTY_"+Account.PROPERTY_NAME, "姓名不能为空");
		}
		if(null ==account.getEnable()){
			errors.rejectValue(Account.PROPERTY_ENABLE, "EMPTY_"+Account.PROPERTY_ENABLE, "已启用不能为空");
		}

		//验证长度
		if(StringUtils.length(account.getNickName()) > 255){
			errors.rejectValue(Account.PROPERTY_NICK_NAME,null,"昵称最长255个字符");
		}
		if(StringUtils.length(account.getName()) > 255){
			errors.rejectValue(Account.PROPERTY_NAME,null,"姓名最长255个字符");
		}
		if(StringUtils.length(account.getAccountName()) > 255){
			errors.rejectValue(Account.PROPERTY_ACCOUNT_NAME,null,"账号最长255个字符");
		}
		if(StringUtils.length(account.getMobile()) > 255){
			errors.rejectValue(Account.PROPERTY_MOBILE,null,"手机号最长255个字符");
		}
		if(StringUtils.length(account.getEmail()) > 255){
			errors.rejectValue(Account.PROPERTY_EMAIL,null,"邮箱最长255个字符");
		}

	}
}