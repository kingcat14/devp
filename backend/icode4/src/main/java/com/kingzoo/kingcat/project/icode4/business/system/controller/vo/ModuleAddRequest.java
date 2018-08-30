package com.kingzoo.kingcat.project.icode4.business.system.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 模块
 * @author icode
 */
public class ModuleAddRequest {

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_PARENT_MODULE_ID = "parentModuleId";
	public static final String PROPERTY_SYSTEM_ID = "systemId";

    /**
	 * 模块名称
	 * 
     */
	@NotNull(message = "模块名称不能为空")
	@Size(max = 255, message = "模块名称超长，最多255个字符")
	private String name;

    /**
	 * 模块代码
	 * 
     */
	@NotNull(message = "模块代码不能为空")
	@Size(max = 255, message = "模块代码超长，最多255个字符")
	private String code;

    /**
	 * 父模块ID
	 * 
     */
	private String parentModuleId;
	private String parentModuleName;

    /**
	 * 所属系统
	 * 
     */
	@NotNull(message = "所属系统不能为空")
	private String systemId;
	private String systemName;


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

	public String getParentModuleId(){
		return parentModuleId;
	}
	public void setParentModuleId(String parentModuleId) {
		this.parentModuleId = parentModuleId;
	}
	public String getParentModuleName(){
		return parentModuleName;
	}
	public void setParentModuleName(String parentModuleName) {
		this.parentModuleName = parentModuleName;
	}

	public String getSystemId(){
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getSystemName(){
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
