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
import net.aicoder.speedcloud.business.config.domain.EnvConfigLevel;
import net.aicoder.speedcloud.business.config.dto.EnvConfigLevelCondition;
import net.aicoder.speedcloud.business.config.dto.EnvConfigLevelAddDto;
import net.aicoder.speedcloud.business.config.dto.EnvConfigLevelEditDto;
import net.aicoder.speedcloud.business.config.service.EnvConfigLevelService;
import net.aicoder.speedcloud.business.config.valid.EnvConfigLevelValidator;
import net.aicoder.speedcloud.business.config.vo.EnvConfigLevelVO;

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
 * 管理环境级别
 * @author icode
 */
@Api(description = "环境级别", tags = "EnvConfigLevel")
@RestController
@RequestMapping(value = "/speedcloud/config/envconfiglevel")
public class EnvConfigLevelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvConfigLevelController.class);


	@Autowired
	private EnvConfigLevelService envConfigLevelService;


	@Autowired
	private EnvConfigLevelValidator envConfigLevelValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(envConfigLevelValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增环境级别
	 * @param envConfigLevelAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增环境级别", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public EnvConfigLevelVO add(@RequestBody @Valid EnvConfigLevelAddDto envConfigLevelAddDto){
		EnvConfigLevel envConfigLevel = new EnvConfigLevel();
		BeanUtils.copyProperties(envConfigLevelAddDto, envConfigLevel);

		envConfigLevelService.add(envConfigLevel);

		return  initViewProperty(envConfigLevel);
	}

	/**
	 * 删除环境级别,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除环境级别", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete envConfigLevel :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			envConfigLevelService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新环境级别
	 * @param envConfigLevelEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产环境级别(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	EnvConfigLevelVO update(@RequestBody @Valid EnvConfigLevelEditDto envConfigLevelEditDto, @PathVariable Long id){
		EnvConfigLevel envConfigLevel = new EnvConfigLevel();
		BeanUtils.copyProperties(envConfigLevelEditDto, envConfigLevel);
		envConfigLevel.setId(id);
		envConfigLevelService.merge(envConfigLevel);

		EnvConfigLevelVO vo = initViewProperty(envConfigLevel);
		return  vo;
	}

	/**
	 * 根据ID查询环境级别
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询环境级别", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  EnvConfigLevelVO get(@PathVariable Long id) {

		EnvConfigLevel envConfigLevel = envConfigLevelService.find(id);

		EnvConfigLevelVO vo = initViewProperty(envConfigLevel);
		return vo;
	}

	/**
	 * 查询环境级别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询环境级别列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<EnvConfigLevelVO> list(@RequestBody PageSearchRequest<EnvConfigLevelCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<EnvConfigLevel> page = envConfigLevelService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<EnvConfigLevelVO> voList = new ArrayList<>();
		for(EnvConfigLevel envConfigLevel : page.getContent()){
			voList.add(initViewProperty(envConfigLevel));
		}

		PageContent<EnvConfigLevelVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出环境级别列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出环境级别列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(EnvConfigLevelCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<EnvConfigLevelCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<EnvConfigLevelVO> content = this.list(pageSearchRequest);

        List<EnvConfigLevelVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(EnvConfigLevelVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("type" ,"类型");

        String title = new String("环境级别");
        String fileName = new String(("环境级别_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private EnvConfigLevelVO initViewProperty(EnvConfigLevel envConfigLevel){

	    EnvConfigLevelVO vo = new EnvConfigLevelVO();
        BeanUtils.copyProperties(envConfigLevel, vo);


	    //初始化其他对象
        return vo;


	}


}
