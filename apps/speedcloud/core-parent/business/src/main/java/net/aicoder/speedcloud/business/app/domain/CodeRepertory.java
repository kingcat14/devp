package net.aicoder.speedcloud.business.app.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 代码库
 * @author icode
 */
@Entity
@Table(appliesTo = "code_repertory", comment = "[代码库]")
//@DynamicUpdate
//@DynamicInsert
public class CodeRepertory extends BaseEntity{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_URL = "url";
	public static final String PROPERTY_DEVELOP_TYPE = "developType";
	public static final String PROPERTY_USERNAME = "username";
	public static final String PROPERTY_PASSWORD = "password";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 类型
    * git,svn
    */
    @Column(name = "type", nullable = true, updatable = true)
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

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
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


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

