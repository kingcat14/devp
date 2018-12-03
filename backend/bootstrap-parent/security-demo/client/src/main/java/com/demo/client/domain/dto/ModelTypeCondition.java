package com.demo.client.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询模型类型使用的DTO")
@Getter @Setter
public class ModelTypeCondition {

	@ApiModelProperty(value = "code", notes = "")
	private String code;
	@ApiModelProperty(value = "name", notes = "")
	private String name;
	@ApiModelProperty(value = "idx", notes = "")
	private Integer idx;
	@ApiModelProperty(value = "idx最大值")
	private Integer idxMax;
	@ApiModelProperty(value = "idx最小值")
	private Integer idxMin;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
