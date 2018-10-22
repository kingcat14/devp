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
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyEditDto;
import net.aicoder.speedcloud.asset.business.asset.info.vo.AssetPropertyVO;
import net.aicoder.speedcloud.console.business.asset.asset.info.service.AssetPropertyRibbonService;
import net.aicoder.speedcloud.console.business.asset.asset.info.valid.AssetPropertyValidator;

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
 * 管理资产属性
 * @author icode
 */
@Api(description = "资产属性", tags = "AssetProperty")
@RestController
@RequestMapping(value = "/asset/asset/info/assetproperty")
public class AssetPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetPropertyController.class);

    @Autowired
	private SaaSUtil saaSUtil;
	
    @Autowired
	private AssetPropertyRibbonService assetPropertyRibbonService;

	@Autowired
	AssetPropertyValidator assetPropertyValidator;


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
  	@SaaSAnnotation()
	public AssetPropertyVO add(@RequestBody AssetPropertyAddDto assetPropertyAddDto){
	
		return  assetPropertyRibbonService.add(assetPropertyAddDto);
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
			assetPropertyRibbonService.delete(Long.parseLong(id));
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
	public AssetPropertyVO update(@RequestBody AssetPropertyEditDto assetPropertyEditDto, @ApiParam(value = "要查询的资产属性id") @PathVariable Long id){

		AssetPropertyVO vo = assetPropertyRibbonService.merge(id, assetPropertyEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询资产属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资产属性", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public AssetPropertyVO get(@ApiParam(value = "要查询的资产属性id") @PathVariable Long id) {

		AssetPropertyVO vo = assetPropertyRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询资产属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资产属性列表", httpMethod = "POST")
	@PostMapping("/list")
  	@SaaSAnnotation(conditionClass = AssetPropertyCondition.class)
	public PageContent<AssetPropertyVO> list(@RequestBody PageSearchRequest<AssetPropertyCondition> pageSearchRequest){

		PageContent<AssetPropertyVO> pageContent = assetPropertyRibbonService.list(pageSearchRequest);
		for(AssetPropertyVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

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
    public void export(AssetPropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

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


	private AssetPropertyVO initViewProperty( AssetPropertyVO vo){

	   


	   
        return vo;

	}


}
