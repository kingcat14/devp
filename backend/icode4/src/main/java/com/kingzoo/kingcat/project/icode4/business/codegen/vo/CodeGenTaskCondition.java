package com.kingzoo.kingcat.project.icode4.business.codegen.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class CodeGenTaskCondition implements Serializable{


	private String id;
	private Integer version;

	private String modelIds;
	private String tplCodeIds;
	private String destPath;


	public String getModelIds(){
		return modelIds;
	}
	public void setModelIds(String modelIds) {
		this.modelIds = modelIds;
	}


	public String getTplCodeIds(){
		return tplCodeIds;
	}
	public void setTplCodeIds(String tplCodeIds) {
		this.tplCodeIds = tplCodeIds;
	}


	public String getDestPath(){
		return destPath;
	}
	public void setDestPath(String destPath) {
		this.destPath = destPath;
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
