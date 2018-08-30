/**
 * 作者: 林森
 * 日期: 2017年4月17日	
 * CopyRight @lins	
 */
package com.kingzoo.kingcat.project.icode4.business.database.service;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 项目名称：代码生成器 类描述： 创建人：林森 创建时间：2017年4月17日 修改人： 修改时间： 修改备注：
 * 
 * @version
 * 
 */
public class GenJavaBean {

	private static final String DB_NAME = "ykrccl_yilu_dev";
	private static final int index = DB_NAME.indexOf("_");
	private String url = "jdbc:mysql://202.116.104.24:3306/" + DB_NAME;
	private String username = "devdict";
	private String password = "dev@dict";
	private String driverClass = "com.mysql.jdbc.Driver";
	private DatabaseMetaData dbMetaData = null;
	private static String packageName = "com.yunkanghealth.rccl.entity;";
	private String path = "./file/";

	private static String strPkg =
			"package " + packageName +"\n"+
			"import java.util.Date;\n"+
			"import com.yunkanghealth.yktechcom.tools.EntityInfo;\n"+
			"import com.yunkanghealth.yktechcom.tools.PropertyInfo;\n"+
			"import com.yunkanghealth.ykframework.model.entity.BaseEntity;" + "\n";
	private static String strClass = 
			 "public class %1$s extends BaseEntity{\n\n" + 
			"\tprivate static final long serialVersionUID = 1L;\n\n";

//	private static String strField = "%5$s \n  " +
//	"\t@PropertyInfo(caption = \"%3$s\", fieldName=\"%6$s\")\n"
//			+ "\tprivate %4$s %1$s; \n" ;
	
	private static String strField = "\t@PropertyInfo(caption = \"%3$s\", fieldName=\"%5$s\")\n"
					+ "\tprivate %4$s %1$s; \n" ;

