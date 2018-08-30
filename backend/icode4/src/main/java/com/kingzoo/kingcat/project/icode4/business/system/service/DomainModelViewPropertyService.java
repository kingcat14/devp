package com.kingzoo.kingcat.project.icode4.business.system.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.system.dao.DomainModelViewPropertyDao;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelViewProperty;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelViewPropertyCondition;
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


@Service("domainModelViewPropertyService")
public class DomainModelViewPropertyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainModelViewProperty.class);

	@Autowired
	@Qualifier("domainModelViewPropertyDao")
	private DomainModelViewPropertyDao domainModelViewPropertyDao;

	/**
	 * 通过对象属性创建一个
	 * @param domainModelProperty
	 */
	@Transactional
	public DomainModelViewProperty create(DomainModelProperty domainModelProperty){

		DomainModelViewProperty domainModelViewProperty = new DomainModelViewProperty();
		BeanUtils.copyProperties(domainModelProperty, domainModelViewProperty);
		domainModelViewProperty.setAddable(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setAddRequired(false);
		domainModelViewProperty.setAddViewable(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setEditable(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setEditRequired(domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setEditViewable(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setSearchCondition(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setGirdColumn(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setSimpleSearchCondition(false);
		checkTid(domainModelViewProperty);
		domainModelViewPropertyDao.insert(domainModelViewProperty);

		return domainModelViewProperty;
	}

	private void checkTid(DomainModelViewProperty domainModelViewProperty){
		if(!"tid".equals(domainModelViewProperty.getCode())){
			return;
		}
		domainModelViewProperty.setAddable(false);
		domainModelViewProperty.setAddRequired(false);
		domainModelViewProperty.setAddViewable(false);
		domainModelViewProperty.setEditable(false);
		domainModelViewProperty.setEditRequired(false);
		domainModelViewProperty.setEditViewable(false);
		domainModelViewProperty.setSearchCondition(false);
		domainModelViewProperty.setGirdColumn(false);
		domainModelViewProperty.setSimpleSearchCondition(false);
	}


	@Transactional
	public void add(DomainModelViewProperty domainModelViewProperty){
		domainModelViewProperty.setId(IDGenerator.get());
		domainModelViewPropertyDao.insert(domainModelViewProperty);
	}

	@Transactional
	public void add(List<DomainModelViewProperty> domainModelViewPropertyList){
		for(DomainModelViewProperty domainModelViewProperty:domainModelViewPropertyList){
			this.add(domainModelViewProperty);
		}
	}

	@Transactional
	public void delete(DomainModelViewProperty domainModelViewProperty){
	    LOGGER.debug("delete domainModelViewProperty:{}", domainModelViewProperty);
		domainModelViewPropertyDao.delete(domainModelViewProperty.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete DomainModelViewProperty by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete domainModelViewProperty:{}", id);
		domainModelViewPropertyDao.delete(id);
	}

	@Transactional
	public void delete(List<DomainModelViewProperty> domainModelViewPropertys){
		for(DomainModelViewProperty domainModelViewProperty: domainModelViewPropertys){
			delete(domainModelViewProperty.getId());
		}
	}

	@Transactional
	public void merge(DomainModelViewProperty domainModelViewProperty){
		domainModelViewPropertyDao.save(domainModelViewProperty);
	}

	@Transactional
	public void merge(List<DomainModelViewProperty> domainModelViewPropertyList){
		for(DomainModelViewProperty domainModelViewProperty:domainModelViewPropertyList){
			this.merge(domainModelViewProperty);
		}
	}

	@Transactional(readOnly=true)
	public DomainModelViewProperty find(String id){
	    DomainModelViewProperty domainModelViewProperty = null;
		if(StringUtils.isNotEmpty(id)) {
            domainModelViewProperty = domainModelViewPropertyDao.findOne(id);
        }

		return domainModelViewProperty;
	}

	@Transactional(readOnly=true)
	public DomainModelViewProperty findOne(DomainModelViewPropertyCondition domainModelViewPropertyCondition){

		List<DomainModelViewProperty> domainModelViewPropertyList = domainModelViewPropertyDao.findAll(domainModelViewPropertyCondition);

		DomainModelViewProperty result = null;
		if(CollectionUtils.isNotEmpty(domainModelViewPropertyList)){
			result = domainModelViewPropertyList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<DomainModelViewProperty> findAll(DomainModelViewPropertyCondition domainModelViewPropertyCondition){

		List<DomainModelViewProperty> domainModelViewPropertyList = domainModelViewPropertyDao.findAll(domainModelViewPropertyCondition, getDefaultSort());

		return domainModelViewPropertyList;
	}

	@Transactional(readOnly=true)
	public Page<DomainModelViewProperty> find(Pageable pageable){
		Page<DomainModelViewProperty> domainModelViewPropertyList = domainModelViewPropertyDao.findAll(pageable, null, getDefaultSort());
		return domainModelViewPropertyList;
	}

	@Transactional(readOnly=true)
	public Page<DomainModelViewProperty> find(Pageable pageable, DomainModelViewPropertyCondition domainModelViewPropertyCondition){
		Page<DomainModelViewProperty> domainModelViewPropertyList = domainModelViewPropertyDao.findAll(pageable, domainModelViewPropertyCondition, getDefaultSort());
		return domainModelViewPropertyList;
	}

	@Transactional(readOnly=true)
	public long count(DomainModelViewPropertyCondition domainModelViewPropertyCondition){
		long count = domainModelViewPropertyDao.count(domainModelViewPropertyCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC , DomainModelViewProperty.PROPERTY_VIEW_INDEX);
		return sort;
	}





}