package com.yunkang.saas.bootstrap.business.platform.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 应用
 * @author icode
 */
@ApiModel(value = "修改应用使用的DTO")
public class AppEditDto {



    /**应用名称*/
	@NotNull(message = "应用名称不能为空")
	@ApiModelProperty(value = "应用名称", required = true)
	@Size(max = 255, message = "应用名称超长，最多255个字符")
	private String name;



    /**应用代码*/
	@NotNull(message = "应用代码不能为空")
	@ApiModelProperty(value = "应用代码", required = true)
	@Size(max = 255, message = "应用代码超长，最多255个字符")
	private String code;



    /**应用类别*/
	@ApiModelProperty(value = "应用类别", required = false)
	private Long appCategoryId;



    /**标签*/
	@ApiModelProperty(value = "标签", required = false, notes = "应用的标签，逗号分隔，可填写多个")
	private String label;



    /**已启用*/
	@ApiModelProperty(value = "已启用", required = false)
	private Boolean enable;



    /**上架时间*/
	@ApiModelProperty(value = "上架时间", required = false)
	private Date onBoardTime;



    /**应用链接*/
	@ApiModelProperty(value = "应用链接", required = false, notes = "跳转至对应的应用")
	@Size(max = 255, message = "应用链接超长，最多255个字符")
	private String url;



    /**描述*/
	@ApiModelProperty(value = "描述", required = false)
	private String description;



    /**可见*/
	@ApiModelProperty(value = "可见", required = false)
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
