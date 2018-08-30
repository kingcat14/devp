package com.kingzoo.kingcat.project.icode4.business.microservice.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.TransModelPropertyAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TransModelPropertyValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(TransModelPropertyAddRequest.class.equals(aClass))
            return true;
		return TransModelProperty.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof TransModelProperty){
            this.validateTransModelProperty((TransModelProperty)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param transModelProperty 传输对象属性
     * @param errors
     */
	public void validateTransModelProperty(TransModelProperty transModelProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(transModelProperty.getName())){
			errors.rejectValue(TransModelProperty.PROPERTY_NAME, "EMPTY_"+TransModelProperty.PROPERTY_NAME, "属性名不能为空");
		}
		if(StringUtils.isEmpty(transModelProperty.getCode())){
			errors.rejectValue(TransModelProperty.PROPERTY_CODE, "EMPTY_"+TransModelProperty.PROPERTY_CODE, "属性代码不能为空");
		}
		if(StringUtils.isEmpty(transModelProperty.getType())){
			errors.rejectValue(TransModelProperty.PROPERTY_TYPE, "EMPTY_"+TransModelProperty.PROPERTY_TYPE, "属性类型不能为空");
		}
		if(StringUtils.isEmpty(transModelProperty.getTransModelId())){
			errors.rejectValue(TransModelProperty.PROPERTY_TRANS_MODEL_ID, "EMPTY_"+TransModelProperty.PROPERTY_TRANS_MODEL_ID, "所属传输对象不能为空");
		}
		if (null == transModelProperty.getEditable() ) {
			errors.rejectValue(TransModelProperty.PROPERTY_EDITABLE, "EMPTY_"+TransModelProperty.PROPERTY_EDITABLE, "可修改不能为空");
		}
		if (null == transModelProperty.getNullable() ) {
			errors.rejectValue(TransModelProperty.PROPERTY_NULLABLE, "EMPTY_"+TransModelProperty.PROPERTY_NULLABLE, "可为空不能为空");
		}
		if (null == transModelProperty.getCoreRelation() ) {
			errors.rejectValue(TransModelProperty.PROPERTY_CORE_RELATION, "EMPTY_"+TransModelProperty.PROPERTY_CORE_RELATION, "核心对象属性不能为空");
		}
		if(StringUtils.isEmpty(transModelProperty.getDomainModelId())){
			errors.rejectValue(TransModelProperty.PROPERTY_DOMAIN_MODEL_ID, "EMPTY_"+TransModelProperty.PROPERTY_DOMAIN_MODEL_ID, "关联对象不能为空");
		}
		if(StringUtils.isEmpty(transModelProperty.getDomainModelPropertyId())){
			errors.rejectValue(TransModelProperty.PROPERTY_DOMAIN_MODEL_PROPERTY_ID, "EMPTY_"+TransModelProperty.PROPERTY_DOMAIN_MODEL_PROPERTY_ID, "关联对象属性不能为空");
		}

		//验证长度
		if(StringUtils.length(transModelProperty.getName()) > 255){
			errors.rejectValue(TransModelProperty.PROPERTY_NAME,null,"属性名最长255个字符");
		}
		if(StringUtils.length(transModelProperty.getCode()) > 255){
			errors.rejectValue(TransModelProperty.PROPERTY_CODE,null,"属性代码最长255个字符");
		}
		if(StringUtils.length(transModelProperty.getType()) > 255){
			errors.rejectValue(TransModelProperty.PROPERTY_TYPE,null,"属性类型最长255个字符");
		}
		if(StringUtils.length(transModelProperty.getMemo()) > 255){
			errors.rejectValue(TransModelProperty.PROPERTY_MEMO,null,"备注最长255个字符");
		}
	}
}