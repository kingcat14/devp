package com.yunkang.saas.bootstrap.platform.business.platform.application.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询应用使用的DTO")
public class AppCondition extends SaaSCondition{

	@ApiModelProperty(value = "应用名称")
	private String name;
	@ApiModelProperty(value = "应用代码")
	private String code;
	@ApiModelProperty(value = "应用类别")
	private Long appCategoryId;
	@ApiModelProperty(value = "应用类别最大值")
	private Long appCategoryIdMax;
	@ApiModelProperty(value = "应用类别最小值")
	private Long appCategoryIdMin;
	@ApiModelProperty(value = "标签", notes = "应用的标签，逗号分隔，可填写多个")
	private String label;
	@ApiModelProperty(value = "上架时间")
	private Date onBoardTime;
	@ApiModelProperty(value = "起始上架时间")
	private Date onBoardTimeStart;
	@ApiModelProperty(value = "结束上架时间")
	private Date onBoardTimeEnd;
	@ApiModelProperty(value = "应用链接", notes = "跳转至对应的应用")
	private String url;
	@ApiModelProperty(value = "描述")
	private String description;
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

	public Long getAppCategoryIdMin(){
		return appCategoryIdMin;
	}
	public void setAppCategoryIdMin(Long appCategoryIdMin) {
		this.appCategoryIdMin = appCategoryIdMin;
	}

	public Long getAppCategoryIdMax(){
		return appCategoryIdMax;
	}
	public void setAppCategoryIdMax(Long appCategoryIdMax) {
		this.appCategoryIdMax = appCategoryIdMax;
	}


	public String getLabel(){
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}


	public Date getOnBoardTime(){
		return onBoardTime;
	}
	public void setOnBoardTime(Date onBoardTime) {
		this.onBoardTime = onBoardTime;
	}
	public Date getOnBoardTimeStart(){
		return onBoardTimeStart;
	}
	public void setOnBoardTimeStart(Date onBoardTimeStart) {
		this.onBoardTimeStart = onBoardTimeStart;
	}
	public Date getOnBoardTimeEnd(){
		return onBoardTimeEnd;
	}
	public void setOnBoardTimeEnd(Date onBoardTimeEnd) {
		this.onBoardTimeEnd = onBoardTimeEnd;
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




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
