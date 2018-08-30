package com.yunkang.saas.bootstrap.business.platform.application.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.yunkang.saas.bootstrap.business.platform.application.domain.ConfigAppCategory;
import com.yunkang.saas.bootstrap.business.platform.application.dto.ConfigAppCategoryCondition;
import com.yunkang.saas.bootstrap.business.platform.application.dto.ConfigAppCategoryAddDto;
import com.yunkang.saas.bootstrap.business.platform.application.dto.ConfigAppCategoryEditDto;
import com.yunkang.saas.bootstrap.business.platform.application.service.ConfigAppCategoryService;
import com.yunkang.saas.bootstrap.business.platform.application.valid.ConfigAppCategoryValidator;
import com.yunkang.saas.bootstrap.business.platform.application.vo.ConfigAppCategoryVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理应用类别
 * @author icode
 */
@Api(description = "应用类别", tags = "ConfigAppCategory")
@RestController
@RequestMapping(value = "/platform/application/configAppCategory")
public class ConfigAppCategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigAppCategoryController.class);


	@Autowired
	private ConfigAppCategoryService configAppCategoryService;


	@Autowired
	private ConfigAppCategoryValidator configAppCategoryValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(configAppCategoryValidator);
	}

	/**
	 * 新增应用类别
	 * @param configAppCategoryAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用类别", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ConfigAppCategoryVO add(@RequestBody @Valid ConfigAppCategoryAddDto configAppCategoryAddDto){
		ConfigAppCategory configAppCategory = new ConfigAppCategory();
		BeanUtils.copyProperties(configAppCategoryAddDto, configAppCategory);

		configAppCategoryService.add(configAppCategory);

		return  initViewProperty(configAppCategory);
	}

	/**
	 * 删除应用类别,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用类别", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete configAppCategory :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			configAppCategoryService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新应用类别
	 * @param configAppCategoryEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用类别(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	ConfigAppCategoryVO update(@RequestBody @Valid ConfigAppCategoryEditDto configAppCategoryEditDto, @PathVariable Long id){
		ConfigAppCategory configAppCategory = new ConfigAppCategory();
		BeanUtils.copyProperties(configAppCategoryEditDto, configAppCategory);
		configAppCategory.setId(id);
		configAppCategoryService.merge(configAppCategory);

		ConfigAppCategoryVO vo = initViewProperty(configAppCategory);
		return  vo;
	}

	/**
	 * 根据ID查询应用类别
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用类别", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ConfigAppCategoryVO get(@PathVariable Long id) {

		ConfigAppCategory configAppCategory = configAppCategoryService.find(id);

		ConfigAppCategoryVO vo = initViewProperty(configAppCategory);
		return vo;
	}

	/**
	 * 查询应用类别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用类别列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ConfigAppCategoryVO> list(@RequestBody PageSearchRequest<ConfigAppCategoryCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<ConfigAppCategory> page = configAppCategoryService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ConfigAppCategoryVO> voList = new ArrayList<>();
		for(ConfigAppCategory configAppCategory : page.getContent()){
			voList.add(initViewProperty(configAppCategory));
		}

		PageContent<ConfigAppCategoryVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private ConfigAppCategoryVO initViewProperty(ConfigAppCategory configAppCategory){
	    ConfigAppCategoryVO vo = new ConfigAppCategoryVO();

        BeanUtils.copyProperties(configAppCategory, vo);

	    //初始化其他对象
        return vo;
	}


}
