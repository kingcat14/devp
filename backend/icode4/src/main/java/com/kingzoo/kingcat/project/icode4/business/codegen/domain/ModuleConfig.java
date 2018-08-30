package com.kingzoo.kingcat.project.icode4.business.codegen.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ModuleConfig{

	/**定级模块的代码*/
	private String topCode;

	/**英文名称*/
    private String code;

    /**中文展现名称*/
    private String name;

    private ModuleConfig parentModule;

    private SystemConfig systemConfig;

	public String getTopCode() {
		return topCode;
	}
	public void setTopCode(String topCode) {
		this.topCode = topCode;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public ModuleConfig getParentModule() {
		return parentModule;
	}
	public void setParentModule(ModuleConfig parentModule) {
		this.parentModule = parentModule;
	}

	public SystemConfig getSystemConfig() {
		return systemConfig;
	}
	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

	@Override
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}
