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
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceRelationType;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeEditDto;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpyResourceRelationTypeService;
import net.aicoder.speedcloud.business.deploy.valid.DevpSysDpyResourceRelationTypeValidator;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourceRelationTypeVO;


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
 * 管理资源关系类型
 * @author icode
 */
@Api(description = "资源关系类型", tags = "DevpSysDpyResourceRelationType")
@RestController
@RequestMapping(value = "/speedcloud/deploy/devpsysdpyresourcerelationtype")
public class DevpSysDpyResourceRelationTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourceRelationTypeController.class);


	@Autowired
	private DevpSysDpyResourceRelationTypeService devpSysDpyResourceRelationTypeService;



	@Autowired
	private DevpSysDpyResourceRelationTypeValidator devpSysDpyResourceRelationTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResourceRelationTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增资源关系类型
	 * @param devpSysDpyResourceRelationTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资源关系类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResourceRelationTypeVO add(@RequestBody @Valid DevpSysDpyResourceRelationTypeAddDto devpSysDpyResourceRelationTypeAddDto){
		DevpSysDpyResourceRelationType devpSysDpyResourceRelationType = new DevpSysDpyResourceRelationType();
		BeanUtils.copyProperties(devpSysDpyResourceRelationTypeAddDto, devpSysDpyResourceRelationType);

		devpSysDpyResourceRelationTypeService.add(devpSysDpyResourceRelationType);

		return  initViewProperty(devpSysDpyResourceRelationType);
	}

	/**
	 * 删除资源关系类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资源关系类型", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResourceRelationType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResourceRelationTypeService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资源关系类型
	 * @param devpSysDpyResourceRelationTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资源关系类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyResourceRelationTypeVO update(@RequestBody @Valid DevpSysDpyResourceRelationTypeEditDto devpSysDpyResourceRelationTypeEditDto, @PathVariable Long id){
		DevpSysDpyResourceRelationType devpSysDpyResourceRelationType = new DevpSysDpyResourceRelationType();
		BeanUtils.copyProperties(devpSysDpyResourceRelationTypeEditDto, devpSysDpyResourceRelationType);
		devpSysDpyResourceRelationType.setId(id);
		devpSysDpyResourceRelationTypeService.merge(devpSysDpyResourceRelationType);

		DevpSysDpyResourceRelationTypeVO vo = initViewProperty(devpSysDpyResourceRelationType);
		return  vo;
	}

	/**
	 * 根据ID查询资源关系类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资源关系类型", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResourceRelationTypeVO get(@PathVariable Long id) {

		DevpSysDpyResourceRelationType devpSysDpyResourceRelationType = devpSysDpyResourceRelationTypeService.find(id);

		DevpSysDpyResourceRelationTypeVO vo = initViewProperty(devpSysDpyResourceRelationType);
		return vo;
	}

	/**
	 * 查询资源关系类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资源关系类型列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResourceRelationTypeVO> list(@RequestBody PageSearchRequest<DevpSysDpyResourceRelationTypeCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyResourceRelationType> page = devpSysDpyResourceRelationTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyResourceRelationTypeVO> voList = new ArrayList<>();
		for(DevpSysDpyResourceRelationType devpSysDpyResourceRelationType : page.getContent()){
			voList.add(initViewProperty(devpSysDpyResourceRelationType));
		}

		PageContent<DevpSysDpyResourceRelationTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出资源关系类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出资源关系类型列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysDpyResourceRelationTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyResourceRelationTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyResourceRelationTypeVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyResourceRelationTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyResourceRelationTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("icon" ,"图标");

        String title = new String("资源关系类型");
        String fileName = new String(("资源关系类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysDpyResourceRelationTypeVO initViewProperty(DevpSysDpyResourceRelationType devpSysDpyResourceRelationType){

	    DevpSysDpyResourceRelationTypeVO vo = new DevpSysDpyResourceRelationTypeVO();
        BeanUtils.copyProperties(devpSysDpyResourceRelationType, vo);


	    //初始化其他对象
        return vo;

	}


}

