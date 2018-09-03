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
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParams;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeParamsService;
import net.aicoder.speedcloud.business.pipeline.exec.valid.PipelineExecInstanceNodeParamsValidator;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceNodeParamsVO;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceNodeVO;


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
 * 管理运行实例节点参数
 * @author icode
 */
@Api(description = "运行实例节点参数", tags = "PipelineExecInstanceNodeParams")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecinstancenodeparams")
public class PipelineExecInstanceNodeParamsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeParamsController.class);


	@Autowired
	private PipelineExecInstanceNodeParamsService pipelineExecInstanceNodeParamsService;

	@Autowired
	private PipelineExecInstanceNodeService pipelineExecInstanceNodeService;


	@Autowired
	private PipelineExecInstanceNodeParamsValidator pipelineExecInstanceNodeParamsValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineExecInstanceNodeParamsValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运行实例节点参数
	 * @param pipelineExecInstanceNodeParamsAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例节点参数", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineExecInstanceNodeParamsVO add(@RequestBody @Valid PipelineExecInstanceNodeParamsAddDto pipelineExecInstanceNodeParamsAddDto){
		PipelineExecInstanceNodeParams pipelineExecInstanceNodeParams = new PipelineExecInstanceNodeParams();
		BeanUtils.copyProperties(pipelineExecInstanceNodeParamsAddDto, pipelineExecInstanceNodeParams);

		pipelineExecInstanceNodeParamsService.add(pipelineExecInstanceNodeParams);

		return  initViewProperty(pipelineExecInstanceNodeParams);
	}

	/**
	 * 删除运行实例节点参数,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运行实例节点参数", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineExecInstanceNodeParams :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineExecInstanceNodeParamsService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新运行实例节点参数
	 * @param pipelineExecInstanceNodeParamsEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运行实例节点参数(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	PipelineExecInstanceNodeParamsVO update(@RequestBody @Valid PipelineExecInstanceNodeParamsEditDto pipelineExecInstanceNodeParamsEditDto, @PathVariable Long id){
		PipelineExecInstanceNodeParams pipelineExecInstanceNodeParams = new PipelineExecInstanceNodeParams();
		BeanUtils.copyProperties(pipelineExecInstanceNodeParamsEditDto, pipelineExecInstanceNodeParams);
		pipelineExecInstanceNodeParams.setId(id);
		pipelineExecInstanceNodeParamsService.merge(pipelineExecInstanceNodeParams);

		PipelineExecInstanceNodeParamsVO vo = initViewProperty(pipelineExecInstanceNodeParams);
		return  vo;
	}

	/**
	 * 根据ID查询运行实例节点参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行实例节点参数", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineExecInstanceNodeParamsVO get(@PathVariable Long id) {

		PipelineExecInstanceNodeParams pipelineExecInstanceNodeParams = pipelineExecInstanceNodeParamsService.find(id);

		PipelineExecInstanceNodeParamsVO vo = initViewProperty(pipelineExecInstanceNodeParams);
		return vo;
	}

	/**
	 * 查询运行实例节点参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运行实例节点参数列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineExecInstanceNodeParamsVO> list(@RequestBody PageSearchRequest<PipelineExecInstanceNodeParamsCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<PipelineExecInstanceNodeParams> page = pipelineExecInstanceNodeParamsService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineExecInstanceNodeParamsVO> voList = new ArrayList<>();
		for(PipelineExecInstanceNodeParams pipelineExecInstanceNodeParams : page.getContent()){
			voList.add(initViewProperty(pipelineExecInstanceNodeParams));
		}

		PageContent<PipelineExecInstanceNodeParamsVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(PipelineExecInstanceNodeParamsCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineExecInstanceNodeParamsCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineExecInstanceNodeParamsVO> content = this.list(pageSearchRequest);

        List<PipelineExecInstanceNodeParamsVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineExecInstanceNodeParamsVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("taskType" ,"所属操作");
            headMap.put("name" ,"属性名称");
            headMap.put("code" ,"属性代码");
            headMap.put("value" ,"属性值");
            headMap.put("viewOrder" ,"排序");
            headMap.put("type" ,"类型");

        String title = new String("运行实例节点参数");
        String fileName = new String(("运行实例节点参数_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineExecInstanceNodeParamsVO initViewProperty(PipelineExecInstanceNodeParams pipelineExecInstanceNodeParams){

	    PipelineExecInstanceNodeParamsVO vo = new PipelineExecInstanceNodeParamsVO();
        BeanUtils.copyProperties(pipelineExecInstanceNodeParams, vo);


	    //初始化其他对象
	    initTaskTypePropertyGroup(vo, pipelineExecInstanceNodeParams);
        return vo;

	}


	private void initTaskTypePropertyGroup(PipelineExecInstanceNodeParamsVO pipelineExecInstanceNodeParamsVO, PipelineExecInstanceNodeParams pipelineExecInstanceNodeParams){
	
		PipelineExecInstanceNode taskType = pipelineExecInstanceNodeService.find(pipelineExecInstanceNodeParams.getTaskType());
		if(taskType == null){
			return;
		}
		PipelineExecInstanceNodeVO taskTypeVO = new PipelineExecInstanceNodeVO();
		BeanUtils.copyProperties(taskType, taskTypeVO);

		pipelineExecInstanceNodeParamsVO.setTaskTypeVO(taskTypeVO);

	}


}

