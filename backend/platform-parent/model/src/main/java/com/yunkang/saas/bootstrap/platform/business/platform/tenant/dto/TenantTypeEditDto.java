package com.yunkang.saas.bootstrap.platform.business.platform.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 租户类型
 * @author icode
 */
@ApiModel(value = "修改租户类型使用的DTO")
public class TenantTypeEditDto {



    /**
	 * 租户类型编码
	 * 
     */
	@NotNull(message = "租户类型编码不能为空")
	@ApiModelProperty(value = "租户类型编码", required = true)
	@Size(max = 255, message = "租户类型编码超长，最多255个字符")
	private String tenantTypeCode;



    /**
	 * 租户类型名称
	 * 
     */
	@NotNull(message = "租户类型名称不能为空")
	@ApiModelProperty(value = "租户类型名称", required = true)
	@Size(max = 255, message = "租户类型名称超长，最多255个字符")
	private String name;



	public String getTenantTypeCode(){
		return tenantTypeCode;
	}
	public void setTenantTypeCode(String tenantTypeCode) {
		this.tenantTypeCode = tenantTypeCode;
	}


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
