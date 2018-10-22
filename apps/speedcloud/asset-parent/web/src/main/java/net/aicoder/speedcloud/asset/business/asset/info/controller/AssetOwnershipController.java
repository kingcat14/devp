package net.aicoder.speedcloud.asset.business.asset.info.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetOwnership;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipEditDto;
import net.aicoder.speedcloud.asset.business.asset.info.service.AssetOwnershipService;
import net.aicoder.speedcloud.asset.business.asset.info.valid.AssetOwnershipValidator;
import net.aicoder.speedcloud.asset.business.asset.info.vo.AssetOwnershipVO;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetCategory;
import net.aicoder.speedcloud.asset.business.asset.config.service.AssetCategoryService;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetCategoryVO;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetType;
import net.aicoder.speedcloud.asset.business.asset.config.service.AssetTypeService;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetTypeVO;


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
 * 管理IT资产归属
 * @author icode
 */
@Api(description = "IT资产归属", tags = "AssetOwnership")
@RestController
@RequestMapping(value = "/asset/asset/info/assetownership")
public class AssetOwnershipController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetOwnershipController.class);


	@Autowired
	private AssetOwnershipService assetOwnershipService;

	@Autowired
	private AssetCategoryService assetCategoryService;
	@Autowired
	private AssetTypeService assetTypeService;


	@Autowired
	private AssetOwnershipValidator assetOwnershipValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(assetOwnershipValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增IT资产归属
	 * @param assetOwnershipAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增IT资产归属", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AssetOwnershipVO add(@RequestBody @Valid AssetOwnershipAddDto assetOwnershipAddDto){
		AssetOwnership assetOwnership = new AssetOwnership();
		BeanUtils.copyProperties(assetOwnershipAddDto, assetOwnership);

		assetOwnershipService.add(assetOwnership);

		return  initViewProperty(assetOwnership);
	}

	/**
	 * 删除IT资产归属,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除IT资产归属", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete assetOwnership :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			assetOwnershipService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新IT资产归属
	 * @param assetOwnershipEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产IT资产归属(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	AssetOwnershipVO update(@RequestBody @Valid AssetOwnershipEditDto assetOwnershipEditDto, @PathVariable Long id){
		AssetOwnership assetOwnership = new AssetOwnership();
		BeanUtils.copyProperties(assetOwnershipEditDto, assetOwnership);
		assetOwnership.setId(id);
		assetOwnershipService.merge(assetOwnership);

		AssetOwnershipVO vo = initViewProperty(assetOwnership);
		return  vo;
	}

	/**
	 * 根据ID查询IT资产归属
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询IT资产归属", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  AssetOwnershipVO get(@PathVariable Long id) {

		AssetOwnership assetOwnership = assetOwnershipService.find(id);

		AssetOwnershipVO vo = initViewProperty(assetOwnership);
		return vo;
	}

	/**
	 * 查询IT资产归属列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询IT资产归属列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<AssetOwnershipVO> list(@RequestBody PageSearchRequest<AssetOwnershipCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<AssetOwnership> page = assetOwnershipService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AssetOwnershipVO> voList = new ArrayList<>();
		for(AssetOwnership assetOwnership : page.getContent()){
			voList.add(initViewProperty(assetOwnership));
		}

		PageContent<AssetOwnershipVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出IT资产归属列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出IT资产归属列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(AssetOwnershipCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AssetOwnershipCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AssetOwnershipVO> content = this.list(pageSearchRequest);

        List<AssetOwnershipVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AssetOwnershipVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("name" ,"资产名称");
            headMap.put("code" ,"资产代码");
            headMap.put("category" ,"资产大类");
            headMap.put("type" ,"资产分类");
            headMap.put("typeCode" ,"资产分类代码");
            headMap.put("assetDept" ,"资产部门");
            headMap.put("assetManager" ,"资产负责人");
            headMap.put("useDept" ,"使用部门");
            headMap.put("useManager" ,"使用负责人");
            headMap.put("opsDept" ,"操作部门");
            headMap.put("opsManager" ,"操作负责人");
            headMap.put("bizLine" ,"业务线");
            headMap.put("bizManager" ,"业务负责人");
            headMap.put("goliveDate" ,"启用时间");
            headMap.put("majorCust" ,"主要客户");
            headMap.put("custManager" ,"客户负责人");
            headMap.put("custUsage" ,"客户使用情况");
            headMap.put("acquisitionProvider" ,"供应商");

        String title = new String("IT资产归属");
        String fileName = new String(("IT资产归属_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AssetOwnershipVO initViewProperty(AssetOwnership assetOwnership){

	    AssetOwnershipVO vo = new AssetOwnershipVO();
        BeanUtils.copyProperties(assetOwnership, vo);


	    //初始化其他对象
	    initCategoryPropertyGroup(vo, assetOwnership);
	    initTypePropertyGroup(vo, assetOwnership);
        return vo;

	}


	private void initCategoryPropertyGroup(AssetOwnershipVO assetOwnershipVO, AssetOwnership assetOwnership){
	
		AssetCategory category = assetCategoryService.find(assetOwnership.getCategory());
		if(category == null){
			return;
		}
		AssetCategoryVO categoryVO = new AssetCategoryVO();
		BeanUtils.copyProperties(category, categoryVO);

		assetOwnershipVO.setCategoryVO(categoryVO);

	}


	private void initTypePropertyGroup(AssetOwnershipVO assetOwnershipVO, AssetOwnership assetOwnership){
	
		AssetType type = assetTypeService.find(assetOwnership.getType());
		if(type == null){
			return;
		}
		AssetTypeVO typeVO = new AssetTypeVO();
		BeanUtils.copyProperties(type, typeVO);

		assetOwnershipVO.setTypeVO(typeVO);

	}


}

