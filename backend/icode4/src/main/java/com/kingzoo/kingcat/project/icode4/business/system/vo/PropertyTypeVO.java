package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 属性类型的值对象
*/
public class PropertyTypeVO {


    private String id;


    /**
    * 展示顺序
    * 
    */
    private String viewIndex;


    /**
    * 代码
    * 
    */
    private String code;


    /**
    * 名称
    * 
    */
    private String name;


    public String getViewIndex(){
        return viewIndex;
    }
    public void setViewIndex(String viewIndex) {
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