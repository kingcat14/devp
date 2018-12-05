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
import net.aicoder.speedcloud.icode.business.domain.controller.convert.EntityActionParameterVOConvert;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityAction;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityActionParameter;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterEditDto;
import net.aicoder.speedcloud.icode.business.domain.service.EntityActionParameterService;
import net.aicoder.speedcloud.icode.business.domain.service.EntityActionService;
import net.aicoder.speedcloud.icode.business.domain.valid.EntityActionParameterValidator;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionVO;
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
 * 管理领域对象行为参数
 * @author icode
 */
@Api(description = "领域对象行为参数", tags = "EntityActionParameter")
@RestController
@RequestMapping(value = "/icode/domain/entityactionparameter")
public class EntityActionParameterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityActionParameterController.class);


	@Autowired
	private EntityActionParameterService entityActionParameterService;

	@Autowired
	private EntityActionService entityActionService;

	@Autowired
	private EntityActionParameterVOConvert parameterVOConvert;


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
  	@BusinessFuncMonitor(value = "icode.domain.entityactionparameter.add")
	public EntityActionParameterVO add(@RequestBody @Valid EntityActionParameterAddDto entityActionParameterAddDto){
		EntityActionParameter entityActionParameter = new EntityActionParameter();
		BeanUtils.copyProperties(entityActionParameterAddDto, entityActionParameter);

		entityActionParameterService.add(entityActionParameter);

		return  initViewProperty(entityActionParameter);
	}

	/**
	 * 删除领域对象行为参数,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除领域对象行为参数", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.domain.entityactionparameter.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete entityActionParameter :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			entityActionParameterService.delete(id);
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
  	@BusinessFuncMonitor(value = "icode.domain.entityactionparameter.update")
	public	EntityActionParameterVO update(@RequestBody @Valid EntityActionParameterEditDto entityActionParameterEditDto, @PathVariable String id){
		EntityActionParameter entityActionParameter = new EntityActionParameter();
		BeanUtils.copyProperties(entityActionParameterEditDto, entityActionParameter);
		entityActionParameter.setId(id);
		entityActionParameterService.merge(entityActionParameter);

		EntityActionParameterVO vo = initViewProperty(entityActionParameter);
		return  vo;
	}

	/**
	 * 根据ID查询领域对象行为参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询领域对象行为参数", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.domain.entityactionparameter.get")
	public  EntityActionParameterVO get(@PathVariable String id) {

		EntityActionParameter entityActionParameter = entityActionParameterService.find(id);

		EntityActionParameterVO vo = initViewProperty(entityActionParameter);
		return vo;
	}

	/**
	 * 根据ID查询行为参数的详细信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询详细信息", notes = "根据ID查询领域对象", httpMethod = "GET")
	@GetMapping(path="/{id}/detail")
	@BusinessFuncMonitor(value = "icode.domain.entity.get")
	public EntityActionParameterVO getDetail(@PathVariable String id) {

		EntityActionParameter parameter = entityActionParameterService.find(id);
		if(parameter == null){
			throw new ResourceNotFoundException("找不到指定的对象，请检查ID");
		}
		EntityActionParameterVO vo = parameterVOConvert.initDetail(parameter);
		return vo;
	}

	/**
	 * 查询领域对象行为参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询领域对象行为参数列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.domain.entityactionparameter.list")
	public PageContent<EntityActionParameterVO> list(@RequestBody PageSearchRequest<EntityActionParameterCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<EntityActionParameter> page = entityActionParameterService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<EntityActionParameterVO> voList = new ArrayList<>();
		for(EntityActionParameter entityActionParameter : page.getContent()){
			voList.add(initViewProperty(entityActionParameter));
		}

		PageContent<EntityActionParameterVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(EntityActionParameterCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("entityAction" ,"领域对象行为");

        String title = new String("领域对象行为参数");
        String fileName = new String(("领域对象行为参数_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private EntityActionParameterVO initViewProperty(EntityActionParameter entityActionParameter){

	    EntityActionParameterVO vo = new EntityActionParameterVO();
        BeanUtils.copyProperties(entityActionParameter, vo);


	    //初始化其他对象
	    initEntityActionPropertyGroup(vo, entityActionParameter);
        return vo;

	}

	private void initEntityActionPropertyGroup(EntityActionParameterVO entityActionParameterVO, EntityActionParameter entityActionParameter){
	
		EntityAction entityAction = entityActionService.find(entityActionParameter.getEntityAction());
		if(entityAction == null){
			return;
		}
		EntityActionVO entityActionVO = new EntityActionVO();
		BeanUtils.copyProperties(entityAction, entityActionVO);

		entityActionParameterVO.setEntityActionVO(entityActionVO);

	}

}

