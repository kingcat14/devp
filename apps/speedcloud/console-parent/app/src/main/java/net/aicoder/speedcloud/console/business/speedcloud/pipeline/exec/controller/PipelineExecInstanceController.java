package net.aicoder.speedcloud.console.business.speedcloud.pipeline.exec.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.exec.service.PipelineExecInstanceRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.exec.valid.PipelineExecInstanceValidator;
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
 * 管理运行计划
 * @author icode
 */
@Api(description = "运行计划", tags = "PipelineExecInstance")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecinstance")
public class PipelineExecInstanceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineExecInstanceRibbonService pipelineExecInstanceRibbonService;

	@Autowired
	PipelineExecInstanceValidator pipelineExecInstanceValidator;


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
	public PipelineExecInstanceVO add(@RequestBody PipelineExecInstanceAddDto pipelineExecInstanceAddDto){
    	pipelineExecInstanceAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  pipelineExecInstanceRibbonService.add(pipelineExecInstanceAddDto);
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
			pipelineExecInstanceRibbonService.delete(Long.parseLong(id));
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
	public PipelineExecInstanceVO update(@RequestBody PipelineExecInstanceEditDto pipelineExecInstanceEditDto, @ApiParam(value = "要查询的运行计划id") @PathVariable Long id){

		PipelineExecInstanceVO vo = pipelineExecInstanceRibbonService.merge(id, pipelineExecInstanceEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询运行计划
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行计划", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineExecInstanceVO get(@ApiParam(value = "要查询的运行计划id") @PathVariable Long id) {

		PipelineExecInstanceVO vo = pipelineExecInstanceRibbonService.find(id);
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

		PipelineExecInstanceCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineExecInstanceCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineExecInstanceVO> pageContent = pipelineExecInstanceRibbonService.list(pageSearchRequest);
		for(PipelineExecInstanceVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

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
    public void export(PipelineExecInstanceCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

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


	private PipelineExecInstanceVO initViewProperty( PipelineExecInstanceVO vo){

	   


	   
        return vo;

	}


}
