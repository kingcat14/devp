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


	/**应用*/
	@ApiModelProperty(value = "应用", required = false)
	private String app;


	/**主机*/
	@ApiModelProperty(value = "主机", required = false)
	private String host;


	/**端口*/
	@ApiModelProperty(value = "端口", required = false)
	private Integer port;


	/**运行中*/
	@ApiModelProperty(value = "运行中", required = false)
	private Boolean alive;


	/**停运告警*/
	@ApiModelProperty(value = "停运告警", required = false)
	private Boolean alarm;


	/**最近活跃时间*/
	@ApiModelProperty(value = "最近活跃时间", required = false, notes = "最近活跃时间")
	private Date aliveTime;


	/**最近检测时间*/
	@ApiModelProperty(value = "最近检测时间", required = false)
	private Date detectionTime;



	public String getApp(){
        return app;
    }
    public void setApp(String app) {
        this.app = app;
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
