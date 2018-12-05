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
import net.aicoder.speedcloud.console.business.icode.domain.service.EntityPropertyRibbonService;
import net.aicoder.speedcloud.console.business.icode.domain.valid.EntityPropertyValidator;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityPropertyVO;
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
 * 管理领域对象属性
 * @author icode
 */
@Api(description = "领域对象属性", tags = "EntityProperty")
@RestController
@RequestMapping(value = "/icode/domain/entityproperty")
public class EntityPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityPropertyController.class);
   
    @Autowired
	private EntityPropertyRibbonService entityPropertyRibbonService;

	@Autowired
	private EntityPropertyValidator entityPropertyValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(entityPropertyValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增领域对象属性
	 * @param entityPropertyAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增领域对象属性", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public EntityPropertyVO add(@RequestBody @Valid EntityPropertyAddDto entityPropertyAddDto){
	
		return  entityPropertyRibbonService.add(entityPropertyAddDto);
	}

	/**
	 * 删除领域对象属性,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除领域对象属性", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete entityProperty :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			entityPropertyRibbonService.delete(id);
		}

	}

	/**
	 * 更新领域对象属性
	 * @param entityPropertyEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产领域对象属性(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public EntityPropertyVO update(@RequestBody @Valid EntityPropertyEditDto entityPropertyEditDto, @ApiParam(value = "要查询的领域对象属性id") @PathVariable String id){

		EntityPropertyVO vo = entityPropertyRibbonService.merge(id, entityPropertyEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询领域对象属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询领域对象属性", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public EntityPropertyVO get(@ApiParam(value = "要查询的领域对象属性id") @PathVariable String id) {

		EntityPropertyVO vo = entityPropertyRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询领域对象属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询领域对象属性列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = EntityPropertyCondition.class)
	public PageContent<EntityPropertyVO> list(@RequestBody @Valid PageSearchRequest<EntityPropertyCondition> pageSearchRequest){

		PageContent<EntityPropertyVO> pageContent = entityPropertyRibbonService.list(pageSearchRequest);
		for(EntityPropertyVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出领域对象属性列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出领域对象属性列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(EntityPropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<EntityPropertyCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<EntityPropertyVO> content = this.list(pageSearchRequest);

        List<EntityPropertyVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(EntityPropertyVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("entity" ,"所属领域对象");
            headMap.put("code" ,"属性代码");
            headMap.put("name" ,"名称");
            headMap.put("type" ,"类型");
            headMap.put("relatedEntityId" ,"引用对象");
            headMap.put("relatedEntityPropertyId" ,"引用对象属性代码");
            headMap.put("idx" ,"排序");
            headMap.put("memo" ,"备注");
            headMap.put("primaryKey" ,"是否主键");
            headMap.put("search" ,"是否查询条件");
            headMap.put("editable" ,"可修改");
            headMap.put("nullable" ,"可为空");
            headMap.put("visible" ,"是否可见");
            headMap.put("persist" ,"持久化");

        String title = new String("领域对象属性");
        String fileName = new String(("领域对象属性_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private EntityPropertyVO initViewProperty( EntityPropertyVO vo){


	   
        return vo;

	}
}
