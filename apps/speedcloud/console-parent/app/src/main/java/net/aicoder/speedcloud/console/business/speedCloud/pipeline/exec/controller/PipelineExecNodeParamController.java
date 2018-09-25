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
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeParamVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service.PipelineExecNodeParamRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.valid.PipelineExecNodeParamValidator;

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
@Api(description = "运行实例节点参数", tags = "PipelineExecNodeParam")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecnodeparam")
public class PipelineExecNodeParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeParamController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineExecNodeParamRibbonService pipelineExecNodeParamRibbonService;

	@Autowired
	PipelineExecNodeParamValidator pipelineExecNodeParamValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineExecNodeParamValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运行实例节点参数
	 * @param pipelineExecNodeParamAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例节点参数", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineExecNodeParamVO add(@RequestBody PipelineExecNodeParamAddDto pipelineExecNodeParamAddDto){
    	pipelineExecNodeParamAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  pipelineExecNodeParamRibbonService.add(pipelineExecNodeParamAddDto);
	}

	/**
	 * 删除运行实例节点参数,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运行实例节点参数", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineExecNodeParam :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineExecNodeParamRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新运行实例节点参数
	 * @param pipelineExecNodeParamEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运行实例节点参数(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineExecNodeParamVO update(@RequestBody PipelineExecNodeParamEditDto pipelineExecNodeParamEditDto, @ApiParam(value = "要查询的运行实例节点参数id") @PathVariable Long id){

		PipelineExecNodeParamVO vo = pipelineExecNodeParamRibbonService.merge(id, pipelineExecNodeParamEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询运行实例节点参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行实例节点参数", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineExecNodeParamVO get(@ApiParam(value = "要查询的运行实例节点参数id") @PathVariable Long id) {

		PipelineExecNodeParamVO vo = pipelineExecNodeParamRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询运行实例节点参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运行实例节点参数列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineExecNodeParamVO> list(@RequestBody PageSearchRequest<PipelineExecNodeParamCondition> pageSearchRequest){

		PipelineExecNodeParamCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineExecNodeParamCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineExecNodeParamVO> pageContent = pipelineExecNodeParamRibbonService.list(pageSearchRequest);
		for(PipelineExecNodeParamVO vo : pageContent.getContent()){
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
    public void export(PipelineExecNodeParamCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineExecNodeParamCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineExecNodeParamVO> content = this.list(pageSearchRequest);

        List<PipelineExecNodeParamVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineExecNodeParamVO vo : voList){
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


	private PipelineExecNodeParamVO initViewProperty( PipelineExecNodeParamVO vo){

	   


	   
        return vo;

	}


}
