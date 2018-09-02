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
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.ExecNodeVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service.ExecNodeRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.valid.ExecNodeValidator;

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
@Api(description = "运行实例节点", tags = "ExecNode")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/execnode")
public class ExecNodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecNodeController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private ExecNodeRibbonService execNodeRibbonService;

	@Autowired
	ExecNodeValidator execNodeValidator;


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
	public ExecNodeVO add(@RequestBody ExecNodeAddDto execNodeAddDto){
    	execNodeAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  execNodeRibbonService.add(execNodeAddDto);
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
			execNodeRibbonService.delete(Long.parseLong(id));
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
	public ExecNodeVO update(@RequestBody ExecNodeEditDto execNodeEditDto, @ApiParam(value = "要查询的运行实例节点id") @PathVariable Long id){

		ExecNodeVO vo = execNodeRibbonService.merge(id, execNodeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询运行实例节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行实例节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public ExecNodeVO get(@ApiParam(value = "要查询的运行实例节点id") @PathVariable Long id) {

		ExecNodeVO vo = execNodeRibbonService.find(id);
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

		ExecNodeCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new ExecNodeCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<ExecNodeVO> pageContent = execNodeRibbonService.list(pageSearchRequest);
		for(ExecNodeVO vo : pageContent.getContent()){
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
    public void export(ExecNodeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

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


	private ExecNodeVO initViewProperty( ExecNodeVO vo){

	   


	   
        return vo;

	}


}
