package com.yunkang.saas.platform.authentication.business.platform.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 账号密码
 * @author icode
 */
@ApiModel(value = "新增账号密码使用的DTO")
@Setter @Getter
public class AccountPasswordAddDto {

    /**账号*/
	@ApiModelProperty(value = "账号", required = false)
	private Long account;

    /**姓名*/
	@ApiModelProperty(value = "姓名", required = false)
	private String accountName;

    /**密码*/
	@ApiModelProperty(value = "密码", required = false)
	private String password;

    /**错误次数*/
	@ApiModelProperty(value = "错误次数", required = false)
	private Integer wrongCount;


	public Long getAccount(){
        return account;
    }
    public void setAccount(Long account) {
        this.account = account;
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
