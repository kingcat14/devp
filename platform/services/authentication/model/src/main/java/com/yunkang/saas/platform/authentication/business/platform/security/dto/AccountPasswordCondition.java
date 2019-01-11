package com.yunkang.saas.platform.authentication.business.platform.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询账号密码使用的DTO")
@Getter @Setter
public class AccountPasswordCondition {

    @ApiModelProperty(value = "账号")
    private Long account;
	@ApiModelProperty(value = "姓名")
	private String accountName;
	@ApiModelProperty(value = "密码")
	private String password;
	@ApiModelProperty(value = "错误次数")
	private Integer wrongCount;
	@ApiModelProperty(value = "错误次数最大值")
	private Integer wrongCountMax;
	@ApiModelProperty(value = "错误次数最小值")
	private Integer wrongCountMin;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
