package com.yunkang.saas.security.local.business.platform.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.security.service.business.platform.domain.AccountRoleRelation;
import com.yunkang.saas.security.model.dto.AccountRoleRelationAddDto;
import com.yunkang.saas.security.model.dto.AccountRoleRelationEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AccountRoleRelationValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(AccountRoleRelationAddDto.class.equals(aClass))
			return true;
		if(AccountRoleRelationEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return AccountRoleRelation.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof AccountRoleRelation){
            this.validateAccountRoleRelation((AccountRoleRelation)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param accountRoleRelation 账号角色关联
     * @param errors
     */
	public void validateAccountRoleRelation(AccountRoleRelation accountRoleRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == accountRoleRelation.getAccountId() ) {
			errors.rejectValue(AccountRoleRelation.PROPERTY_ACCOUNT_ID, "EMPTY_"+AccountRoleRelation.PROPERTY_ACCOUNT_ID, "账号Id不能为空");
		}
		if (null == accountRoleRelation.getRoleId() ) {
			errors.rejectValue(AccountRoleRelation.PROPERTY_ROLE_ID, "EMPTY_"+AccountRoleRelation.PROPERTY_ROLE_ID, "角色Id不能为空");
		}

		//验证长度
	}
}