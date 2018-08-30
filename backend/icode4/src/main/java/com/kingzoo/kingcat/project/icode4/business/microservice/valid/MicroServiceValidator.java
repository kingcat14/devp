package com.kingzoo.kingcat.project.icode4.business.microservice.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.MicroServiceAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MicroServiceValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(MicroServiceAddRequest.class.equals(aClass))
            return true;
		return MicroService.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof MicroService){
            this.validateMicroService((MicroService)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param microService 微服务
     * @param errors
     */
	public void validateMicroService(MicroService microService, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(microService.getName())){
			errors.rejectValue(MicroService.PROPERTY_NAME, "EMPTY_"+MicroService.PROPERTY_NAME, "微服务名称不能为空");
		}
		if(StringUtils.isEmpty(microService.getCode())){
			errors.rejectValue(MicroService.PROPERTY_CODE, "EMPTY_"+MicroService.PROPERTY_CODE, "微服务代码不能为空");
		}
		if(StringUtils.isEmpty(microService.getModuleId())){
			errors.rejectValue(MicroService.PROPERTY_MODULE_ID, "EMPTY_"+MicroService.PROPERTY_MODULE_ID, "所属模块不能为空");
		}

		//验证长度
		if(StringUtils.length(microService.getName()) > 255){
			errors.rejectValue(MicroService.PROPERTY_NAME,null,"微服务名称最长255个字符");
		}
		if(StringUtils.length(microService.getCode()) > 255){
			errors.rejectValue(MicroService.PROPERTY_CODE,null,"微服务代码最长255个字符");
		}
		if(StringUtils.length(microService.getDescription()) > 1000){
			errors.rejectValue(MicroService.PROPERTY_DESCRIPTION,null,"描述最长1000个字符");
		}
	}
}