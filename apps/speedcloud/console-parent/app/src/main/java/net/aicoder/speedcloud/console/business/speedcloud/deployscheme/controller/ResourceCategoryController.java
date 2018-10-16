package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceCategoryVO;
import net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service.ResourceCategoryRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.deployscheme.valid.ResourceCategoryValidator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理部署资源类别
 * @author icode
 */
@Api(description = "部署资源类别", tags = "ResourceCategory")
@RestController
@RequestMapping(value = "/speedcloud/deployscheme/resourcecategory")
public class ResourceCategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCategoryController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private ResourceCategoryRibbonService resourceCategoryRibbonService;

	@Autowired
	ResourceCategoryValidator resourceCategoryValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(resourceCategoryValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署资源类别
	 * @param resourceCategoryAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署资源类别", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ResourceCategoryVO add(@RequestBody ResourceCategoryAddDto resourceCategoryAddDto){
    	resourceCategoryAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  resourceCategoryRibbonService.add(resourceCategoryAddDto);
	}

	/**
	 * 删除部署资源类别,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署资源类别", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete resourceCategory :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			resourceCategoryRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署资源类别
	 * @param resourceCategoryEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署资源类别(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public ResourceCategoryVO update(@RequestBody ResourceCategoryEditDto resourceCategoryEditDto, @ApiParam(value = "要查询的部署资源类别id") @PathVariable Long id){

		ResourceCategoryVO vo = resourceCategoryRibbonService.merge(id, resourceCategoryEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询部署资源类别
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署资源类别", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public ResourceCategoryVO get(@ApiParam(value = "要查询的部署资源类别id") @PathVariable Long id) {

		ResourceCategoryVO vo = resourceCategoryRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询部署资源类别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署资源类别列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ResourceCategoryVO> list(@RequestBody PageSearchRequest<ResourceCategoryCondition> pageSearchRequest){

		ResourceCategoryCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new ResourceCategoryCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<ResourceCategoryVO> pageContent = resourceCategoryRibbonService.list(pageSearchRequest);
		for(ResourceCategoryVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署资源类别列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署资源类别列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ResourceCategoryCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<ResourceCategoryCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ResourceCategoryVO> content = this.list(pageSearchRequest);

        List<ResourceCategoryVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ResourceCategoryVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("icon" ,"图标");

        String title = new String("部署资源类别");
        String fileName = new String(("部署资源类别_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ResourceCategoryVO initViewProperty( ResourceCategoryVO vo){

	   


	   
        return vo;

	}


}
