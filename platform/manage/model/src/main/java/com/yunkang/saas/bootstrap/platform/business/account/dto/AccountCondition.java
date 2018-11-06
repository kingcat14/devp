package com.yunkang.saas.bootstrap.platform.business.account.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询账号使用的DTO")
public class AccountCondition extends SaaSCondition{

	@ApiModelProperty(value = "昵称")
	private String nickName;
	@ApiModelProperty(value = "姓名")
	private String name;
	@ApiModelProperty(value = "账号")
	private String accountName;
	@ApiModelProperty(value = "手机号")
	private String mobile;
	@ApiModelProperty(value = "邮箱")
	private String email;
	@ApiModelProperty(value = "已启用")
	private Boolean enable;



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


	public Boolean getEnable(){
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
