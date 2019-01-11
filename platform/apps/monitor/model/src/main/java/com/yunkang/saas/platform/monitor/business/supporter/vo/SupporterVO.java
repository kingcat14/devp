package com.yunkang.saas.platform.monitor.business.supporter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 运维人员的值对象
*/
@ApiModel(value = "展现运维人员的值对象")
@Setter @Getter
public class SupporterVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "姓名")
    private String name;


    @ApiModelProperty(value = "手机号码")
    private String mobile;


    @ApiModelProperty(value = "邮箱")
    private String email;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}