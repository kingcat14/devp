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
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigCondition;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigEditDto;
import net.aicoder.speedcloud.business.app.vo.AppDevelopConfigVO;
import net.aicoder.speedcloud.console.business.speedcloud.app.service.AppDevelopConfigRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.app.valid.AppDevelopConfigValidator;
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
 * 管理应用开发配置
 * @author icode
 */
@Api(description = "应用开发配置", tags = "AppDevelopConfig")
@RestController
@RequestMapping(value = "/speedcloud/app/appdevelopconfig")
public class AppDevelopConfigController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppDevelopConfigController.class);
   
    @Autowired
	private AppDevelopConfigRibbonService appDevelopConfigRibbonService;

	@Autowired
	private AppDevelopConfigValidator appDevelopConfigValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(appDevelopConfigValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增应用开发配置
	 * @param appDevelopConfigAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用开发配置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public AppDevelopConfigVO add(@RequestBody @Valid AppDevelopConfigAddDto appDevelopConfigAddDto){
	
		return  appDevelopConfigRibbonService.add(appDevelopConfigAddDto);
	}

	/**
	 * 删除应用开发配置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用开发配置", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete appDevelopConfig :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			appDevelopConfigRibbonService.delete(id);
		}

	}

	/**
	 * 更新应用开发配置
	 * @param appDevelopConfigEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用开发配置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public AppDevelopConfigVO update(@RequestBody @Valid AppDevelopConfigEditDto appDevelopConfigEditDto, @ApiParam(value = "要查询的应用开发配置id") @PathVariable String id){

		AppDevelopConfigVO vo = appDevelopConfigRibbonService.merge(id, appDevelopConfigEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询应用开发配置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用开发配置", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public AppDevelopConfigVO get(@ApiParam(value = "要查询的应用开发配置id") @PathVariable String id) {

		AppDevelopConfigVO vo = appDevelopConfigRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询应用开发配置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用开发配置列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = AppDevelopConfigCondition.class)
	public PageContent<AppDevelopConfigVO> list(@RequestBody @Valid PageSearchRequest<AppDevelopConfigCondition> pageSearchRequest){

		PageContent<AppDevelopConfigVO> pageContent = appDevelopConfigRibbonService.list(pageSearchRequest);
		for(AppDevelopConfigVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出应用开发配置列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出应用开发配置列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(AppDevelopConfigCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<AppDevelopConfigCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AppDevelopConfigVO> content = this.list(pageSearchRequest);

        List<AppDevelopConfigVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AppDevelopConfigVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("app" ,"应用");
            headMap.put("developDatabase" ,"开发环境DB");
            headMap.put("developDomainName" ,"开发环境域名");
            headMap.put("testDatabase" ,"测试环境DB");
            headMap.put("testDomainName" ,"测试环境域名");
            headMap.put("productionDatabase" ,"生产环境DB");
            headMap.put("productionDomainName" ,"生产环境域名");

        String title = new String("应用开发配置");
        String fileName = new String(("应用开发配置_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private AppDevelopConfigVO initViewProperty( AppDevelopConfigVO vo){


	   
        return vo;

	}
}
