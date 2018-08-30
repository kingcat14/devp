package com.kingzoo.kingcat.project.icode4.business.security.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


/**
* 资源
*/
@Entity(name = "Resource")
@Table(name = "resource")
public class Resource implements Serializable {

	public static final String PROPERTY_TENANT_ID = "tenantId";
    public static final String PROPERTY_PARENT_ID = "parentId";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_ORDER_INDEX = "orderIndex";
    public static final String PROPERTY_URL = "url";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_CREATE_UID = "createUid";
	public static final String PROPERTY_MODIFY_UID = "modifyUid";
	public static final String PROPERTY_CREATE_AT = "createAt";

	@Id
	@Column(name = "id")
	private String id;

	/**
	* 租户id
	*/
	@Column(name = "tenant_id")
	private String tenantId;


	/**
	* 上级ID
	*/
	@Column
	@NotNull(message = "上级ID不能为空")
	@Size(max = 255, message = "上级ID超长，最多255个字符")
	private String parentId;


	/**
	* 资源名称
	*/
	@Column
	@NotNull(message = "资源名称不能为空")
	@Size(max = 255, message = "资源名称超长，最多255个字符")
	private String name;


	/**
	* 展现顺序
	*/
	@Column
	@NotNull(message = "展现顺序不能为空")
	@Size(max = 255, message = "展现顺序超长，最多255个字符")
	private String orderIndex;


	/**
	* 资源链接
	*/
	@Column
	@Size(max = 255, message = "资源链接超长，最多255个字符")
	private String url;


	/**
	* 资源类型
	*/
	@Column
	@NotNull(message = "资源类型不能为空")
	@Size(max = 255, message = "资源类型超长，最多255个字符")
	private String type;


	/**
	* 资源代码
	*/
	@Column
	@Size(max = 255, message = "资源代码超长，最多255个字符")
	private String code;

	@Column(name = "create_at")
	private Date createAt;

	@Column(name = "create_uid")
	private Long createUid;

	@Column(name = "create_uname")
	private String createUname;

	@Column(name = "modify_at")
	private Date modifyAt;

	@Column(name = "modify_uid")
	private Long modifyUid;

	@Column(name = "modify_uname")
	private String modifyUname;

	public String getParentId(){
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getOrderIndex(){
		return orderIndex;
	}
	public void setOrderIndex(String orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public Long getCreateUid() {
		return createUid;
	}
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}

	public String getCreateUname() {
		return createUname;
	}
	public void setCreateUname(String createUname) {
		this.createUname = createUname;
	}

	public Date getModifyAt() {
		return modifyAt;
	}
	public void setModifyAt(Date modifyAt) {
		this.modifyAt = modifyAt;
	}

	public Long getModifyUid() {
		return modifyUid;
	}

	public void setModifyUid(Long modifyUid) {
		this.modifyUid = modifyUid;
	}

	public String getModifyUname() {
		return modifyUname;
	}
	public void setModifyUname(String modifyUname) {
		this.modifyUname = modifyUname;
	}

    	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}