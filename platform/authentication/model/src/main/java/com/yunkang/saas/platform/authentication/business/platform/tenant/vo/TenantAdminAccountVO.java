package com.yunkang.saas.platform.authentication.business.platform.tenant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 租户管理员账号的值对象
*/
@ApiModel(value = "展现租户管理员账号的值对象")
@Setter @Getter
public class TenantAdminAccountVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "管理员账号")
    private String accountName;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}