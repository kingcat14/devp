package com.kingzoo.kingcat.project.icode4.business.system.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelViewProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DomainModelViewPropertyValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return DomainModelViewProperty.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DomainModelViewProperty){
            this.validateDomainModelViewProperty((DomainModelViewProperty)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param domainModelViewProperty 属性展现配置
     * @param errors
     */
	public void validateDomainModelViewProperty(DomainModelViewProperty domainModelViewProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(domainModelViewProperty.getCode())){
			errors.rejectValue(DomainModelViewProperty.PROPERTY_CODE, "EMPTY_"+DomainModelViewProperty.PROPERTY_CODE, "代码不能为空");
		}
		if (null == domainModelViewProperty.getAddViewable() ) {
			errors.rejectValue(DomainModelViewProperty.PROPERTY_ADD_VIEWABLE, "EMPTY_"+DomainModelViewProperty.PROPERTY_ADD_VIEWABLE, "新增页可见不能为空");
		}
		if (null == domainModelViewProperty.getAddRequired() ) {
			errors.rejectValue(DomainModelViewProperty.PROPERTY_ADD_REQUIRED, "EMPTY_"+DomainModelViewProperty.PROPERTY_ADD_REQUIRED, "新增必填不能为空");
		}
		if (null == domainModelViewProperty.getEditViewable() ) {
			errors.rejectValue(DomainModelViewProperty.PROPERTY_EDIT_VIEWABLE, "EMPTY_"+DomainModelViewProperty.PROPERTY_EDIT_VIEWABLE, "修改页可见不能为空");
		}

		//验证长度
		if(StringUtils.length(domainModelViewProperty.getId()) > 255){
			errors.rejectValue(DomainModelViewProperty.PROPERTY_ID,null,"主键最长255个字符");
		}
		if(StringUtils.length(domainModelViewProperty.getCode()) > 255){
			errors.rejectValue(DomainModelViewProperty.PROPERTY_CODE,null,"代码最长255个字符");
		}

	}
}