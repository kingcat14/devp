package net.aicoder.speedcloud.asset.business.asset.config.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetCategory;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryCondition;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryEditDto;
import net.aicoder.speedcloud.asset.business.asset.config.service.AssetCategoryService;
import net.aicoder.speedcloud.asset.business.asset.config.valid.AssetCategoryValidator;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetCategoryVO;


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
 * 管理资产大类
 * @author icode
 */
@Api(description = "资产大类", tags = "AssetCategory")
@RestController
@RequestMapping(value = "/asset/asset/config/assetcategory")
public class AssetCategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetCategoryController.class);


	@Autowired
	private AssetCategoryService assetCategoryService;



	@Autowired
	private AssetCategoryValidator assetCategoryValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(assetCategoryValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增资产大类
	 * @param assetCategoryAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资产大类", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AssetCategoryVO add(@RequestBody @Valid AssetCategoryAddDto assetCategoryAddDto){
		AssetCategory assetCategory = new AssetCategory();
		BeanUtils.copyProperties(assetCategoryAddDto, assetCategory);

		assetCategoryService.add(assetCategory);

		return  initViewProperty(assetCategory);
	}

	/**
	 * 删除资产大类,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资产大类", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete assetCategory :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			assetCategoryService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资产大类
	 * @param assetCategoryEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资产大类(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	AssetCategoryVO update(@RequestBody @Valid AssetCategoryEditDto assetCategoryEditDto, @PathVariable Long id){
		AssetCategory assetCategory = new AssetCategory();
		BeanUtils.copyProperties(assetCategoryEditDto, assetCategory);
		assetCategory.setId(id);
		assetCategoryService.merge(assetCategory);

		AssetCategoryVO vo = initViewProperty(assetCategory);
		return  vo;
	}

	/**
	 * 根据ID查询资产大类
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资产大类", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  AssetCategoryVO get(@PathVariable Long id) {

		AssetCategory assetCategory = assetCategoryService.find(id);

		AssetCategoryVO vo = initViewProperty(assetCategory);
		return vo;
	}

	/**
	 * 查询资产大类列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资产大类列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<AssetCategoryVO> list(@RequestBody PageSearchRequest<AssetCategoryCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<AssetCategory> page = assetCategoryService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AssetCategoryVO> voList = new ArrayList<>();
		for(AssetCategory assetCategory : page.getContent()){
			voList.add(initViewProperty(assetCategory));
		}

		PageContent<AssetCategoryVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出资产大类列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出资产大类列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(AssetCategoryCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AssetCategoryCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AssetCategoryVO> content = this.list(pageSearchRequest);

        List<AssetCategoryVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AssetCategoryVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户id");
            headMap.put("num" ,"编号");
            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("viewIndex" ,"展现顺序");
            headMap.put("description" ,"说明");

        String title = new String("资产大类");
        String fileName = new String(("资产大类_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AssetCategoryVO initViewProperty(AssetCategory assetCategory){

	    AssetCategoryVO vo = new AssetCategoryVO();
        BeanUtils.copyProperties(assetCategory, vo);


	    //初始化其他对象
        return vo;

	}


}

