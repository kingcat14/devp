package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class DomainModelCondition implements Serializable{


	private String id;
	private Integer version;

	private String name;
	private String code;
	private Integer viewIndex;
	private Integer viewIndexMax;
	private Integer viewIndexMin;
	private String description;
	private String moduleId;
	private String systemId;
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

	public Integer getViewIndexMin(){
		return viewIndexMin;
	}
	public void setViewIndexMin(Integer viewIndexMin) {
		this.viewIndexMin = viewIndexMin;
	}

	public Integer getViewIndexMax(){
		return viewIndexMax;
	}
	public void setViewIndexMax(Integer viewIndexMax) {
		this.viewIndexMax = viewIndexMax;
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


	public String getSystemId(){
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}


	public Boolean getPersist(){
		return persist;
	}
	public void setPersist(Boolean persist) {
		this.persist = persist;
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
