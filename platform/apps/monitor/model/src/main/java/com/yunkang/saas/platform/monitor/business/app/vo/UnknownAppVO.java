package com.yunkang.saas.platform.monitor.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 未知程序的值对象
*/
@ApiModel(value = "展现未知程序的值对象")
public class UnknownAppVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**代码*/
    @ApiModelProperty(value = "代码")
    private String code;


    /**发现时间*/
    @ApiModelProperty(value = "发现时间")
    private Date registerTime;


    @ApiModelProperty(value = "当前活跃")
    private Boolean alive;


    @ApiModelProperty(value = "当前数量")
    private Integer aliveCount;


    @ApiModelProperty(value = "最大数量")
    private Integer maxCount;


    /**状态*/
    @ApiModelProperty(value = "状态", notes = "有效，已移除")
    private String status;


    @ApiModelProperty(value = "忽略")
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