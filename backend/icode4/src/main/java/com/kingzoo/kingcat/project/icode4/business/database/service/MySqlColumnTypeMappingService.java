package com.kingzoo.kingcat.project.icode4.business.database.service;

import com.kingzoo.kingcat.project.icode4.common.constant.PropertyTypeConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @author gonghongrui on 2017/11/18.
 */
@Service
public class MySqlColumnTypeMappingService {

	private HashMap<String, String> typeMap = new HashMap<>();

	@PostConstruct
	public void init(){

		typeMap.put("VARCHAR", PropertyTypeConstants.GENERAL_TYPE_STRING);
		typeMap.put("BIGINT", PropertyTypeConstants.GENERAL_TYPE_LONG);
		typeMap.put("DATETIME", PropertyTypeConstants.GENERAL_TYPE_DATETIME);
		typeMap.put("INT", PropertyTypeConstants.GENERAL_TYPE_INTEGER);
		typeMap.put("MEDIUMINT", PropertyTypeConstants.GENERAL_TYPE_INTEGER);
		typeMap.put("UNSIGNED", PropertyTypeConstants.GENERAL_TYPE_INTEGER);
		typeMap.put("BIGINT UNSIGNED", PropertyTypeConstants.GENERAL_TYPE_LONG);
		typeMap.put("TINYINT UNSIGNED", PropertyTypeConstants.GENERAL_TYPE_INTEGER);
		typeMap.put("DECIMAL", PropertyTypeConstants.GENERAL_TYPE_DOUBLE);
		typeMap.put("DOUBLE", PropertyTypeConstants.GENERAL_TYPE_DOUBLE);
		typeMap.put("TEXT", PropertyTypeConstants.GENERAL_TYPE_STRING);
		typeMap.put("MEDIUMTEXT", PropertyTypeConstants.GENERAL_TYPE_STRING);
		typeMap.put("LONGTEXT", PropertyTypeConstants.GENERAL_TYPE_STRING);
		typeMap.put("TIMESTAMP", PropertyTypeConstants.GENERAL_TYPE_DATETIME);
		typeMap.put("DATETIME", PropertyTypeConstants.GENERAL_TYPE_DATETIME);
		typeMap.put("DATE", PropertyTypeConstants.GENERAL_TYPE_DATE);
		typeMap.put("TINYINT", PropertyTypeConstants.GENERAL_TYPE_INTEGER);
		typeMap.put("DECIMAL UNSIGNED", PropertyTypeConstants.GENERAL_TYPE_DOUBLE);
		typeMap.put("SMALLINT", PropertyTypeConstants.GENERAL_TYPE_INTEGER);
		typeMap.put("BIT", PropertyTypeConstants.GENERAL_TYPE_INTEGER);
		typeMap.put("CHAR", PropertyTypeConstants.GENERAL_TYPE_STRING);
		typeMap.put("VARBINARY", PropertyTypeConstants.GENERAL_TYPE_STRING);
		typeMap.put("BLOB", PropertyTypeConstants.GENERAL_TYPE_TEXT);

	}

	public String getPlatformPropertyType(String dbType){
		String result = PropertyTypeConstants.GENERAL_TYPE_STRING;
		if(typeMap.containsKey(dbType)){
			result = typeMap.get(dbType);
		}
		return result;
	}


	/**
	 * 将列名转换成属性名
	 * @param name
	 * @return
	 */
	public String getPropertyNameFromColumn(String name) {
		name = name.toLowerCase();
		String[] nameTemp = name.split("_");
		StringBuffer buffer = new StringBuffer();
		for (String str : nameTemp) {
			String head = str.substring(0, 1).toUpperCase();
			String tail = str.substring(1);
			buffer.append(head + tail);
		}
		return StringUtils.uncapitalize(buffer.toString());
	}
}
