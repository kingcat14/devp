package com.yunkang.saas.platform.authentication.business.platform.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询应用类别使用的DTO")
@Getter @Setter
public class ConfigAppCategoryCondition {

	@ApiModelProperty(value = "类别名称")
	private String name;
	@ApiModelProperty(value = "类别代码")
	private String code;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
