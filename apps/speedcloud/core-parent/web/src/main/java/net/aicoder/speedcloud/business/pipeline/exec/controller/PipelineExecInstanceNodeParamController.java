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
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParam;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceNodeParamService;
import net.aicoder.speedcloud.business.pipeline.exec.valid.PipelineExecInstanceNodeParamValidator;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceNodeParamVO;
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
@Api(description = "运行实例节点参数", tags = "PipelineExecInstanceNodeParam")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecinstancenodeparam")
public class PipelineExecInstanceNodeParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeParamController.class);


	@Autowired
	private PipelineExecInstanceNodeParamService pipelineExecInstanceNodeParamService;

	@Autowired
	private PipelineExecInstanceNodeService pipelineExecInstanceNodeService;


	@Autowired
	private PipelineExecInstanceNodeParamValidator pipelineExecInstanceNodeParamValidator;

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
	public PipelineExecInstanceNodeParamVO add(@RequestBody @Valid PipelineExecInstanceNodeParamAddDto pipelineExecInstanceNodeParamAddDto){
		PipelineExecInstanceNodeParam pipelineExecInstanceNodeParam = new PipelineExecInstanceNodeParam();
		BeanUtils.copyProperties(pipelineExecInstanceNodeParamAddDto, pipelineExecInstanceNodeParam);

		pipelineExecInstanceNodeParamService.add(pipelineExecInstanceNodeParam);

		return  initViewProperty(pipelineExecInstanceNodeParam);
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
			pipelineExecInstanceNodeParamService.delete(Long.parseLong(id));
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
	public	PipelineExecInstanceNodeParamVO update(@RequestBody @Valid PipelineExecInstanceNodeParamEditDto pipelineExecInstanceNodeParamEditDto, @PathVariable Long id){
		PipelineExecInstanceNodeParam pipelineExecInstanceNodeParam = new PipelineExecInstanceNodeParam();
		BeanUtils.copyProperties(pipelineExecInstanceNodeParamEditDto, pipelineExecInstanceNodeParam);
		pipelineExecInstanceNodeParam.setId(id);
		pipelineExecInstanceNodeParamService.merge(pipelineExecInstanceNodeParam);

		PipelineExecInstanceNodeParamVO vo = initViewProperty(pipelineExecInstanceNodeParam);
		return  vo;
	}

	/**
	 * 根据ID查询运行实例节点参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行实例节点参数", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineExecInstanceNodeParamVO get(@PathVariable Long id) {

		PipelineExecInstanceNodeParam pipelineExecInstanceNodeParam = pipelineExecInstanceNodeParamService.find(id);

		PipelineExecInstanceNodeParamVO vo = initViewProperty(pipelineExecInstanceNodeParam);
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

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<PipelineExecInstanceNodeParam> page = pipelineExecInstanceNodeParamService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineExecInstanceNodeParamVO> voList = new ArrayList<>();
		for(PipelineExecInstanceNodeParam pipelineExecInstanceNodeParam : page.getContent()){
			voList.add(initViewProperty(pipelineExecInstanceNodeParam));
		}

		PageContent<PipelineExecInstanceNodeParamVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(PipelineExecInstanceNodeParamCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

	private PipelineExecInstanceNodeParamVO initViewProperty(PipelineExecInstanceNodeParam pipelineExecInstanceNodeParam){

	    PipelineExecInstanceNodeParamVO vo = new PipelineExecInstanceNodeParamVO();
        BeanUtils.copyProperties(pipelineExecInstanceNodeParam, vo);


	    //初始化其他对象
	    initNodePropertyGroup(vo, pipelineExecInstanceNodeParam);
        return vo;

	}


	private void initNodePropertyGroup(PipelineExecInstanceNodeParamVO pipelineExecInstanceNodeParamVO, PipelineExecInstanceNodeParam pipelineExecInstanceNodeParam){
	
		PipelineExecInstanceNode node = pipelineExecInstanceNodeService.find(pipelineExecInstanceNodeParam.getNode());
		if(node == null){
			return;
		}
		PipelineExecInstanceNodeVO nodeVO = new PipelineExecInstanceNodeVO();
		BeanUtils.copyProperties(node, nodeVO);

		pipelineExecInstanceNodeParamVO.setNodeVO(nodeVO);

	}


}

