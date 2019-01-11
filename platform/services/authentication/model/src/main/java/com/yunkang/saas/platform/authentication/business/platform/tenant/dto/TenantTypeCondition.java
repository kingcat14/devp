package com.yunkang.saas.platform.authentication.business.platform.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询租户类型使用的DTO")
@Getter @Setter
public class TenantTypeCondition {

	@ApiModelProperty(value = "租户类型编码")
	private String tenantTypeCode;
	@ApiModelProperty(value = "租户类型名称")
	private String name;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
