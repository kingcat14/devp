package com.kingzoo.kingcat.project.icode4.business.view.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.view.controller.vo.ViewModelPropertyAddRequest;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ViewModelPropertyValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(ViewModelPropertyAddRequest.class.equals(aClass))
            return true;
		return ViewModelProperty.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof ViewModelProperty){
            this.validateViewModelProperty((ViewModelProperty)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param viewModelProperty 展现对象属性
     * @param errors
     */
	public void validateViewModelProperty(ViewModelProperty viewModelProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(viewModelProperty.getName())){
			errors.rejectValue(ViewModelProperty.PROPERTY_NAME, "EMPTY_"+ViewModelProperty.PROPERTY_NAME, "属性名不能为空");
		}
		if(StringUtils.isEmpty(viewModelProperty.getCode())){
			errors.rejectValue(ViewModelProperty.PROPERTY_CODE, "EMPTY_"+ViewModelProperty.PROPERTY_CODE, "属性代码不能为空");
		}
		if(StringUtils.isEmpty(viewModelProperty.getType())){
			errors.rejectValue(ViewModelProperty.PROPERTY_TYPE, "EMPTY_"+ViewModelProperty.PROPERTY_TYPE, "属性类型不能为空");
		}
		if(StringUtils.isEmpty(viewModelProperty.getViewModelId())){
			errors.rejectValue(ViewModelProperty.PROPERTY_VIEW_MODEL_ID, "EMPTY_"+ViewModelProperty.PROPERTY_VIEW_MODEL_ID, "所属展现对象不能为空");
		}
		if (null == viewModelProperty.getEditable() ) {
			errors.rejectValue(ViewModelProperty.PROPERTY_EDITABLE, "EMPTY_"+ViewModelProperty.PROPERTY_EDITABLE, "可修改不能为空");
		}
		if (null == viewModelProperty.getNullable() ) {
			errors.rejectValue(ViewModelProperty.PROPERTY_NULLABLE, "EMPTY_"+ViewModelProperty.PROPERTY_NULLABLE, "可为空不能为空");
		}
		if (null == viewModelProperty.getCoreRelation() ) {
			errors.rejectValue(ViewModelProperty.PROPERTY_CORE_RELATION, "EMPTY_"+ViewModelProperty.PROPERTY_CORE_RELATION, "核心关联不能为空");
		}
		if (null == viewModelProperty.getGridIndex() ) {
			errors.rejectValue(ViewModelProperty.PROPERTY_GRID_INDEX, "EMPTY_"+ViewModelProperty.PROPERTY_GRID_INDEX, "列表排序不能为空");
		}
		if (null == viewModelProperty.getGridHidden() ) {
			errors.rejectValue(ViewModelProperty.PROPERTY_GRID_HIDDEN, "EMPTY_"+ViewModelProperty.PROPERTY_GRID_HIDDEN, "列表隐藏不能为空");
		}
		if (null == viewModelProperty.getFormIndex() ) {
			errors.rejectValue(ViewModelProperty.PROPERTY_FORM_INDEX, "EMPTY_"+ViewModelProperty.PROPERTY_FORM_INDEX, "表单排序不能为空");
		}
		if (null == viewModelProperty.getFormHidden() ) {
			errors.rejectValue(ViewModelProperty.PROPERTY_FORM_HIDDEN, "EMPTY_"+ViewModelProperty.PROPERTY_FORM_HIDDEN, "表单隐藏不能为空");
		}
		if (null == viewModelProperty.getViewIndex() ) {
			errors.rejectValue(ViewModelProperty.PROPERTY_VIEW_INDEX, "EMPTY_"+ViewModelProperty.PROPERTY_VIEW_INDEX, "展现排序不能为空");
		}

		//验证长度
		if(StringUtils.length(viewModelProperty.getName()) > 255){
			errors.rejectValue(ViewModelProperty.PROPERTY_NAME,null,"属性名最长255个字符");
		}
		if(StringUtils.length(viewModelProperty.getCode()) > 255){
			errors.rejectValue(ViewModelProperty.PROPERTY_CODE,null,"属性代码最长255个字符");
		}
		if(StringUtils.length(viewModelProperty.getType()) > 255){
			errors.rejectValue(ViewModelProperty.PROPERTY_TYPE,null,"属性类型最长255个字符");
		}
		if(StringUtils.length(viewModelProperty.getMemo()) > 255){
			errors.rejectValue(ViewModelProperty.PROPERTY_MEMO,null,"备注最长255个字符");
		}
		if(StringUtils.length(viewModelProperty.getPropertyGroup()) > 255){
			errors.rejectValue(ViewModelProperty.PROPERTY_PROPERTY_GROUP,null,"属性组最长255个字符");
		}
	}
}