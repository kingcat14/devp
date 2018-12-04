package com.yunkang.saas.platform.monitor.business.notification.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 通知方式的值对象
*/
@ApiModel(value = "展现通知方式的值对象")
@Setter @Getter
public class NotificationTypeVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "通知方式", notes = "SMS、EMAIL")
    private String code;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}