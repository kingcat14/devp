package com.yunkang.saas.platform.business.resource.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.resource.domain.Resource;
import com.yunkang.saas.platform.business.resource.dto.ResourceAddDto;
import com.yunkang.saas.platform.business.resource.dto.ResourceEditDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ResourceValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(ResourceAddDto.class.equals(aClass))
			return true;
		if(ResourceEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return Resource.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof Resource){
            this.validateResource((Resource)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param resource 资源
     * @param errors
     */
	public void validateResource(Resource resource, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(resource.getName()) > 255){
			errors.rejectValue(Resource.PROPERTY_NAME,null,"资源名最长255个字符");
		}
		if(StringUtils.length(resource.getUrl()) > 255){
			errors.rejectValue(Resource.PROPERTY_URL,null,"资源链接最长255个字符");
		}
		if(StringUtils.length(resource.getType()) > 255){
			errors.rejectValue(Resource.PROPERTY_TYPE,null,"资源类型最长255个字符");
		}
	}
}