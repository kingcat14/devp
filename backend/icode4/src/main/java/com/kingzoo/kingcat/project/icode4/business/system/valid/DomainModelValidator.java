package com.kingzoo.kingcat.project.icode4.business.system.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.DomainModelAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DomainModelValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(DomainModelAddRequest.class.equals(aClass))
            return true;
		return DomainModel.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DomainModel){
            this.validateDomainModel((DomainModel)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param domainModel 领域根对象
     * @param errors
     */
	public void validateDomainModel(DomainModel domainModel, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(domainModel.getName())){
			errors.rejectValue(DomainModel.PROPERTY_NAME, "EMPTY_"+DomainModel.PROPERTY_NAME, "对象名称不能为空");
		}
		if(StringUtils.isEmpty(domainModel.getCode())){
			errors.rejectValue(DomainModel.PROPERTY_CODE, "EMPTY_"+DomainModel.PROPERTY_CODE, "对象代码不能为空");
		}
		if(StringUtils.isEmpty(domainModel.getModuleId())){
			errors.rejectValue(DomainModel.PROPERTY_MODULE_ID, "EMPTY_"+DomainModel.PROPERTY_MODULE_ID, "所属模块不能为空");
		}
		if (null == domainModel.getPersist() ) {
			errors.rejectValue(DomainModel.PROPERTY_PERSIST, "EMPTY_"+DomainModel.PROPERTY_PERSIST, "是否持久化不能为空");
		}

		//验证长度
		if(StringUtils.length(domainModel.getName()) > 255){
			errors.rejectValue(DomainModel.PROPERTY_NAME,null,"对象名称最长255个字符");
		}
		if(StringUtils.length(domainModel.getCode()) > 255){
			errors.rejectValue(DomainModel.PROPERTY_CODE,null,"对象代码最长255个字符");
		}
	}
}