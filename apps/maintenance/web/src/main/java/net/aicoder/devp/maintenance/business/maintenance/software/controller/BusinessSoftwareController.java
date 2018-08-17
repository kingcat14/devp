package net.aicoder.devp.maintenance.business.maintenance.software.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.platform.business.application.security.SaaSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareCondition;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareAddDto;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareEditDto;
import net.aicoder.devp.maintenance.business.software.vo.BusinessSoftwareVO;
import net.aicoder.devp.maintenance.business.maintenance.software.service.BusinessSoftwareRibbonService;
import net.aicoder.devp.maintenance.business.maintenance.software.valid.BusinessSoftwareValidator;
import com.yunkang.saas.platform.business.common.domain.SimpleConfig;
import com.yunkang.saas.platform.business.common.service.SimpleConfigService;
import com.yunkang.saas.platform.business.common.vo.SimpleConfigVO;

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
 * 管理应用软件
 * @author icode
 */
@Api(description = "应用软件", tags = "BusinessSoftware")
@RestController
@RequestMapping(value = "/software/businessSoftware")
public class BusinessSoftwareController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessSoftwareController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private BusinessSoftwareRibbonService businessSoftwareRibbonService;

	@Autowired
	BusinessSoftwareValidator businessSoftwareValidator;

    @Autowired
    private SimpleConfigService simpleConfigService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(businessSoftwareValidator);
	}

	/**
	 * 新增应用软件
	 * @param businessSoftwareAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用软件", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public BusinessSoftwareVO add(@RequestBody BusinessSoftwareAddDto businessSoftwareAddDto){
		businessSoftwareAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  businessSoftwareRibbonService.add(businessSoftwareAddDto);
	}

	/**
	 * 删除应用软件,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用软件", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete businessSoftware :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			businessSoftwareRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新应用软件
	 * @param businessSoftwareEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用软件(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public BusinessSoftwareVO update(@RequestBody BusinessSoftwareEditDto businessSoftwareEditDto, @ApiParam(value = "要查询的应用软件id") @PathVariable Long id){

		BusinessSoftwareVO vo = businessSoftwareRibbonService.merge(id, businessSoftwareEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询应用软件
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用软件", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public BusinessSoftwareVO get(@ApiParam(value = "要查询的应用软件id") @PathVariable Long id) {

		BusinessSoftwareVO vo = businessSoftwareRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询应用软件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用软件列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<BusinessSoftwareVO> list(@RequestBody PageSearchRequest<BusinessSoftwareCondition> pageSearchRequest){

		BusinessSoftwareCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new BusinessSoftwareCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<BusinessSoftwareVO> pageContent = businessSoftwareRibbonService.list(pageSearchRequest);
		for(BusinessSoftwareVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出应用软件列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出应用软件列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(BusinessSoftwareCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<BusinessSoftwareCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<BusinessSoftwareVO> content = this.list(pageSearchRequest);

        List<BusinessSoftwareVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(BusinessSoftwareVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("alias" ,"别名");
            headMap.put("description" ,"描述");
            headMap.put("typeCode" ,"类型代码");
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
            headMap.put("acquisitionProvider" ,"供应商");

        String title = new String("应用软件_".getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title + DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date()), headMap, jsonArray, response);


    }


	private BusinessSoftwareVO initViewProperty( BusinessSoftwareVO vo){

	   

		SimpleConfig typeCodeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("ASSET-CMDB-TYPECODE", vo.getTypeCode());

		if(typeCodeSimpleConfig!=null) {

		    SimpleConfigVO typeCodeSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(typeCodeSimpleConfig, typeCodeSimpleConfigVO);
		    vo.setTypeCodeVO(typeCodeSimpleConfigVO);
		}
		SimpleConfig statusSimpleConfig = simpleConfigService.findByConfigTypeAndCode("MACHINE-STATUS", vo.getStatus());

		if(statusSimpleConfig!=null) {

		    SimpleConfigVO statusSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(statusSimpleConfig, statusSimpleConfigVO);
		    vo.setStatusVO(statusSimpleConfigVO);
		}

	   
        return vo;

	}


}
