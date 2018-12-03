package com.demo.client.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 模型类型的值对象
*/
@ApiModel(value = "展现模型类型的值对象")
@Setter @Getter
public class ModelTypeVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "code", notes = "")
    private String code;


    @ApiModelProperty(value = "name", notes = "")
    private String name;


    @ApiModelProperty(value = "idx", notes = "")
    private Integer idx;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}