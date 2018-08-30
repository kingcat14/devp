package com.kingzoo.kingcat.project.icode4.business.system.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 对象类型
 * @author icode
 */
public class ModelTypeAddRequest {

	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";

    /**
	 * 类型代码
	 * 
     */
	@NotNull(message = "类型代码不能为空")
	@Size(max = 255, message = "类型代码超长，最多255个字符")
	private String code;

    /**
	 * 类型名称
	 * 
     */
	@NotNull(message = "类型名称不能为空")
	@Size(max = 255, message = "类型名称超长，最多255个字符")
	private String name;


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
