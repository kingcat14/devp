package com.kingzoo.kingcat.project.icode4.business.system.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.SystemAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SystemValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(SystemAddRequest.class.equals(aClass))
            return true;
		return System.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof System){
            this.validateSystem((System)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param system 系统
     * @param errors
     */
	public void validateSystem(System system, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(system.getName())){
			errors.rejectValue(System.PROPERTY_NAME, "EMPTY_"+System.PROPERTY_NAME, "系统名称不能为空");
		}
		if(StringUtils.isEmpty(system.getCode())){
			errors.rejectValue(System.PROPERTY_CODE, "EMPTY_"+System.PROPERTY_CODE, "系统代码不能为空");
		}
		if(StringUtils.isEmpty(system.getBasePackage())){
			errors.rejectValue(System.PROPERTY_BASE_PACKAGE, "EMPTY_"+System.PROPERTY_BASE_PACKAGE, "基础包名称不能为空");
		}

		//验证长度
		if(StringUtils.length(system.getName()) > 255){
			errors.rejectValue(System.PROPERTY_NAME,null,"系统名称最长255个字符");
		}
		if(StringUtils.length(system.getCode()) > 255){
			errors.rejectValue(System.PROPERTY_CODE,null,"系统代码最长255个字符");
		}
		if(StringUtils.length(system.getBasePackage()) > 255){
			errors.rejectValue(System.PROPERTY_BASE_PACKAGE,null,"基础包名称最长255个字符");
		}
	}
}