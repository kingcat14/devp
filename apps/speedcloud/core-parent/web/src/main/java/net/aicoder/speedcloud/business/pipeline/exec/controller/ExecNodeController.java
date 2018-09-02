package net.aicoder.speedcloud.business.pipeline.exec.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.exec.domain.ExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.service.ExecNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.valid.ExecNodeValidator;
import net.aicoder.speedcloud.business.pipeline.exec.vo.ExecNodeVO;
import net.aicoder.speedcloud.business.pipeline.exec.domain.Exec;
import net.aicoder.speedcloud.business.pipeline.exec.service.ExecService;
import net.aicoder.speedcloud.business.pipeline.exec.vo.ExecVO;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskVO;


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
 * 管理运行实例节点
 * @author icode
 */
@Api(description = "运行实例节点", tags = "ExecNode")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/execnode")
public class ExecNodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecNodeController.class);


	@Autowired
	private ExecNodeService execNodeService;

	@Autowired
	private ExecService execService;
	@Autowired
	private PipelineTaskService pipelineTaskService;


	@Autowired
	private ExecNodeValidator execNodeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(execNodeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运行实例节点
	 * @param execNodeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ExecNodeVO add(@RequestBody @Valid ExecNodeAddDto execNodeAddDto){
		ExecNode execNode = new ExecNode();
		BeanUtils.copyProperties(execNodeAddDto, execNode);

		execNodeService.add(execNode);

		return  initViewProperty(execNode);
	}

	/**
	 * 删除运行实例节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运行实例节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete execNode :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			execNodeService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新运行实例节点
	 * @param execNodeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运行实例节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	ExecNodeVO update(@RequestBody @Valid ExecNodeEditDto execNodeEditDto, @PathVariable Long id){
		ExecNode execNode = new ExecNode();
		BeanUtils.copyProperties(execNodeEditDto, execNode);
		execNode.setId(id);
		execNodeService.merge(execNode);

		ExecNodeVO vo = initViewProperty(execNode);
		return  vo;
	}

	/**
	 * 根据ID查询运行实例节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行实例节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ExecNodeVO get(@PathVariable Long id) {

		ExecNode execNode = execNodeService.find(id);

		ExecNodeVO vo = initViewProperty(execNode);
		return vo;
	}

	/**
	 * 查询运行实例节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运行实例节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ExecNodeVO> list(@RequestBody PageSearchRequest<ExecNodeCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<ExecNode> page = execNodeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ExecNodeVO> voList = new ArrayList<>();
		for(ExecNode execNode : page.getContent()){
			voList.add(initViewProperty(execNode));
		}

		PageContent<ExecNodeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出运行实例节点列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出运行实例节点列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ExecNodeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ExecNodeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ExecNodeVO> content = this.list(pageSearchRequest);

        List<ExecNodeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ExecNodeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("code" ,"编号");
            headMap.put("name" ,"节点名称");
            headMap.put("nodeType" ,"节点类型");
            headMap.put("execMode" ,"执行方式");
            headMap.put("status" ,"运行状态");
            headMap.put("result" ,"运行结果");
            headMap.put("exec" ,"所属实例");
            headMap.put("resultMessage" ,"结果消息");
            headMap.put("startTime" ,"开始时间");
            headMap.put("parentId" ,"上级节点");
            headMap.put("task" ,"关联任务");
            headMap.put("autoStart" ,"自动运行");
            headMap.put("execIndex" ,"节点排序");

        String title = new String("运行实例节点");
        String fileName = new String(("运行实例节点_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ExecNodeVO initViewProperty(ExecNode execNode){

	    ExecNodeVO vo = new ExecNodeVO();
        BeanUtils.copyProperties(execNode, vo);


	    //初始化其他对象
	    initExecPropertyGroup(vo, execNode);
	    initTaskPropertyGroup(vo, execNode);
        return vo;

	}


	private void initExecPropertyGroup(ExecNodeVO execNodeVO, ExecNode execNode){
	
		Exec exec = execService.find(execNode.getExec());
		if(exec == null){
			return;
		}
		ExecVO execVO = new ExecVO();
		BeanUtils.copyProperties(exec, execVO);

		execNodeVO.setExecVO(execVO);

	}


	private void initTaskPropertyGroup(ExecNodeVO execNodeVO, ExecNode execNode){
	
		PipelineTask task = pipelineTaskService.find(execNode.getTask());
		if(task == null){
			return;
		}
		PipelineTaskVO taskVO = new PipelineTaskVO();
		BeanUtils.copyProperties(task, taskVO);

		execNodeVO.setTaskVO(taskVO);

	}


}

