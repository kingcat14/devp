package net.aicoder.speedcloud.asset.business.asset.info.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetCategory;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetType;
import net.aicoder.speedcloud.asset.business.asset.config.service.AssetCategoryService;
import net.aicoder.speedcloud.asset.business.asset.config.service.AssetTypeService;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetCategoryVO;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetTypeVO;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetCmdb;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbEditDto;
import net.aicoder.speedcloud.asset.business.asset.info.service.AssetCmdbService;
import net.aicoder.speedcloud.asset.business.asset.info.valid.AssetCmdbValidator;
import net.aicoder.speedcloud.asset.business.asset.info.vo.AssetCmdbVO;
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
 * 管理IT资产
 * @author icode
 */
@Api(description = "IT资产", tags = "AssetCmdb")
@RestController
@RequestMapping(value = "/asset/asset/info/assetcmdb")
public class AssetCmdbController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetCmdbController.class);


	@Autowired
	private AssetCmdbService assetCmdbService;

	@Autowired
	private AssetCategoryService assetCategoryService;
	@Autowired
	private AssetTypeService assetTypeService;


	@Autowired
	private AssetCmdbValidator assetCmdbValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(assetCmdbValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增IT资产
	 * @param assetCmdbAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增IT资产", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AssetCmdbVO add(@RequestBody @Valid AssetCmdbAddDto assetCmdbAddDto){
		AssetCmdb assetCmdb = new AssetCmdb();
		BeanUtils.copyProperties(assetCmdbAddDto, assetCmdb);

		assetCmdbService.add(assetCmdb);

		return  initViewProperty(assetCmdb);
	}

	/**
	 * 删除IT资产,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除IT资产", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete assetCmdb :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			assetCmdbService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新IT资产
	 * @param assetCmdbEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产IT资产(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	AssetCmdbVO update(@RequestBody @Valid AssetCmdbEditDto assetCmdbEditDto, @PathVariable Long id){
		AssetCmdb assetCmdb = new AssetCmdb();
		BeanUtils.copyProperties(assetCmdbEditDto, assetCmdb);
		assetCmdb.setId(id);
		assetCmdbService.merge(assetCmdb);

		AssetCmdbVO vo = initViewProperty(assetCmdb);
		return  vo;
	}

	/**
	 * 根据ID查询IT资产
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询IT资产", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  AssetCmdbVO get(@PathVariable Long id) {

		AssetCmdb assetCmdb = assetCmdbService.find(id);

		AssetCmdbVO vo = initViewProperty(assetCmdb);
		return vo;
	}

	/**
	 * 查询IT资产列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询IT资产列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<AssetCmdbVO> list(@RequestBody PageSearchRequest<AssetCmdbCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<AssetCmdb> page = assetCmdbService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AssetCmdbVO> voList = new ArrayList<>();
		for(AssetCmdb assetCmdb : page.getContent()){
			voList.add(initViewProperty(assetCmdb));
		}

		PageContent<AssetCmdbVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出IT资产列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出IT资产列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(AssetCmdbCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AssetCmdbCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AssetCmdbVO> content = this.list(pageSearchRequest);

        List<AssetCmdbVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AssetCmdbVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("name" ,"资产名称");
            headMap.put("code" ,"资产代码");
            headMap.put("alias" ,"资产别名");
            headMap.put("category" ,"资产大类");
            headMap.put("type" ,"资产分类");
            headMap.put("description" ,"描述");
            headMap.put("status" ,"状态");
            headMap.put("createDate" ,"创建时间");
            headMap.put("expireDate" ,"到期时间");
            headMap.put("assetArea" ,"所在区域");
            headMap.put("assetLocation" ,"所在地址");
            headMap.put("acquisitionMode" ,"获取模式");
            headMap.put("acquisitionDesc" ,"获取描述");
            headMap.put("goliveDate" ,"启用时间");
            headMap.put("notes" ,"notes");

        String title = new String("IT资产");
        String fileName = new String(("IT资产_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AssetCmdbVO initViewProperty(AssetCmdb assetCmdb){

	    AssetCmdbVO vo = new AssetCmdbVO();
        BeanUtils.copyProperties(assetCmdb, vo);


	    //初始化其他对象
	    initCategoryPropertyGroup(vo, assetCmdb);
	    initTypePropertyGroup(vo, assetCmdb);
        return vo;

	}


	private void initCategoryPropertyGroup(AssetCmdbVO assetCmdbVO, AssetCmdb assetCmdb){
	
		AssetCategory category = assetCategoryService.findByCode(assetCmdb.getCategoryCode());
		if(category == null){
			return;
		}
		AssetCategoryVO categoryVO = new AssetCategoryVO();
		BeanUtils.copyProperties(category, categoryVO);

		assetCmdbVO.setCategoryVO(categoryVO);

	}


	private void initTypePropertyGroup(AssetCmdbVO assetCmdbVO, AssetCmdb assetCmdb){


    	AssetType type = assetTypeService.findByCategoryCodeAndCode(assetCmdb.getCategoryCode(), assetCmdb.getTypeCode());
		if(type == null){
			return;
		}

		AssetTypeVO typeVO = new AssetTypeVO();
		BeanUtils.copyProperties(type, typeVO);

		assetCmdbVO.setTypeVO(typeVO);

	}


}

