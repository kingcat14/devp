package com.kingzoo.kingcat.project.icode4.business.tplfile.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.tplfile.controller.vo.TplSetAddRequest;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplSet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TplSetValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(TplSetAddRequest.class.equals(aClass))
            return true;
		return TplSet.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof TplSet){
            this.validateTplSet((TplSet)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param tplSet 模板集合
     * @param errors
     */
	public void validateTplSet(TplSet tplSet, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(tplSet.getName())){
			errors.rejectValue(TplSet.PROPERTY_NAME, "EMPTY_"+TplSet.PROPERTY_NAME, "集合名称不能为空");
		}
		if(StringUtils.isEmpty(tplSet.getCode())){
			errors.rejectValue(TplSet.PROPERTY_CODE, "EMPTY_"+TplSet.PROPERTY_CODE, "集合代码不能为空");
		}

		//验证长度
		if(StringUtils.length(tplSet.getName()) > 255){
			errors.rejectValue(TplSet.PROPERTY_NAME,null,"集合名称最长255个字符");
		}
		if(StringUtils.length(tplSet.getCode()) > 255){
			errors.rejectValue(TplSet.PROPERTY_CODE,null,"集合代码最长255个字符");
		}
		if(StringUtils.length(tplSet.getType()) > 255){
			errors.rejectValue(TplSet.PROPERTY_TYPE,null,"集合类型最长255个字符");
		}
		if(StringUtils.length(tplSet.getDescription()) > 1000){
			errors.rejectValue(TplSet.PROPERTY_DESCRIPTION,null,"描述最长1000个字符");
		}
	}
}