package com.yunkang.saas.platform.monitor.business.supporter.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询支持应用使用的DTO")
@Getter @Setter
public class SupporterAppRelationCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
    @ApiModelProperty(value = "运维人员")
    private String supporter;
    @ApiModelProperty(value = "支持程序")
    private String application;
	@ApiModelProperty(value = "接收通知方式", notes = "逗号分隔的告警方式")
	private String notificationType;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
