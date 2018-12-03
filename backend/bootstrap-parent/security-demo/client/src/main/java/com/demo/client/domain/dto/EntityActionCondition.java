package com.demo.client.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询领域对象行为使用的DTO")
@Getter @Setter
public class EntityActionCondition {

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "行为名称")
	private String name;
	@ApiModelProperty(value = "备注", notes = "行为的概括性描述")
	private String memo;
	@ApiModelProperty(value = "行为描述", notes = "行为的操作步骤")
	private String description;
	@ApiModelProperty(value = "行为输入对象")
	private String request;
	@ApiModelProperty(value = "行为响应对象")
	private String response;
    @ApiModelProperty(value = "所属领域对象")
    private String entity;
	@ApiModelProperty(value = "行为类型", notes = "备用字段,将来用于标识 增、删、改、查、业务 等行为")
	private String type;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
