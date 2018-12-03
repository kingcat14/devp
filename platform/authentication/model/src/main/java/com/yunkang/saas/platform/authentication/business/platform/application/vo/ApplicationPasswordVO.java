package com.yunkang.saas.platform.authentication.business.platform.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用密码的值对象
*/
@ApiModel(value = "展现应用密码的值对象")
@Setter @Getter
public class ApplicationPasswordVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**应用*/
    @ApiModelProperty(value = "应用")
    private Long appId;
    private AppVO appIdVO;


    @ApiModelProperty(value = "访问ID")
    private String accessId;


    @ApiModelProperty(value = "访问密码")
    private String accessKey;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}