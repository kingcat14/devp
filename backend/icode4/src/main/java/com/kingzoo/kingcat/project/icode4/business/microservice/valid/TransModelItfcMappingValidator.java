package com.kingzoo.kingcat.project.icode4.business.microservice.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.TransModelItfcMappingAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModelItfcMapping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TransModelItfcMappingValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(TransModelItfcMappingAddRequest.class.equals(aClass))
            return true;
		return TransModelItfcMapping.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof TransModelItfcMapping){
            this.validateTransModelItfcMapping((TransModelItfcMapping)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param transModelItfcMapping 传输对象接口映射
     * @param errors
     */
	public void validateTransModelItfcMapping(TransModelItfcMapping transModelItfcMapping, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(transModelItfcMapping.getTransModelId())){
			errors.rejectValue(TransModelItfcMapping.PROPERTY_TRANS_MODEL_ID, "EMPTY_"+TransModelItfcMapping.PROPERTY_TRANS_MODEL_ID, "传输对象ID不能为空");
		}
		if(StringUtils.isEmpty(transModelItfcMapping.getMicroServiceItfcId())){
			errors.rejectValue(TransModelItfcMapping.PROPERTY_MICRO_SERVICE_ITFC_ID, "EMPTY_"+TransModelItfcMapping.PROPERTY_MICRO_SERVICE_ITFC_ID, "微服务接口ID不能为空");
		}
		if(StringUtils.isEmpty(transModelItfcMapping.getRelationType())){
			errors.rejectValue(TransModelItfcMapping.PROPERTY_RELATION_TYPE, "EMPTY_"+TransModelItfcMapping.PROPERTY_RELATION_TYPE, "关系类型不能为空");
		}

		//验证长度
		if(StringUtils.length(transModelItfcMapping.getTransModelId()) > 255){
			errors.rejectValue(TransModelItfcMapping.PROPERTY_TRANS_MODEL_ID,null,"传输对象ID最长255个字符");
		}
		if(StringUtils.length(transModelItfcMapping.getMicroServiceItfcId()) > 255){
			errors.rejectValue(TransModelItfcMapping.PROPERTY_MICRO_SERVICE_ITFC_ID,null,"微服务接口ID最长255个字符");
		}
		if(StringUtils.length(transModelItfcMapping.getRelationType()) > 255){
			errors.rejectValue(TransModelItfcMapping.PROPERTY_RELATION_TYPE,null,"关系类型最长255个字符");
		}
	}
}