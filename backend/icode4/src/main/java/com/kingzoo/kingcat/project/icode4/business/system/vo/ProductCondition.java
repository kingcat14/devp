package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class ProductCondition implements Serializable{


	private String id;
	private Integer version;

	private String productName;
	private String productCode;
	private String description;


	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductCode(){
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
