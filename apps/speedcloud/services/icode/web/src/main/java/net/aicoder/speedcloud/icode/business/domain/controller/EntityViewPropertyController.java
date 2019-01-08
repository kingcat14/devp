package net.aicoder.speedcloud.icode.business.domain.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.icode.business.domain.domain.Entity;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityProperty;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityViewProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyEditDto;
import net.aicoder.speedcloud.icode.business.domain.service.EntityPropertyService;
import net.aicoder.speedcloud.icode.business.domain.service.EntityService;
import net.aicoder.speedcloud.icode.business.domain.service.EntityViewPropertyService;
import net.aicoder.speedcloud.icode.business.domain.valid.EntityViewPropertyValidator;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityViewPropertyVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理领域对象展现属性
 * @author icode
 */
@Api(description = "领域对象展现属性", tags = "EntityViewProperty")
@RestController
@RequestMapping(value = "/icode/domain/entityviewproperty")
public class EntityViewPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityViewPropertyController.class);


	@Autowired
	private EntityViewPropertyService entityViewPropertyService;

	@Autowired
	private EntityService entityService;

	@Autowired
	private EntityPropertyService entityPropertyService;


	@Autowired
	private EntityViewPropertyValidator entityViewPropertyValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(entityViewPropertyValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	@ApiOperation(value = "新增", notes = "新增领域对象展现属性", httpMethod = "POST")
	@PostMapping("/createByProperty/{id}")
	@ResponseStatus( HttpStatus.CREATED )
	@BusinessFuncMonitor(value = "icode.domain.entityviewproperty.add", count = true)
	public EntityViewPropertyVO create(@PathVariable String entityPropertyId){
		EntityProperty entityProperty = entityPropertyService.find(entityPropertyId);

		EntityViewProperty entityViewProperty = entityViewPropertyService.create(entityProperty);

		return  initViewProperty(entityViewProperty);
	}

	/**
	 * 新增领域对象展现属性
	 * @param entityViewPropertyAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增领域对象展现属性", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "icode.domain.entityviewproperty.add", count = true)
	public EntityViewPropertyVO add(@RequestBody @Valid EntityViewPropertyAddDto entityViewPropertyAddDto){
		EntityViewProperty entityViewProperty = new EntityViewProperty();
		BeanUtils.copyProperties(entityViewPropertyAddDto, entityViewProperty);

		entityViewPropertyService.add(entityViewProperty);

		return  initViewProperty(entityViewProperty);
	}

	/**
	 * 删除领域对象展现属性,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除领域对象展现属性", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.domain.entityviewproperty.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete entityViewProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			entityViewPropertyService.delete(id);
		}

	}

	/**
	 * 更新领域对象展现属性
	 * @param entityViewPropertyEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改领域对象展现属性(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.domain.entityviewproperty.update", count = true)
	public	EntityViewPropertyVO update(@RequestBody @Valid EntityViewPropertyEditDto entityViewPropertyEditDto, @PathVariable String id){
		EntityViewProperty entityViewProperty = entityViewPropertyService.find(id);
		BeanUtils.copyProperties(entityViewPropertyEditDto, entityViewProperty);
		entityViewProperty.setId(id);
		entityViewPropertyService.merge(entityViewProperty);

		EntityViewPropertyVO vo = initViewProperty(entityViewProperty);
		return  vo;
	}

	/**
	 * 根据ID查询领域对象展现属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询领域对象展现属性", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.domain.entityviewproperty.get")
	public  EntityViewPropertyVO get(@PathVariable String id) {

		EntityViewProperty entityViewProperty = entityViewPropertyService.find(id);
		if(entityViewProperty == null){
			throw new ResourceNotFoundException("找不到指定的领域对象展现属性，请检查ID");
		}
		EntityViewPropertyVO vo = initViewProperty(entityViewProperty);
		return vo;
	}

	/**
	 * 查询领域对象展现属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询领域对象展现属性列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.domain.entityviewproperty.list")
	public PageContent<EntityViewPropertyVO> list(@RequestBody PageSearchRequest<EntityViewPropertyCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<EntityViewProperty> page = entityViewPropertyService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<EntityViewPropertyVO> voList = new ArrayList<>();
		for(EntityViewProperty entityViewProperty : page.getContent()){
			voList.add(initViewProperty(entityViewProperty));
		}

		PageContent<EntityViewPropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出领域对象展现属性列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出领域对象展现属性列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(EntityViewPropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<EntityViewPropertyCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<EntityViewPropertyVO> content = this.list(pageSearchRequest);

        List<EntityViewPropertyVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(EntityViewPropertyVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("entity" ,"所属领域对象");
        headMap.put("code" ,"代码");
        headMap.put("name" ,"名称");
        headMap.put("addRequired" ,"新增必填");
        headMap.put("addViewable" ,"新增可见");
        headMap.put("addable" ,"新增可填");
        headMap.put("editRequired" ,"修改必填");
        headMap.put("editViewable" ,"修改可见");
        headMap.put("editable" ,"可修改");
        headMap.put("girdColumn" ,"列表可见");
        headMap.put("searchCondition" ,"可查询");
        headMap.put("simpleSearchCondition" ,"简单查询条件");
        headMap.put("idx" ,"排序");

        String title = new String("领域对象展现属性");
        String fileName = new String(("领域对象展现属性_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private EntityViewPropertyVO initViewProperty(EntityViewProperty entityViewProperty){

	    EntityViewPropertyVO vo = new EntityViewPropertyVO();
        BeanUtils.copyProperties(entityViewProperty, vo);


	    //初始化其他对象
	    initEntityPropertyGroup(vo, entityViewProperty);
        return vo;

	}

	private void initEntityPropertyGroup(EntityViewPropertyVO entityViewPropertyVO, EntityViewProperty entityViewProperty){
	
		Entity entity = entityService.find(entityViewProperty.getEntity());
		if(entity == null){
			return;
		}
		EntityVO entityVO = new EntityVO();
		BeanUtils.copyProperties(entity, entityVO);

		entityViewPropertyVO.setEntityVO(entityVO);

	}

}
