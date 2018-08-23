package net.aicoder.devp.business.ops.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.domain.DevpOpsAssetCmdb;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbEditDto;
import net.aicoder.devp.business.ops.service.DevpOpsAssetCmdbService;
import net.aicoder.devp.business.ops.valid.DevpOpsAssetCmdbValidator;
import net.aicoder.devp.business.ops.vo.DevpOpsAssetCmdbVO;

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
 * 管理IT资产配置数据库
 * @author icode
 */
@Api(description = "IT资产配置数据库", tags = "DevpOpsAssetCmdb")
@RestController
@RequestMapping(value = "/ops/devpOpsAssetCmdb")
public class DevpOpsAssetCmdbController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAssetCmdbController.class);


	@Autowired
	private DevpOpsAssetCmdbService devpOpsAssetCmdbService;


	@Autowired
	private DevpOpsAssetCmdbValidator devpOpsAssetCmdbValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsAssetCmdbValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增IT资产配置数据库
	 * @param devpOpsAssetCmdbAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增IT资产配置数据库", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsAssetCmdbVO add(@RequestBody @Valid DevpOpsAssetCmdbAddDto devpOpsAssetCmdbAddDto){
		DevpOpsAssetCmdb devpOpsAssetCmdb = new DevpOpsAssetCmdb();
		BeanUtils.copyProperties(devpOpsAssetCmdbAddDto, devpOpsAssetCmdb);

		devpOpsAssetCmdbService.add(devpOpsAssetCmdb);

		return  initViewProperty(devpOpsAssetCmdb);
	}

	/**
	 * 删除IT资产配置数据库,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除IT资产配置数据库", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsAssetCmdb :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsAssetCmdbService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新IT资产配置数据库
	 * @param devpOpsAssetCmdbEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产IT资产配置数据库(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpOpsAssetCmdbVO update(@RequestBody @Valid DevpOpsAssetCmdbEditDto devpOpsAssetCmdbEditDto, @PathVariable Long id){
		DevpOpsAssetCmdb devpOpsAssetCmdb = new DevpOpsAssetCmdb();
		BeanUtils.copyProperties(devpOpsAssetCmdbEditDto, devpOpsAssetCmdb);
		devpOpsAssetCmdb.setId(id);
		devpOpsAssetCmdbService.merge(devpOpsAssetCmdb);

		DevpOpsAssetCmdbVO vo = initViewProperty(devpOpsAssetCmdb);
		return  vo;
	}

	/**
	 * 根据ID查询IT资产配置数据库
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询IT资产配置数据库", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsAssetCmdbVO get(@PathVariable Long id) {

		DevpOpsAssetCmdb devpOpsAssetCmdb = devpOpsAssetCmdbService.find(id);

		DevpOpsAssetCmdbVO vo = initViewProperty(devpOpsAssetCmdb);
		return vo;
	}

	/**
	 * 查询IT资产配置数据库列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询IT资产配置数据库列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsAssetCmdbVO> list(@RequestBody PageSearchRequest<DevpOpsAssetCmdbCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpOpsAssetCmdb> page = devpOpsAssetCmdbService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpOpsAssetCmdbVO> voList = new ArrayList<>();
		for(DevpOpsAssetCmdb devpOpsAssetCmdb : page.getContent()){
			voList.add(initViewProperty(devpOpsAssetCmdb));
		}

		PageContent<DevpOpsAssetCmdbVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出IT资产配置数据库列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出IT资产配置数据库列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpOpsAssetCmdbCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpOpsAssetCmdbCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpOpsAssetCmdbVO> content = this.list(pageSearchRequest);

        List<DevpOpsAssetCmdbVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpOpsAssetCmdbVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"etype");
            headMap.put("name" ,"name");
            headMap.put("code" ,"code");
            headMap.put("alias" ,"alias");
            headMap.put("description" ,"description");
            headMap.put("recordState" ,"记录状态");
            headMap.put("typeCode" ,"type_code");
            headMap.put("typeName" ,"type_name");
            headMap.put("stereotype" ,"stereotype");
            headMap.put("scope" ,"scope");
            headMap.put("hardwareModel" ,"hardware_model");
            headMap.put("softwareModel" ,"software_model");
            headMap.put("version" ,"version");
            headMap.put("status" ,"status");
            headMap.put("createDate" ,"创建时间");
            headMap.put("expireDate" ,"到期时间");
            headMap.put("assetProject" ,"asset_project");
            headMap.put("assetArea" ,"asset_area");
            headMap.put("assetLocation" ,"asset_location");
            headMap.put("intAccessAddr" ,"int_access_addr");
            headMap.put("extAccessAddr" ,"ext_access_addr");
            headMap.put("acquisitionMode" ,"acquisition_mode");
            headMap.put("acquisitionDesc" ,"acquisition_desc");
            headMap.put("assetDept" ,"asset_dept");
            headMap.put("assetManager" ,"asset_manager");
            headMap.put("useDept" ,"use_dept");
            headMap.put("useManager" ,"use_manager");
            headMap.put("opsDept" ,"ops_dept");
            headMap.put("opsManager" ,"ops_manager");
            headMap.put("bizLine" ,"biz_line");
            headMap.put("bizManager" ,"biz_manager");
            headMap.put("goliveDate" ,"启用时间");
            headMap.put("majorCust" ,"major_cust");
            headMap.put("custManager" ,"cust_manager");
            headMap.put("custUsage" ,"cust_usage");
            headMap.put("notes" ,"notes");
            headMap.put("prdTid" ,"关联产品租户编号");
            headMap.put("prdRid" ,"关联产品记录编号");
            headMap.put("parasCode" ,"paras_code");
            headMap.put("createUcode" ,"create_ucode");
            headMap.put("createUname" ,"create_uname");
            headMap.put("modifyUcode" ,"modify_ucode");
            headMap.put("modifyUname" ,"modify_uname");
            headMap.put("acquisitionProvider" ,"acquisition_provider");

        String title = new String("IT资产配置数据库");
        String fileName = new String(("IT资产配置数据库_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpOpsAssetCmdbVO initViewProperty(DevpOpsAssetCmdb devpOpsAssetCmdb){

	    DevpOpsAssetCmdbVO vo = new DevpOpsAssetCmdbVO();
        BeanUtils.copyProperties(devpOpsAssetCmdb, vo);


	    //初始化其他对象
        return vo;


	}


}
