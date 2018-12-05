package net.aicoder.speedcloud.console.business.speedcloud.config.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.config.dto.EnvLevelAddDto;
import net.aicoder.speedcloud.business.config.dto.EnvLevelCondition;
import net.aicoder.speedcloud.business.config.dto.EnvLevelEditDto;
import net.aicoder.speedcloud.business.config.vo.EnvLevelVO;
import net.aicoder.speedcloud.console.business.speedcloud.config.service.EnvLevelRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.config.valid.EnvLevelValidator;
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
 * 管理环境级别
 * @author icode
 */
@Api(description = "环境级别", tags = "EnvLevel")
@RestController
@RequestMapping(value = "/speedcloud/config/envlevel")
public class EnvLevelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvLevelController.class);
   
    @Autowired
	private EnvLevelRibbonService envLevelRibbonService;

	@Autowired
	private EnvLevelValidator envLevelValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(envLevelValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增环境级别
	 * @param envLevelAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增环境级别", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public EnvLevelVO add(@RequestBody @Valid EnvLevelAddDto envLevelAddDto){
	
		return  envLevelRibbonService.add(envLevelAddDto);
	}

	/**
	 * 删除环境级别,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除环境级别", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete envLevel :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			envLevelRibbonService.delete(id);
		}

	}

	/**
	 * 更新环境级别
	 * @param envLevelEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产环境级别(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public EnvLevelVO update(@RequestBody @Valid EnvLevelEditDto envLevelEditDto, @ApiParam(value = "要查询的环境级别id") @PathVariable String id){

		EnvLevelVO vo = envLevelRibbonService.merge(id, envLevelEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询环境级别
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询环境级别", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public EnvLevelVO get(@ApiParam(value = "要查询的环境级别id") @PathVariable String id) {

		EnvLevelVO vo = envLevelRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询环境级别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询环境级别列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = EnvLevelCondition.class)
	public PageContent<EnvLevelVO> list(@RequestBody @Valid PageSearchRequest<EnvLevelCondition> pageSearchRequest){

		PageContent<EnvLevelVO> pageContent = envLevelRibbonService.list(pageSearchRequest);
		for(EnvLevelVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出环境级别列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出环境级别列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(EnvLevelCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<EnvLevelCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<EnvLevelVO> content = this.list(pageSearchRequest);

        List<EnvLevelVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(EnvLevelVO vo : voList){
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


	private EnvLevelVO initViewProperty( EnvLevelVO vo){


	   
        return vo;

	}
}
