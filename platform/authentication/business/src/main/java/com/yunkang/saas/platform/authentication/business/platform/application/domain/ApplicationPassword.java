package com.yunkang.saas.platform.authentication.business.platform.application.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 应用密码
 * @author icode
 */
@Entity()
@Table(name = "platform_application_password")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class ApplicationPassword extends BaseEntity<Long>{

	public static final String PROPERTY_APP_ID = "appId";
	public static final String PROPERTY_ACCESS_ID = "accessId";
	public static final String PROPERTY_ACCESS_KEY = "accessKey";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 应用
    * 
    */
    @Column(name = "app_id", nullable = false, updatable = false)
	private Long appId;

    /**
    * 访问ID
    * 
    */
    @Column(name = "access_id", nullable = false, updatable = true)
	@Size(max = 255, message = "访问ID超长，最多255个字符")
	private String accessId;

    /**
    * 访问密码
    * 
    */
    @Column(name = "access_key", nullable = false, updatable = true)
	@Size(max = 255, message = "访问密码超长，最多255个字符")
	private String accessKey;

	public Long getAppId(){
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAccessId(){
		return accessId;
	}
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public String getAccessKey(){
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
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

