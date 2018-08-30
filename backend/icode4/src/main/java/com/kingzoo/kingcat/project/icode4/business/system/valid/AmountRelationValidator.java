package com.kingzoo.kingcat.project.icode4.business.system.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.AmountRelationAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.AmountRelation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AmountRelationValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(AmountRelationAddRequest.class.equals(aClass))
            return true;
		return AmountRelation.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof AmountRelation){
            this.validateAmountRelation((AmountRelation)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param amountRelation 数量关系
     * @param errors
     */
	public void validateAmountRelation(AmountRelation amountRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(amountRelation.getCode())){
			errors.rejectValue(AmountRelation.PROPERTY_CODE, "EMPTY_"+AmountRelation.PROPERTY_CODE, "代码不能为空");
		}
		if(StringUtils.isEmpty(amountRelation.getName())){
			errors.rejectValue(AmountRelation.PROPERTY_NAME, "EMPTY_"+AmountRelation.PROPERTY_NAME, "名称不能为空");
		}

		//验证长度
		if(StringUtils.length(amountRelation.getCode()) > 255){
			errors.rejectValue(AmountRelation.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(amountRelation.getName()) > 255){
			errors.rejectValue(AmountRelation.PROPERTY_NAME,null,"名称最长255个字符");
		}
	}
}