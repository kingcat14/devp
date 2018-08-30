package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.DomainModelAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModelTypeService;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModuleService;
import com.kingzoo.kingcat.project.icode4.business.system.service.SystemService;
import com.kingzoo.kingcat.project.icode4.business.system.valid.DomainModelValidator;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelVO;
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
 * 管理领域对象
 * @author icode
 */
@RestController
@RequestMapping(value = "/system/domainModel")
public class DomainModelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainModelController.class);


	@Autowired
	private DomainModelService domainModelService;

	@Autowired
	private ModelTypeService modelTypeService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private SystemService systemService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DomainModelValidator());
	}

	/**
	 * 新增领域对象
	 * @param domainModelAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DomainModelVO add(@RequestBody @Valid DomainModelAddRequest domainModelAddRequest){
		DomainModel domainModel = new DomainModel();
		BeanUtils.copyProperties(domainModelAddRequest, domainModel);

		domainModelService.add(domainModel);

		return  initViewProperty(domainModel);
	}

	/**
	 * 删除领域对象,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete domainModel :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			domainModelService.delete(id);
		}

	}

	/**
	 * 更新领域对象
	 * @param domainModel
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public DomainModel update(@RequestBody @Valid DomainModel domainModel, @PathVariable String id){
		domainModel.setId(id);
		domainModelService.merge(domainModel);

		initViewProperty(domainModel);
		return  domainModel;
	}

	/**
	 * 复制领域对象
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}/copy")
	public DomainModel copy(@PathVariable String id){

		DomainModel domainModel = domainModelService.copy(id);
		initViewProperty(domainModel);
		return  domainModel;
	}

	/**
	 * 根据ID查询领域对象
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public DomainModel get(@PathVariable String id) {

		DomainModel domainModel = domainModelService.find(id);

		initViewProperty(domainModel);
		return domainModel;
	}

	/**
	 * 查询领域对象列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<DomainModelVO> list(@RequestBody PageSearchRequest<DomainModelCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<DomainModel> page = domainModelService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<DomainModelVO> voList = new ArrayList<>();
		for(DomainModel domainModel : page.getContent()){
			voList.add(initViewProperty(domainModel));
		}

		PageContent<DomainModelVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DomainModelVO initViewProperty(DomainModel domainModel){
	    DomainModelVO vo = new DomainModelVO();

        BeanUtils.copyProperties(domainModel, vo);

	    //初始化其他对象
		initModuleProperty(vo, domainModel);

		initSystemProperty(vo, domainModel);

        return vo;
	}


	
	private void initModuleProperty(DomainModelVO domainModelVO, DomainModel domainModel){
		if(StringUtils.isEmpty(domainModel.getModuleId())){
			return ;
		}
		//初始化其他对象
		Module module = moduleService.find(domainModel.getModuleId());
        if(module!=null) {
            domainModelVO.setModuleName(module.getName());
        }
	}
	
	private void initSystemProperty(DomainModelVO domainModelVO, DomainModel domainModel){
		if(StringUtils.isEmpty(domainModel.getSystemId())){
			return ;
		}
		//初始化其他对象
		System system = systemService.find(domainModel.getSystemId());
        if(system!=null) {
            domainModelVO.setSystemName(system.getName());
        }
	}
	

}
