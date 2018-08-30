package net.aicoder.maintenance.business.devp.publish.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskBaselineVO;
import net.aicoder.maintenance.business.devp.publish.service.DevpSysOpsTaskBaselineRibbonService;
import net.aicoder.maintenance.business.devp.publish.valid.DevpSysOpsTaskBaselineValidator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理基线设置
 * @author icode
 */
@Api(description = "基线设置", tags = "DevpSysOpsTaskBaseline")
@RestController
@RequestMapping(value = "/publish/devpSysOpsTaskBaseline")
public class DevpSysOpsTaskBaselineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskBaselineController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private DevpSysOpsTaskBaselineRibbonService devpSysOpsTaskBaselineRibbonService;

	@Autowired
	DevpSysOpsTaskBaselineValidator devpSysOpsTaskBaselineValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsTaskBaselineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增基线设置
	 * @param devpSysOpsTaskBaselineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增基线设置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsTaskBaselineVO add(@RequestBody DevpSysOpsTaskBaselineAddDto devpSysOpsTaskBaselineAddDto){
		devpSysOpsTaskBaselineAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  devpSysOpsTaskBaselineRibbonService.add(devpSysOpsTaskBaselineAddDto);
	}

	/**
	 * 删除基线设置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除基线设置", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsTaskBaseline :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsTaskBaselineRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新基线设置
	 * @param devpSysOpsTaskBaselineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产基线设置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysOpsTaskBaselineVO update(@RequestBody DevpSysOpsTaskBaselineEditDto devpSysOpsTaskBaselineEditDto, @ApiParam(value = "要查询的基线设置id") @PathVariable Long id){

		DevpSysOpsTaskBaselineVO vo = devpSysOpsTaskBaselineRibbonService.merge(id, devpSysOpsTaskBaselineEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询基线设置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询基线设置", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysOpsTaskBaselineVO get(@ApiParam(value = "要查询的基线设置id") @PathVariable Long id) {

		DevpSysOpsTaskBaselineVO vo = devpSysOpsTaskBaselineRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询基线设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询基线设置列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsTaskBaselineVO> list(@RequestBody PageSearchRequest<DevpSysOpsTaskBaselineCondition> pageSearchRequest){

		DevpSysOpsTaskBaselineCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new DevpSysOpsTaskBaselineCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<DevpSysOpsTaskBaselineVO> pageContent = devpSysOpsTaskBaselineRibbonService.list(pageSearchRequest);
		for(DevpSysOpsTaskBaselineVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出基线设置列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出基线设置列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsTaskBaselineCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DevpSysOpsTaskBaselineCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsTaskBaselineVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsTaskBaselineVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsTaskBaselineVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"元素类型");
            headMap.put("name" ,"系统元素名称");
            headMap.put("code" ,"系统元素代码");
            headMap.put("alias" ,"系统元素别名");
            headMap.put("description" ,"系统元素描述");
            headMap.put("recordState" ,"记录状态");
            headMap.put("type" ,"类型");
            headMap.put("subType" ,"子类型");
            headMap.put("cmSameas" ,"采用相同代码仓库");
            headMap.put("cmType" ,"代码仓库类型");
            headMap.put("cmRepository" ,"仓库地址");
            headMap.put("cmTag" ,"分支标识");
            headMap.put("cmUser" ,"用户名");
            headMap.put("cmPassword" ,"密码");
            headMap.put("subDir" ,"子目录");
            headMap.put("baseline" ,"基线标识");
            headMap.put("svnFromUrl" ,"来源代码路径");
            headMap.put("svnToUrl" ,"目标基线路径");
            headMap.put("operType" ,"操作方式");
            headMap.put("status" ,"状态");
            headMap.put("notes" ,"备注");
            headMap.put("prdRid" ,"产品编号");
            headMap.put("schemeRid" ,"部署方案编号");
            headMap.put("cmpRid" ,"组件编号");
            headMap.put("taskRid" ,"任务编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("基线设置");
        String fileName = new String(("基线设置_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DevpSysOpsTaskBaselineVO initViewProperty( DevpSysOpsTaskBaselineVO vo){

	   


	   
        return vo;

	}


}
