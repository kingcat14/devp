package net.aicoder.speedcloud.business.deployscheme.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.deployscheme.domain.Resource;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceCategory;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceType;
import net.aicoder.speedcloud.business.deployscheme.domain.Scheme;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceEditDto;
import net.aicoder.speedcloud.business.deployscheme.service.ResourceCategoryService;
import net.aicoder.speedcloud.business.deployscheme.service.ResourceService;
import net.aicoder.speedcloud.business.deployscheme.service.ResourceTypeService;
import net.aicoder.speedcloud.business.deployscheme.service.SchemeService;
import net.aicoder.speedcloud.business.deployscheme.valid.ResourceValidator;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceVO;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourcesCategoryVO;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourcesTypeVO;
import net.aicoder.speedcloud.business.deployscheme.vo.SchemeVO;
import net.aicoder.speedcloud.business.env.domain.AppEnvConfig;
import net.aicoder.speedcloud.business.env.service.AppEnvConfigService;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.business.project.domain.Project;
import net.aicoder.speedcloud.business.project.service.ProjectService;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理方案资源
 * @author icode
 */
@Api(description = "方案资源", tags = "Resource")
@RestController
@RequestMapping(value = "/speedcloud/deployscheme/resource")
public class ResourceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);


	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ResourceCategoryService resourceCategoryService;
	@Autowired
	private ResourceTypeService resourceTypeService;
	@Autowired
	private AppEnvConfigService appEnvConfigService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private SchemeService schemeService;


	@Autowired
	private ResourceValidator resourceValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(resourceValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增方案资源
	 * @param resourceAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增方案资源", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ResourceVO add(@RequestBody @Valid ResourceAddDto resourceAddDto){
		Resource resource = new Resource();
		BeanUtils.copyProperties(resourceAddDto, resource);

		resourceService.add(resource);

		return  initViewProperty(resource);
	}

	/**
	 * 删除方案资源,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除方案资源", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete resource :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			resourceService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新方案资源
	 * @param resourceEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产方案资源(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	ResourceVO update(@RequestBody @Valid ResourceEditDto resourceEditDto, @PathVariable Long id){
		Resource resource = new Resource();
		BeanUtils.copyProperties(resourceEditDto, resource);
		resource.setId(id);
		resourceService.merge(resource);

		ResourceVO vo = initViewProperty(resource);
		return  vo;
	}

	/**
	 * 根据ID查询方案资源
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询方案资源", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ResourceVO get(@PathVariable Long id) {

		Resource resource = resourceService.find(id);

		ResourceVO vo = initViewProperty(resource);
		return vo;
	}

	/**
	 * 查询方案资源列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询方案资源列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ResourceVO> list(@RequestBody PageSearchRequest<ResourceCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<Resource> page = resourceService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ResourceVO> voList = new ArrayList<>();
		for(Resource resource : page.getContent()){
			voList.add(initViewProperty(resource));
		}

		PageContent<ResourceVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(ResourceCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ResourceCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ResourceVO> content = this.list(pageSearchRequest);

        List<ResourceVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ResourceVO vo : voList){
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
            headMap.put("scheme" ,"所属方案");

        String title = new String("方案资源");
        String fileName = new String(("方案资源_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ResourceVO initViewProperty(Resource resource){

	    ResourceVO vo = new ResourceVO();
        BeanUtils.copyProperties(resource, vo);


	    //初始化其他对象
	    initCategoryPropertyGroup(vo, resource);
	    initTypePropertyGroup(vo, resource);
	    initEvnPropertyGroup(vo, resource);
	    initProjectPropertyGroup(vo, resource);
	    initSchemePropertyGroup(vo, resource);
        return vo;

	}


	private void initCategoryPropertyGroup(ResourceVO resourceVO, Resource resource){
	
		ResourceCategory category = resourceCategoryService.find(resource.getCategory());
		if(category == null){
			return;
		}
		ResourcesCategoryVO categoryVO = new ResourcesCategoryVO();
		BeanUtils.copyProperties(category, categoryVO);

		resourceVO.setCategoryVO(categoryVO);

	}


	private void initTypePropertyGroup(ResourceVO resourceVO, Resource resource){
	
		ResourceType type = resourceTypeService.find(resource.getType());
		if(type == null){
			return;
		}
		ResourcesTypeVO typeVO = new ResourcesTypeVO();
		BeanUtils.copyProperties(type, typeVO);

		resourceVO.setTypeVO(typeVO);

	}


	private void initEvnPropertyGroup(ResourceVO resourceVO, Resource resource){
	
		AppEnvConfig evn = appEnvConfigService.find(resource.getEvn());
		if(evn == null){
			return;
		}
		AppEnvConfigVO evnVO = new AppEnvConfigVO();
		BeanUtils.copyProperties(evn, evnVO);

		resourceVO.setEvnVO(evnVO);

	}


	private void initProjectPropertyGroup(ResourceVO resourceVO, Resource resource){
	
		Project project = projectService.find(resource.getProject());
		if(project == null){
			return;
		}
		ProjectVO projectVO = new ProjectVO();
		BeanUtils.copyProperties(project, projectVO);

		resourceVO.setProjectVO(projectVO);

	}


	private void initSchemePropertyGroup(ResourceVO resourceVO, Resource resource){
	
		Scheme scheme = schemeService.find(resource.getScheme());
		if(scheme == null){
			return;
		}
		SchemeVO schemeVO = new SchemeVO();
		BeanUtils.copyProperties(scheme, schemeVO);

		resourceVO.setSchemeVO(schemeVO);

	}


}

