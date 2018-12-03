package com.demo.client.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询领域对象行为参数属性使用的DTO")
@Getter @Setter
public class EntityActionParameterPropertyCondition {

	@ApiModelProperty(value = "租户id")
	private Long tid;
    @ApiModelProperty(value = "所属行为参数", notes = "")
    private String actionParameter;
	@ApiModelProperty(value = "属性代码", notes = "")
	private String code;
	@ApiModelProperty(value = "属性名称", notes = "中文名称")
	private String name;
	@ApiModelProperty(value = "类型", notes = "")
	private String type;
	@ApiModelProperty(value = "外覆类型", notes = "List")
	private String wrapperType;
	@ApiModelProperty(value = "排序", notes = "")
	private Integer idx;
	@ApiModelProperty(value = "排序最大值")
	private Integer idxMax;
	@ApiModelProperty(value = "排序最小值")
	private Integer idxMin;
	@ApiModelProperty(value = "备注", notes = "")
	private String memo;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
