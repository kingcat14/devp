package com.yunkang.saas.platform.authentication.business.platform.tenant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 租户的值对象
*/
@ApiModel(value = "展现租户的值对象")
@Setter @Getter
public class TenantVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "租户代号")
    private String tenantCode;


    /**租户类型*/
    @ApiModelProperty(value = "租户类型")
    private Long tenantType;
    private TenantTypeVO tenantTypeVO;


    @ApiModelProperty(value = "租户名称")
    private String name;


    @ApiModelProperty(value = "国家")
    private String country;


    @ApiModelProperty(value = "省份")
    private String province;


    @ApiModelProperty(value = "市、县")
    private String city;


    @ApiModelProperty(value = "详细地址")
    private String address;


    @ApiModelProperty(value = "传真")
    private String fax;


    @ApiModelProperty(value = "联系电话")
    private String telephoneNo;


    @ApiModelProperty(value = "CRM系统代码")
    private String crmCode;


    @ApiModelProperty(value = "域名前缀")
    private String prefixDomainName;


    @ApiModelProperty(value = "号码")
    private Long mobile;


    @ApiModelProperty(value = "启用")
    private Boolean status;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}