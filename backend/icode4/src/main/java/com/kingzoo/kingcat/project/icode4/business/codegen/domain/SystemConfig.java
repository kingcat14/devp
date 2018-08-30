package com.kingzoo.kingcat.project.icode4.business.codegen.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class SystemConfig {

    private String name;

    private String code;

    private String basePackage;

    private String ui;

    private String dao;

    private List<ModuleConfig> moduleConfigList;

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
    
    public String getBasePackage(){
		return StringUtils.trim(basePackage);
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = StringUtils.trim(basePackage);
	}

	public String getUi(){
		return ui;
	}
	public void setUi(String ui) {
		this.ui = ui;
	}
    
    public String getDao(){
		return dao;
	}
	public void setDao(String dao) {
		this.dao = dao;
	}
	
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}