package com.yunkang.saas.platform.monitor.business.monitor.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationInstanceCondition;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationInstanceAddDto;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationInstanceEditDto;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationInstanceService;
import com.yunkang.saas.platform.monitor.business.app.valid.ApplicationInstanceValidator;
import com.yunkang.saas.platform.monitor.business.app.vo.ApplicationInstanceVO;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.app.vo.ApplicationVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理程序实例
 * @author icode
 */
@Api(description = "程序实例", tags = "ApplicationInstance")
@RestController
@RequestMapping(value =  "/monitor/app/applicationinstance")
public class ApplicationInstanceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInstanceController.class);

	@Autowired
	private ApplicationInstanceService applicationInstanceService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private ApplicationInstanceValidator applicationInstanceValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(applicationInstanceValidator);
	}

	/**
	 * 新增程序实例
	 * @param applicationInstanceAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增程序实例", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
    @SaaSAnnotation
	public ApplicationInstanceVO add(@RequestBody @Valid ApplicationInstanceAddDto applicationInstanceAddDto){

		ApplicationInstance applicationInstance = new ApplicationInstance();
		BeanUtils.copyProperties(applicationInstanceAddDto, applicationInstance);
		applicationInstanceService.add(applicationInstance);

		return  initViewProperty(applicationInstance);
	}

	/**
	 * 删除程序实例,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除程序实例", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete applicationInstance :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			applicationInstanceService.delete(id);
		}

	}

	/**
	 * 更新程序实例
	 * @param applicationInstanceEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产程序实例(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public	ApplicationInstanceVO update(@RequestBody @Valid ApplicationInstanceEditDto applicationInstanceEditDto, @PathVariable String id){
		ApplicationInstance applicationInstance = new ApplicationInstance();
		BeanUtils.copyProperties(applicationInstanceEditDto, applicationInstance);
		applicationInstance.setId(id);
		applicationInstanceService.merge(applicationInstance);

		ApplicationInstanceVO vo = initViewProperty(applicationInstance);
		return  vo;
	}

	/**
	 * 根据ID查询程序实例
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询程序实例", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public  ApplicationInstanceVO get(@PathVariable String id) {

		ApplicationInstance applicationInstance = applicationInstanceService.find(id);

		ApplicationInstanceVO vo = initViewProperty(applicationInstance);
		return vo;
	}

	/**
	 * 查询程序实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询程序实例列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@SaaSAnnotation(conditionClass = ApplicationInstanceCondition.class)
	public PageContent<ApplicationInstanceVO> list(@RequestBody PageSearchRequest<ApplicationInstanceCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
		
		Page<ApplicationInstance> page = applicationInstanceService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ApplicationInstanceVO> voList = new ArrayList<>();
		for(ApplicationInstance applicationInstance : page.getContent()){
			voList.add(initViewProperty(applicationInstance));
		}

		PageContent<ApplicationInstanceVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出程序实例列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出程序实例列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ApplicationInstanceCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ApplicationInstanceCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ApplicationInstanceVO> content = this.list(pageSearchRequest);

        List<ApplicationInstanceVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ApplicationInstanceVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("app" ,"app");
            headMap.put("ip" ,"ip");
            headMap.put("port" ,"port");
            headMap.put("alive" ,"运行中");
            headMap.put("alarm" ,"停运告警");
            headMap.put("stopTime" ,"最近停运时间");

        String title = new String("程序实例");
        String fileName = new String(("程序实例_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ApplicationInstanceVO initViewProperty(ApplicationInstance applicationInstance){

	    ApplicationInstanceVO vo = new ApplicationInstanceVO();
        BeanUtils.copyProperties(applicationInstance, vo);


	    //初始化其他对象
	    initAppPropertyGroup(vo, applicationInstance);
        return vo;


	}


	private void initAppPropertyGroup(ApplicationInstanceVO applicationInstanceVO, ApplicationInstance applicationInstance){
	
		Application app = applicationService.find(applicationInstance.getApp());
		if(app == null){
			return;
		}
		ApplicationVO appVO = new ApplicationVO();
		BeanUtils.copyProperties(app, appVO);

		applicationInstanceVO.setAppVO(appVO);

	}


}
