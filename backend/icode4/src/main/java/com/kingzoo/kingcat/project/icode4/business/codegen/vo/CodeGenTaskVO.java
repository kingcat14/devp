package com.kingzoo.kingcat.project.icode4.business.codegen.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 代码生成任务的值对象
*/
public class CodeGenTaskVO {


    private String id;


    /**
    * 模型ID
    * 
    */
    private String modelIds;


    /**
    * 模板ID
    * 
    */
    private String tplCodeIds;


    /**
    * 目标路径
    * 
    */
    private String destPath;

    /**执行结果*/
    private String status;


    public String getModelIds(){
        return modelIds;
    }
    public void setModelIds(String modelIds) {
        this.modelIds = modelIds;
    }
    public String getTplCodeIds(){
        return tplCodeIds;
    }
    public void setTplCodeIds(String tplCodeIds) {
        this.tplCodeIds = tplCodeIds;
    }
    public String getDestPath(){
        return destPath;
    }
    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}