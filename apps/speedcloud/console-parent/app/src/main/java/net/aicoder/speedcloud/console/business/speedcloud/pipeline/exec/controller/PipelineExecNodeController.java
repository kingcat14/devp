package net.aicoder.speedcloud.console.business.speedcloud.pipeline.exec.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.exec.service.PipelineExecNodeRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.exec.valid.PipelineExecNodeValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理运行实例节点
 * @author icode
 */
@Api(description = "运行实例节点", tags = "PipelineExecNode")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecnode")
public class PipelineExecNodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineExecNodeRibbonService pipelineExecNodeRibbonService;

	@Autowired
	PipelineExecNodeValidator pipelineExecNodeValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineExecNodeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运行实例节点
	 * @param pipelineExecNodeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineExecNodeVO add(@RequestBody PipelineExecNodeAddDto pipelineExecNodeAddDto){
    	pipelineExecNodeAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  pipelineExecNodeRibbonService.add(pipelineExecNodeAddDto);
	}

	/**
	 * 删除运行实例节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运行实例节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineExecNode :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineExecNodeRibbonService.delete(Long.parseLong(id));
		}

	}



	/**
	 * 根据ID查询运行实例节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行实例节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineExecNodeVO get(@ApiParam(value = "要查询的运行实例节点id") @PathVariable Long id) {

		PipelineExecNodeVO vo = pipelineExecNodeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询运行实例节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运行实例节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineExecNodeVO> list(@RequestBody PageSearchRequest<PipelineExecNodeCondition> pageSearchRequest){

		PipelineExecNodeCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineExecNodeCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineExecNodeVO> pageContent = pipelineExecNodeRibbonService.list(pageSearchRequest);
		for(PipelineExecNodeVO vo : pageContent.getContent()){
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
    public void export(PipelineExecNodeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineExecNodeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineExecNodeVO> content = this.list(pageSearchRequest);

        List<PipelineExecNodeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineExecNodeVO vo : voList){
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


	private PipelineExecNodeVO initViewProperty( PipelineExecNodeVO vo){

	   


	   
        return vo;

	}


}
