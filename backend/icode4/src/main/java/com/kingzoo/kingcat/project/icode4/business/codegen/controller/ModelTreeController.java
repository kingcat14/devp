package com.kingzoo.kingcat.project.icode4.business.codegen.controller;

import com.kingzoo.kingcat.project.icode4.business.codegen.controller.vo.ModelTreeNode;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.DomainModelTreeNode;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Product;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModuleService;
import com.kingzoo.kingcat.project.icode4.business.system.service.ProductService;
import com.kingzoo.kingcat.project.icode4.business.system.service.SystemService;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ModuleCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.SystemCondition;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModel;
import com.kingzoo.kingcat.project.icode4.business.view.service.ViewModelService;
import com.kingzoo.kingcat.project.icode4.business.view.vo.ViewModelCondition;
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
@RequestMapping(value = "/codegen/modelTree")
public class ModelTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelTreeController.class);

	@Autowired
	private DomainModelService domainModelService;

	@Autowired
	private ProductService productService;
	@Autowired
	private SystemService systemService;

	@Autowired
	private ModuleService moduleService;



	/**
	 * Method getChildNodes.
	 * @param type String
	 * @param id int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping("/{id}")
	public
	List<ModelTreeNode> getChildNodes(
			@RequestParam(required=false,value="type") String type,
			@RequestParam(required=false,value="id", defaultValue = "-1") String id
	        ,@PathVariable(name="id") String nodeId){


		List<ModelTreeNode> result = new ArrayList<>();


		if(StringUtils.isEmpty(type)){
			result.addAll(this.findProductNode());
		}
		if(StringUtils.equals(DomainModelTreeNode.TYPE_PRODUCT, type)){
			result.addAll(this.findSystemNode(nodeId));
		}
//		if(StringUtils.isEmpty(type)){
//			result.addAll(this.findSystemNode());
//		}

		if(StringUtils.equals(ModelTreeNode.TYPE_SYSTEM, type)){
			result.addAll(this.findTopModuleNode(nodeId));
		}

		if(StringUtils.equals(ModelTreeNode.TYPE_MODULE, type)){
			result.addAll(this.findSubModuleNode(nodeId));
			result.addAll(this.findDomainModelNode(nodeId));
			//result.addAll(this.findViewModelNode(nodeId));
		}

		return result;
	}

	/**
	 * 查找所有产品
	 * @return
	 */
	private List<ModelTreeNode> findProductNode(){
		List<Product> list = productService.findAll(null);

		List<ModelTreeNode> result = new ArrayList<>();
		for(Product product : list){
			ModelTreeNode node = new ModelTreeNode(product);
			result.add(node);
		}

		return result;
	}
	/**
	 * 查找所有系统
	 * @return
	 */
	private List<ModelTreeNode> findSystemNode(String productId){

		SystemCondition systemCondition = new SystemCondition();
		systemCondition.setProductId(productId);

		List<System> list = systemService.findAll(systemCondition);

		List<ModelTreeNode> result = new ArrayList<>();
		for(System system : list){
			ModelTreeNode node = new ModelTreeNode(system);
			result.add(node);
		}

		return result;
	}
	/**
	 * 查找所有系统
	 * @return
	 */
	private List<ModelTreeNode> findSystemNode(){
		List<System> list = systemService.findAll(null);

		List<ModelTreeNode> result = new ArrayList<>();
		for(System system : list){
			ModelTreeNode node = new ModelTreeNode(system);
			result.add(node);
		}

		return result;
	}

	private List<ModelTreeNode> findTopModuleNode(String systemId){

		List<Module> list = moduleService.findAllTopModule(systemId);

		List<ModelTreeNode> result = new ArrayList<>();
		for(Module module : list){
			ModelTreeNode node = new ModelTreeNode(module);
			result.add(node);
		}

		return result;
	}

	private List<ModelTreeNode> findSubModuleNode(String moduleId){

		ModuleCondition condition = new ModuleCondition();
		condition.setParentModuleId(moduleId);

		List<Module> list = moduleService.findAll(condition);

		List<ModelTreeNode> result = new ArrayList<>();
		for(Module module : list){
			ModelTreeNode node = new ModelTreeNode(module);
			result.add(node);
		}

		return result;
	}

	private List<ModelTreeNode> findDomainModelNode(String moduleId){
		DomainModelCondition searchDto = new DomainModelCondition();
		searchDto.setModuleId(moduleId);

		List<DomainModel> list = domainModelService.findAll(searchDto);

		List<ModelTreeNode> result = new ArrayList<>();
		for(DomainModel domainModel : list){
			ModelTreeNode node = new ModelTreeNode(domainModel);
			result.add(node);
		}

		return result;
	}




}