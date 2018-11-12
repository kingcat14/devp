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
	public static final String PROPERTY_IP = "ip";
	public static final String PROPERTY_PORT = "port";
	public static final String PROPERTY_ALIVE = "alive";
	public static final String PROPERTY_ALARM = "alarm";
	public static final String PROPERTY_STOP_TIME = "stopTime";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * app
    * 
    */
    @Column(name = "app", nullable = true, updatable = true)
	@Size(max = 255, message = "app超长，最多255个字符")
	private String app;

    /**
    * ip
    * 
    */
    @Column(name = "ip", nullable = false, updatable = true)
	@Size(max = 255, message = "ip超长，最多255个字符")
	private String ip;

    /**
    * port
    * 
    */
    @Column(name = "port", nullable = true, updatable = true)
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
    * 最近停运时间
    * 最近停运时间
    */
    @Column(name = "stop_time", nullable = true, updatable = true)
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

