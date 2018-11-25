package net.aicoder.speedcloud.console.business.icode.project.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentDomainRelationRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.valid.ComponentDomainRelationValidator;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationEditDto;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentDomainRelationVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理组件-领域-关系
 * @author icode
 */
@Api(description = "组件-领域-关系", tags = "ComponentDomainRelation")
@RestController
@RequestMapping(value = "/icode/project/componentdomainrelation")
public class ComponentDomainRelationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentDomainRelationController.class);
   
    @Autowired
	private ComponentDomainRelationRibbonService componentDomainRelationRibbonService;

	@Autowired
	private ComponentDomainRelationValidator componentDomainRelationValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(componentDomainRelationValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件-领域-关系
	 * @param componentDomainRelationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件-领域-关系", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public ComponentDomainRelationVO add(@RequestBody @Valid ComponentDomainRelationAddDto componentDomainRelationAddDto){
	
		return  componentDomainRelationRibbonService.add(componentDomainRelationAddDto);
	}

	/**
	 * 删除组件-领域-关系,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件-领域-关系", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete componentDomainRelation :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			componentDomainRelationRibbonService.delete(id);
		}

	}

	/**
	 * 更新组件-领域-关系
	 * @param componentDomainRelationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产组件-领域-关系(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public ComponentDomainRelationVO update(@RequestBody @Valid ComponentDomainRelationEditDto componentDomainRelationEditDto, @ApiParam(value = "要查询的组件-领域-关系id") @PathVariable String id){

		ComponentDomainRelationVO vo = componentDomainRelationRibbonService.merge(id, componentDomainRelationEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询组件-领域-关系
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件-领域-关系", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public ComponentDomainRelationVO get(@ApiParam(value = "要查询的组件-领域-关系id") @PathVariable String id) {

		ComponentDomainRelationVO vo = componentDomainRelationRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询组件-领域-关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件-领域-关系列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = ComponentDomainRelationCondition.class)
	public PageContent<ComponentDomainRelationVO> list(@RequestBody @Valid PageSearchRequest<ComponentDomainRelationCondition> pageSearchRequest){

		PageContent<ComponentDomainRelationVO> pageContent = componentDomainRelationRibbonService.list(pageSearchRequest);
		for(ComponentDomainRelationVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件-领域-关系列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件-领域-关系列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ComponentDomainRelationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<ComponentDomainRelationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ComponentDomainRelationVO> content = this.list(pageSearchRequest);

        List<ComponentDomainRelationVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ComponentDomainRelationVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("component" ,"系统组件");
            headMap.put("domain" ,"领域");
            headMap.put("relationType" ,"关系类型");

        String title = new String("组件-领域-关系");
        String fileName = new String(("组件-领域-关系_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ComponentDomainRelationVO initViewProperty( ComponentDomainRelationVO vo){


	   
        return vo;

	}
}
