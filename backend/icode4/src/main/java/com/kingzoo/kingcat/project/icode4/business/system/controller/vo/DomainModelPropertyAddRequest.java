package com.kingzoo.kingcat.project.icode4.business.system.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 领域对象属性
 * @author icode
 */
public class DomainModelPropertyAddRequest {

	public static final String PROPERTY_DOMAIN_MODEL_ID = "domainModelId";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_PERSIST = "persist";
	public static final String PROPERTY_EDITABLE = "editable";
	public static final String PROPERTY_NULLABLE = "nullable";
	public static final String PROPERTY_VISIBLE = "visible";
	public static final String PROPERTY_MEMO = "memo";
	public static final String PROPERTY_PROPERTY_GROUP = "propertyGroup";
	public static final String PROPERTY_RELATED_DOMAIN_MODEL_ID = "relatedDomainModelId";
	public static final String PROPERTY_RELATED_DOMAIN_MODEL_PROPERTY_ID = "relatedDomainModelPropertyId";
	public static final String PROPERTY_VIEW_INDEX = "viewIndex";

    /**
	 * 所属领域对象
	 * 
     */
	@NotNull(message = "所属领域对象不能为空")
	private String domainModelId;
	private String domainModelName;

    /**
	 * 属性名
	 * 
     */
	@NotNull(message = "属性名不能为空")
	@Size(max = 255, message = "属性名超长，最多255个字符")
	private String name;

    /**
	 * 属性代码
	 * 
     */
	@NotNull(message = "属性代码不能为空")
	@Size(max = 255, message = "属性代码超长，最多255个字符")
	private String code;

    /**
	 * 属性类型
	 * String，Integer等
     */
	@NotNull(message = "属性类型不能为空")
	@Size(max = 255, message = "属性类型超长，最多255个字符")
	private String type;

    /**
	 * 持久化
	 * 
     */
	@NotNull(message = "持久化不能为空")
	private Boolean persist;

    /**
	 * 可修改
	 * 
     */
	@NotNull(message = "可修改不能为空")
	private Boolean editable;

    /**
	 * 可为空
	 * 
     */
	@NotNull(message = "可为空不能为空")
	private Boolean nullable;

	/**
	 * 是否为主键
	 *
	 */
	@NotNull(message = "是否为主键")
	private Boolean primaryKey;

	/**
	 * 是否为查询条件不能为空
	 *
	 */
	@NotNull(message = "是否为查询条件不能为空")
	private Boolean search;
    /**
	 * 备注
	 * 
     */
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String memo;

    /**
	 * 属性组
	 * 该字段相同的属性，同属一个组；该字段值与code相同的属性，为属性组的主属性了；当关联对象不为空的，会根据属性组的核心属性将属性关联起来
     */
	@Size(max = 255, message = "属性组超长，最多255个字符")
	private String propertyGroup;

    /**
	 * 关联对象
	 * 属性关联到其他对象时，该字段不为空
     */
	private String relatedDomainModelId;
	private String relatedDomainModelName;

    /**
	 * 关联对象属性
	 * 属性关联到其他对象时，该字段不为空
     */
	private String relatedDomainModelPropertyId;
	private String relatedDomainModelPropertyName;

    /**
	 * 展现顺序
	 * 
     */
	private Integer viewIndex;

	/**
	 * 是否出现在VO中
	 */
	private Boolean visible;


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

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPropertyGroup(){
		return propertyGroup;
	}
	public void setPropertyGroup(String propertyGroup) {
		this.propertyGroup = propertyGroup;
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

	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
