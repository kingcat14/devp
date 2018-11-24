package net.aicoder.speedcloud.console.business.icode.project.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentLocalLocationRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.valid.ComponentLocalLocationValidator;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationEditDto;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentLocalLocationVO;
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
 * 管理组件本地路径
 * @author icode
 */
@Api(description = "组件本地路径", tags = "ComponentLocalLocation")
@RestController
@RequestMapping(value = "/icode/project/componentlocallocation")
public class ComponentLocalLocationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentLocalLocationController.class);

    @Autowired
	private SaaSUtil saaSUtil;
	
    @Autowired
	private ComponentLocalLocationRibbonService componentLocalLocationRibbonService;

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
  	@SaaSAnnotation()
	public ComponentLocalLocationVO add(@RequestBody @Valid ComponentLocalLocationAddDto componentLocalLocationAddDto){
	
		return  componentLocalLocationRibbonService.add(componentLocalLocationAddDto);
	}

	/**
	 * 删除组件本地路径,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件本地路径", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete componentLocalLocation :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			componentLocalLocationRibbonService.delete(id);
		}

	}

	/**
	 * 更新组件本地路径
	 * @param componentLocalLocationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产组件本地路径(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public ComponentLocalLocationVO update(@RequestBody @Valid ComponentLocalLocationEditDto componentLocalLocationEditDto, @ApiParam(value = "要查询的组件本地路径id") @PathVariable String id){

		ComponentLocalLocationVO vo = componentLocalLocationRibbonService.merge(id, componentLocalLocationEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询组件本地路径
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件本地路径", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public ComponentLocalLocationVO get(@ApiParam(value = "要查询的组件本地路径id") @PathVariable String id) {

		ComponentLocalLocationVO vo = componentLocalLocationRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询组件本地路径列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件本地路径列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = ComponentLocalLocationCondition.class)
	public PageContent<ComponentLocalLocationVO> list(@RequestBody @Valid PageSearchRequest<ComponentLocalLocationCondition> pageSearchRequest){

		PageContent<ComponentLocalLocationVO> pageContent = componentLocalLocationRibbonService.list(pageSearchRequest);
		for(ComponentLocalLocationVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

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
    public void export(ComponentLocalLocationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

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

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("component" ,"组件");
            headMap.put("location" ,"本地路径");

        String title = new String("组件本地路径");
        String fileName = new String(("组件本地路径_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ComponentLocalLocationVO initViewProperty( ComponentLocalLocationVO vo){


	   
        return vo;

	}
}
