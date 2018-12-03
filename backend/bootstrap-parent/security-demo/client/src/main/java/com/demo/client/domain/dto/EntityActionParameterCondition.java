package com.demo.client.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询领域对象行为参数使用的DTO")
@Getter @Setter
public class EntityActionParameterCondition {

	@ApiModelProperty(value = "对象代码", notes = "")
	private String code;
	@ApiModelProperty(value = "对象名称", notes = "")
	private String name;
	@ApiModelProperty(value = "备注", notes = "")
	private String memo;
	@ApiModelProperty(value = "描述", notes = "")
	private String description;
    @ApiModelProperty(value = "领域对象行为")
    private String entityAction;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
