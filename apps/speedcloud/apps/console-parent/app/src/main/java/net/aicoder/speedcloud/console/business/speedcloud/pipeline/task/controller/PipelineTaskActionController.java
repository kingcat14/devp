package net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.controller;

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
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionEditDto;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.service.PipelineTaskActionRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.valid.PipelineTaskActionValidator;
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
 * 管理操作
 * @author icode
 */
@Api(description = "操作", tags = "PipelineTaskAction")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/task/pipelinetaskaction")
public class PipelineTaskActionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineTaskActionRibbonService pipelineTaskActionRibbonService;

	@Autowired
	PipelineTaskActionValidator pipelineTaskActionValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTaskActionValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增操作
	 * @param pipelineTaskActionAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增操作", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	@SaaSAnnotation
	public PipelineTaskActionVO add(@RequestBody PipelineTaskActionAddDto pipelineTaskActionAddDto){
		return  pipelineTaskActionRibbonService.add(pipelineTaskActionAddDto);
	}

	/**
	 * 删除操作,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除操作", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTaskAction :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineTaskActionRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新操作
	 * @param pipelineTaskActionEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产操作(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineTaskActionVO update(@RequestBody PipelineTaskActionEditDto pipelineTaskActionEditDto, @ApiParam(value = "要查询的操作id") @PathVariable Long id){

		PipelineTaskActionVO vo = pipelineTaskActionRibbonService.merge(id, pipelineTaskActionEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询操作
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询操作", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineTaskActionVO get(@ApiParam(value = "要查询的操作id") @PathVariable Long id) {

		PipelineTaskActionVO vo = pipelineTaskActionRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询操作列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询操作列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = PipelineTaskActionCondition.class)
	public PageContent<PipelineTaskActionVO> list(@RequestBody PageSearchRequest<PipelineTaskActionCondition> pageSearchRequest){

		PageContent<PipelineTaskActionVO> pageContent = pipelineTaskActionRibbonService.list(pageSearchRequest);
		for(PipelineTaskActionVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出操作列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出操作列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineTaskActionCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineTaskActionCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTaskActionVO> content = this.list(pageSearchRequest);

        List<PipelineTaskActionVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTaskActionVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("task" ,"所属任务");
            headMap.put("name" ,"操作名称");
            headMap.put("memo" ,"操作说明");
            headMap.put("execIndex" ,"执行顺序");
            headMap.put("type" ,"操作类型");
            headMap.put("content" ,"脚本内容");

        String title = new String("操作");
        String fileName = new String(("操作_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineTaskActionVO initViewProperty( PipelineTaskActionVO vo){

	   


	   
        return vo;

	}


}
