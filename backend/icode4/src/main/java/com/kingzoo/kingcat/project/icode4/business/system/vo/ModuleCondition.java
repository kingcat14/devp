package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class ModuleCondition implements Serializable{


	private String id;
	private Integer version;

	private String name;
	private String code;
	private String parentModuleId;
	private String systemId;


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


	public String getSystemId(){
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
