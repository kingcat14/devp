package com.yunkang.saas.tenant.business.tenant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 租户的值对象
*/
@ApiModel(value = "展现租户的值对象")
public class TenantVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    /**
    * 租户代号
    * 
    */
    @ApiModelProperty(value = "租户代号")
    private String tenantCode;

    /**
    * 租户类型
    * 
    */
    @ApiModelProperty(value = "租户类型")
    private Long tenantType;
    private TenantTypeVO tenantTypeVO;

    /**
    * 租户名称
    * 
    */
    @ApiModelProperty(value = "租户名称")
    private String name;

    /**
    * 国家
    * 
    */
    @ApiModelProperty(value = "国家")
    private String country;

    /**
    * 省份
    * 
    */
    @ApiModelProperty(value = "省份")
    private String province;

    /**
    * 市、县
    * 
    */
    @ApiModelProperty(value = "市、县")
    private String city;

    /**
    * 详细地址
    * 
    */
    @ApiModelProperty(value = "详细地址")
    private String address;

    /**
    * 传真
    * 
    */
    @ApiModelProperty(value = "传真")
    private String fax;

    /**
    * 联系电话
    * 
    */
    @ApiModelProperty(value = "联系电话")
    private String telephoneNo;

    /**
    * CRM系统代码
    * 
    */
    @ApiModelProperty(value = "CRM系统代码")
    private String crmCode;

    /**
    * 域名前缀
    * 
    */
    @ApiModelProperty(value = "域名前缀")
    private String prefixDomainName;

    /**
    * 状态
    * 
    */
    @ApiModelProperty(value = "状态")
    private Integer status;


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
    public TenantTypeVO getTenantTypeVO(){
        return tenantTypeVO;
    }
    public void setTenantTypeVO(TenantTypeVO tenantTypeVO) {
        this.tenantTypeVO = tenantTypeVO;
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
    public Integer getStatus(){
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}