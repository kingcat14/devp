package com.yunkang.saas.platform.authentication.business.platform.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 应用类别
 * @author icode
 */
@ApiModel(value = "修改应用类别使用的DTO")
@Setter @Getter
public class ConfigAppCategoryEditDto {


	/**类别名称*/
	@ApiModelProperty(value = "类别名称", required = false)
	private String name;


	/**类别代码*/
	@ApiModelProperty(value = "类别代码", required = false)
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
