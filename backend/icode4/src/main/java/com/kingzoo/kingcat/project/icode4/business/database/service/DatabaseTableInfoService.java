package com.kingzoo.kingcat.project.icode4.business.database.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.database.dao.DatabaseTableInfoDao;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseTableInfo;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DbColumnInfo;
import com.kingzoo.kingcat.project.icode4.business.database.vo.DatabaseTableInfoCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service("databaseTableInfoService")
public class DatabaseTableInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseTableInfo.class);

	@Autowired
	@Qualifier("databaseTableInfoDao")
	private DatabaseTableInfoDao databaseTableInfoDao;


	@Autowired
	private TableLoadService tableLoadService;

	@Transactional
	public void add(DatabaseTableInfo databaseTableInfo){
		databaseTableInfo.setId(IDGenerator.get());
		databaseTableInfoDao.insert(databaseTableInfo);
	}

	@Transactional
	public void add(List<DatabaseTableInfo> databaseTableInfoList){
		for(DatabaseTableInfo databaseTableInfo:databaseTableInfoList){
			this.add(databaseTableInfo);
		}
	}

	@Transactional
	public void delete(DatabaseTableInfo databaseTableInfo){
	    LOGGER.debug("delete databaseTableInfo:{}", databaseTableInfo);
		databaseTableInfoDao.delete(databaseTableInfo.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete DatabaseTableInfo by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete databaseTableInfo:{}", id);
		databaseTableInfoDao.delete(id);
	}

	@Transactional
	public void delete(List<DatabaseTableInfo> databaseTableInfos){
		for(DatabaseTableInfo databaseTableInfo: databaseTableInfos){
			delete(databaseTableInfo.getId());
		}
	}

	@Transactional
	public void merge(DatabaseTableInfo databaseTableInfo){
		databaseTableInfoDao.save(databaseTableInfo);
	}

	@Transactional
	public void merge(List<DatabaseTableInfo> databaseTableInfoList){
		for(DatabaseTableInfo databaseTableInfo:databaseTableInfoList){
			this.merge(databaseTableInfo);
		}
	}

	@Transactional(readOnly=true)
	public DatabaseTableInfo find(String id){
	    DatabaseTableInfo databaseTableInfo = null;
		if(StringUtils.isNotEmpty(id)) {
            databaseTableInfo = databaseTableInfoDao.findOne(id);
        }

		return databaseTableInfo;
	}

	@Transactional(readOnly=true)
	public DatabaseTableInfo findOne(DatabaseTableInfoCondition databaseTableInfoCondition){

		List<DatabaseTableInfo> databaseTableInfoList = databaseTableInfoDao.findAll(databaseTableInfoCondition);

		DatabaseTableInfo result = null;
		if(CollectionUtils.isNotEmpty(databaseTableInfoList)){
			result = databaseTableInfoList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<DatabaseTableInfo> findAll(DatabaseTableInfoCondition databaseTableInfoCondition){

		List<DatabaseTableInfo> databaseTableInfoList = databaseTableInfoDao.findAll(databaseTableInfoCondition, getDefaultSort());

		return databaseTableInfoList;
	}

	@Transactional(readOnly=true)
	public Page<DatabaseTableInfo> find(Pageable pageable){
		Page<DatabaseTableInfo> databaseTableInfoList = databaseTableInfoDao.findAll(pageable, null, getDefaultSort());
		return databaseTableInfoList;
	}

	@Transactional(readOnly=true)
	public Page<DatabaseTableInfo> find(Pageable pageable, DatabaseTableInfoCondition databaseTableInfoCondition){
		Page<DatabaseTableInfo> databaseTableInfoList = databaseTableInfoDao.findAll(pageable, databaseTableInfoCondition, getDefaultSort());
		return databaseTableInfoList;
	}

	@Transactional(readOnly=true)
	public long count(DatabaseTableInfoCondition databaseTableInfoCondition){
		long count = databaseTableInfoDao.count(databaseTableInfoCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DatabaseTableInfo.PROPERTY_CONNECTION_ID);
		return sort;
	}


	public List<DatabaseTableInfo> findTableInfoList(String connectionId){

		//从数据库获取到所有的表
		List<DatabaseTableInfo> dbTableInfoList = tableLoadService.getTableList(connectionId);
		Map<String, DatabaseTableInfo> dbTableInfoMap = new HashMap<>();
		for(DatabaseTableInfo tableInfo : dbTableInfoList){
			tableInfo.setId(connectionId+"_"+tableInfo.getTableName());
			tableInfo.setConnectionId(connectionId);
			dbTableInfoMap.put(tableInfo.getId(), tableInfo);
		}

		//2.从本地获取该库的配置
		DatabaseTableInfoCondition tableInfoCondition = new DatabaseTableInfoCondition();
		tableInfoCondition.setConnectionId(connectionId);
		List<DatabaseTableInfo> resultTableInfoList = this.findAll(tableInfoCondition);

		Map<String, DatabaseTableInfo> resultTableInfoMap = new HashMap<>();

		Iterator<DatabaseTableInfo> iterator = resultTableInfoList.iterator();
		while(iterator.hasNext()){
			DatabaseTableInfo tableInfo = iterator.next();
			//本地存在, 但是数据库中已删掉的数据, 全删掉
			if(!dbTableInfoMap.containsKey(tableInfo.getId())){
				iterator.remove();
				this.delete(tableInfo.getId());
			}
			//剩下的数据放入map
			resultTableInfoMap.put(tableInfo.getId(), tableInfo);
		}


		//如果数据库中有数据，在本地是没有的，则放入结果队列
		for(DatabaseTableInfo tableInfo : dbTableInfoList){
			if(tableInfo.getTableName().equals("ykrccl_cash_apply")){
				LOGGER.info("aaa");
			}

			if(!resultTableInfoMap.containsKey(tableInfo.getId())){
				resultTableInfoList.add(tableInfo);
			}
		}
		return resultTableInfoList;
	}


	public Map<String, List<DbColumnInfo>> findColumnInfo(List<String> tableNameList, String connectionId){
		return tableLoadService.getDbColumnInfoList(tableNameList, connectionId);
	}

}