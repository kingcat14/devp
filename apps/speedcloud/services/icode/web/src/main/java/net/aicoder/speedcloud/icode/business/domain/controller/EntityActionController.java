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
import net.aicoder.speedcloud.icode.business.domain.domain.Entity;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityAction;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionEditDto;
import net.aicoder.speedcloud.icode.business.domain.service.EntityActionService;
import net.aicoder.speedcloud.icode.business.domain.service.EntityService;
import net.aicoder.speedcloud.icode.business.domain.valid.EntityActionValidator;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityVO;
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
 * 管理领域对象行为
 * @author icode
 */
@Api(description = "领域对象行为", tags = "EntityAction")
@RestController
@RequestMapping(value = "/icode/domain/entityaction")
public class EntityActionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityActionController.class);


	@Autowired
	private EntityActionService entityActionService;

	@Autowired
	private EntityService entityService;


	@Autowired
	private EntityActionValidator entityActionValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(entityActionValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增领域对象行为
	 * @param entityActionAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增领域对象行为", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "icode.domain.entityaction.add")
	public EntityActionVO add(@RequestBody @Valid EntityActionAddDto entityActionAddDto){
		EntityAction entityAction = new EntityAction();
		BeanUtils.copyProperties(entityActionAddDto, entityAction);

		entityActionService.add(entityAction);

		return  initViewProperty(entityAction);
	}

	/**
	 * 删除领域对象行为,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除领域对象行为", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.domain.entityaction.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete entityAction :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			entityActionService.delete(id);
		}

	}

	/**
	 * 更新领域对象行为
	 * @param entityActionEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产领域对象行为(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.domain.entityaction.update")
	public	EntityActionVO update(@RequestBody @Valid EntityActionEditDto entityActionEditDto, @PathVariable String id){
		EntityAction entityAction = new EntityAction();
		BeanUtils.copyProperties(entityActionEditDto, entityAction);
		entityAction.setId(id);
		entityActionService.merge(entityAction);

		EntityActionVO vo = initViewProperty(entityAction);
		return  vo;
	}

	/**
	 * 根据ID查询领域对象行为
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询领域对象行为", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.domain.entityaction.get")
	public  EntityActionVO get(@PathVariable String id) {

		EntityAction entityAction = entityActionService.find(id);

		EntityActionVO vo = initViewProperty(entityAction);
		return vo;
	}

	/**
	 * 查询领域对象行为列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询领域对象行为列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.domain.entityaction.list")
	public PageContent<EntityActionVO> list(@RequestBody PageSearchRequest<EntityActionCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<EntityAction> page = entityActionService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<EntityActionVO> voList = new ArrayList<>();
		for(EntityAction entityAction : page.getContent()){
			voList.add(initViewProperty(entityAction));
		}

		PageContent<EntityActionVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出领域对象行为列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出领域对象行为列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(EntityActionCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<EntityActionCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<EntityActionVO> content = this.list(pageSearchRequest);

        List<EntityActionVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(EntityActionVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("code" ,"代码");
        headMap.put("name" ,"行为名称");
        headMap.put("memo" ,"备注");
        headMap.put("description" ,"行为描述");
        headMap.put("request" ,"行为输入对象");
        headMap.put("response" ,"行为响应对象");
        headMap.put("entity" ,"所属领域对象");
        headMap.put("type" ,"行为类型");

        String title = new String("领域对象行为");
        String fileName = new String(("领域对象行为_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private EntityActionVO initViewProperty(EntityAction entityAction){

	    EntityActionVO vo = new EntityActionVO();
        BeanUtils.copyProperties(entityAction, vo);


	    //初始化其他对象
	    initEntityPropertyGroup(vo, entityAction);
        return vo;

	}


	private void initEntityPropertyGroup(EntityActionVO entityActionVO, EntityAction entityAction){
	
		Entity entity = entityService.find(entityAction.getEntity());
		if(entity == null){
			return;
		}
		EntityVO entityVO = new EntityVO();
		BeanUtils.copyProperties(entity, entityVO);

		entityActionVO.setEntityVO(entityVO);

	}


}

