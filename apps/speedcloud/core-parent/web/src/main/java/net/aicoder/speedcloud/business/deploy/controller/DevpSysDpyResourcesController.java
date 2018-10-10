package net.aicoder.speedcloud.business.deploy.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResources;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesEditDto;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpyResourcesService;
import net.aicoder.speedcloud.business.deploy.valid.DevpSysDpyResourcesValidator;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesVO;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourcesCategory;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpyResourcesCategoryService;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesCategoryVO;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourcesType;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpyResourcesTypeService;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesTypeVO;
import net.aicoder.speedcloud.business.env.domain.AppEnvConfig;
import net.aicoder.speedcloud.business.env.service.AppEnvConfigService;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.business.project.domain.Project;
import net.aicoder.speedcloud.business.project.service.ProjectService;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;


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
 * 管理方案资源
 * @author icode
 */
@Api(description = "方案资源", tags = "DevpSysDpyResources")
@RestController
@RequestMapping(value = "/speedcloud/deploy/devpsysdpyresources")
public class DevpSysDpyResourcesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesController.class);


	@Autowired
	private DevpSysDpyResourcesService devpSysDpyResourcesService;

	@Autowired
	private DevpSysDpyResourcesCategoryService devpSysDpyResourcesCategoryService;
	@Autowired
	private DevpSysDpyResourcesTypeService devpSysDpyResourcesTypeService;
	@Autowired
	private AppEnvConfigService appEnvConfigService;
	@Autowired
	private ProjectService projectService;


	@Autowired
	private DevpSysDpyResourcesValidator devpSysDpyResourcesValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResourcesValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增方案资源
	 * @param devpSysDpyResourcesAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增方案资源", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResourcesVO add(@RequestBody @Valid DevpSysDpyResourcesAddDto devpSysDpyResourcesAddDto){
		DevpSysDpyResources devpSysDpyResources = new DevpSysDpyResources();
		BeanUtils.copyProperties(devpSysDpyResourcesAddDto, devpSysDpyResources);

		devpSysDpyResourcesService.add(devpSysDpyResources);

		return  initViewProperty(devpSysDpyResources);
	}

	/**
	 * 删除方案资源,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除方案资源", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResources :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResourcesService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新方案资源
	 * @param devpSysDpyResourcesEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产方案资源(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyResourcesVO update(@RequestBody @Valid DevpSysDpyResourcesEditDto devpSysDpyResourcesEditDto, @PathVariable Long id){
		DevpSysDpyResources devpSysDpyResources = new DevpSysDpyResources();
		BeanUtils.copyProperties(devpSysDpyResourcesEditDto, devpSysDpyResources);
		devpSysDpyResources.setId(id);
		devpSysDpyResourcesService.merge(devpSysDpyResources);

		DevpSysDpyResourcesVO vo = initViewProperty(devpSysDpyResources);
		return  vo;
	}

	/**
	 * 根据ID查询方案资源
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询方案资源", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResourcesVO get(@PathVariable Long id) {

		DevpSysDpyResources devpSysDpyResources = devpSysDpyResourcesService.find(id);

		DevpSysDpyResourcesVO vo = initViewProperty(devpSysDpyResources);
		return vo;
	}

	/**
	 * 查询方案资源列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询方案资源列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResourcesVO> list(@RequestBody PageSearchRequest<DevpSysDpyResourcesCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyResources> page = devpSysDpyResourcesService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyResourcesVO> voList = new ArrayList<>();
		for(DevpSysDpyResources devpSysDpyResources : page.getContent()){
			voList.add(initViewProperty(devpSysDpyResources));
		}

		PageContent<DevpSysDpyResourcesVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出方案资源列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出方案资源列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysDpyResourcesCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyResourcesCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyResourcesVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyResourcesVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyResourcesVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"资源名称");
            headMap.put("code" ,"资源代码");
            headMap.put("alias" ,"资源别名");
            headMap.put("category" ,"资源类别");
            headMap.put("type" ,"资源类型");
            headMap.put("notes" ,"备注");
            headMap.put("description" ,"资源描述");
            headMap.put("version" ,"版本");
            headMap.put("seq" ,"顺序号");
            headMap.put("evn" ,"所属环境");
            headMap.put("project" ,"产品编号");
            headMap.put("outerResource" ,"外部资源");

        String title = new String("方案资源");
        String fileName = new String(("方案资源_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysDpyResourcesVO initViewProperty(DevpSysDpyResources devpSysDpyResources){

	    DevpSysDpyResourcesVO vo = new DevpSysDpyResourcesVO();
        BeanUtils.copyProperties(devpSysDpyResources, vo);


	    //初始化其他对象
	    initCategoryPropertyGroup(vo, devpSysDpyResources);
	    initTypePropertyGroup(vo, devpSysDpyResources);
	    initEvnPropertyGroup(vo, devpSysDpyResources);
	    initProjectPropertyGroup(vo, devpSysDpyResources);
        return vo;

	}


	private void initCategoryPropertyGroup(DevpSysDpyResourcesVO devpSysDpyResourcesVO, DevpSysDpyResources devpSysDpyResources){
	
		DevpSysDpyResourcesCategory category = devpSysDpyResourcesCategoryService.find(devpSysDpyResources.getCategory());
		if(category == null){
			return;
		}
		DevpSysDpyResourcesCategoryVO categoryVO = new DevpSysDpyResourcesCategoryVO();
		BeanUtils.copyProperties(category, categoryVO);

		devpSysDpyResourcesVO.setCategoryVO(categoryVO);

	}


	private void initTypePropertyGroup(DevpSysDpyResourcesVO devpSysDpyResourcesVO, DevpSysDpyResources devpSysDpyResources){
	
		DevpSysDpyResourcesType type = devpSysDpyResourcesTypeService.find(devpSysDpyResources.getType());
		if(type == null){
			return;
		}
		DevpSysDpyResourcesTypeVO typeVO = new DevpSysDpyResourcesTypeVO();
		BeanUtils.copyProperties(type, typeVO);

		devpSysDpyResourcesVO.setTypeVO(typeVO);

	}


	private void initEvnPropertyGroup(DevpSysDpyResourcesVO devpSysDpyResourcesVO, DevpSysDpyResources devpSysDpyResources){
	
		AppEnvConfig evn = appEnvConfigService.find(devpSysDpyResources.getEvn());
		if(evn == null){
			return;
		}
		AppEnvConfigVO evnVO = new AppEnvConfigVO();
		BeanUtils.copyProperties(evn, evnVO);

		devpSysDpyResourcesVO.setEvnVO(evnVO);

	}


	private void initProjectPropertyGroup(DevpSysDpyResourcesVO devpSysDpyResourcesVO, DevpSysDpyResources devpSysDpyResources){
	
		Project project = projectService.find(devpSysDpyResources.getProject());
		if(project == null){
			return;
		}
		ProjectVO projectVO = new ProjectVO();
		BeanUtils.copyProperties(project, projectVO);

		devpSysDpyResourcesVO.setProjectVO(projectVO);

	}


}

