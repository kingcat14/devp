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
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceRef;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefEditDto;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpyResourceRefService;
import net.aicoder.speedcloud.business.deploy.valid.DevpSysDpyResourceRefValidator;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourceRefVO;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResources;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpyResourcesService;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesVO;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceRelationType;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpyResourceRelationTypeService;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourceRelationTypeVO;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyScheme;
import net.aicoder.speedcloud.business.deploy.service.DevpSysDpySchemeService;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpySchemeVO;


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
 * 管理方案资源间关系
 * @author icode
 */
@Api(description = "方案资源间关系", tags = "DevpSysDpyResourceRef")
@RestController
@RequestMapping(value = "/speedcloud/deploy/devpsysdpyresourceref")
public class DevpSysDpyResourceRefController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourceRefController.class);


	@Autowired
	private DevpSysDpyResourceRefService devpSysDpyResourceRefService;

	@Autowired
	private DevpSysDpyResourcesService devpSysDpyResourcesService;
	@Autowired
	private DevpSysDpyResourceRelationTypeService devpSysDpyResourceRelationTypeService;
	@Autowired
	private DevpSysDpySchemeService devpSysDpySchemeService;


	@Autowired
	private DevpSysDpyResourceRefValidator devpSysDpyResourceRefValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResourceRefValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增方案资源间关系
	 * @param devpSysDpyResourceRefAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增方案资源间关系", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResourceRefVO add(@RequestBody @Valid DevpSysDpyResourceRefAddDto devpSysDpyResourceRefAddDto){
		DevpSysDpyResourceRef devpSysDpyResourceRef = new DevpSysDpyResourceRef();
		BeanUtils.copyProperties(devpSysDpyResourceRefAddDto, devpSysDpyResourceRef);

		devpSysDpyResourceRefService.add(devpSysDpyResourceRef);

		return  initViewProperty(devpSysDpyResourceRef);
	}

	/**
	 * 删除方案资源间关系,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除方案资源间关系", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResourceRef :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResourceRefService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新方案资源间关系
	 * @param devpSysDpyResourceRefEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产方案资源间关系(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyResourceRefVO update(@RequestBody @Valid DevpSysDpyResourceRefEditDto devpSysDpyResourceRefEditDto, @PathVariable Long id){
		DevpSysDpyResourceRef devpSysDpyResourceRef = new DevpSysDpyResourceRef();
		BeanUtils.copyProperties(devpSysDpyResourceRefEditDto, devpSysDpyResourceRef);
		devpSysDpyResourceRef.setId(id);
		devpSysDpyResourceRefService.merge(devpSysDpyResourceRef);

		DevpSysDpyResourceRefVO vo = initViewProperty(devpSysDpyResourceRef);
		return  vo;
	}

	/**
	 * 根据ID查询方案资源间关系
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询方案资源间关系", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResourceRefVO get(@PathVariable Long id) {

		DevpSysDpyResourceRef devpSysDpyResourceRef = devpSysDpyResourceRefService.find(id);

		DevpSysDpyResourceRefVO vo = initViewProperty(devpSysDpyResourceRef);
		return vo;
	}

	/**
	 * 查询方案资源间关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询方案资源间关系列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResourceRefVO> list(@RequestBody PageSearchRequest<DevpSysDpyResourceRefCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyResourceRef> page = devpSysDpyResourceRefService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyResourceRefVO> voList = new ArrayList<>();
		for(DevpSysDpyResourceRef devpSysDpyResourceRef : page.getContent()){
			voList.add(initViewProperty(devpSysDpyResourceRef));
		}

		PageContent<DevpSysDpyResourceRefVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出方案资源间关系列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出方案资源间关系列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysDpyResourceRefCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyResourceRefCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyResourceRefVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyResourceRefVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyResourceRefVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("resource" ,"主资源");
            headMap.put("refResource" ,"关联资源");
            headMap.put("type" ,"对应关系类型");
            headMap.put("code" ,"对应关系代码");
            headMap.put("name" ,"对应关系名称");
            headMap.put("description" ,"对应关系描述");
            headMap.put("scheme" ,"部署方案编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("direction" ,"对应关系方向");

        String title = new String("方案资源间关系");
        String fileName = new String(("方案资源间关系_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysDpyResourceRefVO initViewProperty(DevpSysDpyResourceRef devpSysDpyResourceRef){

	    DevpSysDpyResourceRefVO vo = new DevpSysDpyResourceRefVO();
        BeanUtils.copyProperties(devpSysDpyResourceRef, vo);


	    //初始化其他对象
	    initResourcePropertyGroup(vo, devpSysDpyResourceRef);
	    initRefResourcePropertyGroup(vo, devpSysDpyResourceRef);
	    initTypePropertyGroup(vo, devpSysDpyResourceRef);
	    initSchemePropertyGroup(vo, devpSysDpyResourceRef);
        return vo;

	}


	private void initResourcePropertyGroup(DevpSysDpyResourceRefVO devpSysDpyResourceRefVO, DevpSysDpyResourceRef devpSysDpyResourceRef){
	
		DevpSysDpyResources resource = devpSysDpyResourcesService.find(devpSysDpyResourceRef.getResource());
		if(resource == null){
			return;
		}
		DevpSysDpyResourcesVO resourceVO = new DevpSysDpyResourcesVO();
		BeanUtils.copyProperties(resource, resourceVO);

		devpSysDpyResourceRefVO.setResourceVO(resourceVO);

	}


	private void initRefResourcePropertyGroup(DevpSysDpyResourceRefVO devpSysDpyResourceRefVO, DevpSysDpyResourceRef devpSysDpyResourceRef){
	
		DevpSysDpyResources refResource = devpSysDpyResourcesService.find(devpSysDpyResourceRef.getRefResource());
		if(refResource == null){
			return;
		}
		DevpSysDpyResourcesVO refResourceVO = new DevpSysDpyResourcesVO();
		BeanUtils.copyProperties(refResource, refResourceVO);

		devpSysDpyResourceRefVO.setRefResourceVO(refResourceVO);

	}


	private void initTypePropertyGroup(DevpSysDpyResourceRefVO devpSysDpyResourceRefVO, DevpSysDpyResourceRef devpSysDpyResourceRef){
	
		DevpSysDpyResourceRelationType type = devpSysDpyResourceRelationTypeService.find(devpSysDpyResourceRef.getType());
		if(type == null){
			return;
		}
		DevpSysDpyResourceRelationTypeVO typeVO = new DevpSysDpyResourceRelationTypeVO();
		BeanUtils.copyProperties(type, typeVO);

		devpSysDpyResourceRefVO.setTypeVO(typeVO);

	}


	private void initSchemePropertyGroup(DevpSysDpyResourceRefVO devpSysDpyResourceRefVO, DevpSysDpyResourceRef devpSysDpyResourceRef){
	
		DevpSysDpyScheme scheme = devpSysDpySchemeService.find(devpSysDpyResourceRef.getScheme());
		if(scheme == null){
			return;
		}
		DevpSysDpySchemeVO schemeVO = new DevpSysDpySchemeVO();
		BeanUtils.copyProperties(scheme, schemeVO);

		devpSysDpyResourceRefVO.setSchemeVO(schemeVO);

	}


}

