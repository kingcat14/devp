package com.yunkang.saas.application.business.application.valid;

import com.yunkang.saas.application.business.application.domain.AppCategory;
import com.yunkang.saas.application.business.application.dto.AppCategoryAddDto;
import com.yunkang.saas.application.business.application.dto.AppCategoryEditDto;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AppCategoryValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(AppCategoryAddDto.class.equals(aClass))
			return true;
		if(AppCategoryEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return AppCategory.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof AppCategory){
            this.validateAppCategory((AppCategory)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param appCategory 应用类别
     * @param errors
     */
	public void validateAppCategory(AppCategory appCategory, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(appCategory.getName()) > 255){
			errors.rejectValue(AppCategory.PROPERTY_NAME,null,"类别名称最长255个字符");
		}
		if(StringUtils.length(appCategory.getCode()) > 255){
			errors.rejectValue(AppCategory.PROPERTY_CODE,null,"类别代码最长255个字符");
		}
	}
}