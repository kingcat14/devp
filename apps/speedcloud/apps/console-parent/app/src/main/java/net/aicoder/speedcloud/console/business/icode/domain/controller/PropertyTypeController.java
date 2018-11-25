package net.aicoder.speedcloud.console.business.icode.domain.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.console.business.icode.domain.service.PropertyTypeRibbonService;
import net.aicoder.speedcloud.console.business.icode.domain.valid.PropertyTypeValidator;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.PropertyTypeVO;
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
 * 管理属性类型
 * @author icode
 */
@Api(description = "属性类型", tags = "PropertyType")
@RestController
@RequestMapping(value = "/icode/domain/propertytype")
public class PropertyTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyTypeController.class);
   
    @Autowired
	private PropertyTypeRibbonService propertyTypeRibbonService;

	@Autowired
	private PropertyTypeValidator propertyTypeValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(propertyTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增属性类型
	 * @param propertyTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增属性类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public PropertyTypeVO add(@RequestBody @Valid PropertyTypeAddDto propertyTypeAddDto){
	
		return  propertyTypeRibbonService.add(propertyTypeAddDto);
	}

	/**
	 * 删除属性类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除属性类型", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete propertyType :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			propertyTypeRibbonService.delete(id);
		}

	}

	/**
	 * 更新属性类型
	 * @param propertyTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产属性类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public PropertyTypeVO update(@RequestBody @Valid PropertyTypeEditDto propertyTypeEditDto, @ApiParam(value = "要查询的属性类型id") @PathVariable String id){

		PropertyTypeVO vo = propertyTypeRibbonService.merge(id, propertyTypeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询属性类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询属性类型", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public PropertyTypeVO get(@ApiParam(value = "要查询的属性类型id") @PathVariable String id) {

		PropertyTypeVO vo = propertyTypeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询属性类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询属性类型列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = PropertyTypeCondition.class)
	public PageContent<PropertyTypeVO> list(@RequestBody @Valid PageSearchRequest<PropertyTypeCondition> pageSearchRequest){

		PageContent<PropertyTypeVO> pageContent = propertyTypeRibbonService.list(pageSearchRequest);
		for(PropertyTypeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出属性类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出属性类型列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(PropertyTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<PropertyTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PropertyTypeVO> content = this.list(pageSearchRequest);

        List<PropertyTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PropertyTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("code" ,"代码");
            headMap.put("name" ,"名称");
            headMap.put("viewIndex" ,"排序");

        String title = new String("属性类型");
        String fileName = new String(("属性类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private PropertyTypeVO initViewProperty( PropertyTypeVO vo){


	   
        return vo;

	}
}
