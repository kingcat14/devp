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
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceNodeParamVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service.PipelineExecInstanceNodeParamRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.valid.PipelineExecInstanceNodeParamValidator;

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
 * 管理运行实例节点参数
 * @author icode
 */
@Api(description = "运行实例节点参数", tags = "PipelineExecInstanceNodeParam")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecinstancenodeparam")
public class PipelineExecInstanceNodeParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeParamController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineExecInstanceNodeParamRibbonService pipelineExecInstanceNodeParamRibbonService;

	@Autowired
	PipelineExecInstanceNodeParamValidator pipelineExecInstanceNodeParamValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineExecInstanceNodeParamValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运行实例节点参数
	 * @param pipelineExecInstanceNodeParamAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例节点参数", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineExecInstanceNodeParamVO add(@RequestBody PipelineExecInstanceNodeParamAddDto pipelineExecInstanceNodeParamAddDto){
    	pipelineExecInstanceNodeParamAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  pipelineExecInstanceNodeParamRibbonService.add(pipelineExecInstanceNodeParamAddDto);
	}

	/**
	 * 删除运行实例节点参数,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运行实例节点参数", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineExecInstanceNodeParam :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineExecInstanceNodeParamRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新运行实例节点参数
	 * @param pipelineExecInstanceNodeParamEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运行实例节点参数(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineExecInstanceNodeParamVO update(@RequestBody PipelineExecInstanceNodeParamEditDto pipelineExecInstanceNodeParamEditDto, @ApiParam(value = "要查询的运行实例节点参数id") @PathVariable Long id){

		PipelineExecInstanceNodeParamVO vo = pipelineExecInstanceNodeParamRibbonService.merge(id, pipelineExecInstanceNodeParamEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询运行实例节点参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行实例节点参数", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineExecInstanceNodeParamVO get(@ApiParam(value = "要查询的运行实例节点参数id") @PathVariable Long id) {

		PipelineExecInstanceNodeParamVO vo = pipelineExecInstanceNodeParamRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询运行实例节点参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运行实例节点参数列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineExecInstanceNodeParamVO> list(@RequestBody PageSearchRequest<PipelineExecInstanceNodeParamCondition> pageSearchRequest){

		PipelineExecInstanceNodeParamCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineExecInstanceNodeParamCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineExecInstanceNodeParamVO> pageContent = pipelineExecInstanceNodeParamRibbonService.list(pageSearchRequest);
		for(PipelineExecInstanceNodeParamVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出运行实例节点参数列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出运行实例节点参数列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineExecInstanceNodeParamCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineExecInstanceNodeParamCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineExecInstanceNodeParamVO> content = this.list(pageSearchRequest);

        List<PipelineExecInstanceNodeParamVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineExecInstanceNodeParamVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("node" ,"所属节点");
            headMap.put("name" ,"属性名称");
            headMap.put("code" ,"属性代码");
            headMap.put("value" ,"属性值");
            headMap.put("viewOrder" ,"排序");
            headMap.put("type" ,"类型");

        String title = new String("运行实例节点参数");
        String fileName = new String(("运行实例节点参数_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineExecInstanceNodeParamVO initViewProperty( PipelineExecInstanceNodeParamVO vo){

	   


	   
        return vo;

	}


}
