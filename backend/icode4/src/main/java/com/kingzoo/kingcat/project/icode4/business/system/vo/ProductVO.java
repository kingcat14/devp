package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* Product的值对象
*/
public class ProductVO {


    private String id;


    /**
    * 产品名称
    * 
    */
    private String productName;


    /**
    * 产品代码
    * 
    */
    private String productCode;


    /**
    * 产品说明
    * 
    */
    private String description;


    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductCode(){
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
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