package net.aicoder.speedcloud.icode.business.project.controller;

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
import net.aicoder.speedcloud.icode.business.project.domain.ComponentType;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeEditDto;
import net.aicoder.speedcloud.icode.business.project.service.ComponentTypeService;
import net.aicoder.speedcloud.icode.business.project.valid.ComponentTypeValidator;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentTypeVO;
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
 * 管理组件类型
 * @author icode
 */
@Api(description = "组件类型", tags = "ComponentType")
@RestController
@RequestMapping(value = "/icode/project/componenttype")
public class ComponentTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentTypeController.class);


	@Autowired
	private ComponentTypeService componentTypeService;



	@Autowired
	private ComponentTypeValidator componentTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(componentTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件类型
	 * @param componentTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "icode.project.componenttype.add", count = true)
	public ComponentTypeVO add(@RequestBody @Valid ComponentTypeAddDto componentTypeAddDto){
		ComponentType componentType = new ComponentType();
		BeanUtils.copyProperties(componentTypeAddDto, componentType);

		componentTypeService.add(componentType);

		return  initViewProperty(componentType);
	}

	/**
	 * 删除组件类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件类型", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.project.componenttype.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete componentType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			componentTypeService.delete(id);
		}

	}

	/**
	 * 更新组件类型
	 * @param componentTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改组件类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.project.componenttype.update", count = true)
	public	ComponentTypeVO update(@RequestBody @Valid ComponentTypeEditDto componentTypeEditDto, @PathVariable String id){
		ComponentType componentType = componentTypeService.find(id);
		BeanUtils.copyProperties(componentTypeEditDto, componentType);
		componentType.setId(id);
		componentTypeService.merge(componentType);

		ComponentTypeVO vo = initViewProperty(componentType);
		return  vo;
	}

	/**
	 * 根据ID查询组件类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件类型", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.project.componenttype.get")
	public  ComponentTypeVO get(@PathVariable String id) {

		ComponentType componentType = componentTypeService.find(id);
		if(componentType == null){
			throw new ResourceNotFoundException("找不到指定的组件类型，请检查ID");
		}
		ComponentTypeVO vo = initViewProperty(componentType);
		return vo;
	}

	/**
	 * 查询组件类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件类型列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.project.componenttype.list")
	public PageContent<ComponentTypeVO> list(@RequestBody PageSearchRequest<ComponentTypeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<ComponentType> page = componentTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ComponentTypeVO> voList = new ArrayList<>();
		for(ComponentType componentType : page.getContent()){
			voList.add(initViewProperty(componentType));
		}

		PageContent<ComponentTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件类型列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ComponentTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ComponentTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ComponentTypeVO> content = this.list(pageSearchRequest);

        List<ComponentTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ComponentTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("idx" ,"排序");
        headMap.put("code" ,"代码");
        headMap.put("name" ,"名称");
        headMap.put("category" ,"种类");
        headMap.put("icon" ,"默认图标");

        String title = new String("组件类型");
        String fileName = new String(("组件类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ComponentTypeVO initViewProperty(ComponentType componentType){

	    ComponentTypeVO vo = new ComponentTypeVO();
        BeanUtils.copyProperties(componentType, vo);


	    //初始化其他对象
        return vo;

	}


}

