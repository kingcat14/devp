package com.yunkang.saas.bootstrap.business.resource.domain;

import com.yunkang.saas.common.framework.eo.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;


/**
 * 资源
 * @author icode
 */
@Entity(name = "platform_resource")
@Table(appliesTo = "platform_resource", comment = "[资源]")
public class Resource extends BaseEntity{

	public static final Long TOP_NODE_ID = -1L;

	public static final String PROPERTY_APP_CODE = "appCode";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_URL = "url";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_PARENT_CODE = "parentCode";
	public static final String PROPERTY_ORDER_INDEX = "orderIndex";
	public static final String PROPERTY_ICON_CLASS = "iconClass";
	public static final String PROPERTY_HIDDEN = "hidden";

    @Id
    @Column(name = "rid")
    private Long id;

    /**
    * 资源名
    * 
    */
    @Column(name = "name")
	@Size(max = 255, message = "资源名超长，最多255个字符")
	private String name;

    /**
    * 资源链接
    * 
    */
    @Column(name = "url")
	@Size(max = 255, message = "资源链接超长，最多255个字符")
	private String url;

    /**
    * 资源类型
    * 
    */
    @Column(name = "type")
	@Size(max = 255, message = "资源类型超长，最多255个字符")
	private String type;

	/** 所属应用 */
	@Column(name = "app_code")
	private String appCode;
    /**
    * 资源代码
    * 
    */
    @Column(name = "code")
	private Long code;

    /**
    * 父节点
    * 
    */
    @Column(name = "parent_code")
	private Long parentCode;

    /**排序*/
    @Column(name = "order_index")
	private Integer orderIndex;

    /**资源图标*/
	@Column(name = "icon_class")
    private String iconClass;

	@Column(name = "hidden")
	private Boolean hidden;

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

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Long getCode(){
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}

	public Long getParentCode() {
		return parentCode;
	}
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getOrderIndex(){
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
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

	public Boolean getHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

