package com.yunkang.saas.common.framework.eo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * SaaS对象
 * @author gonghongrui on 2018/4/17.
 */
public class SaaSEntity extends BaseEntity {

	/**
	 * 所属租户
	 */
	protected String tentantId;

	/**
	 * 所属应用
	 */
	protected String appId;


	@ApiModelProperty(value = "创建者", hidden = true)
	protected String createBy; //创建者
	@ApiModelProperty(value = "创建时间", hidden = true)
	protected Date createDate;  //创建时间
	@ApiModelProperty(value = "更新者", hidden = true)
	protected String updateBy; //更新者
	@ApiModelProperty(value = "更新时间", hidden = true)
	protected Date updateDate;  //更新时间


	public String getTentantId() {
		return tentantId;
	}

	public void setTentantId(String tentantId) {
		this.tentantId = tentantId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
