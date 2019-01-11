package com.yunkang.saas.platform.monitor.business.supporter.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询运维人员使用的DTO")
@Getter @Setter
public class SupporterCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
	@ApiModelProperty(value = "姓名")
	private String name;
	@ApiModelProperty(value = "手机号码")
	private String mobile;
	@ApiModelProperty(value = "邮箱")
	private String email;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
