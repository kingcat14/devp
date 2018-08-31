package net.aicoder.speedcloud.business.pipeline.controller;

import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageEditDto;
import net.aicoder.speedcloud.business.pipeline.service.PipelineStageService;
import net.aicoder.speedcloud.business.pipeline.valid.PipelineStageValidator;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageVO;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineVO;


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
 * 管理阶段
 * @author icode
 */
@Api(description = "阶段", tags = "PipelineStage")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinestage")
public class PipelineStageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageController.class);


	@Autowired
	private PipelineStageService pipelineStageService;

	@Autowired
	private PipelineService pipelineService;

    @Autowired
    private SimpleConfigService simpleConfigService;

	@Autowired
	private PipelineStageValidator pipelineStageValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineStageValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增阶段
	 * @param pipelineStageAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增阶段", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineStageVO add(@RequestBody @Valid PipelineStageAddDto pipelineStageAddDto){
		PipelineStage pipelineStage = new PipelineStage();
		BeanUtils.copyProperties(pipelineStageAddDto, pipelineStage);

		pipelineStageService.add(pipelineStage);

		return  initViewProperty(pipelineStage);
	}

	/**
	 * 删除阶段,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除阶段", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineStage :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineStageService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新阶段
	 * @param pipelineStageEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产阶段(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	PipelineStageVO update(@RequestBody @Valid PipelineStageEditDto pipelineStageEditDto, @PathVariable Long id){
		PipelineStage pipelineStage = new PipelineStage();
		BeanUtils.copyProperties(pipelineStageEditDto, pipelineStage);
		pipelineStage.setId(id);
		pipelineStageService.merge(pipelineStage);

		PipelineStageVO vo = initViewProperty(pipelineStage);
		return  vo;
	}

	/**
	 * 根据ID查询阶段
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询阶段", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineStageVO get(@PathVariable Long id) {

		PipelineStage pipelineStage = pipelineStageService.find(id);

		PipelineStageVO vo = initViewProperty(pipelineStage);
		return vo;
	}

	/**
	 * 查询阶段列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询阶段列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineStageVO> list(@RequestBody PageSearchRequest<PipelineStageCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<PipelineStage> page = pipelineStageService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineStageVO> voList = new ArrayList<>();
		for(PipelineStage pipelineStage : page.getContent()){
			voList.add(initViewProperty(pipelineStage));
		}

		PageContent<PipelineStageVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出阶段列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出阶段列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineStageCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineStageCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineStageVO> content = this.list(pageSearchRequest);

        List<PipelineStageVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineStageVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("pipeline" ,"所属流水线");
            headMap.put("name" ,"阶段名称");
            headMap.put("flowType" ,"流转方式");

        String title = new String("阶段");
        String fileName = new String(("阶段_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineStageVO initViewProperty(PipelineStage pipelineStage){

	    PipelineStageVO vo = new PipelineStageVO();
        BeanUtils.copyProperties(pipelineStage, vo);

		SimpleConfig flowTypeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINESTAGE-FLOWTYPE", pipelineStage.getFlowType());

		if(flowTypeSimpleConfig!=null) {
		    SimpleConfigVO flowTypeSimpleConfigVO = new SimpleConfigVO();
            BeanUtils.copyProperties(flowTypeSimpleConfig, flowTypeSimpleConfigVO);
			vo.setFlowTypeVO(flowTypeSimpleConfigVO);
		}

	    //初始化其他对象
	    initPipelinePropertyGroup(vo, pipelineStage);
        return vo;

	}


	private void initPipelinePropertyGroup(PipelineStageVO pipelineStageVO, PipelineStage pipelineStage){
	
		Pipeline pipeline = pipelineService.find(pipelineStage.getPipeline());
		if(pipeline == null){
			return;
		}
		PipelineVO pipelineVO = new PipelineVO();
		BeanUtils.copyProperties(pipeline, pipelineVO);

		pipelineStageVO.setPipelineVO(pipelineVO);

	}


}

