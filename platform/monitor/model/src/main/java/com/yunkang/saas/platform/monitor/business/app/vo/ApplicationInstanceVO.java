package com.yunkang.saas.platform.monitor.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import com.yunkang.saas.platform.monitor.business.app.vo.ApplicationVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 程序实例的值对象
*/
@ApiModel(value = "展现程序实例的值对象")
public class ApplicationInstanceVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**app*/
    @ApiModelProperty(value = "app")
    private String app;
    private ApplicationVO appVO;


    /**ip*/
    @ApiModelProperty(value = "ip")
    private String ip;


    @ApiModelProperty(value = "port")
    private Integer port;


    @ApiModelProperty(value = "运行中")
    private Boolean alive;


    @ApiModelProperty(value = "停运告警")
    private Boolean alarm;


    /**最近停运时间*/
    @ApiModelProperty(value = "最近停运时间", notes = "最近停运时间")
    private Date stopTime;


    public String getApp(){
        return app;
    }
    public void setApp(String app) {
        this.app = app;
    }
    public ApplicationVO getAppVO(){
        return appVO;
    }
    public void setAppVO(ApplicationVO appVO) {
        this.appVO = appVO;
    }

    public String getIp(){
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort(){
        return port;
    }
    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getAlive(){
        return alive;
    }
    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public Boolean getAlarm(){
        return alarm;
    }
    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }

    public Date getStopTime(){
        return stopTime;
    }
    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
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