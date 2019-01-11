package com.yunkang.saas.platform.monitor.business.app.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 程序实例
 * @author icode
 */
@Entity()
@Table(name = "app_application_instance")
//@DynamicUpdate
//@DynamicInsert
public class ApplicationInstance extends BaseEntity<String>{

	public static final String PROPERTY_APP = "app";
	public static final String PROPERTY_HOST = "host";
	public static final String PROPERTY_PORT = "port";
	public static final String PROPERTY_ALIVE = "alive";
	public static final String PROPERTY_ALARM = "alarm";
	public static final String PROPERTY_ALIVE_TIME = "aliveTime";
	public static final String PROPERTY_DETECTION_TIME = "detectionTime";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 应用
    * 
    */
    @Column(name = "app", nullable = false, updatable = true)
	@Size(max = 255, message = "应用超长，最多255个字符")
	private String app;

    /**
    * 主机
    * 
    */
    @Column(name = "host", nullable = false, updatable = true)
	@Size(max = 255, message = "主机超长，最多255个字符")
	private String host;

    /**
    * 端口
    * 
    */
    @Column(name = "port", nullable = false, updatable = true)
	private Integer port;

    /**
    * 运行中
    * 
    */
    @Column(name = "alive", nullable = true, updatable = true)
	private Boolean alive;

    /**
    * 停运告警
    * 
    */
    @Column(name = "alarm", nullable = true, updatable = true)
	private Boolean alarm;

    /**
    * 最近活跃时间
    * 最近活跃时间
    */
    @Column(name = "alive_time", nullable = true, updatable = true)
	private Date aliveTime;

    /**
    * 最近检测时间
    * 
    */
    @Column(name = "detection_time", nullable = true, updatable = true)
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

