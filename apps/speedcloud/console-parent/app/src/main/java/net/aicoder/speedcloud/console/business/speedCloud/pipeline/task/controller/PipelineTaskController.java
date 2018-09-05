package net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceVO;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskEditDto;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service.PipelineExecInstanceRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.service.PipelineTaskRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.valid.PipelineTaskValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineTaskRibbonService pipelineTaskRibbonService;

	@Autowired
	PipelineTaskValidator pipelineTaskValidator;

	@Autowired
	private PipelineExecInstanceRibbonService pipelineExecInstanceRibbonService;

    @Autowired
    private SimpleConfigService simpleConfigService;

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
	public PipelineTaskVO add(@RequestBody PipelineTaskAddDto pipelineTaskAddDto){
    	pipelineTaskAddDto.setTid(saaSUtil.getAccount().getTenantId());
    	if(CollectionUtils.isNotEmpty(pipelineTaskAddDto.getActions())){
    		List<PipelineTaskActionAddDto> actionAddDtoList = pipelineTaskAddDto.getActions();
    		for(PipelineTaskActionAddDto actionAddDto : actionAddDtoList){
    			actionAddDto.setTid(saaSUtil.getAccount().getId());
			}
		}
		return  pipelineTaskRibbonService.add(pipelineTaskAddDto);
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
			pipelineTaskRibbonService.delete(Long.parseLong(id));
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
	public PipelineTaskVO update(@RequestBody PipelineTaskEditDto pipelineTaskEditDto, @ApiParam(value = "要查询的任务id") @PathVariable Long id){

		PipelineTaskVO vo = pipelineTaskRibbonService.merge(id, pipelineTaskEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询任务
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineTaskVO get(@ApiParam(value = "要查询的任务id") @PathVariable Long id) {

		PipelineTaskVO vo = pipelineTaskRibbonService.find(id);
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

		PipelineTaskCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineTaskCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineTaskVO> pageContent = pipelineTaskRibbonService.list(pageSearchRequest);
		for(PipelineTaskVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

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
    public void export(PipelineTaskCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

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


	private PipelineTaskVO initViewProperty( PipelineTaskVO vo){

	   

		SimpleConfig taskTypeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINETASK-TASKTYPE", vo.getTaskType());

		if(taskTypeSimpleConfig!=null) {

		    SimpleConfigVO taskTypeSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(taskTypeSimpleConfig, taskTypeSimpleConfigVO);
		    vo.setTaskTypeVO(taskTypeSimpleConfigVO);
		}
		SimpleConfig execTypeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINETASK-EXECTYPE", vo.getExecType());

		if(execTypeSimpleConfig!=null) {

		    SimpleConfigVO execTypeSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(execTypeSimpleConfig, execTypeSimpleConfigVO);
		    vo.setExecTypeVO(execTypeSimpleConfigVO);
		}


		PipelineExecInstanceVO execVO = pipelineExecInstanceRibbonService.getLastExec(vo.getId());
		vo.setLastExecVO(execVO);

	   
        return vo;

	}


}
