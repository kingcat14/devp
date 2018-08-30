package com.kingzoo.kingcat.project.icode4.business.microservice.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.microservice.dao.TransModelItfcMappingDao;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModelItfcMapping;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelItfcMappingCondition;
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


@Service("transModelItfcMappingService")
public class TransModelItfcMappingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransModelItfcMapping.class);

	@Autowired
	@Qualifier("transModelItfcMappingDao")
	private TransModelItfcMappingDao transModelItfcMappingDao;

	@Transactional
	public void add(TransModelItfcMapping transModelItfcMapping){
		transModelItfcMapping.setId(IDGenerator.get());
		transModelItfcMappingDao.insert(transModelItfcMapping);
	}

	@Transactional
	public void add(List<TransModelItfcMapping> transModelItfcMappingList){
		for(TransModelItfcMapping transModelItfcMapping:transModelItfcMappingList){
			this.add(transModelItfcMapping);
		}
	}

	@Transactional
	public void delete(TransModelItfcMapping transModelItfcMapping){
	    LOGGER.debug("delete transModelItfcMapping:{}", transModelItfcMapping);
		transModelItfcMappingDao.delete(transModelItfcMapping.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete TransModelItfcMapping by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete transModelItfcMapping:{}", id);
		transModelItfcMappingDao.delete(id);
	}

	@Transactional
	public void delete(List<TransModelItfcMapping> transModelItfcMappings){
		for(TransModelItfcMapping transModelItfcMapping: transModelItfcMappings){
			delete(transModelItfcMapping.getId());
		}
	}

	@Transactional
	public void merge(TransModelItfcMapping transModelItfcMapping){
		transModelItfcMappingDao.save(transModelItfcMapping);
	}

	@Transactional
	public void merge(List<TransModelItfcMapping> transModelItfcMappingList){
		for(TransModelItfcMapping transModelItfcMapping:transModelItfcMappingList){
			this.merge(transModelItfcMapping);
		}
	}

	@Transactional(readOnly=true)
	public TransModelItfcMapping find(String id){
	    TransModelItfcMapping transModelItfcMapping = null;
		if(StringUtils.isNotEmpty(id)) {
            transModelItfcMapping = transModelItfcMappingDao.findOne(id);
        }

		return transModelItfcMapping;
	}

	@Transactional(readOnly=true)
	public TransModelItfcMapping findOne(TransModelItfcMappingCondition transModelItfcMappingCondition){

		List<TransModelItfcMapping> transModelItfcMappingList = transModelItfcMappingDao.findAll(transModelItfcMappingCondition);

		TransModelItfcMapping result = null;
		if(CollectionUtils.isNotEmpty(transModelItfcMappingList)){
			result = transModelItfcMappingList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<TransModelItfcMapping> findAll(TransModelItfcMappingCondition transModelItfcMappingCondition){

		List<TransModelItfcMapping> transModelItfcMappingList = transModelItfcMappingDao.findAll(transModelItfcMappingCondition, getDefaultSort());

		return transModelItfcMappingList;
	}

	@Transactional(readOnly=true)
	public Page<TransModelItfcMapping> find(Pageable pageable){
		Page<TransModelItfcMapping> transModelItfcMappingList = transModelItfcMappingDao.findAll(pageable, null, getDefaultSort());
		return transModelItfcMappingList;
	}

	@Transactional(readOnly=true)
	public Page<TransModelItfcMapping> find(Pageable pageable, TransModelItfcMappingCondition transModelItfcMappingCondition){
		Page<TransModelItfcMapping> transModelItfcMappingList = transModelItfcMappingDao.findAll(pageable, transModelItfcMappingCondition, getDefaultSort());
		return transModelItfcMappingList;
	}

	@Transactional(readOnly=true)
	public long count(TransModelItfcMappingCondition transModelItfcMappingCondition){
		long count = transModelItfcMappingDao.count(transModelItfcMappingCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , TransModelItfcMapping.PROPERTY_TRANS_MODEL_ID);
		return sort;
	}





}