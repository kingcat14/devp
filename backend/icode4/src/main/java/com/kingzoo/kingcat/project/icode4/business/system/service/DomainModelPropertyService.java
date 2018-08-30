package com.kingzoo.kingcat.project.icode4.business.system.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.system.dao.DomainModelPropertyDao;
import com.kingzoo.kingcat.project.icode4.business.system.dao.DomainModelViewPropertyDao;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelViewProperty;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelPropertyCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("domainModelPropertyService")
public class DomainModelPropertyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainModelProperty.class);


	@Autowired
	@Qualifier("domainModelPropertyDao")
	private DomainModelPropertyDao domainModelPropertyDao;

	@Autowired
	@Qualifier("domainModelViewPropertyDao")
	private DomainModelViewPropertyDao domainModelViewPropertyDao;

	@Autowired
	private DomainModelViewPropertyService domainModelViewPropertyService;

	@Transactional
	public void add(DomainModelProperty domainModelProperty){

		/*
		 * 1.新增属性
		 * 2.新增显示属性
		 */

		//1.新增属性
		domainModelProperty.setId(IDGenerator.get());
		domainModelPropertyDao.insert(domainModelProperty);

		//2.新增显示属性
		domainModelViewPropertyService.create(domainModelProperty);

	}

	@Transactional
	public void add(List<DomainModelProperty> domainModelPropertyList){
		for(DomainModelProperty domainModelProperty:domainModelPropertyList){
			this.add(domainModelProperty);
		}
	}

	@Transactional
	public void delete(DomainModelProperty domainModelProperty){
	    LOGGER.debug("delete domainModelProperty:{}", domainModelProperty);
		domainModelPropertyDao.delete(domainModelProperty.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete DomainModelProperty by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete domainModelProperty:{}", id);
		domainModelPropertyDao.delete(id);
		domainModelViewPropertyDao.delete(id);
	}

	@Transactional
	public void delete(List<DomainModelProperty> domainModelPropertys){
		for(DomainModelProperty domainModelProperty: domainModelPropertys){
			delete(domainModelProperty.getId());
		}
	}

	@Transactional
	public void merge(DomainModelProperty domainModelProperty){

		domainModelPropertyDao.save(domainModelProperty);

		DomainModelViewProperty viewProperty = domainModelViewPropertyDao.findOne(domainModelProperty.getId());
		viewProperty.setCode(domainModelProperty.getCode());
		viewProperty.setName(domainModelProperty.getName());
		viewProperty.setViewIndex(domainModelProperty.getViewIndex());
	}

	@Transactional
	public void merge(List<DomainModelProperty> domainModelPropertyList){
		for(DomainModelProperty domainModelProperty:domainModelPropertyList){
			this.merge(domainModelProperty);
		}
	}


	@Transactional(readOnly=true)
	public DomainModelProperty find(String id){
	    DomainModelProperty domainModelProperty = null;
		if(StringUtils.isNotEmpty(id)) {
            domainModelProperty = domainModelPropertyDao.findOne(id);
        }

		return domainModelProperty;
	}

	@Transactional(readOnly=true)
	public DomainModelProperty findOne(DomainModelPropertyCondition domainModelPropertyCondition){

		List<DomainModelProperty> domainModelPropertyList = domainModelPropertyDao.findAll(domainModelPropertyCondition);

		DomainModelProperty result = null;
		if(CollectionUtils.isNotEmpty(domainModelPropertyList)){
			result = domainModelPropertyList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<DomainModelProperty> findAll(DomainModelPropertyCondition domainModelPropertyCondition){

		List<DomainModelProperty> domainModelPropertyList = domainModelPropertyDao.findAll(domainModelPropertyCondition, getDefaultSort());

		return domainModelPropertyList;
	}

	@Transactional(readOnly=true)
	public Page<DomainModelProperty> find(Pageable pageable){
		Page<DomainModelProperty> domainModelPropertyList = domainModelPropertyDao.findAll(pageable, null, getDefaultSort());
		return domainModelPropertyList;
	}

	@Transactional(readOnly=true)
	public Page<DomainModelProperty> find(Pageable pageable, DomainModelPropertyCondition domainModelPropertyCondition){
		Page<DomainModelProperty> domainModelPropertyList = domainModelPropertyDao.findAll(pageable, domainModelPropertyCondition, getDefaultSort());
		return domainModelPropertyList;
	}

	@Transactional(readOnly=true)
	public long count(DomainModelPropertyCondition domainModelPropertyCondition){
		long count = domainModelPropertyDao.count(domainModelPropertyCondition);
		return count;
	}

	@Transactional(readOnly=true)
	public boolean exist(String domainId, String propertyId){

		DomainModelProperty domainModelProperty = find(propertyId);
		if(domainModelProperty == null){
			return false;
		}

		return domainModelProperty.getDomainModelId().equals(domainId);


	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC , DomainModelProperty.PROPERTY_VIEW_INDEX);
		return sort;
	}


	/**
	 * 删除对象的所有属性
	 * @param modelId
	 */
	public void deletePropertyForModel(String modelId){
		DomainModelPropertyCondition condition = new DomainModelPropertyCondition();
		condition.setDomainModelId(modelId);
		List<DomainModelProperty> domainModelPropertyList = this.domainModelPropertyDao.findAll(condition);
		for(DomainModelProperty domainModelProperty : domainModelPropertyList){
			this.delete(domainModelProperty.getId());
		}
	}

	public DomainModelProperty findPrimaryKeyFormModel(String modelId){
		DomainModelPropertyCondition condition = new DomainModelPropertyCondition();
		condition.setDomainModelId(modelId);
		condition.setPrimaryKey(true);
		return this.findOne(condition);
	}




}