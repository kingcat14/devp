package net.aicoder.speedcloud.console.business.speedCloud.pipeline.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.dto.*;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.service.PipelineRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.valid.PipelineValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理流水线
 * @author icode
 */
@Api(description = "流水线", tags = "Pipeline")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipeline")
public class PipelineAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineAction.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineRibbonService pipelineRibbonService;

	@Autowired
	PipelineValidator pipelineValidator;

    @Autowired
    private SimpleConfigService simpleConfigService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增流水线
	 * @param pipelineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增流水线", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineVO add(@RequestBody PipelineAddDto pipelineAddDto){

    	pipelineAddDto.setTid(saaSUtil.getAccount().getTenantId());

    	//处理参数的tid
    	if(CollectionUtils.isNotEmpty(pipelineAddDto.getParamList())){
    		for(PipelineParamAddDto paramAddDto : pipelineAddDto.getParamList()){
				paramAddDto.setTid(pipelineAddDto.getTid());
			}
		}
		//处理阶段的tid
		if(CollectionUtils.isNotEmpty(pipelineAddDto.getStageList())){
			for(PipelineStageAddDto stageAddDto : pipelineAddDto.getStageList()){
				stageAddDto.setTid(pipelineAddDto.getTid());

				//处理阶段节点的tid
				if(CollectionUtils.isNotEmpty(stageAddDto.getNodeList())){
					for(PipelineStageNodeAddDto stageNodeAddDto : stageAddDto.getNodeList()){
						stageNodeAddDto.setTid(pipelineAddDto.getTid());
					}
				}
			}
		}

		return  pipelineRibbonService.add(pipelineAddDto);
	}

	/**
	 * 删除流水线,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除流水线", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipeline :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新流水线
	 * @param pipelineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产流水线(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineVO update(@RequestBody PipelineEditDto pipelineEditDto, @ApiParam(value = "要查询的流水线id") @PathVariable Long id){

		PipelineVO vo = pipelineRibbonService.merge(id, pipelineEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询流水线
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询流水线", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineVO get(@ApiParam(value = "要查询的流水线id") @PathVariable Long id) {

		PipelineVO vo = pipelineRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询流水线列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询流水线列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineVO> list(@RequestBody PageSearchRequest<PipelineCondition> pageSearchRequest){

		PipelineCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineVO> pageContent = pipelineRibbonService.list(pageSearchRequest);
		for(PipelineVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出流水线列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出流水线列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineVO> content = this.list(pageSearchRequest);

        List<PipelineVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"流水线名称");
            headMap.put("type" ,"类型");
            headMap.put("project" ,"所属产品");

        String title = new String("流水线");
        String fileName = new String(("流水线_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineVO initViewProperty( PipelineVO vo){

	   

		SimpleConfig typeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINE-TYPE", vo.getType());

		if(typeSimpleConfig!=null) {

		    SimpleConfigVO typeSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(typeSimpleConfig, typeSimpleConfigVO);
		    vo.setTypeVO(typeSimpleConfigVO);
		}

	   
        return vo;

	}


}