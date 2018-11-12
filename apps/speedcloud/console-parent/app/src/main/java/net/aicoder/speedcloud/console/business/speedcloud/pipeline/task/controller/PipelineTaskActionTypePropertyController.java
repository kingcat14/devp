package net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyEditDto;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionTypePropertyVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.service.PipelineTaskActionTypePropertyRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.valid.PipelineTaskActionTypePropertyValidator;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理操作类型属性定义
 * @author icode
 */
@Api(description = "操作类型属性定义", tags = "PipelineTaskActionTypeProperty")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/task/pipelinetaskactiontypeproperty")
public class PipelineTaskActionTypePropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionTypePropertyController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineTaskActionTypePropertyRibbonService pipelineTaskActionTypePropertyRibbonService;

	@Autowired
	PipelineTaskActionTypePropertyValidator pipelineTaskActionTypePropertyValidator;

    @Autowired
    private SimpleConfigService simpleConfigService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTaskActionTypePropertyValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增操作类型属性定义
	 * @param pipelineTaskActionTypePropertyAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增操作类型属性定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	@SaaSAnnotation
	public PipelineTaskActionTypePropertyVO add(@RequestBody PipelineTaskActionTypePropertyAddDto pipelineTaskActionTypePropertyAddDto){
		return  pipelineTaskActionTypePropertyRibbonService.add(pipelineTaskActionTypePropertyAddDto);
	}

	/**
	 * 删除操作类型属性定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除操作类型属性定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTaskActionTypeProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineTaskActionTypePropertyRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新操作类型属性定义
	 * @param pipelineTaskActionTypePropertyEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产操作类型属性定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineTaskActionTypePropertyVO update(@RequestBody PipelineTaskActionTypePropertyEditDto pipelineTaskActionTypePropertyEditDto, @ApiParam(value = "要查询的操作类型属性定义id") @PathVariable Long id){

		PipelineTaskActionTypePropertyVO vo = pipelineTaskActionTypePropertyRibbonService.merge(id, pipelineTaskActionTypePropertyEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询操作类型属性定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询操作类型属性定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineTaskActionTypePropertyVO get(@ApiParam(value = "要查询的操作类型属性定义id") @PathVariable Long id) {

		PipelineTaskActionTypePropertyVO vo = pipelineTaskActionTypePropertyRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询操作类型属性定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询操作类型属性定义列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = PipelineTaskActionTypePropertyCondition.class)
	public PageContent<PipelineTaskActionTypePropertyVO> list(@RequestBody PageSearchRequest<PipelineTaskActionTypePropertyCondition> pageSearchRequest){

		PageContent<PipelineTaskActionTypePropertyVO> pageContent = pipelineTaskActionTypePropertyRibbonService.list(pageSearchRequest);
		for(PipelineTaskActionTypePropertyVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出操作类型属性定义列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出操作类型属性定义列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineTaskActionTypePropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineTaskActionTypePropertyCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTaskActionTypePropertyVO> content = this.list(pageSearchRequest);

        List<PipelineTaskActionTypePropertyVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTaskActionTypePropertyVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("taskType" ,"所属任务类型");
            headMap.put("name" ,"属性名称");
            headMap.put("code" ,"属性代码");
            headMap.put("viewOrder" ,"展现顺序");
            headMap.put("type" ,"属性类型");
            headMap.put("optionValue" ,"可选值");

        String title = new String("操作类型属性定义");
        String fileName = new String(("操作类型属性定义_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineTaskActionTypePropertyVO initViewProperty( PipelineTaskActionTypePropertyVO vo){

	   

		SimpleConfig typeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINETASKACTIONTYPEPROPERTY-TYPE", vo.getType());

		if(typeSimpleConfig!=null) {

		    SimpleConfigVO typeSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(typeSimpleConfig, typeSimpleConfigVO);
		    vo.setTypeVO(typeSimpleConfigVO);
		}

	   
        return vo;

	}


}
