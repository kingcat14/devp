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
 * 系统
 * @author icode
 */
@Entity
@Table
public class System {

	public static final String PROPERTY_PRODUCT_ID = "productId";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_BASE_PACKAGE = "basePackage";


    @Id
    private String id;


	@Column(name = "productId")
    private String productId;


    /**
    * 系统名称
    * 
    */
    @Column(name = "name")
	@NotNull(message = "系统名称不能为空")
	@Size(max = 255, message = "系统名称超长，最多255个字符")
	private String name;

    /**
    * 系统代码
    * 
    */
    @Column(name = "code")
	@NotNull(message = "系统代码不能为空")
	@Size(max = 255, message = "系统代码超长，最多255个字符")
	private String code;

    /**
    * 描述
    * 
    */
    @Column(name = "description")
	private String description;

    /**
    * 基础包名称
    * 
    */
    @Column(name = "base_package")
	@NotNull(message = "基础包名称不能为空")
	@Size(max = 255, message = "基础包名称超长，最多255个字符")
	private String basePackage;


	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode(){
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

	public String getBasePackage(){
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
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