package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 系统的值对象
*/
public class SystemVO {


    private String id;

    private String productId;

    /**
    * 系统名称
    * 
    */
    private String name;


    /**
    * 系统代码
    * 
    */
    private String code;


    /**
    * 描述
    * 
    */
    private String description;


    /**
    * 基础包名称
    * 
    */
    private String basePackage;


    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
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