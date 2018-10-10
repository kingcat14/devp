package net.aicoder.speedcloud.console.business.speedCloud.deploy.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeEditDto;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesTypeVO;
import net.aicoder.speedcloud.console.business.speedCloud.deploy.service.DevpSysDpyResourcesTypeRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.deploy.valid.DevpSysDpyResourcesTypeValidator;

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
 * 管理部署资源类型
 * @author icode
 */
@Api(description = "部署资源类型", tags = "DevpSysDpyResourcesType")
@RestController
@RequestMapping(value = "/speedcloud/deploy/devpsysdpyresourcestype")
public class DevpSysDpyResourcesTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesTypeController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private DevpSysDpyResourcesTypeRibbonService devpSysDpyResourcesTypeRibbonService;

	@Autowired
	DevpSysDpyResourcesTypeValidator devpSysDpyResourcesTypeValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResourcesTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署资源类型
	 * @param devpSysDpyResourcesTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署资源类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResourcesTypeVO add(@RequestBody DevpSysDpyResourcesTypeAddDto devpSysDpyResourcesTypeAddDto){
    	devpSysDpyResourcesTypeAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  devpSysDpyResourcesTypeRibbonService.add(devpSysDpyResourcesTypeAddDto);
	}

	/**
	 * 删除部署资源类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署资源类型", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResourcesType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResourcesTypeRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署资源类型
	 * @param devpSysDpyResourcesTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署资源类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysDpyResourcesTypeVO update(@RequestBody DevpSysDpyResourcesTypeEditDto devpSysDpyResourcesTypeEditDto, @ApiParam(value = "要查询的部署资源类型id") @PathVariable Long id){

		DevpSysDpyResourcesTypeVO vo = devpSysDpyResourcesTypeRibbonService.merge(id, devpSysDpyResourcesTypeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询部署资源类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署资源类型", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysDpyResourcesTypeVO get(@ApiParam(value = "要查询的部署资源类型id") @PathVariable Long id) {

		DevpSysDpyResourcesTypeVO vo = devpSysDpyResourcesTypeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询部署资源类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署资源类型列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResourcesTypeVO> list(@RequestBody PageSearchRequest<DevpSysDpyResourcesTypeCondition> pageSearchRequest){

		DevpSysDpyResourcesTypeCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new DevpSysDpyResourcesTypeCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<DevpSysDpyResourcesTypeVO> pageContent = devpSysDpyResourcesTypeRibbonService.list(pageSearchRequest);
		for(DevpSysDpyResourcesTypeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署资源类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署资源类型列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysDpyResourcesTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DevpSysDpyResourcesTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyResourcesTypeVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyResourcesTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyResourcesTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("icon" ,"图标");

        String title = new String("部署资源类型");
        String fileName = new String(("部署资源类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DevpSysDpyResourcesTypeVO initViewProperty( DevpSysDpyResourcesTypeVO vo){

	   


	   
        return vo;

	}


}
