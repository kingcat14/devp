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
import net.aicoder.speedcloud.icode.business.domain.domain.EntityActionParameterProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyEditDto;
import net.aicoder.speedcloud.icode.business.domain.service.EntityActionParameterPropertyService;
import net.aicoder.speedcloud.icode.business.domain.service.EntityActionParameterService;
import net.aicoder.speedcloud.icode.business.domain.valid.EntityActionParameterPropertyValidator;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterPropertyVO;
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
 * 管理领域对象行为参数属性
 * @author icode
 */
@Api(description = "领域对象行为参数属性", tags = "EntityActionParameterProperty")
@RestController
@RequestMapping(value = "/icode/domain/entityactionparameterproperty")
public class EntityActionParameterPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityActionParameterPropertyController.class);


	@Autowired
	private EntityActionParameterPropertyService entityActionParameterPropertyService;

	@Autowired
	private EntityActionParameterService entityActionParameterService;


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
  	@BusinessFuncMonitor(value = "icode.domain.entityactionparameterproperty.add")
	public EntityActionParameterPropertyVO add(@RequestBody @Valid EntityActionParameterPropertyAddDto entityActionParameterPropertyAddDto){
		EntityActionParameterProperty entityActionParameterProperty = new EntityActionParameterProperty();
		BeanUtils.copyProperties(entityActionParameterPropertyAddDto, entityActionParameterProperty);

		entityActionParameterPropertyService.add(entityActionParameterProperty);

		return  initViewProperty(entityActionParameterProperty);
	}

	/**
	 * 删除领域对象行为参数属性,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除领域对象行为参数属性", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.domain.entityactionparameterproperty.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete entityActionParameterProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			entityActionParameterPropertyService.delete(id);
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
  	@BusinessFuncMonitor(value = "icode.domain.entityactionparameterproperty.update")
	public	EntityActionParameterPropertyVO update(@RequestBody @Valid EntityActionParameterPropertyEditDto entityActionParameterPropertyEditDto, @PathVariable String id){
		EntityActionParameterProperty entityActionParameterProperty = new EntityActionParameterProperty();
		BeanUtils.copyProperties(entityActionParameterPropertyEditDto, entityActionParameterProperty);
		entityActionParameterProperty.setId(id);
		entityActionParameterPropertyService.merge(entityActionParameterProperty);

		EntityActionParameterPropertyVO vo = initViewProperty(entityActionParameterProperty);
		return  vo;
	}

	/**
	 * 根据ID查询领域对象行为参数属性
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询领域对象行为参数属性", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.domain.entityactionparameterproperty.get")
	public  EntityActionParameterPropertyVO get(@PathVariable String id) {

		EntityActionParameterProperty entityActionParameterProperty = entityActionParameterPropertyService.find(id);

		EntityActionParameterPropertyVO vo = initViewProperty(entityActionParameterProperty);
		return vo;
	}

	/**
	 * 查询领域对象行为参数属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询领域对象行为参数属性列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.domain.entityactionparameterproperty.list")
	public PageContent<EntityActionParameterPropertyVO> list(@RequestBody PageSearchRequest<EntityActionParameterPropertyCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<EntityActionParameterProperty> page = entityActionParameterPropertyService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<EntityActionParameterPropertyVO> voList = new ArrayList<>();
		for(EntityActionParameterProperty entityActionParameterProperty : page.getContent()){
			voList.add(initViewProperty(entityActionParameterProperty));
		}

		PageContent<EntityActionParameterPropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(EntityActionParameterPropertyCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

        Map<String,String> headMap = new LinkedHashMap<>();

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

	private EntityActionParameterPropertyVO initViewProperty(EntityActionParameterProperty entityActionParameterProperty){

	    EntityActionParameterPropertyVO vo = new EntityActionParameterPropertyVO();
        BeanUtils.copyProperties(entityActionParameterProperty, vo);


	    //初始化其他对象
        return vo;

	}


}

