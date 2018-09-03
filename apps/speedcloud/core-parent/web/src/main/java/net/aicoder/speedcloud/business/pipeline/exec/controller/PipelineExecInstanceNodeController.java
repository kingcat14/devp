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
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.valid.PipelineExecInstanceNodeValidator;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceNodeVO;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceService;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceVO;
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
@Api(description = "运行实例节点", tags = "PipelineExecInstanceNode")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecinstancenode")
public class PipelineExecInstanceNodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeController.class);


	@Autowired
	private PipelineExecInstanceNodeService pipelineExecInstanceNodeService;

	@Autowired
	private PipelineExecInstanceService pipelineExecInstanceService;
	@Autowired
	private PipelineTaskService pipelineTaskService;


	@Autowired
	private PipelineExecInstanceNodeValidator pipelineExecInstanceNodeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineExecInstanceNodeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运行实例节点
	 * @param pipelineExecInstanceNodeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineExecInstanceNodeVO add(@RequestBody @Valid PipelineExecInstanceNodeAddDto pipelineExecInstanceNodeAddDto){
		PipelineExecInstanceNode pipelineExecInstanceNode = new PipelineExecInstanceNode();
		BeanUtils.copyProperties(pipelineExecInstanceNodeAddDto, pipelineExecInstanceNode);

		pipelineExecInstanceNodeService.add(pipelineExecInstanceNode);

		return  initViewProperty(pipelineExecInstanceNode);
	}

	/**
	 * 删除运行实例节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运行实例节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineExecInstanceNode :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineExecInstanceNodeService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新运行实例节点
	 * @param pipelineExecInstanceNodeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运行实例节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	PipelineExecInstanceNodeVO update(@RequestBody @Valid PipelineExecInstanceNodeEditDto pipelineExecInstanceNodeEditDto, @PathVariable Long id){
		PipelineExecInstanceNode pipelineExecInstanceNode = new PipelineExecInstanceNode();
		BeanUtils.copyProperties(pipelineExecInstanceNodeEditDto, pipelineExecInstanceNode);
		pipelineExecInstanceNode.setId(id);
		pipelineExecInstanceNodeService.merge(pipelineExecInstanceNode);

		PipelineExecInstanceNodeVO vo = initViewProperty(pipelineExecInstanceNode);
		return  vo;
	}

	/**
	 * 根据ID查询运行实例节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行实例节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineExecInstanceNodeVO get(@PathVariable Long id) {

		PipelineExecInstanceNode pipelineExecInstanceNode = pipelineExecInstanceNodeService.find(id);

		PipelineExecInstanceNodeVO vo = initViewProperty(pipelineExecInstanceNode);
		return vo;
	}

	/**
	 * 查询运行实例节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运行实例节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineExecInstanceNodeVO> list(@RequestBody PageSearchRequest<PipelineExecInstanceNodeCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<PipelineExecInstanceNode> page = pipelineExecInstanceNodeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineExecInstanceNodeVO> voList = new ArrayList<>();
		for(PipelineExecInstanceNode pipelineExecInstanceNode : page.getContent()){
			voList.add(initViewProperty(pipelineExecInstanceNode));
		}

		PageContent<PipelineExecInstanceNodeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(PipelineExecInstanceNodeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineExecInstanceNodeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineExecInstanceNodeVO> content = this.list(pageSearchRequest);

        List<PipelineExecInstanceNodeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineExecInstanceNodeVO vo : voList){
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

	private PipelineExecInstanceNodeVO initViewProperty(PipelineExecInstanceNode pipelineExecInstanceNode){

	    PipelineExecInstanceNodeVO vo = new PipelineExecInstanceNodeVO();
        BeanUtils.copyProperties(pipelineExecInstanceNode, vo);


	    //初始化其他对象
	    initExecPropertyGroup(vo, pipelineExecInstanceNode);
	    initTaskPropertyGroup(vo, pipelineExecInstanceNode);
        return vo;

	}


	private void initExecPropertyGroup(PipelineExecInstanceNodeVO pipelineExecInstanceNodeVO, PipelineExecInstanceNode pipelineExecInstanceNode){
	
		PipelineExecInstance exec = pipelineExecInstanceService.find(pipelineExecInstanceNode.getExec());
		if(exec == null){
			return;
		}
		PipelineExecInstanceVO execVO = new PipelineExecInstanceVO();
		BeanUtils.copyProperties(exec, execVO);

		pipelineExecInstanceNodeVO.setExecVO(execVO);

	}


	private void initTaskPropertyGroup(PipelineExecInstanceNodeVO pipelineExecInstanceNodeVO, PipelineExecInstanceNode pipelineExecInstanceNode){
	
		PipelineTask task = pipelineTaskService.find(pipelineExecInstanceNode.getTask());
		if(task == null){
			return;
		}
		PipelineTaskVO taskVO = new PipelineTaskVO();
		BeanUtils.copyProperties(task, taskVO);

		pipelineExecInstanceNodeVO.setTaskVO(taskVO);

	}


}

