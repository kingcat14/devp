package com.kingzoo.kingcat.project.icode4.business.microservice.controller;

import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.MicroServiceTreeNode;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroService;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfc;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModel;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.MicroServiceItfcService;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.MicroServiceService;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.TransModelService;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceCondition;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceItfcCondition;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModuleService;
import com.kingzoo.kingcat.project.icode4.business.system.service.SystemService;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ModuleCondition;
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
@RequestMapping(value = "/microservice/microServiceTree")
public class MicroServiceTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroServiceTreeController.class);



	@Autowired
	private SystemService systemService;
	@Autowired
	private ModuleService moduleService;

	@Autowired
	private MicroServiceService microServiceService;

	@Autowired
	private MicroServiceItfcService microServiceItfcService;

	@Autowired
	private TransModelService transModelService;




	/**
	 * Method getChildNodes.
	 * @param type String
	 * @param id int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping("/{id}")
	public
	List<MicroServiceTreeNode> getChildNodes(
			@RequestParam(required=false,value="type") String type,
			@RequestParam(required=false,value="id", defaultValue = "-1") String id
	        ,@PathVariable(name="id") String nodeId){


		List<MicroServiceTreeNode> result = new ArrayList<>();


		if(StringUtils.isEmpty(type)){
			result.addAll(this.findSystemNode());
		}

		if(StringUtils.equals(MicroServiceTreeNode.TYPE_SYSTEM, type)){
			result.addAll(this.findTopModuleNode(nodeId));
		}

		if(StringUtils.equals(MicroServiceTreeNode.TYPE_MODULE, type)){
			result.addAll(this.findSubModuleNode(nodeId));
			result.addAll(this.findMicroServiceNode(nodeId));
		}

		if(StringUtils.equals(MicroServiceTreeNode.TYPE_MICROSERVICE, type)){
			result.addAll(this.findItfcNode(nodeId));
		}

		if(StringUtils.equals(MicroServiceTreeNode.TYPE_MICROSERVICE_ITFC, type)){
			result.addAll(this.findTransModelNode(nodeId));
		}
		return result;
	}

	/**
	 * 查找所有系统
	 * @return
	 */
	private List<MicroServiceTreeNode> findSystemNode(){
		List<System> list = systemService.findAll(null);

		List<MicroServiceTreeNode> result = new ArrayList<>();
		for(System system : list){
			MicroServiceTreeNode node = new MicroServiceTreeNode(system);
			result.add(node);
		}

		return result;
	}

	private List<MicroServiceTreeNode> findTopModuleNode(String systemId){

		List<Module> list = moduleService.findAllTopModule(systemId);

		List<MicroServiceTreeNode> result = new ArrayList<>();
		for(Module module : list){
			MicroServiceTreeNode node = new MicroServiceTreeNode(module);
			result.add(node);
		}

		return result;
	}

	private List<MicroServiceTreeNode> findSubModuleNode(String moduleId){

		ModuleCondition condition = new ModuleCondition();
		condition.setParentModuleId(moduleId);

		List<Module> list = moduleService.findAll(condition);

		List<MicroServiceTreeNode> result = new ArrayList<>();
		for(Module module : list){
			MicroServiceTreeNode node = new MicroServiceTreeNode(module);
			result.add(node);
		}

		return result;
	}


	private List<MicroServiceTreeNode> findMicroServiceNode(String moduleId){
		MicroServiceCondition searchDto = new MicroServiceCondition();
		searchDto.setModuleId(moduleId);

		List<MicroService> list = microServiceService.findAll(searchDto);

		List<MicroServiceTreeNode> result = new ArrayList<>();
		for(MicroService microService : list){
			MicroServiceTreeNode node = new MicroServiceTreeNode(microService);
			result.add(node);
		}

		return result;
	}

	private List<MicroServiceTreeNode> findItfcNode(String microServiceId){
		MicroServiceItfcCondition searchDto = new MicroServiceItfcCondition();
		searchDto.setMicroServiceId(microServiceId);

		List<MicroServiceItfc> list = microServiceItfcService.findAll(searchDto);

		List<MicroServiceTreeNode> result = new ArrayList<>();
		for(MicroServiceItfc microServiceItfc : list){
			MicroServiceTreeNode node = new MicroServiceTreeNode(microServiceItfc);
			result.add(node);
		}

		return result;
	}

	private List<MicroServiceTreeNode> findTransModelNode(String microServiceItfcId){

		List<MicroServiceTreeNode> result = new ArrayList<>();

		MicroServiceItfc microServiceItfc = microServiceItfcService.find(microServiceItfcId);

		String requestTransModelId = microServiceItfc.getRequestId();
		if(StringUtils.isNotEmpty(requestTransModelId)) {
			TransModel requestTransModel = transModelService.find(requestTransModelId);
			if(null != requestTransModel){
				MicroServiceTreeNode node = new MicroServiceTreeNode(microServiceItfcId, "request", requestTransModel);
				node.setName("(Requ)"+node.getName());
				result.add(node);
			}else{
				microServiceItfc.setRequestId(null);
				microServiceItfcService.merge(microServiceItfc);
			}

		}

		String responseTransModelId = microServiceItfc.getResponseId();
		if(StringUtils.isNotEmpty(responseTransModelId)){
			TransModel responseTransModel = transModelService.find(responseTransModelId);
			if(null != responseTransModel) {
				MicroServiceTreeNode node = new MicroServiceTreeNode(microServiceItfcId, "response", responseTransModel);
				node.setName("(Resp)" + node.getName());
				result.add(node);
			}else{
				microServiceItfc.setResponseId(null);
				microServiceItfcService.merge(microServiceItfc);
			}
		}

		return result;
	}

}