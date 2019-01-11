package com.yunkang.saas.platform.monitor.business.indicator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 指标值
 * @author icode
 */
@ApiModel(value = "修改指标值使用的DTO")
public class IndicatorValueEditDto {


	/**指标*/
	@ApiModelProperty(value = "指标", required = false)
	private String indicator;


	/**对象代码*/
	@ApiModelProperty(value = "对象代码", required = false)
	private String target;


	/**对象类型*/
	@ApiModelProperty(value = "对象类型", required = false, notes = "应用、实例")
	private String targetType;


	/**取值*/
	@ApiModelProperty(value = "取值", required = false)
	private String value;


	/**采集时间*/
	@ApiModelProperty(value = "采集时间", required = false)
	private Date collectTime;


	/**入库时间*/
	@ApiModelProperty(value = "入库时间", required = false)
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
