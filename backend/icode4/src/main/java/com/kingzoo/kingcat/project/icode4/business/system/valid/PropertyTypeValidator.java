package com.kingzoo.kingcat.project.icode4.business.system.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.PropertyTypeAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.PropertyType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PropertyTypeValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(PropertyTypeAddRequest.class.equals(aClass))
            return true;
		return PropertyType.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof PropertyType){
            this.validatePropertyType((PropertyType)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param propertyType 属性类型
     * @param errors
     */
	public void validatePropertyType(PropertyType propertyType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(propertyType.getCode())){
			errors.rejectValue(PropertyType.PROPERTY_CODE, "EMPTY_"+PropertyType.PROPERTY_CODE, "代码不能为空");
		}
		if(StringUtils.isEmpty(propertyType.getName())){
			errors.rejectValue(PropertyType.PROPERTY_NAME, "EMPTY_"+PropertyType.PROPERTY_NAME, "名称不能为空");
		}

		//验证长度
		if(StringUtils.length(propertyType.getViewIndex()) > 255){
			errors.rejectValue(PropertyType.PROPERTY_VIEW_INDEX,null,"展示顺序最长255个字符");
		}
		if(StringUtils.length(propertyType.getCode()) > 255){
			errors.rejectValue(PropertyType.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(propertyType.getName()) > 255){
			errors.rejectValue(PropertyType.PROPERTY_NAME,null,"名称最长255个字符");
		}
	}
}