package com.kingzoo.kingcat.project.icode4.business.system.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.DomainModelPropertyAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelPropertyService;
import com.kingzoo.kingcat.project.icode4.business.system.service.PropertyTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class DomainModelPropertyValidator implements Validator {

	@Autowired
	private PropertyTypeService propertyTypeService;

	@Autowired
    private DomainModelPropertyService domainModelPropertyService;

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(DomainModelPropertyAddRequest.class.equals(aClass))
            return true;
		return DomainModelProperty.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DomainModelProperty){
            this.validateDomainModelProperty((DomainModelProperty)obj, errors);
        }
		if(obj instanceof DomainModelPropertyAddRequest){
			this.validateAddRequest((DomainModelPropertyAddRequest)obj, errors);
		}
	}

	/**
     * 实现Validator中的validate接口
     * @param domainModelProperty 领域对象属性
     * @param errors
     */
	public void validateDomainModelProperty(DomainModelProperty domainModelProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(domainModelProperty.getDomainModelId())){
			errors.rejectValue(DomainModelProperty.PROPERTY_DOMAIN_MODEL_ID, "EMPTY_"+DomainModelProperty.PROPERTY_DOMAIN_MODEL_ID, "所属领域对象不能为空");
		}
		if(StringUtils.isEmpty(domainModelProperty.getName())){
			errors.rejectValue(DomainModelProperty.PROPERTY_NAME, "EMPTY_"+DomainModelProperty.PROPERTY_NAME, "属性名不能为空");
		}
		if(StringUtils.isEmpty(domainModelProperty.getCode())){
			errors.rejectValue(DomainModelProperty.PROPERTY_CODE, "EMPTY_"+DomainModelProperty.PROPERTY_CODE, "属性代码不能为空");
		}
		if(StringUtils.isEmpty(domainModelProperty.getType())){
			errors.rejectValue(DomainModelProperty.PROPERTY_TYPE, "EMPTY_"+DomainModelProperty.PROPERTY_TYPE, "属性类型不能为空");
		}
		if (null == domainModelProperty.getPersist() ) {
			errors.rejectValue(DomainModelProperty.PROPERTY_PERSIST, "EMPTY_"+DomainModelProperty.PROPERTY_PERSIST, "持久化不能为空");
		}
		if (null == domainModelProperty.getEditable() ) {
			errors.rejectValue(DomainModelProperty.PROPERTY_EDITABLE, "EMPTY_"+DomainModelProperty.PROPERTY_EDITABLE, "可修改不能为空");
		}
		if (null == domainModelProperty.getNullable() ) {
			errors.rejectValue(DomainModelProperty.PROPERTY_NULLABLE, "EMPTY_"+DomainModelProperty.PROPERTY_NULLABLE, "可为空不能为空");
		}

		//验证长度
		if(StringUtils.length(domainModelProperty.getName()) > 255){
			errors.rejectValue(DomainModelProperty.PROPERTY_NAME,null,"属性名最长255个字符");
		}
		if(StringUtils.length(domainModelProperty.getCode()) > 255){
			errors.rejectValue(DomainModelProperty.PROPERTY_CODE,null,"属性代码最长255个字符");
		}
		if(StringUtils.length(domainModelProperty.getType()) > 255){
			errors.rejectValue(DomainModelProperty.PROPERTY_TYPE,null,"属性类型最长255个字符");
		}
		if(StringUtils.length(domainModelProperty.getMemo()) > 255){
			errors.rejectValue(DomainModelProperty.PROPERTY_MEMO,null,"备注最长255个字符");
		}

		checkRelateProperty(domainModelProperty.getType(), domainModelProperty.getRelatedDomainModelPropertyId(), errors);
	}


	public void validateAddRequest(DomainModelPropertyAddRequest addRequest, Errors errors){
		checkRelateProperty(addRequest.getType(), addRequest.getRelatedDomainModelPropertyId(), errors);
	}

	/**
	 * 检查属性的配置是不是正确
	 * @param type
	 * @param relatePropertyId
	 * @param errors
	 */
	public void checkRelateProperty(String type, String relatePropertyId, Errors errors){
        if(propertyTypeService.exist(type)){
            return;
        }
        if(!domainModelPropertyService.exist(type, relatePropertyId)){
            errors.rejectValue(DomainModelProperty.PROPERTY_RELATED_DOMAIN_MODEL_PROPERTY_ID,null,"关联的属性不正确");
        }

	}
}