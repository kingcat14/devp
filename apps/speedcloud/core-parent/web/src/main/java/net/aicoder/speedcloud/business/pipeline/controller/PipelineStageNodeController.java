package net.aicoder.speedcloud.business.pipeline.controller;

import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
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
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNodeParam;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageNodeParamService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageNodeService;
import net.aicoder.speedcloud.business.pipeline.valid.PipelineStageNodeValidator;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageNodeParamVO;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageNodeVO;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageService;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageVO;


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
 * 管理阶段执行节点
 * @author icode
 */
@Api(description = "阶段执行节点", tags = "PipelineStageNode")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinestagenode")
public class PipelineStageNodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeController.class);


	@Autowired
	private PipelineStageNodeService pipelineStageNodeService;

	@Autowired
	private PipelineStageNodeParamService pipelineStageNodeParamService;

	@Autowired
	private PipelineStageService pipelineStageService;

    @Autowired
    private SimpleConfigService simpleConfigService;

	@Autowired
	private PipelineStageNodeValidator pipelineStageNodeValidator;

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
	public PipelineStageNodeVO add(@RequestBody @Valid PipelineStageNodeAddDto pipelineStageNodeAddDto){
		PipelineStageNode pipelineStageNode = new PipelineStageNode();
		BeanUtils.copyProperties(pipelineStageNodeAddDto, pipelineStageNode);

		pipelineStageNodeService.add(pipelineStageNode);

		return  initViewProperty(pipelineStageNode);
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
			pipelineStageNodeService.delete(Long.parseLong(id));
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
	public	PipelineStageNodeVO update(@RequestBody @Valid PipelineStageNodeEditDto pipelineStageNodeEditDto, @PathVariable Long id){
		PipelineStageNode pipelineStageNode = new PipelineStageNode();
		BeanUtils.copyProperties(pipelineStageNodeEditDto, pipelineStageNode);
		pipelineStageNode.setId(id);
		pipelineStageNodeService.merge(pipelineStageNode);

		PipelineStageNodeVO vo = initViewProperty(pipelineStageNode);
		return  vo;
	}

	/**
	 * 根据ID查询阶段执行节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询阶段执行节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineStageNodeVO get(@PathVariable Long id) {

		PipelineStageNode pipelineStageNode = pipelineStageNodeService.find(id);

		PipelineStageNodeVO vo = initViewProperty(pipelineStageNode);
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

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<PipelineStageNode> page = pipelineStageNodeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineStageNodeVO> voList = new ArrayList<>();
		for(PipelineStageNode pipelineStageNode : page.getContent()){
			voList.add(initViewProperty(pipelineStageNode));
		}

		PageContent<PipelineStageNodeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(PipelineStageNodeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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
            headMap.put("objType" ,"节点类型");
            headMap.put("nodeId" ,"节点节点ID");
            headMap.put("execOrder" ,"执行排序");

        String title = new String("阶段执行节点");
        String fileName = new String(("阶段执行节点_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineStageNodeVO initViewProperty(PipelineStageNode pipelineStageNode){

	    PipelineStageNodeVO vo = new PipelineStageNodeVO();
        BeanUtils.copyProperties(pipelineStageNode, vo);

		SimpleConfig nodeTypeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINESTAGENODE-OBJTYPE", pipelineStageNode.getObjType());

		if(nodeTypeSimpleConfig!=null) {
		    SimpleConfigVO nodeTypeSimpleConfigVO = new SimpleConfigVO();
            BeanUtils.copyProperties(nodeTypeSimpleConfig, nodeTypeSimpleConfigVO);
			vo.setObjTypeVO(nodeTypeSimpleConfigVO);
		}

	    //初始化其他对象
	    initStagePropertyGroup(vo, pipelineStageNode);
		initParamPropertyGroup(vo, pipelineStageNode);
        return vo;

	}


	private void initStagePropertyGroup(PipelineStageNodeVO pipelineStageNodeVO, PipelineStageNode pipelineStageNode){
	
		PipelineStage stage = pipelineStageService.find(pipelineStageNode.getStage());
		if(stage == null){
			return;
		}
		PipelineStageVO stageVO = new PipelineStageVO();
		BeanUtils.copyProperties(stage, stageVO);

		pipelineStageNodeVO.setStageVO(stageVO);




	}

	private void initParamPropertyGroup(PipelineStageNodeVO pipelineStageNodeVO, PipelineStageNode pipelineStageNode) {

		List<PipelineStageNodeParam> pipelineStageNodeParamList = pipelineStageNodeParamService.findByNodeId(pipelineStageNode.getId());

		if(CollectionUtils.isEmpty(pipelineStageNodeParamList)){
			return ;
		}

		List<PipelineStageNodeParamVO> paramVOList = new ArrayList<>();
		PipelineStageNodeParamVO paramVO;
		for(PipelineStageNodeParam param : pipelineStageNodeParamList){
			paramVO =  new PipelineStageNodeParamVO();
			BeanUtils.copyProperties(param, paramVO);
			paramVOList.add(paramVO);
		}
		pipelineStageNodeVO.setParamList(paramVOList);
    }

}

