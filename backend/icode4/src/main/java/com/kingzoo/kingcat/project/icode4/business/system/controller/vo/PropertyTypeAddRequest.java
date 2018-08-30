package com.kingzoo.kingcat.project.icode4.business.system.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 属性类型
 * @author icode
 */
public class PropertyTypeAddRequest {

	public static final String PROPERTY_VIEW_INDEX = "viewIndex";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";

    /**
	 * 展示顺序
	 * 
     */
	@Size(max = 255, message = "展示顺序超长，最多255个字符")
	private String viewIndex;

    /**
	 * 代码
	 * 
     */
	@NotNull(message = "代码不能为空")
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
	 * 名称
	 * 
     */
	@NotNull(message = "名称不能为空")
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;


	public String getViewIndex(){
		return viewIndex;
	}
	public void setViewIndex(String viewIndex) {
		this.viewIndex = viewIndex;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
