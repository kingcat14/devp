package com.kingzoo.kingcat.project.icode4.business.system.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.system.dao.DomainModelDao;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelCondition;
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


@Service("domainModelService")
public class DomainModelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainModel.class);


	@Autowired
	private ModuleService moduleService;

	@Autowired
	@Qualifier("domainModelDao")
	private DomainModelDao domainModelDao;

	@Autowired
	private DomainModelPropertyService domainModelPropertyService;

	@Transactional(rollbackFor = Exception.class)
	public void add(DomainModel domainModel){
		domainModel.setId(IDGenerator.get());
		domainModelDao.insert(domainModel);
	}

	public DomainModel copy(String id){
		DomainModel oldModel = this.find(id);
		DomainModel domainModel = new DomainModel();
		BeanUtils.copyProperties(oldModel, domainModel);
		domainModel.setName("COPY_"+domainModel.getName());
		domainModel.setCode("COPY_"+domainModel.getCode());
		this.add(domainModel);

		DomainModelPropertyCondition propertyCondition = new DomainModelPropertyCondition();
		propertyCondition.setDomainModelId(id);
		List<DomainModelProperty> propertyList = domainModelPropertyService.findAll(propertyCondition);

		for(DomainModelProperty property : propertyList){
			DomainModelProperty newProperty = new DomainModelProperty();
			BeanUtils.copyProperties(property, newProperty);
			newProperty.setDomainModelId(domainModel.getId()+"");
			newProperty.setId(null);
			domainModelPropertyService.add(newProperty);
		}

		return domainModel;
	}

	@Transactional(rollbackFor = Exception.class)
	public void add(List<DomainModel> domainModelList){
		for(DomainModel domainModel:domainModelList){
			this.add(domainModel);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete DomainModel by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete domainModel:{}", id);
		domainModelDao.delete(id);

		//删除对象的所有属性
		domainModelPropertyService.deletePropertyForModel(id);

	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(List<DomainModel> domainModels){
		for(DomainModel domainModel: domainModels) {
			delete(domainModel.getId());
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void merge(DomainModel domainModel){
		domainModelDao.save(domainModel);
	}

	@Transactional(rollbackFor = Exception.class)
	public void merge(List<DomainModel> domainModelList){
		for(DomainModel domainModel:domainModelList){
			this.merge(domainModel);
		}
	}

	@Transactional(readOnly=true, rollbackFor = Exception.class)
	public DomainModel find(String id){
	    DomainModel domainModel = null;
		if(StringUtils.isNotEmpty(id)) {
            domainModel = domainModelDao.findOne(id);
        }

		return domainModel;
	}

	@Transactional(readOnly=true, rollbackFor = Exception.class)
	public DomainModel findOne(DomainModelCondition domainModelCondition){

		List<DomainModel> domainModelList = domainModelDao.findAll(domainModelCondition);

		DomainModel result = null;
		if(CollectionUtils.isNotEmpty(domainModelList)){
			result = domainModelList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true, rollbackFor = Exception.class)
	public List<DomainModel> findAll(DomainModelCondition domainModelCondition){

		List<DomainModel> domainModelList = domainModelDao.findAll(domainModelCondition, getDefaultSort());

		return domainModelList;
	}

	@Transactional(readOnly=true, rollbackFor = Exception.class)
	public Page<DomainModel> find(Pageable pageable){
		Page<DomainModel> domainModelList = domainModelDao.findAll(pageable, null, getDefaultSort());
		return domainModelList;
	}

	@Transactional(readOnly=true, rollbackFor = Exception.class)
	public Page<DomainModel> find(Pageable pageable, DomainModelCondition domainModelCondition){
		Page<DomainModel> domainModelList = domainModelDao.findAll(pageable, domainModelCondition, getDefaultSort());
		return domainModelList;
	}

	@Transactional(readOnly=true, rollbackFor = Exception.class)
	public long count(DomainModelCondition domainModelCondition){
		long count = domainModelDao.count(domainModelCondition);
		return count;
	}


	public void deleteDomainModelForModule(String moduleId){
		DomainModelCondition condition = new DomainModelCondition();
		condition.setModuleId(moduleId);
		List<DomainModel> domainModelList = this.domainModelDao.findAll(condition);
		for(DomainModel domainModel : domainModelList){
			this.delete(domainModel.getId());

		}
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC , DomainModel.PROPERTY_VIEW_INDEX, DomainModel.PROPERTY_NAME);
		return sort;
	}



	/**
	 * 查找给定模块或领域对象同一系统下的所有对象
	 * @param moduleId 模块ID
	 * @param domainModelId 对象ID
	 * @return
	 */
	public List<DomainModel> findSystemDomainModel(String moduleId, String domainModelId){


		String targetDomainId = moduleId;
		if(moduleId == null){
			DomainModel rootModel = this.find(domainModelId);
			targetDomainId = rootModel.getModuleId();
		}

		return this.findSystemDomainModel(targetDomainId);
	}

	/**
	 * 根据给出的domainId查出相同project下的所有对象
	 * @return
	 */
	public List<DomainModel> findSystemDomainModel(String moduleId){

		List<DomainModel> result = new ArrayList<>();

		/**先得到最上级的模块*/
		Module topModule = moduleService.findTopModule(moduleId);

		//得到
		ModuleCondition moduleCondition = new ModuleCondition();
		moduleCondition.setSystemId(topModule.getSystemId());
		List<Module> moduleList = moduleService.findAll(moduleCondition);
		for(Module module : moduleList){
			result.addAll(this.findModuleDomainModelCascade(module));
		}



		Module domain = moduleService.find(moduleId);
		ModuleCondition condition = new ModuleCondition();
		condition.setSystemId(domain.getSystemId());
		List<Module> domainList = moduleService.findAll(condition);

		for(Module d : domainList){
			DomainModelCondition rcondition = new DomainModelCondition();
			rcondition.setModuleId(d.getId());
			List<DomainModel> modelList = this.findAll(rcondition);
			result.addAll(modelList);
		}

		return result;
	}

	/**
	 * 得到模块及模块下级的模块的所有对象
	 * @param module
	 * @return
	 */
	public List<DomainModel> findModuleDomainModelCascade(Module module){

		List<DomainModel> result = new ArrayList<>();

		//得到当前模块下的对象
		DomainModelCondition condition = new DomainModelCondition();
		condition.setModuleId(module.getId());
		List<DomainModel> modelList = this.findDomainModelByModuleId(module.getId());
		result.addAll(modelList);

		//得到子模块
		ModuleCondition moduleCondition = new ModuleCondition();
		moduleCondition.setParentModuleId(module.getId());
		List<Module> moduleList = moduleService.findAll(moduleCondition);

		for(Module subModule : moduleList){
			CollectionUtils.addAll(result, findModuleDomainModelCascade(subModule));

		}

		return result;

	}

	/**
	 * 得到当前模块下的对象
	 * @param moduleId
	 * @return
	 */
	public List<DomainModel> findDomainModelByModuleId(String moduleId){
		List<DomainModel> result = new ArrayList<>();
		//得到当前模块下的对象
		DomainModelCondition condition = new DomainModelCondition();
		condition.setModuleId(moduleId);
		List<DomainModel> modelList = this.findAll(condition);
		if(CollectionUtils.isNotEmpty(modelList)) {
			result.addAll(modelList);
		}
		return result;
	}



}