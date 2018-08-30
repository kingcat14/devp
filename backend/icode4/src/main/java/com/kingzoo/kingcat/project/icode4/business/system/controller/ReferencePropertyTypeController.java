package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.domain.PropertyType;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModuleService;
import com.kingzoo.kingcat.project.icode4.business.system.service.PropertyTypeService;
import com.kingzoo.kingcat.project.icode4.business.system.vo.PropertyTypeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gonghongrui on 2018/3/29.
 */
@RestController
@RequestMapping(value = "/system/referencePropertyType")
public class ReferencePropertyTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferencePropertyTypeController.class);


	@Autowired
	private PropertyTypeService propertyTypeService;

	@Autowired
	private DomainModelService domainModelService;

	@Autowired
	private ModuleService moduleService;

	/**
	 * 查询属性类型列表
	 * @return
	 */
	@GetMapping
	public PageContent<PropertyTypeVO> list(@RequestParam(required=false, value = "domainModelId") String domainModelId,
                                            @RequestParam(required=false, value = "moduleId") String moduleId){


		//得到所有基本类型
		List<PropertyType> propertyTypeList = propertyTypeService.findAll(null);

		//得到所有的引用类型
		List<DomainModel> domainModelList = domainModelService.findSystemDomainModel(moduleId, domainModelId);


		List<PropertyTypeVO> voList = new ArrayList<>();
		for(PropertyType propertyType : propertyTypeList){
			voList.add(initViewProperty(propertyType));
		}

		for(DomainModel domainModel : domainModelList){
			voList.add(initViewProperty(domainModel));
		}


		PageContent<PropertyTypeVO> pageContent = new PageContent<>(voList, voList.size());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,voList.size());
		return pageContent;

	}

	private PropertyTypeVO initViewProperty(PropertyType propertyType){
		PropertyTypeVO vo = new PropertyTypeVO();

		BeanUtils.copyProperties(propertyType, vo);

		//初始化其他对象
		return vo;
	}

	private PropertyTypeVO initViewProperty(DomainModel domainModel){
		PropertyTypeVO vo = new PropertyTypeVO();

//		Module module  = moduleService.find(domainModel.getModuleId());
		vo.setName(moduleService.findModuleNamePath(domainModel.getModuleId())+"."+domainModel.getName());
		vo.setId(domainModel.getId());
		vo.setCode(domainModel.getId());
		//初始化其他对象
		return vo;
	}



}
