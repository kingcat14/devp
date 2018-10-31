package com.yunkang.saas.bootstrap.platform.business.platform.security.vo;

import com.yunkang.saas.bootstrap.platform.business.platform.tenant.vo.TenantVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 账号的值对象
*/
@ApiModel(value = "展现账号的值对象")
public class AccountVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    /**
    * 昵称
    * 
    */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
    * 姓名
    * 
    */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
    * 账号
    * 
    */
    @ApiModelProperty(value = "账号")
    private String accountName;

    /**
    * 手机号
    * 
    */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
    * 邮箱
    * 
    */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
    * 已启用
    * 
    */
    @ApiModelProperty(value = "已启用")
    private Boolean enable;


    @ApiModelProperty(value = "租户")
    private Long tenantId;
    private TenantVO tenantVO;


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

    public Long getTenantId() {
        return tenantId;
    }
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public TenantVO getTenantVO() {
        return tenantVO;
    }
    public void setTenantVO(TenantVO tenantVO) {
        this.tenantVO = tenantVO;
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