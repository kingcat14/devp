package net.aicoder.speedcloud.business.deployscheme.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceType;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeEditDto;
import net.aicoder.speedcloud.business.deployscheme.service.ResourceTypeService;
import net.aicoder.speedcloud.business.deployscheme.valid.ResourceTypeValidator;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceTypeVO;


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
 * 管理部署资源类型
 * @author icode
 */
@Api(description = "部署资源类型", tags = "ResourceType")
@RestController
@RequestMapping(value = "/speedcloud/deployscheme/resourcetype")
public class ResourceTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceTypeController.class);


	@Autowired
	private ResourceTypeService resourceTypeService;



	@Autowired
	private ResourceTypeValidator resourceTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(resourceTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署资源类型
	 * @param resourceTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署资源类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ResourceTypeVO add(@RequestBody @Valid ResourceTypeAddDto resourceTypeAddDto){
		ResourceType resourceType = new ResourceType();
		BeanUtils.copyProperties(resourceTypeAddDto, resourceType);

		resourceTypeService.add(resourceType);

		return  initViewProperty(resourceType);
	}

	/**
	 * 删除部署资源类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署资源类型", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete resourceType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			resourceTypeService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署资源类型
	 * @param resourceTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署资源类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	ResourceTypeVO update(@RequestBody @Valid ResourceTypeEditDto resourceTypeEditDto, @PathVariable Long id){
		ResourceType resourceType = new ResourceType();
		BeanUtils.copyProperties(resourceTypeEditDto, resourceType);
		resourceType.setId(id);
		resourceTypeService.merge(resourceType);

		ResourceTypeVO vo = initViewProperty(resourceType);
		return  vo;
	}

	/**
	 * 根据ID查询部署资源类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署资源类型", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ResourceTypeVO get(@PathVariable Long id) {

		ResourceType resourceType = resourceTypeService.find(id);

		ResourceTypeVO vo = initViewProperty(resourceType);
		return vo;
	}

	/**
	 * 查询部署资源类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署资源类型列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ResourceTypeVO> list(@RequestBody PageSearchRequest<ResourceTypeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<ResourceType> page = resourceTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ResourceTypeVO> voList = new ArrayList<>();
		for(ResourceType resourceType : page.getContent()){
			voList.add(initViewProperty(resourceType));
		}

		PageContent<ResourceTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署资源类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署资源类型列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ResourceTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ResourceTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ResourceTypeVO> content = this.list(pageSearchRequest);

        List<ResourceTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ResourceTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("icon" ,"图标");

        String title = new String("部署资源类型");
        String fileName = new String(("部署资源类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ResourceTypeVO initViewProperty(ResourceType resourceType){

	    ResourceTypeVO vo = new ResourceTypeVO();
        BeanUtils.copyProperties(resourceType, vo);


	    //初始化其他对象
        return vo;

	}


}

