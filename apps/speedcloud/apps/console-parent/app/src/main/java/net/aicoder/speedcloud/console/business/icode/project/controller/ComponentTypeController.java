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
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentTypeRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.valid.ComponentTypeValidator;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeEditDto;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentTypeVO;
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
 * 管理组件类型
 * @author icode
 */
@Api(description = "组件类型", tags = "ComponentType")
@RestController
@RequestMapping(value = "/icode/project/componenttype")
public class ComponentTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentTypeController.class);
   
    @Autowired
	private ComponentTypeRibbonService componentTypeRibbonService;

	@Autowired
	private ComponentTypeValidator componentTypeValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(componentTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件类型
	 * @param componentTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public ComponentTypeVO add(@RequestBody @Valid ComponentTypeAddDto componentTypeAddDto){
	
		return  componentTypeRibbonService.add(componentTypeAddDto);
	}

	/**
	 * 删除组件类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件类型", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete componentType :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			componentTypeRibbonService.delete(id);
		}

	}

	/**
	 * 更新组件类型
	 * @param componentTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产组件类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public ComponentTypeVO update(@RequestBody @Valid ComponentTypeEditDto componentTypeEditDto, @ApiParam(value = "要查询的组件类型id") @PathVariable String id){

		ComponentTypeVO vo = componentTypeRibbonService.merge(id, componentTypeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询组件类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件类型", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public ComponentTypeVO get(@ApiParam(value = "要查询的组件类型id") @PathVariable String id) {

		ComponentTypeVO vo = componentTypeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询组件类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件类型列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = ComponentTypeCondition.class)
	public PageContent<ComponentTypeVO> list(@RequestBody @Valid PageSearchRequest<ComponentTypeCondition> pageSearchRequest){

		PageContent<ComponentTypeVO> pageContent = componentTypeRibbonService.list(pageSearchRequest);
		for(ComponentTypeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件类型列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ComponentTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<ComponentTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ComponentTypeVO> content = this.list(pageSearchRequest);

        List<ComponentTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ComponentTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("code" ,"代码");
            headMap.put("name" ,"名称");
            headMap.put("category" ,"种类");
            headMap.put("icon" ,"默认图标");
            headMap.put("idx" ,"排序");

        String title = new String("组件类型");
        String fileName = new String(("组件类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ComponentTypeVO initViewProperty( ComponentTypeVO vo){


	   
        return vo;

	}
}
