package com.kingzoo.kingcat.project.icode4.business.database.service;


import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseProject;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseTableInfo;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabseProjectSyncTask;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DbColumnInfo;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelPropertyService;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModuleService;
import com.kingzoo.kingcat.project.icode4.business.system.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gonghongrui on 2017/11/22.
 */
@Service
public class DatabseProjectSyncService {

	@Autowired
	private TableLoadService tableLoadService;

	@Autowired
	private DatabaseTableInfoService tableInfoService;

	@Autowired
	private MySqlColumnTypeMappingService mySqlColumnTypeMappingService;

	@Autowired
	private DatabaseProjectService databaseProjectService;

	@Autowired
	private SystemService projectService;

	@Autowired
	private ModuleService domainService;

	@Autowired
	private DomainModelService rootModelService;

	@Autowired
	private DomainModelPropertyService propertyService;

	/**
	 * 将数据库对象同步至本地
	 * @param databseProjectSyncTask
	 */
	@Transactional
	public void sync(DatabseProjectSyncTask databseProjectSyncTask){
		/*
		* 1.捞出数据库和本地弄好的表（带模块信息）
		* */

		//1.得到传入的表名
		List<String> tableNames = databseProjectSyncTask.getTableNames();
		HashMap<String, String> targetTableNameMap = new HashMap<>();
		for(String tableName : tableNames){
			targetTableNameMap.put(tableName, tableName);
		}


		//2..从数据库获取到所有的表
		List<DatabaseTableInfo> dbTableInfoList = tableInfoService.findTableInfoList(databseProjectSyncTask.getConnectionId());

		//3.只保留选中的表的相关信息，其他过滤掉
		List<DatabaseTableInfo> targetTableInfoList = new ArrayList<>();
		HashMap<String, Module> targetDomainMap = new HashMap<>();
		for(DatabaseTableInfo tableInfo : dbTableInfoList){
			//如果传入的表名里包含这个, 则这个表示目标表
			if(targetTableNameMap.containsKey(tableInfo.getTableName())){
				//放入目标表队列
				targetTableInfoList.add(tableInfo);
				if(StringUtils.isEmpty(tableInfo.getModuleName())){
					tableInfo.setModuleName("defaultdomain");
				}
				//如果这个domain不存在, 则也放入
				if(!targetDomainMap.containsKey(tableInfo.getModuleName())){
					Module domain = new Module();
					domain.setName(tableInfo.getModuleName());
					domain.setCode(tableInfo.getModuleName());
					targetDomainMap.put(domain.getName(), domain);
				}
			}
		}

		System project = this.convertConnectionToProject(databseProjectSyncTask.getConnectionId());

		Map<String, DomainModel> rootModelHashMap = new HashMap<>();

		//得到一个表名和RootModel的Map
		for(DatabaseTableInfo tableInfo : targetTableInfoList){
			rootModelHashMap.put(tableInfo.getTableName(), this.convertTableInfoToRootModel(tableInfo));
		}


		//得到一个表名和property的Map
		Map<String, List<DomainModelProperty>> modelPropertyMap = this.fillPropertyConfigForModelConfigList(targetTableInfoList, databseProjectSyncTask.getConnectionId());


		//保存project
		projectService.add(project);
		for(Module domain : targetDomainMap.values()){
			//保存一个domain
			domain.setSystemId(project.getId());
			domainService.add(domain);
		}



		for(DatabaseTableInfo tableInfo : targetTableInfoList){

			DomainModel rootModel = rootModelHashMap.get(tableInfo.getTableName());
			rootModel.setModuleId(targetDomainMap.get(tableInfo.getModuleName()).getId()+"");
			rootModel.setSystemId(project.getId()+"");
			rootModel.setPersist(true);
//			rootModel.setCode("");
			rootModelService.add(rootModel);

			List<DomainModelProperty> propertyList = modelPropertyMap.get(tableInfo.getTableName());
			for(DomainModelProperty property : propertyList){
				property.setDomainModelId(rootModel.getId());
				propertyService.add(property);
			}
		}




	}



	/**
	 * 将链接配置转换为project对象
	 * @param connectionId
	 * @return
	 */
	private System convertConnectionToProject(String connectionId){
		DatabaseProject configuration = databaseProjectService.find(connectionId);

		System project = new System();
		project.setName(configuration.getUrl());
		project.setCode(configuration.getUrl());
		project.setBasePackage("");
		project.setProductId("-1");
		return project;
	}

