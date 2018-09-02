package net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.controller;

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
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamEditDto;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskParamVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.service.PipelineTaskParamRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.valid.PipelineTaskParamValidator;

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
 * 管理任务参数
 * @author icode
 */
@Api(description = "任务参数", tags = "PipelineTaskParam")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/task/pipelinetaskparam")
public class PipelineTaskParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskParamController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineTaskParamRibbonService pipelineTaskParamRibbonService;

	@Autowired
	PipelineTaskParamValidator pipelineTaskParamValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTaskParamValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增任务参数
	 * @param pipelineTaskParamAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增任务参数", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineTaskParamVO add(@RequestBody PipelineTaskParamAddDto pipelineTaskParamAddDto){
    	pipelineTaskParamAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  pipelineTaskParamRibbonService.add(pipelineTaskParamAddDto);
	}

	/**
	 * 删除任务参数,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务参数", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTaskParam :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineTaskParamRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新任务参数
	 * @param pipelineTaskParamEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产任务参数(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineTaskParamVO update(@RequestBody PipelineTaskParamEditDto pipelineTaskParamEditDto, @ApiParam(value = "要查询的任务参数id") @PathVariable Long id){

		PipelineTaskParamVO vo = pipelineTaskParamRibbonService.merge(id, pipelineTaskParamEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询任务参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务参数", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineTaskParamVO get(@ApiParam(value = "要查询的任务参数id") @PathVariable Long id) {

		PipelineTaskParamVO vo = pipelineTaskParamRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询任务参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务参数列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineTaskParamVO> list(@RequestBody PageSearchRequest<PipelineTaskParamCondition> pageSearchRequest){

		PipelineTaskParamCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineTaskParamCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineTaskParamVO> pageContent = pipelineTaskParamRibbonService.list(pageSearchRequest);
		for(PipelineTaskParamVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出任务参数列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出任务参数列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineTaskParamCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineTaskParamCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTaskParamVO> content = this.list(pageSearchRequest);

        List<PipelineTaskParamVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTaskParamVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("taskType" ,"所属任务");
            headMap.put("name" ,"参数名称");
            headMap.put("type" ,"参数类型");
            headMap.put("defaultValue" ,"默认值");
            headMap.put("viewOrder" ,"展现顺序");
            headMap.put("description" ,"参数描述");
            headMap.put("deletable" ,"可删除");
            headMap.put("enumValue" ,"可选值");
            headMap.put("value" ,"参数值");

        String title = new String("任务参数");
        String fileName = new String(("任务参数_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineTaskParamVO initViewProperty( PipelineTaskParamVO vo){

	   


	   
        return vo;

	}


}
