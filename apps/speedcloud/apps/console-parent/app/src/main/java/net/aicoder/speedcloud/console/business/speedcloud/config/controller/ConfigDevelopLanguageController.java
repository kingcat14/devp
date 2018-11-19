package net.aicoder.speedcloud.console.business.speedcloud.config.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageAddDto;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageCondition;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageEditDto;
import net.aicoder.speedcloud.business.config.vo.ConfigDevelopLanguageVO;
import net.aicoder.speedcloud.console.business.speedcloud.config.service.ConfigDevelopLanguageRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.config.valid.ConfigDevelopLanguageValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理开发语言
 * @author icode
 */
@Api(description = "开发语言", tags = "ConfigDevelopLanguage")
@RestController
@RequestMapping(value = "/speedcloud/config/configdeveloplanguage")
public class ConfigDevelopLanguageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDevelopLanguageController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private ConfigDevelopLanguageRibbonService configDevelopLanguageRibbonService;

	@Autowired
	ConfigDevelopLanguageValidator configDevelopLanguageValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(configDevelopLanguageValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增开发语言
	 * @param configDevelopLanguageAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增开发语言", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	@SaaSAnnotation
	public ConfigDevelopLanguageVO add(@RequestBody ConfigDevelopLanguageAddDto configDevelopLanguageAddDto){
		return  configDevelopLanguageRibbonService.add(configDevelopLanguageAddDto);
	}

	/**
	 * 删除开发语言,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除开发语言", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete configDevelopLanguage :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			configDevelopLanguageRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新开发语言
	 * @param configDevelopLanguageEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产开发语言(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public ConfigDevelopLanguageVO update(@RequestBody ConfigDevelopLanguageEditDto configDevelopLanguageEditDto, @ApiParam(value = "要查询的开发语言id") @PathVariable Long id){

		ConfigDevelopLanguageVO vo = configDevelopLanguageRibbonService.merge(id, configDevelopLanguageEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询开发语言
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询开发语言", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public ConfigDevelopLanguageVO get(@ApiParam(value = "要查询的开发语言id") @PathVariable Long id) {

		ConfigDevelopLanguageVO vo = configDevelopLanguageRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询开发语言列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询开发语言列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = ConfigDevelopLanguageCondition.class)
	public PageContent<ConfigDevelopLanguageVO> list(@RequestBody PageSearchRequest<ConfigDevelopLanguageCondition> pageSearchRequest){

		PageContent<ConfigDevelopLanguageVO> pageContent = configDevelopLanguageRibbonService.list(pageSearchRequest);
		for(ConfigDevelopLanguageVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出开发语言列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出开发语言列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ConfigDevelopLanguageCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<ConfigDevelopLanguageCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ConfigDevelopLanguageVO> content = this.list(pageSearchRequest);

        List<ConfigDevelopLanguageVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ConfigDevelopLanguageVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"名称");

        String title = new String("开发语言");
        String fileName = new String(("开发语言_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ConfigDevelopLanguageVO initViewProperty( ConfigDevelopLanguageVO vo){

	   


	   
        return vo;

	}


}
