package com.yunkang.saas.platform.monitor.business.indicator.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;




/**
* 指标值的值对象
*/
@ApiModel(value = "展现指标值的值对象")
public class IndicatorValueVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**指标*/
    @ApiModelProperty(value = "指标")
    private String indicator;
    private IndicatorVO indicatorVO;


    /**对象代码*/
    @ApiModelProperty(value = "对象代码")
    private String target;


    /**对象类型*/
    @ApiModelProperty(value = "对象类型", notes = "应用、实例")
    private String targetType;


    /**取值*/
    @ApiModelProperty(value = "取值")
    private String value;


    /**采集时间*/
    @ApiModelProperty(value = "采集时间")
    private Date collectTime;


    /**入库时间*/
    @ApiModelProperty(value = "入库时间")
    private Date saveTime;


    public String getIndicator(){
        return indicator;
    }
    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }
    public IndicatorVO getIndicatorVO(){
        return indicatorVO;
    }
    public void setIndicatorVO(IndicatorVO indicatorVO) {
        this.indicatorVO = indicatorVO;
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