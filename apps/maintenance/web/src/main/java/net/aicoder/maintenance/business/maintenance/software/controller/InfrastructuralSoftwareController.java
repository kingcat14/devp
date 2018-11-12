package net.aicoder.maintenance.business.maintenance.software.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.maintenance.business.maintenance.software.service.InfrastructuralSoftwareRibbonService;
import net.aicoder.maintenance.business.maintenance.software.valid.InfrastructuralSoftwareValidator;
import net.aicoder.maintenance.business.software.dto.InfrastructuralSoftwareAddDto;
import net.aicoder.maintenance.business.software.dto.InfrastructuralSoftwareCondition;
import net.aicoder.maintenance.business.software.dto.InfrastructuralSoftwareEditDto;
import net.aicoder.maintenance.business.software.vo.InfrastructuralSoftwareVO;



import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理基础软件
 * @author icode
 */
@Api(description = "基础软件", tags = "InfrastructuralSoftware")
@RestController
@RequestMapping(value = "/software/infrastructuralSoftware")
public class InfrastructuralSoftwareController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfrastructuralSoftwareController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private InfrastructuralSoftwareRibbonService infrastructuralSoftwareRibbonService;

	@Autowired
	InfrastructuralSoftwareValidator infrastructuralSoftwareValidator;

    @Autowired
    private SimpleConfigService simpleConfigService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(infrastructuralSoftwareValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增基础软件
	 * @param infrastructuralSoftwareAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增基础软件", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public InfrastructuralSoftwareVO add(@RequestBody InfrastructuralSoftwareAddDto infrastructuralSoftwareAddDto){
		infrastructuralSoftwareAddDto.setTid(saaSUtil.getAccount().getTid());
		return  infrastructuralSoftwareRibbonService.add(infrastructuralSoftwareAddDto);
	}

	/**
	 * 删除基础软件,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除基础软件", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete infrastructuralSoftware :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			infrastructuralSoftwareRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新基础软件
	 * @param infrastructuralSoftwareEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产基础软件(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public InfrastructuralSoftwareVO update(@RequestBody InfrastructuralSoftwareEditDto infrastructuralSoftwareEditDto, @ApiParam(value = "要查询的基础软件id") @PathVariable Long id){

		InfrastructuralSoftwareVO vo = infrastructuralSoftwareRibbonService.merge(id, infrastructuralSoftwareEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询基础软件
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询基础软件", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public InfrastructuralSoftwareVO get(@ApiParam(value = "要查询的基础软件id") @PathVariable Long id) {

		InfrastructuralSoftwareVO vo = infrastructuralSoftwareRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询基础软件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询基础软件列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<InfrastructuralSoftwareVO> list(@RequestBody PageSearchRequest<InfrastructuralSoftwareCondition> pageSearchRequest){

		InfrastructuralSoftwareCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new InfrastructuralSoftwareCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTid());
		PageContent<InfrastructuralSoftwareVO> pageContent = infrastructuralSoftwareRibbonService.list(pageSearchRequest);
		for(InfrastructuralSoftwareVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出基础软件列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出基础软件列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(InfrastructuralSoftwareCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<InfrastructuralSoftwareCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<InfrastructuralSoftwareVO> content = this.list(pageSearchRequest);

        List<InfrastructuralSoftwareVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(InfrastructuralSoftwareVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("alias" ,"别名");
            headMap.put("description" ,"描述");
            headMap.put("typeCode" ,"类型");
            headMap.put("hardwareModel" ,"硬件型号");
            headMap.put("softwareModel" ,"软件型号");
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

        String title = new String("基础软件");
        String fileName = new String(("基础软件_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private InfrastructuralSoftwareVO initViewProperty( InfrastructuralSoftwareVO vo){

	   

		SimpleConfig statusSimpleConfig = simpleConfigService.findByConfigTypeAndCode("OPS_ASSET_STATUS", vo.getStatus());

		if(statusSimpleConfig!=null) {

		    SimpleConfigVO statusSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(statusSimpleConfig, statusSimpleConfigVO);
		    vo.setStatusVO(statusSimpleConfigVO);
		}

	   
        return vo;

	}


}
