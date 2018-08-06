package com.yunkang.saas.security.model.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class RoleResourceRelationCondition implements Serializable{


	private String id;

	private Long roleId;
	private Long roleIdMax;
	private Long roleIdMin;
	private Long resourceId;
	private Long resourceIdMax;
	private Long resourceIdMin;


	public Long getRoleId(){
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRoleIdMin(){
		return roleIdMin;
	}
	public void setRoleIdMin(Long roleIdMin) {
		this.roleIdMin = roleIdMin;
	}

	public Long getRoleIdMax(){
		return roleIdMax;
	}
	public void setRoleIdMax(Long roleIdMax) {
		this.roleIdMax = roleIdMax;
	}


	public Long getResourceId(){
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getResourceIdMin(){
		return resourceIdMin;
	}
	public void setResourceIdMin(Long resourceIdMin) {
		this.resourceIdMin = resourceIdMin;
	}

	public Long getResourceIdMax(){
		return resourceIdMax;
	}
	public void setResourceIdMax(Long resourceIdMax) {
		this.resourceIdMax = resourceIdMax;
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
