package com.kingzoo.kingcat.project.icode4.business.view.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.view.controller.vo.ViewModelAddRequest;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ViewModelValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(ViewModelAddRequest.class.equals(aClass))
            return true;
		return ViewModel.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof ViewModel){
            this.validateViewModel((ViewModel)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param viewModel 展现对象
     * @param errors
     */
	public void validateViewModel(ViewModel viewModel, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(viewModel.getName())){
			errors.rejectValue(ViewModel.PROPERTY_NAME, "EMPTY_"+ViewModel.PROPERTY_NAME, "对象名称不能为空");
		}
		if(StringUtils.isEmpty(viewModel.getCode())){
			errors.rejectValue(ViewModel.PROPERTY_CODE, "EMPTY_"+ViewModel.PROPERTY_CODE, "对象代码不能为空");
		}
		if(StringUtils.isEmpty(viewModel.getMemo())){
			errors.rejectValue(ViewModel.PROPERTY_MEMO, "EMPTY_"+ViewModel.PROPERTY_MEMO, "备注不能为空");
		}
		if(StringUtils.isEmpty(viewModel.getDescription())){
			errors.rejectValue(ViewModel.PROPERTY_DESCIPTION, "EMPTY_"+ViewModel.PROPERTY_DESCIPTION, "描述不能为空");
		}
		if(StringUtils.isEmpty(viewModel.getModuleId())){
			errors.rejectValue(ViewModel.PROPERTY_MODULE_ID, "EMPTY_"+ViewModel.PROPERTY_MODULE_ID, "所属模块ID不能为空");
		}

		//验证长度
		if(StringUtils.length(viewModel.getName()) > 255){
			errors.rejectValue(ViewModel.PROPERTY_NAME,null,"对象名称最长255个字符");
		}
		if(StringUtils.length(viewModel.getCode()) > 255){
			errors.rejectValue(ViewModel.PROPERTY_CODE,null,"对象代码最长255个字符");
		}
		if(StringUtils.length(viewModel.getMemo()) > 255){
			errors.rejectValue(ViewModel.PROPERTY_MEMO,null,"备注最长255个字符");
		}
		if(StringUtils.length(viewModel.getDescription()) > 9999){
			errors.rejectValue(ViewModel.PROPERTY_DESCIPTION,null,"描述最长1000个字符");
		}
	}
}