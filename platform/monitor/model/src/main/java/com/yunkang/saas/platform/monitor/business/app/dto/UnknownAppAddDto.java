package com.yunkang.saas.platform.monitor.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 未知程序
 * @author icode
 */
@ApiModel(value = "新增未知程序使用的DTO")
public class UnknownAppAddDto {

    /**代码*/
	@ApiModelProperty(value = "代码", required = false)
	private String code;

    /**发现时间*/
	@ApiModelProperty(value = "发现时间", required = false)
	private Date registerTime;

    /**当前活跃*/
	@ApiModelProperty(value = "当前活跃", required = false)
	private Boolean alive;

    /**当前数量*/
	@ApiModelProperty(value = "当前数量", required = false)
	private Integer aliveCount;

    /**最大数量*/
	@ApiModelProperty(value = "最大数量", required = false)
	private Integer maxCount;

    /**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "有效，已移除")
	private String status;

    /**忽略*/
	@ApiModelProperty(value = "忽略", required = false)
	private Boolean ignored;


	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Date getRegisterTime(){
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Boolean getAlive(){
		return alive;
	}
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public Integer getAliveCount(){
		return aliveCount;
	}
	public void setAliveCount(Integer aliveCount) {
		this.aliveCount = aliveCount;
	}

	public Integer getMaxCount(){
		return maxCount;
	}
	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIgnored(){
		return ignored;
	}
	public void setIgnored(Boolean ignored) {
		this.ignored = ignored;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
