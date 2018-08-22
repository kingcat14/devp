package com.yunkang.saas.platform.business.platform.application.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询应用密码使用的DTO")
public class ApplicationPasswordCondition extends SaaSCondition{

	@ApiModelProperty(value = "应用Id")
	private Long appId;
	@ApiModelProperty(value = "应用Id最大值")
	private Long appIdMax;
	@ApiModelProperty(value = "应用Id最小值")
	private Long appIdMin;
	@ApiModelProperty(value = "访问ID")
	private String accessId;


	public Long getAppId(){
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getAppIdMin(){
		return appIdMin;
	}
	public void setAppIdMin(Long appIdMin) {
		this.appIdMin = appIdMin;
	}

	public Long getAppIdMax(){
		return appIdMax;
	}
	public void setAppIdMax(Long appIdMax) {
		this.appIdMax = appIdMax;
	}


	public String getAccessId(){
		return accessId;
	}
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
