package com.yunkang.saas.bootstrap.business.platform.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 租户
 * @author icode
 */
@ApiModel(value = "新增租户使用的DTO")
public class TenantAddDto {

    /**
	 * 租户代号
	 * 
     */
	@NotNull(message = "租户代号不能为空")
	@ApiModelProperty(value = "租户代号", required = true)
	@Size(max = 255, message = "租户代号超长，最多255个字符")
	private String tenantCode;

    /**
	 * 租户类型
	 * 
     */
	@NotNull(message = "租户类型不能为空")
	@ApiModelProperty(value = "租户类型", required = true)
	private Long tenantType;

    /**
	 * 租户名称
	 * 
     */
	@NotNull(message = "租户名称不能为空")
	@ApiModelProperty(value = "租户名称", required = true)
	@Size(max = 255, message = "租户名称超长，最多255个字符")
	private String name;

    /**
	 * 国家
	 * 
     */
	@ApiModelProperty(value = "国家", required = false)
	@Size(max = 255, message = "国家超长，最多255个字符")
	private String country;

    /**
	 * 省份
	 * 
     */
	@ApiModelProperty(value = "省份", required = false)
	@Size(max = 255, message = "省份超长，最多255个字符")
	private String province;

    /**
	 * 市、县
	 * 
     */
	@ApiModelProperty(value = "市、县", required = false)
	@Size(max = 255, message = "市、县超长，最多255个字符")
	private String city;

    /**
	 * 详细地址
	 * 
     */
	@ApiModelProperty(value = "详细地址", required = false)
	@Size(max = 255, message = "详细地址超长，最多255个字符")
	private String address;

    /**
	 * 传真
	 * 
     */
	@ApiModelProperty(value = "传真", required = false)
	@Size(max = 255, message = "传真超长，最多255个字符")
	private String fax;

    /**
	 * 联系电话
	 * 
     */
	@ApiModelProperty(value = "联系电话", required = false)
	@Size(max = 255, message = "联系电话超长，最多255个字符")
	private String telephoneNo;

    /**
	 * CRM系统代码
	 * 
     */
	@ApiModelProperty(value = "CRM系统代码", required = false)
	@Size(max = 255, message = "CRM系统代码超长，最多255个字符")
	private String crmCode;

    /**
	 * 域名前缀
	 * 
     */
	@ApiModelProperty(value = "域名前缀", required = false)
	@Size(max = 255, message = "域名前缀超长，最多255个字符")
	private String prefixDomainName;

    /**
	 * 状态
	 * 
     */
	@NotNull(message = "状态不能为空")
	@ApiModelProperty(value = "状态", required = true)
	private Boolean status;


	public String getTenantCode(){
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public Long getTenantType(){
		return tenantType;
	}
	public void setTenantType(Long tenantType) {
		this.tenantType = tenantType;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCountry(){
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince(){
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity(){
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress(){
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getFax(){
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTelephoneNo(){
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getCrmCode(){
		return crmCode;
	}
	public void setCrmCode(String crmCode) {
		this.crmCode = crmCode;
	}

	public String getPrefixDomainName(){
		return prefixDomainName;
	}
	public void setPrefixDomainName(String prefixDomainName) {
		this.prefixDomainName = prefixDomainName;
	}

	public Boolean getStatus(){
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
