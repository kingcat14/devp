package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.SystemAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.system.service.SystemService;
import com.kingzoo.kingcat.project.icode4.business.system.valid.SystemValidator;
import com.kingzoo.kingcat.project.icode4.business.system.vo.SystemCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.SystemVO;
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
 * 管理系统
 * @author icode
 */
@RestController
@RequestMapping(value = "/system/system")
public class SystemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);


	@Autowired
	private SystemService systemService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new SystemValidator());
	}

	/**
	 * 新增系统
	 * @param systemAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public SystemVO add(@RequestBody @Valid SystemAddRequest systemAddRequest){
		System system = new System();
		BeanUtils.copyProperties(systemAddRequest, system);

		systemService.add(system);

		return  initViewProperty(system);
	}

	/**
	 * 删除系统,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete system :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			systemService.delete(id);
		}

	}

	/**
	 * 更新系统
	 * @param system
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public SystemVO update(@RequestBody @Valid System system, @PathVariable String id){
		system.setId(id);
		systemService.merge(system);

		SystemVO vo = initViewProperty(system);
		return  vo;
	}

	/**
	 * 根据ID查询系统
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public SystemVO get(@PathVariable String id) {

		System system = systemService.find(id);

		SystemVO vo = initViewProperty(system);
		return vo;
	}

	/**
	 * 查询系统列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<SystemVO> list(@RequestBody PageSearchRequest<SystemCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<System> page = systemService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<SystemVO> voList = new ArrayList<>();
		for(System system : page.getContent()){
			voList.add(initViewProperty(system));
		}

		PageContent<SystemVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private SystemVO initViewProperty(System system){
	    SystemVO vo = new SystemVO();

        BeanUtils.copyProperties(system, vo);

	    //初始化其他对象
        return vo;
	}




}
