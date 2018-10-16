package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.controller;

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
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceInstanceRelationVO;
import net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service.ResourceInstanceRelationRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.deployscheme.valid.ResourceInstanceRelationValidator;

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
 * 管理方案资源关联实例
 * @author icode
 */
@Api(description = "方案资源关联实例", tags = "ResourceInstanceRelation")
@RestController
@RequestMapping(value = "/speedcloud/deployscheme/resourceinstancerelation")
public class ResourceInstanceRelationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceInstanceRelationController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private ResourceInstanceRelationRibbonService resourceInstanceRelationRibbonService;

	@Autowired
	ResourceInstanceRelationValidator resourceInstanceRelationValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(resourceInstanceRelationValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增方案资源关联实例
	 * @param resourceInstanceRelationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增方案资源关联实例", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ResourceInstanceRelationVO add(@RequestBody ResourceInstanceRelationAddDto resourceInstanceRelationAddDto){
    	resourceInstanceRelationAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  resourceInstanceRelationRibbonService.add(resourceInstanceRelationAddDto);
	}

	/**
	 * 删除方案资源关联实例,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除方案资源关联实例", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete resourceInstanceRelation :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			resourceInstanceRelationRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新方案资源关联实例
	 * @param resourceInstanceRelationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产方案资源关联实例(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public ResourceInstanceRelationVO update(@RequestBody ResourceInstanceRelationEditDto resourceInstanceRelationEditDto, @ApiParam(value = "要查询的方案资源关联实例id") @PathVariable Long id){

		ResourceInstanceRelationVO vo = resourceInstanceRelationRibbonService.merge(id, resourceInstanceRelationEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询方案资源关联实例
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询方案资源关联实例", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public ResourceInstanceRelationVO get(@ApiParam(value = "要查询的方案资源关联实例id") @PathVariable Long id) {

		ResourceInstanceRelationVO vo = resourceInstanceRelationRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询方案资源关联实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询方案资源关联实例列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ResourceInstanceRelationVO> list(@RequestBody PageSearchRequest<ResourceInstanceRelationCondition> pageSearchRequest){

		ResourceInstanceRelationCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new ResourceInstanceRelationCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<ResourceInstanceRelationVO> pageContent = resourceInstanceRelationRibbonService.list(pageSearchRequest);
		for(ResourceInstanceRelationVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出方案资源关联实例列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出方案资源关联实例列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ResourceInstanceRelationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<ResourceInstanceRelationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ResourceInstanceRelationVO> content = this.list(pageSearchRequest);

        List<ResourceInstanceRelationVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ResourceInstanceRelationVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("type" ,"类型");
            headMap.put("status" ,"状态");
            headMap.put("notes" ,"备注");
            headMap.put("seq" ,"顺序号");
            headMap.put("prdRid" ,"产品编号");
            headMap.put("scheme" ,"部署方案编号");
            headMap.put("resource" ,"关联资源编号");
            headMap.put("asset" ,"关联实例编号");
            headMap.put("assetCategoryCode" ,"关联实例类别代码");
            headMap.put("assetTypeCode" ,"关联实例类型代码");

        String title = new String("方案资源关联实例");
        String fileName = new String(("方案资源关联实例_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ResourceInstanceRelationVO initViewProperty( ResourceInstanceRelationVO vo){

	   


	   
        return vo;

	}


}
