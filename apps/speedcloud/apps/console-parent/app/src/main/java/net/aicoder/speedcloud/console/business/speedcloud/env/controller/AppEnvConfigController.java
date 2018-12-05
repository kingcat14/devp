package net.aicoder.speedcloud.console.business.speedcloud.env.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigAddDto;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigCondition;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigEditDto;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.console.business.speedcloud.env.service.AppEnvConfigRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.env.valid.AppEnvConfigValidator;
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
 * 管理产品环境
 * @author icode
 */
@Api(description = "产品环境", tags = "AppEnvConfig")
@RestController
@RequestMapping(value = "/speedcloud/env/appenvconfig")
public class AppEnvConfigController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppEnvConfigController.class);
   
    @Autowired
	private AppEnvConfigRibbonService appEnvConfigRibbonService;

	@Autowired
	private AppEnvConfigValidator appEnvConfigValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(appEnvConfigValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增产品环境
	 * @param appEnvConfigAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品环境", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public AppEnvConfigVO add(@RequestBody @Valid AppEnvConfigAddDto appEnvConfigAddDto){
	
		return  appEnvConfigRibbonService.add(appEnvConfigAddDto);
	}

	/**
	 * 删除产品环境,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品环境", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete appEnvConfig :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			appEnvConfigRibbonService.delete(id);
		}

	}

	/**
	 * 更新产品环境
	 * @param appEnvConfigEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品环境(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public AppEnvConfigVO update(@RequestBody @Valid AppEnvConfigEditDto appEnvConfigEditDto, @ApiParam(value = "要查询的产品环境id") @PathVariable String id){

		AppEnvConfigVO vo = appEnvConfigRibbonService.merge(id, appEnvConfigEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询产品环境
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品环境", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public AppEnvConfigVO get(@ApiParam(value = "要查询的产品环境id") @PathVariable String id) {

		AppEnvConfigVO vo = appEnvConfigRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询产品环境列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品环境列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = AppEnvConfigCondition.class)
	public PageContent<AppEnvConfigVO> list(@RequestBody @Valid PageSearchRequest<AppEnvConfigCondition> pageSearchRequest){

		PageContent<AppEnvConfigVO> pageContent = appEnvConfigRibbonService.list(pageSearchRequest);
		for(AppEnvConfigVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出产品环境列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出产品环境列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(AppEnvConfigCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<AppEnvConfigCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AppEnvConfigVO> content = this.list(pageSearchRequest);

        List<AppEnvConfigVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AppEnvConfigVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("project" ,"所属产品（项目）");
            headMap.put("name" ,"环境名称");
            headMap.put("level" ,"环境级别");
            headMap.put("seq" ,"顺序号");

        String title = new String("产品环境");
        String fileName = new String(("产品环境_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private AppEnvConfigVO initViewProperty( AppEnvConfigVO vo){


	   
        return vo;

	}
}
