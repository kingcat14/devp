package com.kingzoo.kingcat.project.icode4.business.microservice.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class TransModelPropertyCondition implements Serializable{


	private String id;
	private Integer version;

	private String name;
	private String code;
	private String type;
	private String transModelId;
	private Boolean editable;
	private Boolean nullable;
	private String memo;
	private Boolean coreRelation;
	private String domainModelId;
	private String domainModelPropertyId;


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


	public String getDomainModelPropertyId(){
		return domainModelPropertyId;
	}
	public void setDomainModelPropertyId(String domainModelPropertyId) {
		this.domainModelPropertyId = domainModelPropertyId;
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
