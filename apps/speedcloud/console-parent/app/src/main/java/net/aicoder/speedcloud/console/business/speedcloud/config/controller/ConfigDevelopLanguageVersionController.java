package net.aicoder.speedcloud.console.business.speedcloud.config.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionCondition;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionAddDto;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionEditDto;
import net.aicoder.speedcloud.business.config.vo.ConfigDevelopLanguageVersionVO;
import net.aicoder.speedcloud.console.business.speedcloud.config.service.ConfigDevelopLanguageVersionRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.config.valid.ConfigDevelopLanguageVersionValidator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理开发语言版本
 * @author icode
 */
@Api(description = "开发语言版本", tags = "ConfigDevelopLanguageVersion")
@RestController
@RequestMapping(value = "/speedcloud/config/configdeveloplanguageversion")
public class ConfigDevelopLanguageVersionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDevelopLanguageVersionController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private ConfigDevelopLanguageVersionRibbonService configDevelopLanguageVersionRibbonService;

	@Autowired
	ConfigDevelopLanguageVersionValidator configDevelopLanguageVersionValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(configDevelopLanguageVersionValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增开发语言版本
	 * @param configDevelopLanguageVersionAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增开发语言版本", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	@SaaSAnnotation
	public ConfigDevelopLanguageVersionVO add(@RequestBody ConfigDevelopLanguageVersionAddDto configDevelopLanguageVersionAddDto){
		return  configDevelopLanguageVersionRibbonService.add(configDevelopLanguageVersionAddDto);
	}

	/**
	 * 删除开发语言版本,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除开发语言版本", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete configDevelopLanguageVersion :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			configDevelopLanguageVersionRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新开发语言版本
	 * @param configDevelopLanguageVersionEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产开发语言版本(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public ConfigDevelopLanguageVersionVO update(@RequestBody ConfigDevelopLanguageVersionEditDto configDevelopLanguageVersionEditDto, @ApiParam(value = "要查询的开发语言版本id") @PathVariable Long id){

		ConfigDevelopLanguageVersionVO vo = configDevelopLanguageVersionRibbonService.merge(id, configDevelopLanguageVersionEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询开发语言版本
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询开发语言版本", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public ConfigDevelopLanguageVersionVO get(@ApiParam(value = "要查询的开发语言版本id") @PathVariable Long id) {

		ConfigDevelopLanguageVersionVO vo = configDevelopLanguageVersionRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询开发语言版本列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询开发语言版本列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = ConfigDevelopLanguageVersionCondition.class)
	public PageContent<ConfigDevelopLanguageVersionVO> list(@RequestBody PageSearchRequest<ConfigDevelopLanguageVersionCondition> pageSearchRequest){

		PageContent<ConfigDevelopLanguageVersionVO> pageContent = configDevelopLanguageVersionRibbonService.list(pageSearchRequest);
		for(ConfigDevelopLanguageVersionVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出开发语言版本列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出开发语言版本列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ConfigDevelopLanguageVersionCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<ConfigDevelopLanguageVersionCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ConfigDevelopLanguageVersionVO> content = this.list(pageSearchRequest);

        List<ConfigDevelopLanguageVersionVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ConfigDevelopLanguageVersionVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"版本号");
            headMap.put("language" ,"开发语言");

        String title = new String("开发语言版本");
        String fileName = new String(("开发语言版本_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ConfigDevelopLanguageVersionVO initViewProperty( ConfigDevelopLanguageVersionVO vo){

	   


	   
        return vo;

	}


}
