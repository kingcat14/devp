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
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskEditDto;
import net.aicoder.speedcloud.business.pipeline.service.PipelineTaskService;
import net.aicoder.speedcloud.business.pipeline.valid.PipelineTaskValidator;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineTaskVO;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageService;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageVO;
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
 * 管理任务
 * @author icode
 */
@Api(description = "任务", tags = "PipelineTask")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinetask")
public class PipelineTaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskController.class);


	@Autowired
	private PipelineTaskService pipelineTaskService;

	@Autowired
	private PipelineStageService pipelineStageService;
	@Autowired
	private PipelineTaskTypeService pipelineTaskTypeService;


	@Autowired
	private PipelineTaskValidator pipelineTaskValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTaskValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增任务
	 * @param pipelineTaskAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增任务", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineTaskVO add(@RequestBody @Valid PipelineTaskAddDto pipelineTaskAddDto){
		PipelineTask pipelineTask = new PipelineTask();
		BeanUtils.copyProperties(pipelineTaskAddDto, pipelineTask);

		pipelineTaskService.add(pipelineTask);

		return  initViewProperty(pipelineTask);
	}

	/**
	 * 删除任务,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTask :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineTaskService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新任务
	 * @param pipelineTaskEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产任务(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	PipelineTaskVO update(@RequestBody @Valid PipelineTaskEditDto pipelineTaskEditDto, @PathVariable Long id){
		PipelineTask pipelineTask = new PipelineTask();
		BeanUtils.copyProperties(pipelineTaskEditDto, pipelineTask);
		pipelineTask.setId(id);
		pipelineTaskService.merge(pipelineTask);

		PipelineTaskVO vo = initViewProperty(pipelineTask);
		return  vo;
	}

	/**
	 * 根据ID查询任务
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineTaskVO get(@PathVariable Long id) {

		PipelineTask pipelineTask = pipelineTaskService.find(id);

		PipelineTaskVO vo = initViewProperty(pipelineTask);
		return vo;
	}

	/**
	 * 查询任务列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineTaskVO> list(@RequestBody PageSearchRequest<PipelineTaskCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<PipelineTask> page = pipelineTaskService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineTaskVO> voList = new ArrayList<>();
		for(PipelineTask pipelineTask : page.getContent()){
			voList.add(initViewProperty(pipelineTask));
		}

		PageContent<PipelineTaskVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出任务列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出任务列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineTaskCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineTaskCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTaskVO> content = this.list(pageSearchRequest);

        List<PipelineTaskVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTaskVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("stage" ,"所属阶段");
            headMap.put("execOrder" ,"执行排序");
            headMap.put("taskType" ,"任务类型");

        String title = new String("任务");
        String fileName = new String(("任务_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineTaskVO initViewProperty(PipelineTask pipelineTask){

	    PipelineTaskVO vo = new PipelineTaskVO();
        BeanUtils.copyProperties(pipelineTask, vo);


	    //初始化其他对象
	    initStagePropertyGroup(vo, pipelineTask);
	    initTaskTypePropertyGroup(vo, pipelineTask);
        return vo;

	}


	private void initStagePropertyGroup(PipelineTaskVO pipelineTaskVO, PipelineTask pipelineTask){
	
		PipelineStage stage = pipelineStageService.find(pipelineTask.getStage());
		if(stage == null){
			return;
		}
		PipelineStageVO stageVO = new PipelineStageVO();
		BeanUtils.copyProperties(stage, stageVO);

		pipelineTaskVO.setStageVO(stageVO);

	}


	private void initTaskTypePropertyGroup(PipelineTaskVO pipelineTaskVO, PipelineTask pipelineTask){
	
		PipelineTaskType taskType = pipelineTaskTypeService.find(pipelineTask.getTaskType());
		if(taskType == null){
			return;
		}
		PipelineTaskTypeVO taskTypeVO = new PipelineTaskTypeVO();
		BeanUtils.copyProperties(taskType, taskTypeVO);

		pipelineTaskVO.setTaskTypeVO(taskTypeVO);

	}


}

