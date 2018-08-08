package com.yunkang.saas.tenant.business.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询租户使用的DTO")
public class TenantCondition implements Serializable{

	@ApiModelProperty(value = "租户代号")
	private String tenantCode;
	@ApiModelProperty(value = "租户类型")
	private Long tenantType;
	@ApiModelProperty(value = "租户类型最大值")
	private Long tenantTypeMax;
	@ApiModelProperty(value = "租户类型最小值")
	private Long tenantTypeMin;
	@ApiModelProperty(value = "租户名称")
	private String name;


	public String getTenantCode(){
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}


	public Long getTenantType(){
		return tenantType;
	}
	public void setTenantType(Long tenantType) {
		this.tenantType = tenantType;
	}

	public Long getTenantTypeMin(){
		return tenantTypeMin;
	}
	public void setTenantTypeMin(Long tenantTypeMin) {
		this.tenantTypeMin = tenantTypeMin;
	}

	public Long getTenantTypeMax(){
		return tenantTypeMax;
	}
	public void setTenantTypeMax(Long tenantTypeMax) {
		this.tenantTypeMax = tenantTypeMax;
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
