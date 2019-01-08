package net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionCondition;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionEditDto;
import net.aicoder.speedcloud.business.pipeline.template.vo.PipelineTemplateTaskActionVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.service.PipelineTemplateTaskActionRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.valid.PipelineTemplateTaskActionValidator;
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
 * 管理操作模板
 * @author icode
 */
@Api(description = "操作模板", tags = "PipelineTemplateTaskAction")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/template/pipelinetemplatetaskaction")
public class PipelineTemplateTaskActionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTemplateTaskActionController.class);
   
    @Autowired
	private PipelineTemplateTaskActionRibbonService pipelineTemplateTaskActionRibbonService;

	@Autowired
	private PipelineTemplateTaskActionValidator pipelineTemplateTaskActionValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTemplateTaskActionValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增操作模板
	 * @param pipelineTemplateTaskActionAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增操作模板", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public PipelineTemplateTaskActionVO add(@RequestBody @Valid PipelineTemplateTaskActionAddDto pipelineTemplateTaskActionAddDto){
	
		return  pipelineTemplateTaskActionRibbonService.add(pipelineTemplateTaskActionAddDto);
	}

	/**
	 * 删除操作模板,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除操作模板", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTemplateTaskAction :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			pipelineTemplateTaskActionRibbonService.delete(id);
		}

	}

	/**
	 * 更新操作模板
	 * @param pipelineTemplateTaskActionEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产操作模板(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public PipelineTemplateTaskActionVO update(@RequestBody @Valid PipelineTemplateTaskActionEditDto pipelineTemplateTaskActionEditDto, @ApiParam(value = "要查询的操作模板id") @PathVariable String id){

		PipelineTemplateTaskActionVO vo = pipelineTemplateTaskActionRibbonService.merge(id, pipelineTemplateTaskActionEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询操作模板
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询操作模板", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public PipelineTemplateTaskActionVO get(@ApiParam(value = "要查询的操作模板id") @PathVariable String id) {

		PipelineTemplateTaskActionVO vo = pipelineTemplateTaskActionRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询操作模板列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询操作模板列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = PipelineTemplateTaskActionCondition.class)
	public PageContent<PipelineTemplateTaskActionVO> list(@RequestBody @Valid PageSearchRequest<PipelineTemplateTaskActionCondition> pageSearchRequest){

		PageContent<PipelineTemplateTaskActionVO> pageContent = pipelineTemplateTaskActionRibbonService.list(pageSearchRequest);
		for(PipelineTemplateTaskActionVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出操作模板列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出操作模板列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(PipelineTemplateTaskActionCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineTemplateTaskActionCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTemplateTaskActionVO> content = this.list(pageSearchRequest);

        List<PipelineTemplateTaskActionVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTemplateTaskActionVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("task" ,"所属任务");
            headMap.put("memo" ,"操作说明");
            headMap.put("name" ,"操作名称");
            headMap.put("execIndex" ,"执行顺序");
            headMap.put("type" ,"操作类型");
            headMap.put("content" ,"脚本内容");

        String title = new String("操作模板");
        String fileName = new String(("操作模板_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineTemplateTaskActionVO initViewProperty( PipelineTemplateTaskActionVO vo){


	   
        return vo;

	}
}
