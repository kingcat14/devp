package com.yunkang.saas.platform.monitor.business.app.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationCondition;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationAddDto;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationEditDto;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.app.valid.ApplicationValidator;
import com.yunkang.saas.platform.monitor.business.app.vo.ApplicationVO;


import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理程序
 * @author icode
 */
@Api(description = "程序", tags = "Application")
@RestController
@RequestMapping(value = "/monitor/app/application")
public class ApplicationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);


	@Autowired
	private ApplicationService applicationService;



	@Autowired
	private ApplicationValidator applicationValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(applicationValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增程序
	 * @param applicationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增程序", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ApplicationVO add(@RequestBody @Valid ApplicationAddDto applicationAddDto){
		Application application = new Application();
		BeanUtils.copyProperties(applicationAddDto, application);

		applicationService.add(application);

		return  initViewProperty(application);
	}

	/**
	 * 删除程序,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除程序", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete application :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			applicationService.delete(id);
		}

	}

	/**
	 * 更新程序
	 * @param applicationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产程序(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public	ApplicationVO update(@RequestBody @Valid ApplicationEditDto applicationEditDto, @PathVariable String id){
		Application application = new Application();
		BeanUtils.copyProperties(applicationEditDto, application);
		application.setId(id);
		applicationService.merge(application);

		ApplicationVO vo = initViewProperty(application);
		return  vo;
	}

	/**
	 * 根据ID查询程序
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询程序", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public  ApplicationVO get(@PathVariable String id) {

		Application application = applicationService.find(id);

		ApplicationVO vo = initViewProperty(application);
		return vo;
	}

	/**
	 * 查询程序列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询程序列表", httpMethod = "POST")
	@PostMapping(path="/list")
	public PageContent<ApplicationVO> list(@RequestBody PageSearchRequest<ApplicationCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<Application> page = applicationService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ApplicationVO> voList = new ArrayList<>();
		for(Application application : page.getContent()){
			voList.add(initViewProperty(application));
		}

		PageContent<ApplicationVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出程序列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出程序列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ApplicationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ApplicationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ApplicationVO> content = this.list(pageSearchRequest);

        List<ApplicationVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ApplicationVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("totalCount" ,"配置实例数量");
            headMap.put("aliveCount" ,"当前实例数量");
            headMap.put("alarm" ,"低实例告警");
            headMap.put("enable" ,"启动监控");
            headMap.put("thresholdValue" ,"告警数量");

        String title = new String("程序");
        String fileName = new String(("程序_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ApplicationVO initViewProperty(Application application){

	    ApplicationVO vo = new ApplicationVO();
        BeanUtils.copyProperties(application, vo);


	    //初始化其他对象
        return vo;

	}


}

