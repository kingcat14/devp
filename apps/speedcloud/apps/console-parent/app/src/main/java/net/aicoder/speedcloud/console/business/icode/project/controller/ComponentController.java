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
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.valid.ComponentValidator;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentEditDto;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
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
 * 管理组件
 * @author icode
 */
@Api(description = "组件", tags = "Component")
@RestController
@RequestMapping(value = "/icode/project/component")
public class ComponentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentController.class);
   
    @Autowired
	private ComponentRibbonService componentRibbonService;

	@Autowired
	private ComponentValidator componentValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(componentValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件
	 * @param componentAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public ComponentVO add(@RequestBody @Valid ComponentAddDto componentAddDto){
	
		return  componentRibbonService.add(componentAddDto);
	}

	/**
	 * 删除组件,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete component :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			componentRibbonService.delete(id);
		}

	}

	/**
	 * 更新组件
	 * @param componentEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产组件(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public ComponentVO update(@RequestBody @Valid ComponentEditDto componentEditDto, @ApiParam(value = "要查询的组件id") @PathVariable String id){

		ComponentVO vo = componentRibbonService.merge(id, componentEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询组件
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public ComponentVO get(@ApiParam(value = "要查询的组件id") @PathVariable String id) {

		ComponentVO vo = componentRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询组件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = ComponentCondition.class)
	public PageContent<ComponentVO> list(@RequestBody @Valid PageSearchRequest<ComponentCondition> pageSearchRequest){

		PageContent<ComponentVO> pageContent = componentRibbonService.list(pageSearchRequest);
		for(ComponentVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ComponentCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<ComponentCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ComponentVO> content = this.list(pageSearchRequest);

        List<ComponentVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ComponentVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"组件名称");
            headMap.put("code" ,"组件代码");
            headMap.put("basePackage" ,"基础包");
            headMap.put("description" ,"描述");
            headMap.put("tplSet" ,"代码模板");
            headMap.put("number" ,"组件编号");
            headMap.put("groupCode" ,"分组代码");
            headMap.put("product" ,"所属产品");

        String title = new String("组件");
        String fileName = new String(("组件_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ComponentVO initViewProperty( ComponentVO vo){


	   
        return vo;

	}
}
