package com.kingzoo.kingcat.project.icode4.business.system.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.system.dao.DomainChildModelDao;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainChildModel;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainChildModelCondition;
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


@Service("domainChildModelService")
public class DomainChildModelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainChildModel.class);

	@Autowired
	@Qualifier("domainChildModelDao")
	private DomainChildModelDao domainChildModelDao;

	@Transactional
	public void add(DomainChildModel domainChildModel){
		domainChildModel.setId(IDGenerator.get());
		domainChildModelDao.insert(domainChildModel);
	}

	@Transactional
	public void add(List<DomainChildModel> domainChildModelList){
		for(DomainChildModel domainChildModel:domainChildModelList){
			this.add(domainChildModel);
		}
	}

	@Transactional
	public void delete(DomainChildModel domainChildModel){
	    LOGGER.debug("delete domainChildModel:{}", domainChildModel);
		domainChildModelDao.delete(domainChildModel.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete DomainChildModel by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete domainChildModel:{}", id);
		domainChildModelDao.delete(id);
	}

	@Transactional
	public void delete(List<DomainChildModel> domainChildModels){
		for(DomainChildModel domainChildModel: domainChildModels){
			delete(domainChildModel.getId());
		}
	}

	@Transactional
	public void merge(DomainChildModel domainChildModel){
		domainChildModelDao.save(domainChildModel);
	}

	@Transactional
	public void merge(List<DomainChildModel> domainChildModelList){
		for(DomainChildModel domainChildModel:domainChildModelList){
			this.merge(domainChildModel);
		}
	}

	@Transactional(readOnly=true)
	public DomainChildModel find(String id){
	    DomainChildModel domainChildModel = null;
		if(StringUtils.isNotEmpty(id)) {
            domainChildModel = domainChildModelDao.findOne(id);
        }

		return domainChildModel;
	}

	@Transactional(readOnly=true)
	public DomainChildModel findOne(DomainChildModelCondition domainChildModelCondition){

		List<DomainChildModel> domainChildModelList = domainChildModelDao.findAll(domainChildModelCondition);

		DomainChildModel result = null;
		if(CollectionUtils.isNotEmpty(domainChildModelList)){
			result = domainChildModelList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<DomainChildModel> findAll(DomainChildModelCondition domainChildModelCondition){

		List<DomainChildModel> domainChildModelList = domainChildModelDao.findAll(domainChildModelCondition, getDefaultSort());

		return domainChildModelList;
	}

	@Transactional(readOnly=true)
	public Page<DomainChildModel> find(Pageable pageable){
		Page<DomainChildModel> domainChildModelList = domainChildModelDao.findAll(pageable, null, getDefaultSort());
		return domainChildModelList;
	}

	@Transactional(readOnly=true)
	public Page<DomainChildModel> find(Pageable pageable, DomainChildModelCondition domainChildModelCondition){
		Page<DomainChildModel> domainChildModelList = domainChildModelDao.findAll(pageable, domainChildModelCondition, getDefaultSort());
		return domainChildModelList;
	}

	@Transactional(readOnly=true)
	public long count(DomainChildModelCondition domainChildModelCondition){
		long count = domainChildModelDao.count(domainChildModelCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DomainChildModel.PROPERTY_NAME);
		return sort;
	}





}