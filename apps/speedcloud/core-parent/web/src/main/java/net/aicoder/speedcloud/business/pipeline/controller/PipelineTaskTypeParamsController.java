package net.aicoder.speedcloud.business.pipeline.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTaskTypeParams;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsEditDto;
import net.aicoder.speedcloud.business.pipeline.service.PipelineTaskTypeParamsService;
import net.aicoder.speedcloud.business.pipeline.valid.PipelineTaskTypeParamsValidator;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineTaskTypeParamsVO;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTaskType;
import net.aicoder.speedcloud.business.pipeline.service.PipelineTaskTypeService;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineTaskTypeVO;


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
 * 管理任务类型参数定义
 * @author icode
 */
@Api(description = "任务类型参数定义", tags = "PipelineTaskTypeParams")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinetasktypeparams")
public class PipelineTaskTypeParamsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeParamsController.class);


	@Autowired
	private PipelineTaskTypeParamsService pipelineTaskTypeParamsService;

	@Autowired
	private PipelineTaskTypeService pipelineTaskTypeService;


	@Autowired
	private PipelineTaskTypeParamsValidator pipelineTaskTypeParamsValidator;

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
	public PipelineTaskTypeParamsVO add(@RequestBody @Valid PipelineTaskTypeParamsAddDto pipelineTaskTypeParamsAddDto){
		PipelineTaskTypeParams pipelineTaskTypeParams = new PipelineTaskTypeParams();
		BeanUtils.copyProperties(pipelineTaskTypeParamsAddDto, pipelineTaskTypeParams);

		pipelineTaskTypeParamsService.add(pipelineTaskTypeParams);

		return  initViewProperty(pipelineTaskTypeParams);
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
			pipelineTaskTypeParamsService.delete(Long.parseLong(id));
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
	public	PipelineTaskTypeParamsVO update(@RequestBody @Valid PipelineTaskTypeParamsEditDto pipelineTaskTypeParamsEditDto, @PathVariable Long id){
		PipelineTaskTypeParams pipelineTaskTypeParams = new PipelineTaskTypeParams();
		BeanUtils.copyProperties(pipelineTaskTypeParamsEditDto, pipelineTaskTypeParams);
		pipelineTaskTypeParams.setId(id);
		pipelineTaskTypeParamsService.merge(pipelineTaskTypeParams);

		PipelineTaskTypeParamsVO vo = initViewProperty(pipelineTaskTypeParams);
		return  vo;
	}

	/**
	 * 根据ID查询任务类型参数定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务类型参数定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineTaskTypeParamsVO get(@PathVariable Long id) {

		PipelineTaskTypeParams pipelineTaskTypeParams = pipelineTaskTypeParamsService.find(id);

		PipelineTaskTypeParamsVO vo = initViewProperty(pipelineTaskTypeParams);
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

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<PipelineTaskTypeParams> page = pipelineTaskTypeParamsService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineTaskTypeParamsVO> voList = new ArrayList<>();
		for(PipelineTaskTypeParams pipelineTaskTypeParams : page.getContent()){
			voList.add(initViewProperty(pipelineTaskTypeParams));
		}

		PageContent<PipelineTaskTypeParamsVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(PipelineTaskTypeParamsCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

	private PipelineTaskTypeParamsVO initViewProperty(PipelineTaskTypeParams pipelineTaskTypeParams){

	    PipelineTaskTypeParamsVO vo = new PipelineTaskTypeParamsVO();
        BeanUtils.copyProperties(pipelineTaskTypeParams, vo);


	    //初始化其他对象
	    initTaskTypePropertyGroup(vo, pipelineTaskTypeParams);
        return vo;

	}


	private void initTaskTypePropertyGroup(PipelineTaskTypeParamsVO pipelineTaskTypeParamsVO, PipelineTaskTypeParams pipelineTaskTypeParams){
	
		PipelineTaskType taskType = pipelineTaskTypeService.find(pipelineTaskTypeParams.getTaskType());
		if(taskType == null){
			return;
		}
		PipelineTaskTypeVO taskTypeVO = new PipelineTaskTypeVO();
		BeanUtils.copyProperties(taskType, taskTypeVO);

		pipelineTaskTypeParamsVO.setTaskTypeVO(taskTypeVO);

	}


}

