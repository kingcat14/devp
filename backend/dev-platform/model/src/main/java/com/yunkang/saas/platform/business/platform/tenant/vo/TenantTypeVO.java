package com.yunkang.saas.platform.business.platform.tenant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 租户类型的值对象
*/
@ApiModel(value = "展现租户类型的值对象")
public class TenantTypeVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    /**
    * 租户类型编码
    * 
    */
    @ApiModelProperty(value = "租户类型编码")
    private String tenantTypeCode;

    /**
    * 租户类型名称
    * 
    */
    @ApiModelProperty(value = "租户类型名称")
    private String name;


    public String getTenantTypeCode(){
        return tenantTypeCode;
    }
    public void setTenantTypeCode(String tenantTypeCode) {
        this.tenantTypeCode = tenantTypeCode;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
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