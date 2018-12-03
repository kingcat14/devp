package com.yunkang.saas.platform.authentication.business.platform.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@ApiModel(value = "查询租户开通的应用使用的DTO")
@Getter @Setter
public class AppTenantRelationCondition {

	@ApiModelProperty(value = "应用")
	private String app;
	@ApiModelProperty(value = "账号")
	private String account;
	@ApiModelProperty(value = "生效日期")
	private Date startDate;
	@ApiModelProperty(value = "起始生效日期")
	private Date startDateStart;
	@ApiModelProperty(value = "结束生效日期")
	private Date startDateEnd;
	@ApiModelProperty(value = "截止日期")
	private Date endDate;
	@ApiModelProperty(value = "起始截止日期")
	private Date endDateStart;
	@ApiModelProperty(value = "结束截止日期")
	private Date endDateEnd;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
