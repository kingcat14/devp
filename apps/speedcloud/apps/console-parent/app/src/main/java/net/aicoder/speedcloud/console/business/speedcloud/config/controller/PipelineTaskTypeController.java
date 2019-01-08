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
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeCondition;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeEditDto;
import net.aicoder.speedcloud.business.config.vo.PipelineTaskTypeVO;
import net.aicoder.speedcloud.console.business.speedcloud.config.service.PipelineTaskTypeRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.config.valid.PipelineTaskTypeValidator;
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
 * 管理任务类型
 * @author icode
 */
@Api(description = "任务类型", tags = "PipelineTaskType")
@RestController
@RequestMapping(value = "/speedcloud/config/pipelinetasktype")
public class PipelineTaskTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeController.class);
   
    @Autowired
	private PipelineTaskTypeRibbonService pipelineTaskTypeRibbonService;

	@Autowired
	private PipelineTaskTypeValidator pipelineTaskTypeValidator;


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
  	@SaaSAnnotation()
	public PipelineTaskTypeVO add(@RequestBody @Valid PipelineTaskTypeAddDto pipelineTaskTypeAddDto){
	
		return  pipelineTaskTypeRibbonService.add(pipelineTaskTypeAddDto);
	}

	/**
	 * 删除任务类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务类型", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTaskType :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			pipelineTaskTypeRibbonService.delete(id);
		}

	}

	/**
	 * 更新任务类型
	 * @param pipelineTaskTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产任务类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public PipelineTaskTypeVO update(@RequestBody @Valid PipelineTaskTypeEditDto pipelineTaskTypeEditDto, @ApiParam(value = "要查询的任务类型id") @PathVariable String id){

		PipelineTaskTypeVO vo = pipelineTaskTypeRibbonService.merge(id, pipelineTaskTypeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询任务类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务类型", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public PipelineTaskTypeVO get(@ApiParam(value = "要查询的任务类型id") @PathVariable String id) {

		PipelineTaskTypeVO vo = pipelineTaskTypeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询任务类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务类型列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = PipelineTaskTypeCondition.class)
	public PageContent<PipelineTaskTypeVO> list(@RequestBody @Valid PageSearchRequest<PipelineTaskTypeCondition> pageSearchRequest){

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
    @RequestMapping(path="/export")
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

    
            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");

        String title = new String("任务类型");
        String fileName = new String(("任务类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineTaskTypeVO initViewProperty( PipelineTaskTypeVO vo){


	   
        return vo;

	}
}
