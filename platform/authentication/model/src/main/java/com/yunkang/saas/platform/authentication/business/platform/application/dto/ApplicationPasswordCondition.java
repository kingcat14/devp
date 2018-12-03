package com.yunkang.saas.platform.authentication.business.platform.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询应用密码使用的DTO")
@Getter @Setter
public class ApplicationPasswordCondition {

    @ApiModelProperty(value = "应用")
    private Long appId;
	@ApiModelProperty(value = "访问ID")
	private String accessId;
	@ApiModelProperty(value = "访问密码")
	private String accessKey;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
