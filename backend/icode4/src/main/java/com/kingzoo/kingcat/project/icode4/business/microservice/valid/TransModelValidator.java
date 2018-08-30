package com.kingzoo.kingcat.project.icode4.business.microservice.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.TransModelAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TransModelValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(TransModelAddRequest.class.equals(aClass))
            return true;
		return TransModel.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof TransModel){
            this.validateTransModel((TransModel)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param transModel 传输对象
     * @param errors
     */
	public void validateTransModel(TransModel transModel, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(transModel.getName())){
			errors.rejectValue(TransModel.PROPERTY_NAME, "EMPTY_"+TransModel.PROPERTY_NAME, "对象名称不能为空");
		}
		if(StringUtils.isEmpty(transModel.getCode())){
			errors.rejectValue(TransModel.PROPERTY_CODE, "EMPTY_"+TransModel.PROPERTY_CODE, "对象代码不能为空");
		}
		if(StringUtils.isEmpty(transModel.getMemo())){
			errors.rejectValue(TransModel.PROPERTY_MEMO, "EMPTY_"+TransModel.PROPERTY_MEMO, "备注不能为空");
		}
		if(StringUtils.isEmpty(transModel.getDescription())){
			errors.rejectValue(TransModel.PROPERTY_DESCIPTION, "EMPTY_"+TransModel.PROPERTY_DESCIPTION, "描述不能为空");
		}
		if(StringUtils.isEmpty(transModel.getModuleId())){
			errors.rejectValue(TransModel.PROPERTY_MODULE_ID, "EMPTY_"+TransModel.PROPERTY_MODULE_ID, "所属模块ID不能为空");
		}
		if (null == transModel.getViewIndex() ) {
			errors.rejectValue(TransModel.PROPERTY_VIEW_INDEX, "EMPTY_"+TransModel.PROPERTY_VIEW_INDEX, "展现排序不能为空");
		}

		//验证长度
		if(StringUtils.length(transModel.getName()) > 255){
			errors.rejectValue(TransModel.PROPERTY_NAME,null,"对象名称最长255个字符");
		}
		if(StringUtils.length(transModel.getCode()) > 255){
			errors.rejectValue(TransModel.PROPERTY_CODE,null,"对象代码最长255个字符");
		}
		if(StringUtils.length(transModel.getMemo()) > 255){
			errors.rejectValue(TransModel.PROPERTY_MEMO,null,"备注最长255个字符");
		}
		if(StringUtils.length(transModel.getDescription()) > 255){
			errors.rejectValue(TransModel.PROPERTY_DESCIPTION,null,"描述最长255个字符");
		}
	}
}