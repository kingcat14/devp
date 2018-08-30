package com.kingzoo.kingcat.project.icode4.business.microservice.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.MicroServiceItfcAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MicroServiceItfcValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(MicroServiceItfcAddRequest.class.equals(aClass))
            return true;
		return MicroServiceItfc.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof MicroServiceItfc){
            this.validateMicroServiceItfc((MicroServiceItfc)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param microServiceItfc 微服务接口
     * @param errors
     */
	public void validateMicroServiceItfc(MicroServiceItfc microServiceItfc, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(microServiceItfc.getName())){
			errors.rejectValue(MicroServiceItfc.PROPERTY_NAME, "EMPTY_"+MicroServiceItfc.PROPERTY_NAME, "接口名称不能为空");
		}
		if(StringUtils.isEmpty(microServiceItfc.getCode())){
			errors.rejectValue(MicroServiceItfc.PROPERTY_CODE, "EMPTY_"+MicroServiceItfc.PROPERTY_CODE, "接口代码不能为空");
		}
		if(StringUtils.isEmpty(microServiceItfc.getUrl())){
			errors.rejectValue(MicroServiceItfc.PROPERTY_URL, "EMPTY_"+MicroServiceItfc.PROPERTY_URL, "接口地址不能为空");
		}
		if(StringUtils.isEmpty(microServiceItfc.getMethod())){
			errors.rejectValue(MicroServiceItfc.PROPERTY_METHOD, "EMPTY_"+MicroServiceItfc.PROPERTY_METHOD, "接口方法不能为空");
		}
		if(StringUtils.isEmpty(microServiceItfc.getMicroServiceId())){
			errors.rejectValue(MicroServiceItfc.PROPERTY_MICRO_SERVICE_ID, "EMPTY_"+MicroServiceItfc.PROPERTY_MICRO_SERVICE_ID, "所属微服务不能为空");
		}

		//验证长度
		if(StringUtils.length(microServiceItfc.getName()) > 255){
			errors.rejectValue(MicroServiceItfc.PROPERTY_NAME,null,"接口名称最长255个字符");
		}
		if(StringUtils.length(microServiceItfc.getCode()) > 255){
			errors.rejectValue(MicroServiceItfc.PROPERTY_CODE,null,"接口代码最长255个字符");
		}
		if(StringUtils.length(microServiceItfc.getUrl()) > 255){
			errors.rejectValue(MicroServiceItfc.PROPERTY_URL,null,"接口地址最长255个字符");
		}
		if(StringUtils.length(microServiceItfc.getMethod()) > 255){
			errors.rejectValue(MicroServiceItfc.PROPERTY_METHOD,null,"接口方法最长255个字符");
		}
		if(StringUtils.length(microServiceItfc.getDescription()) > 1000){
			errors.rejectValue(MicroServiceItfc.PROPERTY_DESCRIPTION,null,"接口描述最长1000个字符");
		}
	}
}