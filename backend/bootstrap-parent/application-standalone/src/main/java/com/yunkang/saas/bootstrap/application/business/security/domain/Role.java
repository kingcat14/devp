package com.yunkang.saas.bootstrap.application.business.security.domain;

import com.yunkang.saas.common.jpa.SaaSEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 角色
 * @author icode
 */
@Entity(name = "platform_role")
@Table(appliesTo = "platform_role", comment = "[账号密码]")
public class Role extends SaaSEntity<Long> {

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_APP_CODE = "appCode";

    @Id
    @Column(name = "rid")
    private Long id;

	@Column(name = "app_code", updatable = false)
	private String appCode;

    /**角色名称*/
    @Column(name = "name")
	@NotNull(message = "角色名称不能为空")
	@Size(max = 255, message = "角色名称超长，最多255个字符")
	private String name;

	/**角色代码*/
	@Column(name = "code")
	@Size(max = 255, message = "角色名称超长，最多255个字符")
	private String code;

    /**角色描述*/
    @Column(name = "description")
	@Size(max = 255, message = "角色描述超长，最多255个字符")
	private String description;

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
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

