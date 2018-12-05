package net.aicoder.speedcloud.console.business.speedcloud.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoEditDto;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;
import net.aicoder.speedcloud.console.business.speedcloud.app.service.AppBaseInfoRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.app.valid.AppBaseInfoValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理应用（系统）
 * @author icode
 */
@Api(description = "应用（系统）", tags = "AppBaseInfo")
@RestController
@RequestMapping(value = "/speedcloud/app/appbaseinfo")
public class AppBaseInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppBaseInfoController.class);
   
    @Autowired
	private AppBaseInfoRibbonService appBaseInfoRibbonService;

	@Autowired
	private AppBaseInfoValidator appBaseInfoValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(appBaseInfoValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增应用（系统）
	 * @param appBaseInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用（系统）", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public AppBaseInfoVO add(@RequestBody @Valid AppBaseInfoAddDto appBaseInfoAddDto){
	
		return  appBaseInfoRibbonService.add(appBaseInfoAddDto);
	}

	/**
	 * 删除应用（系统）,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用（系统）", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete appBaseInfo :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			appBaseInfoRibbonService.delete(id);
		}

	}

	/**
	 * 更新应用（系统）
	 * @param appBaseInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用（系统）(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public AppBaseInfoVO update(@RequestBody @Valid AppBaseInfoEditDto appBaseInfoEditDto, @ApiParam(value = "要查询的应用（系统）id") @PathVariable String id){

		AppBaseInfoVO vo = appBaseInfoRibbonService.merge(id, appBaseInfoEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询应用（系统）
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用（系统）", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public AppBaseInfoVO get(@ApiParam(value = "要查询的应用（系统）id") @PathVariable String id) {

		AppBaseInfoVO vo = appBaseInfoRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询应用（系统）列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用（系统）列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = AppBaseInfoCondition.class)
	public PageContent<AppBaseInfoVO> list(@RequestBody @Valid PageSearchRequest<AppBaseInfoCondition> pageSearchRequest){

		PageContent<AppBaseInfoVO> pageContent = appBaseInfoRibbonService.list(pageSearchRequest);
		for(AppBaseInfoVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出应用（系统）列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出应用（系统）列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(AppBaseInfoCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<AppBaseInfoCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AppBaseInfoVO> content = this.list(pageSearchRequest);

        List<AppBaseInfoVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AppBaseInfoVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("project" ,"所属项目");
            headMap.put("type" ,"应用类型");
            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("status" ,"状态");
            headMap.put("description" ,"描述");
            headMap.put("registTime" ,"注册时间");

        String title = new String("应用（系统）");
        String fileName = new String(("应用（系统）_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private AppBaseInfoVO initViewProperty( AppBaseInfoVO vo){


	   
        return vo;

	}
}
