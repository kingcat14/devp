package com.yunkang.saas.platform.monitor.business.indicator.domain;

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
 * 指标值
 * @author icode
 */
@Entity()
@Table(name = "indicator_indicator_value")
//@DynamicUpdate
//@DynamicInsert
public class IndicatorValue extends BaseEntity<String>{

	public static final String PROPERTY_INDICATOR = "indicator";
	public static final String PROPERTY_TARGET = "target";
	public static final String PROPERTY_TARGET_TYPE = "targetType";
	public static final String PROPERTY_VALUE = "value";
	public static final String PROPERTY_COLLECT_TIME = "collectTime";
	public static final String PROPERTY_SAVE_TIME = "saveTime";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 指标
    * 
    */
    @Column(name = "indicator", nullable = false, updatable = true)
	@Size(max = 255, message = "指标超长，最多255个字符")
	private String indicator;

    /**
    * 对象代码
    * 
    */
    @Column(name = "target", nullable = false, updatable = true)
	@Size(max = 255, message = "对象代码超长，最多255个字符")
	private String target;

    /**
    * 对象类型
    * 应用、实例
    */
    @Column(name = "target_type", nullable = true, updatable = true)
	@Size(max = 255, message = "对象类型超长，最多255个字符")
	private String targetType;

    /**
    * 取值
    * 
    */
    @Column(name = "value", nullable = false, updatable = true)
	@Size(max = 255, message = "取值超长，最多255个字符")
	private String value;

    /**
    * 采集时间
    * 
    */
    @Column(name = "collect_time", nullable = false, updatable = false)
	private Date collectTime;

    /**
    * 入库时间
    * 
    */
    @Column(name = "save_time", nullable = false, updatable = true)
	private Date saveTime;

	public String getIndicator(){
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getTarget(){
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}

	public String getTargetType(){
		return targetType;
	}
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getValue(){
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Date getCollectTime(){
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	public Date getSaveTime(){
		return saveTime;
	}
	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
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

