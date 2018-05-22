package net.aicoder.devp.maintenanceui.business.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 账号角色关联的值对象
*/
@ApiModel(value = "展现账号角色关联的值对象")
public class AccountRoleRelationVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "账号Id")
    /**
    * 账号Id
    * 
    */
    private Long accountId;

    @ApiModelProperty(value = "角色Id")
    /**
    * 角色Id
    * 
    */
    private Long roleId;


    public Long getAccountId(){
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public Long getRoleId(){
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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