package net.aicoder.maintenance.business.maintenance.hardware.controller;

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
import net.aicoder.maintenance.business.hardware.dto.MachineAddDto;
import net.aicoder.maintenance.business.hardware.dto.MachineCondition;
import net.aicoder.maintenance.business.hardware.dto.MachineEditDto;
import net.aicoder.maintenance.business.hardware.vo.MachineVO;
import net.aicoder.maintenance.business.maintenance.hardware.service.MachineRibbonService;
import net.aicoder.maintenance.business.maintenance.hardware.valid.MachineValidator;



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
 * 管理服务器
 * @author icode
 */
@Api(description = "服务器", tags = "Machine")
@RestController
@RequestMapping(value = "/hardware/machine")
public class MachineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MachineController.class);

	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private MachineRibbonService machineRibbonService;

	@Autowired
	MachineValidator machineValidator;

    @Autowired
    private SimpleConfigService simpleConfigService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(machineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增服务器
	 * @param machineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增服务器", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public MachineVO add(@RequestBody MachineAddDto machineAddDto){
		machineAddDto.setTid(saaSUtil.getAccount().getTid());
		return  machineRibbonService.add(machineAddDto);
	}

	/**
	 * 删除服务器,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除服务器", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete machine :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			machineRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新服务器
	 * @param machineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产服务器(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public MachineVO update(@RequestBody MachineEditDto machineEditDto, @ApiParam(value = "要查询的服务器id") @PathVariable Long id){

		MachineVO vo = machineRibbonService.merge(id, machineEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询服务器
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询服务器", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public MachineVO get(@ApiParam(value = "要查询的服务器id") @PathVariable Long id) {

		MachineVO vo = machineRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询服务器列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询服务器列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<MachineVO> list(@RequestBody PageSearchRequest<MachineCondition> pageSearchRequest){

		MachineCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new MachineCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTid());
		PageContent<MachineVO> pageContent = machineRibbonService.list(pageSearchRequest);
		for(MachineVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出服务器列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出服务器列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(MachineCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<MachineCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<MachineVO> content = this.list(pageSearchRequest);

        List<MachineVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(MachineVO vo : voList){
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
            headMap.put("parasCode" ,"参数定义标识");
            headMap.put("acquisitionProvider" ,"供应商");
            headMap.put("recordState" ,"记录状态");

        String title = new String("服务器");
        String fileName = new String(("服务器_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private MachineVO initViewProperty( MachineVO vo){

	   

		SimpleConfig statusSimpleConfig = simpleConfigService.findByConfigTypeAndCode("OPS_ASSET_STATUS", vo.getStatus());

		if(statusSimpleConfig!=null) {

		    SimpleConfigVO statusSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(statusSimpleConfig, statusSimpleConfigVO);
		    vo.setStatusVO(statusSimpleConfigVO);
		}

	   
        return vo;

	}


}
