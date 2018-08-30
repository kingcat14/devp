package com.kingzoo.kingcat.project.icode4.business.microservice.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.microservice.dao.MicroServiceItfcParametersDao;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfcParameters;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceItfcParametersCondition;
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


@Service("microServiceItfcParametersService")
public class MicroServiceItfcParametersService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroServiceItfcParameters.class);

	@Autowired
	@Qualifier("microServiceItfcParametersDao")
	private MicroServiceItfcParametersDao microServiceItfcParametersDao;

	@Transactional
	public void add(MicroServiceItfcParameters microServiceItfcParameters){
		microServiceItfcParameters.setId(IDGenerator.get());
		microServiceItfcParametersDao.insert(microServiceItfcParameters);
	}

	@Transactional
	public void add(List<MicroServiceItfcParameters> microServiceItfcParametersList){
		for(MicroServiceItfcParameters microServiceItfcParameters:microServiceItfcParametersList){
			this.add(microServiceItfcParameters);
		}
	}

	@Transactional
	public void delete(MicroServiceItfcParameters microServiceItfcParameters){
	    LOGGER.debug("delete microServiceItfcParameters:{}", microServiceItfcParameters);
		microServiceItfcParametersDao.delete(microServiceItfcParameters.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete MicroServiceItfcParameters by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete microServiceItfcParameters:{}", id);
		microServiceItfcParametersDao.delete(id);
	}

	@Transactional
	public void delete(List<MicroServiceItfcParameters> microServiceItfcParameterss){
		for(MicroServiceItfcParameters microServiceItfcParameters: microServiceItfcParameterss){
			delete(microServiceItfcParameters.getId());
		}
	}

	@Transactional
	public void merge(MicroServiceItfcParameters microServiceItfcParameters){
		microServiceItfcParametersDao.save(microServiceItfcParameters);
	}

	@Transactional
	public void merge(List<MicroServiceItfcParameters> microServiceItfcParametersList){
		for(MicroServiceItfcParameters microServiceItfcParameters:microServiceItfcParametersList){
			this.merge(microServiceItfcParameters);
		}
	}

	@Transactional(readOnly=true)
	public MicroServiceItfcParameters find(String id){
	    MicroServiceItfcParameters microServiceItfcParameters = null;
		if(StringUtils.isNotEmpty(id)) {
            microServiceItfcParameters = microServiceItfcParametersDao.findOne(id);
        }

		return microServiceItfcParameters;
	}

	@Transactional(readOnly=true)
	public MicroServiceItfcParameters findOne(MicroServiceItfcParametersCondition microServiceItfcParametersCondition){

		List<MicroServiceItfcParameters> microServiceItfcParametersList = microServiceItfcParametersDao.findAll(microServiceItfcParametersCondition);

		MicroServiceItfcParameters result = null;
		if(CollectionUtils.isNotEmpty(microServiceItfcParametersList)){
			result = microServiceItfcParametersList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<MicroServiceItfcParameters> findAll(MicroServiceItfcParametersCondition microServiceItfcParametersCondition){

		List<MicroServiceItfcParameters> microServiceItfcParametersList = microServiceItfcParametersDao.findAll(microServiceItfcParametersCondition, getDefaultSort());

		return microServiceItfcParametersList;
	}

	@Transactional(readOnly=true)
	public Page<MicroServiceItfcParameters> find(Pageable pageable){
		Page<MicroServiceItfcParameters> microServiceItfcParametersList = microServiceItfcParametersDao.findAll(pageable, null, getDefaultSort());
		return microServiceItfcParametersList;
	}

	@Transactional(readOnly=true)
	public Page<MicroServiceItfcParameters> find(Pageable pageable, MicroServiceItfcParametersCondition microServiceItfcParametersCondition){
		Page<MicroServiceItfcParameters> microServiceItfcParametersList = microServiceItfcParametersDao.findAll(pageable, microServiceItfcParametersCondition, getDefaultSort());
		return microServiceItfcParametersList;
	}

	@Transactional(readOnly=true)
	public long count(MicroServiceItfcParametersCondition microServiceItfcParametersCondition){
		long count = microServiceItfcParametersDao.count(microServiceItfcParametersCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , MicroServiceItfcParameters.PROPERTY_VIEW_INDEX);
		return sort;
	}




	public void deleteByItfcId(String microServiceItfcId){
		if(StringUtils.isEmpty(microServiceItfcId)){
			return;
		}

		MicroServiceItfcParametersCondition condition = new MicroServiceItfcParametersCondition();
		condition.setMicroServiceItfcId(microServiceItfcId);

		List<MicroServiceItfcParameters> list = this.findAll(condition);
		this.delete(list);
	}

}