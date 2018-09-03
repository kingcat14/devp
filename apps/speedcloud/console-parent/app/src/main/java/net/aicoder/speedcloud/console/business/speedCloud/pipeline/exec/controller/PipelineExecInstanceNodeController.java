package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceNodeVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service.PipelineExecInstanceNodeRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.valid.PipelineExecInstanceNodeValidator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
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
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineExecInstanceNodeRibbonService pipelineExecInstanceNodeRibbonService;

	@Autowired
	PipelineExecInstanceNodeValidator pipelineExecInstanceNodeValidator;


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
	public PipelineExecInstanceNodeVO add(@RequestBody PipelineExecInstanceNodeAddDto pipelineExecInstanceNodeAddDto){
    	pipelineExecInstanceNodeAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  pipelineExecInstanceNodeRibbonService.add(pipelineExecInstanceNodeAddDto);
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
			pipelineExecInstanceNodeRibbonService.delete(Long.parseLong(id));
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
	public PipelineExecInstanceNodeVO update(@RequestBody PipelineExecInstanceNodeEditDto pipelineExecInstanceNodeEditDto, @ApiParam(value = "要查询的运行实例节点id") @PathVariable Long id){

		PipelineExecInstanceNodeVO vo = pipelineExecInstanceNodeRibbonService.merge(id, pipelineExecInstanceNodeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询运行实例节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行实例节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineExecInstanceNodeVO get(@ApiParam(value = "要查询的运行实例节点id") @PathVariable Long id) {

		PipelineExecInstanceNodeVO vo = pipelineExecInstanceNodeRibbonService.find(id);
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

		PipelineExecInstanceNodeCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineExecInstanceNodeCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineExecInstanceNodeVO> pageContent = pipelineExecInstanceNodeRibbonService.list(pageSearchRequest);
		for(PipelineExecInstanceNodeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

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
    public void export(PipelineExecInstanceNodeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

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


	private PipelineExecInstanceNodeVO initViewProperty( PipelineExecInstanceNodeVO vo){

	   


	   
        return vo;

	}


}
