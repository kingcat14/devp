package com.kingzoo.kingcat.project.icode4.business.microservice.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.microservice.dao.TransModelPropertyDao;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModelProperty;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelPropertyCondition;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
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

import java.util.ArrayList;
import java.util.List;


@Service("transModelPropertyService")
public class TransModelPropertyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransModelProperty.class);

	@Autowired
	@Qualifier("transModelPropertyDao")
	private TransModelPropertyDao transModelPropertyDao;

	@Transactional
	public void add(TransModelProperty transModelProperty){
		transModelProperty.setId(IDGenerator.get());
		transModelPropertyDao.insert(transModelProperty);
	}

	@Transactional
	public void add(List<TransModelProperty> transModelPropertyList){
		for(TransModelProperty transModelProperty:transModelPropertyList){
			this.add(transModelProperty);
		}
	}

	@Transactional
	public void delete(TransModelProperty transModelProperty){
	    LOGGER.debug("delete transModelProperty:{}", transModelProperty);
		transModelPropertyDao.delete(transModelProperty.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete TransModelProperty by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete transModelProperty:{}", id);
		transModelPropertyDao.delete(id);
	}

	@Transactional
	public void delete(List<TransModelProperty> transModelPropertys){
		for(TransModelProperty transModelProperty: transModelPropertys){
			delete(transModelProperty.getId());
		}
	}

	@Transactional
	public void merge(TransModelProperty transModelProperty){
		transModelPropertyDao.save(transModelProperty);
	}

	@Transactional
	public void merge(List<TransModelProperty> transModelPropertyList){
		for(TransModelProperty transModelProperty:transModelPropertyList){
			this.merge(transModelProperty);
		}
	}

	@Transactional(readOnly=true)
	public TransModelProperty find(String id){
	    TransModelProperty transModelProperty = null;
		if(StringUtils.isNotEmpty(id)) {
            transModelProperty = transModelPropertyDao.findOne(id);
        }

		return transModelProperty;
	}

	@Transactional(readOnly=true)
	public TransModelProperty findOne(TransModelPropertyCondition transModelPropertyCondition){

		List<TransModelProperty> transModelPropertyList = transModelPropertyDao.findAll(transModelPropertyCondition);

		TransModelProperty result = null;
		if(CollectionUtils.isNotEmpty(transModelPropertyList)){
			result = transModelPropertyList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<TransModelProperty> findAll(TransModelPropertyCondition transModelPropertyCondition){

		List<TransModelProperty> transModelPropertyList = transModelPropertyDao.findAll(transModelPropertyCondition, getDefaultSort());

		return transModelPropertyList;
	}

	@Transactional(readOnly=true)
	public Page<TransModelProperty> find(Pageable pageable){
		Page<TransModelProperty> transModelPropertyList = transModelPropertyDao.findAll(pageable, null, getDefaultSort());
		return transModelPropertyList;
	}

	@Transactional(readOnly=true)
	public Page<TransModelProperty> find(Pageable pageable, TransModelPropertyCondition transModelPropertyCondition){
		Page<TransModelProperty> transModelPropertyList = transModelPropertyDao.findAll(pageable, transModelPropertyCondition, getDefaultSort());
		return transModelPropertyList;
	}

	@Transactional(readOnly=true)
	public long count(TransModelPropertyCondition transModelPropertyCondition){
		long count = transModelPropertyDao.count(transModelPropertyCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , TransModelProperty.PROPERTY_NAME);
		return sort;
	}


	/**
	 * 创建范围属性
	 * @return
	 */
	public List<TransModelProperty> createRangeProperty(DomainModelProperty domainModelProperty, String transModelId){


		List<TransModelProperty> result = new ArrayList<>();

		TransModelProperty transModelProperty = new TransModelProperty();
		BeanUtils.copyProperties(domainModelProperty, transModelProperty);
		transModelProperty.setDomainModelId(domainModelProperty.getDomainModelId());
		transModelProperty.setDomainModelPropertyId(domainModelProperty.getId());
		transModelProperty.setCoreRelation(true);
		transModelProperty.setTransModelId(transModelId);
		transModelProperty.setId(null);


		if("Integer".equals(domainModelProperty.getType())
				|| "Long".equals(domainModelProperty.getType())
				|| "Double".equals(domainModelProperty.getType())){
			TransModelProperty maxTransModelProperty = new TransModelProperty();
			BeanUtils.copyProperties(domainModelProperty, maxTransModelProperty);
			maxTransModelProperty.setDomainModelId(domainModelProperty.getDomainModelId());
			maxTransModelProperty.setDomainModelPropertyId(domainModelProperty.getId());
			maxTransModelProperty.setCoreRelation(true);
			maxTransModelProperty.setCode(domainModelProperty.getCode()+"Max");
			maxTransModelProperty.setTransModelId(transModelId);
			maxTransModelProperty.setId(null);

			TransModelProperty minTransModelProperty = new TransModelProperty();
			BeanUtils.copyProperties(domainModelProperty, minTransModelProperty);
			minTransModelProperty.setDomainModelId(domainModelProperty.getDomainModelId());
			minTransModelProperty.setDomainModelPropertyId(domainModelProperty.getId());
			minTransModelProperty.setCoreRelation(true);
			minTransModelProperty.setCode(domainModelProperty.getCode()+"Min");
			minTransModelProperty.setTransModelId(transModelId);
			minTransModelProperty.setId(null);

			result.add(maxTransModelProperty);
			result.add(minTransModelProperty);
		}

		if("DateTime".equals(domainModelProperty.getType())
				|| "Date".equals(domainModelProperty.getType())){
			TransModelProperty maxTransModelProperty = new TransModelProperty();
			BeanUtils.copyProperties(domainModelProperty, maxTransModelProperty);
			maxTransModelProperty.setDomainModelId(domainModelProperty.getDomainModelId());
			maxTransModelProperty.setDomainModelPropertyId(domainModelProperty.getId());
			maxTransModelProperty.setCoreRelation(true);
			maxTransModelProperty.setCode(domainModelProperty.getCode()+"End");
			maxTransModelProperty.setTransModelId(transModelId);
			maxTransModelProperty.setId(null);

			TransModelProperty minTransModelProperty = new TransModelProperty();
			BeanUtils.copyProperties(domainModelProperty, minTransModelProperty);
			minTransModelProperty.setDomainModelId(domainModelProperty.getDomainModelId());
			minTransModelProperty.setDomainModelPropertyId(domainModelProperty.getId());
			minTransModelProperty.setCoreRelation(true);
			minTransModelProperty.setCode(domainModelProperty.getCode()+"Start");
			minTransModelProperty.setTransModelId(transModelId);
			minTransModelProperty.setId(null);

			result.add(maxTransModelProperty);
			result.add(minTransModelProperty);
		}

		return result;

	}


	public List<TransModelProperty> createProperty(DomainModelProperty domainModelProperty, String transModelId){


		List<TransModelProperty> result = new ArrayList<>();

		TransModelProperty transModelProperty = new TransModelProperty();
		BeanUtils.copyProperties(domainModelProperty, transModelProperty);
		transModelProperty.setDomainModelId(domainModelProperty.getDomainModelId());
		transModelProperty.setDomainModelPropertyId(domainModelProperty.getId());
		transModelProperty.setCoreRelation(true);
		transModelProperty.setTransModelId(transModelId);
		transModelProperty.setId(null);

		result.add(transModelProperty);


		return result;

	}


}