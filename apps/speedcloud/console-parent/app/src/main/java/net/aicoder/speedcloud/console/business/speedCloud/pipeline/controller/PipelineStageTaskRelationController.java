package net.aicoder.speedcloud.console.business.speedCloud.pipeline.controller;

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
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageTaskRelationVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.service.PipelineStageTaskRelationRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.valid.PipelineStageTaskRelationValidator;

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
 * 管理阶段任务关联
 * @author icode
 */
@Api(description = "阶段任务关联", tags = "PipelineStageTaskRelation")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinestagetaskrelation")
public class PipelineStageTaskRelationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageTaskRelationController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineStageTaskRelationRibbonService pipelineStageTaskRelationRibbonService;

	@Autowired
	PipelineStageTaskRelationValidator pipelineStageTaskRelationValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineStageTaskRelationValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增阶段任务关联
	 * @param pipelineStageTaskRelationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增阶段任务关联", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineStageTaskRelationVO add(@RequestBody PipelineStageTaskRelationAddDto pipelineStageTaskRelationAddDto){
    	pipelineStageTaskRelationAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  pipelineStageTaskRelationRibbonService.add(pipelineStageTaskRelationAddDto);
	}

	/**
	 * 删除阶段任务关联,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除阶段任务关联", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineStageTaskRelation :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineStageTaskRelationRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新阶段任务关联
	 * @param pipelineStageTaskRelationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产阶段任务关联(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineStageTaskRelationVO update(@RequestBody PipelineStageTaskRelationEditDto pipelineStageTaskRelationEditDto, @ApiParam(value = "要查询的阶段任务关联id") @PathVariable Long id){

		PipelineStageTaskRelationVO vo = pipelineStageTaskRelationRibbonService.merge(id, pipelineStageTaskRelationEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询阶段任务关联
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询阶段任务关联", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineStageTaskRelationVO get(@ApiParam(value = "要查询的阶段任务关联id") @PathVariable Long id) {

		PipelineStageTaskRelationVO vo = pipelineStageTaskRelationRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询阶段任务关联列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询阶段任务关联列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineStageTaskRelationVO> list(@RequestBody PageSearchRequest<PipelineStageTaskRelationCondition> pageSearchRequest){

		PipelineStageTaskRelationCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineStageTaskRelationCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineStageTaskRelationVO> pageContent = pipelineStageTaskRelationRibbonService.list(pageSearchRequest);
		for(PipelineStageTaskRelationVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出阶段任务关联列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出阶段任务关联列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineStageTaskRelationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineStageTaskRelationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineStageTaskRelationVO> content = this.list(pageSearchRequest);

        List<PipelineStageTaskRelationVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineStageTaskRelationVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("stage" ,"阶段");
            headMap.put("task" ,"任务");
            headMap.put("execOrder" ,"执行排序");

        String title = new String("阶段任务关联");
        String fileName = new String(("阶段任务关联_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineStageTaskRelationVO initViewProperty( PipelineStageTaskRelationVO vo){

	   


	   
        return vo;

	}


}
