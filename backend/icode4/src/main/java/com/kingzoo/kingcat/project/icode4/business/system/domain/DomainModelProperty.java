package com.kingzoo.kingcat.project.icode4.business.system.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 领域对象属性
 * @author icode
 */
@Entity

public class DomainModelProperty {

	public static final String PROPERTY_DOMAIN_MODEL_ID = "domainModelId";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_PERSIST = "persist";
	public static final String PROPERTY_EDITABLE = "editable";
	public static final String PROPERTY_NULLABLE = "nullable";
	public static final String PROPERTY_VISIBLE = "visible";
	public static final String PROPERTY_MEMO = "memo";
	public static final String PROPERTY_RELATED_DOMAIN_MODEL_ID = "relatedDomainModelId";
	public static final String PROPERTY_RELATED_DOMAIN_MODEL_PROPERTY_ID = "relatedDomainModelPropertyId";
	public static final String PROPERTY_VIEW_INDEX = "viewIndex";
	public static final String PROPERTY_PRIMARY_KEY = "primaryKey";

    @Id
    private String id;


    /**
    * 所属领域对象
    * 
    */
    @Column(name = "domain_modelId")
	@NotNull(message = "所属领域对象不能为空")
	private String domainModelId;
	@Transient
	private String domainModelName;

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
    * String，Integer等
    */
    @Column(name = "type")
	@NotNull(message = "属性类型不能为空")
	@Size(max = 255, message = "属性类型超长，最多255个字符")
	private String type;

    /**
    * 持久化
    * 
    */
    @Column(name = "persist")
	@NotNull(message = "持久化不能为空")
	private Boolean persist;

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
	 * 可见
	 */
	@Column(name = "visible")
	@NotNull(message = "可见空不能为空")
	private Boolean visible;

	/**
	 * 是否为主键
	 *
	 */
	@Column(name = "primaryKey")
	@NotNull(message = "是否为主键")
	private Boolean primaryKey;

	/**
	 * 是否为查询条件不能为空
	 *
	 */
	@Column(name = "search")
	@NotNull(message = "是否为查询条件不能为空")
	private Boolean search;

    /**
    * 备注
    * 
    */
    @Column(name = "memo")
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String memo;

    /**
    * 关联对象
    * 属性关联到其他对象时，该字段不为空
    */
    @Column(name = "related_domain_modelId")
	private String relatedDomainModelId;
	@Transient
	private String relatedDomainModelName;

    /**
    * 关联对象属性
    * 属性关联到其他对象时，该字段不为空
    */
    @Column(name = "related_domain_model_propertyId")
	private String relatedDomainModelPropertyId;
	@Transient
	private String relatedDomainModelPropertyName;

    /**
    * 展现顺序
    * 
    */
    @Column(name = "view_index")
	private Integer viewIndex;

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

	public Boolean getPersist(){
		return persist;
	}
	public void setPersist(Boolean persist) {
		this.persist = persist;
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

	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Boolean getSearch() {
		return search;
	}
	public void setSearch(Boolean search) {
		this.search = search;
	}

	public String getRelatedDomainModelId(){
		return relatedDomainModelId;
	}
	public void setRelatedDomainModelId(String relatedDomainModelId) {
		this.relatedDomainModelId = relatedDomainModelId;
	}

	public String getRelatedDomainModelName(){
		return relatedDomainModelName;
	}
	public void setRelatedDomainModelName(String relatedDomainModelName) {
		this.relatedDomainModelName = relatedDomainModelName;
	}

	public String getRelatedDomainModelPropertyId(){
		return relatedDomainModelPropertyId;
	}
	public void setRelatedDomainModelPropertyId(String relatedDomainModelPropertyId) {
		this.relatedDomainModelPropertyId = relatedDomainModelPropertyId;
	}

	public String getRelatedDomainModelPropertyName(){
		return relatedDomainModelPropertyName;
	}
	public void setRelatedDomainModelPropertyName(String relatedDomainModelPropertyName) {
		this.relatedDomainModelPropertyName = relatedDomainModelPropertyName;
	}

	public Integer getViewIndex(){
		return viewIndex;
	}
	public void setViewIndex(Integer viewIndex) {
		this.viewIndex = viewIndex;
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