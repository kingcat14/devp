package net.aicoder.speedcloud.icode.business.domain.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyEditDto;
import net.aicoder.speedcloud.icode.business.domain.service.EntityPropertyService;
import net.aicoder.speedcloud.icode.business.domain.service.EntityService;
import net.aicoder.speedcloud.icode.business.domain.valid.EntityPropertyValidator;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityPropertyVO;
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
 * 管理领域对象属性
 * @author icode
 */
@Api(description = "领域对象属性", tags = "EntityProperty")
@RestController
@RequestMapping(value = "/icode/domain/entityproperty")
public class EntityPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityPropertyController.class);


	@Autowired
	private EntityPropertyService entityPropertyService;

	@Autowired
	private EntityService entityService;


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
  	@BusinessFuncMonitor(value = "icode.domain.entityproperty.add")
	public EntityPropertyVO add(@RequestBody @Valid EntityPropertyAddDto entityPropertyAddDto){
		EntityProperty entityProperty = new EntityProperty();
		BeanUtils.copyProperties(entityPropertyAddDto, entityProperty);

		entityPropertyService.add(entityProperty);

		return  initViewProperty(entityProperty);
	}

	/**
	 * 删除领域对象属性,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除领域对象属性", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.domain.entityproperty.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete entityProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			entityPropertyService.delete(id);
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
  	@BusinessFuncMonitor(value = "icode.domain.entityproperty.update")
	public	EntityPropertyVO update(@RequestBody @Valid EntityPropertyEditDto entityPropertyEditDto, @PathVariable String id){
		EntityProperty entityProperty = new EntityProperty();
		BeanUtils.copyProperties(entityPropertyEditDto, entityProperty);
		entityProperty.setId(id);
		entityPropertyService.merge(entityProperty);

		EntityPropertyVO vo = initViewProperty(entityProperty);
		return  vo;
	}

	/**
	 * 根据ID查询领域对象属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询领域对象属性", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.domain.entityproperty.get")
	public  EntityPropertyVO get(@PathVariable String id) {

		EntityProperty entityProperty = entityPropertyService.find(id);

		EntityPropertyVO vo = initViewProperty(entityProperty);
		return vo;
	}

	/**
	 * 查询领域对象属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询领域对象属性列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.domain.entityproperty.list")
	public PageContent<EntityPropertyVO> list(@RequestBody PageSearchRequest<EntityPropertyCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<EntityProperty> page = entityPropertyService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<EntityPropertyVO> voList = new ArrayList<>();
		for(EntityProperty entityProperty : page.getContent()){
			voList.add(initViewProperty(entityProperty));
		}

		PageContent<EntityPropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(EntityPropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

        Map<String,String> headMap = new LinkedHashMap<>();

        String title = new String("领域对象属性");
        String fileName = new String(("领域对象属性_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private EntityPropertyVO initViewProperty(EntityProperty entityProperty){

	    EntityPropertyVO vo = new EntityPropertyVO();
        BeanUtils.copyProperties(entityProperty, vo);

	    return vo;

	}



}

