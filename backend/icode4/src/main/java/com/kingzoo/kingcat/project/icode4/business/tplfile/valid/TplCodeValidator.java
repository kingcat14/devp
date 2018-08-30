package com.kingzoo.kingcat.project.icode4.business.tplfile.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.tplfile.controller.vo.TplCodeAddRequest;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TplCodeValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(TplCodeAddRequest.class.equals(aClass))
            return true;
		return TplCode.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof TplCode){
            this.validateTplCode((TplCode)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param tplCode 文件模板
     * @param errors
     */
	public void validateTplCode(TplCode tplCode, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(tplCode.getName())){
			errors.rejectValue(TplCode.PROPERTY_NAME, "EMPTY_"+TplCode.PROPERTY_NAME, "模板名称不能为空");
		}
		if(StringUtils.isEmpty(tplCode.getCode())){
			errors.rejectValue(TplCode.PROPERTY_CODE, "EMPTY_"+TplCode.PROPERTY_CODE, "模板代码不能为空");
		}
		if(StringUtils.isEmpty(tplCode.getType())){
			errors.rejectValue(TplCode.PROPERTY_TYPE, "EMPTY_"+TplCode.PROPERTY_TYPE, "模板类型不能为空");
		}
		if(StringUtils.isEmpty(tplCode.getAcceptModelTypeId())){
			errors.rejectValue(TplCode.PROPERTY_ACCEPT_MODEL_TYPE_ID, "EMPTY_"+TplCode.PROPERTY_ACCEPT_MODEL_TYPE_ID, "接收模型类型不能为空");
		}
		if(StringUtils.isEmpty(tplCode.getFilePath())){
			errors.rejectValue(TplCode.PROPERTY_FILE_PATH, "EMPTY_"+TplCode.PROPERTY_FILE_PATH, "文件路径不能为空");
		}
		if(StringUtils.isEmpty(tplCode.getFileName())){
			errors.rejectValue(TplCode.PROPERTY_FILE_NAME, "EMPTY_"+TplCode.PROPERTY_FILE_NAME, "文件名不能为空");
		}
		if (null == tplCode.getOverridable() ) {
			errors.rejectValue(TplCode.PROPERTY_OVERRIDABLE, "EMPTY_"+TplCode.PROPERTY_OVERRIDABLE, "可覆盖不能为空");
		}

		//验证长度
		if(StringUtils.length(tplCode.getName()) > 255){
			errors.rejectValue(TplCode.PROPERTY_NAME,null,"模板名称最长255个字符");
		}
		if(StringUtils.length(tplCode.getCode()) > 255){
			errors.rejectValue(TplCode.PROPERTY_CODE,null,"模板代码最长255个字符");
		}
		if(StringUtils.length(tplCode.getType()) > 255){
			errors.rejectValue(TplCode.PROPERTY_TYPE,null,"模板类型最长255个字符");
		}
		if(StringUtils.length(tplCode.getFilePath()) > 255){
			errors.rejectValue(TplCode.PROPERTY_FILE_PATH,null,"文件路径最长255个字符");
		}
		if(StringUtils.length(tplCode.getFileName()) > 255){
			errors.rejectValue(TplCode.PROPERTY_FILE_NAME,null,"文件名最长255个字符");
		}
	}
}