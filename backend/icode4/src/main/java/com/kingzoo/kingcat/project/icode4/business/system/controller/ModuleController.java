package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.ModuleAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModuleService;
import com.kingzoo.kingcat.project.icode4.business.system.service.SystemService;
import com.kingzoo.kingcat.project.icode4.business.system.valid.ModuleValidator;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ModuleCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ModuleVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理模块
 * @author icode
 */
@RestController
@RequestMapping(value = "/system/module")
public class ModuleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModuleController.class);

	@Autowired
	private ModuleService moduleService;
	@Autowired
	private SystemService systemService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new ModuleValidator());
	}

	/**
	 * 新增模块
	 * @param moduleAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ModuleVO add(@RequestBody @Valid ModuleAddRequest moduleAddRequest){
		Module module = new Module();
		BeanUtils.copyProperties(moduleAddRequest, module);

		moduleService.add(module);

		return  initViewProperty(module);
	}

	/**
	 * 删除模块,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete module :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			moduleService.delete(id);
		}

	}

	/**
	 * 更新模块
	 * @param module
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public Module update(@RequestBody @Valid Module module, @PathVariable String id){
		module.setId(id);
		moduleService.merge(module);

		initViewProperty(module);
		return  module;
	}

	/**
	 * 复制领域对象
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}/copy")
	public Module copy(@PathVariable String id){

		Module module = moduleService.copy(id);
		initViewProperty(module);
		return  module;
	}

	/**
	 * 根据ID查询模块
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public Module get(@PathVariable String id) {

		Module module = moduleService.find(id);

		initViewProperty(module);
		return module;
	}

	/**
	 * 查询模块列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<ModuleVO> list(@RequestBody PageSearchRequest<ModuleCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<Module> page = moduleService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<ModuleVO> voList = new ArrayList<>();
		for(Module module : page.getContent()){
			voList.add(initViewProperty(module));
		}

		PageContent<ModuleVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private ModuleVO initViewProperty(Module module){
	    ModuleVO vo = new ModuleVO();

        BeanUtils.copyProperties(module, vo);

	    //初始化其他对象
		initParentModuleProperty(vo, module);

		initSystemProperty(vo, module);

        return vo;
	}



	private void initParentModuleProperty(ModuleVO moduleVO, Module module){
		if(StringUtils.isEmpty(module.getParentModuleId())){
			return ;
		}
		//初始化其他对象
		Module parentModule = moduleService.find(module.getParentModuleId());
        if(parentModule!=null) {
            moduleVO.setParentModuleName(parentModule.getName());
        }
	}
	
	private void initSystemProperty(ModuleVO moduleVO, Module module){
		if(StringUtils.isEmpty(module.getSystemId())){
			return ;
		}
		//初始化其他对象
		System system = systemService.find(module.getSystemId());
        if(system!=null) {
            moduleVO.setSystemName(system.getName());
        }
	}
	

}
