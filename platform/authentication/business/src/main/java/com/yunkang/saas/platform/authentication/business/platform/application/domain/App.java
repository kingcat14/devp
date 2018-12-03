package com.yunkang.saas.platform.authentication.business.platform.application.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;



/**
 * 应用
 * @author icode
 */
@Entity()
@Table(name = "platform_app")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class App extends BaseEntity<Long>{

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_APP_CATEGORY_ID = "appCategoryId";
	public static final String PROPERTY_LABEL = "label";
	public static final String PROPERTY_ENABLE = "enable";
	public static final String PROPERTY_ON_BOARD_TIME = "onBoardTime";
	public static final String PROPERTY_URL = "url";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_VISIBLE = "visible";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 应用名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "应用名称超长，最多255个字符")
	private String name;

    /**
    * 应用代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "应用代码超长，最多255个字符")
	private String code;

    /**
    * 应用类别
    * 
    */
    @Column(name = "app_category_id", nullable = true, updatable = true)
	private Long appCategoryId;

    /**
    * 标签
    * 应用的标签，逗号分隔，可填写多个
    */
    @Column(name = "label", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String label;

    /**
    * 已启用
    * 
    */
    @Column(name = "enable", nullable = true, updatable = true)
	private Boolean enable;

    /**
    * 上架时间
    * 
    */
    @Column(name = "on_board_time", nullable = true, updatable = true)
	private Date onBoardTime;

    /**
    * 应用链接
    * 跳转至对应的应用
    */
    @Column(name = "url", nullable = true, updatable = true)
	@Size(max = 255, message = "应用链接超长，最多255个字符")
	private String url;

    /**
    * 描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String description;

    /**
    * 可见
    * 
    */
    @Column(name = "visible", nullable = true, updatable = true)
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

