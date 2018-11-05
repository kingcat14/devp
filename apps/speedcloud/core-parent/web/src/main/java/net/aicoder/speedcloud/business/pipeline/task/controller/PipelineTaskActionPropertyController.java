package net.aicoder.speedcloud.business.pipeline.task.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionProperty;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyEditDto;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskActionPropertyService;
import net.aicoder.speedcloud.business.pipeline.task.valid.PipelineTaskActionPropertyValidator;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionPropertyVO;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskAction;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskActionService;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionVO;


import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
	private PipelineTaskActionPropertyService pipelineTaskActionPropertyService;

	@Autowired
	private PipelineTaskActionService pipelineTaskActionService;


	@Autowired
	private PipelineTaskActionPropertyValidator pipelineTaskActionPropertyValidator;

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
	public PipelineTaskActionPropertyVO add(@RequestBody @Valid PipelineTaskActionPropertyAddDto pipelineTaskActionPropertyAddDto){
		PipelineTaskActionProperty pipelineTaskActionProperty = new PipelineTaskActionProperty();
		BeanUtils.copyProperties(pipelineTaskActionPropertyAddDto, pipelineTaskActionProperty);

		pipelineTaskActionPropertyService.add(pipelineTaskActionProperty);

		return  initViewProperty(pipelineTaskActionProperty);
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
			pipelineTaskActionPropertyService.delete(Long.parseLong(id));
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
	public	PipelineTaskActionPropertyVO update(@RequestBody @Valid PipelineTaskActionPropertyEditDto pipelineTaskActionPropertyEditDto, @PathVariable Long id){
		PipelineTaskActionProperty pipelineTaskActionProperty = new PipelineTaskActionProperty();
		BeanUtils.copyProperties(pipelineTaskActionPropertyEditDto, pipelineTaskActionProperty);
		pipelineTaskActionProperty.setId(id);
		pipelineTaskActionPropertyService.merge(pipelineTaskActionProperty);

		PipelineTaskActionPropertyVO vo = initViewProperty(pipelineTaskActionProperty);
		return  vo;
	}

	/**
	 * 根据ID查询操作属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询操作属性", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineTaskActionPropertyVO get(@PathVariable Long id) {

		PipelineTaskActionProperty pipelineTaskActionProperty = pipelineTaskActionPropertyService.find(id);

		PipelineTaskActionPropertyVO vo = initViewProperty(pipelineTaskActionProperty);
		return vo;
	}

	/**
	 * 查询操作属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询操作属性列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineTaskActionPropertyVO> list(@RequestBody PageSearchRequest<PipelineTaskActionPropertyCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<PipelineTaskActionProperty> page = pipelineTaskActionPropertyService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineTaskActionPropertyVO> voList = new ArrayList<>();
		for(PipelineTaskActionProperty pipelineTaskActionProperty : page.getContent()){
			voList.add(initViewProperty(pipelineTaskActionProperty));
		}

		PageContent<PipelineTaskActionPropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(PipelineTaskActionPropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

	private PipelineTaskActionPropertyVO initViewProperty(PipelineTaskActionProperty pipelineTaskActionProperty){

	    PipelineTaskActionPropertyVO vo = new PipelineTaskActionPropertyVO();
        BeanUtils.copyProperties(pipelineTaskActionProperty, vo);


	    //初始化其他对象
	    initTaskTypePropertyGroup(vo, pipelineTaskActionProperty);
        return vo;

	}


	private void initTaskTypePropertyGroup(PipelineTaskActionPropertyVO pipelineTaskActionPropertyVO, PipelineTaskActionProperty pipelineTaskActionProperty){
	
		PipelineTaskAction taskType = pipelineTaskActionService.find(pipelineTaskActionProperty.getTaskType());
		if(taskType == null){
			return;
		}
		PipelineTaskActionVO taskTypeVO = new PipelineTaskActionVO();
		BeanUtils.copyProperties(taskType, taskTypeVO);

		pipelineTaskActionPropertyVO.setTaskTypeVO(taskTypeVO);

	}


}

