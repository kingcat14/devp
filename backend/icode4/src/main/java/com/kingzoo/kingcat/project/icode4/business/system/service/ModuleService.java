package com.kingzoo.kingcat.project.icode4.business.system.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.system.dao.ModuleDao;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
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


@Service("moduleService")
public class ModuleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(Module.class);

	@Autowired
	@Qualifier("moduleDao")
	private ModuleDao moduleDao;

	@Autowired
	private DomainModelService domainModelService;

	@Transactional
	public void add(Module module){
		module.setId(IDGenerator.get());
		changeProject(module);
		moduleDao.insert(module);
	}

	@Transactional
	public void add(List<Module> moduleList){
		for(Module module:moduleList){
			this.add(module);
		}
	}

	public Module copy(String moduleId){
		Module oldModule = moduleDao.findOne(moduleId);
		if(oldModule == null){
			return null;
		}
		Module module = new Module();
		BeanUtils.copyProperties(oldModule, module);
		module.setCode("COPY_"+oldModule.getCode());
		module.setName("COPY_"+oldModule.getName());
		module.setId(null);
		this.add(module);

		//复制领域对象
		List<DomainModel> domainModelList = domainModelService.findDomainModelByModuleId(oldModule.getId());
		for(DomainModel target : domainModelList){
			DomainModel domainModel = domainModelService.copy(target.getId());
			domainModel.setModuleId(module.getId());
			domainModel.setModuleName(module.getName());
			domainModel.setSystemId(module.getSystemId());
			domainModel.setSystemName(module.getSystemName());
			domainModel.setName(domainModel.getName().replaceFirst("COPY_", ""));
			domainModel.setCode(domainModel.getCode().replaceFirst("COPY_", ""));
			domainModelService.merge(domainModel);
		}

		//复制下级模块
		List<Module> subModule1Lst = this.findSubModule(oldModule.getId());
		for(Module subModuleTarget:subModule1Lst){
			Module subModule = copy(subModuleTarget.getId());
			subModule.setParentModuleId(module.getId());
			subModule.setParentModuleName(module.getName());
			subModule.setName(subModule.getName().replaceFirst("COPY_", ""));
			subModule.setCode(subModule.getCode().replaceFirst("COPY_", ""));
			this.merge(subModule);
		}

		return module;
	}

	/**
	 * 查询模块的直接子模块
	 * @param parentModuleId
	 * @return
	 */
	public List<Module> findSubModule(String parentModuleId){
		List<Module> result = new ArrayList<>();

		ModuleCondition condition = new ModuleCondition();
		condition.setParentModuleId(parentModuleId);
		List<Module> subModuleList= this.findAll(condition);
		if(CollectionUtils.isNotEmpty(subModuleList)){
			result.addAll(subModuleList);
		}
		return result;
	}
	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete Module by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete module:{}", id);
		moduleDao.delete(id);
		//删除模块下的所有对象
		domainModelService.deleteDomainModelForModule(id);

		//如果有子模块，则删除子模块
		ModuleCondition condition = new ModuleCondition();
		condition.setParentModuleId(id);
		List<Module> childModuleList = this.moduleDao.findAll(condition);
		if(CollectionUtils.isNotEmpty(childModuleList)) {
			for (Module childModule : childModuleList) {
				this.delete(childModule.getId());
			}
		}
	}

	@Transactional
	public void delete(List<Module> modules){
		for(Module module: modules) {
			delete(module.getId());
		}
	}

	@Transactional
	public void merge(Module module){
		changeProject(module);
		moduleDao.save(module);
	}

	@Transactional
	public void merge(List<Module> moduleList){
		for(Module module:moduleList){
			this.merge(module);
		}
	}

	@Transactional(readOnly=true)
	public Module find(String id){
	    Module module = null;
		if(StringUtils.isNotEmpty(id)) {
            module = moduleDao.findOne(id);
        }
		return module;
	}

	/**
	 * 查询模块的详细信息(code的级联关系)
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Module findDetail(String id){
		Module module = null;
		if(StringUtils.isNotEmpty(id)) {
			module = moduleDao.findOne(id);
		}

		Module current = module;
		while(current != null &&  current.getParentModuleId()!=null){
			current = moduleDao.findOne(module.getParentModuleId());
			//module.setCode(current.getCode()+"."+module.getCode());
			module.setSystemId(current.getSystemId());
			module.setSystemName(current.getSystemName());
		}

		return module;
	}

	public String findModuleCodePath(String id){

		String result = "";
		Module current = null;
		if(StringUtils.isNotEmpty(id)) {
			current = moduleDao.findOne(id);
			result = current.getCode();
		}
		while(current != null &&  current.getParentModuleId()!=null){
			current = moduleDao.findOne(current.getParentModuleId());
			//module.setCode(current.getCode()+"."+module.getCode());
			result = current.getCode()+"."+result;
		}

		return result;
	}

	public String findModuleNamePath(String id){

		String result = "";
		Module current = null;
		if(StringUtils.isNotEmpty(id)) {
			current = moduleDao.findOne(id);
			result = current.getName();
		}
		while(current != null &&  current.getParentModuleId()!=null){
			current = moduleDao.findOne(current.getParentModuleId());
			//module.setCode(current.getCode()+"."+module.getCode());
			result = current.getName()+"."+result;
		}

		return result;
	}

	/**
	 * 找到最上层的Module
	 * @param id 当前module的id
	 * @return
	 */
	public Module findTopModule(String id){

		Module current = null;
		if(StringUtils.isNotEmpty(id)) {
			current = moduleDao.findOne(id);
		}
		while(current != null &&  current.getParentModuleId()!=null){
			current = moduleDao.findOne(current.getParentModuleId());
			//module.setCode(current.getCode()+"."+module.getCode());
		}

		return current;
	}

	@Transactional(readOnly=true)
	public Module findOne(ModuleCondition moduleCondition){

		List<Module> moduleList = moduleDao.findAll(moduleCondition);

		Module result = null;
		if(CollectionUtils.isNotEmpty(moduleList)){
			result = moduleList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<Module> findAll(ModuleCondition moduleCondition){

		List<Module> moduleList = moduleDao.findAll(moduleCondition, getDefaultSort());

		return moduleList;
	}

	@Transactional(readOnly=true)
	public Page<Module> find(Pageable pageable){
		Page<Module> moduleList = moduleDao.findAll(pageable, null, getDefaultSort());
		return moduleList;
	}

	@Transactional(readOnly=true)
	public Page<Module> find(Pageable pageable, ModuleCondition moduleCondition){
		Page<Module> moduleList = moduleDao.findAll(pageable, moduleCondition, getDefaultSort());
		return moduleList;
	}

	@Transactional(readOnly=true)
	public long count(ModuleCondition moduleCondition){
		long count = moduleDao.count(moduleCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , Module.PROPERTY_NAME);
		return sort;
	}


	private void changeProject(Module module){

		//如果有父模块, 则将项目ID设置为空
		if(StringUtils.isNotEmpty(module.getParentModuleId())){
			module.setSystemId(null);
		}

		//如果有项目ID, 则将副模块ID设置为空
		if(StringUtils.isNotEmpty(module.getSystemId())){
			module.setParentModuleId(null);
		}
	}

	public void deleteBySystemId(String systemId){

		if(StringUtils.isEmpty(systemId)){
			return;
		}

		ModuleCondition moduleCondition = new ModuleCondition();
		moduleCondition.setSystemId(systemId);
		List<Module> moduleList = moduleDao.findAll(moduleCondition);
		for(Module module : moduleList){
			moduleDao.delete(module.getId());
		}
	}



	public List<Module> findAllTopModule(String projectId){
		return this.moduleDao.findTopModule(projectId);
	}



}