package com.kingzoo.kingcat.project.icode4.business.view.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class ViewModelPropertyCondition implements Serializable{


	private String id;
	private Integer version;

	private String name;
	private String code;
	private String type;
	private String viewModelId;
	private Boolean editable;
	private Boolean nullable;
	private String memo;
	private Boolean coreRelation;
	private String domainModelId;
	private String domainModelPropertyId;
	private String propertyGroup;
	private Integer gridIndex;
	private Integer gridIndexMax;
	private Integer gridIndexMin;
	private Boolean gridHidden;
	private Boolean quickSearchCondition;
	private Boolean searchCondition;
	private Integer formIndex;
	private Integer formIndexMax;
	private Integer formIndexMin;
	private Boolean formHidden;
	private Integer viewIndex;
	private Integer viewIndexMax;
	private Integer viewIndexMin;


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


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getViewModelId(){
		return viewModelId;
	}
	public void setViewModelId(String viewModelId) {
		this.viewModelId = viewModelId;
	}


	public Boolean getEditable(){
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}


	public Boolean getNullable(){
		return nullable;
	}
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}


	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}


	public Boolean getCoreRelation(){
		return coreRelation;
	}
	public void setCoreRelation(Boolean coreRelation) {
		this.coreRelation = coreRelation;
	}


	public String getDomainModelId(){
		return domainModelId;
	}
	public void setDomainModelId(String domainModelId) {
		this.domainModelId = domainModelId;
	}


	public String getDomainModelPropertyId(){
		return domainModelPropertyId;
	}
	public void setDomainModelPropertyId(String domainModelPropertyId) {
		this.domainModelPropertyId = domainModelPropertyId;
	}


	public String getPropertyGroup(){
		return propertyGroup;
	}
	public void setPropertyGroup(String propertyGroup) {
		this.propertyGroup = propertyGroup;
	}


	public Integer getGridIndex(){
		return gridIndex;
	}
	public void setGridIndex(Integer gridIndex) {
		this.gridIndex = gridIndex;
	}

	public Integer getGridIndexMin(){
		return gridIndexMin;
	}
	public void setGridIndexMin(Integer gridIndexMin) {
		this.gridIndexMin = gridIndexMin;
	}

	public Integer getGridIndexMax(){
		return gridIndexMax;
	}
	public void setGridIndexMax(Integer gridIndexMax) {
		this.gridIndexMax = gridIndexMax;
	}


	public Boolean getGridHidden(){
		return gridHidden;
	}
	public void setGridHidden(Boolean gridHidden) {
		this.gridHidden = gridHidden;
	}


	public Boolean getQuickSearchCondition(){
		return quickSearchCondition;
	}
	public void setQuickSearchCondition(Boolean quickSearchCondition) {
		this.quickSearchCondition = quickSearchCondition;
	}


	public Boolean getSearchCondition(){
		return searchCondition;
	}
	public void setSearchCondition(Boolean searchCondition) {
		this.searchCondition = searchCondition;
	}


	public Integer getFormIndex(){
		return formIndex;
	}
	public void setFormIndex(Integer formIndex) {
		this.formIndex = formIndex;
	}

	public Integer getFormIndexMin(){
		return formIndexMin;
	}
	public void setFormIndexMin(Integer formIndexMin) {
		this.formIndexMin = formIndexMin;
	}

	public Integer getFormIndexMax(){
		return formIndexMax;
	}
	public void setFormIndexMax(Integer formIndexMax) {
		this.formIndexMax = formIndexMax;
	}


	public Boolean getFormHidden(){
		return formHidden;
	}
	public void setFormHidden(Boolean formHidden) {
		this.formHidden = formHidden;
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


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
