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
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsPipePlanVO;
import net.aicoder.maintenance.business.devp.publish.service.DevpSysOpsPipePlanRibbonService;
import net.aicoder.maintenance.business.devp.publish.valid.DevpSysOpsPipePlanValidator;

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
 * 管理产品运维流水线执行计划
 * @author icode
 */
@Api(description = "产品运维流水线执行计划", tags = "DevpSysOpsPipePlan")
@RestController
@RequestMapping(value = "/publish/devpSysOpsPipePlan")
public class DevpSysOpsPipePlanController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipePlanController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private DevpSysOpsPipePlanRibbonService devpSysOpsPipePlanRibbonService;

	@Autowired
	DevpSysOpsPipePlanValidator devpSysOpsPipePlanValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsPipePlanValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增产品运维流水线执行计划
	 * @param devpSysOpsPipePlanAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品运维流水线执行计划", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsPipePlanVO add(@RequestBody DevpSysOpsPipePlanAddDto devpSysOpsPipePlanAddDto){
		devpSysOpsPipePlanAddDto.setTid(saaSUtil.getAccount().getTid());
		return  devpSysOpsPipePlanRibbonService.add(devpSysOpsPipePlanAddDto);
	}

	/**
	 * 删除产品运维流水线执行计划,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品运维流水线执行计划", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsPipePlan :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsPipePlanRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品运维流水线执行计划
	 * @param devpSysOpsPipePlanEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品运维流水线执行计划(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysOpsPipePlanVO update(@RequestBody DevpSysOpsPipePlanEditDto devpSysOpsPipePlanEditDto, @ApiParam(value = "要查询的产品运维流水线执行计划id") @PathVariable Long id){

		DevpSysOpsPipePlanVO vo = devpSysOpsPipePlanRibbonService.merge(id, devpSysOpsPipePlanEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询产品运维流水线执行计划
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品运维流水线执行计划", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysOpsPipePlanVO get(@ApiParam(value = "要查询的产品运维流水线执行计划id") @PathVariable Long id) {

		DevpSysOpsPipePlanVO vo = devpSysOpsPipePlanRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询产品运维流水线执行计划列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品运维流水线执行计划列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsPipePlanVO> list(@RequestBody PageSearchRequest<DevpSysOpsPipePlanCondition> pageSearchRequest){

		DevpSysOpsPipePlanCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new DevpSysOpsPipePlanCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTid());
		PageContent<DevpSysOpsPipePlanVO> pageContent = devpSysOpsPipePlanRibbonService.list(pageSearchRequest);
		for(DevpSysOpsPipePlanVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出产品运维流水线执行计划列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出产品运维流水线执行计划列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsPipePlanCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DevpSysOpsPipePlanCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsPipePlanVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsPipePlanVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsPipePlanVO vo : voList){
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
            headMap.put("prdRid" ,"产品编号");
            headMap.put("schemeRid" ,"部署方案编号");
            headMap.put("prePipelineRid" ,"前置流水线编号");
            headMap.put("autoBuild" ,"自动构建");
            headMap.put("schedule" ,"定时执行配置");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("产品运维流水线执行计划");
        String fileName = new String(("产品运维流水线执行计划_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DevpSysOpsPipePlanVO initViewProperty( DevpSysOpsPipePlanVO vo){

	   


	   
        return vo;

	}


}
