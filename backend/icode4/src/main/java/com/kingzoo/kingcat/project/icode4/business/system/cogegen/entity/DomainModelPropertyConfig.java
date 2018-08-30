package com.kingzoo.kingcat.project.icode4.business.system.cogegen.entity;

import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelViewProperty;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DomainModelPropertyConfig extends DomainModelAttributeConfig {

	private String id;

    private String name;

    private String code;
    
    private String displayName;
    
    private String type;

    private Boolean persist;

	private Boolean editable;

	private Boolean required;

	private Boolean visible;

	private Boolean primaryKey;

	private Boolean search;

	private boolean isReferenceFlag = false;

	private DomainModelConfig reference;

	private String referencePropertyCode;
	private String referencePropertyName;

	private String memo;

	private Integer viewIndex;

	private String columnName;

	/**针对展现的一些配置*/
	private DomainModelViewProperty viewConfig;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName(){
		return StringUtils.trim(name);
	}
	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}
    
    public String getDisplayName(){
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Boolean getEditable() {
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
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

	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean isReferenceFlag() {
		return isReferenceFlag;
	}
	public void setReferenceFlag(boolean referenceFlag) {
		isReferenceFlag = referenceFlag;
	}

	public DomainModelConfig getReference() {
		return reference;
	}
	public void setReference(DomainModelConfig reference) {
		this.reference = reference;
	}

	public Integer getViewIndex() {
		return viewIndex;
	}
	public void setViewIndex(Integer viewIndex) {
		this.viewIndex = viewIndex;
	}

	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getReferencePropertyCode() {
		return referencePropertyCode;
	}
	public void setReferencePropertyCode(String referencePropertyCode) {
		this.referencePropertyCode = referencePropertyCode;
	}

	public String getReferencePropertyName() {
		return referencePropertyName;
	}
	public void setReferencePropertyName(String referencePropertyName) {
		this.referencePropertyName = referencePropertyName;
	}

	public DomainModelViewProperty getViewConfig() {
		return viewConfig;
	}
	public void setViewConfig(DomainModelViewProperty viewConfig) {
		this.viewConfig = viewConfig;
	}

	public String getCategory(){
		return "property";
	}

	@Override
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}
