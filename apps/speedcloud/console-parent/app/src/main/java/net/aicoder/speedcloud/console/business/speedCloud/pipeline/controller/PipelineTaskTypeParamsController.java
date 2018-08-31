package net.aicoder.speedcloud.console.business.speedCloud.pipeline.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineTaskTypeParamsVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.service.PipelineTaskTypeParamsRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.valid.PipelineTaskTypeParamsValidator;

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
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理任务类型参数定义
 * @author icode
 */
@Api(description = "任务类型参数定义", tags = "PipelineTaskTypeParams")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinetasktypeparams")
public class PipelineTaskTypeParamsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeParamsController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineTaskTypeParamsRibbonService pipelineTaskTypeParamsRibbonService;

	@Autowired
	PipelineTaskTypeParamsValidator pipelineTaskTypeParamsValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTaskTypeParamsValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增任务类型参数定义
	 * @param pipelineTaskTypeParamsAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增任务类型参数定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineTaskTypeParamsVO add(@RequestBody PipelineTaskTypeParamsAddDto pipelineTaskTypeParamsAddDto){
		return  pipelineTaskTypeParamsRibbonService.add(pipelineTaskTypeParamsAddDto);
	}

	/**
	 * 删除任务类型参数定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务类型参数定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTaskTypeParams :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineTaskTypeParamsRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新任务类型参数定义
	 * @param pipelineTaskTypeParamsEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产任务类型参数定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineTaskTypeParamsVO update(@RequestBody PipelineTaskTypeParamsEditDto pipelineTaskTypeParamsEditDto, @ApiParam(value = "要查询的任务类型参数定义id") @PathVariable Long id){

		PipelineTaskTypeParamsVO vo = pipelineTaskTypeParamsRibbonService.merge(id, pipelineTaskTypeParamsEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询任务类型参数定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务类型参数定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineTaskTypeParamsVO get(@ApiParam(value = "要查询的任务类型参数定义id") @PathVariable Long id) {

		PipelineTaskTypeParamsVO vo = pipelineTaskTypeParamsRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询任务类型参数定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务类型参数定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineTaskTypeParamsVO> list(@RequestBody PageSearchRequest<PipelineTaskTypeParamsCondition> pageSearchRequest){

		PipelineTaskTypeParamsCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineTaskTypeParamsCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		PageContent<PipelineTaskTypeParamsVO> pageContent = pipelineTaskTypeParamsRibbonService.list(pageSearchRequest);
		for(PipelineTaskTypeParamsVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出任务类型参数定义列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出任务类型参数定义列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineTaskTypeParamsCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineTaskTypeParamsCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTaskTypeParamsVO> content = this.list(pageSearchRequest);

        List<PipelineTaskTypeParamsVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTaskTypeParamsVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("taskType" ,"所属任务类型");
            headMap.put("name" ,"参数名称");
            headMap.put("code" ,"参数代码");
            headMap.put("viewOrder" ,"展现顺序");

        String title = new String("任务类型参数定义");
        String fileName = new String(("任务类型参数定义_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineTaskTypeParamsVO initViewProperty( PipelineTaskTypeParamsVO vo){

	   


	   
        return vo;

	}


}
