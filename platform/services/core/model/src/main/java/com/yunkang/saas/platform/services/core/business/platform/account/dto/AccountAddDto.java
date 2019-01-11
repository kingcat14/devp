package com.yunkang.saas.platform.services.core.business.platform.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 账号
 * @author icode
 */
@ApiModel(value = "新增账号使用的DTO")
@Setter @Getter
public class AccountAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**昵称*/
	@ApiModelProperty(value = "昵称", required = false)
	private String nickName;

    /**姓名*/
	@ApiModelProperty(value = "姓名", required = false)
	private String name;

    /**账号*/
	@ApiModelProperty(value = "账号", required = false)
	private String accountName;

    /**密码*/
	@ApiModelProperty(value = "密码", required = false)
	private String accountPassword;

    /**手机号*/
	@ApiModelProperty(value = "手机号", required = false)
	private String mobile;

    /**邮箱*/
	@ApiModelProperty(value = "邮箱", required = false)
	private String email;

    /**已启用*/
	@ApiModelProperty(value = "已启用", required = false)
	private Boolean enable;

    /**已过期*/
	@ApiModelProperty(value = "已过期", required = false)
	private Boolean expired;

    /**已锁定*/
	@ApiModelProperty(value = "已锁定", required = false)
	private Boolean locked;

    /**已删除*/
	@ApiModelProperty(value = "已删除", required = false)
	private Boolean deleted;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

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

	public String getAccountPassword(){
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
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

	public Boolean getExpired(){
		return expired;
	}
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	public Boolean getLocked(){
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getDeleted(){
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
