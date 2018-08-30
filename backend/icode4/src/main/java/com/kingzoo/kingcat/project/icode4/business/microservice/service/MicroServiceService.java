package com.kingzoo.kingcat.project.icode4.business.microservice.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.microservice.dao.MicroServiceDao;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroService;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceCondition;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
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


@Service("microServiceService")
public class MicroServiceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroService.class);

	@Autowired
	@Qualifier("microServiceDao")
	private MicroServiceDao microServiceDao;


	@Autowired
	private DomainModelService domainModelService;

	@Autowired
	private MicroServiceItfcService microServiceItfcService;

	@Autowired
	private TransModelService transModelService;

	@Autowired
	private TransModelPropertyService transModelPropertyService;

	@Transactional
	public void add(MicroService microService){
		microService.setId(IDGenerator.get());
		microServiceDao.insert(microService);
	}

	@Transactional
	public void add(List<MicroService> microServiceList){
		for(MicroService microService:microServiceList){
			this.add(microService);
		}
	}

	@Transactional
	public void delete(MicroService microService){
	    LOGGER.debug("delete microService:{}", microService);
		delete(microService.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete MicroService by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete microService:{}", id);
		microServiceDao.delete(id);

		microServiceItfcService.deleteByMicroServiceId(id);
	}

	@Transactional
	public void delete(List<MicroService> microServices){
		for(MicroService microService: microServices){
			delete(microService.getId());
		}
	}

	@Transactional
	public void merge(MicroService microService){
		microServiceDao.save(microService);
	}

	@Transactional
	public void merge(List<MicroService> microServiceList){
		for(MicroService microService:microServiceList){
			this.merge(microService);
		}
	}

	@Transactional(readOnly=true)
	public MicroService find(String id){
	    MicroService microService = null;
		if(StringUtils.isNotEmpty(id)) {
            microService = microServiceDao.findOne(id);
        }

		return microService;
	}

	@Transactional(readOnly=true)
	public MicroService findOne(MicroServiceCondition microServiceCondition){

		List<MicroService> microServiceList = microServiceDao.findAll(microServiceCondition);

		MicroService result = null;
		if(CollectionUtils.isNotEmpty(microServiceList)){
			result = microServiceList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<MicroService> findAll(MicroServiceCondition microServiceCondition){

		List<MicroService> microServiceList = microServiceDao.findAll(microServiceCondition, getDefaultSort());

		return microServiceList;
	}

	@Transactional(readOnly=true)
	public Page<MicroService> find(Pageable pageable){
		Page<MicroService> microServiceList = microServiceDao.findAll(pageable, null, getDefaultSort());
		return microServiceList;
	}

	@Transactional(readOnly=true)
	public Page<MicroService> find(Pageable pageable, MicroServiceCondition microServiceCondition){
		Page<MicroService> microServiceList = microServiceDao.findAll(pageable, microServiceCondition, getDefaultSort());
		return microServiceList;
	}

	@Transactional(readOnly=true)
	public long count(MicroServiceCondition microServiceCondition){
		long count = microServiceDao.count(microServiceCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , MicroService.PROPERTY_NAME);
		return sort;
	}


	/**
	 * 从一个领域对象，创建一组微服务以及相关的传输对象
	 * @param domainId
	 */
	public void createFromDomainModel(String domainId){

		DomainModel domainModel = domainModelService.find(domainId);

		/*
		 * 1.创建微服务
		 * 2.创建增删改查的微服务接口
		 */

		//1.
		MicroService microService = new MicroService();
		microService.setModuleId(domainModel.getModuleId());
		microService.setName(domainModel.getName()+"服务");
		microService.setCode(domainModel.getCode()+"Service");


		this.add(microService);

		//创建
		transModelService.createFromDomainModel(domainId);

		//2.
		microServiceItfcService.createPostItfc(microService, domainModel);
		microServiceItfcService.createDeleteItfc(microService, domainModel);
		microServiceItfcService.createPutItfc(microService, domainModel);
		microServiceItfcService.createGetItfc(microService, domainModel);
		microServiceItfcService.createListItfc(microService, domainModel);
	}



}