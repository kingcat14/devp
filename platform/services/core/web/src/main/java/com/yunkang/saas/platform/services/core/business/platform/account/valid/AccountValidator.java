package com.yunkang.saas.platform.services.core.business.platform.account.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.services.core.business.platform.account.domain.Account;
import com.yunkang.saas.platform.services.core.business.platform.account.dto.AccountAddDto;
import com.yunkang.saas.platform.services.core.business.platform.account.dto.AccountCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AccountValidator implements Validator {

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
	    if(obj instanceof AccountAddDto){
            this.validateAccountAddDto((AccountAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AccountCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AccountCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param account 账号
     * @param errors
     */
	public void validateAccountAddDto(AccountAddDto account, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

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
		if(StringUtils.length(account.getAccountPassword()) > 255){
			errors.rejectValue(Account.PROPERTY_ACCOUNT_PASSWORD,null,"密码最长255个字符");
		}
		if(StringUtils.length(account.getMobile()) > 255){
			errors.rejectValue(Account.PROPERTY_MOBILE,null,"手机号最长255个字符");
		}
		if(StringUtils.length(account.getEmail()) > 255){
			errors.rejectValue(Account.PROPERTY_EMAIL,null,"邮箱最长255个字符");
		}
	}
}