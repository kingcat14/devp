package com.kingzoo.kingcat.project.icode4.business.system.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 领域根对象
 * @author icode
 */
public class DomainModelAddRequest {

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_VIEW_INDEX = "viewIndex";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_MODULE_ID = "moduleId";
	public static final String PROPERTY_SYSTEM_ID = "systemId";
	public static final String PROPERTY_PERSIST = "persist";

    /**
	 * 对象名称
	 * 
     */
	@NotNull(message = "对象名称不能为空")
	@Size(max = 255, message = "对象名称超长，最多255个字符")
	private String name;

    /**
	 * 对象代码
	 * 
     */
	@NotNull(message = "对象代码不能为空")
	@Size(max = 255, message = "对象代码超长，最多255个字符")
	private String code;

    /**
	 * 排序
	 * 
     */
	private Integer viewIndex;

    /**
	 * 描述
	 * 
     */
	private String description;

    /**
	 * 所属模块
	 * 
     */
	@NotNull(message = "所属模块不能为空")
	private String moduleId;
	private String moduleName;

    /**
	 * 所属系统
	 * 
     */
	private String systemId;
	private String systemName;

    /**
	 * 是否持久化
	 * 
     */
	@NotNull(message = "是否持久化不能为空")
	private Boolean persist;


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

	public Integer getViewIndex(){
		return viewIndex;
	}
	public void setViewIndex(Integer viewIndex) {
		this.viewIndex = viewIndex;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getModuleId(){
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName(){
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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

	public Boolean getPersist(){
		return persist;
	}
	public void setPersist(Boolean persist) {
		this.persist = persist;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
