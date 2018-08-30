package com.kingzoo.kingcat.project.icode4.business.microservice.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 传输对象属性
 * @author icode
 */
@Entity
@Table
public class TransModelProperty {

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_TRANS_MODEL_ID = "transModelId";
	public static final String PROPERTY_EDITABLE = "editable";
	public static final String PROPERTY_NULLABLE = "nullable";
	public static final String PROPERTY_MEMO = "memo";
	public static final String PROPERTY_CORE_RELATION = "coreRelation";
	public static final String PROPERTY_DOMAIN_MODEL_ID = "domainModelId";
	public static final String PROPERTY_DOMAIN_MODEL_PROPERTY_ID = "domainModelPropertyId";


    @Id
    private String id;


    /**
    * 属性名
    * 
    */
    @Column(name = "name")
	@NotNull(message = "属性名不能为空")
	@Size(max = 255, message = "属性名超长，最多255个字符")
	private String name;

    /**
    * 属性代码
    * 
    */
    @Column(name = "code")
	@NotNull(message = "属性代码不能为空")
	@Size(max = 255, message = "属性代码超长，最多255个字符")
	private String code;

    /**
    * 属性类型
    * 
    */
    @Column(name = "type")
	@NotNull(message = "属性类型不能为空")
	@Size(max = 255, message = "属性类型超长，最多255个字符")
	private String type;

    /**
    * 所属传输对象
    * 
    */
    @Column(name = "trans_modelId")
	@NotNull(message = "所属传输对象不能为空")
	private String transModelId;
	@Transient
	private String transModelName;

    /**
    * 可修改
    * 
    */
    @Column(name = "editable")
	@NotNull(message = "可修改不能为空")
	private Boolean editable;

    /**
    * 可为空
    * 
    */
    @Column(name = "nullable")
	@NotNull(message = "可为空不能为空")
	private Boolean nullable;

    /**
    * 备注
    * 
    */
    @Column(name = "memo")
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String memo;

    /**
    * 核心对象属性
    * 
    */
    @Column(name = "core_relation")
	@NotNull(message = "核心对象属性不能为空")
	private Boolean coreRelation;

    /**
    * 关联对象
    * 
    */
    @Column(name = "domain_modelId")
	@NotNull(message = "关联对象不能为空")
	private String domainModelId;
	@Transient
	private String domainModelName;

    /**
    * 关联对象属性
    * 
    */
    @Column(name = "domain_model_propertyId")
	@NotNull(message = "关联对象属性不能为空")
	private String domainModelPropertyId;
	@Transient
	private String domainModelPropertyName;

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

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getTransModelId(){
		return transModelId;
	}
	public void setTransModelId(String transModelId) {
		this.transModelId = transModelId;
	}
	public String getTransModelName(){
		return transModelName;
	}
	public void setTransModelName(String transModelName) {
		this.transModelName = transModelName;
	}

	public Boolean getEditable(){
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getNullable(){
		return nullable;
	}
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getCoreRelation(){
		return coreRelation;
	}
	public void setCoreRelation(Boolean coreRelation) {
		this.coreRelation = coreRelation;
	}

	public String getDomainModelId(){
		return domainModelId;
	}
	public void setDomainModelId(String domainModelId) {
		this.domainModelId = domainModelId;
	}
	public String getDomainModelName(){
		return domainModelName;
	}
	public void setDomainModelName(String domainModelName) {
		this.domainModelName = domainModelName;
	}

	public String getDomainModelPropertyId(){
		return domainModelPropertyId;
	}
	public void setDomainModelPropertyId(String domainModelPropertyId) {
		this.domainModelPropertyId = domainModelPropertyId;
	}
	public String getDomainModelPropertyName(){
		return domainModelPropertyName;
	}
	public void setDomainModelPropertyName(String domainModelPropertyName) {
		this.domainModelPropertyName = domainModelPropertyName;
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