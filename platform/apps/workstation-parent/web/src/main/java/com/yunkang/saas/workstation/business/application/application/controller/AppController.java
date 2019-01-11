package com.yunkang.saas.workstation.business.application.application.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.yunkang.saas.application.business.application.dto.AppCondition;
import com.yunkang.saas.application.business.application.dto.AppAddDto;
import com.yunkang.saas.application.business.application.dto.AppEditDto;
import com.yunkang.saas.application.business.application.vo.AppVO;
import com.yunkang.saas.workstation.business.application.application.service.AppRibbonService;
import com.yunkang.saas.workstation.business.application.application.valid.AppValidator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RestController("workstationAppController")
@RequestMapping(value = "/application/app")
public class AppController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);


	@Autowired
	private AppRibbonService appRibbonService;

	@Autowired
	AppValidator appValidator;

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
	public AppVO add(@RequestBody AppAddDto appAddDto){

		return  appRibbonService.add(appAddDto);
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
			appRibbonService.delete(Long.parseLong(id));
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
	public AppVO update(@RequestBody AppEditDto appEditDto, @ApiParam(value = "要查询的应用id") @PathVariable Long id){

		AppVO vo = appRibbonService.merge(id, appEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询应用
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public AppVO get(@ApiParam(value = "要查询的应用id") @PathVariable Long id) {

		AppVO vo = appRibbonService.find(id);
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


		PageContent<AppVO> pageContent = appRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
