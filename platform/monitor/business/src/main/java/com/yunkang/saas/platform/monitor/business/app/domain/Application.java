package com.yunkang.saas.platform.monitor.business.app.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 程序
 * @author icode
 */
@Entity()
@Table(name = "app_application")
//@DynamicUpdate
//@DynamicInsert
public class Application extends BaseEntity<String>{

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_TOTAL_COUNT = "totalCount";
	public static final String PROPERTY_ALIVE_COUNT = "aliveCount";
	public static final String PROPERTY_ALARM = "alarm";
	public static final String PROPERTY_ENABLE = "enable";
	public static final String PROPERTY_THRESHOLD_VALUE = "thresholdValue";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = false)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
    * 配置实例数量
    * 
    */
    @Column(name = "total_count", nullable = true, updatable = true)
	private Integer totalCount;

    /**
    * 当前实例数量
    * 
    */
    @Column(name = "alive_count", nullable = true, updatable = true)
	private Integer aliveCount;

    /**
    * 低实例告警
    * 
    */
    @Column(name = "alarm", nullable = false, updatable = true)
	private Boolean alarm;

    /**
    * 启动监控
    * 
    */
    @Column(name = "enable", nullable = true, updatable = true)
	private Boolean enable;

    /**
    * 告警数量
    * 
    */
    @Column(name = "threshold_value", nullable = true, updatable = true)
	private Integer thresholdValue;

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

