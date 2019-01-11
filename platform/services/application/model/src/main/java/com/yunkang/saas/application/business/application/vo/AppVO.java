package com.yunkang.saas.application.business.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;




/**
* 应用的值对象
*/
@ApiModel(value = "展现应用的值对象")
public class AppVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "应用名称")
    /**
    * 应用名称
    * 
    */
    private String name;

    @ApiModelProperty(value = "应用代码")
    /**
    * 应用代码
    * 
    */
    private String code;

    @ApiModelProperty(value = "所属租户")
    /**
    * 所属租户
    * 
    */
    private Long tenantId;

    @ApiModelProperty(value = "应用类别类别代码")
    /**
    * 应用类别类别代码
    * 
    */
    private String appCategoryCode;

    @ApiModelProperty(value = "应用类别")
    /**
    * 应用类别
    * 
    */
    private Long appCategoryId;

    @ApiModelProperty(value = "标签")
    /**
    * 标签
    * 应用的标签，逗号分隔，可填写多个
    */
    private String label;

    @ApiModelProperty(value = "已启用")
    /**
    * 已启用
    * 
    */
    private Boolean enable;

    @ApiModelProperty(value = "上架时间")
    /**
    * 上架时间
    * 
    */
    private Date onBoardTime;

    @ApiModelProperty(value = "应用链接")
    /**
    * 应用链接
    * 跳转至对应的应用
    */
    private String url;

    @ApiModelProperty(value = "描述")
    /**
    * 描述
    * 
    */
    private String description;

    @ApiModelProperty(value = "可见")
    /**
    * 可见
    * 
    */
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
    public Long getTenantId(){
        return tenantId;
    }
    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
    public String getAppCategoryCode(){
        return appCategoryCode;
    }
    public void setAppCategoryCode(String appCategoryCode) {
        this.appCategoryCode = appCategoryCode;
    }
    public Long getAppCategoryId(){
        return appCategoryId;
    }
    public void setAppCategoryId(Long appCategoryId) {
        this.appCategoryId = appCategoryId;
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