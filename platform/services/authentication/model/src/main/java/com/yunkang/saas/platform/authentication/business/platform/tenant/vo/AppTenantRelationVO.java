package com.yunkang.saas.platform.authentication.business.platform.tenant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;




/**
* 租户开通的应用的值对象
*/
@ApiModel(value = "展现租户开通的应用的值对象")
@Setter @Getter
public class AppTenantRelationVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "应用")
    private String app;


    @ApiModelProperty(value = "账号")
    private String account;


    /**生效日期*/
    @ApiModelProperty(value = "生效日期")
    private Date startDate;


    /**截止日期*/
    @ApiModelProperty(value = "截止日期")
    private Date endDate;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}