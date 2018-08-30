package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.DomainModelTreeNode;
import com.kingzoo.kingcat.project.icode4.business.system.domain.*;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.system.service.*;
import com.kingzoo.kingcat.project.icode4.business.system.vo.*;
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
@RequestMapping(value = "/system/domainModelTree")
public class DomainModelTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainModelTreeController.class);

	@Autowired
	private DomainModelService domainModelService;

	@Autowired
	private DomainChildModelService domainChildModelService;

	@Autowired
	private SystemService systemService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ModuleService moduleService;

	@Autowired
	private DomainModelPropertyService domainModelPropertyService;




	/**
	 * Method getChildNodes.
	 * @param type String
	 * @param id int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping("/{id}")
	public
	List<DomainModelTreeNode> getChildNodes(
			@RequestParam(required=false,value="type") String type,
			@RequestParam(required=false,value="id", defaultValue = "-1") String id
	        ,@PathVariable(name="id") String nodeId){

		List<DomainModelTreeNode> result = new ArrayList<>();

		if(StringUtils.isEmpty(type)){
			result.addAll(this.findProductNode());
		}
		if(StringUtils.equals(DomainModelTreeNode.TYPE_PRODUCT, type)){
			result.addAll(this.findSystemNode(nodeId));
		}
		if(StringUtils.equals(DomainModelTreeNode.TYPE_SYSTEM, type)){
			result.addAll(this.findTopModuleNode(nodeId));
		}

		if(StringUtils.equals(DomainModelTreeNode.TYPE_MODULE, type)){
			result.addAll(this.findSubModuleNode(nodeId));
			result.addAll(this.findDomainModelNode(nodeId));
		}

		if(StringUtils.equals(DomainModelTreeNode.TYPE_DOMAIN_MODEL, type)){
			result.addAll(this.findDomainChildModelNode(nodeId));
			result.addAll(this.findPropertyNode(nodeId));
		}

		if(StringUtils.equals(DomainModelTreeNode.TYPE_CHILD_MODEL, type)){
			result.addAll(this.findPropertyNode(nodeId));
		}

		return result;
	}

	/**
	 * 查找所有产品
	 * @return
	 */
	private List<DomainModelTreeNode> findProductNode(){
		List<Product> list = productService.findAll(null);

		List<DomainModelTreeNode> result = new ArrayList<>();
		for(Product product : list){
			DomainModelTreeNode node = new DomainModelTreeNode(product);
			result.add(node);
		}

		return result;
	}
	/**
	 * 查找所有系统
	 * @return
	 */
	private List<DomainModelTreeNode> findSystemNode(String productId){

		SystemCondition systemCondition = new SystemCondition();
		systemCondition.setProductId(productId);

		List<System> list = systemService.findAll(systemCondition);

		List<DomainModelTreeNode> result = new ArrayList<>();
		for(System system : list){
			DomainModelTreeNode node = new DomainModelTreeNode(system);
			result.add(node);
		}

		return result;
	}

	private List<DomainModelTreeNode> findTopModuleNode(String systemId){

		List<Module> list = moduleService.findAllTopModule(systemId);

		List<DomainModelTreeNode> result = new ArrayList<>();
		for(Module module : list){
			DomainModelTreeNode node = new DomainModelTreeNode(module);
			result.add(node);
		}

		return result;
	}

	private List<DomainModelTreeNode> findSubModuleNode(String moduleId){

		ModuleCondition condition = new ModuleCondition();
		condition.setParentModuleId(moduleId);

		List<Module> list = moduleService.findAll(condition);

		List<DomainModelTreeNode> result = new ArrayList<>();
		for(Module module : list){
			DomainModelTreeNode node = new DomainModelTreeNode(module);
			result.add(node);
		}

		return result;
	}


	private List<DomainModelTreeNode> findDomainModelNode(String moduleId){
		DomainModelCondition searchDto = new DomainModelCondition();
		searchDto.setModuleId(moduleId);

		List<DomainModel> list = domainModelService.findAll(searchDto);

		List<DomainModelTreeNode> result = new ArrayList<>();
		for(DomainModel domainModel : list){
			DomainModelTreeNode node = new DomainModelTreeNode(domainModel);
			result.add(node);
		}

		return result;
	}

	private List<DomainModelTreeNode> findDomainChildModelNode(String domainModelId){
		DomainChildModelCondition searchDto = new DomainChildModelCondition();
		searchDto.setDomainModelId(domainModelId);

		List<DomainChildModel> list = domainChildModelService.findAll(searchDto);

		List<DomainModelTreeNode> result = new ArrayList<>();
		for(DomainChildModel domainModel : list){
			DomainModelTreeNode node = new DomainModelTreeNode(domainModel);
			result.add(node);
		}

		return result;
	}

	private List<DomainModelTreeNode> findPropertyNode(String domainModelId){
		DomainModelPropertyCondition searchDto = new DomainModelPropertyCondition();
		searchDto.setDomainModelId(domainModelId);

		List<DomainModelProperty> list = domainModelPropertyService.findAll(searchDto);

		List<DomainModelTreeNode> result = new ArrayList<>();
		for(DomainModelProperty property : list){
			DomainModelTreeNode node = new DomainModelTreeNode(property);
			result.add(node);
		}

		return result;
	}

}