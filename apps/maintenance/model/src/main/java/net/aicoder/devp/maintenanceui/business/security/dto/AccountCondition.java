package net.aicoder.devp.maintenanceui.business.security.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



public class AccountCondition implements Serializable{


	private String id;

	private String nickName;
	private String name;
	private String accountName;
	private String mobile;
	private String email;
	private String enable;


	public String getNickName(){
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getAccountName(){
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getMobile(){
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getEmail(){
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getEnable(){
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
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
