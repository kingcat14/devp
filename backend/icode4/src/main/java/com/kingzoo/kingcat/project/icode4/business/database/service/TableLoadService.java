/**
 * 作者: 林森
 * 日期: 2017年4月17日	
 * CopyRight @lins	
 */
package com.kingzoo.kingcat.project.icode4.business.database.service;

import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseProject;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseTableInfo;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DbColumnInfo;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DbTableInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;


/**
 * 
 * 加载数据库表
 *
 * 
 */
@Service
public class TableLoadService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TableLoadService.class);

	@Autowired
	private DatabaseProjectService databaseProjectService;


	public Connection getConnection(DatabaseProject databaseProject){

		Properties props =new Properties();
		props.put("user", databaseProject.getUsername());
		props.put("password", databaseProject.getPassword());
		props.put("useInformationSchema","true"); //表注释

		Connection conn = null;
		try {
			Class.forName(databaseProject.getDriverName());
			conn = DriverManager.getConnection(databaseProject.getUrl(), props);
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		return conn;
	}


	/**
	 * @Description: 获取所有的表
	 * @return: void
	 */
	public List<DbTableInfo> getAllDbTableInfoList(String connectionId) {

		DatabaseProject configuration = databaseProjectService.find(connectionId);
		Connection connection = this.getConnection(configuration);


		List<DbTableInfo> tableList = new ArrayList<>();
		if(connection == null){
			return tableList;
		}
		try {

			DatabaseMetaData dbMetaData = connection.getMetaData();

			String[] types = { "TABLE" };
			ResultSet rs = dbMetaData.getTables(null, null, "%", types);
			while (rs.next()) {

				//得到表名
				String tableName = rs.getString("TABLE_NAME"); // 表名
				//得到表描述
				String caption = rs.getString("REMARKS");
				DbTableInfo dbTableInfo = new DbTableInfo(tableName, caption);

				//表描述不为空的话，从中间获取表名
				if(StringUtils.isNotEmpty(caption)){
					String tableDisplayName = caption.split("—")[0].trim().replaceAll("\\[", "").replaceAll("\\]", "");
					int lastWordIndex = StringUtils.lastIndexOf(tableDisplayName,"表");
					if(lastWordIndex == StringUtils.length(tableDisplayName) - 1){
						tableDisplayName = StringUtils.substring(tableDisplayName, 0, lastWordIndex);
					}
					dbTableInfo.setTableDisplayName(tableDisplayName);
				}

				tableList.add(dbTableInfo);
			}
		} catch (SQLException e) {
			LOGGER.error("", e);
		}finally {

			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage(), e);
			}

		}

		return tableList;
	}


	/**
	 * 得到一组表的列信息
	 * @param tableNameList
	 * @param connectionId
	 * @return
	 */
	public Map<String, List<DbColumnInfo>> getDbColumnInfoList(List<String> tableNameList, String connectionId){
		HashMap<String, List<DbColumnInfo>> result = new HashMap<>();

		DatabaseProject databaseProject = databaseProjectService.find(connectionId);
		Connection connection = this.getConnection(databaseProject);

		try {
			DatabaseMetaData dbMetaData = connection.getMetaData();
			for(String tableName : tableNameList){

				result.put(tableName, this.getDbColumnInfoList(tableName, dbMetaData));

			}
		}
		catch (SQLException e) {
			LOGGER.error("", e);
		}finally {

			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage(), e);
			}

		}

		return result;
	}


	/**
	 * 得到给定表的列信息
	 * @param tableName
	 * @param dbMetaData
	 * @return
	 */
	private List<DbColumnInfo> getDbColumnInfoList(String tableName, DatabaseMetaData dbMetaData) throws SQLException {

		//结果集
		List<DbColumnInfo> result = new ArrayList<>();
		Set<String> primaryKeyNames = new HashSet<>();
		ResultSet primaryKeys = dbMetaData.getPrimaryKeys(null, null, tableName);
		while (primaryKeys.next()) {
			String dbColumnName = primaryKeys.getString("COLUMN_NAME");
			primaryKeyNames.add(dbColumnName);
		}

		ResultSet resultSetColumn = dbMetaData.getColumns(null, null, tableName, "%");
		//ResultSet rs = dbMetaData.getTables(null, null, "%", types);
		while (resultSetColumn.next()) {

			String columnType = resultSetColumn.getString("TYPE_NAME");

			String dbColumnName = resultSetColumn.getString("COLUMN_NAME");
			String remarks = resultSetColumn.getString("REMARKS");
			String displayName = this.getLogRemark(remarks);

			String tableName_ = resultSetColumn.getString("TABLE_NAME");  //表名
			String columnName = resultSetColumn.getString("COLUMN_NAME");  //列名
			int dataType = resultSetColumn.getInt("DATA_TYPE");     //对应的java.sql.Types的SQL类型(列类型ID)
			String dataTypeName = resultSetColumn.getString("TYPE_NAME");  //java.sql.Types类型名称(列类型名称)
			int columnSize = resultSetColumn.getInt("COLUMN_SIZE");  //列大小
			int decimalDigits = resultSetColumn.getInt("DECIMAL_DIGITS");  //小数位数
			int numPrecRadix = resultSetColumn.getInt("NUM_PREC_RADIX");  //基数（通常是10或2） --未知
			/**
			 *  0 (columnNoNulls) - 该列不允许为空
			 *  1 (columnNullable) - 该列允许为空
			 *  2 (columnNullableUnknown) - 不确定该列是否为空
			 */
			int nullAble = resultSetColumn.getInt("NULLABLE");  //是否允许为null

			String columnDef = resultSetColumn.getString("COLUMN_DEF");  //默认值
			int charOctetLength = resultSetColumn.getInt("CHAR_OCTET_LENGTH");    // 对于 char 类型，该长度是列中的最大字节数
			int ordinalPosition = resultSetColumn.getInt("ORDINAL_POSITION");   //表中列的索引（从1开始）
			/**
			 * ISO规则用来确定某一列的是否可为空(等同于NULLABLE的值:[ 0:'YES'; 1:'NO'; 2:''; ])
			 * YES -- 该列可以有空值;
			 * NO -- 该列不能为空;
			 * 空字符串--- 不知道该列是否可为空
			 */
			String isNullAble = resultSetColumn.getString("IS_NULLABLE");
			if(StringUtils.isEmpty(displayName)){
				displayName = dbColumnName;
			}
			if("create_uid".equals(dbColumnName)
//					||"create_uname".equals(dbColumnName)
//					||"create_ucode".equals(dbColumnName)
					||"create_at".equals(dbColumnName)
					||"modify_at".equals(dbColumnName)
					||"modify_uid".equals(dbColumnName)
//					||"modify_uname".equals(dbColumnName)
//					||"modify_ucode".equals(dbColumnName)

			){
				continue;
			}

			DbColumnInfo dbColumnInfo = new DbColumnInfo();
			result.add(dbColumnInfo);
			dbColumnInfo.setColumnName(dbColumnName);
			dbColumnInfo.setDisplayName(displayName);
			dbColumnInfo.setRemarks(remarks.replace("/*", "/ *").replace("*/", "* /"));
			dbColumnInfo.setNullable(BooleanUtils.toBoolean(nullAble));
			dbColumnInfo.setColumnType(columnType);
			dbColumnInfo.setPrimaryKey(primaryKeyNames.contains(dbColumnName));

		}


		return result;
	}

	public List<DatabaseTableInfo> getTableList(String databaseConfigurationId){

		List<DbTableInfo> tableList = this.getAllDbTableInfoList(databaseConfigurationId);

		List<DatabaseTableInfo> tableInfoList = new ArrayList<>(CollectionUtils.size(tableList));

		DatabaseTableInfo tableInfo ;
		for(DbTableInfo dbTableInfo : tableList){
			tableInfo = new DatabaseTableInfo();
			tableInfo.setTableName(dbTableInfo.getTableName());
			tableInfo.setTableDesc(dbTableInfo.getTableCaption());
			tableInfo.setTableDisplayName(dbTableInfo.getTableDisplayName());
			tableInfo.setEntityName(this.getEntityFromTableName(dbTableInfo.getTableName()));
			tableInfoList.add(tableInfo);
		}

		return tableInfoList;

	}


	/**
	 * 将表名转换成实体名
	 * @param name
	 * @return
	 */
	private String getEntityFromTableName(String name) {

		name = name.toLowerCase();
		if(name.startsWith("ykpp")){
			name = name.replaceFirst("ykpp", "");
		}
		if(name.startsWith("_")){
			name = name.replaceFirst("_", "");
		}

		String[] nameTemp = name.split("_");
		StringBuffer buffer = new StringBuffer();
		for (String str : nameTemp) {
			String head = str.substring(0, 1).toUpperCase();
			String tail = str.substring(1);
			buffer.append(head + tail);
		}
		return buffer.toString();
	}



	/**
	 * @param str
	 * @return
	 * 只取[]之间的数据,如果无则只要第一行数据
	 */
	private String getLogRemark(String str){
		int iStart = str.indexOf('[');
		int iEnd = str.indexOf(']');
		if(iStart>=0 && iEnd>0 && iEnd>iStart){
			return str.substring(iStart+1, iEnd);
		}
		return str.split("\n")[0];
	}




}
