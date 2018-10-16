package net.aicoder.speedcloud.console.business.speedcloud.deploy.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryEditDto;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesCategoryVO;
import net.aicoder.speedcloud.console.business.speedcloud.deploy.service.DevpSysDpyResourcesCategoryRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.deploy.valid.DevpSysDpyResourcesCategoryValidator;

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
 * 管理部署资源类别
 * @author icode
 */
@Api(description = "部署资源类别", tags = "DevpSysDpyResourcesCategory")
@RestController
@RequestMapping(value = "/speedcloud/deploy/devpsysdpyresourcescategory")
public class DevpSysDpyResourcesCategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesCategoryController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private DevpSysDpyResourcesCategoryRibbonService devpSysDpyResourcesCategoryRibbonService;

	@Autowired
	DevpSysDpyResourcesCategoryValidator devpSysDpyResourcesCategoryValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResourcesCategoryValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署资源类别
	 * @param devpSysDpyResourcesCategoryAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署资源类别", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResourcesCategoryVO add(@RequestBody DevpSysDpyResourcesCategoryAddDto devpSysDpyResourcesCategoryAddDto){
    	devpSysDpyResourcesCategoryAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  devpSysDpyResourcesCategoryRibbonService.add(devpSysDpyResourcesCategoryAddDto);
	}

	/**
	 * 删除部署资源类别,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署资源类别", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResourcesCategory :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResourcesCategoryRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署资源类别
	 * @param devpSysDpyResourcesCategoryEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署资源类别(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysDpyResourcesCategoryVO update(@RequestBody DevpSysDpyResourcesCategoryEditDto devpSysDpyResourcesCategoryEditDto, @ApiParam(value = "要查询的部署资源类别id") @PathVariable Long id){

		DevpSysDpyResourcesCategoryVO vo = devpSysDpyResourcesCategoryRibbonService.merge(id, devpSysDpyResourcesCategoryEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询部署资源类别
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署资源类别", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysDpyResourcesCategoryVO get(@ApiParam(value = "要查询的部署资源类别id") @PathVariable Long id) {

		DevpSysDpyResourcesCategoryVO vo = devpSysDpyResourcesCategoryRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询部署资源类别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署资源类别列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResourcesCategoryVO> list(@RequestBody PageSearchRequest<DevpSysDpyResourcesCategoryCondition> pageSearchRequest){

		DevpSysDpyResourcesCategoryCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new DevpSysDpyResourcesCategoryCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<DevpSysDpyResourcesCategoryVO> pageContent = devpSysDpyResourcesCategoryRibbonService.list(pageSearchRequest);
		for(DevpSysDpyResourcesCategoryVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署资源类别列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署资源类别列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysDpyResourcesCategoryCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DevpSysDpyResourcesCategoryCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyResourcesCategoryVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyResourcesCategoryVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyResourcesCategoryVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("icon" ,"图标");

        String title = new String("部署资源类别");
        String fileName = new String(("部署资源类别_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DevpSysDpyResourcesCategoryVO initViewProperty( DevpSysDpyResourcesCategoryVO vo){

	   


	   
        return vo;

	}


}