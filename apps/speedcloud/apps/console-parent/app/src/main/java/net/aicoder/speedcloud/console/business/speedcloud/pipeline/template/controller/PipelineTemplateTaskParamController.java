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
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamCondition;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamEditDto;
import net.aicoder.speedcloud.business.pipeline.template.vo.PipelineTemplateTaskParamVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.service.PipelineTemplateTaskParamRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.valid.PipelineTemplateTaskParamValidator;
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
 * 管理任务模板参数
 * @author icode
 */
@Api(description = "任务模板参数", tags = "PipelineTemplateTaskParam")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/template/pipelinetemplatetaskparam")
public class PipelineTemplateTaskParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTemplateTaskParamController.class);
   
    @Autowired
	private PipelineTemplateTaskParamRibbonService pipelineTemplateTaskParamRibbonService;

	@Autowired
	private PipelineTemplateTaskParamValidator pipelineTemplateTaskParamValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTemplateTaskParamValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增任务模板参数
	 * @param pipelineTemplateTaskParamAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增任务模板参数", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public PipelineTemplateTaskParamVO add(@RequestBody @Valid PipelineTemplateTaskParamAddDto pipelineTemplateTaskParamAddDto){
	
		return  pipelineTemplateTaskParamRibbonService.add(pipelineTemplateTaskParamAddDto);
	}

	/**
	 * 删除任务模板参数,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务模板参数", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTemplateTaskParam :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			pipelineTemplateTaskParamRibbonService.delete(id);
		}

	}

	/**
	 * 更新任务模板参数
	 * @param pipelineTemplateTaskParamEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产任务模板参数(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public PipelineTemplateTaskParamVO update(@RequestBody @Valid PipelineTemplateTaskParamEditDto pipelineTemplateTaskParamEditDto, @ApiParam(value = "要查询的任务模板参数id") @PathVariable String id){

		PipelineTemplateTaskParamVO vo = pipelineTemplateTaskParamRibbonService.merge(id, pipelineTemplateTaskParamEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询任务模板参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务模板参数", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public PipelineTemplateTaskParamVO get(@ApiParam(value = "要查询的任务模板参数id") @PathVariable String id) {

		PipelineTemplateTaskParamVO vo = pipelineTemplateTaskParamRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询任务模板参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务模板参数列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = PipelineTemplateTaskParamCondition.class)
	public PageContent<PipelineTemplateTaskParamVO> list(@RequestBody @Valid PageSearchRequest<PipelineTemplateTaskParamCondition> pageSearchRequest){

		PageContent<PipelineTemplateTaskParamVO> pageContent = pipelineTemplateTaskParamRibbonService.list(pageSearchRequest);
		for(PipelineTemplateTaskParamVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出任务模板参数列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出任务模板参数列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(PipelineTemplateTaskParamCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineTemplateTaskParamCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTemplateTaskParamVO> content = this.list(pageSearchRequest);

        List<PipelineTemplateTaskParamVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTemplateTaskParamVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("task" ,"所属任务");
            headMap.put("name" ,"参数名称");
            headMap.put("type" ,"参数类型");
            headMap.put("defaultValue" ,"默认值");
            headMap.put("viewOrder" ,"展现顺序");
            headMap.put("description" ,"参数描述");
            headMap.put("deletable" ,"可删除");
            headMap.put("enumValue" ,"可选值");
            headMap.put("value" ,"参数值");

        String title = new String("任务模板参数");
        String fileName = new String(("任务模板参数_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineTemplateTaskParamVO initViewProperty( PipelineTemplateTaskParamVO vo){


	   
        return vo;

	}
}
