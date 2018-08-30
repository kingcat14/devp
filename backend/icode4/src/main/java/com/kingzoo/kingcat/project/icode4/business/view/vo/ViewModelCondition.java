package com.kingzoo.kingcat.project.icode4.business.view.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class ViewModelCondition implements Serializable{


	private String id;
	private Integer version;

	private String name;
	private String code;
	private String memo;
	private String description;
	private Integer viewIndex;
	private Integer viewIndexMax;
	private Integer viewIndexMin;
	private String domainModelId;
	private String moduleId;


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


	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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


	public String getDomainModelId(){
		return domainModelId;
	}
	public void setDomainModelId(String domainModelId) {
		this.domainModelId = domainModelId;
	}


	public String getModuleId(){
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
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
