package com.kingzoo.kingcat.project.icode4.business.tplfile.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class TplCodeCondition implements Serializable{


	private String id;
	private Integer version;

	private String name;
	private String code;
	private String type;
	private String acceptModelTypeId;
	private String filePath;
	private String fileName;
	private String tplSetId;
	private Boolean overridable;
	private String content;


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


	public String getAcceptModelTypeId(){
		return acceptModelTypeId;
	}
	public void setAcceptModelTypeId(String acceptModelTypeId) {
		this.acceptModelTypeId = acceptModelTypeId;
	}


	public String getFilePath(){
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getFileName(){
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getTplSetId(){
		return tplSetId;
	}
	public void setTplSetId(String tplSetId) {
		this.tplSetId = tplSetId;
	}


	public Boolean getOverridable(){
		return overridable;
	}
	public void setOverridable(Boolean overridable) {
		this.overridable = overridable;
	}


	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
