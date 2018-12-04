package com.yunkang.saas.platform.monitor.business.alarm.vo;

import com.yunkang.saas.platform.monitor.business.app.vo.ApplicationVO;
import com.yunkang.saas.platform.monitor.business.indicator.vo.IndicatorVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;




/**
* 告警的值对象
*/
@ApiModel(value = "展现告警的值对象")
@Setter @Getter
public class AlarmVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**类型*/
    @ApiModelProperty(value = "类型")
    private String type;
    private AlarmTypeVO typeVO;


    /**程序*/
    @ApiModelProperty(value = "程序")
    private String app;
    private ApplicationVO appVO;


    /**指标*/
    @ApiModelProperty(value = "指标")
    private String counter;
    private IndicatorVO counterVO;


    @ApiModelProperty(value = "指标值")
    private String value;


    @ApiModelProperty(value = "状态", notes = "已确认、已取消、已失效")
    private String status;


    /**发生时间*/
    @ApiModelProperty(value = "发生时间")
    private Date occurTime;


    /**最后发生时间*/
    @ApiModelProperty(value = "最后发生时间")
    private Date lastOccurTime;


    /**消失时间*/
    @ApiModelProperty(value = "消失时间")
    private Date disappearTime;


    @ApiModelProperty(value = "发生次数")
    private Integer occurCount;


    @ApiModelProperty(value = "内容", notes = "告警内容")
    private String content;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}