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
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsDockerParamVO;
import net.aicoder.maintenance.business.devp.publish.service.DevpSysOpsDockerParamRibbonService;
import net.aicoder.maintenance.business.devp.publish.valid.DevpSysOpsDockerParamValidator;

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
 * 管理部署容器参数定义
 * @author icode
 */
@Api(description = "部署容器参数定义", tags = "DevpSysOpsDockerParam")
@RestController
@RequestMapping(value = "/publish/devpSysOpsDockerParam")
public class DevpSysOpsDockerParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerParamController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private DevpSysOpsDockerParamRibbonService devpSysOpsDockerParamRibbonService;

	@Autowired
	DevpSysOpsDockerParamValidator devpSysOpsDockerParamValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsDockerParamValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署容器参数定义
	 * @param devpSysOpsDockerParamAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署容器参数定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsDockerParamVO add(@RequestBody DevpSysOpsDockerParamAddDto devpSysOpsDockerParamAddDto){
		devpSysOpsDockerParamAddDto.setTid(saaSUtil.getAccount().getTid());
		return  devpSysOpsDockerParamRibbonService.add(devpSysOpsDockerParamAddDto);
	}

	/**
	 * 删除部署容器参数定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署容器参数定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsDockerParam :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsDockerParamRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署容器参数定义
	 * @param devpSysOpsDockerParamEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署容器参数定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysOpsDockerParamVO update(@RequestBody DevpSysOpsDockerParamEditDto devpSysOpsDockerParamEditDto, @ApiParam(value = "要查询的部署容器参数定义id") @PathVariable Long id){

		DevpSysOpsDockerParamVO vo = devpSysOpsDockerParamRibbonService.merge(id, devpSysOpsDockerParamEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询部署容器参数定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署容器参数定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysOpsDockerParamVO get(@ApiParam(value = "要查询的部署容器参数定义id") @PathVariable Long id) {

		DevpSysOpsDockerParamVO vo = devpSysOpsDockerParamRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询部署容器参数定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署容器参数定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsDockerParamVO> list(@RequestBody PageSearchRequest<DevpSysOpsDockerParamCondition> pageSearchRequest){

		DevpSysOpsDockerParamCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new DevpSysOpsDockerParamCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTid());
		PageContent<DevpSysOpsDockerParamVO> pageContent = devpSysOpsDockerParamRibbonService.list(pageSearchRequest);
		for(DevpSysOpsDockerParamVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署容器参数定义列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署容器参数定义列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsDockerParamCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DevpSysOpsDockerParamCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsDockerParamVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsDockerParamVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsDockerParamVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"元素类型");
            headMap.put("name" ,"系统元素名称");
            headMap.put("code" ,"参数代码");
            headMap.put("alias" ,"系统元素别名");
            headMap.put("description" ,"系统元素描述");
            headMap.put("recordState" ,"记录状态");
            headMap.put("type" ,"类型");
            headMap.put("subType" ,"子类型");
            headMap.put("value" ,"参数值");
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

        String title = new String("部署容器参数定义");
        String fileName = new String(("部署容器参数定义_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DevpSysOpsDockerParamVO initViewProperty( DevpSysOpsDockerParamVO vo){

	   


	   
        return vo;

	}


}
