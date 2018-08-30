package com.kingzoo.kingcat.project.icode4.business.microservice.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 传输对象接口映射的值对象
*/
public class TransModelItfcMappingVO {


    private String id;


    /**
    * 传输对象ID
    * 
    */
    private String transModelId;


    /**
    * 微服务接口ID
    * 
    */
    private String microServiceItfcId;


    /**
    * 关系类型
    * REQUEST,RESPONSE
    */
    private String relationType;


    public String getTransModelId(){
        return transModelId;
    }
    public void setTransModelId(String transModelId) {
        this.transModelId = transModelId;
    }
    public String getMicroServiceItfcId(){
        return microServiceItfcId;
    }
    public void setMicroServiceItfcId(String microServiceItfcId) {
        this.microServiceItfcId = microServiceItfcId;
    }
    public String getRelationType(){
        return relationType;
    }
    public void setRelationType(String relationType) {
        this.relationType = relationType;
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