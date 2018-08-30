package com.yunkang.saas.bootstrap.business.platform.application.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.yunkang.saas.bootstrap.business.platform.application.domain.ApplicationPassword;
import com.yunkang.saas.bootstrap.business.platform.application.dto.ApplicationPasswordCondition;
import com.yunkang.saas.bootstrap.business.platform.application.dto.ApplicationPasswordAddDto;
import com.yunkang.saas.bootstrap.business.platform.application.dto.ApplicationPasswordEditDto;
import com.yunkang.saas.bootstrap.business.platform.application.service.ApplicationPasswordService;
import com.yunkang.saas.bootstrap.business.platform.application.valid.ApplicationPasswordValidator;
import com.yunkang.saas.bootstrap.business.platform.application.vo.ApplicationPasswordVO;
import com.yunkang.saas.bootstrap.business.platform.application.domain.App;
import com.yunkang.saas.bootstrap.business.platform.application.service.AppService;
import com.yunkang.saas.bootstrap.business.platform.application.vo.AppVO;

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
 * 管理应用密码
 * @author icode
 */
@Api(description = "应用密码", tags = "ApplicationPassword")
@RestController
@RequestMapping(value = "/platform/application/applicationPassword")
public class ApplicationPasswordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPasswordController.class);


	@Autowired
	private ApplicationPasswordService applicationPasswordService;

	@Autowired
	private AppService appService;

	@Autowired
	private ApplicationPasswordValidator applicationPasswordValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(applicationPasswordValidator);
	}

	/**
	 * 新增应用密码
	 * @param applicationPasswordAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用密码", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ApplicationPasswordVO add(@RequestBody @Valid ApplicationPasswordAddDto applicationPasswordAddDto){
		ApplicationPassword applicationPassword = new ApplicationPassword();
		BeanUtils.copyProperties(applicationPasswordAddDto, applicationPassword);

		applicationPasswordService.add(applicationPassword);

		return  initViewProperty(applicationPassword);
	}

	/**
	 * 删除应用密码,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用密码", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete applicationPassword :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			applicationPasswordService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新应用密码
	 * @param applicationPasswordEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用密码(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	ApplicationPasswordVO update(@RequestBody @Valid ApplicationPasswordEditDto applicationPasswordEditDto, @PathVariable Long id){
		ApplicationPassword applicationPassword = new ApplicationPassword();
		BeanUtils.copyProperties(applicationPasswordEditDto, applicationPassword);
		applicationPassword.setId(id);
		applicationPasswordService.merge(applicationPassword);

		ApplicationPasswordVO vo = initViewProperty(applicationPassword);
		return  vo;
	}

	/**
	 * 根据ID查询应用密码
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用密码", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ApplicationPasswordVO get(@PathVariable Long id) {

		ApplicationPassword applicationPassword = applicationPasswordService.find(id);

		ApplicationPasswordVO vo = initViewProperty(applicationPassword);
		return vo;
	}

	/**
	 * 查询应用密码列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用密码列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ApplicationPasswordVO> list(@RequestBody PageSearchRequest<ApplicationPasswordCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<ApplicationPassword> page = applicationPasswordService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ApplicationPasswordVO> voList = new ArrayList<>();
		for(ApplicationPassword applicationPassword : page.getContent()){
			voList.add(initViewProperty(applicationPassword));
		}

		PageContent<ApplicationPasswordVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private ApplicationPasswordVO initViewProperty(ApplicationPassword applicationPassword){
	    ApplicationPasswordVO vo = new ApplicationPasswordVO();

        BeanUtils.copyProperties(applicationPassword, vo);

	    //初始化其他对象
	    initAppIdPropertyGroup(vo, applicationPassword);
        return vo;
	}


	private void initAppIdPropertyGroup(ApplicationPasswordVO applicationPasswordVO, ApplicationPassword applicationPassword){
	
		App appId = appService.find(applicationPassword.getAppId());
		if(appId == null){
			return;
		}
		AppVO appIdVO = new AppVO();
		BeanUtils.copyProperties(appId, appIdVO);

		applicationPasswordVO.setAppIdVO(appIdVO);

	}


}
