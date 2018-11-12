package com.yunkang.saas.platform.monitor.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 程序
 * @author icode
 */
@ApiModel(value = "新增程序使用的DTO")
public class ApplicationAddDto {

    /**名称*/
	@ApiModelProperty(value = "名称", required = true)
	private String name;

    /**代码*/
	@ApiModelProperty(value = "代码", required = false)
	private String code;

    /**配置实例数量*/
	@ApiModelProperty(value = "配置实例数量", required = false)
	private Integer totalCount;

    /**当前实例数量*/
	@ApiModelProperty(value = "当前实例数量", required = false)
	private Integer aliveCount;

    /**低实例告警*/
	@ApiModelProperty(value = "低实例告警", required = false, notes = "")
	private Boolean alarm;

    /**启动监控*/
	@ApiModelProperty(value = "启动监控", required = false)
	private Boolean enable;

    /**告警数量*/
	@ApiModelProperty(value = "告警数量", required = false)
	private Integer thresholdValue;

    /**当前状态*/
	@ApiModelProperty(value = "当前状态", required = false, notes = "正常、异常")
	private String status;


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Integer getTotalCount(){
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getAliveCount(){
		return aliveCount;
	}
	public void setAliveCount(Integer aliveCount) {
		this.aliveCount = aliveCount;
	}

	public Boolean getAlarm(){
		return alarm;
	}
	public void setAlarm(Boolean alarm) {
		this.alarm = alarm;
	}

	public Boolean getEnable(){
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Integer getThresholdValue(){
		return thresholdValue;
	}
	public void setThresholdValue(Integer thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
