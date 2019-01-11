package com.yunkang.saas.platform.authentication.business.platform.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


/**
 * 租户开通的应用
 * @author icode
 */
@ApiModel(value = "新增租户开通的应用使用的DTO")
@Setter @Getter
public class AppTenantRelationAddDto {

    /**应用*/
	@ApiModelProperty(value = "应用", required = false)
	private String app;

    /**账号*/
	@ApiModelProperty(value = "账号", required = false)
	private String account;

    /**生效日期*/
	@ApiModelProperty(value = "生效日期", required = false)
	@Temporal(TemporalType.DATE)
	private Date startDate;

    /**截止日期*/
	@ApiModelProperty(value = "截止日期", required = false)
	@Temporal(TemporalType.DATE)
	private Date endDate;


	public String getApp(){
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}

	public String getAccount(){
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	public Date getStartDate(){
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate(){
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
