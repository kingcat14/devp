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
 * 程序实例
 * @author icode
 */
@ApiModel(value = "修改程序实例使用的DTO")
public class ApplicationInstanceEditDto {


	/**app*/
	@ApiModelProperty(value = "app", required = false)
	private String app;


	/**ip*/
	@ApiModelProperty(value = "ip", required = false)
	private String ip;


	/**port*/
	@ApiModelProperty(value = "port", required = false)
	private Integer port;


	/**运行中*/
	@ApiModelProperty(value = "运行中", required = false)
	private Boolean alive;


	/**停运告警*/
	@ApiModelProperty(value = "停运告警", required = false)
	private Boolean alarm;


	/**最近停运时间*/
	@ApiModelProperty(value = "最近停运时间", required = false, notes = "最近停运时间")
	private Date stopTime;



	public String getApp(){
        return app;
    }
    public void setApp(String app) {
        this.app = app;
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
