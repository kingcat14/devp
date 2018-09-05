package net.aicoder.speedcloud.business.pipeline.task.controller;

import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskAction;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import net.aicoder.speedcloud.business.pipeline.task.dto.*;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import net.aicoder.speedcloud.business.pipeline.task.valid.PipelineTaskValidator;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskVO;
import net.aicoder.speedcloud.business.project.domain.Project;
import net.aicoder.speedcloud.business.project.service.ProjectService;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;


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
@RequestMapping(value = "/speedcloud/pipeline/task/pipelinetask")
public class PipelineTaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskController.class);


	@Autowired
	private PipelineTaskService pipelineTaskService;

	@Autowired
	private ProjectService projectService;

    @Autowired
    private SimpleConfigService simpleConfigService;

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

		List<PipelineTaskAction> actionList = getPipelineTaskActions(pipelineTaskAddDto.getActions(), pipelineTaskAddDto.getTid());

		List<PipelineTaskParam> paramList = getPipelineTaskParams(pipelineTaskAddDto.getParams(), pipelineTaskAddDto.getTid());

		PipelineTask pipelineTask = new PipelineTask();
		BeanUtils.copyProperties(pipelineTaskAddDto, pipelineTask);

		pipelineTaskService.create(pipelineTask, actionList, paramList);

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
		PipelineTask pipelineTask = pipelineTaskService.find(id);
		BeanUtils.copyProperties(pipelineTaskEditDto, pipelineTask);

		List<PipelineTaskAction> actionList = getPipelineTaskActions(pipelineTaskEditDto.getActions(), pipelineTask.getTid());
		List<PipelineTaskParam> paramList = getPipelineTaskParams(pipelineTaskEditDto.getParams(), pipelineTask.getTid());

		pipelineTask.setId(id);
		pipelineTaskService.update(pipelineTask, actionList, paramList);

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

            headMap.put("name" ,"任务名称");
            headMap.put("taskType" ,"任务类型");
            headMap.put("execType" ,"执行计划");
            headMap.put("taskStartTime" ,"执行开始时间");
            headMap.put("taskDayOfWeeks" ,"执行日");
            headMap.put("description" ,"任务描述");
            headMap.put("project" ,"所属产品");

        String title = new String("任务");
        String fileName = new String(("任务_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineTaskVO initViewProperty(PipelineTask pipelineTask){

	    PipelineTaskVO vo = new PipelineTaskVO();
        BeanUtils.copyProperties(pipelineTask, vo);

		SimpleConfig taskTypeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINETASK-TASKTYPE", pipelineTask.getTaskType());

		if(taskTypeSimpleConfig!=null) {
		    SimpleConfigVO taskTypeSimpleConfigVO = new SimpleConfigVO();
            BeanUtils.copyProperties(taskTypeSimpleConfig, taskTypeSimpleConfigVO);
			vo.setTaskTypeVO(taskTypeSimpleConfigVO);
		}
		SimpleConfig execTypeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINETASK-EXECTYPE", pipelineTask.getExecType());

		if(execTypeSimpleConfig!=null) {
		    SimpleConfigVO execTypeSimpleConfigVO = new SimpleConfigVO();
            BeanUtils.copyProperties(execTypeSimpleConfig, execTypeSimpleConfigVO);
			vo.setExecTypeVO(execTypeSimpleConfigVO);
		}

	    //初始化其他对象
	    initProjectPropertyGroup(vo, pipelineTask);
        return vo;

	}


	private void initProjectPropertyGroup(PipelineTaskVO pipelineTaskVO, PipelineTask pipelineTask){
	
		Project project = projectService.find(pipelineTask.getProject());
		if(project == null){
			return;
		}
		ProjectVO projectVO = new ProjectVO();
		BeanUtils.copyProperties(project, projectVO);

		pipelineTaskVO.setProjectVO(projectVO);

	}


	private List<PipelineTaskAction> getPipelineTaskActions(List<PipelineTaskActionAddDto> actionAddDtoList, Long tid) {
		List<PipelineTaskAction> actionList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(actionAddDtoList)){
			PipelineTaskAction pipelineTaskAction;
			for(PipelineTaskActionAddDto actionAddDto : actionAddDtoList){
				pipelineTaskAction = new PipelineTaskAction();
				BeanUtils.copyProperties(actionAddDto, pipelineTaskAction);
				pipelineTaskAction.setTid(tid);

				actionList.add(pipelineTaskAction);
			}
		}
		return actionList;
	}

	private List<PipelineTaskParam> getPipelineTaskParams(List<PipelineTaskParamAddDto> paramAddDtoList, Long tid) {
		List<PipelineTaskParam> paramList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(paramAddDtoList)){

			PipelineTaskParam pipelineTaskParam;
			for(PipelineTaskParamAddDto paramAddDto : paramAddDtoList){

				pipelineTaskParam = new PipelineTaskParam();
				BeanUtils.copyProperties(paramAddDto, pipelineTaskParam);
				pipelineTaskParam.setTid(tid);

				paramList.add(pipelineTaskParam);
			}
		}
		return paramList;
	}


}

