package com.kingzoo.kingcat.project.icode4.business.microservice.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class MicroServiceItfcCondition implements Serializable{


	private String id;
	private Integer version;

	private String name;
	private String code;
	private String url;
	private String method;
	private String microServiceId;
	private String description;
	private String responseId;
	private String requestId;


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


	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


	public String getMethod(){
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}


	public String getMicroServiceId(){
		return microServiceId;
	}
	public void setMicroServiceId(String microServiceId) {
		this.microServiceId = microServiceId;
	}


	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getResponseId(){
		return responseId;
	}
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}


	public String getRequestId(){
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
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
