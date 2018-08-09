package com.yunkang.saas.platform.business.platform.security.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class AccountRoleRelationCondition implements Serializable{


	private String id;

	private Long accountId;
	private Long accountIdMax;
	private Long accountIdMin;
	private Long roleId;
	private Long roleIdMax;
	private Long roleIdMin;


	public Long getAccountId(){
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getAccountIdMin(){
		return accountIdMin;
	}
	public void setAccountIdMin(Long accountIdMin) {
		this.accountIdMin = accountIdMin;
	}

	public Long getAccountIdMax(){
		return accountIdMax;
	}
	public void setAccountIdMax(Long accountIdMax) {
		this.accountIdMax = accountIdMax;
	}


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
