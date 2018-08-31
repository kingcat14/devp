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
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTaskType;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeEditDto;
import net.aicoder.speedcloud.business.pipeline.service.PipelineTaskTypeService;
import net.aicoder.speedcloud.business.pipeline.valid.PipelineTaskTypeValidator;
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
 * 管理任务类型
 * @author icode
 */
@Api(description = "任务类型", tags = "PipelineTaskType")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinetasktype")
public class PipelineTaskTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeController.class);


	@Autowired
	private PipelineTaskTypeService pipelineTaskTypeService;



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
	public PipelineTaskTypeVO add(@RequestBody @Valid PipelineTaskTypeAddDto pipelineTaskTypeAddDto){
		PipelineTaskType pipelineTaskType = new PipelineTaskType();
		BeanUtils.copyProperties(pipelineTaskTypeAddDto, pipelineTaskType);

		pipelineTaskTypeService.add(pipelineTaskType);

		return  initViewProperty(pipelineTaskType);
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
			pipelineTaskTypeService.delete(Long.parseLong(id));
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
	public	PipelineTaskTypeVO update(@RequestBody @Valid PipelineTaskTypeEditDto pipelineTaskTypeEditDto, @PathVariable Long id){
		PipelineTaskType pipelineTaskType = new PipelineTaskType();
		BeanUtils.copyProperties(pipelineTaskTypeEditDto, pipelineTaskType);
		pipelineTaskType.setId(id);
		pipelineTaskTypeService.merge(pipelineTaskType);

		PipelineTaskTypeVO vo = initViewProperty(pipelineTaskType);
		return  vo;
	}

	/**
	 * 根据ID查询任务类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务类型", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineTaskTypeVO get(@PathVariable Long id) {

		PipelineTaskType pipelineTaskType = pipelineTaskTypeService.find(id);

		PipelineTaskTypeVO vo = initViewProperty(pipelineTaskType);
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

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<PipelineTaskType> page = pipelineTaskTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineTaskTypeVO> voList = new ArrayList<>();
		for(PipelineTaskType pipelineTaskType : page.getContent()){
			voList.add(initViewProperty(pipelineTaskType));
		}

		PageContent<PipelineTaskTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(PipelineTaskTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

	private PipelineTaskTypeVO initViewProperty(PipelineTaskType pipelineTaskType){

	    PipelineTaskTypeVO vo = new PipelineTaskTypeVO();
        BeanUtils.copyProperties(pipelineTaskType, vo);


	    //初始化其他对象
        return vo;

	}


}

