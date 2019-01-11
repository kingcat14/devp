package com.yunkang.saas.platform.authentication.business.platform.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;




/**
* 应用的值对象
*/
@ApiModel(value = "展现应用的值对象")
@Setter @Getter
public class AppVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "应用名称")
    private String name;


    @ApiModelProperty(value = "应用代码")
    private String code;


    /**应用类别*/
    @ApiModelProperty(value = "应用类别")
    private Long appCategoryId;
    private ConfigAppCategoryVO appCategoryIdVO;


    /**标签*/
    @ApiModelProperty(value = "标签", notes = "应用的标签，逗号分隔，可填写多个")
    private String label;


    @ApiModelProperty(value = "已启用")
    private Boolean enable;


    /**上架时间*/
    @ApiModelProperty(value = "上架时间")
    private Date onBoardTime;


    @ApiModelProperty(value = "应用链接", notes = "跳转至对应的应用")
    private String url;


    /**描述*/
    @ApiModelProperty(value = "描述")
    private String description;


    @ApiModelProperty(value = "可见")
    private Boolean visible;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}