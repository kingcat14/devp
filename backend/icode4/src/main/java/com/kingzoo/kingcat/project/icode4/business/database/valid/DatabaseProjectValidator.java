package com.kingzoo.kingcat.project.icode4.business.database.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.database.controller.vo.DatabaseProjectAddRequest;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseProject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DatabaseProjectValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(DatabaseProjectAddRequest.class.equals(aClass))
            return true;
		return DatabaseProject.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DatabaseProject){
            this.validateDatabaseProject((DatabaseProject)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param databaseProject 数据库项目
     * @param errors
     */
	public void validateDatabaseProject(DatabaseProject databaseProject, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(databaseProject.getName())){
			errors.rejectValue(DatabaseProject.PROPERTY_NAME, "EMPTY_"+DatabaseProject.PROPERTY_NAME, "项目名称不能为空");
		}
		if(StringUtils.isEmpty(databaseProject.getUrl())){
			errors.rejectValue(DatabaseProject.PROPERTY_URL, "EMPTY_"+DatabaseProject.PROPERTY_URL, "数据库链接不能为空");
		}
		if(StringUtils.isEmpty(databaseProject.getUsername())){
			errors.rejectValue(DatabaseProject.PROPERTY_USERNAME, "EMPTY_"+DatabaseProject.PROPERTY_USERNAME, "用户名不能为空");
		}
		if(StringUtils.isEmpty(databaseProject.getPassword())){
			errors.rejectValue(DatabaseProject.PROPERTY_PASSWORD, "EMPTY_"+DatabaseProject.PROPERTY_PASSWORD, "密码不能为空");
		}
		if(StringUtils.isEmpty(databaseProject.getDriverName())){
			errors.rejectValue(DatabaseProject.PROPERTY_DRIVER_NAME, "EMPTY_"+DatabaseProject.PROPERTY_DRIVER_NAME, "驱动名词不能为空");
		}
		if (null == databaseProject.getEditable() ) {
			errors.rejectValue(DatabaseProject.PROPERTY_EDITABLE, "EMPTY_"+DatabaseProject.PROPERTY_EDITABLE, "可修改不能为空");
		}

		//验证长度
		if(StringUtils.length(databaseProject.getName()) > 255){
			errors.rejectValue(DatabaseProject.PROPERTY_NAME,null,"项目名称最长255个字符");
		}
		if(StringUtils.length(databaseProject.getUrl()) > 255){
			errors.rejectValue(DatabaseProject.PROPERTY_URL,null,"数据库链接最长255个字符");
		}
		if(StringUtils.length(databaseProject.getUsername()) > 255){
			errors.rejectValue(DatabaseProject.PROPERTY_USERNAME,null,"用户名最长255个字符");
		}
		if(StringUtils.length(databaseProject.getPassword()) > 255){
			errors.rejectValue(DatabaseProject.PROPERTY_PASSWORD,null,"密码最长255个字符");
		}
		if(StringUtils.length(databaseProject.getDriverName()) > 255){
			errors.rejectValue(DatabaseProject.PROPERTY_DRIVER_NAME,null,"驱动名词最长255个字符");
		}
	}
}