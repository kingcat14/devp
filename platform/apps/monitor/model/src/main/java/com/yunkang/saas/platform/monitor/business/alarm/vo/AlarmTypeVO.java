package com.yunkang.saas.platform.monitor.business.alarm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 告警类型的值对象
*/
@ApiModel(value = "展现告警类型的值对象")
@Setter @Getter
public class AlarmTypeVO {

    @ApiModelProperty(value = "记录id")
    private String id;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}