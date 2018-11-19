package net.aicoder.speedcloud.console.business.asset.asset.info.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbEditDto;
import net.aicoder.speedcloud.asset.business.asset.info.vo.AssetCmdbVO;
import net.aicoder.speedcloud.console.business.asset.asset.info.service.AssetCmdbRibbonService;
import net.aicoder.speedcloud.console.business.asset.asset.info.valid.AssetCmdbValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
	private SaaSUtil saaSUtil;
	
    @Autowired
	private AssetCmdbRibbonService assetCmdbRibbonService;

	@Autowired
	AssetCmdbValidator assetCmdbValidator;


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
  	@SaaSAnnotation()
	public AssetCmdbVO add(@RequestBody AssetCmdbAddDto assetCmdbAddDto){
		return  assetCmdbRibbonService.add(assetCmdbAddDto);
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
			assetCmdbRibbonService.delete(Long.parseLong(id));
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
	public AssetCmdbVO update(@RequestBody AssetCmdbEditDto assetCmdbEditDto, @ApiParam(value = "要查询的IT资产id") @PathVariable Long id){

		AssetCmdbVO vo = assetCmdbRibbonService.merge(id, assetCmdbEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询IT资产
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询IT资产", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public AssetCmdbVO get(@ApiParam(value = "要查询的IT资产id") @PathVariable Long id) {

		AssetCmdbVO vo = assetCmdbRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询IT资产列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询IT资产列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = AssetCmdbCondition.class)
	public PageContent<AssetCmdbVO> list(@RequestBody PageSearchRequest<AssetCmdbCondition> pageSearchRequest){

		PageContent<AssetCmdbVO> pageContent = assetCmdbRibbonService.list(pageSearchRequest);
		for(AssetCmdbVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

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
    public void export(AssetCmdbCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

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
            headMap.put("barcode" ,"资产条码");
            headMap.put("name" ,"资产名称");
            headMap.put("code" ,"资产代码");
            headMap.put("alias" ,"资产别名");
            headMap.put("category" ,"资产大类");
            headMap.put("type" ,"资产分类");
            headMap.put("unit" ,"计量单位");
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


	private AssetCmdbVO initViewProperty( AssetCmdbVO vo){

	   


	   
        return vo;

	}


}
