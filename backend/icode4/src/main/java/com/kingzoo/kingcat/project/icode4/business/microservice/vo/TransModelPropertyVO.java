package com.kingzoo.kingcat.project.icode4.business.microservice.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 传输对象属性的值对象
*/
public class TransModelPropertyVO {


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
    * 所属传输对象
    * 
    */
    private String transModelId;
    private String transModelName;


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
    private String domainModelId;
    private String domainModelName;


    /**
    * 关联对象属性
    * 
    */
    private String domainModelPropertyId;
    private String domainModelPropertyName;


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
    public String getTransModelId(){
        return transModelId;
    }
    public void setTransModelId(String transModelId) {
        this.transModelId = transModelId;
    }
    public String getTransModelName(){
        return transModelName;
    }
    public void setTransModelName(String transModelName) {
        this.transModelName = transModelName;
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