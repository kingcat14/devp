package com.yunkang.saas.platform.monitor.business.supporter.vo;

import com.yunkang.saas.platform.monitor.business.app.vo.ApplicationVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 支持应用的值对象
*/
@ApiModel(value = "展现支持应用的值对象")
@Setter @Getter
public class SupporterAppRelationVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**运维人员*/
    @ApiModelProperty(value = "运维人员")
    private String supporter;
    private SupporterVO supporterVO;


    /**支持程序*/
    @ApiModelProperty(value = "支持程序")
    private String application;
    private ApplicationVO applicationVO;


    @ApiModelProperty(value = "接收通知方式", notes = "逗号分隔的告警方式")
    private String notificationType;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}