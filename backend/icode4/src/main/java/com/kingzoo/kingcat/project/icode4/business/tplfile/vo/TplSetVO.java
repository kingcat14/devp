package com.kingzoo.kingcat.project.icode4.business.tplfile.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 模板集合的值对象
*/
public class TplSetVO {


    private String id;


    /**
    * 集合名称
    * 
    */
    private String name;


    /**
    * 集合代码
    * 
    */
    private String code;


    /**
    * 集合类型
    * 
    */
    private String type;


    /**
    * 上级集合
    * 
    */
    private String parentId;
    private String parentName;


    /**
    * 描述
    * 
    */
    private String description;


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
    public String getParentId(){
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getParentName(){
        return parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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