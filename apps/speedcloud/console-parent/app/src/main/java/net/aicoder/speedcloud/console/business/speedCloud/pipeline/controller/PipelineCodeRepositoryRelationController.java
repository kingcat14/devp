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
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineCodeRepositoryRelationVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.service.PipelineCodeRepositoryRelationRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.valid.PipelineCodeRepositoryRelationValidator;

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
 * 管理流水线代码库关联
 * @author icode
 */
@Api(description = "流水线代码库关联", tags = "PipelineCodeRepositoryRelation")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinecoderepositoryrelation")
public class PipelineCodeRepositoryRelationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineCodeRepositoryRelationController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineCodeRepositoryRelationRibbonService pipelineCodeRepositoryRelationRibbonService;

	@Autowired
	PipelineCodeRepositoryRelationValidator pipelineCodeRepositoryRelationValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineCodeRepositoryRelationValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增流水线代码库关联
	 * @param pipelineCodeRepositoryRelationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增流水线代码库关联", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineCodeRepositoryRelationVO add(@RequestBody PipelineCodeRepositoryRelationAddDto pipelineCodeRepositoryRelationAddDto){
		return  pipelineCodeRepositoryRelationRibbonService.add(pipelineCodeRepositoryRelationAddDto);
	}

	/**
	 * 删除流水线代码库关联,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除流水线代码库关联", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineCodeRepositoryRelation :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineCodeRepositoryRelationRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新流水线代码库关联
	 * @param pipelineCodeRepositoryRelationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产流水线代码库关联(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineCodeRepositoryRelationVO update(@RequestBody PipelineCodeRepositoryRelationEditDto pipelineCodeRepositoryRelationEditDto, @ApiParam(value = "要查询的流水线代码库关联id") @PathVariable Long id){

		PipelineCodeRepositoryRelationVO vo = pipelineCodeRepositoryRelationRibbonService.merge(id, pipelineCodeRepositoryRelationEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询流水线代码库关联
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询流水线代码库关联", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineCodeRepositoryRelationVO get(@ApiParam(value = "要查询的流水线代码库关联id") @PathVariable Long id) {

		PipelineCodeRepositoryRelationVO vo = pipelineCodeRepositoryRelationRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询流水线代码库关联列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询流水线代码库关联列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineCodeRepositoryRelationVO> list(@RequestBody PageSearchRequest<PipelineCodeRepositoryRelationCondition> pageSearchRequest){

		PipelineCodeRepositoryRelationCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineCodeRepositoryRelationCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		PageContent<PipelineCodeRepositoryRelationVO> pageContent = pipelineCodeRepositoryRelationRibbonService.list(pageSearchRequest);
		for(PipelineCodeRepositoryRelationVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出流水线代码库关联列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出流水线代码库关联列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineCodeRepositoryRelationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineCodeRepositoryRelationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineCodeRepositoryRelationVO> content = this.list(pageSearchRequest);

        List<PipelineCodeRepositoryRelationVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineCodeRepositoryRelationVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("codeRepository" ,"代码库");
            headMap.put("pipeline" ,"流水线");
            headMap.put("autoStart" ,"提交代码触发流水线");

        String title = new String("流水线代码库关联");
        String fileName = new String(("流水线代码库关联_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineCodeRepositoryRelationVO initViewProperty( PipelineCodeRepositoryRelationVO vo){

	   


	   
        return vo;

	}


}
