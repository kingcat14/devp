package net.aicoder.speedcloud.console.business.speedcloud.project.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.project.dto.ProjectSetCondition;
import net.aicoder.speedcloud.business.project.dto.ProjectSetAddDto;
import net.aicoder.speedcloud.business.project.dto.ProjectSetEditDto;
import net.aicoder.speedcloud.business.project.vo.ProjectSetVO;
import net.aicoder.speedcloud.console.business.speedcloud.project.service.ProjectSetRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.project.valid.ProjectSetValidator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理项目集
 * @author icode
 */
@Api(description = "项目集", tags = "ProjectSet")
@RestController
@RequestMapping(value = "/speedcloud/project/projectset")
public class ProjectSetController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectSetController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private ProjectSetRibbonService projectSetRibbonService;

	@Autowired
	ProjectSetValidator projectSetValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(projectSetValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增项目集
	 * @param projectSetAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增项目集", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ProjectSetVO add(@RequestBody ProjectSetAddDto projectSetAddDto){
    	projectSetAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  projectSetRibbonService.add(projectSetAddDto);
	}

	/**
	 * 删除项目集,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除项目集", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete projectSet :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			projectSetRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新项目集
	 * @param projectSetEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产项目集(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public ProjectSetVO update(@RequestBody ProjectSetEditDto projectSetEditDto, @ApiParam(value = "要查询的项目集id") @PathVariable Long id){

		ProjectSetVO vo = projectSetRibbonService.merge(id, projectSetEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询项目集
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询项目集", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public ProjectSetVO get(@ApiParam(value = "要查询的项目集id") @PathVariable Long id) {

		ProjectSetVO vo = projectSetRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询项目集列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询项目集列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ProjectSetVO> list(@RequestBody PageSearchRequest<ProjectSetCondition> pageSearchRequest){

		ProjectSetCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new ProjectSetCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<ProjectSetVO> pageContent = projectSetRibbonService.list(pageSearchRequest);
		for(ProjectSetVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出项目集列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出项目集列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ProjectSetCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<ProjectSetCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ProjectSetVO> content = this.list(pageSearchRequest);

        List<ProjectSetVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ProjectSetVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"名称");
            headMap.put("description" ,"描述");

        String title = new String("项目集");
        String fileName = new String(("项目集_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ProjectSetVO initViewProperty( ProjectSetVO vo){

	   


	   
        return vo;

	}


}
