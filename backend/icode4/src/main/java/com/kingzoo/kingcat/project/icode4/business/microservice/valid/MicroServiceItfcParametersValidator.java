package com.kingzoo.kingcat.project.icode4.business.microservice.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.MicroServiceItfcParametersAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfcParameters;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MicroServiceItfcParametersValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(MicroServiceItfcParametersAddRequest.class.equals(aClass))
            return true;
		return MicroServiceItfcParameters.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof MicroServiceItfcParameters){
            this.validateMicroServiceItfcParameters((MicroServiceItfcParameters)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param microServiceItfcParameters 微服务接口参数
     * @param errors
     */
	public void validateMicroServiceItfcParameters(MicroServiceItfcParameters microServiceItfcParameters, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == microServiceItfcParameters.getViewIndex() ) {
			errors.rejectValue(MicroServiceItfcParameters.PROPERTY_VIEW_INDEX, "EMPTY_"+MicroServiceItfcParameters.PROPERTY_VIEW_INDEX, "排序不能为空");
		}
		if(StringUtils.isEmpty(microServiceItfcParameters.getName())){
			errors.rejectValue(MicroServiceItfcParameters.PROPERTY_NAME, "EMPTY_"+MicroServiceItfcParameters.PROPERTY_NAME, "参数名称不能为空");
		}
		if(StringUtils.isEmpty(microServiceItfcParameters.getCode())){
			errors.rejectValue(MicroServiceItfcParameters.PROPERTY_CODE, "EMPTY_"+MicroServiceItfcParameters.PROPERTY_CODE, "代码不能为空");
		}
		if(StringUtils.isEmpty(microServiceItfcParameters.getType())){
			errors.rejectValue(MicroServiceItfcParameters.PROPERTY_TYPE, "EMPTY_"+MicroServiceItfcParameters.PROPERTY_TYPE, "参数类型不能为空");
		}
		if(StringUtils.isEmpty(microServiceItfcParameters.getMicroServiceItfcId())){
			errors.rejectValue(MicroServiceItfcParameters.PROPERTY_MICRO_SERVICE_ITFC_ID, "EMPTY_"+MicroServiceItfcParameters.PROPERTY_MICRO_SERVICE_ITFC_ID, "所属微服务接口不能为空");
		}
		if (null == microServiceItfcParameters.getRequired() ) {
			errors.rejectValue(MicroServiceItfcParameters.PROPERTY_REQUIRED, "EMPTY_"+MicroServiceItfcParameters.PROPERTY_REQUIRED, "必填不能为空");
		}

		//验证长度
		if(StringUtils.length(microServiceItfcParameters.getName()) > 255){
			errors.rejectValue(MicroServiceItfcParameters.PROPERTY_NAME,null,"参数名称最长255个字符");
		}
		if(StringUtils.length(microServiceItfcParameters.getCode()) > 255){
			errors.rejectValue(MicroServiceItfcParameters.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(microServiceItfcParameters.getType()) > 255){
			errors.rejectValue(MicroServiceItfcParameters.PROPERTY_TYPE,null,"参数类型最长255个字符");
		}
		if(StringUtils.length(microServiceItfcParameters.getPathMapping()) > 255){
			errors.rejectValue(MicroServiceItfcParameters.PROPERTY_PATH_MAPPING,null,"路径映射最长255个字符");
		}
		if(StringUtils.length(microServiceItfcParameters.getMemo()) > 255){
			errors.rejectValue(MicroServiceItfcParameters.PROPERTY_MEMO,null,"备注最长255个字符");
		}
	}
}