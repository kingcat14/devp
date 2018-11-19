package net.aicoder.speedcloud.console.business.speedcloud.project.controller;

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
import net.aicoder.speedcloud.business.project.dto.ProjectAddDto;
import net.aicoder.speedcloud.business.project.dto.ProjectCondition;
import net.aicoder.speedcloud.business.project.dto.ProjectEditDto;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import net.aicoder.speedcloud.console.business.speedcloud.project.service.ProjectRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.project.valid.ProjectValidator;
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
 * 管理项目
 * @author icode
 */
@Api(description = "项目", tags = "Project")
@RestController
@RequestMapping(value = "/speedcloud/project/project")
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private ProjectRibbonService projectRibbonService;

	@Autowired
	ProjectValidator projectValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(projectValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增项目
	 * @param projectAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增项目", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	@SaaSAnnotation
	public ProjectVO add(@RequestBody ProjectAddDto projectAddDto){
    	return  projectRibbonService.add(projectAddDto);
	}

	/**
	 * 删除项目,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除项目", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete project :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			projectRibbonService.delete(Long.parseLong(id));
		}
	}

	/**
	 * 更新项目
	 * @param projectEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产项目(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public ProjectVO update(@RequestBody ProjectEditDto projectEditDto, @ApiParam(value = "要查询的项目id") @PathVariable Long id){

		ProjectVO vo = projectRibbonService.merge(id, projectEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询项目
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询项目", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public ProjectVO get(@ApiParam(value = "要查询的项目id") @PathVariable Long id) {

		ProjectVO vo = projectRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询项目列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询项目列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = ProjectCondition.class)
	public PageContent<ProjectVO> list(@RequestBody PageSearchRequest<ProjectCondition> pageSearchRequest){

		PageContent<ProjectVO> pageContent = projectRibbonService.list(pageSearchRequest);
		for(ProjectVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出项目列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出项目列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ProjectCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<ProjectCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ProjectVO> content = this.list(pageSearchRequest);

        List<ProjectVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ProjectVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"名称");
            headMap.put("type" ,"类型");
            headMap.put("scope" ,"公开性");
            headMap.put("description" ,"描述");
            headMap.put("parent" ,"上级项目");
            headMap.put("projectSet" ,"所属项目集");

        String title = new String("项目");
        String fileName = new String(("项目_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ProjectVO initViewProperty( ProjectVO vo){

	   


	   
        return vo;

	}


}
