package com.yunkang.saas.bootstrap.platform.business.platform.application.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.bootstrap.platform.business.platform.application.dto.ConfigAppCategoryAddDto;
import com.yunkang.saas.bootstrap.platform.business.platform.application.dto.ConfigAppCategoryEditDto;
import com.yunkang.saas.bootstrap.platform.business.platform.application.domain.ConfigAppCategory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ConfigAppCategoryValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(ConfigAppCategoryAddDto.class.equals(aClass))
			return true;
		if(ConfigAppCategoryEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return false;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof ConfigAppCategoryAddDto){
            this.validateConfigAppCategoryAddDto((ConfigAppCategoryAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param configAppCategory 应用类别
     * @param errors
     */
	public void validateConfigAppCategoryAddDto(ConfigAppCategoryAddDto configAppCategory, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(configAppCategory.getName()) > 255){
			errors.rejectValue(ConfigAppCategory.PROPERTY_NAME,null,"类别名称最长255个字符");
		}
		if(StringUtils.length(configAppCategory.getCode()) > 255){
			errors.rejectValue(ConfigAppCategory.PROPERTY_CODE,null,"类别代码最长255个字符");
		}
	}
}