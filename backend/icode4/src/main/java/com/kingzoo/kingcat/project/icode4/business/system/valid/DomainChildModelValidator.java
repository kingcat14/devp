package com.kingzoo.kingcat.project.icode4.business.system.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.DomainChildModelAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainChildModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DomainChildModelValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(DomainChildModelAddRequest.class.equals(aClass))
            return true;
		return DomainChildModel.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DomainChildModel){
            this.validateDomainChildModel((DomainChildModel)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param domainChildModel 领域子对象
     * @param errors
     */
	public void validateDomainChildModel(DomainChildModel domainChildModel, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(domainChildModel.getName())){
			errors.rejectValue(DomainChildModel.PROPERTY_NAME, "EMPTY_"+DomainChildModel.PROPERTY_NAME, "对象名称不能为空");
		}
		if(StringUtils.isEmpty(domainChildModel.getCode())){
			errors.rejectValue(DomainChildModel.PROPERTY_CODE, "EMPTY_"+DomainChildModel.PROPERTY_CODE, "对象代码不能为空");
		}
		if(StringUtils.isEmpty(domainChildModel.getDomainModelId())){
			errors.rejectValue(DomainChildModel.PROPERTY_DOMAIN_MODEL_ID, "EMPTY_"+DomainChildModel.PROPERTY_DOMAIN_MODEL_ID, "所属根对象不能为空");
		}
		if (null == domainChildModel.getPersist() ) {
			errors.rejectValue(DomainChildModel.PROPERTY_PERSIST, "EMPTY_"+DomainChildModel.PROPERTY_PERSIST, "是否持久化不能为空");
		}

		//验证长度
		if(StringUtils.length(domainChildModel.getName()) > 255){
			errors.rejectValue(DomainChildModel.PROPERTY_NAME,null,"对象名称最长255个字符");
		}
		if(StringUtils.length(domainChildModel.getCode()) > 255){
			errors.rejectValue(DomainChildModel.PROPERTY_CODE,null,"对象代码最长255个字符");
		}
	}
}