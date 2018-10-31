package com.yunkang.saas.bootstrap.platform.business.platform.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 角色
 * @author icode
 */
@ApiModel(value = "新增角色使用的DTO")
public class RoleAddDto {

    /**
	 * 角色名称
	 * 
     */
	@NotNull(message = "角色名称不能为空")
	@ApiModelProperty(value = "角色名称", required = true)
	@Size(max = 255, message = "角色名称超长，最多255个字符")
	private String name;


	/**角色代码*/
	@ApiModelProperty(value = "角色代码", required = true)
	@Size(max = 255, message = "角色代码超长，最多255个字符")
	private String code;

    /**
	 * 角色描述
	 * 
     */
	@ApiModelProperty(value = "角色描述", required = false)
	@Size(max = 255, message = "角色描述超长，最多255个字符")
	private String description;

	@ApiModelProperty(value = "租户")
	private Long tenantId;

	@ApiModelProperty(value = "应用")
	private String appCode;

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
