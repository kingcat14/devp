package com.kingzoo.kingcat.project.icode4.business.codegen.domain.viewmodelcogegen;

import com.kingzoo.kingcat.project.icode4.business.system.cogegen.entity.DomainModelConfig;
import com.kingzoo.kingcat.project.icode4.business.system.cogegen.entity.DomainModelPropertyConfig;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 展现对象属性
 */
public class ViewModelPropertyConfig {

	private String id;

	/**
	 * 属性名
	 *
	 */
	private String name;

	/**
	 * 属性代码
	 *
	 */
	private String code;

	/**
	 * 属性类型
	 *
	 */
	private String type;

	/**
	 * 所属展现对象
	 *
	 */
	private ViewModelConfig viewModel;

	/**
	 * 可修改
	 *
	 */
	private Boolean editable;

	/**
	 * 可为空
	 *
	 */
	private Boolean nullable;

	/**
	 * 备注
	 *
	 */
	private String memo;

	/**
	 * 核心对象属性
	 *
	 */
	private Boolean coreRelation;

	/**
	 * 关联对象
	 *
	 */
	private DomainModelConfig domainModel;


	/**
	 * 关联对象属性
	 *
	 */
	private DomainModelPropertyConfig domainModelProperty;


	/**
	 * 属性组
	 * 该字段相同的属性，同属一个组；该字段值与code相同的属性，为属性组的主属性了；当关联对象不为空的，会根据属性组的核心属性将属性关联起来
	 */
	private String propertyGroup;

	/**
	 * 列表排序
	 *
	 */
	private Integer gridIndex;

	/**
	 * 列表隐藏
	 *
	 */
	private Boolean gridHidden;

	/**
	 * 表单排序
	 *
	 */
	private Integer formIndex;

	/**
	 * 表单隐藏
	 *
	 */
	private Boolean formHidden;

	/**
	 * 展现排序
	 *
	 */
	private Integer viewIndex;


	/**
	 * 是否查询条件
	 */
	private Boolean searchCondition;

	/**
	 * 是否快速查询条件
	 */
	private Boolean quickSearchCondition;


	/**是否是引用属性(基础领域对象或者其他领域对象的属性)*/
	private Boolean reference;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ViewModelConfig getViewModel() {
		return viewModel;
	}

	public void setViewModel(ViewModelConfig viewModel) {
		this.viewModel = viewModel;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getCoreRelation() {
		return coreRelation;
	}

	public void setCoreRelation(Boolean coreRelation) {
		this.coreRelation = coreRelation;
	}

	public DomainModelConfig getDomainModel() {
		return domainModel;
	}

	public void setDomainModel(DomainModelConfig domainModel) {
		this.domainModel = domainModel;
	}

	public DomainModelPropertyConfig getDomainModelProperty() {
		return domainModelProperty;
	}

	public void setDomainModelProperty(DomainModelPropertyConfig domainModelProperty) {
		this.domainModelProperty = domainModelProperty;
	}

	public String getPropertyGroup() {
		return propertyGroup;
	}

	public void setPropertyGroup(String propertyGroup) {
		this.propertyGroup = propertyGroup;
	}

	public Integer getGridIndex() {
		return gridIndex;
	}

	public void setGridIndex(Integer gridIndex) {
		this.gridIndex = gridIndex;
	}

	public Boolean getGridHidden() {
		return gridHidden;
	}

	public void setGridHidden(Boolean gridHidden) {
		this.gridHidden = gridHidden;
	}

	public Integer getFormIndex() {
		return formIndex;
	}

	public void setFormIndex(Integer formIndex) {
		this.formIndex = formIndex;
	}

	public Boolean getFormHidden() {
		return formHidden;
	}

	public void setFormHidden(Boolean formHidden) {
		this.formHidden = formHidden;
	}

	public Integer getViewIndex() {
		return viewIndex;
	}

	public void setViewIndex(Integer viewIndex) {
		this.viewIndex = viewIndex;
	}


	public Boolean getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(Boolean searchCondition) {
		this.searchCondition = searchCondition;
	}

	public Boolean getQuickSearchCondition() {
		return quickSearchCondition;
	}

	public void setQuickSearchCondition(Boolean quickSearchCondition) {
		this.quickSearchCondition = quickSearchCondition;
	}

	public Boolean getReference() {
		return reference;
	}

	public void setReference(Boolean reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}
