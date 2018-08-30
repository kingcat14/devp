package com.kingzoo.kingcat.project.icode4.business.database.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 数据库项目
 * @author icode
 */
@Entity
@Table
public class DatabaseProject {

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_URL = "url";
	public static final String PROPERTY_USERNAME = "username";
	public static final String PROPERTY_PASSWORD = "password";
	public static final String PROPERTY_DRIVER_NAME = "driverName";
	public static final String PROPERTY_EDITABLE = "editable";


    @Id
    private String id;


    /**
    * 项目名称
    * 
    */
    @Column(name = "name")
	@NotNull(message = "项目名称不能为空")
	@Size(max = 255, message = "项目名称超长，最多255个字符")
	private String name;

    /**
    * 数据库链接
    * 
    */
    @Column(name = "url")
	@NotNull(message = "数据库链接不能为空")
	@Size(max = 255, message = "数据库链接超长，最多255个字符")
	private String url;

    /**
    * 用户名
    * 
    */
    @Column(name = "username")
	@NotNull(message = "用户名不能为空")
	@Size(max = 255, message = "用户名超长，最多255个字符")
	private String username;

    /**
    * 密码
    * 
    */
    @Column(name = "password")
	@NotNull(message = "密码不能为空")
	@Size(max = 255, message = "密码超长，最多255个字符")
	private String password;

    /**
    * 驱动名词
    * 
    */
    @Column(name = "driver_name")
	@NotNull(message = "驱动名词不能为空")
	@Size(max = 255, message = "驱动名词超长，最多255个字符")
	private String driverName;

    /**
    * 可修改
    * 
    */
    @Column(name = "editable")
	@NotNull(message = "可修改不能为空")
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}