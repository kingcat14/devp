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
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageNodeVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.service.PipelineStageNodeRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.valid.PipelineStageNodeValidator;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;

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
 * 管理阶段执行节点
 * @author icode
 */
@Api(description = "阶段执行节点", tags = "PipelineStageNode")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinestagenode")
public class PipelineStageNodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineStageNodeRibbonService pipelineStageNodeRibbonService;

	@Autowired
	PipelineStageNodeValidator pipelineStageNodeValidator;

    @Autowired
    private SimpleConfigService simpleConfigService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineStageNodeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增阶段执行节点
	 * @param pipelineStageNodeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增阶段执行节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineStageNodeVO add(@RequestBody PipelineStageNodeAddDto pipelineStageNodeAddDto){
    	pipelineStageNodeAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  pipelineStageNodeRibbonService.add(pipelineStageNodeAddDto);
	}

	/**
	 * 删除阶段执行节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除阶段执行节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineStageNode :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineStageNodeRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新阶段执行节点
	 * @param pipelineStageNodeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产阶段执行节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineStageNodeVO update(@RequestBody PipelineStageNodeEditDto pipelineStageNodeEditDto, @ApiParam(value = "要查询的阶段执行节点id") @PathVariable Long id){

		PipelineStageNodeVO vo = pipelineStageNodeRibbonService.merge(id, pipelineStageNodeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询阶段执行节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询阶段执行节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineStageNodeVO get(@ApiParam(value = "要查询的阶段执行节点id") @PathVariable Long id) {

		PipelineStageNodeVO vo = pipelineStageNodeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询阶段执行节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询阶段执行节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineStageNodeVO> list(@RequestBody PageSearchRequest<PipelineStageNodeCondition> pageSearchRequest){

		PipelineStageNodeCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineStageNodeCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineStageNodeVO> pageContent = pipelineStageNodeRibbonService.list(pageSearchRequest);
		for(PipelineStageNodeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出阶段执行节点列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出阶段执行节点列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineStageNodeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineStageNodeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineStageNodeVO> content = this.list(pageSearchRequest);

        List<PipelineStageNodeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineStageNodeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"名称");
            headMap.put("stage" ,"阶段");
            headMap.put("nodeType" ,"节点类型");
            headMap.put("nodeId" ,"节点节点ID");
            headMap.put("execOrder" ,"执行排序");

        String title = new String("阶段执行节点");
        String fileName = new String(("阶段执行节点_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineStageNodeVO initViewProperty( PipelineStageNodeVO vo){

	   

		SimpleConfig nodeTypeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINESTAGENODE-NODETYPE", vo.getNodeType());

		if(nodeTypeSimpleConfig!=null) {

		    SimpleConfigVO nodeTypeSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(nodeTypeSimpleConfig, nodeTypeSimpleConfigVO);
		    vo.setNodeTypeVO(nodeTypeSimpleConfigVO);
		}

	   
        return vo;

	}


}
