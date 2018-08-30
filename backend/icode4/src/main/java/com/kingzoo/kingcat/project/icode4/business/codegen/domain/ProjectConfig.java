package com.kingzoo.kingcat.project.icode4.business.codegen.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 工程配置
 * 要把代码生成到哪个工程里面去
 */
public class ProjectConfig {

    private String name;

    private String code;

    private Integer number;

    private String basePackage;

	private String projectPath;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getBasePackage(){
		return StringUtils.trim(basePackage);
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = StringUtils.trim(basePackage);
	}

	public String getProjectPath() {
		return projectPath;
	}
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}