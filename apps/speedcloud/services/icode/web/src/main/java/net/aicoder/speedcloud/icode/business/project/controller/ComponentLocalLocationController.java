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
import net.aicoder.speedcloud.icode.business.project.domain.Component;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentLocalLocation;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationEditDto;
import net.aicoder.speedcloud.icode.business.project.service.ComponentLocalLocationService;
import net.aicoder.speedcloud.icode.business.project.service.ComponentService;
import net.aicoder.speedcloud.icode.business.project.valid.ComponentLocalLocationValidator;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentLocalLocationVO;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
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
 * 管理组件本地路径
 * @author icode
 */
@Api(description = "组件本地路径", tags = "ComponentLocalLocation")
@RestController
@RequestMapping(value = "/icode/project/componentlocallocation")
public class ComponentLocalLocationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentLocalLocationController.class);


	@Autowired
	private ComponentLocalLocationService componentLocalLocationService;

	@Autowired
	private ComponentService componentService;


	@Autowired
	private ComponentLocalLocationValidator componentLocalLocationValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(componentLocalLocationValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件本地路径
	 * @param componentLocalLocationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件本地路径", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "icode.project.componentlocallocation.add", count = true)
	public ComponentLocalLocationVO add(@RequestBody @Valid ComponentLocalLocationAddDto componentLocalLocationAddDto){
		ComponentLocalLocation componentLocalLocation = new ComponentLocalLocation();
		BeanUtils.copyProperties(componentLocalLocationAddDto, componentLocalLocation);

		componentLocalLocationService.add(componentLocalLocation);

		return  initViewProperty(componentLocalLocation);
	}

	/**
	 * 删除组件本地路径,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件本地路径", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.project.componentlocallocation.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete componentLocalLocation :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			componentLocalLocationService.delete(id);
		}

	}

	/**
	 * 更新组件本地路径
	 * @param componentLocalLocationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改组件本地路径(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.project.componentlocallocation.update", count = true)
	public	ComponentLocalLocationVO update(@RequestBody @Valid ComponentLocalLocationEditDto componentLocalLocationEditDto, @PathVariable String id){
		ComponentLocalLocation componentLocalLocation = componentLocalLocationService.find(id);
		BeanUtils.copyProperties(componentLocalLocationEditDto, componentLocalLocation);
		componentLocalLocation.setId(id);
		componentLocalLocationService.merge(componentLocalLocation);

		ComponentLocalLocationVO vo = initViewProperty(componentLocalLocation);
		return  vo;
	}

	/**
	 * 根据ID查询组件本地路径
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件本地路径", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.project.componentlocallocation.get")
	public  ComponentLocalLocationVO get(@PathVariable String id) {

		ComponentLocalLocation componentLocalLocation = componentLocalLocationService.find(id);
		if(componentLocalLocation == null){
			throw new ResourceNotFoundException("找不到指定的组件本地路径，请检查ID");
		}
		ComponentLocalLocationVO vo = initViewProperty(componentLocalLocation);
		return vo;
	}

	/**
	 * 查询组件本地路径列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件本地路径列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.project.componentlocallocation.list")
	public PageContent<ComponentLocalLocationVO> list(@RequestBody PageSearchRequest<ComponentLocalLocationCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<ComponentLocalLocation> page = componentLocalLocationService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ComponentLocalLocationVO> voList = new ArrayList<>();
		for(ComponentLocalLocation componentLocalLocation : page.getContent()){
			voList.add(initViewProperty(componentLocalLocation));
		}

		PageContent<ComponentLocalLocationVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件本地路径列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件本地路径列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ComponentLocalLocationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ComponentLocalLocationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ComponentLocalLocationVO> content = this.list(pageSearchRequest);

        List<ComponentLocalLocationVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ComponentLocalLocationVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("component" ,"组件");
        headMap.put("location" ,"本地路径");

        String title = new String("组件本地路径");
        String fileName = new String(("组件本地路径_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ComponentLocalLocationVO initViewProperty(ComponentLocalLocation componentLocalLocation){

	    ComponentLocalLocationVO vo = new ComponentLocalLocationVO();
        BeanUtils.copyProperties(componentLocalLocation, vo);


	    //初始化其他对象
	    initComponentPropertyGroup(vo, componentLocalLocation);
        return vo;

	}

	private void initComponentPropertyGroup(ComponentLocalLocationVO componentLocalLocationVO, ComponentLocalLocation componentLocalLocation){
	
		Component component = componentService.find(componentLocalLocation.getComponent());
		if(component == null){
			return;
		}
		ComponentVO componentVO = new ComponentVO();
		BeanUtils.copyProperties(component, componentVO);

		componentLocalLocationVO.setComponentVO(componentVO);

	}

}

