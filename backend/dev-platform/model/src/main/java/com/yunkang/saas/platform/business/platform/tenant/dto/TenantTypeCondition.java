package com.yunkang.saas.platform.business.platform.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询租户类型使用的DTO")
public class TenantTypeCondition implements Serializable{

	@ApiModelProperty(value = "租户类型编码")
	private String tenantTypeCode;
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




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
