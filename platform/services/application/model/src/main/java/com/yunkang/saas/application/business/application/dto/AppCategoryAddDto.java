package com.yunkang.saas.application.business.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Size;




/**
 * 应用类别
 * @author icode
 */
@ApiModel(value = "新增应用类别使用的DTO")
public class AppCategoryAddDto {

    /**
	 * 类别名称
	 * 
     */
	@ApiModelProperty(value = "类别名称", required = false)
	@Size(max = 255, message = "类别名称超长，最多255个字符")
	private String name;

    /**
	 * 类别代码
	 * 
     */
	@ApiModelProperty(value = "类别代码", required = false)
	@Size(max = 255, message = "类别代码超长，最多255个字符")
	private String code;


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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
