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
 * 管理资产定义
 * @author icode
 */
@Api(description = "资产定义", tags = "DevpOpsAssetCmdb")
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
	 * 新增资产定义
	 * @param devpOpsAssetCmdbAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资产定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsAssetCmdbVO add(@RequestBody @Valid DevpOpsAssetCmdbAddDto devpOpsAssetCmdbAddDto){
		DevpOpsAssetCmdb devpOpsAssetCmdb = new DevpOpsAssetCmdb();
		BeanUtils.copyProperties(devpOpsAssetCmdbAddDto, devpOpsAssetCmdb);

		devpOpsAssetCmdbService.add(devpOpsAssetCmdb);

		return  initViewProperty(devpOpsAssetCmdb);
	}

	/**
	 * 删除资产定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资产定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsAssetCmdb :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsAssetCmdbService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资产定义
	 * @param devpOpsAssetCmdbEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资产定义(修改全部字段,未传入置空)", httpMethod = "PUT")
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
	 * 根据ID查询资产定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资产定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsAssetCmdbVO get(@PathVariable Long id) {

		DevpOpsAssetCmdb devpOpsAssetCmdb = devpOpsAssetCmdbService.find(id);

		DevpOpsAssetCmdbVO vo = initViewProperty(devpOpsAssetCmdb);
		return vo;
	}

	/**
	 * 查询资产定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资产定义列表", httpMethod = "POST")
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
     * 导出资产定义列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出资产定义列表", httpMethod = "POST")
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

            headMap.put("etype" ,"元素类型");
            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("alias" ,"别名");
            headMap.put("description" ,"描述");
            headMap.put("typeCode" ,"类型代码");
            headMap.put("typeName" ,"类型名称");
            headMap.put("stereotype" ,"构造型");
            headMap.put("scope" ,"范围");
            headMap.put("hardwareModel" ,"硬件型号");
            headMap.put("softwareModel" ,"软件型号");
            headMap.put("version" ,"版本");
            headMap.put("status" ,"状态");
            headMap.put("createDate" ,"创建时间");
            headMap.put("expireDate" ,"到期时间");
            headMap.put("assetProject" ,"所属项目");
            headMap.put("assetArea" ,"所属区域");
            headMap.put("assetLocation" ,"资产位置");
            headMap.put("intAccessAddr" ,"内部访问地址");
            headMap.put("extAccessAddr" ,"外部访问地址");
            headMap.put("acquisitionMode" ,"获取方式");
            headMap.put("acquisitionDesc" ,"获取方式说明");
            headMap.put("assetDept" ,"归属部门");
            headMap.put("assetManager" ,"资产负责人");
            headMap.put("useDept" ,"使用部门");
            headMap.put("useManager" ,"使用负责人");
            headMap.put("opsDept" ,"维护部门");
            headMap.put("opsManager" ,"维护负责人");
            headMap.put("bizLine" ,"业务线");
            headMap.put("bizManager" ,"业务代表");
            headMap.put("goliveDate" ,"启用时间");
            headMap.put("majorCust" ,"主要客户");
            headMap.put("custManager" ,"客户代表");
            headMap.put("custUsage" ,"使用情况");
            headMap.put("notes" ,"备注");
            headMap.put("prdTid" ,"关联产品租户编号");
            headMap.put("prdRid" ,"关联产品记录编号");
            headMap.put("parasCode" ,"参数定义标识");
            headMap.put("acquisitionProvider" ,"供应商");

        String title = new String("资产定义");
        String fileName = new String(("资产定义_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpOpsAssetCmdbVO initViewProperty(DevpOpsAssetCmdb devpOpsAssetCmdb){

	    DevpOpsAssetCmdbVO vo = new DevpOpsAssetCmdbVO();
        BeanUtils.copyProperties(devpOpsAssetCmdb, vo);


	    //初始化其他对象
        return vo;


	}


}
