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


    /**应用*/
    @ApiModelProperty(value = "应用")
    private String app;
    private ApplicationVO appVO;


    /**主机*/
    @ApiModelProperty(value = "主机")
    private String host;


    @ApiModelProperty(value = "端口")
    private Integer port;


    @ApiModelProperty(value = "运行中")
    private Boolean alive;


    @ApiModelProperty(value = "停运告警")
    private Boolean alarm;


    /**最近活跃时间*/
    @ApiModelProperty(value = "最近活跃时间", notes = "最近活跃时间")
    private Date aliveTime;


    /**最近检测时间*/
    @ApiModelProperty(value = "最近检测时间")
    private Date detectionTime;


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

    public String getHost(){
        return host;
    }
    public void setHost(String host) {
        this.host = host;
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

    public Date getAliveTime(){
        return aliveTime;
    }
    public void setAliveTime(Date aliveTime) {
        this.aliveTime = aliveTime;
    }

    public Date getDetectionTime(){
        return detectionTime;
    }
    public void setDetectionTime(Date detectionTime) {
        this.detectionTime = detectionTime;
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