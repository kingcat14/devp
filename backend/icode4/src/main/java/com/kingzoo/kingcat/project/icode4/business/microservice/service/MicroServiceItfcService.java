package com.kingzoo.kingcat.project.icode4.business.microservice.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.microservice.dao.MicroServiceItfcDao;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroService;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfc;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfcParameters;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModel;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceItfcCondition;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelCondition;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
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


@Service("microServiceItfcService")
public class MicroServiceItfcService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroServiceItfc.class);

	@Autowired
	@Qualifier("microServiceItfcDao")
	private MicroServiceItfcDao microServiceItfcDao;

	@Autowired
	private TransModelService transModelService;

	@Autowired
	private TransModelPropertyService transModelPropertyService;

	@Autowired
	private MicroServiceItfcParametersService microServiceItfcParametersService;

	@Transactional
	public void add(MicroServiceItfc microServiceItfc){
		microServiceItfc.setId(IDGenerator.get());
		microServiceItfcDao.insert(microServiceItfc);
	}

	@Transactional
	public void add(List<MicroServiceItfc> microServiceItfcList){
		for(MicroServiceItfc microServiceItfc:microServiceItfcList){
			this.add(microServiceItfc);
		}
	}

	@Transactional
	public void delete(MicroServiceItfc microServiceItfc){
	    LOGGER.debug("delete microServiceItfc:{}", microServiceItfc);
		delete(microServiceItfc.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete MicroServiceItfc by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete microServiceItfc:{}", id);
		microServiceItfcDao.delete(id);
		microServiceItfcParametersService.deleteByItfcId(id);
	}

	@Transactional
	public void delete(List<MicroServiceItfc> microServiceItfcs){
		for(MicroServiceItfc microServiceItfc: microServiceItfcs){
			delete(microServiceItfc.getId());
		}
	}

	@Transactional
	public void merge(MicroServiceItfc microServiceItfc){
		microServiceItfcDao.save(microServiceItfc);
	}

	@Transactional
	public void merge(List<MicroServiceItfc> microServiceItfcList){
		for(MicroServiceItfc microServiceItfc:microServiceItfcList){
			this.merge(microServiceItfc);
		}
	}

	@Transactional(readOnly=true)
	public MicroServiceItfc find(String id){
	    MicroServiceItfc microServiceItfc = null;
		if(StringUtils.isNotEmpty(id)) {
            microServiceItfc = microServiceItfcDao.findOne(id);
        }

		return microServiceItfc;
	}

	@Transactional(readOnly=true)
	public MicroServiceItfc findOne(MicroServiceItfcCondition microServiceItfcCondition){

		List<MicroServiceItfc> microServiceItfcList = microServiceItfcDao.findAll(microServiceItfcCondition);

		MicroServiceItfc result = null;
		if(CollectionUtils.isNotEmpty(microServiceItfcList)){
			result = microServiceItfcList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<MicroServiceItfc> findAll(MicroServiceItfcCondition microServiceItfcCondition){

		List<MicroServiceItfc> microServiceItfcList = microServiceItfcDao.findAll(microServiceItfcCondition, getDefaultSort());

		return microServiceItfcList;
	}

	@Transactional(readOnly=true)
	public Page<MicroServiceItfc> find(Pageable pageable){
		Page<MicroServiceItfc> microServiceItfcList = microServiceItfcDao.findAll(pageable, null, getDefaultSort());
		return microServiceItfcList;
	}

	@Transactional(readOnly=true)
	public Page<MicroServiceItfc> find(Pageable pageable, MicroServiceItfcCondition microServiceItfcCondition){
		Page<MicroServiceItfc> microServiceItfcList = microServiceItfcDao.findAll(pageable, microServiceItfcCondition, getDefaultSort());
		return microServiceItfcList;
	}

	@Transactional(readOnly=true)
	public long count(MicroServiceItfcCondition microServiceItfcCondition){
		long count = microServiceItfcDao.count(microServiceItfcCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , MicroServiceItfc.PROPERTY_NAME);
		return sort;
	}


	/**
	 *
	 * @param microService
	 * @param domainModel
	 */
	public void createPostItfc(MicroService microService, DomainModel domainModel){

		MicroServiceItfc microServiceItfc = new MicroServiceItfc();
		microServiceItfc.setMicroServiceId(microService.getId());
		microServiceItfc.setName("新增"+domainModel.getName());
		microServiceItfc.setCode("add");
		microServiceItfc.setMethod("POST");
		microServiceItfc.setUrl("");

		TransModel transModel = transModelService.createFromDomainModel(domainModel, domainModel.getCode()+"AddRequestTO");
		transModel.setName("新增"+domainModel.getName()+"请求");
		transModelService.merge(transModel);

		TransModelCondition transModelCondition = new TransModelCondition();
		transModelCondition.setDomainModelId(domainModel.getId());
		transModelCondition.setCode(domainModel.getCode()+"TO");

		TransModel transModelTO = transModelService.findOne(transModelCondition);


		microServiceItfc.setRequestId(transModel.getId());
		microServiceItfc.setResponseId(transModelTO.getId());


		this.add(microServiceItfc);

	}
	public void createDeleteItfc(MicroService microService, DomainModel domainModel){

		MicroServiceItfc microServiceItfc = new MicroServiceItfc();
		microServiceItfc.setMicroServiceId(microService.getId());
		microServiceItfc.setName("删除"+domainModel.getName());
		microServiceItfc.setCode("delete");
		microServiceItfc.setMethod("DELETE");
		microServiceItfc.setUrl("/{idArray}");

		this.add(microServiceItfc);

		MicroServiceItfcParameters parameters = new MicroServiceItfcParameters();
		parameters.setCode("idArray");
		parameters.setName("id数组");
		parameters.setMemo("删除传输对象,id以逗号分隔");
		parameters.setPathMapping("idArray");
		parameters.setType("String");
		parameters.setViewIndex(0);
		parameters.setRequired(true);
		parameters.setMicroServiceItfcId(microServiceItfc.getId());
		microServiceItfcParametersService.add(parameters);

	}
	public void createPutItfc(MicroService microService, DomainModel domainModel){

		MicroServiceItfc microServiceItfc = new MicroServiceItfc();
		microServiceItfc.setMicroServiceId(microService.getId());
		microServiceItfc.setName("修改" + domainModel.getName());
		microServiceItfc.setCode("update");
		microServiceItfc.setMethod("PUT");
		microServiceItfc.setUrl("/{id}");
		microServiceItfc.setDescription("修改" + domainModel.getName());

		TransModel transModel = transModelService.createFromDomainModel(domainModel, domainModel.getCode()+"UpdateRequestTO");
		transModel.setName("修改" + domainModel.getName() + "请求");
		transModelService.merge(transModel);

		TransModelCondition transModelCondition = new TransModelCondition();
		transModelCondition.setDomainModelId(domainModel.getId());
		transModelCondition.setCode(domainModel.getCode()+"TO");

		TransModel transModelTO = transModelService.findOne(transModelCondition);

		microServiceItfc.setRequestId(transModel.getId());
		microServiceItfc.setResponseId(transModelTO.getId());

		this.add(microServiceItfc);

		MicroServiceItfcParameters parameters = new MicroServiceItfcParameters();
		parameters.setCode("id");
		parameters.setName("对象的id");
		parameters.setMemo("待修改的对象ID");
		parameters.setPathMapping("id");
		parameters.setType("String");
		parameters.setViewIndex(0);
		parameters.setRequired(true);
		parameters.setMicroServiceItfcId(microServiceItfc.getId());
		microServiceItfcParametersService.add(parameters);
	}

	public void createGetItfc(MicroService microService, DomainModel domainModel){

		MicroServiceItfc microServiceItfc = new MicroServiceItfc();
		microServiceItfc.setMicroServiceId(microService.getId());
		microServiceItfc.setName("查询" + domainModel.getName());
		microServiceItfc.setCode("get");
		microServiceItfc.setMethod("GET");
		microServiceItfc.setUrl("/{id}");
		microServiceItfc.setDescription("根据ID查询" + domainModel.getName()+"的详情");


		TransModelCondition transModelCondition = new TransModelCondition();
		transModelCondition.setDomainModelId(domainModel.getId());
		transModelCondition.setCode(domainModel.getCode()+"TO");

		TransModel transModelTO = transModelService.findOne(transModelCondition);

		microServiceItfc.setResponseId(transModelTO.getId());

		this.add(microServiceItfc);

		MicroServiceItfcParameters parameters = new MicroServiceItfcParameters();
		parameters.setCode("id");
		parameters.setName("对象的id");
		parameters.setMemo("待查询的对象ID");
		parameters.setPathMapping("id");
		parameters.setType("String");
		parameters.setViewIndex(0);
		parameters.setRequired(true);
		parameters.setMicroServiceItfcId(microServiceItfc.getId());
		microServiceItfcParametersService.add(parameters);
	}

	public void createListItfc(MicroService microService, DomainModel domainModel){

		MicroServiceItfc microServiceItfc = new MicroServiceItfc();
		microServiceItfc.setMicroServiceId(microService.getId());
		microServiceItfc.setName("查询" + domainModel.getName()+"列表");
		microServiceItfc.setCode("list");
		microServiceItfc.setMethod("PUT");
		microServiceItfc.setUrl("/list");
		microServiceItfc.setDescription("查询" + domainModel.getName()+"列表");

		TransModel transModel = transModelService.createSearchTOFromDomainModel(domainModel, domainModel.getCode()+"SearchRequestTO");
		transModel.setName("查询" + domainModel.getName() + "请求");
		transModelService.merge(transModel);

		TransModelCondition transModelCondition = new TransModelCondition();
		transModelCondition.setDomainModelId(domainModel.getId());
		transModelCondition.setCode(domainModel.getCode()+"TO");

		TransModel transModelTO = transModelService.findOne(transModelCondition);

		microServiceItfc.setRequestId(transModel.getId());
		microServiceItfc.setResponseId(transModelTO.getId());

		this.add(microServiceItfc);

	}



	public void deleteByMicroServiceId(String microServiceId){
		if(StringUtils.isEmpty(microServiceId)){
			return ;
		}
		MicroServiceItfcCondition condition = new MicroServiceItfcCondition();
		condition.setMicroServiceId(microServiceId);


		List<MicroServiceItfc> list = this.findAll(condition);
		this.delete(list);
	}


}