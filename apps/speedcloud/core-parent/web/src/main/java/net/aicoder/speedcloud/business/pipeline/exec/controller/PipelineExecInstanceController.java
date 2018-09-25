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
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.service.ExecAction;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceService;
import net.aicoder.speedcloud.business.pipeline.exec.valid.PipelineExecInstanceValidator;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceVO;


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
 * 管理运行计划
 * @author icode
 */
@Api(description = "运行计划", tags = "PipelineExecInstance")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecinstance")
public class PipelineExecInstanceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceController.class);

	@Autowired
	private	ExecAction execAction;


	@Autowired
	private PipelineExecInstanceService pipelineExecInstanceService;



	@Autowired
	private PipelineExecInstanceValidator pipelineExecInstanceValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineExecInstanceValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运行计划
	 * @param pipelineExecInstanceAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行计划", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineExecInstanceVO add(@RequestBody @Valid PipelineExecInstanceAddDto pipelineExecInstanceAddDto){
		PipelineExecInstance pipelineExecInstance = new PipelineExecInstance();
		BeanUtils.copyProperties(pipelineExecInstanceAddDto, pipelineExecInstance);

		//pipelineExecInstanceService.add(pipelineExecInstance);
		execAction.createExec(pipelineExecInstance);

		return  initViewProperty(pipelineExecInstance);
	}

	/**
	 * 删除运行计划,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运行计划", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineExecInstance :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineExecInstanceService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新运行计划
	 * @param pipelineExecInstanceEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运行计划(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	PipelineExecInstanceVO update(@RequestBody @Valid PipelineExecInstanceEditDto pipelineExecInstanceEditDto, @PathVariable Long id){
		PipelineExecInstance pipelineExecInstance = new PipelineExecInstance();
		BeanUtils.copyProperties(pipelineExecInstanceEditDto, pipelineExecInstance);
		pipelineExecInstance.setId(id);
		pipelineExecInstanceService.merge(pipelineExecInstance);

		PipelineExecInstanceVO vo = initViewProperty(pipelineExecInstance);
		return  vo;
	}

	/**
	 * 根据ID查询运行计划
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行计划", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineExecInstanceVO get(@PathVariable Long id) {

		PipelineExecInstance pipelineExecInstance = pipelineExecInstanceService.find(id);

		PipelineExecInstanceVO vo = initViewProperty(pipelineExecInstance);
		return vo;
	}

	/**
	 * 查询运行计划列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运行计划列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineExecInstanceVO> list(@RequestBody PageSearchRequest<PipelineExecInstanceCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<PipelineExecInstance> page = pipelineExecInstanceService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineExecInstanceVO> voList = new ArrayList<>();
		for(PipelineExecInstance pipelineExecInstance : page.getContent()){
			voList.add(initViewProperty(pipelineExecInstance));
		}

		PageContent<PipelineExecInstanceVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出运行计划列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出运行计划列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineExecInstanceCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineExecInstanceCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineExecInstanceVO> content = this.list(pageSearchRequest);

        List<PipelineExecInstanceVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineExecInstanceVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("code" ,"编号");
            headMap.put("executeTargetId" ,"运行主体");
            headMap.put("executeTargetType" ,"运行类型");
            headMap.put("status" ,"运行状态");
            headMap.put("result" ,"运行结果");
            headMap.put("startTime" ,"开始时间");

        String title = new String("运行计划");
        String fileName = new String(("运行计划_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	public PipelineExecInstanceVO initViewProperty(PipelineExecInstance pipelineExecInstance){

	    PipelineExecInstanceVO vo = new PipelineExecInstanceVO();
        BeanUtils.copyProperties(pipelineExecInstance, vo);


	    //初始化其他对象
        return vo;

	}


}

