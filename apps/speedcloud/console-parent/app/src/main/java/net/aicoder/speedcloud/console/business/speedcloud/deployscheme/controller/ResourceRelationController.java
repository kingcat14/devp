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
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceRelationVO;
import net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service.ResourceRelationRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.deployscheme.valid.ResourceRelationValidator;

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
 * 管理方案资源间关系
 * @author icode
 */
@Api(description = "方案资源间关系", tags = "ResourceRelation")
@RestController
@RequestMapping(value = "/speedcloud/deployscheme/resourcerelation")
public class ResourceRelationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceRelationController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private ResourceRelationRibbonService resourceRelationRibbonService;

	@Autowired
	ResourceRelationValidator resourceRelationValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(resourceRelationValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增方案资源间关系
	 * @param resourceRelationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增方案资源间关系", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ResourceRelationVO add(@RequestBody ResourceRelationAddDto resourceRelationAddDto){
    	resourceRelationAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  resourceRelationRibbonService.add(resourceRelationAddDto);
	}

	/**
	 * 删除方案资源间关系,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除方案资源间关系", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete resourceRelation :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			resourceRelationRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新方案资源间关系
	 * @param resourceRelationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产方案资源间关系(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public ResourceRelationVO update(@RequestBody ResourceRelationEditDto resourceRelationEditDto, @ApiParam(value = "要查询的方案资源间关系id") @PathVariable Long id){

		ResourceRelationVO vo = resourceRelationRibbonService.merge(id, resourceRelationEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询方案资源间关系
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询方案资源间关系", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public ResourceRelationVO get(@ApiParam(value = "要查询的方案资源间关系id") @PathVariable Long id) {

		ResourceRelationVO vo = resourceRelationRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询方案资源间关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询方案资源间关系列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ResourceRelationVO> list(@RequestBody PageSearchRequest<ResourceRelationCondition> pageSearchRequest){

		ResourceRelationCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new ResourceRelationCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<ResourceRelationVO> pageContent = resourceRelationRibbonService.list(pageSearchRequest);
		for(ResourceRelationVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出方案资源间关系列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出方案资源间关系列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ResourceRelationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<ResourceRelationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ResourceRelationVO> content = this.list(pageSearchRequest);

        List<ResourceRelationVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ResourceRelationVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("resource" ,"主资源");
            headMap.put("refResource" ,"关联资源");
            headMap.put("type" ,"对应关系类型");
            headMap.put("code" ,"对应关系代码");
            headMap.put("name" ,"对应关系名称");
            headMap.put("alias" ,"对应关系别名");
            headMap.put("description" ,"对应关系描述");
            headMap.put("scheme" ,"部署方案编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("direction" ,"对应关系方向");

        String title = new String("方案资源间关系");
        String fileName = new String(("方案资源间关系_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ResourceRelationVO initViewProperty( ResourceRelationVO vo){

	   


	   
        return vo;

	}


}
