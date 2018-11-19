package net.aicoder.speedcloud.console.business.speedcloud.pipeline.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineParamVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.service.PipelineParamRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.valid.PipelineParamValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理流水线参数
 * @author icode
 */
@Api(description = "流水线参数", tags = "PipelineParam")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelineparam")
public class PipelineParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineParamController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineParamRibbonService pipelineParamRibbonService;

	@Autowired
	PipelineParamValidator pipelineParamValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineParamValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增流水线参数
	 * @param pipelineParamAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增流水线参数", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	@SaaSAnnotation
	public PipelineParamVO add(@RequestBody PipelineParamAddDto pipelineParamAddDto){
		return  pipelineParamRibbonService.add(pipelineParamAddDto);
	}

	/**
	 * 删除流水线参数,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除流水线参数", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineParam :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineParamRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新流水线参数
	 * @param pipelineParamEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产流水线参数(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineParamVO update(@RequestBody PipelineParamEditDto pipelineParamEditDto, @ApiParam(value = "要查询的流水线参数id") @PathVariable Long id){

		PipelineParamVO vo = pipelineParamRibbonService.merge(id, pipelineParamEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询流水线参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询流水线参数", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineParamVO get(@ApiParam(value = "要查询的流水线参数id") @PathVariable Long id) {

		PipelineParamVO vo = pipelineParamRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询流水线参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询流水线参数列表", httpMethod = "POST")
	@PostMapping("/list")@SaaSAnnotation(conditionClass = PipelineParamCondition.class)
	public PageContent<PipelineParamVO> list(@RequestBody PageSearchRequest<PipelineParamCondition> pageSearchRequest){

		PageContent<PipelineParamVO> pageContent = pipelineParamRibbonService.list(pageSearchRequest);
		for(PipelineParamVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出流水线参数列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出流水线参数列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineParamCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PipelineParamCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineParamVO> content = this.list(pageSearchRequest);

        List<PipelineParamVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineParamVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("pipeline" ,"所属流水线");
            headMap.put("name" ,"参数名称");
            headMap.put("type" ,"参数类型");
            headMap.put("defaultValue" ,"默认值");
            headMap.put("viewOrder" ,"展现顺序");
            headMap.put("description" ,"参数描述");
            headMap.put("deletable" ,"可删除");
            headMap.put("enumValue" ,"可选值");
            headMap.put("value" ,"参数值");

        String title = new String("流水线参数");
        String fileName = new String(("流水线参数_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PipelineParamVO initViewProperty( PipelineParamVO vo){

	   


	   
        return vo;

	}


}
