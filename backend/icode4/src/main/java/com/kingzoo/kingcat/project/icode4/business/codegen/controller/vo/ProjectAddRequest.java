package com.kingzoo.kingcat.project.icode4.business.codegen.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 工程
 * @author icode
 */
public class ProjectAddRequest {

	public static final String PROPERTY_GROUP_CODE = "groupCode";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NUMBER = "number";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_BASE_PACKAGE = "basePackage";
	public static final String PROPERTY_PROJECT_PATH = "projectPath";
	public static final String PROPERTY_TPL_SET_ID = "tplSetId";

    /**
	 * 组名
	 * 
     */
	@Size(max = 255, message = "组名超长，最多255个字符")
	private String groupCode;

    /**
	 * 名称
	 * 
     */
	@NotNull(message = "名称不能为空")
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
	 * 代码
	 * 
     */
	@NotNull(message = "代码不能为空")
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
	 * 编号
	 * 
     */
	private Integer number;

    /**
	 * 描述
	 * 
     */
	@Size(max = 255, message = "描述超长，最多255个字符")
	private String description;

    /**
	 * 基础包
	 * 
     */
	@NotNull(message = "基础包不能为空")
	@Size(max = 255, message = "基础包超长，最多255个字符")
	private String basePackage;

    /**
	 * 工程目录
	 * 
     */
	@NotNull(message = "工程目录不能为空")
	@Size(max = 255, message = "工程目录超长，最多255个字符")
	private String projectPath;

    /**
	 * 模板集合
	 * 
     */
	@Size(max = 255, message = "模板集合超长，最多255个字符")
	private String tplSetId;


	public String getGroupCode(){
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Integer getNumber(){
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getBasePackage(){
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getProjectPath(){
		return projectPath;
	}
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getTplSetId(){
		return tplSetId;
	}
	public void setTplSetId(String tplSetId) {
		this.tplSetId = tplSetId;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
