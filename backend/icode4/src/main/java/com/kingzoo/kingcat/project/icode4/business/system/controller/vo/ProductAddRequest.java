package com.kingzoo.kingcat.project.icode4.business.system.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Product
 * @author icode
 */
public class ProductAddRequest {

	public static final String PROPERTY_PRODUCT_NAME = "productName";
	public static final String PROPERTY_PRODUCT_CODE = "productCode";
	public static final String PROPERTY_DESCRIPTION = "description";

    /**
	 * 产品名称
	 * 
     */
	@NotNull(message = "产品名称不能为空")
	@Size(max = 255, message = "产品名称超长，最多255个字符")
	private String productName;

    /**
	 * 产品代码
	 * 
     */
	@NotNull(message = "产品代码不能为空")
	@Size(max = 255, message = "产品代码超长，最多255个字符")
	private String productCode;

    /**
	 * 产品说明
	 * 
     */
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
