package com.kingzoo.kingcat.project.icode4.business.codegen.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 工程的值对象
*/
public class ProjectVO {


    private String id;


    /**
    * 组名
    * 
    */
    private String groupCode;


    /**
    * 名称
    * 
    */
    private String name;


    /**
    * 代码
    * 
    */
    private String code;


    /**
    * 编号
    * 
    */
    private Integer number;


    /**
    * 描述
    * 
    */
    private String description;


    /**
    * 基础包
    * 
    */
    private String basePackage;


    /**
    * 代码模板
    *
    */
    private String tplSetId;
    private String tplSetName;

    /**
    * 工程目录
    * 
    */
    private String projectPath;




    public String getGroupCode(){
        return groupCode;
    }
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getBasePackage(){
        return basePackage;
    }
    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTplSetId(){
        return tplSetId;
    }
    public void setTplSetId(String tplSetId) {
        this.tplSetId = tplSetId;
    }
    public String getProjectPath(){
        return projectPath;
    }
    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getTplSetName() {
        return tplSetName;
    }
    public void setTplSetName(String tplSetName) {
        this.tplSetName = tplSetName;
    }

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
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