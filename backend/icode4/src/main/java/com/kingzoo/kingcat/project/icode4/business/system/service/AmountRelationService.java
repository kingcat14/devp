package com.kingzoo.kingcat.project.icode4.business.system.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.system.dao.AmountRelationDao;
import com.kingzoo.kingcat.project.icode4.business.system.domain.AmountRelation;
import com.kingzoo.kingcat.project.icode4.business.system.vo.AmountRelationCondition;
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


@Service("amountRelationService")
public class AmountRelationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AmountRelation.class);

	@Autowired
	@Qualifier("amountRelationDao")
	private AmountRelationDao amountRelationDao;

	@Transactional
	public void add(AmountRelation amountRelation){
		amountRelation.setId(IDGenerator.get());
		amountRelationDao.insert(amountRelation);
	}

	@Transactional
	public void add(List<AmountRelation> amountRelationList){
		for(AmountRelation amountRelation:amountRelationList){
			this.add(amountRelation);
		}
	}

	@Transactional
	public void delete(AmountRelation amountRelation){
	    LOGGER.debug("delete amountRelation:{}", amountRelation);
		amountRelationDao.delete(amountRelation);
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete AmountRelation by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete amountRelation:{}", id);
		amountRelationDao.delete(id);
	}

	@Transactional
	public void delete(List<AmountRelation> amountRelations){
		for(AmountRelation amountRelation: amountRelations)
			delete(amountRelation);
	}

	@Transactional
	public void merge(AmountRelation amountRelation){
		amountRelationDao.save(amountRelation);
	}

	@Transactional
	public void merge(List<AmountRelation> amountRelationList){
		for(AmountRelation amountRelation:amountRelationList){
			this.merge(amountRelation);
		}
	}

	@Transactional(readOnly=true)
	public AmountRelation find(String id){
	    AmountRelation amountRelation = null;
		if(StringUtils.isNotEmpty(id)) {
            amountRelation = amountRelationDao.findOne(id);
        }

		return amountRelation;
	}

	@Transactional(readOnly=true)
	public AmountRelation findOne(AmountRelationCondition amountRelationCondition){

		List<AmountRelation> amountRelationList = amountRelationDao.findAll(amountRelationCondition);

		AmountRelation result = null;
		if(CollectionUtils.isNotEmpty(amountRelationList)){
			result = amountRelationList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<AmountRelation> findAll(AmountRelationCondition amountRelationCondition){

		List<AmountRelation> amountRelationList = amountRelationDao.findAll(amountRelationCondition, getDefaultSort());

		return amountRelationList;
	}

	@Transactional(readOnly=true)
	public Page<AmountRelation> find(Pageable pageable){
		Page<AmountRelation> amountRelationList = amountRelationDao.findAll(pageable, null, getDefaultSort());
		return amountRelationList;
	}

	@Transactional(readOnly=true)
	public Page<AmountRelation> find(Pageable pageable, AmountRelationCondition amountRelationCondition){
		Page<AmountRelation> amountRelationList = amountRelationDao.findAll(pageable, amountRelationCondition, getDefaultSort());
		return amountRelationList;
	}

	@Transactional(readOnly=true)
	public long count(AmountRelationCondition amountRelationCondition){
		long count = amountRelationDao.count(amountRelationCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , AmountRelation.PROPERTY_CODE);
		return sort;
	}





}