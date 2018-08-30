package com.kingzoo.kingcat.project.icode4.business.system.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Product
 * @author icode
 */
@Entity
@Table
public class Product {

	public static final String PROPERTY_PRODUCT_NAME = "productName";
	public static final String PROPERTY_PRODUCT_CODE = "productCode";
	public static final String PROPERTY_DESCRIPTION = "description";


    @Id
    private String id;


    /**
    * 产品名称
    * 
    */
    @Column(name = "product_name")
	@NotNull(message = "产品名称不能为空")
	@Size(max = 255, message = "产品名称超长，最多255个字符")
	private String productName;

    /**
    * 产品代码
    * 
    */
    @Column(name = "product_code")
	@NotNull(message = "产品代码不能为空")
	@Size(max = 255, message = "产品代码超长，最多255个字符")
	private String productCode;

    /**
    * 产品说明
    * 
    */
    @Column(name = "description", columnDefinition = "TEXT")
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}