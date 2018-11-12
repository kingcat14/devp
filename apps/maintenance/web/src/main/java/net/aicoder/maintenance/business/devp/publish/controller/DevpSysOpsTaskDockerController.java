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
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskDockerVO;
import net.aicoder.maintenance.business.devp.publish.service.DevpSysOpsTaskDockerRibbonService;
import net.aicoder.maintenance.business.devp.publish.valid.DevpSysOpsTaskDockerValidator;

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
 * 管理部署容器
 * @author icode
 */
@Api(description = "部署容器", tags = "DevpSysOpsTaskDocker")
@RestController
@RequestMapping(value = "/publish/devpSysOpsTaskDocker")
public class DevpSysOpsTaskDockerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskDockerController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private DevpSysOpsTaskDockerRibbonService devpSysOpsTaskDockerRibbonService;

	@Autowired
	DevpSysOpsTaskDockerValidator devpSysOpsTaskDockerValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsTaskDockerValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署容器
	 * @param devpSysOpsTaskDockerAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署容器", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsTaskDockerVO add(@RequestBody DevpSysOpsTaskDockerAddDto devpSysOpsTaskDockerAddDto){
		devpSysOpsTaskDockerAddDto.setTid(saaSUtil.getAccount().getTid());
		return  devpSysOpsTaskDockerRibbonService.add(devpSysOpsTaskDockerAddDto);
	}

	/**
	 * 删除部署容器,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署容器", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsTaskDocker :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsTaskDockerRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署容器
	 * @param devpSysOpsTaskDockerEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署容器(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysOpsTaskDockerVO update(@RequestBody DevpSysOpsTaskDockerEditDto devpSysOpsTaskDockerEditDto, @ApiParam(value = "要查询的部署容器id") @PathVariable Long id){

		DevpSysOpsTaskDockerVO vo = devpSysOpsTaskDockerRibbonService.merge(id, devpSysOpsTaskDockerEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询部署容器
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署容器", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysOpsTaskDockerVO get(@ApiParam(value = "要查询的部署容器id") @PathVariable Long id) {

		DevpSysOpsTaskDockerVO vo = devpSysOpsTaskDockerRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询部署容器列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署容器列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsTaskDockerVO> list(@RequestBody PageSearchRequest<DevpSysOpsTaskDockerCondition> pageSearchRequest){

		DevpSysOpsTaskDockerCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new DevpSysOpsTaskDockerCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTid());
		PageContent<DevpSysOpsTaskDockerVO> pageContent = devpSysOpsTaskDockerRibbonService.list(pageSearchRequest);
		for(DevpSysOpsTaskDockerVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署容器列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署容器列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsTaskDockerCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DevpSysOpsTaskDockerCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsTaskDockerVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsTaskDockerVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsTaskDockerVO vo : voList){
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
            headMap.put("group" ,"所在集群");
            headMap.put("namespace" ,"命名空间");
            headMap.put("publishGroup" ,"发布物分组");
            headMap.put("publishArtifact" ,"发布物");
            headMap.put("publishVersion" ,"版本标识");
            headMap.put("publishFile" ,"发布文件名");
            headMap.put("instancesNum" ,"实例个数");
            headMap.put("cpu" ,"CPU");
            headMap.put("memory" ,"内存(G)");
            headMap.put("serviceable" ,"发布为服务");
            headMap.put("lbStrategy" ,"负载策略");
            headMap.put("accessType" ,"访问类型");
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

        String title = new String("部署容器");
        String fileName = new String(("部署容器_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DevpSysOpsTaskDockerVO initViewProperty( DevpSysOpsTaskDockerVO vo){

	   


	   
        return vo;

	}


}
