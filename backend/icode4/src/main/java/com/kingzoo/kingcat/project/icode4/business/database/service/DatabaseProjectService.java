package com.kingzoo.kingcat.project.icode4.business.database.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.database.dao.DatabaseProjectDao;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseProject;
import com.kingzoo.kingcat.project.icode4.business.database.vo.DatabaseProjectCondition;
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

import java.util.List;


@Service("databaseProjectService")
public class DatabaseProjectService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseProject.class);

	@Autowired
	@Qualifier("databaseProjectDao")
	private DatabaseProjectDao databaseProjectDao;

	@Transactional
	public void add(DatabaseProject databaseProject){
		databaseProject.setId(IDGenerator.get());
		databaseProjectDao.insert(databaseProject);
	}

	@Transactional
	public void add(List<DatabaseProject> databaseProjectList){
		for(DatabaseProject databaseProject:databaseProjectList){
			this.add(databaseProject);
		}
	}

	@Transactional
	public void delete(DatabaseProject databaseProject){
	    LOGGER.debug("delete databaseProject:{}", databaseProject);
		databaseProjectDao.delete(databaseProject.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete DatabaseProject by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete databaseProject:{}", id);
		databaseProjectDao.delete(id);
	}

	@Transactional
	public void delete(List<DatabaseProject> databaseProjects){
		for(DatabaseProject databaseProject: databaseProjects){
			delete(databaseProject.getId());
		}
	}

	@Transactional
	public void merge(DatabaseProject databaseProject){
		databaseProjectDao.save(databaseProject);
	}

	@Transactional
	public void merge(List<DatabaseProject> databaseProjectList){
		for(DatabaseProject databaseProject:databaseProjectList){
			this.merge(databaseProject);
		}
	}

	@Transactional(readOnly=true)
	public DatabaseProject find(String id){
	    DatabaseProject databaseProject = null;
		if(StringUtils.isNotEmpty(id)) {
            databaseProject = databaseProjectDao.findOne(id);
        }

		return databaseProject;
	}

	@Transactional(readOnly=true)
	public DatabaseProject findOne(DatabaseProjectCondition databaseProjectCondition){

		List<DatabaseProject> databaseProjectList = databaseProjectDao.findAll(databaseProjectCondition);

		DatabaseProject result = null;
		if(CollectionUtils.isNotEmpty(databaseProjectList)){
			result = databaseProjectList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<DatabaseProject> findAll(DatabaseProjectCondition databaseProjectCondition){

		List<DatabaseProject> databaseProjectList = databaseProjectDao.findAll(databaseProjectCondition, getDefaultSort());

		return databaseProjectList;
	}

	@Transactional(readOnly=true)
	public Page<DatabaseProject> find(Pageable pageable){
		Page<DatabaseProject> databaseProjectList = databaseProjectDao.findAll(pageable, null, getDefaultSort());
		return databaseProjectList;
	}

	@Transactional(readOnly=true)
	public Page<DatabaseProject> find(Pageable pageable, DatabaseProjectCondition databaseProjectCondition){
		Page<DatabaseProject> databaseProjectList = databaseProjectDao.findAll(pageable, databaseProjectCondition, getDefaultSort());
		return databaseProjectList;
	}

	@Transactional(readOnly=true)
	public long count(DatabaseProjectCondition databaseProjectCondition){
		long count = databaseProjectDao.count(databaseProjectCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DatabaseProject.PROPERTY_NAME);
		return sort;
	}





}