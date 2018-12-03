package com.demo.client.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询领域对象使用的DTO")
@Getter @Setter
public class EntityCondition {

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "实体代码", notes = "")
	private String code;
	@ApiModelProperty(value = "实体名称", notes = "")
	private String name;
	@ApiModelProperty(value = "排序", notes = "")
	private Integer viewIndex;
	@ApiModelProperty(value = "排序最大值")
	private Integer viewIndexMax;
	@ApiModelProperty(value = "排序最小值")
	private Integer viewIndexMin;
	@ApiModelProperty(value = "描述", notes = "")
	private String description;
	@ApiModelProperty(value = "父对象")
	private String parentEntity;
    @ApiModelProperty(value = "所属聚合")
    private String aggregate;
    @ApiModelProperty(value = "所属领域")
    private String domain;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
