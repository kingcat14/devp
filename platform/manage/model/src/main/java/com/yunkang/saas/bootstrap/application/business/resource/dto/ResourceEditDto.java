package com.yunkang.saas.bootstrap.application.business.resource.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Size;


/**
 * 资源
 * @author icode
 */
@ApiModel(value = "修改资源使用的DTO")
public class ResourceEditDto {

	/** 所属应用 */
	@ApiModelProperty(value = "所属应用")
	private String appCode;

    /**
	 * 资源名
	 * 
     */
	@ApiModelProperty(value = "资源名", required = false)
	@Size(max = 255, message = "资源名超长，最多255个字符")
	private String name;

    /**
	 * 资源链接
	 * 
     */
	@ApiModelProperty(value = "资源链接", required = false)
	@Size(max = 255, message = "资源链接超长，最多255个字符")
	private String url;

    /**
	 * 资源类型
	 * 
     */
	@ApiModelProperty(value = "资源类型", required = false)
	@Size(max = 255, message = "资源类型超长，最多255个字符")
	private String type;

    /**
	 * 资源代码
	 * 
     */
	@ApiModelProperty(value = "资源代码", required = false)
	private Long code;

    /**
	 * 父节点
	 * 
     */
	@ApiModelProperty(value = "父节点", required = false)
	private Long parentCode;


	/**
	 * 排序
	 * 
     */
	@ApiModelProperty(value = "排序", required = false)
	private Integer orderIndex;

	/**是否隐藏菜单*/
	@ApiModelProperty(value = "排序", required = true)
	private Boolean hidden;

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Long getCode(){
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}

	public Long getParentCode() {
		return parentCode;
	}
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public Integer getOrderIndex(){
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Boolean getHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
