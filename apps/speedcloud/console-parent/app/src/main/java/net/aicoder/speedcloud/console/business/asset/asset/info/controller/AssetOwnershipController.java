package net.aicoder.speedcloud.console.business.asset.asset.info.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipEditDto;
import net.aicoder.speedcloud.asset.business.asset.info.vo.AssetOwnershipVO;
import net.aicoder.speedcloud.console.business.asset.asset.info.service.AssetOwnershipRibbonService;
import net.aicoder.speedcloud.console.business.asset.asset.info.valid.AssetOwnershipValidator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
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
	private SaaSUtil saaSUtil;
	
    @Autowired
	private AssetOwnershipRibbonService assetOwnershipRibbonService;

	@Autowired
	AssetOwnershipValidator assetOwnershipValidator;


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
  	@SaaSAnnotation()
	public AssetOwnershipVO add(@RequestBody AssetOwnershipAddDto assetOwnershipAddDto){
	
		return  assetOwnershipRibbonService.add(assetOwnershipAddDto);
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
			assetOwnershipRibbonService.delete(Long.parseLong(id));
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
	public AssetOwnershipVO update(@RequestBody AssetOwnershipEditDto assetOwnershipEditDto, @ApiParam(value = "要查询的IT资产归属id") @PathVariable Long id){

		AssetOwnershipVO vo = assetOwnershipRibbonService.merge(id, assetOwnershipEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询IT资产归属
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询IT资产归属", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public AssetOwnershipVO get(@ApiParam(value = "要查询的IT资产归属id") @PathVariable Long id) {

		AssetOwnershipVO vo = assetOwnershipRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询IT资产归属列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询IT资产归属列表", httpMethod = "POST")
	@PostMapping("/list")
  	@SaaSAnnotation(conditionClass = AssetOwnershipCondition.class)
	public PageContent<AssetOwnershipVO> list(@RequestBody PageSearchRequest<AssetOwnershipCondition> pageSearchRequest){

		PageContent<AssetOwnershipVO> pageContent = assetOwnershipRibbonService.list(pageSearchRequest);
		for(AssetOwnershipVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

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
    public void export(AssetOwnershipCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

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


	private AssetOwnershipVO initViewProperty( AssetOwnershipVO vo){

	   


	   
        return vo;

	}


}
