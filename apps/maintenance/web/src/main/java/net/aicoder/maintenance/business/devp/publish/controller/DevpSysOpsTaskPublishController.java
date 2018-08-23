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
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskPublishVO;
import net.aicoder.maintenance.business.devp.publish.service.DevpSysOpsTaskPublishRibbonService;
import net.aicoder.maintenance.business.devp.publish.valid.DevpSysOpsTaskPublishValidator;

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
 * 管理发布设置
 * @author icode
 */
@Api(description = "发布设置", tags = "DevpSysOpsTaskPublish")
@RestController
@RequestMapping(value = "/publish/devpSysOpsTaskPublish")
public class DevpSysOpsTaskPublishController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskPublishController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private DevpSysOpsTaskPublishRibbonService devpSysOpsTaskPublishRibbonService;

	@Autowired
	DevpSysOpsTaskPublishValidator devpSysOpsTaskPublishValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsTaskPublishValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增发布设置
	 * @param devpSysOpsTaskPublishAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增发布设置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsTaskPublishVO add(@RequestBody DevpSysOpsTaskPublishAddDto devpSysOpsTaskPublishAddDto){
		devpSysOpsTaskPublishAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  devpSysOpsTaskPublishRibbonService.add(devpSysOpsTaskPublishAddDto);
	}

	/**
	 * 删除发布设置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除发布设置", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsTaskPublish :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsTaskPublishRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新发布设置
	 * @param devpSysOpsTaskPublishEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产发布设置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysOpsTaskPublishVO update(@RequestBody DevpSysOpsTaskPublishEditDto devpSysOpsTaskPublishEditDto, @ApiParam(value = "要查询的发布设置id") @PathVariable Long id){

		DevpSysOpsTaskPublishVO vo = devpSysOpsTaskPublishRibbonService.merge(id, devpSysOpsTaskPublishEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询发布设置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询发布设置", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysOpsTaskPublishVO get(@ApiParam(value = "要查询的发布设置id") @PathVariable Long id) {

		DevpSysOpsTaskPublishVO vo = devpSysOpsTaskPublishRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询发布设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询发布设置列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsTaskPublishVO> list(@RequestBody PageSearchRequest<DevpSysOpsTaskPublishCondition> pageSearchRequest){

		DevpSysOpsTaskPublishCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new DevpSysOpsTaskPublishCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<DevpSysOpsTaskPublishVO> pageContent = devpSysOpsTaskPublishRibbonService.list(pageSearchRequest);
		for(DevpSysOpsTaskPublishVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出发布设置列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出发布设置列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsTaskPublishCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DevpSysOpsTaskPublishCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsTaskPublishVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsTaskPublishVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsTaskPublishVO vo : voList){
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
            headMap.put("template" ,"参考模板");
            headMap.put("group" ,"发布物分组");
            headMap.put("artifact" ,"发布物");
            headMap.put("version" ,"版本标识");
            headMap.put("publishFile" ,"发布文件名");
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

        String title = new String("发布设置");
        String fileName = new String(("发布设置_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DevpSysOpsTaskPublishVO initViewProperty( DevpSysOpsTaskPublishVO vo){

	   


	   
        return vo;

	}


}
