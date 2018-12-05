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
import net.aicoder.speedcloud.icode.business.domain.controller.convert.EntityVOConvert;
import net.aicoder.speedcloud.icode.business.domain.domain.Domain;
import net.aicoder.speedcloud.icode.business.domain.domain.Entity;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityEditDto;
import net.aicoder.speedcloud.icode.business.domain.service.DomainService;
import net.aicoder.speedcloud.icode.business.domain.service.EntityService;
import net.aicoder.speedcloud.icode.business.domain.valid.EntityValidator;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
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
 * 管理领域对象
 * @author icode
 */
@Api(description = "领域对象", tags = "Entity")
@RestController
@RequestMapping(value = "/icode/domain/entity")
public class EntityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityController.class);

	@Autowired
	private EntityService entityService;

	@Autowired
	private DomainService domainService;

	@Autowired
	private EntityVOConvert entityVOConvert;

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
  	@BusinessFuncMonitor(value = "icode.domain.entity.add")
	public EntityVO add(@RequestBody @Valid EntityAddDto entityAddDto){
		Entity entity = new Entity();
		BeanUtils.copyProperties(entityAddDto, entity);

		entityService.add(entity);

		return  initViewProperty(entity);
	}

	/**
	 * 删除领域对象,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除领域对象", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.domain.entity.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete entity :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			entityService.delete(id);
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
  	@BusinessFuncMonitor(value = "icode.domain.entity.update")
	public	EntityVO update(@RequestBody @Valid EntityEditDto entityEditDto, @PathVariable String id){
		Entity entity = new Entity();
		BeanUtils.copyProperties(entityEditDto, entity);
		entity.setId(id);
		entityService.merge(entity);

		EntityVO vo = initViewProperty(entity);
		return  vo;
	}

	/**
	 * 复制领域对象
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}/copy")
	public EntityVO copy(@PathVariable String id){

		Entity entity = entityService.copy(id);
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
  	@BusinessFuncMonitor(value = "icode.domain.entity.get")
	public  EntityVO get(@PathVariable String id) {

		Entity entity = entityService.find(id);
		if(entity == null){
			throw new ResourceNotFoundException("找不到指定的对象，请检查ID");
		}
		EntityVO vo = initViewProperty(entity);
		return vo;
	}

	/**
	 * 根据ID查询领域对象的详细信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询领域对象", httpMethod = "GET")
	@GetMapping(path="/{id}/detail")
	@BusinessFuncMonitor(value = "icode.domain.entity.get")
	public  EntityVO getDetail(@PathVariable String id) {

		Entity entity = entityService.find(id);
		if(entity == null){
			throw new ResourceNotFoundException("找不到指定的对象，请检查ID");
		}
		EntityVO vo = entityVOConvert.initDetail(entity);
		return vo;
	}

	/**
	 * 查询领域对象列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询领域对象列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.domain.entity.list")
	public PageContent<EntityVO> list(@RequestBody PageSearchRequest<EntityCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<Entity> page = entityService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<EntityVO> voList = new ArrayList<>();
		for(Entity entity : page.getContent()){
			voList.add(initViewProperty(entity));
		}

		PageContent<EntityVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(EntityCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

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

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("code" ,"实体代码");
        headMap.put("name" ,"实体名称");
        headMap.put("parentEntity" ,"父对象");
        headMap.put("aggregate" ,"所属聚合");
        headMap.put("domain" ,"所属领域");

        String title = new String("领域对象");
        String fileName = new String(("领域对象_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	public EntityVO initViewProperty(Entity entity){

	    EntityVO vo = new EntityVO();

        BeanUtils.copyProperties(entity, vo);


	    //初始化其他对象
	    initDomainPropertyGroup(vo, entity);
        return vo;

	}





	private void initDomainPropertyGroup(EntityVO entityVO, Entity entity){
	
		Domain domain = domainService.find(entity.getDomain());
		if(domain == null){
			return;
		}
		DomainVO domainVO = new DomainVO();
		BeanUtils.copyProperties(domain, domainVO);


		entityVO.setDomainVO(domainVO);

	}









}

