package net.aicoder.devp.business.deploy.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyResources;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResourcesCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResourcesAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResourcesEditDto;
import net.aicoder.devp.business.deploy.service.DevpSysDpyResourcesService;
import net.aicoder.devp.business.deploy.valid.DevpSysDpyResourcesValidator;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyResourcesVO;

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
 * 管理部署关联资源
 * @author icode
 */
@Api(description = "部署关联资源", tags = "DevpSysDpyResources")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyResources")
public class DevpSysDpyResourcesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesController.class);


	@Autowired
	private DevpSysDpyResourcesService devpSysDpyResourcesService;


	@Autowired
	private DevpSysDpyResourcesValidator devpSysDpyResourcesValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResourcesValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署关联资源
	 * @param devpSysDpyResourcesAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署关联资源", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResourcesVO add(@RequestBody @Valid DevpSysDpyResourcesAddDto devpSysDpyResourcesAddDto){
		DevpSysDpyResources devpSysDpyResources = new DevpSysDpyResources();
		BeanUtils.copyProperties(devpSysDpyResourcesAddDto, devpSysDpyResources);

		devpSysDpyResourcesService.add(devpSysDpyResources);

		return  initViewProperty(devpSysDpyResources);
	}

	/**
	 * 删除部署关联资源,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署关联资源", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResources :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResourcesService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署关联资源
	 * @param devpSysDpyResourcesEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署关联资源(修改全部字段,未传入置空)", httpMethod = "PUT")
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
	 * 根据ID查询部署关联资源
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署关联资源", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResourcesVO get(@PathVariable Long id) {

		DevpSysDpyResources devpSysDpyResources = devpSysDpyResourcesService.find(id);

		DevpSysDpyResourcesVO vo = initViewProperty(devpSysDpyResources);
		return vo;
	}

	/**
	 * 查询部署关联资源列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署关联资源列表", httpMethod = "POST")
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
     * 导出部署关联资源列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署关联资源列表", httpMethod = "POST")
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


        String title = new String("部署关联资源");
        String fileName = new String(("部署关联资源_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysDpyResourcesVO initViewProperty(DevpSysDpyResources devpSysDpyResources){

	    DevpSysDpyResourcesVO vo = new DevpSysDpyResourcesVO();
        BeanUtils.copyProperties(devpSysDpyResources, vo);


	    //初始化其他对象
        return vo;


	}


}
