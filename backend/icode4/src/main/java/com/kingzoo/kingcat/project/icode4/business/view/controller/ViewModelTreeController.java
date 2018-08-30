package com.kingzoo.kingcat.project.icode4.business.view.controller;

import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.DomainModelTreeNode;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModuleService;
import com.kingzoo.kingcat.project.icode4.business.system.service.SystemService;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ModuleCondition;
import com.kingzoo.kingcat.project.icode4.business.view.controller.vo.ViewModelTreeNode;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModel;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModelProperty;
import com.kingzoo.kingcat.project.icode4.business.view.service.ViewModelPropertyService;
import com.kingzoo.kingcat.project.icode4.business.view.service.ViewModelService;
import com.kingzoo.kingcat.project.icode4.business.view.vo.ViewModelCondition;
import com.kingzoo.kingcat.project.icode4.business.view.vo.ViewModelPropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理业务对象定义
 */
@RestController
@RequestMapping(value = "/view/viewModelTree")
public class ViewModelTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewModelTreeController.class);

	@Autowired
	private ViewModelService domainModelService;

	@Autowired
	private SystemService systemService;
	@Autowired
	private ModuleService moduleService;

	@Autowired
	private ViewModelPropertyService domainModelPropertyService;



	/**
	 * Method getChildNodes.
	 * @param type String
	 * @param id int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping("/{id}")
	public
	List<ViewModelTreeNode> getChildNodes(
			@RequestParam(required=false,value="type") String type,
			@RequestParam(required=false,value="id", defaultValue = "-1") String id
	        ,@PathVariable(name="id") String nodeId){


		List<ViewModelTreeNode> result = new ArrayList<>();


		if(StringUtils.isEmpty(type)){
			result.addAll(this.findSystemNode());
		}

		if(StringUtils.equals(DomainModelTreeNode.TYPE_SYSTEM, type)){
			result.addAll(this.findTopModuleNode(nodeId));
		}

		if(StringUtils.equals(DomainModelTreeNode.TYPE_MODULE, type)){
			result.addAll(this.findSubModuleNode(nodeId));
			result.addAll(this.findViewModelNode(nodeId));
		}

		if(StringUtils.equals(DomainModelTreeNode.TYPE_DOMAIN_MODEL, type)){
			result.addAll(this.findPropertyNode(nodeId));
		}

		return result;
	}

	/**
	 * 查找所有系统
	 * @return
	 */
	private List<ViewModelTreeNode> findSystemNode(){
		List<System> list = systemService.findAll(null);

		List<ViewModelTreeNode> result = new ArrayList<>();
		for(System system : list){
			ViewModelTreeNode node = new ViewModelTreeNode(system);
			result.add(node);
		}

		return result;
	}

	private List<ViewModelTreeNode> findTopModuleNode(String systemId){

		List<Module> list = moduleService.findAllTopModule(systemId);

		List<ViewModelTreeNode> result = new ArrayList<>();
		for(Module module : list){
			ViewModelTreeNode node = new ViewModelTreeNode(module);
			result.add(node);
		}

		return result;
	}

	private List<ViewModelTreeNode> findSubModuleNode(String moduleId){

		ModuleCondition condition = new ModuleCondition();
		condition.setParentModuleId(moduleId);

		List<Module> list = moduleService.findAll(condition);

		List<ViewModelTreeNode> result = new ArrayList<>();
		for(Module module : list){
			ViewModelTreeNode node = new ViewModelTreeNode(module);
			result.add(node);
		}

		return result;
	}


	private List<ViewModelTreeNode> findViewModelNode(String moduleId){
		ViewModelCondition searchDto = new ViewModelCondition();
		searchDto.setModuleId(moduleId);

		List<ViewModel> list = domainModelService.findAll(searchDto);

		List<ViewModelTreeNode> result = new ArrayList<>();
		for(ViewModel domainModel : list){
			ViewModelTreeNode node = new ViewModelTreeNode(domainModel);
			result.add(node);
		}

		return result;
	}

	private List<ViewModelTreeNode> findPropertyNode(String domainModelId){
		ViewModelPropertyCondition searchDto = new ViewModelPropertyCondition();
		searchDto.setDomainModelId(domainModelId);

		List<ViewModelProperty> list = domainModelPropertyService.findAll(searchDto);

		List<ViewModelTreeNode> result = new ArrayList<>();
		for(ViewModelProperty property : list){
			ViewModelTreeNode node = new ViewModelTreeNode(property);
			result.add(node);
		}

		return result;
	}

}