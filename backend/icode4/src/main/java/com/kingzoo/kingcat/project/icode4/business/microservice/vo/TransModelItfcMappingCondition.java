package com.kingzoo.kingcat.project.icode4.business.microservice.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class TransModelItfcMappingCondition implements Serializable{


	private String id;
	private Integer version;

	private String transModelId;
	private String microServiceItfcId;
	private String relationType;


	public String getTransModelId(){
		return transModelId;
	}
	public void setTransModelId(String transModelId) {
		this.transModelId = transModelId;
	}


	public String getMicroServiceItfcId(){
		return microServiceItfcId;
	}
	public void setMicroServiceItfcId(String microServiceItfcId) {
		this.microServiceItfcId = microServiceItfcId;
	}


	public String getRelationType(){
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
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
