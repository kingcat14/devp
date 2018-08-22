package net.aicoder.maintenance.business.rdc.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询开发模式使用的DTO")
public class DevelopTypeCondition implements Serializable{

	@ApiModelProperty(value = "名称")
	private String name;


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
