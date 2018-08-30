package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 属性展现配置的值对象
*/
public class DomainModelViewPropertyVO {



    /**
    * 主键
    * 
    */
    private String id;


    /**
    * 展现顺序
    * 
    */
    private Integer viewIndex;


    /**
    * 属性代码
    * 
    */
    private String code;


    /**
    * 属性名
    * 
    */
    private String name;


    /**
    * 新增页可见
    * 
    */
    private Boolean addViewable;


    /**
    * 新增页可填写
    * 
    */
    private Boolean addable;


    /**
    * 新增必填
    * 
    */
    private Boolean addRequired;


    /**
    * 修改页可见
    * 
    */
    private Boolean editViewable;


    /**
    * 修改页可修改
    * 
    */
    private Boolean editable;


    /**
    * 修改页必填
    * 
    */
    private Boolean editRequired;


    /**
    * 查询可见
    * 
    */
    private Boolean searchCondition;


    /**
    * 列表可见
    * 
    */
    private Boolean girdColumn;


    /**
    * 快速查询条件
    * 
    */
    private Boolean simpleSearchCondition;


    /**
    * domainModelId
    * 
    */
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


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}