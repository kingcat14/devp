package com.kingzoo.kingcat.project.icode4.business.database.domain;

import java.util.List;

/**
 * @author gonghongrui on 2017/11/16.
 */
public class DbTableInfo {

	public DbTableInfo(String tName, String caption) {
		tableName = tName;
		tableCaption = caption;
	}

	private String tableName;
	private String tableCaption;
	private String tableDisplayName;

	private List<DbColumnInfo> dbColumnInfoList;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableCaption() {
		return tableCaption;
	}
	public void setTableCaption(String tableCaption) {
		this.tableCaption = tableCaption;
	}


	public String getTableDisplayName() {
		return tableDisplayName;
	}
	public void setTableDisplayName(String tableDisplayName) {
		this.tableDisplayName = tableDisplayName;
	}

	public List<DbColumnInfo> getDbColumnInfoList() {
		return dbColumnInfoList;
	}
	public void setDbColumnInfoList(List<DbColumnInfo> dbColumnInfoList) {
		this.dbColumnInfoList = dbColumnInfoList;
	}
}
