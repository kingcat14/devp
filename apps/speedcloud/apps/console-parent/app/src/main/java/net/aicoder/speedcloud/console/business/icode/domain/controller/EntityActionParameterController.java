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
import net.aicoder.speedcloud.console.business.icode.domain.service.EntityActionParameterRibbonService;
import net.aicoder.speedcloud.console.business.icode.domain.valid.EntityActionParameterValidator;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterVO;
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
 * 管理领域对象行为参数
 * @author icode
 */
@Api(description = "领域对象行为参数", tags = "EntityActionParameter")
@RestController
@RequestMapping(value = "/icode/domain/entityactionparameter")
public class EntityActionParameterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityActionParameterController.class);
   
    @Autowired
	private EntityActionParameterRibbonService entityActionParameterRibbonService;

	@Autowired
	private EntityActionParameterValidator entityActionParameterValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(entityActionParameterValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增领域对象行为参数
	 * @param entityActionParameterAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增领域对象行为参数", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public EntityActionParameterVO add(@RequestBody @Valid EntityActionParameterAddDto entityActionParameterAddDto){
	
		return  entityActionParameterRibbonService.add(entityActionParameterAddDto);
	}

	/**
	 * 删除领域对象行为参数,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除领域对象行为参数", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete entityActionParameter :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			entityActionParameterRibbonService.delete(id);
		}

	}

	/**
	 * 更新领域对象行为参数
	 * @param entityActionParameterEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产领域对象行为参数(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public EntityActionParameterVO update(@RequestBody @Valid EntityActionParameterEditDto entityActionParameterEditDto, @ApiParam(value = "要查询的领域对象行为参数id") @PathVariable String id){

		EntityActionParameterVO vo = entityActionParameterRibbonService.merge(id, entityActionParameterEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询领域对象行为参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询领域对象行为参数", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public EntityActionParameterVO get(@ApiParam(value = "要查询的领域对象行为参数id") @PathVariable String id) {

		EntityActionParameterVO vo = entityActionParameterRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询领域对象行为参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询领域对象行为参数列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = EntityActionParameterCondition.class)
	public PageContent<EntityActionParameterVO> list(@RequestBody @Valid PageSearchRequest<EntityActionParameterCondition> pageSearchRequest){

		PageContent<EntityActionParameterVO> pageContent = entityActionParameterRibbonService.list(pageSearchRequest);
		for(EntityActionParameterVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出领域对象行为参数列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出领域对象行为参数列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(EntityActionParameterCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<EntityActionParameterCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<EntityActionParameterVO> content = this.list(pageSearchRequest);

        List<EntityActionParameterVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(EntityActionParameterVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("entityAction" ,"领域对象行为");

        String title = new String("领域对象行为参数");
        String fileName = new String(("领域对象行为参数_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private EntityActionParameterVO initViewProperty( EntityActionParameterVO vo){


	   
        return vo;

	}
}
