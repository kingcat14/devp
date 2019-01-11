package com.yunkang.saas.platform.monitor.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 程序的值对象
*/
@ApiModel(value = "展现程序的值对象")
public class ApplicationVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;


    /**代码*/
    @ApiModelProperty(value = "代码")
    private String code;


    @ApiModelProperty(value = "配置实例数量")
    private Integer totalCount;


    @ApiModelProperty(value = "当前实例数量")
    private Integer aliveCount;


    @ApiModelProperty(value = "低实例告警", notes = "")
    private Boolean alarm;


    @ApiModelProperty(value = "启动监控")
    private Boolean enable;


    @ApiModelProperty(value = "告警数量")
    private Integer thresholdValue;


    /**当前状态*/
    @ApiModelProperty(value = "当前状态", notes = "正常、异常")
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


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}