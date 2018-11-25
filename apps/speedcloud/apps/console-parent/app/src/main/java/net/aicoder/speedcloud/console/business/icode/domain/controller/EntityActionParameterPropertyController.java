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
import net.aicoder.speedcloud.console.business.icode.domain.service.EntityActionParameterPropertyRibbonService;
import net.aicoder.speedcloud.console.business.icode.domain.valid.EntityActionParameterPropertyValidator;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterPropertyVO;
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
 * 管理领域对象行为参数属性
 * @author icode
 */
@Api(description = "领域对象行为参数属性", tags = "EntityActionParameterProperty")
@RestController
@RequestMapping(value = "/icode/domain/entityactionparameterproperty")
public class EntityActionParameterPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityActionParameterPropertyController.class);
   
    @Autowired
	private EntityActionParameterPropertyRibbonService entityActionParameterPropertyRibbonService;

	@Autowired
	private EntityActionParameterPropertyValidator entityActionParameterPropertyValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(entityActionParameterPropertyValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增领域对象行为参数属性
	 * @param entityActionParameterPropertyAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增领域对象行为参数属性", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public EntityActionParameterPropertyVO add(@RequestBody @Valid EntityActionParameterPropertyAddDto entityActionParameterPropertyAddDto){
	
		return  entityActionParameterPropertyRibbonService.add(entityActionParameterPropertyAddDto);
	}

	/**
	 * 删除领域对象行为参数属性,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除领域对象行为参数属性", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete entityActionParameterProperty :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			entityActionParameterPropertyRibbonService.delete(id);
		}

	}

	/**
	 * 更新领域对象行为参数属性
	 * @param entityActionParameterPropertyEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产领域对象行为参数属性(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public EntityActionParameterPropertyVO update(@RequestBody @Valid EntityActionParameterPropertyEditDto entityActionParameterPropertyEditDto, @ApiParam(value = "要查询的领域对象行为参数属性id") @PathVariable String id){

		EntityActionParameterPropertyVO vo = entityActionParameterPropertyRibbonService.merge(id, entityActionParameterPropertyEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询领域对象行为参数属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询领域对象行为参数属性", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public EntityActionParameterPropertyVO get(@ApiParam(value = "要查询的领域对象行为参数属性id") @PathVariable String id) {

		EntityActionParameterPropertyVO vo = entityActionParameterPropertyRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询领域对象行为参数属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询领域对象行为参数属性列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = EntityActionParameterPropertyCondition.class)
	public PageContent<EntityActionParameterPropertyVO> list(@RequestBody @Valid PageSearchRequest<EntityActionParameterPropertyCondition> pageSearchRequest){

		PageContent<EntityActionParameterPropertyVO> pageContent = entityActionParameterPropertyRibbonService.list(pageSearchRequest);
		for(EntityActionParameterPropertyVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出领域对象行为参数属性列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出领域对象行为参数属性列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(EntityActionParameterPropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<EntityActionParameterPropertyCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<EntityActionParameterPropertyVO> content = this.list(pageSearchRequest);

        List<EntityActionParameterPropertyVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(EntityActionParameterPropertyVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("code" ,"属性代码");
            headMap.put("name" ,"属性名称");
            headMap.put("type" ,"类型");
            headMap.put("wrapperType" ,"外覆类型");
            headMap.put("idx" ,"排序");
            headMap.put("memo" ,"备注");

        String title = new String("领域对象行为参数属性");
        String fileName = new String(("领域对象行为参数属性_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private EntityActionParameterPropertyVO initViewProperty( EntityActionParameterPropertyVO vo){


	   
        return vo;

	}
}
