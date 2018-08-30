package com.kingzoo.kingcat.project.icode4.business.database.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 数据库表信息的值对象
*/
public class DatabaseTableInfoVO {


    private String id;


    /**
    * 链接ID
    * 
    */
    private String connectionId;


    /**
    * 链接url
    * 
    */
    private String connectionUrl;


    /**
    * 模块名称
    * 
    */
    private String moduleName;


    /**
    * 表名称
    * 
    */
    private String tableName;


    /**
    * 表展现名称
    * 
    */
    private String tableDisplayName;


    /**
    * 实体名称
    * 
    */
    private String entityName;


    /**
    * 表描述
    * 
    */
    private String tableDesc;


    public String getConnectionId(){
        return connectionId;
    }
    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }
    public String getConnectionUrl(){
        return connectionUrl;
    }
    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
    public String getModuleName(){
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public String getTableName(){
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getTableDisplayName(){
        return tableDisplayName;
    }
    public void setTableDisplayName(String tableDisplayName) {
        this.tableDisplayName = tableDisplayName;
    }
    public String getEntityName(){
        return entityName;
    }
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    public String getTableDesc(){
        return tableDesc;
    }
    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
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