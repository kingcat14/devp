package com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 传输对象
 * @author icode
 */
public class TransModelAddRequest {

    /**
	 * 对象名称
	 * 
     */
	@NotNull(message = "对象名称不能为空")
	@Size(max = 255, message = "对象名称超长，最多255个字符")
	private String name;

    /**
	 * 对象代码
	 * 
     */
	@NotNull(message = "对象代码不能为空")
	@Size(max = 255, message = "对象代码超长，最多255个字符")
	private String code;

    /**
	 * 备注
	 * 
     */
	@NotNull(message = "备注不能为空")
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String memo;

    /**
	 * 描述
	 * 
     */
	@NotNull(message = "描述不能为空")
	@Size(max = 255, message = "描述超长，最多255个字符")
	private String description;

    /**
	 * 所属模块ID
	 * 
     */
	@NotNull(message = "所属模块ID不能为空")
	private String moduleId;
	private String moduleName;

    /**
	 * 所属领域对象
	 * 
     */
	private String domainModelId;
	private String domainModelName;

    /**
	 * 展现排序
	 * 
     */
	@NotNull(message = "展现排序不能为空")
	private Integer viewIndex;


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

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getModuleId(){
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName(){
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDomainModelId(){
		return domainModelId;
	}
	public void setDomainModelId(String domainModelId) {
		this.domainModelId = domainModelId;
	}
	public String getDomainModelName(){
		return domainModelName;
	}
	public void setDomainModelName(String domainModelName) {
		this.domainModelName = domainModelName;
	}

	public Integer getViewIndex(){
		return viewIndex;
	}
	public void setViewIndex(Integer viewIndex) {
		this.viewIndex = viewIndex;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
