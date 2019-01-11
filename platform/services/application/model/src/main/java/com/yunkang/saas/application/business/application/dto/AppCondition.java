package com.yunkang.saas.application.business.application.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;



public class AppCondition implements Serializable{


	private String id;

	private String name;
	private String code;
	private Long tenantId;
	private Long tenantIdMax;
	private Long tenantIdMin;
	private String appCategoryCode;
	private String appCategoryId;
	private String label;
	private Boolean enable;
	private Date onBoardTime;
	private Date onBoardTimeStart;
	private Date onBoardTimeEnd;
	private String url;
	private String description;
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

	public Long getTenantIdMin(){
		return tenantIdMin;
	}
	public void setTenantIdMin(Long tenantIdMin) {
		this.tenantIdMin = tenantIdMin;
	}

	public Long getTenantIdMax(){
		return tenantIdMax;
	}
	public void setTenantIdMax(Long tenantIdMax) {
		this.tenantIdMax = tenantIdMax;
	}


	public String getAppCategoryCode(){
		return appCategoryCode;
	}
	public void setAppCategoryCode(String appCategoryCode) {
		this.appCategoryCode = appCategoryCode;
	}


	public String getAppCategoryId(){
		return appCategoryId;
	}
	public void setAppCategoryId(String appCategoryId) {
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
	public Date getOnBoardTimeStart(){
		return onBoardTimeStart;
	}
	public void setOnBoardTimeStart(Date onBoardTimeStart) {
		this.onBoardTimeStart = onBoardTimeStart;
	}
	public Date getOnBoardTimeEnd(){
		return onBoardTimeEnd;
	}
	public void setOnBoardTimeEnd(Date onBoardTimeEnd) {
		this.onBoardTimeEnd = onBoardTimeEnd;
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
