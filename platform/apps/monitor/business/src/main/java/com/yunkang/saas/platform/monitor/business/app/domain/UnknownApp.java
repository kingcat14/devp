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
 * 未知程序
 * @author icode
 */
@Entity()
@Table(name = "app_unknown_app")
//@DynamicUpdate
//@DynamicInsert
public class UnknownApp extends BaseEntity<String>{

	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_REGISTER_TIME = "registerTime";
	public static final String PROPERTY_ALIVE = "alive";
	public static final String PROPERTY_ALIVE_COUNT = "aliveCount";
	public static final String PROPERTY_MAX_COUNT = "maxCount";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_IGNORED = "ignored";


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
    * 发现时间
    * 
    */
    @Column(name = "register_time", nullable = false, updatable = false)
	private Date registerTime;

    /**
    * 当前活跃
    * 
    */
    @Column(name = "alive", nullable = false, updatable = true)
	private Boolean alive;

    /**
    * 当前数量
    * 
    */
    @Column(name = "alive_count", nullable = false, updatable = true)
	private Integer aliveCount;

    /**
    * 最大数量
    * 
    */
    @Column(name = "max_count", nullable = false, updatable = true)
	private Integer maxCount;

    /**
    * 状态
    * 有效，已移除
    */
    @Column(name = "status", nullable = true, updatable = true)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
    * 忽略
    * 
    */
    @Column(name = "ignored", nullable = true, updatable = true)
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

