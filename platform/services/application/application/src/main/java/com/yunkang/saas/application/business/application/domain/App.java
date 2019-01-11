package com.yunkang.saas.application.business.application.domain;

import com.yunkang.saas.common.framework.eo.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;



/**
 * 应用
 * @author icode
 */
@Entity
@Table
public class App extends BaseEntity<Long>{

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_TENANT_ID = "tenantId";
	public static final String PROPERTY_APP_CATEGORY_CODE = "appCategoryCode";
	public static final String PROPERTY_APP_CATEGORY_ID = "appCategoryId";
	public static final String PROPERTY_LABEL = "label";
	public static final String PROPERTY_ENABLE = "enable";
	public static final String PROPERTY_ON_BOARD_TIME = "onBoardTime";
	public static final String PROPERTY_URL = "url";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_VISIBLE = "visible";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 应用名称
    * 
    */
    @Column(name = "name")
	@NotNull(message = "应用名称不能为空")
	@Size(max = 255, message = "应用名称超长，最多255个字符")
	private String name;

    /**
    * 应用代码
    * 
    */
    @Column(name = "code")
	@NotNull(message = "应用代码不能为空")
	@Size(max = 255, message = "应用代码超长，最多255个字符")
	private String code;

    /**
    * 所属租户
    * 
    */
    @Column(name = "tenant_id")
	@NotNull(message = "所属租户不能为空")
	private Long tenantId;

    /**
    * 应用类别类别代码
    * 
    */
    @Column(name = "app_category_code")
	@Size(max = 255, message = "应用类别类别代码超长，最多255个字符")
	private String appCategoryCode;

    /**
    * 应用类别
    * 
    */
    @Column(name = "app_category_id")
	private Long appCategoryId;

    /**
    * 标签
    * 应用的标签，逗号分隔，可填写多个
    */
    @Column(name = "标签", columnDefinition = "TEXT")
	private String label;

    /**
    * 已启用
    * 
    */
    @Column(name = "enable")
	private Boolean enable;

    /**
    * 上架时间
    * 
    */
    @Column(name = "on_board_time")
	private Date onBoardTime;

    /**
    * 应用链接
    * 跳转至对应的应用
    */
    @Column(name = "url")
	@Size(max = 255, message = "应用链接超长，最多255个字符")
	private String url;

    /**
    * 描述
    * 
    */
    @Column(name = "描述", columnDefinition = "TEXT")
	private String description;

    /**
    * 可见
    * 
    */
    @Column(name = "visible")
	private Boolean visible;

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

	public Long getTenantId(){
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getAppCategoryCode(){
		return appCategoryCode;
	}
	public void setAppCategoryCode(String appCategoryCode) {
		this.appCategoryCode = appCategoryCode;
	}

	public Long getAppCategoryId(){
		return appCategoryId;
	}
	public void setAppCategoryId(Long appCategoryId) {
		this.appCategoryId = appCategoryId;
	}

	public String getLabel(){
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getEnable(){
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Date getOnBoardTime(){
		return onBoardTime;
	}
	public void setOnBoardTime(Date onBoardTime) {
		this.onBoardTime = onBoardTime;
	}

	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getVisible(){
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
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

