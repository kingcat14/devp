package com.kingzoo.kingcat.project.icode4.business.database.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class DatabaseProjectCondition implements Serializable{


	private String id;
	private Integer version;

	private String name;
	private String url;
	private String username;
	private String password;
	private String driverName;
	private Boolean editable;


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


	public String getUsername(){
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword(){
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getDriverName(){
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}


	public Boolean getEditable(){
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
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
