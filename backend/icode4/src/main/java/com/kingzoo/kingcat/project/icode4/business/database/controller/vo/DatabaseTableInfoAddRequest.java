package com.kingzoo.kingcat.project.icode4.business.database.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Size;


/**
 * 数据库表信息
 * @author icode
 */
public class DatabaseTableInfoAddRequest {

	public static final String PROPERTY_CONNECTION_ID = "connectionId";
	public static final String PROPERTY_CONNECTION_URL = "connectionUrl";
	public static final String PROPERTY_MODULE_NAME = "moduleName";
	public static final String PROPERTY_TABLE_NAME = "tableName";
	public static final String PROPERTY_TABLE_DISPLAY_NAME = "tableDisplayName";
	public static final String PROPERTY_ENTITY_NAME = "entityName";
	public static final String PROPERTY_TABLE_DESC = "tableDesc";

    /**
	 * 链接ID
	 * 
     */
	@Size(max = 255, message = "链接ID超长，最多255个字符")
	private String connectionId;

    /**
	 * 链接url
	 * 
     */
	@Size(max = 255, message = "链接url超长，最多255个字符")
	private String connectionUrl;

    /**
	 * 模块名称
	 * 
     */
	@Size(max = 255, message = "模块名称超长，最多255个字符")
	private String moduleName;

    /**
	 * 表名称
	 * 
     */
	@Size(max = 255, message = "表名称超长，最多255个字符")
	private String tableName;

    /**
	 * 表展现名称
	 * 
     */
	@Size(max = 255, message = "表展现名称超长，最多255个字符")
	private String tableDisplayName;

    /**
	 * 实体名称
	 * 
     */
	@Size(max = 255, message = "实体名称超长，最多255个字符")
	private String entityName;

    /**
	 * 表描述
	 * 
     */
	@Size(max = 255, message = "表描述超长，最多255个字符")
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
