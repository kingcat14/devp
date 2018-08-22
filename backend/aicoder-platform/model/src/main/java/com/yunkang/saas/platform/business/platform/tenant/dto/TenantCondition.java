package com.yunkang.saas.platform.business.platform.tenant.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询租户使用的DTO")
public class TenantCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户代号")
	private String tenantCode;
    @ApiModelProperty(value = "租户类型")
    private String tenantType;
	@ApiModelProperty(value = "租户名称")
	private String name;
	@ApiModelProperty(value = "号码")
	private Long mobile;
	@ApiModelProperty(value = "号码最大值")
	private Long mobileMax;
	@ApiModelProperty(value = "号码最小值")
	private Long mobileMin;


	public String getTenantCode(){
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}


    public String getTenantType(){
        return tenantType;
    }
    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public Long getMobile(){
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Long getMobileMin(){
		return mobileMin;
	}
	public void setMobileMin(Long mobileMin) {
		this.mobileMin = mobileMin;
	}

	public Long getMobileMax(){
		return mobileMax;
	}
	public void setMobileMax(Long mobileMax) {
		this.mobileMax = mobileMax;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
