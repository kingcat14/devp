package com.kingzoo.kingcat.project.icode4.business.microservice.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 传输对象的值对象
*/
public class TransModelVO {


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
    * 备注
    * 
    */
    private String memo;


    /**
    * 描述
    * 
    */
    private String description;


    /**
    * 所属模块ID
    * 
    */
    private String moduleId;
    private String moduleName;


    /**
    * 所属领域对象
    * 
    */
    private String domainModelId;
    private String domainModelName;


    /**
    * 展现排序
    * 
    */
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
    public String getMemo(){
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getModuleId(){
        return moduleId;
    }
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
    public String getModuleName(){
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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