	/**
	 * 将表转换成RootModel对象
	 * @param tableInfo
	 * @return
	 */
	private DomainModel convertTableInfoToRootModel(DatabaseTableInfo tableInfo){

		DomainModel rootModel = new DomainModel();
		rootModel.setCode(tableInfo.getEntityName());
		rootModel.setName(StringUtils.isEmpty(tableInfo.getTableDisplayName())?tableInfo.getEntityName():tableInfo.getTableDisplayName());
		rootModel.setDescription(tableInfo.getTableDesc());

		return rootModel;
	}


	private Map<String, List<DomainModelProperty>> fillPropertyConfigForModelConfigList(List<DatabaseTableInfo> tableInfoList, String connectionId){

		List<String> tableNames = new ArrayList<>();
		for(DatabaseTableInfo modelConfig : tableInfoList){
			tableNames.add(modelConfig.getTableName());
		}

		//得到每个表的列集合
		Map<String, List<DbColumnInfo>> dbColumnInfoHashMap = tableInfoService.findColumnInfo(tableNames, connectionId);

		//方法的返回结果集
		Map<String, List<DomainModelProperty>> result = new HashMap<>();


		List<DbColumnInfo> tempDbColumnInfoList;
		List<DomainModelProperty> tempPropertyConfigList;
		for(DatabaseTableInfo modelConfig : tableInfoList){
			tempDbColumnInfoList = dbColumnInfoHashMap.get(modelConfig.getTableName());
			tempPropertyConfigList = this.convertDbColumnInfoListToPropertyConfigList(tempDbColumnInfoList);

			result.put(modelConfig.getTableName(), tempPropertyConfigList);
		}
		return result;
	}

	private List<DomainModelProperty> convertDbColumnInfoListToPropertyConfigList(List<DbColumnInfo> dbColumnInfoList){
		List<DomainModelProperty> result = new ArrayList<>();

		DomainModelProperty tempPropertyConfig;
		for(int i = 0; i < dbColumnInfoList.size(); i++){
			DbColumnInfo dbColumnInfo = dbColumnInfoList.get(i);

			tempPropertyConfig = this.convertDbColumnInfoToPropertyConfig(dbColumnInfo);

			if(tempPropertyConfig != null ){
				tempPropertyConfig.setViewIndex(i+1);
				result.add(tempPropertyConfig);
			}
		}
		return result;
	}

	private DomainModelProperty convertDbColumnInfoToPropertyConfig(DbColumnInfo dbColumnInfo){

//		if("rid".equals(dbColumnInfo.getColumnName())){
//			return null;
//		}

		DomainModelProperty propertyConfig = new DomainModelProperty();

		propertyConfig.setName(dbColumnInfo.getDisplayName());
		propertyConfig.setEditable(true);
//		propertyConfig.setInSearch(true);
		propertyConfig.setPersist(true);
//		propertyConfig.setVisibility(true);
//		propertyConfig.setRequired(!dbColumnInfo.isNullable());
//		propertyConfig.setMemo(dbColumnInfo.getRemarks());
		propertyConfig.setNullable(dbColumnInfo.isNullable());
		propertyConfig.setCode(mySqlColumnTypeMappingService.getPropertyNameFromColumn(dbColumnInfo.getColumnName()));
		propertyConfig.setType(mySqlColumnTypeMappingService.getPlatformPropertyType(dbColumnInfo.getColumnType()));
		propertyConfig.setVisible(false);
		propertyConfig.setSearch(true);
		propertyConfig.setPrimaryKey(dbColumnInfo.isPrimaryKey());
		//propertyConfig.setDescription(dbColumnInfo.ge);
		propertyConfig.setMemo(dbColumnInfo.getRemarks());

		if("tid".equals(dbColumnInfo.getColumnName())
				||"c_uid".equals(dbColumnInfo.getColumnName())
				||"c_name".equals(dbColumnInfo.getColumnName())
				||"c_time".equals(dbColumnInfo.getColumnName())
				||"m_uid".equals(dbColumnInfo.getColumnName())
				||"m_name".equals(dbColumnInfo.getColumnName())
				||"m_time".equals(dbColumnInfo.getColumnName())
				){
//			propertyConfig.setInSearch(false);
			propertyConfig.setVisible(false);
		}
		return propertyConfig;
	}
}

