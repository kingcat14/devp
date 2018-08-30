package com.kingzoo.kingcat.project.icode4.business.tplfile.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 文件模板的值对象
*/
public class TplCodeVO {


    private String id;


    /**
    * 模板名称
    * 
    */
    private String name;


    /**
    * 模板代码
    * 
    */
    private String code;


    /**
    * 模板类型
    * CODE,PROJECT
    */
    private String type;


    /**
    * 接收模型类型
    * 
    */
    private String acceptModelTypeId;
    private String acceptModelTypeName;


    /**
    * 文件路径
    * 
    */
    private String filePath;


    /**
    * 文件名
    * 
    */
    private String fileName;


    /**
    * 所属集合
    * 
    */
    private String tplSetId;
    private String tplSetName;


    /**
    * 可覆盖
    * 
    */
    private Boolean overridable;


    /**
    * 文件内容
    * 
    */
    private String content;


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
    public String getAcceptModelTypeId(){
        return acceptModelTypeId;
    }
    public void setAcceptModelTypeId(String acceptModelTypeId) {
        this.acceptModelTypeId = acceptModelTypeId;
    }
    public String getAcceptModelTypeName(){
        return acceptModelTypeName;
    }
    public void setAcceptModelTypeName(String acceptModelTypeName) {
        this.acceptModelTypeName = acceptModelTypeName;
    }
    public String getFilePath(){
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFileName(){
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getTplSetId(){
        return tplSetId;
    }
    public void setTplSetId(String tplSetId) {
        this.tplSetId = tplSetId;
    }
    public String getTplSetName(){
        return tplSetName;
    }
    public void setTplSetName(String tplSetName) {
        this.tplSetName = tplSetName;
    }
    public Boolean getOverridable(){
        return overridable;
    }
    public void setOverridable(Boolean overridable) {
        this.overridable = overridable;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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