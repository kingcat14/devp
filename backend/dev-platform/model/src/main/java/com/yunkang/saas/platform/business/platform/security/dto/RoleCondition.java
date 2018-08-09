package com.yunkang.saas.platform.business.platform.security.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class RoleCondition implements Serializable{

	private String id;

	private String name;
	private String description;
	private Long appId;
	private Long tenantId;

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
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
