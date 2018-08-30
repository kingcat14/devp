package com.kingzoo.kingcat.project.icode4.business.system.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.system.dao.SystemDao;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.system.vo.SystemCondition;
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


@Service("systemService")
public class SystemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(System.class);

	@Autowired
	@Qualifier("systemDao")
	private SystemDao systemDao;

	@Transactional
	public void add(System system){
		system.setId(IDGenerator.get());
		systemDao.insert(system);
	}

	@Transactional
	public void add(List<System> systemList){
		for(System system:systemList){
			this.add(system);
		}
	}

	@Transactional
	public void delete(System system){
	    LOGGER.debug("delete system:{}", system);
		systemDao.delete(system.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete System by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete system:{}", id);
		systemDao.delete(id);
	}

	@Transactional
	public void delete(List<System> systems){
		for(System system: systems){
			delete(system.getId());
		}
	}

	@Transactional
	public void merge(System system){
		systemDao.save(system);
	}

	@Transactional
	public void merge(List<System> systemList){
		for(System system:systemList){
			this.merge(system);
		}
	}

	@Transactional(readOnly=true)
	public System find(String id){
	    System system = null;
		if(StringUtils.isNotEmpty(id)) {
            system = systemDao.findOne(id);
        }

		return system;
	}

	@Transactional(readOnly=true)
	public System findOne(SystemCondition systemCondition){

		List<System> systemList = systemDao.findAll(systemCondition);

		System result = null;
		if(CollectionUtils.isNotEmpty(systemList)){
			result = systemList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<System> findAll(SystemCondition systemCondition){

		List<System> systemList = systemDao.findAll(systemCondition, getDefaultSort());

		return systemList;
	}

	@Transactional(readOnly=true)
	public Page<System> find(Pageable pageable){
		Page<System> systemList = systemDao.findAll(pageable, null, getDefaultSort());
		return systemList;
	}

	@Transactional(readOnly=true)
	public Page<System> find(Pageable pageable, SystemCondition systemCondition){
		Page<System> systemList = systemDao.findAll(pageable, systemCondition, getDefaultSort());
		return systemList;
	}

	@Transactional(readOnly=true)
	public long count(SystemCondition systemCondition){
		long count = systemDao.count(systemCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , System.PROPERTY_NAME);
		return sort;
	}





}