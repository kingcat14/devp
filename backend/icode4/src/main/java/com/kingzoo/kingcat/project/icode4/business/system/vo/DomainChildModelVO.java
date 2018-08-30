package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 领域子对象的值对象
*/
public class DomainChildModelVO {


    private String id;


    /**
    * 对象名称
    * 
    */
    private String name;


    /**
    * 对象代码
    * 
    */
    private String code;


    /**
    * 排序
    * 
    */
    private Integer viewIndex;


    /**
    * 描述
    * 
    */
    private String description;


    /**
    * 所属根对象
    * 
    */
    private String domainModelId;
    private String domainModelName;


    /**
    * 是否持久化
    * 
    */
    private Boolean persist;


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
    public Integer getViewIndex(){
        return viewIndex;
    }
    public void setViewIndex(Integer viewIndex) {
        this.viewIndex = viewIndex;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public Boolean getPersist(){
        return persist;
    }
    public void setPersist(Boolean persist) {
        this.persist = persist;
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