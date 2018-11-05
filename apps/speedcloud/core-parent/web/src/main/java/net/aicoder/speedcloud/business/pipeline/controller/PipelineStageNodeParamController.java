package net.aicoder.speedcloud.business.pipeline.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNodeParam;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamEditDto;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageNodeParamService;
import net.aicoder.speedcloud.business.pipeline.valid.PipelineStageNodeParamValidator;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageNodeParamVO;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageNodeService;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageNodeVO;


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
 * 管理阶段执行节点参数
 * @author icode
 */
@Api(description = "阶段执行节点参数", tags = "PipelineStageNodeParam")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinestagenodeparam")
public class PipelineStageNodeParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeParamController.class);


	@Autowired
	private PipelineStageNodeParamService pipelineStageNodeParamService;

	@Autowired
	private PipelineStageNodeService pipelineStageNodeService;


	@Autowired
	private PipelineStageNodeParamValidator pipelineStageNodeParamValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineStageNodeParamValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增阶段执行节点参数
	 * @param pipelineStageNodeParamAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增阶段执行节点参数", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineStageNodeParamVO add(@RequestBody @Valid PipelineStageNodeParamAddDto pipelineStageNodeParamAddDto){
		PipelineStageNodeParam pipelineStageNodeParam = new PipelineStageNodeParam();
		BeanUtils.copyProperties(pipelineStageNodeParamAddDto, pipelineStageNodeParam);

		pipelineStageNodeParamService.add(pipelineStageNodeParam);

		return  initViewProperty(pipelineStageNodeParam);
	}

	/**
	 * 删除阶段执行节点参数,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除阶段执行节点参数", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineStageNodeParam :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineStageNodeParamService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新阶段执行节点参数
	 * @param pipelineStageNodeParamEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产阶段执行节点参数(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	PipelineStageNodeParamVO update(@RequestBody @Valid PipelineStageNodeParamEditDto pipelineStageNodeParamEditDto, @PathVariable Long id){
		PipelineStageNodeParam pipelineStageNodeParam = new PipelineStageNodeParam();
		BeanUtils.copyProperties(pipelineStageNodeParamEditDto, pipelineStageNodeParam);
		pipelineStageNodeParam.setId(id);
		pipelineStageNodeParamService.merge(pipelineStageNodeParam);

		PipelineStageNodeParamVO vo = initViewProperty(pipelineStageNodeParam);
		return  vo;
	}

	/**
	 * 根据ID查询阶段执行节点参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询阶段执行节点参数", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineStageNodeParamVO get(@PathVariable Long id) {

		PipelineStageNodeParam pipelineStageNodeParam = pipelineStageNodeParamService.find(id);

		PipelineStageNodeParamVO vo = initViewProperty(pipelineStageNodeParam);
		return vo;
	}

	/**
	 * 查询阶段执行节点参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询阶段执行节点参数列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineStageNodeParamVO> list(@RequestBody PageSearchRequest<PipelineStageNodeParamCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<PipelineStageNodeParam> page = pipelineStageNodeParamService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineStageNodeParamVO> voList = new ArrayList<>();
		for(PipelineStageNodeParam pipelineStageNodeParam : page.getContent()){
			voList.add(initViewProperty(pipelineStageNodeParam));
		}

		PageContent<PipelineStageNodeParamVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出阶段执行节点参数列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出阶段执行节点参数列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineStageNodeParamCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineStageNodeParamCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineStageNodeParamVO> content = this.list(pageSearchRequest);

        List<PipelineStageNodeParamVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineStageNodeParamVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"参数名称");
            headMap.put("value" ,"参数值");
            headMap.put("viewOrder" ,"展现顺序");
            headMap.put("pipelineStageNode" ,"阶段执行节点");

        String title = new String("阶段执行节点参数");
        String fileName = new String(("阶段执行节点参数_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineStageNodeParamVO initViewProperty(PipelineStageNodeParam pipelineStageNodeParam){

	    PipelineStageNodeParamVO vo = new PipelineStageNodeParamVO();
        BeanUtils.copyProperties(pipelineStageNodeParam, vo);


	    //初始化其他对象
	    initPipelineStageNodePropertyGroup(vo, pipelineStageNodeParam);
        return vo;

	}


	private void initPipelineStageNodePropertyGroup(PipelineStageNodeParamVO pipelineStageNodeParamVO, PipelineStageNodeParam pipelineStageNodeParam){

		PipelineStageNode pipelineStageNode = pipelineStageNodeService.find(pipelineStageNodeParam.getPipelineStageNode());
		if(pipelineStageNode == null){
			return;
		}
		PipelineStageNodeVO pipelineStageNodeVO = new PipelineStageNodeVO();
		BeanUtils.copyProperties(pipelineStageNode, pipelineStageNodeVO);

		pipelineStageNodeParamVO.setPipelineStageNodeVO(pipelineStageNodeVO);

	}


}

