package com.yunkang.saas.platform.authentication.business.platform.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 租户
 * @author icode
 */
@ApiModel(value = "新增租户使用的DTO")
@Setter @Getter
public class TenantAddDto {

    /**租户代号*/
	@ApiModelProperty(value = "租户代号", required = false)
	private String tenantCode;

    /**租户类型*/
	@ApiModelProperty(value = "租户类型", required = false)
	private Long tenantType;

    /**租户名称*/
	@ApiModelProperty(value = "租户名称", required = false)
	private String name;

    /**国家*/
	@ApiModelProperty(value = "国家", required = false)
	private String country;

    /**省份*/
	@ApiModelProperty(value = "省份", required = false)
	private String province;

    /**市、县*/
	@ApiModelProperty(value = "市、县", required = false)
	private String city;

    /**详细地址*/
	@ApiModelProperty(value = "详细地址", required = false)
	private String address;

    /**传真*/
	@ApiModelProperty(value = "传真", required = false)
	private String fax;

    /**联系电话*/
	@ApiModelProperty(value = "联系电话", required = false)
	private String telephoneNo;

    /**CRM系统代码*/
	@ApiModelProperty(value = "CRM系统代码", required = false)
	private String crmCode;

    /**域名前缀*/
	@ApiModelProperty(value = "域名前缀", required = false)
	private String prefixDomainName;

    /**号码*/
	@ApiModelProperty(value = "号码", required = false)
	private Long mobile;

    /**启用*/
	@ApiModelProperty(value = "启用", required = false)
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

	public Long getMobile(){
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
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
