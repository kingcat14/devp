package com.kingzoo.kingcat.project.icode4.business.system.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 属性展现配置
 * @author icode
 */
@Entity
@Table
public class DomainModelViewProperty {

	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_VIEW_INDEX = "viewIndex";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ADD_VIEWABLE = "addViewable";
	public static final String PROPERTY_ADDABLE = "addable";
	public static final String PROPERTY_ADD_REQUIRED = "addRequired";
	public static final String PROPERTY_EDIT_VIEWABLE = "editViewable";
	public static final String PROPERTY_EDITABLE = "editable";
	public static final String PROPERTY_EDIT_REQUIRED = "editRequired";
	public static final String PROPERTY_SEARCH_CONDITION = "searchCondition";
	public static final String PROPERTY_GIRD_COLUMN = "girdColumn";
	public static final String PROPERTY_SIMPLE_SEARCH_CONDITION = "simpleSearchCondition";
	public static final String PROPERTY_DOMAIN_MODEL_ID = "domainModelId";


    @Id
    private String id;


    /**
    * 展现顺序
    * 
    */
    @Column(name = "view_index")
	private Integer viewIndex;

    /**
    * 属性代码
    * 
    */
    @Column(name = "code")
	@NotNull(message = "属性代码不能为空")
	@Size(max = 255, message = "属性代码超长，最多255个字符")
	private String code;

    /**
    * 属性名
    * 
    */
    @Column(name = "name")
	@NotNull(message = "属性名不能为空")
	@Size(max = 255, message = "属性名超长，最多255个字符")
	private String name;

    /**
    * 新增页可见
    * 
    */
    @Column(name = "add_viewable")
	@NotNull(message = "新增页可见不能为空")
	private Boolean addViewable;

    /**
    * 新增页可填写
    * 
    */
    @Column(name = "addable")
	private Boolean addable;

    /**
    * 新增必填
    * 
    */
    @Column(name = "add_required")
	@NotNull(message = "新增必填不能为空")
	private Boolean addRequired;

    /**
    * 修改页可见
    * 
    */
    @Column(name = "edit_viewable")
	@NotNull(message = "修改页可见不能为空")
	private Boolean editViewable;

    /**
    * 修改页可修改
    * 
    */
    @Column(name = "editable")
	private Boolean editable;

    /**
    * 修改页必填
    * 
    */
    @Column(name = "edit_required")
	private Boolean editRequired;

    /**
    * 查询可见
    * 
    */
    @Column(name = "search_condition")
	private Boolean searchCondition;

    /**
    * 列表可见
    * 
    */
    @Column(name = "gird_column")
	private Boolean girdColumn;

    /**
    * 快速查询条件
    * 
    */
    @Column(name = "simple_search_condition")
	private Boolean simpleSearchCondition;

    /**
    * domainModelId
    * 
    */
    @Column(name = "domain_model_id")
	@Size(max = 255, message = "domainModelId超长，最多255个字符")
	private String domainModelId;

	public Integer getViewIndex(){
		return viewIndex;
	}
	public void setViewIndex(Integer viewIndex) {
		this.viewIndex = viewIndex;
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