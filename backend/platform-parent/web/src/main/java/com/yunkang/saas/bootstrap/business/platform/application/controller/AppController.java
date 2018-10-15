package com.yunkang.saas.bootstrap.business.platform.application.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.yunkang.saas.bootstrap.business.platform.application.domain.App;
import com.yunkang.saas.bootstrap.business.platform.application.dto.AppCondition;
import com.yunkang.saas.bootstrap.business.platform.application.dto.AppAddDto;
import com.yunkang.saas.bootstrap.business.platform.application.dto.AppEditDto;
import com.yunkang.saas.bootstrap.business.platform.application.service.AppService;
import com.yunkang.saas.bootstrap.business.platform.application.valid.AppValidator;
import com.yunkang.saas.bootstrap.business.platform.application.vo.AppVO;
import com.yunkang.saas.bootstrap.business.platform.application.domain.ConfigAppCategory;
import com.yunkang.saas.bootstrap.business.platform.application.service.ConfigAppCategoryService;
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
 * 管理应用
 * @author icode
 */
@Api(description = "应用", tags = "App")
@RestController
@RequestMapping(value = "/platform/application/app")
public class AppController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);


	@Autowired
	private AppService appService;

	@Autowired
	private ConfigAppCategoryService configAppCategoryService;

	@Autowired
	private AppValidator appValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(appValidator);
	}

	/**
	 * 新增应用
	 * @param appAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AppVO add(@RequestBody @Valid AppAddDto appAddDto){
		App app = new App();
		BeanUtils.copyProperties(appAddDto, app);

		appService.add(app);

		return  initViewProperty(app);
	}

	/**
	 * 删除应用,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete app :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			appService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新应用
	 * @param appEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	AppVO update(@RequestBody @Valid AppEditDto appEditDto, @PathVariable Long id){
		App app = new App();
		BeanUtils.copyProperties(appEditDto, app);
		app.setId(id);
		appService.merge(app);

		AppVO vo = initViewProperty(app);
		return  vo;
	}

	/**
	 * 根据ID查询应用
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  AppVO get(@PathVariable Long id) {

		App app = appService.find(id);

		AppVO vo = initViewProperty(app);
		return vo;
	}

	/**
	 * 查询应用列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<AppVO> list(@RequestBody PageSearchRequest<AppCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<App> page = appService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AppVO> voList = new ArrayList<>();
		for(App app : page.getContent()){
			voList.add(initViewProperty(app));
		}

		PageContent<AppVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private AppVO initViewProperty(App app){
	    AppVO vo = new AppVO();

        BeanUtils.copyProperties(app, vo);

	    //初始化其他对象
	    initAppCategoryIdPropertyGroup(vo, app);
        return vo;
	}


	private void initAppCategoryIdPropertyGroup(AppVO appVO, App app){
	
		ConfigAppCategory appCategoryId = configAppCategoryService.find(app.getAppCategoryId());
		if(appCategoryId == null){
			return;
		}
		ConfigAppCategoryVO appCategoryIdVO = new ConfigAppCategoryVO();
		BeanUtils.copyProperties(appCategoryId, appCategoryIdVO);

		appVO.setAppCategoryIdVO(appCategoryIdVO);

	}


}
