package net.aicoder.speedcloud.business.config.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.config.domain.ConfigDevelopLanguageVersion;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionCondition;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionAddDto;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionEditDto;
import net.aicoder.speedcloud.business.config.service.ConfigDevelopLanguageVersionService;
import net.aicoder.speedcloud.business.config.valid.ConfigDevelopLanguageVersionValidator;
import net.aicoder.speedcloud.business.config.vo.ConfigDevelopLanguageVersionVO;
import net.aicoder.speedcloud.business.config.domain.ConfigDevelopLanguage;
import net.aicoder.speedcloud.business.config.service.ConfigDevelopLanguageService;
import net.aicoder.speedcloud.business.config.vo.ConfigDevelopLanguageVO;

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
 * 管理开发语言版本
 * @author icode
 */
@Api(description = "开发语言版本", tags = "ConfigDevelopLanguageVersion")
@RestController
@RequestMapping(value = "/speedcloud/config/configdeveloplanguageversion")
public class ConfigDevelopLanguageVersionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDevelopLanguageVersionController.class);


	@Autowired
	private ConfigDevelopLanguageVersionService configDevelopLanguageVersionService;

	@Autowired
	private ConfigDevelopLanguageService configDevelopLanguageService;

	@Autowired
	private ConfigDevelopLanguageVersionValidator configDevelopLanguageVersionValidator;

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
	public ConfigDevelopLanguageVersionVO add(@RequestBody @Valid ConfigDevelopLanguageVersionAddDto configDevelopLanguageVersionAddDto){
		ConfigDevelopLanguageVersion configDevelopLanguageVersion = new ConfigDevelopLanguageVersion();
		BeanUtils.copyProperties(configDevelopLanguageVersionAddDto, configDevelopLanguageVersion);

		configDevelopLanguageVersionService.add(configDevelopLanguageVersion);

		return  initViewProperty(configDevelopLanguageVersion);
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
			configDevelopLanguageVersionService.delete(Long.parseLong(id));
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
	public	ConfigDevelopLanguageVersionVO update(@RequestBody @Valid ConfigDevelopLanguageVersionEditDto configDevelopLanguageVersionEditDto, @PathVariable Long id){
		ConfigDevelopLanguageVersion configDevelopLanguageVersion = new ConfigDevelopLanguageVersion();
		BeanUtils.copyProperties(configDevelopLanguageVersionEditDto, configDevelopLanguageVersion);
		configDevelopLanguageVersion.setId(id);
		configDevelopLanguageVersionService.merge(configDevelopLanguageVersion);

		ConfigDevelopLanguageVersionVO vo = initViewProperty(configDevelopLanguageVersion);
		return  vo;
	}

	/**
	 * 根据ID查询开发语言版本
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询开发语言版本", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ConfigDevelopLanguageVersionVO get(@PathVariable Long id) {

		ConfigDevelopLanguageVersion configDevelopLanguageVersion = configDevelopLanguageVersionService.find(id);

		ConfigDevelopLanguageVersionVO vo = initViewProperty(configDevelopLanguageVersion);
		return vo;
	}

	/**
	 * 查询开发语言版本列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询开发语言版本列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ConfigDevelopLanguageVersionVO> list(@RequestBody PageSearchRequest<ConfigDevelopLanguageVersionCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<ConfigDevelopLanguageVersion> page = configDevelopLanguageVersionService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ConfigDevelopLanguageVersionVO> voList = new ArrayList<>();
		for(ConfigDevelopLanguageVersion configDevelopLanguageVersion : page.getContent()){
			voList.add(initViewProperty(configDevelopLanguageVersion));
		}

		PageContent<ConfigDevelopLanguageVersionVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(ConfigDevelopLanguageVersionCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

	private ConfigDevelopLanguageVersionVO initViewProperty(ConfigDevelopLanguageVersion configDevelopLanguageVersion){

	    ConfigDevelopLanguageVersionVO vo = new ConfigDevelopLanguageVersionVO();
        BeanUtils.copyProperties(configDevelopLanguageVersion, vo);


	    //初始化其他对象
	    initLanguagePropertyGroup(vo, configDevelopLanguageVersion);
        return vo;


	}


	private void initLanguagePropertyGroup(ConfigDevelopLanguageVersionVO configDevelopLanguageVersionVO, ConfigDevelopLanguageVersion configDevelopLanguageVersion){
	
		ConfigDevelopLanguage language = configDevelopLanguageService.find(configDevelopLanguageVersion.getLanguage());
		if(language == null){
			return;
		}
		ConfigDevelopLanguageVO languageVO = new ConfigDevelopLanguageVO();
		BeanUtils.copyProperties(language, languageVO);

		configDevelopLanguageVersionVO.setLanguageVO(languageVO);

	}


}
