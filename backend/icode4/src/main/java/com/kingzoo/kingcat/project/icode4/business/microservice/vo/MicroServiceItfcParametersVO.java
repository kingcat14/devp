package com.kingzoo.kingcat.project.icode4.business.microservice.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 微服务接口参数的值对象
*/
public class MicroServiceItfcParametersVO {


    private String id;


    /**
    * 排序
    * 
    */
    private Integer viewIndex;


    /**
    * 参数名称
    * 
    */
    private String name;


    /**
    * 代码
    * 
    */
    private String code;


    /**
    * 参数类型
    * 
    */
    private String type;


    /**
    * 路径映射
    * 
    */
    private String pathMapping;


    /**
    * 备注
    * 
    */
    private String memo;


    /**
    * 所属微服务接口
    * 
    */
    private String microServiceItfcId;
    private String microServiceItfcName;


    /**
    * 必填
    * 
    */
    private Boolean required;


    public Integer getViewIndex(){
        return viewIndex;
    }
    public void setViewIndex(Integer viewIndex) {
        this.viewIndex = viewIndex;
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
    public String getPathMapping(){
        return pathMapping;
    }
    public void setPathMapping(String pathMapping) {
        this.pathMapping = pathMapping;
    }
    public String getMemo(){
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getMicroServiceItfcId(){
        return microServiceItfcId;
    }
    public void setMicroServiceItfcId(String microServiceItfcId) {
        this.microServiceItfcId = microServiceItfcId;
    }
    public String getMicroServiceItfcName(){
        return microServiceItfcName;
    }
    public void setMicroServiceItfcName(String microServiceItfcName) {
        this.microServiceItfcName = microServiceItfcName;
    }
    public Boolean getRequired(){
        return required;
    }
    public void setRequired(Boolean required) {
        this.required = required;
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