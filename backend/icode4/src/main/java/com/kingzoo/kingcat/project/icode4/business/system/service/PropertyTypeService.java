package com.kingzoo.kingcat.project.icode4.business.system.service;

import com.kingzoo.kingcat.project.icode4.business.system.dao.PropertyTypeDao;
import com.kingzoo.kingcat.project.icode4.business.system.domain.PropertyType;
import com.kingzoo.kingcat.project.icode4.business.system.vo.PropertyTypeCondition;
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


@Service("propertyTypeService")
public class PropertyTypeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyType.class);

	@Autowired
	@Qualifier("propertyTypeDao")
	private PropertyTypeDao propertyTypeDao;

	@Transactional
	public void add(PropertyType propertyType){
		propertyType.setId(propertyType.getCode());
		propertyTypeDao.insert(propertyType);
	}

	@Transactional
	public void add(List<PropertyType> propertyTypeList){
		for(PropertyType propertyType:propertyTypeList){
			this.add(propertyType);
		}
	}

	@Transactional
	public void delete(PropertyType propertyType){
	    LOGGER.debug("delete propertyType:{}", propertyType);
		propertyTypeDao.delete(propertyType.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete PropertyType by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete propertyType:{}", id);
		propertyTypeDao.delete(id);
	}

	@Transactional
	public void delete(List<PropertyType> propertyTypes){
		for(PropertyType propertyType: propertyTypes){
			delete(propertyType.getId());
		}
	}

	@Transactional
	public void merge(PropertyType propertyType){
		propertyTypeDao.save(propertyType);
	}

	@Transactional
	public void merge(List<PropertyType> propertyTypeList){
		for(PropertyType propertyType:propertyTypeList){
			this.merge(propertyType);
		}
	}


	public boolean exist(String code){
		if(StringUtils.isEmpty(code)){
			return false;
		}
		PropertyTypeCondition condition = new PropertyTypeCondition();
		condition.setCode(code);
		long count = propertyTypeDao.count(condition);
		return count>0;
	}

	@Transactional(readOnly=true)
	public PropertyType find(String id){
	    PropertyType propertyType = null;
		if(StringUtils.isNotEmpty(id)) {
            propertyType = propertyTypeDao.findOne(id);
        }

		return propertyType;
	}

	@Transactional(readOnly=true)
	public PropertyType findOne(PropertyTypeCondition propertyTypeCondition){

		List<PropertyType> propertyTypeList = propertyTypeDao.findAll(propertyTypeCondition);

		PropertyType result = null;
		if(CollectionUtils.isNotEmpty(propertyTypeList)){
			result = propertyTypeList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<PropertyType> findAll(PropertyTypeCondition propertyTypeCondition){

		List<PropertyType> propertyTypeList = propertyTypeDao.findAll(propertyTypeCondition, getDefaultSort());

		return propertyTypeList;
	}

	@Transactional(readOnly=true)
	public Page<PropertyType> find(Pageable pageable){
		Page<PropertyType> propertyTypeList = propertyTypeDao.findAll(pageable, null, getDefaultSort());
		return propertyTypeList;
	}

	@Transactional(readOnly=true)
	public Page<PropertyType> find(Pageable pageable, PropertyTypeCondition propertyTypeCondition){
		Page<PropertyType> propertyTypeList = propertyTypeDao.findAll(pageable, propertyTypeCondition, getDefaultSort());
		return propertyTypeList;
	}

	@Transactional(readOnly=true)
	public long count(PropertyTypeCondition propertyTypeCondition){
		long count = propertyTypeDao.count(propertyTypeCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC , PropertyType.PROPERTY_VIEW_INDEX);
		return sort;
	}





}