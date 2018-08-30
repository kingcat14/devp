package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class DomainModelPropertyCondition implements Serializable{


	private String id;
	private Integer version;

	private String domainModelId;
	private String name;
	private String code;
	private String type;
	private Boolean persist;
	private Boolean editable;
	private Boolean nullable;
	private String memo;
	private String propertyGroup;
	private String relatedDomainModelId;
	private String relatedDomainModelPropertyId;
	private Integer viewIndex;
	private Integer viewIndexMax;
	private Integer viewIndexMin;
	private Boolean primaryKey;


	public String getDomainModelId(){
		return domainModelId;
	}
	public void setDomainModelId(String domainModelId) {
		this.domainModelId = domainModelId;
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


	public String getRelatedDomainModelPropertyId(){
		return relatedDomainModelPropertyId;
	}
	public void setRelatedDomainModelPropertyId(String relatedDomainModelPropertyId) {
		this.relatedDomainModelPropertyId = relatedDomainModelPropertyId;
	}


	public Integer getViewIndex(){
		return viewIndex;
	}
	public void setViewIndex(Integer viewIndex) {
		this.viewIndex = viewIndex;
	}

	public Integer getViewIndexMin(){
		return viewIndexMin;
	}
	public void setViewIndexMin(Integer viewIndexMin) {
		this.viewIndexMin = viewIndexMin;
	}

	public Integer getViewIndexMax(){
		return viewIndexMax;
	}
	public void setViewIndexMax(Integer viewIndexMax) {
		this.viewIndexMax = viewIndexMax;
	}

	public Boolean getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
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
