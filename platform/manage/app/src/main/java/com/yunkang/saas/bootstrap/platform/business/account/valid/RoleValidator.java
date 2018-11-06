package com.yunkang.saas.bootstrap.platform.business.account.valid;


import com.yunkang.saas.bootstrap.application.business.security.domain.Role;
import com.yunkang.saas.bootstrap.platform.business.account.dto.RoleAddDto;
import com.yunkang.saas.bootstrap.platform.business.account.dto.RoleEditDto;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RoleValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(RoleAddDto.class.equals(aClass))
			return true;
		if(RoleEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return Role.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof Role){
            this.validateRole((Role)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param role 角色
     * @param errors
     */
	public void validateRole(Role role, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(role.getName())){
			errors.rejectValue(Role.PROPERTY_NAME, "EMPTY_"+Role.PROPERTY_NAME, "角色名称不能为空");
		}

		//验证长度
		if(StringUtils.length(role.getName()) > 255){
			errors.rejectValue(Role.PROPERTY_NAME,null,"角色名称最长255个字符");
		}
		if(StringUtils.length(role.getDescription()) > 255){
			errors.rejectValue(Role.PROPERTY_DESCRIPTION,null,"角色描述最长255个字符");
		}
	}
}