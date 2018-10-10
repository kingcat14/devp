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
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourcesType;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeEditDto;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpyResourcesTypeService;
import net.aicoder.speedcloud.business.deploy.valid.DevpSysDpyResourcesTypeValidator;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesTypeVO;


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
@Api(description = "部署资源类型", tags = "DevpSysDpyResourcesType")
@RestController
@RequestMapping(value = "/speedcloud/deploy/devpsysdpyresourcestype")
public class DevpSysDpyResourcesTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesTypeController.class);


	@Autowired
	private DevpSysDpyResourcesTypeService devpSysDpyResourcesTypeService;



	@Autowired
	private DevpSysDpyResourcesTypeValidator devpSysDpyResourcesTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResourcesTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署资源类型
	 * @param devpSysDpyResourcesTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署资源类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResourcesTypeVO add(@RequestBody @Valid DevpSysDpyResourcesTypeAddDto devpSysDpyResourcesTypeAddDto){
		DevpSysDpyResourcesType devpSysDpyResourcesType = new DevpSysDpyResourcesType();
		BeanUtils.copyProperties(devpSysDpyResourcesTypeAddDto, devpSysDpyResourcesType);

		devpSysDpyResourcesTypeService.add(devpSysDpyResourcesType);

		return  initViewProperty(devpSysDpyResourcesType);
	}

	/**
	 * 删除部署资源类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署资源类型", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResourcesType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResourcesTypeService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署资源类型
	 * @param devpSysDpyResourcesTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署资源类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyResourcesTypeVO update(@RequestBody @Valid DevpSysDpyResourcesTypeEditDto devpSysDpyResourcesTypeEditDto, @PathVariable Long id){
		DevpSysDpyResourcesType devpSysDpyResourcesType = new DevpSysDpyResourcesType();
		BeanUtils.copyProperties(devpSysDpyResourcesTypeEditDto, devpSysDpyResourcesType);
		devpSysDpyResourcesType.setId(id);
		devpSysDpyResourcesTypeService.merge(devpSysDpyResourcesType);

		DevpSysDpyResourcesTypeVO vo = initViewProperty(devpSysDpyResourcesType);
		return  vo;
	}

	/**
	 * 根据ID查询部署资源类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署资源类型", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResourcesTypeVO get(@PathVariable Long id) {

		DevpSysDpyResourcesType devpSysDpyResourcesType = devpSysDpyResourcesTypeService.find(id);

		DevpSysDpyResourcesTypeVO vo = initViewProperty(devpSysDpyResourcesType);
		return vo;
	}

	/**
	 * 查询部署资源类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署资源类型列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResourcesTypeVO> list(@RequestBody PageSearchRequest<DevpSysDpyResourcesTypeCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyResourcesType> page = devpSysDpyResourcesTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyResourcesTypeVO> voList = new ArrayList<>();
		for(DevpSysDpyResourcesType devpSysDpyResourcesType : page.getContent()){
			voList.add(initViewProperty(devpSysDpyResourcesType));
		}

		PageContent<DevpSysDpyResourcesTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(DevpSysDpyResourcesTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyResourcesTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyResourcesTypeVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyResourcesTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyResourcesTypeVO vo : voList){
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

	private DevpSysDpyResourcesTypeVO initViewProperty(DevpSysDpyResourcesType devpSysDpyResourcesType){

	    DevpSysDpyResourcesTypeVO vo = new DevpSysDpyResourcesTypeVO();
        BeanUtils.copyProperties(devpSysDpyResourcesType, vo);


	    //初始化其他对象
        return vo;

	}


}

