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
import net.aicoder.speedcloud.console.business.icode.domain.service.EntityRibbonService;
import net.aicoder.speedcloud.console.business.icode.domain.valid.EntityValidator;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityVO;
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
 * 管理领域对象
 * @author icode
 */
@Api(description = "领域对象", tags = "Entity")
@RestController
@RequestMapping(value = "/icode/domain/entity")
public class EntityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityController.class);
   
    @Autowired
	private EntityRibbonService entityRibbonService;

	@Autowired
	private EntityValidator entityValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(entityValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增领域对象
	 * @param entityAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增领域对象", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public EntityVO add(@RequestBody @Valid EntityAddDto entityAddDto){
	
		return  entityRibbonService.add(entityAddDto);
	}

	/**
	 * 删除领域对象,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除领域对象", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete entity :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			entityRibbonService.delete(id);
		}

	}

	/**
	 * 更新领域对象
	 * @param entityEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产领域对象(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public EntityVO update(@RequestBody @Valid EntityEditDto entityEditDto, @ApiParam(value = "要查询的领域对象id") @PathVariable String id){

		EntityVO vo = entityRibbonService.merge(id, entityEditDto);

		return  vo;
	}

	/**
	 * 复制领域对象
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}/copy")
	public EntityVO copy(@PathVariable String id){

		EntityVO entity = entityRibbonService.copy(id);
		EntityVO entityVO = initViewProperty(entity);
		return  entityVO;
	}

	/**
	 * 根据ID查询领域对象
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询领域对象", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public EntityVO get(@ApiParam(value = "要查询的领域对象id") @PathVariable String id) {

		EntityVO vo = entityRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询领域对象列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询领域对象列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = EntityCondition.class)
	public PageContent<EntityVO> list(@RequestBody @Valid PageSearchRequest<EntityCondition> pageSearchRequest){

		PageContent<EntityVO> pageContent = entityRibbonService.list(pageSearchRequest);
		for(EntityVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出领域对象列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出领域对象列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(EntityCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<EntityCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<EntityVO> content = this.list(pageSearchRequest);

        List<EntityVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(EntityVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("code" ,"实体代码");
            headMap.put("name" ,"实体名称");
            headMap.put("parentEntity" ,"父对象");
            headMap.put("aggregate" ,"所属聚合");
            headMap.put("domain" ,"所属领域");

        String title = new String("领域对象");
        String fileName = new String(("领域对象_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private EntityVO initViewProperty( EntityVO vo){


	   
        return vo;

	}
}
