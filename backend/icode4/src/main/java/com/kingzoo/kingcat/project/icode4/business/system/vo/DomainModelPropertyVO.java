package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 领域对象属性的值对象
*/
public class DomainModelPropertyVO {


    private String id;


    /**
    * 所属领域对象
    * 
    */
    private String domainModelId;
    private String domainModelName;


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
    * String，Integer等
    */
    private String type;


    /**
    * 持久化
    * 
    */
    private Boolean persist;


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
     * 可展现
     */
    private Boolean visible;


    /**
     * 主键
     */
    private Boolean primaryKey;

    /**
     * 是否为查询条件不能为空
     *
     */
    private Boolean search;

    /**
    * 备注
    * 
    */
    private String memo;


    /**
    * 属性组
    * 该字段相同的属性，同属一个组；该字段值与code相同的属性，为属性组的主属性了；当关联对象不为空的，会根据属性组的核心属性将属性关联起来
    */
    private String propertyGroup;


    /**
    * 关联对象
    * 属性关联到其他对象时，该字段不为空
    */
    private String relatedDomainModelId;
    private String relatedDomainModelName;


    /**
    * 关联对象属性
    * 属性关联到其他对象时，该字段不为空
    */
    private String relatedDomainModelPropertyId;
    private String relatedDomainModelPropertyName;


    /**
    * 展现顺序
    * 
    */
    private Integer viewIndex;


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
    public Boolean getPersist(){
        return persist;
    }
    public void setPersist(Boolean persist) {
        this.persist = persist;
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
    public String getPropertyGroup(){
        return propertyGroup;
    }
    public void setPropertyGroup(String propertyGroup) {
        this.propertyGroup = propertyGroup;
    }
    public String getRelatedDomainModelId(){
        return relatedDomainModelId;
    }
    public void setRelatedDomainModelId(String relatedDomainModelId) {
        this.relatedDomainModelId = relatedDomainModelId;
    }
    public String getRelatedDomainModelName(){
        return relatedDomainModelName;
    }
    public void setRelatedDomainModelName(String relatedDomainModelName) {
        this.relatedDomainModelName = relatedDomainModelName;
    }
    public String getRelatedDomainModelPropertyId(){
        return relatedDomainModelPropertyId;
    }
    public void setRelatedDomainModelPropertyId(String relatedDomainModelPropertyId) {
        this.relatedDomainModelPropertyId = relatedDomainModelPropertyId;
    }
    public String getRelatedDomainModelPropertyName(){
        return relatedDomainModelPropertyName;
    }
    public void setRelatedDomainModelPropertyName(String relatedDomainModelPropertyName) {
        this.relatedDomainModelPropertyName = relatedDomainModelPropertyName;
    }
    public Integer getViewIndex(){
        return viewIndex;
    }
    public void setViewIndex(Integer viewIndex) {
        this.viewIndex = viewIndex;
    }

    public Boolean getVisible() {
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

    public Boolean getPrimaryKey() {
        return primaryKey;
    }
    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Boolean getSearch() {
        return search;
    }
    public void setSearch(Boolean search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}