package net.aicoder.speedcloud.icode.business.project.controller;

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
import net.aicoder.speedcloud.icode.business.domain.domain.Domain;
import net.aicoder.speedcloud.icode.business.domain.service.DomainService;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
import net.aicoder.speedcloud.icode.business.project.domain.Component;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentDomainRelation;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationEditDto;
import net.aicoder.speedcloud.icode.business.project.service.ComponentDomainRelationService;
import net.aicoder.speedcloud.icode.business.project.service.ComponentService;
import net.aicoder.speedcloud.icode.business.project.valid.ComponentDomainRelationValidator;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentDomainRelationVO;
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
 * 管理组件-领域-关系
 * @author icode
 */
@Api(description = "组件-领域-关系", tags = "ComponentDomainRelation")
@RestController
@RequestMapping(value = "/icode/project/componentdomainrelation")
public class ComponentDomainRelationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentDomainRelationController.class);


	@Autowired
	private ComponentDomainRelationService componentDomainRelationService;

	@Autowired
	private ComponentService componentService;
	@Autowired
	private DomainService domainService;


	@Autowired
	private ComponentDomainRelationValidator componentDomainRelationValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(componentDomainRelationValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件-领域-关系
	 * @param componentDomainRelationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件-领域-关系", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "icode.project.componentdomainrelation.add")
	public ComponentDomainRelationVO add(@RequestBody @Valid ComponentDomainRelationAddDto componentDomainRelationAddDto){
		ComponentDomainRelation componentDomainRelation = new ComponentDomainRelation();
		BeanUtils.copyProperties(componentDomainRelationAddDto, componentDomainRelation);

		componentDomainRelationService.add(componentDomainRelation);

		return  initViewProperty(componentDomainRelation);
	}

	/**
	 * 删除组件-领域-关系,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件-领域-关系", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.project.componentdomainrelation.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete componentDomainRelation :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			componentDomainRelationService.delete(id);
		}

	}

	/**
	 * 更新组件-领域-关系
	 * @param componentDomainRelationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产组件-领域-关系(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.project.componentdomainrelation.update")
	public	ComponentDomainRelationVO update(@RequestBody @Valid ComponentDomainRelationEditDto componentDomainRelationEditDto, @PathVariable String id){
		ComponentDomainRelation componentDomainRelation = new ComponentDomainRelation();
		BeanUtils.copyProperties(componentDomainRelationEditDto, componentDomainRelation);
		componentDomainRelation.setId(id);
		componentDomainRelationService.merge(componentDomainRelation);

		ComponentDomainRelationVO vo = initViewProperty(componentDomainRelation);
		return  vo;
	}

	/**
	 * 根据ID查询组件-领域-关系
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件-领域-关系", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.project.componentdomainrelation.get")
	public  ComponentDomainRelationVO get(@PathVariable String id) {

		ComponentDomainRelation componentDomainRelation = componentDomainRelationService.find(id);

		ComponentDomainRelationVO vo = initViewProperty(componentDomainRelation);
		return vo;
	}

	/**
	 * 查询组件-领域-关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件-领域-关系列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.project.componentdomainrelation.list")
	public PageContent<ComponentDomainRelationVO> list(@RequestBody PageSearchRequest<ComponentDomainRelationCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<ComponentDomainRelation> page = componentDomainRelationService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ComponentDomainRelationVO> voList = new ArrayList<>();
		for(ComponentDomainRelation componentDomainRelation : page.getContent()){
			voList.add(initViewProperty(componentDomainRelation));
		}

		PageContent<ComponentDomainRelationVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件-领域-关系列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件-领域-关系列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ComponentDomainRelationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ComponentDomainRelationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ComponentDomainRelationVO> content = this.list(pageSearchRequest);

        List<ComponentDomainRelationVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ComponentDomainRelationVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("component" ,"系统组件");
        headMap.put("domain" ,"领域");
        headMap.put("relationType" ,"关系类型");

        String title = new String("组件-领域-关系");
        String fileName = new String(("组件-领域-关系_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ComponentDomainRelationVO initViewProperty(ComponentDomainRelation componentDomainRelation){

	    ComponentDomainRelationVO vo = new ComponentDomainRelationVO();
        BeanUtils.copyProperties(componentDomainRelation, vo);


	    //初始化其他对象
	    initComponentPropertyGroup(vo, componentDomainRelation);
	    initDomainPropertyGroup(vo, componentDomainRelation);
        return vo;

	}


	private void initComponentPropertyGroup(ComponentDomainRelationVO componentDomainRelationVO, ComponentDomainRelation componentDomainRelation){
	
		Component component = componentService.find(componentDomainRelation.getComponent());
		if(component == null){
			return;
		}
		ComponentVO componentVO = new ComponentVO();
		BeanUtils.copyProperties(component, componentVO);

		componentDomainRelationVO.setComponentVO(componentVO);

	}


	private void initDomainPropertyGroup(ComponentDomainRelationVO componentDomainRelationVO, ComponentDomainRelation componentDomainRelation){
	
		Domain domain = domainService.find(componentDomainRelation.getDomain());
		if(domain == null){
			return;
		}
		DomainVO domainVO = new DomainVO();
		BeanUtils.copyProperties(domain, domainVO);

		componentDomainRelationVO.setDomainVO(domainVO);

	}


}

