package com.kingzoo.kingcat.project.icode4.business.microservice.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class MicroServiceItfcParametersCondition implements Serializable{


	private String id;
	private Integer version;

	private Integer viewIndex;
	private Integer viewIndexMax;
	private Integer viewIndexMin;
	private String name;
	private String code;
	private String type;
	private String pathMapping;
	private String memo;
	private String microServiceItfcId;
	private Boolean required;


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


	public String getPathMapping(){
		return pathMapping;
	}
	public void setPathMapping(String pathMapping) {
		this.pathMapping = pathMapping;
	}


	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getMicroServiceItfcId(){
		return microServiceItfcId;
	}
	public void setMicroServiceItfcId(String microServiceItfcId) {
		this.microServiceItfcId = microServiceItfcId;
	}


	public Boolean getRequired(){
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
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
