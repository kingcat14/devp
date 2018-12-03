package com.demo.client.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询属性类型使用的DTO")
@Getter @Setter
public class PropertyTypeCondition {

	@ApiModelProperty(value = "代码", notes = "")
	private String code;
	@ApiModelProperty(value = "名称", notes = "")
	private String name;
	@ApiModelProperty(value = "排序", notes = "")
	private Integer viewIndex;
	@ApiModelProperty(value = "排序最大值")
	private Integer viewIndexMax;
	@ApiModelProperty(value = "排序最小值")
	private Integer viewIndexMin;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
