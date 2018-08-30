package com.kingzoo.kingcat.project.icode4.business.microservice.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 微服务接口的值对象
*/
public class MicroServiceItfcVO {


    private String id;


    /**
    * 接口名称
    * 
    */
    private String name;


    /**
    * 接口代码
    * 对应到方法名
    */
    private String code;


    /**
    * 接口地址
    * 
    */
    private String url;


    /**
    * 接口方法
    * POST, PUT, GET......
    */
    private String method;


    /**
    * 所属微服务
    * 
    */
    private String microServiceId;
    private String microServiceName;


    /**
    * 接口描述
    * 
    */
    private String description;


    /**
    * 返回对象
    * 
    */
    private String responseId;
    private String responseName;


    /**
    * 请求对象
    * 
    */
    private String requestId;
    private String requestName;


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
    public String getUrl(){
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMethod(){
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getMicroServiceId(){
        return microServiceId;
    }
    public void setMicroServiceId(String microServiceId) {
        this.microServiceId = microServiceId;
    }
    public String getMicroServiceName(){
        return microServiceName;
    }
    public void setMicroServiceName(String microServiceName) {
        this.microServiceName = microServiceName;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getResponseId(){
        return responseId;
    }
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }
    public String getResponseName(){
        return responseName;
    }
    public void setResponseName(String responseName) {
        this.responseName = responseName;
    }
    public String getRequestId(){
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    public String getRequestName(){
        return requestName;
    }
    public void setRequestName(String requestName) {
        this.requestName = requestName;
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