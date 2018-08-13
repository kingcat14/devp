package com.yunkang.saas.platform.business.resource.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class ResourceCondition implements Serializable{


	private String id;

	private Long appId;
	private String name;
	private String url;
	private String type;
	private Long code;
	private Long parentCode;
	private Integer orderIndex;

	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public Long getCode(){
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}


	public Long getParentCode() {
		return parentCode;
	}
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getOrderIndex(){
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
