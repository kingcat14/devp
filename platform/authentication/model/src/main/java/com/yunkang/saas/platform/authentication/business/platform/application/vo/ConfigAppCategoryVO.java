package com.yunkang.saas.platform.authentication.business.platform.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用类别的值对象
*/
@ApiModel(value = "展现应用类别的值对象")
@Setter @Getter
public class ConfigAppCategoryVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "类别名称")
    private String name;


    @ApiModelProperty(value = "类别代码")
    private String code;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}