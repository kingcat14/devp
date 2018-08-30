package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class DomainModelViewPropertyCondition implements Serializable{


	private String id;
	private Integer version;

	private Integer viewIndex;
	private Integer viewIndexMax;
	private Integer viewIndexMin;
	private String code;
	private String name;
	private Boolean addViewable;
	private Boolean addable;
	private Boolean addRequired;
	private Boolean editViewable;
	private Boolean editable;
	private Boolean editRequired;
	private Boolean searchCondition;
	private Boolean girdColumn;
	private Boolean simpleSearchCondition;
	private String domainModelId;


	public String getId(){
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public Integer getViewIndex(){
		return viewIndex;
	}
	public void setViewIndex(Integer viewIndex) {
		this.viewIndex = viewIndex;
	}

	public Integer getViewIndexMin(){
		return viewIndexMin;
	}
	public void setViewIndexMin(Integer viewIndexMin) {
		this.viewIndexMin = viewIndexMin;
	}

	public Integer getViewIndexMax(){
		return viewIndexMax;
	}
	public void setViewIndexMax(Integer viewIndexMax) {
		this.viewIndexMax = viewIndexMax;
	}


	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public Boolean getAddViewable(){
		return addViewable;
	}
	public void setAddViewable(Boolean addViewable) {
		this.addViewable = addViewable;
	}


	public Boolean getAddable(){
		return addable;
	}
	public void setAddable(Boolean addable) {
		this.addable = addable;
	}


	public Boolean getAddRequired(){
		return addRequired;
	}
	public void setAddRequired(Boolean addRequired) {
		this.addRequired = addRequired;
	}


	public Boolean getEditViewable(){
		return editViewable;
	}
	public void setEditViewable(Boolean editViewable) {
		this.editViewable = editViewable;
	}


	public Boolean getEditable(){
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}


	public Boolean getEditRequired(){
		return editRequired;
	}
	public void setEditRequired(Boolean editRequired) {
		this.editRequired = editRequired;
	}


	public Boolean getSearchCondition(){
		return searchCondition;
	}
	public void setSearchCondition(Boolean searchCondition) {
		this.searchCondition = searchCondition;
	}


	public Boolean getGirdColumn(){
		return girdColumn;
	}
	public void setGirdColumn(Boolean girdColumn) {
		this.girdColumn = girdColumn;
	}


	public Boolean getSimpleSearchCondition(){
		return simpleSearchCondition;
	}
	public void setSimpleSearchCondition(Boolean simpleSearchCondition) {
		this.simpleSearchCondition = simpleSearchCondition;
	}


	public String getDomainModelId(){
		return domainModelId;
	}
	public void setDomainModelId(String domainModelId) {
		this.domainModelId = domainModelId;
	}




	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
