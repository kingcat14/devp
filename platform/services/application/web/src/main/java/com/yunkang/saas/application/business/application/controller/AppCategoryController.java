package com.yunkang.saas.application.business.application.controller;

import com.yunkang.saas.application.business.application.domain.AppCategory;
import com.yunkang.saas.application.business.application.dto.AppCategoryAddDto;
import com.yunkang.saas.application.business.application.dto.AppCategoryCondition;
import com.yunkang.saas.application.business.application.dto.AppCategoryEditDto;
import com.yunkang.saas.application.business.application.service.AppCategoryService;
import com.yunkang.saas.application.business.application.valid.AppCategoryValidator;
import com.yunkang.saas.application.business.application.vo.AppCategoryVO;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 管理应用类别
 * @author icode
 */
@Api(description = "应用类别", tags = "AppCategory")
@RestController
@RequestMapping(value = "/application/appCategory")
public class AppCategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppCategoryController.class);


	@Autowired
	private AppCategoryService appCategoryService;


	@Autowired
	private AppCategoryValidator appCategoryValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(appCategoryValidator);
	}

	/**
	 * 新增应用类别
	 * @param appCategoryAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用类别", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AppCategoryVO add(@RequestBody @Valid AppCategoryAddDto appCategoryAddDto){
		AppCategory appCategory = new AppCategory();
		BeanUtils.copyProperties(appCategoryAddDto, appCategory);

		appCategoryService.add(appCategory);

		return  initViewProperty(appCategory);
	}

	/**
	 * 删除应用类别,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用类别", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete appCategory :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			appCategoryService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新应用类别
	 * @param appCategoryEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用类别(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	AppCategoryVO update(@RequestBody @Valid AppCategoryEditDto appCategoryEditDto, @PathVariable Long id){
		AppCategory appCategory = new AppCategory();
		BeanUtils.copyProperties(appCategoryEditDto, appCategory);
		appCategory.setId(id);
		appCategoryService.merge(appCategory);

		AppCategoryVO vo = initViewProperty(appCategory);
		return  vo;
	}

	/**
	 * 根据ID查询应用类别
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用类别", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  AppCategoryVO get(@PathVariable Long id) {

		AppCategory appCategory = appCategoryService.find(id);

		AppCategoryVO vo = initViewProperty(appCategory);
		return vo;
	}

	/**
	 * 查询应用类别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用类别列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<AppCategoryVO> list(@RequestBody PageSearchRequest<AppCategoryCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());

		Page<AppCategory> page = appCategoryService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AppCategoryVO> voList = new ArrayList<>();
		for(AppCategory appCategory : page.getContent()){
			voList.add(initViewProperty(appCategory));
		}

		PageContent<AppCategoryVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private AppCategoryVO initViewProperty(AppCategory appCategory){
	    AppCategoryVO vo = new AppCategoryVO();

        BeanUtils.copyProperties(appCategory, vo);

	    //初始化其他对象
        return vo;
	}


}
