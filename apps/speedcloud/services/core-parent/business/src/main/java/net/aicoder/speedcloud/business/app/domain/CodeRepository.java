package net.aicoder.speedcloud.business.app.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 代码库
 * @author icode
 */
@Entity()
@Table(name = "app_code_repository")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class CodeRepository extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_URL = "url";
	public static final String PROPERTY_DEVELOP_TYPE = "developType";
	public static final String PROPERTY_USERNAME = "username";
	public static final String PROPERTY_PASSWORD = "password";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_APP = "app";


    @Id
    @Column(name = "id", length = 32)
    private String id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 代码库类型
    * git,svn
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "代码库类型超长，最多255个字符")
	private String type;

    /**
    * url
    * 
    */
    @Column(name = "url", nullable = true, updatable = true)
	@Size(max = 255, message = "url超长，最多255个字符")
	private String url;

    /**
    * 开发模式
    * 
    */
    @Column(name = "develop_type", nullable = true, updatable = true)
	private Long developType;

    /**
    * 用户名
    * 
    */
    @Column(name = "username", nullable = true, updatable = true)
	@Size(max = 255, message = "用户名超长，最多255个字符")
	private String username;

    /**
    * 密码
    * 
    */
    @Column(name = "password", nullable = true, updatable = true)
	@Size(max = 255, message = "密码超长，最多255个字符")
	private String password;

    /**
    * 描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String description;

    /**
    * 应用
    * 
    */
    @Column(name = "app", nullable = true, updatable = true)
	@Size(max = 255, message = "应用超长，最多255个字符")
	private String app;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public Long getDevelopType(){
		return developType;
	}
	public void setDevelopType(Long developType) {
		this.developType = developType;
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

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getApp(){
		return app;
	}
	public void setApp(String app) {
		this.app = app;
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

