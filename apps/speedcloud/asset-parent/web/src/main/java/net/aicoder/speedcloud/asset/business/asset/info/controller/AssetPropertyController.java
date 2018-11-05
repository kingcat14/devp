package net.aicoder.speedcloud.asset.business.asset.info.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetProperty;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyEditDto;
import net.aicoder.speedcloud.asset.business.asset.info.service.AssetPropertyService;
import net.aicoder.speedcloud.asset.business.asset.info.valid.AssetPropertyValidator;
import net.aicoder.speedcloud.asset.business.asset.info.vo.AssetPropertyVO;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetCmdb;
import net.aicoder.speedcloud.asset.business.asset.info.service.AssetCmdbService;
import net.aicoder.speedcloud.asset.business.asset.info.vo.AssetCmdbVO;


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
 * 管理资产属性
 * @author icode
 */
@Api(description = "资产属性", tags = "AssetProperty")
@RestController
@RequestMapping(value = "/asset/asset/info/assetproperty")
public class AssetPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetPropertyController.class);


	@Autowired
	private AssetPropertyService assetPropertyService;

	@Autowired
	private AssetCmdbService assetCmdbService;


	@Autowired
	private AssetPropertyValidator assetPropertyValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(assetPropertyValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增资产属性
	 * @param assetPropertyAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资产属性", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AssetPropertyVO add(@RequestBody @Valid AssetPropertyAddDto assetPropertyAddDto){
		AssetProperty assetProperty = new AssetProperty();
		BeanUtils.copyProperties(assetPropertyAddDto, assetProperty);

		assetPropertyService.add(assetProperty);

		return  initViewProperty(assetProperty);
	}

	/**
	 * 删除资产属性,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资产属性", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete assetProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			assetPropertyService.delete(id);
		}

	}

	/**
	 * 更新资产属性
	 * @param assetPropertyEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资产属性(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	AssetPropertyVO update(@RequestBody @Valid AssetPropertyEditDto assetPropertyEditDto, @PathVariable String id){
		AssetProperty assetProperty = new AssetProperty();
		BeanUtils.copyProperties(assetPropertyEditDto, assetProperty);
		assetProperty.setId(id);
		assetPropertyService.merge(assetProperty);

		AssetPropertyVO vo = initViewProperty(assetProperty);
		return  vo;
	}

	/**
	 * 根据ID查询资产属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资产属性", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  AssetPropertyVO get(@PathVariable String id) {

		AssetProperty assetProperty = assetPropertyService.find(id);

		AssetPropertyVO vo = initViewProperty(assetProperty);
		return vo;
	}

	/**
	 * 查询资产属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资产属性列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<AssetPropertyVO> list(@RequestBody PageSearchRequest<AssetPropertyCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<AssetProperty> page = assetPropertyService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AssetPropertyVO> voList = new ArrayList<>();
		for(AssetProperty assetProperty : page.getContent()){
			voList.add(initViewProperty(assetProperty));
		}

		PageContent<AssetPropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出资产属性列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出资产属性列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(AssetPropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AssetPropertyCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AssetPropertyVO> content = this.list(pageSearchRequest);

        List<AssetPropertyVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AssetPropertyVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"属性名称");
            headMap.put("value" ,"属性值");
            headMap.put("asset" ,"所属资产");

        String title = new String("资产属性");
        String fileName = new String(("资产属性_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AssetPropertyVO initViewProperty(AssetProperty assetProperty){

	    AssetPropertyVO vo = new AssetPropertyVO();
        BeanUtils.copyProperties(assetProperty, vo);


	    //初始化其他对象
	    initAssetPropertyGroup(vo, assetProperty);
        return vo;

	}


	private void initAssetPropertyGroup(AssetPropertyVO assetPropertyVO, AssetProperty assetProperty){
	
		AssetCmdb asset = assetCmdbService.find(assetProperty.getAsset());
		if(asset == null){
			return;
		}
		AssetCmdbVO assetVO = new AssetCmdbVO();
		BeanUtils.copyProperties(asset, assetVO);

		assetPropertyVO.setAssetVO(assetVO);

	}


}

