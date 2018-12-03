package com.demo.client.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 属性类型的值对象
*/
@ApiModel(value = "展现属性类型的值对象")
@Setter @Getter
public class PropertyTypeVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "代码", notes = "")
    private String code;


    @ApiModelProperty(value = "名称", notes = "")
    private String name;


    @ApiModelProperty(value = "排序", notes = "")
    private Integer viewIndex;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}