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
import net.aicoder.speedcloud.business.config.domain.ConfigDevelopLanguage;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageCondition;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageAddDto;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageEditDto;
import net.aicoder.speedcloud.business.config.service.ConfigDevelopLanguageService;
import net.aicoder.speedcloud.business.config.valid.ConfigDevelopLanguageValidator;
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
 * 管理开发语言
 * @author icode
 */
@Api(description = "开发语言", tags = "ConfigDevelopLanguage")
@RestController
@RequestMapping(value = "/speedcloud/config/configdeveloplanguage")
public class ConfigDevelopLanguageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDevelopLanguageController.class);


	@Autowired
	private ConfigDevelopLanguageService configDevelopLanguageService;


	@Autowired
	private ConfigDevelopLanguageValidator configDevelopLanguageValidator;

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
	public ConfigDevelopLanguageVO add(@RequestBody @Valid ConfigDevelopLanguageAddDto configDevelopLanguageAddDto){
		ConfigDevelopLanguage configDevelopLanguage = new ConfigDevelopLanguage();
		BeanUtils.copyProperties(configDevelopLanguageAddDto, configDevelopLanguage);

		configDevelopLanguageService.add(configDevelopLanguage);

		return  initViewProperty(configDevelopLanguage);
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
			configDevelopLanguageService.delete(Long.parseLong(id));
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
	public	ConfigDevelopLanguageVO update(@RequestBody @Valid ConfigDevelopLanguageEditDto configDevelopLanguageEditDto, @PathVariable Long id){
		ConfigDevelopLanguage configDevelopLanguage = new ConfigDevelopLanguage();
		BeanUtils.copyProperties(configDevelopLanguageEditDto, configDevelopLanguage);
		configDevelopLanguage.setId(id);
		configDevelopLanguageService.merge(configDevelopLanguage);

		ConfigDevelopLanguageVO vo = initViewProperty(configDevelopLanguage);
		return  vo;
	}

	/**
	 * 根据ID查询开发语言
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询开发语言", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ConfigDevelopLanguageVO get(@PathVariable Long id) {

		ConfigDevelopLanguage configDevelopLanguage = configDevelopLanguageService.find(id);

		ConfigDevelopLanguageVO vo = initViewProperty(configDevelopLanguage);
		return vo;
	}

	/**
	 * 查询开发语言列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询开发语言列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ConfigDevelopLanguageVO> list(@RequestBody PageSearchRequest<ConfigDevelopLanguageCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<ConfigDevelopLanguage> page = configDevelopLanguageService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ConfigDevelopLanguageVO> voList = new ArrayList<>();
		for(ConfigDevelopLanguage configDevelopLanguage : page.getContent()){
			voList.add(initViewProperty(configDevelopLanguage));
		}

		PageContent<ConfigDevelopLanguageVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(ConfigDevelopLanguageCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

	private ConfigDevelopLanguageVO initViewProperty(ConfigDevelopLanguage configDevelopLanguage){

	    ConfigDevelopLanguageVO vo = new ConfigDevelopLanguageVO();
        BeanUtils.copyProperties(configDevelopLanguage, vo);


	    //初始化其他对象
        return vo;


	}


}
