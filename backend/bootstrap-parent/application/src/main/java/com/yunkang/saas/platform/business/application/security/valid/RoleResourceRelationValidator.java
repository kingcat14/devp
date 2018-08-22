package com.yunkang.saas.platform.business.application.security.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.platform.security.domain.RoleResourceRelation;
import com.yunkang.saas.platform.business.platform.security.dto.RoleResourceRelationAddDto;
import com.yunkang.saas.platform.business.platform.security.dto.RoleResourceRelationEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RoleResourceRelationValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(RoleResourceRelationAddDto.class.equals(aClass))
			return true;
		if(RoleResourceRelationEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return RoleResourceRelation.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof RoleResourceRelation){
            this.validateRoleResourceRelation((RoleResourceRelation)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param roleResourceRelation 角色资源关系
     * @param errors
     */
	public void validateRoleResourceRelation(RoleResourceRelation roleResourceRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == roleResourceRelation.getRoleId() ) {
			errors.rejectValue(RoleResourceRelation.PROPERTY_ROLE_ID, "EMPTY_"+RoleResourceRelation.PROPERTY_ROLE_ID, "角色Id不能为空");
		}
		if (null == roleResourceRelation.getResourceId() ) {
			errors.rejectValue(RoleResourceRelation.PROPERTY_RESOURCE_ID, "EMPTY_"+RoleResourceRelation.PROPERTY_RESOURCE_ID, "资源Id不能为空");
		}

		//验证长度
	}
}