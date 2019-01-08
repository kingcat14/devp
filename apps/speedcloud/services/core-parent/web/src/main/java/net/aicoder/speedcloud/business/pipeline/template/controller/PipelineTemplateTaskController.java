package net.aicoder.speedcloud.business.pipeline.template.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.config.domain.PipelineTaskType;
import net.aicoder.speedcloud.business.config.service.PipelineTaskTypeService;
import net.aicoder.speedcloud.business.config.vo.PipelineTaskTypeVO;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTask;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskAction;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskParam;
import net.aicoder.speedcloud.business.pipeline.template.dto.*;
import net.aicoder.speedcloud.business.pipeline.template.service.PipelineTemplateTaskService;
import net.aicoder.speedcloud.business.pipeline.template.valid.PipelineTemplateTaskValidator;
import net.aicoder.speedcloud.business.pipeline.template.vo.PipelineTemplateTaskVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理任务模板
 * @author icode
 */
@Api(description = "任务模板", tags = "PipelineTemplateTask")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/template/pipelinetemplatetask")
public class PipelineTemplateTaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTemplateTaskController.class);


	@Autowired
	private PipelineTemplateTaskService pipelineTemplateTaskService;

	@Autowired
	private PipelineTaskTypeService pipelineTaskTypeService;

    @Autowired
    private SimpleConfigService simpleConfigService;

	@Autowired
	private PipelineTemplateTaskValidator pipelineTemplateTaskValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTemplateTaskValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增任务模板
	 * @param pipelineTemplateTaskAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增任务模板", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.template.pipelinetemplatetask.add", count = true)
	public PipelineTemplateTaskVO add(@RequestBody @Valid PipelineTemplateTaskAddDto pipelineTemplateTaskAddDto){
		List<PipelineTemplateTaskAction> actionList = getPipelineTemplateTaskActions(pipelineTemplateTaskAddDto.getActions(), pipelineTemplateTaskAddDto.getTid());

		List<PipelineTemplateTaskParam> paramList = getPipelineTemplateTaskParams(pipelineTemplateTaskAddDto.getParams(), pipelineTemplateTaskAddDto.getTid());

		PipelineTemplateTask pipelineTemplateTask = new PipelineTemplateTask();
		BeanUtils.copyProperties(pipelineTemplateTaskAddDto, pipelineTemplateTask);

		pipelineTemplateTaskService.create(pipelineTemplateTask, actionList, paramList);

		return  initViewProperty(pipelineTemplateTask);

	}

	/**
	 * 删除任务模板,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务模板", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.template.pipelinetemplatetask.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTemplateTask :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineTemplateTaskService.delete(id);
		}

	}

	/**
	 * 更新任务模板
	 * @param pipelineTemplateTaskEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改任务模板(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.template.pipelinetemplatetask.update", count = true)
	public	PipelineTemplateTaskVO update(@RequestBody @Valid PipelineTemplateTaskEditDto pipelineTemplateTaskEditDto, @PathVariable String id){
		PipelineTemplateTask pipelineTask = pipelineTemplateTaskService.find(id);
		BeanUtils.copyProperties(pipelineTemplateTaskEditDto, pipelineTask);

		List<PipelineTemplateTaskAction> actionList = getPipelineTemplateTaskActions(pipelineTemplateTaskEditDto.getActions(), pipelineTask.getTid());
		List<PipelineTemplateTaskParam> paramList = getPipelineTemplateTaskParams(pipelineTemplateTaskEditDto.getParams(), pipelineTask.getTid());

		pipelineTask.setId(id);
		pipelineTemplateTaskService.update(pipelineTask, actionList, paramList);

		PipelineTemplateTaskVO vo = initViewProperty(pipelineTask);
		return  vo;
	}

	/**
	 * 根据ID查询任务模板
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询任务模板", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.template.pipelinetemplatetask.get")
	public  PipelineTemplateTaskVO get(@PathVariable String id) {

		PipelineTemplateTask pipelineTemplateTask = pipelineTemplateTaskService.find(id);
		if(pipelineTemplateTask == null){
			throw new ResourceNotFoundException("找不到指定的任务模板，请检查ID");
		}
		PipelineTemplateTaskVO vo = initViewProperty(pipelineTemplateTask);
		return vo;
	}

	/**
	 * 查询任务模板列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务模板列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "speedcloud.pipeline.template.pipelinetemplatetask.list")
	public PageContent<PipelineTemplateTaskVO> list(@RequestBody PageSearchRequest<PipelineTemplateTaskCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<PipelineTemplateTask> page = pipelineTemplateTaskService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineTemplateTaskVO> voList = new ArrayList<>();
		for(PipelineTemplateTask pipelineTemplateTask : page.getContent()){
			voList.add(initViewProperty(pipelineTemplateTask));
		}

		PageContent<PipelineTemplateTaskVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出任务模板列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出任务模板列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(PipelineTemplateTaskCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineTemplateTaskCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTemplateTaskVO> content = this.list(pageSearchRequest);

        List<PipelineTemplateTaskVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTemplateTaskVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("name" ,"任务名称");
        headMap.put("taskType" ,"任务类型");
        headMap.put("execType" ,"执行计划");
        headMap.put("taskStartTime" ,"执行开始时间");
        headMap.put("taskDayOfWeeks" ,"执行日");
        headMap.put("description" ,"任务描述");

        String title = new String("任务模板");
        String fileName = new String(("任务模板_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineTemplateTaskVO initViewProperty(PipelineTemplateTask pipelineTemplateTask){

	    PipelineTemplateTaskVO vo = new PipelineTemplateTaskVO();
        BeanUtils.copyProperties(pipelineTemplateTask, vo);

		SimpleConfig execTypeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINETEMPLATETASK-EXECTYPE", pipelineTemplateTask.getExecType());

		if(execTypeSimpleConfig!=null) {
		    SimpleConfigVO execTypeSimpleConfigVO = new SimpleConfigVO();
            BeanUtils.copyProperties(execTypeSimpleConfig, execTypeSimpleConfigVO);
			vo.setExecTypeVO(execTypeSimpleConfigVO);
		}

	    //初始化其他对象
	    initTaskTypePropertyGroup(vo, pipelineTemplateTask);
        return vo;

	}

	private void initTaskTypePropertyGroup(PipelineTemplateTaskVO pipelineTemplateTaskVO, PipelineTemplateTask pipelineTemplateTask){
	
		PipelineTaskType taskType = pipelineTaskTypeService.find(pipelineTemplateTask.getTaskType());
		if(taskType == null){
			return;
		}
		PipelineTaskTypeVO taskTypeVO = new PipelineTaskTypeVO();
		BeanUtils.copyProperties(taskType, taskTypeVO);

		pipelineTemplateTaskVO.setTaskTypeVO(taskTypeVO);

	}

	private List<PipelineTemplateTaskAction> getPipelineTemplateTaskActions(List<PipelineTemplateTaskActionAddDto> actionAddDtoList, Long tid) {
		List<PipelineTemplateTaskAction> actionList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(actionAddDtoList)){
			PipelineTemplateTaskAction pipelineTaskAction;
			for(PipelineTemplateTaskActionAddDto actionAddDto : actionAddDtoList){
				pipelineTaskAction = new PipelineTemplateTaskAction();
				BeanUtils.copyProperties(actionAddDto, pipelineTaskAction);
				pipelineTaskAction.setTid(tid);

				actionList.add(pipelineTaskAction);
			}
		}
		return actionList;
	}

	private List<PipelineTemplateTaskParam> getPipelineTemplateTaskParams(List<PipelineTemplateTaskParamAddDto> paramAddDtoList, Long tid) {
		List<PipelineTemplateTaskParam> paramList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(paramAddDtoList)){

			PipelineTemplateTaskParam pipelineTaskParam;
			for(PipelineTemplateTaskParamAddDto paramAddDto : paramAddDtoList){

				pipelineTaskParam = new PipelineTemplateTaskParam();
				BeanUtils.copyProperties(paramAddDto, pipelineTaskParam);
				pipelineTaskParam.setTid(tid);

				paramList.add(pipelineTaskParam);
			}
		}
		return paramList;
	}


}

