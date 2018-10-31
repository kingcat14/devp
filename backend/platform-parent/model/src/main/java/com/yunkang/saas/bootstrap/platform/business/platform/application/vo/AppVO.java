package com.yunkang.saas.bootstrap.platform.business.platform.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用的值对象
*/
@ApiModel(value = "展现应用的值对象")
public class AppVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    /**应用名称*/
    @ApiModelProperty(value = "应用名称")
    private String name;

    /**应用代码*/
    @ApiModelProperty(value = "应用代码")
    private String code;

    /**应用类别*/
    @ApiModelProperty(value = "应用类别")
    private Long appCategoryId;
    private ConfigAppCategoryVO appCategoryIdVO;

    /**标签*/
    @ApiModelProperty(value = "标签", notes = "应用的标签，逗号分隔，可填写多个")
    private String label;

    /**已启用*/
    @ApiModelProperty(value = "已启用")
    private Boolean enable;

    /**上架时间*/
    @ApiModelProperty(value = "上架时间")
    private Date onBoardTime;

    /**应用链接*/
    @ApiModelProperty(value = "应用链接", notes = "跳转至对应的应用")
    private String url;

    /**描述*/
    @ApiModelProperty(value = "描述")
    private String description;

    /**可见*/
    @ApiModelProperty(value = "可见")
    private Boolean visible;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCode(){
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public Long getAppCategoryId(){
        return appCategoryId;
    }
    public void setAppCategoryId(Long appCategoryId) {
        this.appCategoryId = appCategoryId;
    }
    public ConfigAppCategoryVO getAppCategoryIdVO(){
        return appCategoryIdVO;
    }
    public void setAppCategoryIdVO(ConfigAppCategoryVO appCategoryIdVO) {
        this.appCategoryIdVO = appCategoryIdVO;
    }

    public String getLabel(){
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getEnable(){
        return enable;
    }
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Date getOnBoardTime(){
        return onBoardTime;
    }
    public void setOnBoardTime(Date onBoardTime) {
        this.onBoardTime = onBoardTime;
    }

    public String getUrl(){
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVisible(){
        return visible;
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}