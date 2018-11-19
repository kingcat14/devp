package net.aicoder.speedcloud.business.deployscheme.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.deployscheme.domain.Resource;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceRelation;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceRelationType;
import net.aicoder.speedcloud.business.deployscheme.domain.Scheme;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationEditDto;
import net.aicoder.speedcloud.business.deployscheme.service.ResourceRelationService;
import net.aicoder.speedcloud.business.deployscheme.service.ResourceRelationTypeService;
import net.aicoder.speedcloud.business.deployscheme.service.ResourceService;
import net.aicoder.speedcloud.business.deployscheme.service.SchemeService;
import net.aicoder.speedcloud.business.deployscheme.valid.ResourceRelationValidator;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceRelationTypeVO;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceRelationVO;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceVO;
import net.aicoder.speedcloud.business.deployscheme.vo.SchemeVO;
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
 * 管理方案资源间关系
 * @author icode
 */
@Api(description = "方案资源间关系", tags = "ResourceRelation")
@RestController
@RequestMapping(value = "/speedcloud/deployscheme/resourcerelation")
public class ResourceRelationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceRelationController.class);


	@Autowired
	private ResourceRelationService resourceRelationService;

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private ResourceRelationTypeService resourceRelationTypeService;
	@Autowired
	private SchemeService schemeService;


	@Autowired
	private ResourceRelationValidator resourceRelationValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(resourceRelationValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增方案资源间关系
	 * @param resourceRelationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增方案资源间关系", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ResourceRelationVO add(@RequestBody @Valid ResourceRelationAddDto resourceRelationAddDto){
		ResourceRelation resourceRelation = new ResourceRelation();
		BeanUtils.copyProperties(resourceRelationAddDto, resourceRelation);

		resourceRelationService.add(resourceRelation);

		return  initViewProperty(resourceRelation);
	}

	/**
	 * 删除方案资源间关系,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除方案资源间关系", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete resourceRelation :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			resourceRelationService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新方案资源间关系
	 * @param resourceRelationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产方案资源间关系(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	ResourceRelationVO update(@RequestBody @Valid ResourceRelationEditDto resourceRelationEditDto, @PathVariable Long id){
		ResourceRelation resourceRelation = new ResourceRelation();
		BeanUtils.copyProperties(resourceRelationEditDto, resourceRelation);
		resourceRelation.setId(id);
		resourceRelationService.merge(resourceRelation);

		ResourceRelationVO vo = initViewProperty(resourceRelation);
		return  vo;
	}

	/**
	 * 根据ID查询方案资源间关系
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询方案资源间关系", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ResourceRelationVO get(@PathVariable Long id) {

		ResourceRelation resourceRelation = resourceRelationService.find(id);

		ResourceRelationVO vo = initViewProperty(resourceRelation);
		return vo;
	}

	/**
	 * 查询方案资源间关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询方案资源间关系列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ResourceRelationVO> list(@RequestBody PageSearchRequest<ResourceRelationCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<ResourceRelation> page = resourceRelationService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ResourceRelationVO> voList = new ArrayList<>();
		for(ResourceRelation resourceRelation : page.getContent()){
			voList.add(initViewProperty(resourceRelation));
		}

		PageContent<ResourceRelationVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出方案资源间关系列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出方案资源间关系列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ResourceRelationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ResourceRelationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ResourceRelationVO> content = this.list(pageSearchRequest);

        List<ResourceRelationVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ResourceRelationVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("resource" ,"主资源");
            headMap.put("refResource" ,"关联资源");
            headMap.put("type" ,"对应关系类型");
            headMap.put("code" ,"对应关系代码");
            headMap.put("name" ,"对应关系名称");
            headMap.put("alias" ,"对应关系别名");
            headMap.put("description" ,"对应关系描述");
            headMap.put("scheme" ,"部署方案编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("direction" ,"对应关系方向");

        String title = new String("方案资源间关系");
        String fileName = new String(("方案资源间关系_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ResourceRelationVO initViewProperty(ResourceRelation resourceRelation){

	    ResourceRelationVO vo = new ResourceRelationVO();
        BeanUtils.copyProperties(resourceRelation, vo);


	    //初始化其他对象
	    initResourcePropertyGroup(vo, resourceRelation);
	    initRefResourcePropertyGroup(vo, resourceRelation);
	    initTypePropertyGroup(vo, resourceRelation);
	    initSchemePropertyGroup(vo, resourceRelation);
        return vo;

	}


	private void initResourcePropertyGroup(ResourceRelationVO resourceRelationVO, ResourceRelation resourceRelation){
	
		Resource resource = resourceService.find(resourceRelation.getResource());
		if(resource == null){
			return;
		}
		ResourceVO resourceVO = new ResourceVO();
		BeanUtils.copyProperties(resource, resourceVO);

		resourceRelationVO.setResourceVO(resourceVO);

	}


	private void initRefResourcePropertyGroup(ResourceRelationVO resourceRelationVO, ResourceRelation resourceRelation){
	
		Resource refResource = resourceService.find(resourceRelation.getRefResource());
		if(refResource == null){
			return;
		}
		ResourceVO refResourceVO = new ResourceVO();
		BeanUtils.copyProperties(refResource, refResourceVO);

		resourceRelationVO.setRefResourceVO(refResourceVO);

	}


	private void initTypePropertyGroup(ResourceRelationVO resourceRelationVO, ResourceRelation resourceRelation){
	
		ResourceRelationType type = resourceRelationTypeService.find(resourceRelation.getType());
		if(type == null){
			return;
		}
		ResourceRelationTypeVO typeVO = new ResourceRelationTypeVO();
		BeanUtils.copyProperties(type, typeVO);

		resourceRelationVO.setTypeVO(typeVO);

	}


	private void initSchemePropertyGroup(ResourceRelationVO resourceRelationVO, ResourceRelation resourceRelation){
	
		Scheme scheme = schemeService.find(resourceRelation.getScheme());
		if(scheme == null){
			return;
		}
		SchemeVO schemeVO = new SchemeVO();
		BeanUtils.copyProperties(scheme, schemeVO);

		resourceRelationVO.setSchemeVO(schemeVO);

	}


}

