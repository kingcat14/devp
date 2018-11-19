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
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyEditDto;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionPropertyVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.service.PipelineTaskActionPropertyRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.valid.PipelineTaskActionPropertyValidator;
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
 * 管理操作属性
 * @author icode
 */
@Api(description = "操作属性", tags = "PipelineTaskActionProperty")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/task/pipelinetaskactionproperty")
public class PipelineTaskActionPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionPropertyController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineTaskActionPropertyRibbonService pipelineTaskActionPropertyRibbonService;

	@Autowired
	PipelineTaskActionPropertyValidator pipelineTaskActionPropertyValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTaskActionPropertyValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增操作属性
	 * @param pipelineTaskActionPropertyAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增操作属性", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	@SaaSAnnotation
	public PipelineTaskActionPropertyVO add(@RequestBody PipelineTaskActionPropertyAddDto pipelineTaskActionPropertyAddDto){
		return  pipelineTaskActionPropertyRibbonService.add(pipelineTaskActionPropertyAddDto);
	}

	/**
	 * 删除操作属性,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除操作属性", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTaskActionProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineTaskActionPropertyRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新操作属性
	 * @param pipelineTaskActionPropertyEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产操作属性(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineTaskActionPropertyVO update(@RequestBody PipelineTaskActionPropertyEditDto pipelineTaskActionPropertyEditDto, @ApiParam(value = "要查询的操作属性id") @PathVariable Long id){

		PipelineTaskActionPropertyVO vo = pipelineTaskActionPropertyRibbonService.merge(id, pipelineTaskActionPropertyEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询操作属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询操作属性", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineTaskActionPropertyVO get(@ApiParam(value = "要查询的操作属性id") @PathVariable Long id) {

		PipelineTaskActionPropertyVO vo = pipelineTaskActionPropertyRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询操作属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询操作属性列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = PipelineTaskActionPropertyCondition.class)
	public PageContent<PipelineTaskActionPropertyVO> list(@RequestBody PageSearchRequest<PipelineTaskActionPropertyCondition> pageSearchRequest){

		PageContent<PipelineTaskActionPropertyVO> pageContent = pipelineTaskActionPropertyRibbonService.list(pageSearchRequest);
		for(PipelineTaskActionPropertyVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出操作属性列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出操作属性列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineTaskActionPropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineTaskActionPropertyCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTaskActionPropertyVO> content = this.list(pageSearchRequest);

        List<PipelineTaskActionPropertyVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTaskActionPropertyVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("taskType" ,"所属操作");
            headMap.put("name" ,"属性名称");
            headMap.put("code" ,"属性代码");
            headMap.put("value" ,"属性值");
            headMap.put("viewOrder" ,"排序");
            headMap.put("type" ,"类型");

        String title = new String("操作属性");
        String fileName = new String(("操作属性_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineTaskActionPropertyVO initViewProperty( PipelineTaskActionPropertyVO vo){

	   


	   
        return vo;

	}


}
