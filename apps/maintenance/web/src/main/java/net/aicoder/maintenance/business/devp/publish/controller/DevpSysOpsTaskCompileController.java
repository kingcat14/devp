package net.aicoder.maintenance.business.devp.publish.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.platform.business.application.security.SaaSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskCompileVO;
import net.aicoder.maintenance.business.devp.publish.service.DevpSysOpsTaskCompileRibbonService;
import net.aicoder.maintenance.business.devp.publish.valid.DevpSysOpsTaskCompileValidator;

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
 * 管理编译设置
 * @author icode
 */
@Api(description = "编译设置", tags = "DevpSysOpsTaskCompile")
@RestController
@RequestMapping(value = "/publish/devpSysOpsTaskCompile")
public class DevpSysOpsTaskCompileController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskCompileController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private DevpSysOpsTaskCompileRibbonService devpSysOpsTaskCompileRibbonService;

	@Autowired
	DevpSysOpsTaskCompileValidator devpSysOpsTaskCompileValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsTaskCompileValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增编译设置
	 * @param devpSysOpsTaskCompileAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增编译设置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsTaskCompileVO add(@RequestBody DevpSysOpsTaskCompileAddDto devpSysOpsTaskCompileAddDto){
		devpSysOpsTaskCompileAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  devpSysOpsTaskCompileRibbonService.add(devpSysOpsTaskCompileAddDto);
	}

	/**
	 * 删除编译设置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除编译设置", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsTaskCompile :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsTaskCompileRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新编译设置
	 * @param devpSysOpsTaskCompileEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产编译设置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysOpsTaskCompileVO update(@RequestBody DevpSysOpsTaskCompileEditDto devpSysOpsTaskCompileEditDto, @ApiParam(value = "要查询的编译设置id") @PathVariable Long id){

		DevpSysOpsTaskCompileVO vo = devpSysOpsTaskCompileRibbonService.merge(id, devpSysOpsTaskCompileEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询编译设置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询编译设置", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysOpsTaskCompileVO get(@ApiParam(value = "要查询的编译设置id") @PathVariable Long id) {

		DevpSysOpsTaskCompileVO vo = devpSysOpsTaskCompileRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询编译设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询编译设置列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsTaskCompileVO> list(@RequestBody PageSearchRequest<DevpSysOpsTaskCompileCondition> pageSearchRequest){

		DevpSysOpsTaskCompileCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new DevpSysOpsTaskCompileCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<DevpSysOpsTaskCompileVO> pageContent = devpSysOpsTaskCompileRibbonService.list(pageSearchRequest);
		for(DevpSysOpsTaskCompileVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出编译设置列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出编译设置列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsTaskCompileCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DevpSysOpsTaskCompileCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsTaskCompileVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsTaskCompileVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsTaskCompileVO vo : voList){
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
            headMap.put("svnCodeUrl" ,"代码路径");
            headMap.put("buildTools" ,"构建工具");
            headMap.put("envVersion" ,"执行环境版本");
            headMap.put("toolVersion" ,"工具版本");
            headMap.put("preAction" ,"构建前操作");
            headMap.put("buildAction" ,"构建操作");
            headMap.put("postAction" ,"构建后操作");
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

        String title = new String("编译设置");
        String fileName = new String(("编译设置_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DevpSysOpsTaskCompileVO initViewProperty( DevpSysOpsTaskCompileVO vo){

	   


	   
        return vo;

	}


}
