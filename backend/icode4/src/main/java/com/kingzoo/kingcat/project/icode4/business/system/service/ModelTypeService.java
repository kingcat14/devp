package com.kingzoo.kingcat.project.icode4.business.system.service;

import com.kingzoo.kingcat.project.icode4.business.system.dao.ModelTypeDao;
import com.kingzoo.kingcat.project.icode4.business.system.domain.ModelType;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ModelTypeCondition;
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


@Service("modelTypeService")
public class ModelTypeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelType.class);

	@Autowired
	@Qualifier("modelTypeDao")
	private ModelTypeDao modelTypeDao;

	@Transactional
	public void add(ModelType modelType){
		modelType.setId(modelType.getCode());
		modelTypeDao.insert(modelType);
	}

	@Transactional
	public void add(List<ModelType> modelTypeList){
		for(ModelType modelType:modelTypeList){
			this.add(modelType);
		}
	}

	@Transactional
	public void delete(ModelType modelType){
	    LOGGER.debug("delete modelType:{}", modelType);
		modelTypeDao.delete(modelType);
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete ModelType by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete modelType:{}", id);
		modelTypeDao.delete(id);
	}

	@Transactional
	public void delete(List<ModelType> modelTypes){
		for(ModelType modelType: modelTypes)
			delete(modelType);
	}

	@Transactional
	public void merge(ModelType modelType){
		modelTypeDao.save(modelType);
	}

	@Transactional
	public void merge(List<ModelType> modelTypeList){
		for(ModelType modelType:modelTypeList){
			this.merge(modelType);
		}
	}

	@Transactional(readOnly=true)
	public ModelType find(String id){
	    ModelType modelType = null;
		if(StringUtils.isNotEmpty(id)) {
            modelType = modelTypeDao.findOne(id);
        }

		return modelType;
	}

	@Transactional(readOnly=true)
	public ModelType findOne(ModelTypeCondition modelTypeCondition){

		List<ModelType> modelTypeList = modelTypeDao.findAll(modelTypeCondition);

		ModelType result = null;
		if(CollectionUtils.isNotEmpty(modelTypeList)){
			result = modelTypeList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<ModelType> findAll(ModelTypeCondition modelTypeCondition){

		List<ModelType> modelTypeList = modelTypeDao.findAll(modelTypeCondition, getDefaultSort());

		return modelTypeList;
	}

	@Transactional(readOnly=true)
	public Page<ModelType> find(Pageable pageable){
		Page<ModelType> modelTypeList = modelTypeDao.findAll(pageable, null, getDefaultSort());
		return modelTypeList;
	}

	@Transactional(readOnly=true)
	public Page<ModelType> find(Pageable pageable, ModelTypeCondition modelTypeCondition){
		Page<ModelType> modelTypeList = modelTypeDao.findAll(pageable, modelTypeCondition, getDefaultSort());
		return modelTypeList;
	}

	@Transactional(readOnly=true)
	public long count(ModelTypeCondition modelTypeCondition){
		long count = modelTypeDao.count(modelTypeCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , ModelType.PROPERTY_CODE);
		return sort;
	}





}