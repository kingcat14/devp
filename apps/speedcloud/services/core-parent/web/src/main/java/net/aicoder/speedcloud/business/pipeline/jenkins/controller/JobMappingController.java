package net.aicoder.speedcloud.business.pipeline.jenkins.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.jenkins.domain.JobMapping;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingAddDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingCondition;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingEditDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.service.JobMappingService;
import net.aicoder.speedcloud.business.pipeline.jenkins.valid.JobMappingValidator;
import net.aicoder.speedcloud.business.pipeline.jenkins.vo.JobMappingVO;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import net.aicoder.speedcloud.business.project.domain.Project;
import net.aicoder.speedcloud.business.project.service.ProjectService;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	private JobMappingService jobMappingService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private PipelineTaskService pipelineTaskService;

	@Autowired
	private PipelineService pipelineService;


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
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.jenkins.jobmapping.add", count = true)
	public JobMappingVO add(@RequestBody @Valid JobMappingAddDto jobMappingAddDto){
		JobMapping jobMapping = new JobMapping();
		BeanUtils.copyProperties(jobMappingAddDto, jobMapping);

		jobMappingService.add(jobMapping);

		return  initViewProperty(jobMapping);
	}

	/**
	 * 删除任务映射,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务映射", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.jenkins.jobmapping.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete jobMapping :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			jobMappingService.delete(id);
		}

	}

	/**
	 * 更新任务映射
	 * @param jobMappingEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改任务映射(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.jenkins.jobmapping.update", count = true)
	public	JobMappingVO update(@RequestBody @Valid JobMappingEditDto jobMappingEditDto, @PathVariable String id){
		JobMapping jobMapping = jobMappingService.find(id);
		BeanUtils.copyProperties(jobMappingEditDto, jobMapping);
		jobMapping.setId(id);
		jobMappingService.merge(jobMapping);

		JobMappingVO vo = initViewProperty(jobMapping);
		return  vo;
	}

	/**
	 * 根据ID查询任务映射
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询任务映射", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.jenkins.jobmapping.get")
	public  JobMappingVO get(@PathVariable String id) {

		JobMapping jobMapping = jobMappingService.find(id);
		if(jobMapping == null){
			throw new ResourceNotFoundException("找不到指定的任务映射，请检查ID");
		}
		JobMappingVO vo = initViewProperty(jobMapping);
		return vo;
	}

	/**
	 * 查询任务映射列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务映射列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "speedcloud.pipeline.jenkins.jobmapping.list")
	public PageContent<JobMappingVO> list(@RequestBody PageSearchRequest<JobMappingCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<JobMapping> page = jobMappingService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<JobMappingVO> voList = new ArrayList<>();
		for(JobMapping jobMapping : page.getContent()){
			voList.add(initViewProperty(jobMapping));
		}

		PageContent<JobMappingVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(JobMappingCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("project" ,"所属产品");
        headMap.put("taskType" ,"类型");
        headMap.put("jobInPlatform" ,"任务或流水线");
        headMap.put("jobInPlatformName" ,"任务或流水线名称");
        headMap.put("jobInJenkinsName" ,"Jenkins中任务名称");

        String title = new String("任务映射");
        String fileName = new String(("任务映射_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private JobMappingVO initViewProperty(JobMapping jobMapping){

	    JobMappingVO vo = new JobMappingVO();
        BeanUtils.copyProperties(jobMapping, vo);

		String projectId = null;
        if(StringUtils.equals("PIPELINE",jobMapping.getTaskType())){
			Pipeline pipeline = pipelineService.find(Long.parseLong(jobMapping.getJobInPlatform()));
			if(pipeline!=null){
				vo.setJobInPlatformName(pipeline.getName());
				projectId = pipeline.getProject();
			}
		}else{
			PipelineTask task = pipelineTaskService.find(Long.parseLong(jobMapping.getJobInPlatform()));
			if(task!=null){
				vo.setJobInPlatformName(task.getName());
				projectId = task.getProject();
			}
		}


	    //初始化其他对象
	    initProjectPropertyGroup(vo, projectId);
        return vo;

	}

	private void initProjectPropertyGroup(JobMappingVO jobMappingVO, String projectId){

    	if(StringUtils.isEmpty(projectId)){
    		return;
		}

		Project project = projectService.find(projectId);
		if(project == null){
			return;
		}
		ProjectVO projectVO = new ProjectVO();
		BeanUtils.copyProperties(project, projectVO);

		jobMappingVO.setProjectVO(projectVO);

	}

}

