package com.yunkang.saas.platform.authentication.business.platform.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 租户类型
 * @author icode
 */
@ApiModel(value = "修改租户类型使用的DTO")
@Setter @Getter
public class TenantTypeEditDto {


	/**租户类型编码*/
	@ApiModelProperty(value = "租户类型编码", required = false)
	private String tenantTypeCode;


	/**租户类型名称*/
	@ApiModelProperty(value = "租户类型名称", required = false)
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
