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
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourcesCategory;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryEditDto;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpyResourcesCategoryService;
import net.aicoder.speedcloud.business.deploy.valid.DevpSysDpyResourcesCategoryValidator;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesCategoryVO;


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
 * 管理部署资源类别
 * @author icode
 */
@Api(description = "部署资源类别", tags = "DevpSysDpyResourcesCategory")
@RestController
@RequestMapping(value = "/speedcloud/deploy/devpsysdpyresourcescategory")
public class DevpSysDpyResourcesCategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesCategoryController.class);


	@Autowired
	private DevpSysDpyResourcesCategoryService devpSysDpyResourcesCategoryService;



	@Autowired
	private DevpSysDpyResourcesCategoryValidator devpSysDpyResourcesCategoryValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResourcesCategoryValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署资源类别
	 * @param devpSysDpyResourcesCategoryAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署资源类别", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResourcesCategoryVO add(@RequestBody @Valid DevpSysDpyResourcesCategoryAddDto devpSysDpyResourcesCategoryAddDto){
		DevpSysDpyResourcesCategory devpSysDpyResourcesCategory = new DevpSysDpyResourcesCategory();
		BeanUtils.copyProperties(devpSysDpyResourcesCategoryAddDto, devpSysDpyResourcesCategory);

		devpSysDpyResourcesCategoryService.add(devpSysDpyResourcesCategory);

		return  initViewProperty(devpSysDpyResourcesCategory);
	}

	/**
	 * 删除部署资源类别,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署资源类别", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResourcesCategory :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResourcesCategoryService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署资源类别
	 * @param devpSysDpyResourcesCategoryEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署资源类别(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyResourcesCategoryVO update(@RequestBody @Valid DevpSysDpyResourcesCategoryEditDto devpSysDpyResourcesCategoryEditDto, @PathVariable Long id){
		DevpSysDpyResourcesCategory devpSysDpyResourcesCategory = new DevpSysDpyResourcesCategory();
		BeanUtils.copyProperties(devpSysDpyResourcesCategoryEditDto, devpSysDpyResourcesCategory);
		devpSysDpyResourcesCategory.setId(id);
		devpSysDpyResourcesCategoryService.merge(devpSysDpyResourcesCategory);

		DevpSysDpyResourcesCategoryVO vo = initViewProperty(devpSysDpyResourcesCategory);
		return  vo;
	}

	/**
	 * 根据ID查询部署资源类别
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署资源类别", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResourcesCategoryVO get(@PathVariable Long id) {

		DevpSysDpyResourcesCategory devpSysDpyResourcesCategory = devpSysDpyResourcesCategoryService.find(id);

		DevpSysDpyResourcesCategoryVO vo = initViewProperty(devpSysDpyResourcesCategory);
		return vo;
	}

	/**
	 * 查询部署资源类别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署资源类别列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResourcesCategoryVO> list(@RequestBody PageSearchRequest<DevpSysDpyResourcesCategoryCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyResourcesCategory> page = devpSysDpyResourcesCategoryService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyResourcesCategoryVO> voList = new ArrayList<>();
		for(DevpSysDpyResourcesCategory devpSysDpyResourcesCategory : page.getContent()){
			voList.add(initViewProperty(devpSysDpyResourcesCategory));
		}

		PageContent<DevpSysDpyResourcesCategoryVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(DevpSysDpyResourcesCategoryCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyResourcesCategoryCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyResourcesCategoryVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyResourcesCategoryVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyResourcesCategoryVO vo : voList){
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

	private DevpSysDpyResourcesCategoryVO initViewProperty(DevpSysDpyResourcesCategory devpSysDpyResourcesCategory){

	    DevpSysDpyResourcesCategoryVO vo = new DevpSysDpyResourcesCategoryVO();
        BeanUtils.copyProperties(devpSysDpyResourcesCategory, vo);


	    //初始化其他对象
        return vo;

	}


}

