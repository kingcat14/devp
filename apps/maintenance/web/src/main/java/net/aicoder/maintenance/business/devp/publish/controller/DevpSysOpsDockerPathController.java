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
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsDockerPathVO;
import net.aicoder.maintenance.business.devp.publish.service.DevpSysOpsDockerPathRibbonService;
import net.aicoder.maintenance.business.devp.publish.valid.DevpSysOpsDockerPathValidator;

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
 * 管理存储路径定义
 * @author icode
 */
@Api(description = "存储路径定义", tags = "DevpSysOpsDockerPath")
@RestController
@RequestMapping(value = "/publish/devpSysOpsDockerPath")
public class DevpSysOpsDockerPathController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerPathController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private DevpSysOpsDockerPathRibbonService devpSysOpsDockerPathRibbonService;

	@Autowired
	DevpSysOpsDockerPathValidator devpSysOpsDockerPathValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsDockerPathValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增存储路径定义
	 * @param devpSysOpsDockerPathAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增存储路径定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsDockerPathVO add(@RequestBody DevpSysOpsDockerPathAddDto devpSysOpsDockerPathAddDto){
		devpSysOpsDockerPathAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  devpSysOpsDockerPathRibbonService.add(devpSysOpsDockerPathAddDto);
	}

	/**
	 * 删除存储路径定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除存储路径定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsDockerPath :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsDockerPathRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新存储路径定义
	 * @param devpSysOpsDockerPathEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产存储路径定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysOpsDockerPathVO update(@RequestBody DevpSysOpsDockerPathEditDto devpSysOpsDockerPathEditDto, @ApiParam(value = "要查询的存储路径定义id") @PathVariable Long id){

		DevpSysOpsDockerPathVO vo = devpSysOpsDockerPathRibbonService.merge(id, devpSysOpsDockerPathEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询存储路径定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询存储路径定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysOpsDockerPathVO get(@ApiParam(value = "要查询的存储路径定义id") @PathVariable Long id) {

		DevpSysOpsDockerPathVO vo = devpSysOpsDockerPathRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询存储路径定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询存储路径定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsDockerPathVO> list(@RequestBody PageSearchRequest<DevpSysOpsDockerPathCondition> pageSearchRequest){

		DevpSysOpsDockerPathCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new DevpSysOpsDockerPathCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<DevpSysOpsDockerPathVO> pageContent = devpSysOpsDockerPathRibbonService.list(pageSearchRequest);
		for(DevpSysOpsDockerPathVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出存储路径定义列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出存储路径定义列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsDockerPathCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DevpSysOpsDockerPathCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsDockerPathVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsDockerPathVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsDockerPathVO vo : voList){
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
            headMap.put("dockerPath" ,"容器挂载路径");
            headMap.put("hostPath" ,"属主机路径");
            headMap.put("notes" ,"备注");
            headMap.put("prdRid" ,"产品编号");
            headMap.put("schemeRid" ,"部署方案编号");
            headMap.put("cmpRid" ,"组件编号");
            headMap.put("taskRid" ,"任务编号");
            headMap.put("dockerRid" ,"容器编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("存储路径定义");
        String fileName = new String(("存储路径定义_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DevpSysOpsDockerPathVO initViewProperty( DevpSysOpsDockerPathVO vo){

	   


	   
        return vo;

	}


}