	/**
	 * @Description: 获取表对应的所有列
	 *
	 * @param tableName
	 * @return: void
	 */
	public void getTableColumns(TableInfo tableInfo) {
		try {
			String tableName = tableInfo.tableName;
			String ACCESS_DOMAIN = "private";
			ResultSet resultSet = dbMetaData.getColumns(null, null, tableName, "%");
			while (resultSet.next()) {
				ResultSet resultSetColumn = dbMetaData.getColumns(null, null, tableName, null);
				String className = getFormatString(tableName, true);
				className = className.substring(index);
				StringBuffer header = new StringBuffer("package " + packageName + "\n\n");
				StringBuffer footer = new StringBuffer();
				StringBuffer contentBuffer = new StringBuffer();
				StringBuffer temp = new StringBuffer();
				// contentBuffer.append("public class " + className + "{\n");
				contentBuffer.append(strPkg);
				contentBuffer.append("\n\n");
				contentBuffer.append(String.format(
						"@EntityInfo(caption = \"%s\", name = \"%s\")\n", tableInfo.tableCaption,
						tableInfo.tableName));
				contentBuffer.append(String.format(strClass, className + "DO"));
				while (resultSetColumn.next()) {
					// contentBuffer.append("\t"+ACCESS_DOMAIN + " ");
					String columnType = resultSetColumn.getString("TYPE_NAME");
					String is_nullable = resultSetColumn.getString("is_nullable");
					String COLUMN_TYPE = getColumnType(columnType);
					if ("Date".equals(COLUMN_TYPE)) {
						header.append("import java.util.Date;\n");
					}
					// contentBuffer.append(COLUMN_TYPE + " ");
					String dbColumnName = resultSetColumn.getString("COLUMN_NAME");
					String _remark = resultSetColumn.getString("REMARKS");
					
					String columnName = this.getFormatString(dbColumnName, false);
					// footer.append(getSetGenerater(columnName, COLUMN_TYPE));
					// contentBuffer.append(columnName+";\t//"+remark+"\n");
					String logRemark = this.getLogRemark(_remark);
					String remark = this.getRemark(_remark);
					// if(remark.contains("]")){
					// logRemark= remark.split("]")[0].trim()+"]";
					// }
					if (!is_nullable.toString().equalsIgnoreCase("YES")) {
						// logRemark += ",checkNull=true";
					}
					contentBuffer.append(String.format(strField,  columnName, columnName, logRemark,
							COLUMN_TYPE, dbColumnName) + "\n");
//					contentBuffer.append(String.format("\t@PropertyInfo(caption = \"%1$s\", fieldName=\"%2$s\")\n",
//							logRemark, dbColumnName							) );
					
					temp.append(this.getSetGenerater(columnName, COLUMN_TYPE));
				}
				contentBuffer.append(temp);
				// contentBuffer.append("\n\n"+footer);
				contentBuffer.append("}");
				// header.append("\n");
				// header.append(contentBuffer);
				this.outputToFile(className + "DO.java", contentBuffer.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public GenJavaBean() {
		try {
			Class.forName(driverClass);
			Connection conn = DriverManager.getConnection(url, username, password);
			dbMetaData = conn.getMetaData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * @param str
	 * @return
	 * 在所有行前增加//
	 */
	private String getRemark(String str){
		String[] lstStr = str.split("\n");
		if(lstStr.length == 1) return "\t//" +str;
		StringBuilder sb = new StringBuilder();
		for(String s:lstStr){
			if(s.trim().equals("")) continue;
			s = "\t//" + s + "\n";
			sb.append(s);
		}
		return sb.toString();
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
			return str.substring(iStart, iEnd+1);
		}
		return str.split("\n")[0];
	}
	
	static class TableInfo{
		TableInfo(String tName,String caption){
			tableName = tName;
			tableCaption = caption;
		}
		public String tableName;
		public String tableCaption;
		
	}
	
	/**
	 * @Description: 获取所以的表
	 * @return: void
	 */
	public List<TableInfo> getAllTableList() {
		List<TableInfo> tableList = new ArrayList<TableInfo>();
		try {
			String[] types = { "TABLE" };
			ResultSet rs = dbMetaData.getTables(null, null, "%", types);
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME"); // 表名
				tableList.add(new TableInfo(tableName,rs.getString("REMARKS")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableList;
	}

	/**
	 * 处理字符串，去掉下划线“_”，并且把下划线的下一个字符变大写，flag为true，表示首字母要大写
	 * 
	 * @param name
	 * @param flag
	 * @return
	 */
	private String getFormatString(String name, boolean flag) {
		name = name.toLowerCase();
		String[] nameTemp = name.split("_");
		StringBuffer buffer = new StringBuffer();
		for (String str : nameTemp) {
			String head = str.substring(0, 1).toUpperCase();
			String tail = str.substring(1);
			buffer.append(head + tail);
		}
		StringBuffer result = null;
		if (!flag) {
			result = new StringBuffer();
			String head = buffer.substring(0, 1).toLowerCase();
			String tail = buffer.substring(1);
			result.append(head + tail);
			return result.toString();
		}
		return buffer.toString();
	}
	
	public boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	
	public boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    boolean flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}

	/**
	 * 把String内容写到文件
	 * 
	 * @param fileName
	 * @param content
	 */
	private void outputToFile(String fileName, String content) {
		OutputStream os = null;
		try {
			File file = new File(path);    
			if(!file.exists() && !file.isDirectory()){       
			    file .mkdir();    
			} 
			os = new FileOutputStream(path + fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		byte[] b = content.getBytes();
		try {
			os.write(b);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 数据库类型
	 * 
	 * @param column
	 * @return
	 */
	private String getColumnType(String column) {
		String COLUMN_TYPE = null;
		if ("VARCHAR".equals(column)) {
			COLUMN_TYPE = "String";
		} else if ("BIGINT".equals(column)) {
			COLUMN_TYPE = "Long";
		} else if ("DATETIME".equals(column)) {
			COLUMN_TYPE = "Date";
		} else if ("INT".equals(column) || "INT UNSIGNED".equals(column)) {
			COLUMN_TYPE = "Integer";
		} else if ("BIGINT UNSIGNED".equals(column)) {
			COLUMN_TYPE = "Long";
		} else if ("TINYINT UNSIGNED".equals(column)) {
			COLUMN_TYPE = "Short";
		} else if ("DECIMAL".equals(column) || "FLOAT".equals(column) || "DOUBLE".equals(column)) {
			COLUMN_TYPE = "Double";
		} else if ("TEXT".equals(column) || "MEDIUMTEXT".equals(column) || "LONGTEXT".equals(column)) {
			COLUMN_TYPE = "String";
		} else if ("TIMESTAMP".equals(column) || "DATE".equals(column) || "DATETIME".equals(column)) {
			COLUMN_TYPE = "Date";
		} else if ("TINYINT".equals(column)) {
			COLUMN_TYPE = "Short";
		} else if ("DECIMAL UNSIGNED".equals(column)) {
			COLUMN_TYPE = "Double";
		} else if ("SMALLINT".equals(column)) {
			COLUMN_TYPE = "Short";
		} else if ("BIT".equals(column)) {
			COLUMN_TYPE = "Short";
		} else if ("CHAR".equals(column)) {
			COLUMN_TYPE = "String";
		} else if ("VARBINARY".equals(column)) {
			COLUMN_TYPE = "Byte";
		} else if ("BLOB".equals(column)) {
			COLUMN_TYPE = "Byte[]";
		}
		return COLUMN_TYPE;
	}

	private String getSetGenerater(String columnName, String columnType) {
		StringBuffer sb = new StringBuffer();
		sb.append("\tpublic " + columnType + " get" + columnName.substring(0, 1).toUpperCase()
				+ columnName.substring(1, columnName.length()) + "() {\n");
		sb.append("\t\treturn " + columnName + ";\n");
		sb.append("\t}\n\n");
		sb.append("\tpublic void set" + columnName.substring(0, 1).toUpperCase()
				+ columnName.substring(1, columnName.length()));
		sb.append("(" + columnType + " " + columnName + ") {\n");
		sb.append("\t\tthis." + columnName + " = " + columnName + ";\n");
		sb.append("\t}\n\n");
		return sb.toString();
	}

	public static void main(String[] agrs) throws IOException {
		GenJavaBean aa = new GenJavaBean();
		List<TableInfo> tableList = aa.getAllTableList();
		for (TableInfo tableName : tableList) {
			aa.getTableColumns(tableName);
		}

		// BufferedReader strin=new BufferedReader(new
		// InputStreamReader(System.in));
		// System.out.print("请输入表名：");
		// String str = strin.readLine();
		// aa.getTableColumns(str);
		// System.out.println("complete:" + aa.path);

	}

}
