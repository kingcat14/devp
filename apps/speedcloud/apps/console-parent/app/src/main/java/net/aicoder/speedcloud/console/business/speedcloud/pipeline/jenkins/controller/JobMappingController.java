package net.aicoder.speedcloud.console.business.speedcloud.pipeline.jenkins.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingAddDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingCondition;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingEditDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.vo.JobMappingVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.jenkins.service.JobMappingRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.jenkins.valid.JobMappingValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理任务映射
 * @author icode
 */
@Api(description = "任务映射", tags = "JobMapping")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/jenkins/jobmapping")
public class JobMappingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobMappingController.class);
   
    @Autowired
	private JobMappingRibbonService jobMappingRibbonService;

	@Autowired
	private JobMappingValidator jobMappingValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(jobMappingValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增任务映射
	 * @param jobMappingAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增任务映射", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public JobMappingVO add(@RequestBody @Valid JobMappingAddDto jobMappingAddDto){
	
		return  jobMappingRibbonService.add(jobMappingAddDto);
	}

	/**
	 * 删除任务映射,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务映射", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete jobMapping :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			jobMappingRibbonService.delete(id);
		}

	}

	/**
	 * 更新任务映射
	 * @param jobMappingEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产任务映射(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public JobMappingVO update(@RequestBody @Valid JobMappingEditDto jobMappingEditDto, @ApiParam(value = "要查询的任务映射id") @PathVariable String id){

		JobMappingVO vo = jobMappingRibbonService.merge(id, jobMappingEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询任务映射
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询任务映射", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public JobMappingVO get(@ApiParam(value = "要查询的任务映射id") @PathVariable String id) {

		JobMappingVO vo = jobMappingRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询任务映射列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务映射列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = JobMappingCondition.class)
	public PageContent<JobMappingVO> list(@RequestBody @Valid PageSearchRequest<JobMappingCondition> pageSearchRequest){

		PageContent<JobMappingVO> pageContent = jobMappingRibbonService.list(pageSearchRequest);
		for(JobMappingVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出任务映射列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出任务映射列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(JobMappingCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<JobMappingCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<JobMappingVO> content = this.list(pageSearchRequest);

        List<JobMappingVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(JobMappingVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("project" ,"所属产品");
            headMap.put("taskType" ,"类型");
            headMap.put("jobInPlatform" ,"任务或流水线");
            headMap.put("jobInPlatformName" ,"任务或流水线名称");
            headMap.put("jobInJenkinsName" ,"Jenkins中任务名称");

        String title = new String("任务映射");
        String fileName = new String(("任务映射_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private JobMappingVO initViewProperty( JobMappingVO vo){


	   
        return vo;

	}
}
