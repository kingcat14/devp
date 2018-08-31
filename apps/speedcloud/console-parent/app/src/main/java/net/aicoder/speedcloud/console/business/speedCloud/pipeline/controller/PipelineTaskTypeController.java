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
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineTaskTypeVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.service.PipelineTaskTypeRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.valid.PipelineTaskTypeValidator;

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
 * 管理任务类型
 * @author icode
 */
@Api(description = "任务类型", tags = "PipelineTaskType")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinetasktype")
public class PipelineTaskTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineTaskTypeRibbonService pipelineTaskTypeRibbonService;

	@Autowired
	PipelineTaskTypeValidator pipelineTaskTypeValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTaskTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增任务类型
	 * @param pipelineTaskTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增任务类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineTaskTypeVO add(@RequestBody PipelineTaskTypeAddDto pipelineTaskTypeAddDto){
		return  pipelineTaskTypeRibbonService.add(pipelineTaskTypeAddDto);
	}

	/**
	 * 删除任务类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务类型", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTaskType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineTaskTypeRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新任务类型
	 * @param pipelineTaskTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产任务类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineTaskTypeVO update(@RequestBody PipelineTaskTypeEditDto pipelineTaskTypeEditDto, @ApiParam(value = "要查询的任务类型id") @PathVariable Long id){

		PipelineTaskTypeVO vo = pipelineTaskTypeRibbonService.merge(id, pipelineTaskTypeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询任务类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务类型", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineTaskTypeVO get(@ApiParam(value = "要查询的任务类型id") @PathVariable Long id) {

		PipelineTaskTypeVO vo = pipelineTaskTypeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询任务类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务类型列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineTaskTypeVO> list(@RequestBody PageSearchRequest<PipelineTaskTypeCondition> pageSearchRequest){

		PipelineTaskTypeCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineTaskTypeCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		PageContent<PipelineTaskTypeVO> pageContent = pipelineTaskTypeRibbonService.list(pageSearchRequest);
		for(PipelineTaskTypeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出任务类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出任务类型列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineTaskTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineTaskTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTaskTypeVO> content = this.list(pageSearchRequest);

        List<PipelineTaskTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTaskTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("code" ,"类型代码");
            headMap.put("name" ,"类型名称");

        String title = new String("任务类型");
        String fileName = new String(("任务类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineTaskTypeVO initViewProperty( PipelineTaskTypeVO vo){

	   


	   
        return vo;

	}


}
