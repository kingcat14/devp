package net.aicoder.speedcloud.asset.business.asset.config.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetType;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetTypeProperty;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyCondition;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyEditDto;
import net.aicoder.speedcloud.asset.business.asset.config.service.AssetTypePropertyService;
import net.aicoder.speedcloud.asset.business.asset.config.service.AssetTypeService;
import net.aicoder.speedcloud.asset.business.asset.config.valid.AssetTypePropertyValidator;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetTypePropertyVO;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetTypeVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理资产分类属性
 * @author icode
 */
@Api(description = "资产分类属性", tags = "AssetTypeProperty")
@RestController
@RequestMapping(value = "/asset/asset/config/assettypeproperty")
public class AssetTypePropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetTypePropertyController.class);


	@Autowired
	private AssetTypePropertyService assetTypePropertyService;

	@Autowired
	private AssetTypeService assetTypeService;


	@Autowired
	private AssetTypePropertyValidator assetTypePropertyValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(assetTypePropertyValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增资产分类属性
	 * @param assetTypePropertyAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资产分类属性", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AssetTypePropertyVO add(@RequestBody @Valid AssetTypePropertyAddDto assetTypePropertyAddDto){
		AssetTypeProperty assetTypeProperty = new AssetTypeProperty();
		BeanUtils.copyProperties(assetTypePropertyAddDto, assetTypeProperty);

		assetTypePropertyService.add(assetTypeProperty);

		return  initViewProperty(assetTypeProperty);
	}

	/**
	 * 删除资产分类属性,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资产分类属性", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete assetTypeProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			assetTypePropertyService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资产分类属性
	 * @param assetTypePropertyEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资产分类属性(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	AssetTypePropertyVO update(@RequestBody @Valid AssetTypePropertyEditDto assetTypePropertyEditDto, @PathVariable Long id){
		AssetTypeProperty assetTypeProperty = new AssetTypeProperty();
		BeanUtils.copyProperties(assetTypePropertyEditDto, assetTypeProperty);
		assetTypeProperty.setId(id);
		assetTypePropertyService.merge(assetTypeProperty);

		AssetTypePropertyVO vo = initViewProperty(assetTypeProperty);
		return  vo;
	}

	/**
	 * 根据ID查询资产分类属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资产分类属性", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  AssetTypePropertyVO get(@PathVariable Long id) {

		AssetTypeProperty assetTypeProperty = assetTypePropertyService.find(id);

		AssetTypePropertyVO vo = initViewProperty(assetTypeProperty);
		return vo;
	}

	/**
	 * 查询资产分类属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资产分类属性列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<AssetTypePropertyVO> list(@RequestBody PageSearchRequest<AssetTypePropertyCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<AssetTypeProperty> page = assetTypePropertyService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AssetTypePropertyVO> voList = new ArrayList<>();
		for(AssetTypeProperty assetTypeProperty : page.getContent()){
			voList.add(initViewProperty(assetTypeProperty));
		}

		PageContent<AssetTypePropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出资产分类属性列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出资产分类属性列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(AssetTypePropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AssetTypePropertyCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AssetTypePropertyVO> content = this.list(pageSearchRequest);

        List<AssetTypePropertyVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AssetTypePropertyVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("assetType" ,"资产分类");
            headMap.put("name" ,"属性名称");
            headMap.put("type" ,"属性类型");
            headMap.put("required" ,"必填");
            headMap.put("optionValues" ,"备选值");
            headMap.put("seq" ,"顺序");

        String title = new String("资产分类属性");
        String fileName = new String(("资产分类属性_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AssetTypePropertyVO initViewProperty(AssetTypeProperty assetTypeProperty){

	    AssetTypePropertyVO vo = new AssetTypePropertyVO();
        BeanUtils.copyProperties(assetTypeProperty, vo);


	    //初始化其他对象
	    initAssetTypePropertyGroup(vo, assetTypeProperty);
        return vo;

	}


	private void initAssetTypePropertyGroup(AssetTypePropertyVO assetTypePropertyVO, AssetTypeProperty assetTypeProperty){
	
		AssetType assetType = assetTypeService.find(assetTypeProperty.getAssetType());
		if(assetType == null){
			return;
		}
		AssetTypeVO assetTypeVO = new AssetTypeVO();
		BeanUtils.copyProperties(assetType, assetTypeVO);

		assetTypePropertyVO.setAssetTypeVO(assetTypeVO);

	}


}

