package net.aicoder.speedcloud.business.pipeline.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageTaskRelation;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationEditDto;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageTaskRelationService;
import net.aicoder.speedcloud.business.pipeline.valid.PipelineStageTaskRelationValidator;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageTaskRelationVO;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageService;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageVO;



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
@Api(description = "阶段执行节点", tags = "PipelineStageTaskRelation")
@RestController
@RequestMapping(value = "/pipeline/pipelineStageTaskRelation")
public class PipelineStageTaskRelationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageTaskRelationController.class);

	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineStageTaskRelationService pipelineStageTaskRelationService;

	@Autowired
	private PipelineStageService pipelineStageService;
    @Autowired
    private SimpleConfigService simpleConfigService;

	@Autowired
	private PipelineStageTaskRelationValidator pipelineStageTaskRelationValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineStageTaskRelationValidator);
	}

	/**
	 * 新增阶段执行节点
	 * @param pipelineStageTaskRelationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增阶段执行节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineStageTaskRelationVO add(@RequestBody @Valid PipelineStageTaskRelationAddDto pipelineStageTaskRelationAddDto){
		PipelineStageTaskRelation pipelineStageTaskRelation = new PipelineStageTaskRelation();
		BeanUtils.copyProperties(pipelineStageTaskRelationAddDto, pipelineStageTaskRelation);
		pipelineStageTaskRelation.setTid(saaSUtil.getAccount().getTenantId());
		pipelineStageTaskRelationService.add(pipelineStageTaskRelation);

		return  initViewProperty(pipelineStageTaskRelation);
	}

	/**
	 * 删除阶段执行节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除阶段执行节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineStageTaskRelation :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineStageTaskRelationService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新阶段执行节点
	 * @param pipelineStageTaskRelationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产阶段执行节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	PipelineStageTaskRelationVO update(@RequestBody @Valid PipelineStageTaskRelationEditDto pipelineStageTaskRelationEditDto, @PathVariable Long id){
		PipelineStageTaskRelation pipelineStageTaskRelation = new PipelineStageTaskRelation();
		BeanUtils.copyProperties(pipelineStageTaskRelationEditDto, pipelineStageTaskRelation);
		pipelineStageTaskRelation.setId(id);
		pipelineStageTaskRelationService.merge(pipelineStageTaskRelation);

		PipelineStageTaskRelationVO vo = initViewProperty(pipelineStageTaskRelation);
		return  vo;
	}

	/**
	 * 根据ID查询阶段执行节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询阶段执行节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineStageTaskRelationVO get(@PathVariable Long id) {

		PipelineStageTaskRelation pipelineStageTaskRelation = pipelineStageTaskRelationService.find(id);

		PipelineStageTaskRelationVO vo = initViewProperty(pipelineStageTaskRelation);
		return vo;
	}

	/**
	 * 查询阶段执行节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询阶段执行节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineStageTaskRelationVO> list(@RequestBody PageSearchRequest<PipelineStageTaskRelationCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<PipelineStageTaskRelation> page = pipelineStageTaskRelationService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineStageTaskRelationVO> voList = new ArrayList<>();
		for(PipelineStageTaskRelation pipelineStageTaskRelation : page.getContent()){
			voList.add(initViewProperty(pipelineStageTaskRelation));
		}

		PageContent<PipelineStageTaskRelationVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(PipelineStageTaskRelationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

            headMap.put("name" ,"名称");
            headMap.put("stage" ,"阶段");
            headMap.put("nodeType" ,"节点类型");
            headMap.put("nodeId" ,"节点节点ID");
            headMap.put("execOrder" ,"执行排序");

        String title = new String("阶段执行节点");
        String fileName = new String(("阶段执行节点_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineStageTaskRelationVO initViewProperty(PipelineStageTaskRelation pipelineStageTaskRelation){

	    PipelineStageTaskRelationVO vo = new PipelineStageTaskRelationVO();
        BeanUtils.copyProperties(pipelineStageTaskRelation, vo);

		SimpleConfig nodeTypeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINESTAGETASKRELATION-NODETYPE", 阶段执行节点.getNodeType());

		if(节点类型SimpleConfig!=null) {
		    SimpleConfigVO nodeTypeSimpleConfigVO = new SimpleConfigVO()
            BeanUtils.copyProperties(nodeTypeSimpleConfig, nodeTypeSimpleConfigVO);
			vo.setNodeTypeVO(nodeTypeSimpleConfigVO);
		}

	    //初始化其他对象
	    initStagePropertyGroup(vo, pipelineStageTaskRelation);
        return vo;


	}


	private void initStagePropertyGroup(PipelineStageTaskRelationVO pipelineStageTaskRelationVO, PipelineStageTaskRelation pipelineStageTaskRelation){
	
		PipelineStage stage = pipelineStageService.find(pipelineStageTaskRelation.getStage());
		if(stage == null){
			return;
		}
		PipelineStageVO stageVO = new PipelineStageVO();
		BeanUtils.copyProperties(stage, stageVO);

		pipelineStageTaskRelationVO.setStageVO(stageVO);

	}


}
