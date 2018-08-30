package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.PropertyTypeAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.PropertyType;
import com.kingzoo.kingcat.project.icode4.business.system.service.PropertyTypeService;
import com.kingzoo.kingcat.project.icode4.business.system.valid.PropertyTypeValidator;
import com.kingzoo.kingcat.project.icode4.business.system.vo.PropertyTypeCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.PropertyTypeVO;
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
 * 管理属性类型
 * @author icode
 */
@RestController
@RequestMapping(value = "/system/propertyType")
public class PropertyTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyTypeController.class);


	@Autowired
	private PropertyTypeService propertyTypeService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new PropertyTypeValidator());
	}

	/**
	 * 新增属性类型
	 * @param propertyTypeAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PropertyTypeVO add(@RequestBody @Valid PropertyTypeAddRequest propertyTypeAddRequest){
		PropertyType propertyType = new PropertyType();
		BeanUtils.copyProperties(propertyTypeAddRequest, propertyType);

		propertyTypeService.add(propertyType);

		return  initViewProperty(propertyType);
	}

	/**
	 * 删除属性类型,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete propertyType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			propertyTypeService.delete(id);
		}

	}

	/**
	 * 更新属性类型
	 * @param propertyType
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public PropertyType update(@RequestBody @Valid PropertyType propertyType, @PathVariable String id){
		propertyType.setId(id);
		propertyTypeService.merge(propertyType);

		initViewProperty(propertyType);
		return  propertyType;
	}

	/**
	 * 根据ID查询属性类型
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public PropertyType get(@PathVariable String id) {

		PropertyType propertyType = propertyTypeService.find(id);

		initViewProperty(propertyType);
		return propertyType;
	}

	/**
	 * 查询属性类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<PropertyTypeVO> list(@RequestBody PageSearchRequest<PropertyTypeCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<PropertyType> page = propertyTypeService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<PropertyTypeVO> voList = new ArrayList<>();
		for(PropertyType propertyType : page.getContent()){
			voList.add(initViewProperty(propertyType));
		}

		PageContent<PropertyTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private PropertyTypeVO initViewProperty(PropertyType propertyType){
	    PropertyTypeVO vo = new PropertyTypeVO();

        BeanUtils.copyProperties(propertyType, vo);

	    //初始化其他对象
        return vo;
	}




}
