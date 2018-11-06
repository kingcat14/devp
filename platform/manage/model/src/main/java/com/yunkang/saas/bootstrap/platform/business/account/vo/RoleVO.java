package com.yunkang.saas.bootstrap.platform.business.account.vo;

import com.yunkang.saas.bootstrap.platform.business.application.vo.AppVO;
import com.yunkang.saas.bootstrap.platform.business.tenant.vo.TenantVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 角色的值对象
*/
@ApiModel(value = "展现角色的值对象")
public class RoleVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    /**
    * 角色名称
    * 
    */
    private String name;

    @ApiModelProperty(value = "角色描述")
    /**角色描述*/
    private String description;

    @ApiModelProperty(value = "租户")
    private Long tenantId;
    private TenantVO tenantVO;

    @ApiModelProperty(value = "应用")
    private String appCode;
    private AppVO appVO;


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

    public String getAppCode() {
        return appCode;
    }
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public AppVO getAppVO() {
        return appVO;
    }
    public void setAppVO(AppVO appVO) {
        this.appVO = appVO;
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