package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourcePropertyVO;
import net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service.ResourcePropertyRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.deployscheme.valid.ResourcePropertyValidator;

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
 * 管理资源属性
 * @author icode
 */
@Api(description = "资源属性", tags = "ResourceProperty")
@RestController
@RequestMapping(value = "/speedcloud/deployscheme/resourceproperty")
public class ResourcePropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcePropertyController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private ResourcePropertyRibbonService resourcePropertyRibbonService;

	@Autowired
	ResourcePropertyValidator resourcePropertyValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(resourcePropertyValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增资源属性
	 * @param resourcePropertyAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资源属性", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	@SaaSAnnotation
	public ResourcePropertyVO add(@RequestBody ResourcePropertyAddDto resourcePropertyAddDto){

		return  resourcePropertyRibbonService.add(resourcePropertyAddDto);
	}

	/**
	 * 删除资源属性,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资源属性", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete resourceProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			resourcePropertyRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资源属性
	 * @param resourcePropertyEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资源属性(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public ResourcePropertyVO update(@RequestBody ResourcePropertyEditDto resourcePropertyEditDto, @ApiParam(value = "要查询的资源属性id") @PathVariable Long id){

		ResourcePropertyVO vo = resourcePropertyRibbonService.merge(id, resourcePropertyEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询资源属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资源属性", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public ResourcePropertyVO get(@ApiParam(value = "要查询的资源属性id") @PathVariable Long id) {

		ResourcePropertyVO vo = resourcePropertyRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询资源属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资源属性列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = ResourcePropertyCondition.class )
	public PageContent<ResourcePropertyVO> list(@RequestBody PageSearchRequest<ResourcePropertyCondition> pageSearchRequest){

		PageContent<ResourcePropertyVO> pageContent = resourcePropertyRibbonService.list(pageSearchRequest);
		for(ResourcePropertyVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出资源属性列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出资源属性列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ResourcePropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<ResourcePropertyCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ResourcePropertyVO> content = this.list(pageSearchRequest);

        List<ResourcePropertyVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ResourcePropertyVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("resource" ,"所属资源");
            headMap.put("name" ,"属性名称");
            headMap.put("code" ,"属性代码");
            headMap.put("value" ,"属性值");

        String title = new String("资源属性");
        String fileName = new String(("资源属性_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ResourcePropertyVO initViewProperty( ResourcePropertyVO vo){

	   


	   
        return vo;

	}


}
