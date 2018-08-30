package com.kingzoo.kingcat.project.icode4.business.view.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 展现对象属性
 * @author icode
 */
@Entity
@Table
public class  ViewModelProperty {

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_VIEW_MODEL_ID = "viewModelId";
	public static final String PROPERTY_EDITABLE = "editable";
	public static final String PROPERTY_NULLABLE = "nullable";
	public static final String PROPERTY_MEMO = "memo";
	public static final String PROPERTY_CORE_RELATION = "coreRelation";
	public static final String PROPERTY_DOMAIN_MODEL_ID = "domainModelId";
	public static final String PROPERTY_DOMAIN_MODEL_PROPERTY_ID = "domainModelPropertyId";
	public static final String PROPERTY_PROPERTY_GROUP = "propertyGroup";
	public static final String PROPERTY_GRID_INDEX = "gridIndex";
	public static final String PROPERTY_GRID_HIDDEN = "gridHidden";
	public static final String PROPERTY_QUICK_SEARCH_CONDITION = "quickSearchCondition";
	public static final String PROPERTY_SEARCH_CONDITION = "searchCondition";
	public static final String PROPERTY_FORM_INDEX = "formIndex";
	public static final String PROPERTY_FORM_HIDDEN = "formHidden";
	public static final String PROPERTY_VIEW_INDEX = "viewIndex";


    @Id
    private String id;


    /**
    * 属性名
    * 
    */
    @Column(name = "name")
	@NotNull(message = "属性名不能为空")
	@Size(max = 255, message = "属性名超长，最多255个字符")
	private String name;

    /**
    * 属性代码
    * 
    */
    @Column(name = "code")
	@NotNull(message = "属性代码不能为空")
	@Size(max = 255, message = "属性代码超长，最多255个字符")
	private String code;

    /**
    * 属性类型
    * 
    */
    @Column(name = "type")
	@NotNull(message = "属性类型不能为空")
	@Size(max = 255, message = "属性类型超长，最多255个字符")
	private String type;

    /**
    * 所属展现对象
    * 
    */
    @Column(name = "view_modelId")
	@NotNull(message = "所属展现对象不能为空")
	private String viewModelId;
	@Transient
	private String viewModelName;

    /**
    * 可修改
    * 
    */
    @Column(name = "editable")
	@NotNull(message = "可修改不能为空")
	private Boolean editable;

    /**
    * 可为空
    * 
    */
    @Column(name = "nullable")
	@NotNull(message = "可为空不能为空")
	private Boolean nullable;

    /**
    * 备注
    * 
    */
    @Column(name = "memo")
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String memo;

    /**
    * 核心关联
    * 如果为true，则通过该属性关联到对应的关联对象
    */
    @Column(name = "core_relation")
	@NotNull(message = "核心关联不能为空")
	private Boolean coreRelation;

    /**
    * 关联对象
    * 
    */
    @Column(name = "domain_modelId")
	private String domainModelId;
	@Transient
	private String domainModelName;

    /**
    * 关联对象属性
    * 
    */
    @Column(name = "domain_model_propertyId")
	private String domainModelPropertyId;
	@Transient
	private String domainModelPropertyName;

    /**
    * 属性组
    * 该字段相同的属性，同属一个组；该字段值与code相同的属性，为属性组的主属性了；当关联对象不为空的，会根据属性组的核心属性将属性关联起来
    */
    @Column(name = "property_group")
	@Size(max = 255, message = "属性组超长，最多255个字符")
	private String propertyGroup;

    /**
    * 列表排序
    * 
    */
    @Column(name = "grid_index")
	@NotNull(message = "列表排序不能为空")
	private Integer gridIndex;

    /**
    * 列表隐藏
    * 
    */
    @Column(name = "grid_hidden")
	@NotNull(message = "列表隐藏不能为空")
	private Boolean gridHidden;

    /**
    * 是否快速查询条件
    * 
    */
    @Column(name = "quick_search_condition")
	private Boolean quickSearchCondition;

    /**
    * 是否查询条件
    * 
    */
    @Column(name = "search_condition")
	private Boolean searchCondition;

    /**
    * 表单排序
    * 
    */
    @Column(name = "form_index")
	@NotNull(message = "表单排序不能为空")
	private Integer formIndex;

    /**
    * 表单隐藏
    * 
    */
    @Column(name = "form_hidden")
	@NotNull(message = "表单隐藏不能为空")
	private Boolean formHidden;

    /**
    * 展现排序
    * 
    */
    @Column(name = "view_index")
	@NotNull(message = "展现排序不能为空")
	private Integer viewIndex;

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
	public String getViewModelName(){
		return viewModelName;
	}
	public void setViewModelName(String viewModelName) {
		this.viewModelName = viewModelName;
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
	public String getDomainModelName(){
		return domainModelName;
	}
	public void setDomainModelName(String domainModelName) {
		this.domainModelName = domainModelName;
	}

	public String getDomainModelPropertyId(){
		return domainModelPropertyId;
	}
	public void setDomainModelPropertyId(String domainModelPropertyId) {
		this.domainModelPropertyId = domainModelPropertyId;
	}
	public String getDomainModelPropertyName(){
		return domainModelPropertyName;
	}
	public void setDomainModelPropertyName(String domainModelPropertyName) {
		this.domainModelPropertyName = domainModelPropertyName;
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