package com.kingzoo.kingcat.project.icode4.business.security.valid;

import com.kingzoo.kingcat.project.icode4.business.security.dto.ResourceAddDto;
import com.kingzoo.kingcat.project.icode4.business.security.dto.ResourceEditDto;
import com.kingzoo.kingcat.project.icode4.business.security.entity.Resource;
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
		if(ResourceAddDto.class.equals(aClass)||ResourceEditDto.class.equals(aClass)){
			return true;
		}

		return false;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof ResourceAddDto){
            	this.validateResourceAddDto((ResourceAddDto)obj, errors);
	    }
	    if(obj instanceof ResourceEditDto){
            	this.validateResourceEditDto((ResourceEditDto)obj, errors);
	    }
	}

	/**
	* 实现Validator中的validate接口
	* @param resource 资源
	* @param errors
	*/
	public void validateResourceAddDto(ResourceAddDto resource, Errors errors) {

		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(resource.getParentId())){
			errors.rejectValue(Resource.PROPERTY_PARENT_ID, "EMPTY_"+Resource.PROPERTY_PARENT_ID, "上级ID不能为空");
		}
		if(StringUtils.isEmpty(resource.getName())){
			errors.rejectValue(Resource.PROPERTY_NAME, "EMPTY_"+Resource.PROPERTY_NAME, "资源名称不能为空");
		}
		if(StringUtils.isEmpty(resource.getOrderIndex())){
			errors.rejectValue(Resource.PROPERTY_ORDER_INDEX, "EMPTY_"+Resource.PROPERTY_ORDER_INDEX, "展现顺序不能为空");
		}
		if(StringUtils.isEmpty(resource.getType())){
			errors.rejectValue(Resource.PROPERTY_TYPE, "EMPTY_"+Resource.PROPERTY_TYPE, "资源类型不能为空");
		}

		//验证长度
		if(StringUtils.length(resource.getParentId()) > 255){
			errors.rejectValue(Resource.PROPERTY_PARENT_ID,null,"上级ID最长255个字符");
		}
		if(StringUtils.length(resource.getName()) > 255){
			errors.rejectValue(Resource.PROPERTY_NAME,null,"资源名称最长255个字符");
		}
		if(StringUtils.length(resource.getOrderIndex()) > 255){
			errors.rejectValue(Resource.PROPERTY_ORDER_INDEX,null,"展现顺序最长255个字符");
		}
		if(StringUtils.length(resource.getUrl()) > 255){
			errors.rejectValue(Resource.PROPERTY_URL,null,"资源链接最长255个字符");
		}
		if(StringUtils.length(resource.getType()) > 255){
			errors.rejectValue(Resource.PROPERTY_TYPE,null,"资源类型最长255个字符");
		}
		if(StringUtils.length(resource.getCode()) > 255){
			errors.rejectValue(Resource.PROPERTY_CODE,null,"资源代码最长255个字符");
		}
	}

    /**
     * 实现Validator中的validate接口
     * @param resource 资源
     * @param errors
     */
    public void validateResourceEditDto(ResourceEditDto resource, Errors errors) {
       


        //把校验信息注册到Error的实现类里
        //验证必填
        if(StringUtils.isEmpty(resource.getParentId())){
            errors.rejectValue(Resource.PROPERTY_PARENT_ID, "EMPTY_"+Resource.PROPERTY_PARENT_ID, "上级ID不能为空");
        }
        if(StringUtils.isEmpty(resource.getName())){
            errors.rejectValue(Resource.PROPERTY_NAME, "EMPTY_"+Resource.PROPERTY_NAME, "资源名称不能为空");
        }
        if(StringUtils.isEmpty(resource.getOrderIndex())){
            errors.rejectValue(Resource.PROPERTY_ORDER_INDEX, "EMPTY_"+Resource.PROPERTY_ORDER_INDEX, "展现顺序不能为空");
        }
        if(StringUtils.isEmpty(resource.getType())){
            errors.rejectValue(Resource.PROPERTY_TYPE, "EMPTY_"+Resource.PROPERTY_TYPE, "资源类型不能为空");
        }

        //验证长度
        if(StringUtils.length(resource.getParentId()) > 255){
            errors.rejectValue(Resource.PROPERTY_PARENT_ID,null,"上级ID最长255个字符");
        }
        if(StringUtils.length(resource.getName()) > 255){
            errors.rejectValue(Resource.PROPERTY_NAME,null,"资源名称最长255个字符");
        }
        if(StringUtils.length(resource.getOrderIndex()) > 255){
            errors.rejectValue(Resource.PROPERTY_ORDER_INDEX,null,"展现顺序最长255个字符");
        }
        if(StringUtils.length(resource.getUrl()) > 255){
            errors.rejectValue(Resource.PROPERTY_URL,null,"资源链接最长255个字符");
        }
        if(StringUtils.length(resource.getType()) > 255){
            errors.rejectValue(Resource.PROPERTY_TYPE,null,"资源类型最长255个字符");
        }
        if(StringUtils.length(resource.getCode()) > 255){
            errors.rejectValue(Resource.PROPERTY_CODE,null,"资源代码最长255个字符");
        }
    }
}