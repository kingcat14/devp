package com.yunkang.saas.bootstrap.business.platform.tenant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 租户管理员账号的值对象
*/
@ApiModel(value = "展现租户管理员账号的值对象")
public class TenantAdminAccountVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**管理员账号*/
    @ApiModelProperty(value = "管理员账号")
    private String accountName;


    public String getAccountName(){
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}