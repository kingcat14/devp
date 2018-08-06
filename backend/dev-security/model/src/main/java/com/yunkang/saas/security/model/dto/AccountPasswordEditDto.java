package com.yunkang.saas.security.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Size;


/**
 * 账号密码
 * @author icode
 */
@ApiModel(value = "修改账号密码使用的DTO")
public class AccountPasswordEditDto {

    /**
	 * 账号Id
	 * 
     */
	@ApiModelProperty(value = "账号Id", required = false)
	@Size(max = 255, message = "账号Id超长，最多255个字符")
	private String accountId;

    /**
	 * 账号账号
	 * 
     */
	@ApiModelProperty(value = "账号账号", required = false)
	@Size(max = 255, message = "账号账号超长，最多255个字符")
	private String accountName;

    /**
	 * 密码
	 * 
     */
	@ApiModelProperty(value = "密码", required = false)
	@Size(max = 255, message = "密码超长，最多255个字符")
	private String password;

    /**
	 * 错误次数
	 * 
     */
	@ApiModelProperty(value = "错误次数", required = false)
	private Integer wrongCount;


	public String getAccountId(){
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName(){
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getWrongCount(){
		return wrongCount;
	}
	public void setWrongCount(Integer wrongCount) {
		this.wrongCount = wrongCount;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
