package com.kingzoo.kingcat.project.icode4.business.system.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.ModelTypeAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.ModelType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ModelTypeValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(ModelTypeAddRequest.class.equals(aClass))
            return true;
		return ModelType.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof ModelType){
            this.validateModelType((ModelType)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param modelType 对象类型
     * @param errors
     */
	public void validateModelType(ModelType modelType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(modelType.getCode())){
			errors.rejectValue(ModelType.PROPERTY_CODE, "EMPTY_"+ModelType.PROPERTY_CODE, "类型代码不能为空");
		}
		if(StringUtils.isEmpty(modelType.getName())){
			errors.rejectValue(ModelType.PROPERTY_NAME, "EMPTY_"+ModelType.PROPERTY_NAME, "类型名称不能为空");
		}

		//验证长度
		if(StringUtils.length(modelType.getCode()) > 255){
			errors.rejectValue(ModelType.PROPERTY_CODE,null,"类型代码最长255个字符");
		}
		if(StringUtils.length(modelType.getName()) > 255){
			errors.rejectValue(ModelType.PROPERTY_NAME,null,"类型名称最长255个字符");
		}
	}
}