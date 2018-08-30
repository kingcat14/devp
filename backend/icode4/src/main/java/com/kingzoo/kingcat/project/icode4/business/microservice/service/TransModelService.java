package com.kingzoo.kingcat.project.icode4.business.microservice.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.microservice.dao.TransModelDao;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModel;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModelProperty;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelCondition;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelPropertyCondition;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelPropertyService;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModuleService;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelPropertyCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ModuleCondition;
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


@Service("transModelService")
public class TransModelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransModel.class);

	@Autowired
	@Qualifier("transModelDao")
	private TransModelDao transModelDao;

	@Autowired
	private TransModelPropertyService transModelPropertyService;

	@Autowired
	private DomainModelService domainModelService;

	@Autowired
	private DomainModelPropertyService domainModelPropertyService;

	@Autowired
	private ModuleService moduleService;


	@Transactional
	public void add(TransModel transModel){
		transModel.setId(IDGenerator.get());
		transModelDao.insert(transModel);
	}

	@Transactional
	public void add(List<TransModel> transModelList){
		for(TransModel transModel:transModelList){
			this.add(transModel);
		}
	}

	@Transactional
	public void delete(TransModel transModel){
	    LOGGER.debug("delete transModel:{}", transModel);
		transModelDao.delete(transModel.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete TransModel by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete transModel:{}", id);
		transModelDao.delete(id);
	}

	@Transactional
	public void delete(List<TransModel> transModels){
		for(TransModel transModel: transModels){
			delete(transModel.getId());
		}
	}

	@Transactional
	public void merge(TransModel transModel){
		transModelDao.save(transModel);
	}

	@Transactional
	public void merge(List<TransModel> transModelList){
		for(TransModel transModel:transModelList){
			this.merge(transModel);
		}
	}

	@Transactional(readOnly=true)
	public TransModel find(String id){
	    TransModel transModel = null;
		if(StringUtils.isNotEmpty(id)) {
            transModel = transModelDao.findOne(id);
        }

		return transModel;
	}

	@Transactional(readOnly=true)
	public TransModel findOne(TransModelCondition transModelCondition){

		List<TransModel> transModelList = transModelDao.findAll(transModelCondition);

		TransModel result = null;
		if(CollectionUtils.isNotEmpty(transModelList)){
			result = transModelList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<TransModel> findAll(TransModelCondition transModelCondition){

		List<TransModel> transModelList = transModelDao.findAll(transModelCondition, getDefaultSort());

		return transModelList;
	}

	@Transactional(readOnly=true)
	public Page<TransModel> find(Pageable pageable){
		Page<TransModel> transModelList = transModelDao.findAll(pageable, null, getDefaultSort());
		return transModelList;
	}

	@Transactional(readOnly=true)
	public Page<TransModel> find(Pageable pageable, TransModelCondition transModelCondition){
		Page<TransModel> transModelList = transModelDao.findAll(pageable, transModelCondition, getDefaultSort());
		return transModelList;
	}

	@Transactional(readOnly=true)
	public long count(TransModelCondition transModelCondition){
		long count = transModelDao.count(transModelCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , TransModel.PROPERTY_NAME);
		return sort;
	}


	public void createFromDomainModel(String domainModelId){

		DomainModel domainModel = domainModelService.find(domainModelId);

		//如果包含了同一个领域对象Code相同的TO，则不再创建
		TransModelCondition condition = new TransModelCondition();
		condition.setDomainModelId(domainModelId);
		condition.setCode(domainModel.getCode()+"TO");

		long existCount = this.transModelDao.count(condition);
		if(existCount > 0){
			return;
		}

		TransModel transModel = new TransModel();
		BeanUtils.copyProperties(domainModel, transModel);
		transModel.setName(domainModel.getName());
		transModel.setCode(domainModel.getCode()+"TO");
		transModel.setDomainModelId(domainModelId);
		transModel.setDomainModelName(domainModel.getName());
		transModel.setDescription(domainModel.getDescription());
		transModel.setViewIndex(0);
		transModel.setMemo(domainModel.getDescription());

		transModel.setId(null);

		this.add(transModel);

		DomainModelPropertyCondition propertyCondition = new DomainModelPropertyCondition();
		propertyCondition.setDomainModelId(domainModelId+"");
		List<DomainModelProperty> propertyList = domainModelPropertyService.findAll(propertyCondition);

		for(DomainModelProperty property:propertyList){
			List<TransModelProperty> list = transModelPropertyService.createProperty(property, transModel.getId());
			transModelPropertyService.add(list);
		}


	}


	public TransModel createFromDomainModel(DomainModel domainModel, String code){

		TransModel oldTransModel = new TransModel();

		//如果包含了同一个领域对象Code相同的TO，则不再创建
		TransModelCondition condition = new TransModelCondition();
		condition.setDomainModelId(domainModel.getId());
		condition.setCode(code);

		long existCount = this.transModelDao.count(condition);
		if(existCount > 0){

			oldTransModel = this.findOne(condition);

		}

		TransModel transModel = new TransModel();
		BeanUtils.copyProperties(domainModel, transModel);
		transModel.setName(domainModel.getName());
		transModel.setCode(code);
		transModel.setDomainModelId(domainModel.getId());
		transModel.setDomainModelName(domainModel.getName());
		transModel.setDescription(domainModel.getDescription());
		transModel.setViewIndex(0);
		transModel.setMemo(domainModel.getDescription());
		transModel.setId(null);

		if(existCount > 0){
			transModel.setId(oldTransModel.getId());
			this.merge(transModel);
		}else{
			this.add(transModel);
		}

		//清空历史数据
		TransModelPropertyCondition propertyCondition = new TransModelPropertyCondition();
		propertyCondition.setTransModelId(transModel.getId());
		List<TransModelProperty> transModelPropertyList = transModelPropertyService.findAll(propertyCondition);
		transModelPropertyService.delete(transModelPropertyList);

		//插入新数据

		DomainModelPropertyCondition domainModelPropertyCondition = new DomainModelPropertyCondition();
		domainModelPropertyCondition.setDomainModelId(domainModel.getId());
		List<DomainModelProperty> propertyList = domainModelPropertyService.findAll(domainModelPropertyCondition);

		for(DomainModelProperty property:propertyList){
			TransModelProperty transModelProperty = new TransModelProperty();
			BeanUtils.copyProperties(property, transModelProperty);
			transModelProperty.setDomainModelId(domainModel.getId());
			transModelProperty.setDomainModelPropertyId(property.getId());
			transModelProperty.setTransModelId(transModel.getId());

			transModelProperty.setCoreRelation(true);

			transModelProperty.setId(null);
			transModelPropertyService.add(transModelProperty);
		}

		return transModel;
	}

	/**
	 * 创建查询条件类
	 * @param domainModel
	 * @param code
	 * @return
	 */
	public TransModel createSearchTOFromDomainModel(DomainModel domainModel, String code){

		TransModel oldTransModel = new TransModel();

		//如果包含了同一个领域对象Code相同的TO，则不再创建
		TransModelCondition condition = new TransModelCondition();
		condition.setDomainModelId(domainModel.getId());
		condition.setCode(code);

		long existCount = this.transModelDao.count(condition);
		if(existCount > 0){
			oldTransModel = this.findOne(condition);

		}

		TransModel transModel = new TransModel();
		BeanUtils.copyProperties(domainModel, transModel);
		transModel.setName(domainModel.getName());
		transModel.setCode(code);
		transModel.setDomainModelId(domainModel.getId());
		transModel.setDomainModelName(domainModel.getName());
		transModel.setDescription(domainModel.getDescription());
		transModel.setViewIndex(0);
		transModel.setMemo(domainModel.getDescription());
		transModel.setId(null);
		if(existCount > 0){
			transModel.setId(oldTransModel.getId());
			this.merge(transModel);
		}else{
			this.add(transModel);
		}

		//清空历史数据
		TransModelPropertyCondition propertyCondition = new TransModelPropertyCondition();
		propertyCondition.setTransModelId(transModel.getId());
		List<TransModelProperty> transModelPropertyList = transModelPropertyService.findAll(propertyCondition);
		transModelPropertyService.delete(transModelPropertyList);

		//插入新数据

		DomainModelPropertyCondition domainModelPropertyCondition = new DomainModelPropertyCondition();
		domainModelPropertyCondition.setDomainModelId(domainModel.getId());
		List<DomainModelProperty> domainModelPropertyList = domainModelPropertyService.findAll(domainModelPropertyCondition);

		for(DomainModelProperty domainModelProperty : domainModelPropertyList){
			List<TransModelProperty> transModelPropertyList1 = transModelPropertyService.createRangeProperty(domainModelProperty, transModel.getId());

			transModelPropertyService.add(transModelPropertyList1);
		}

		return transModel;
	}



	/**
	 * 查找给定模块或领域对象同一系统下的所有对象
	 * @param moduleId 模块ID
	 * @param domainModelId 对象ID
	 * @return
	 */
	public List<TransModel> findSystemDomainModel(String moduleId, String domainModelId){


		String targetDomainId = moduleId;
		if(moduleId == null){
			TransModel rootModel = this.find(domainModelId);
			targetDomainId = rootModel.getModuleId();
		}

		return this.findSystemDomainModel(targetDomainId);
	}

	/**
	 * 根据给出的domainId查出相同project下的所有对象
	 * @return
	 */
	public List<TransModel> findSystemDomainModel(String moduleId){

		Module domain = moduleService.find(moduleId);
		ModuleCondition condition = new ModuleCondition();
		condition.setSystemId(domain.getSystemId());
		List<Module> domainList = moduleService.findAll(condition);

		List<TransModel> result = new ArrayList<>();

		for(Module d : domainList){
			TransModelCondition rcondition = new TransModelCondition();
			rcondition.setModuleId(d.getId());
			List<TransModel> modelList = this.findAll(rcondition);
			result.addAll(modelList);
		}

		return result;
	}



}