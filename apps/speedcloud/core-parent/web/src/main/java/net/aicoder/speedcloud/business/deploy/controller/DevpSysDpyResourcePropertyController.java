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
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceProperty;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcePropertyCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcePropertyAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcePropertyEditDto;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpyResourcePropertyService;
import net.aicoder.speedcloud.business.deploy.valid.DevpSysDpyResourcePropertyValidator;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcePropertyVO;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResources;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpyResourcesService;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesVO;


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
 * 管理资源属性
 * @author icode
 */
@Api(description = "资源属性", tags = "DevpSysDpyResourceProperty")
@RestController
@RequestMapping(value = "/speedcloud/deploy/devpsysdpyresourceproperty")
public class DevpSysDpyResourcePropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcePropertyController.class);


	@Autowired
	private DevpSysDpyResourcePropertyService devpSysDpyResourcePropertyService;

	@Autowired
	private DevpSysDpyResourcesService devpSysDpyResourcesService;


	@Autowired
	private DevpSysDpyResourcePropertyValidator devpSysDpyResourcePropertyValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResourcePropertyValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增资源属性
	 * @param devpSysDpyResourcePropertyAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资源属性", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResourcePropertyVO add(@RequestBody @Valid DevpSysDpyResourcePropertyAddDto devpSysDpyResourcePropertyAddDto){
		DevpSysDpyResourceProperty devpSysDpyResourceProperty = new DevpSysDpyResourceProperty();
		BeanUtils.copyProperties(devpSysDpyResourcePropertyAddDto, devpSysDpyResourceProperty);

		devpSysDpyResourcePropertyService.add(devpSysDpyResourceProperty);

		return  initViewProperty(devpSysDpyResourceProperty);
	}

	/**
	 * 删除资源属性,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资源属性", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResourceProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResourcePropertyService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资源属性
	 * @param devpSysDpyResourcePropertyEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资源属性(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyResourcePropertyVO update(@RequestBody @Valid DevpSysDpyResourcePropertyEditDto devpSysDpyResourcePropertyEditDto, @PathVariable Long id){
		DevpSysDpyResourceProperty devpSysDpyResourceProperty = new DevpSysDpyResourceProperty();
		BeanUtils.copyProperties(devpSysDpyResourcePropertyEditDto, devpSysDpyResourceProperty);
		devpSysDpyResourceProperty.setId(id);
		devpSysDpyResourcePropertyService.merge(devpSysDpyResourceProperty);

		DevpSysDpyResourcePropertyVO vo = initViewProperty(devpSysDpyResourceProperty);
		return  vo;
	}

	/**
	 * 根据ID查询资源属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资源属性", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResourcePropertyVO get(@PathVariable Long id) {

		DevpSysDpyResourceProperty devpSysDpyResourceProperty = devpSysDpyResourcePropertyService.find(id);

		DevpSysDpyResourcePropertyVO vo = initViewProperty(devpSysDpyResourceProperty);
		return vo;
	}

	/**
	 * 查询资源属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资源属性列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResourcePropertyVO> list(@RequestBody PageSearchRequest<DevpSysDpyResourcePropertyCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyResourceProperty> page = devpSysDpyResourcePropertyService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyResourcePropertyVO> voList = new ArrayList<>();
		for(DevpSysDpyResourceProperty devpSysDpyResourceProperty : page.getContent()){
			voList.add(initViewProperty(devpSysDpyResourceProperty));
		}

		PageContent<DevpSysDpyResourcePropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(DevpSysDpyResourcePropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyResourcePropertyCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyResourcePropertyVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyResourcePropertyVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyResourcePropertyVO vo : voList){
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

	private DevpSysDpyResourcePropertyVO initViewProperty(DevpSysDpyResourceProperty devpSysDpyResourceProperty){

	    DevpSysDpyResourcePropertyVO vo = new DevpSysDpyResourcePropertyVO();
        BeanUtils.copyProperties(devpSysDpyResourceProperty, vo);


	    //初始化其他对象
	    initResourcePropertyGroup(vo, devpSysDpyResourceProperty);
        return vo;

	}


	private void initResourcePropertyGroup(DevpSysDpyResourcePropertyVO devpSysDpyResourcePropertyVO, DevpSysDpyResourceProperty devpSysDpyResourceProperty){
	
		DevpSysDpyResources resource = devpSysDpyResourcesService.find(devpSysDpyResourceProperty.getResource());
		if(resource == null){
			return;
		}
		DevpSysDpyResourcesVO resourceVO = new DevpSysDpyResourcesVO();
		BeanUtils.copyProperties(resource, resourceVO);

		devpSysDpyResourcePropertyVO.setResourceVO(resourceVO);

	}


}

