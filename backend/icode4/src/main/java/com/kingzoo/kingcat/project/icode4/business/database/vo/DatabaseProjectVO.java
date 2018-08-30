package com.kingzoo.kingcat.project.icode4.business.database.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 数据库项目的值对象
*/
public class DatabaseProjectVO {


    private String id;


    /**
    * 项目名称
    * 
    */
    private String name;


    /**
    * 数据库链接
    * 
    */
    private String url;


    /**
    * 用户名
    * 
    */
    private String username;


    /**
    * 密码
    * 
    */
    private String password;


    /**
    * 驱动名词
    * 
    */
    private String driverName;


    /**
    * 可修改
    * 
    */
    private Boolean editable;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDriverName(){
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public Boolean getEditable(){
        return editable;
    }
    public void setEditable(Boolean editable) {
        this.editable = editable;
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