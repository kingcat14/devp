package com.yunkang.saas.platform.monitor.business.alarm.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;



/**
 * 告警
 * @author icode
 */
@Entity()
@Table(name = "alarm_alarm")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class Alarm extends BaseEntity<String>{

	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_APP = "app";
	public static final String PROPERTY_COUNTER = "counter";
	public static final String PROPERTY_VALUE = "value";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_OCCUR_TIME = "occurTime";
	public static final String PROPERTY_LAST_OCCUR_TIME = "lastOccurTime";
	public static final String PROPERTY_DISAPPEAR_TIME = "disappearTime";
	public static final String PROPERTY_OCCUR_COUNT = "occurCount";
	public static final String PROPERTY_CONTENT = "content";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = false)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
    * 名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = false)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 类型
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
    * 程序
    * 
    */
    @Column(name = "app", nullable = true, updatable = true)
	@Size(max = 255, message = "程序超长，最多255个字符")
	private String app;

    /**
    * 指标
    * 
    */
    @Column(name = "counter", nullable = true, updatable = true)
	@Size(max = 255, message = "指标超长，最多255个字符")
	private String counter;

    /**
    * 指标值
    * 
    */
    @Column(name = "value", nullable = false, updatable = true)
	@Size(max = 255, message = "指标值超长，最多255个字符")
	private String value;

    /**
    * 状态
    * 已确认、已取消、已失效
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 发生时间
    * 
    */
    @Column(name = "occur_time", nullable = true, updatable = true)
	private Date occurTime;

    /**
    * 最后发生时间
    * 
    */
    @Column(name = "last_occur_time", nullable = true, updatable = true)
	private Date lastOccurTime;

    /**
    * 消失时间
    * 
    */
    @Column(name = "disappear_time", nullable = true, updatable = true)
	private Date disappearTime;

    /**
    * 发生次数
    * 
    */
    @Column(name = "occur_count", nullable = true, updatable = true)
	private Integer occurCount;

    /**
    * 内容
    * 告警内容
    */
    @Column(name = "content", nullable = true, updatable = true)
	@Size(max = 255, message = "内容超长，最多255个字符")
	private String content;

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getApp(){
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}

	public String getCounter(){
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
	}

	public String getValue(){
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOccurTime(){
		return occurTime;
	}
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public Date getLastOccurTime(){
		return lastOccurTime;
	}
	public void setLastOccurTime(Date lastOccurTime) {
		this.lastOccurTime = lastOccurTime;
	}

	public Date getDisappearTime(){
		return disappearTime;
	}
	public void setDisappearTime(Date disappearTime) {
		this.disappearTime = disappearTime;
	}

	public Integer getOccurCount(){
		return occurCount;
	}
	public void setOccurCount(Integer occurCount) {
		this.occurCount = occurCount;
	}

	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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

