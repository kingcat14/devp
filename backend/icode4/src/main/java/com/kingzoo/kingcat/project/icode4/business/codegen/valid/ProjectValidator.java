package com.kingzoo.kingcat.project.icode4.business.codegen.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.codegen.controller.vo.ProjectAddRequest;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.Project;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProjectValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(ProjectAddRequest.class.equals(aClass))
            return true;
		return Project.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof Project){
            this.validateProject((Project)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param project 工程
     * @param errors
     */
	public void validateProject(Project project, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(project.getName())){
			errors.rejectValue(Project.PROPERTY_NAME, "EMPTY_"+Project.PROPERTY_NAME, "名称不能为空");
		}
		if(StringUtils.isEmpty(project.getCode())){
			errors.rejectValue(Project.PROPERTY_CODE, "EMPTY_"+Project.PROPERTY_CODE, "代码不能为空");
		}
		if(StringUtils.isEmpty(project.getBasePackage())){
			errors.rejectValue(Project.PROPERTY_BASE_PACKAGE, "EMPTY_"+Project.PROPERTY_BASE_PACKAGE, "基础包不能为空");
		}
		if(StringUtils.isEmpty(project.getProjectPath())){
			errors.rejectValue(Project.PROPERTY_PROJECT_PATH, "EMPTY_"+Project.PROPERTY_PROJECT_PATH, "工程目录不能为空");
		}

		//验证长度
		if(StringUtils.length(project.getGroupCode()) > 255){
			errors.rejectValue(Project.PROPERTY_GROUP_CODE,null,"组名最长255个字符");
		}
		if(StringUtils.length(project.getName()) > 255){
			errors.rejectValue(Project.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(project.getCode()) > 255){
			errors.rejectValue(Project.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(project.getDescription()) > 255){
			errors.rejectValue(Project.PROPERTY_DESCRIPTION,null,"描述最长255个字符");
		}
		if(StringUtils.length(project.getBasePackage()) > 255){
			errors.rejectValue(Project.PROPERTY_BASE_PACKAGE,null,"基础包最长255个字符");
		}
		if(StringUtils.length(project.getProjectPath()) > 255){
			errors.rejectValue(Project.PROPERTY_PROJECT_PATH,null,"工程目录最长255个字符");
		}
		if(StringUtils.length(project.getTplSetId()) > 255){
			errors.rejectValue(Project.PROPERTY_TPL_SET_ID,null,"模板集合最长255个字符");
		}
	}
}