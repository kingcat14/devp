package com.yunkang.saas.platform.monitor.business.indicator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 指标
 * @author icode
 */
@ApiModel(value = "修改指标使用的DTO")
public class IndicatorEditDto {


	/**代码*/
	@ApiModelProperty(value = "代码", required = true)
	private String code;


	/**名称*/
	@ApiModelProperty(value = "名称", required = true)
	private String name;


	/**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "持续型,数值型")
	private String type;



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


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
