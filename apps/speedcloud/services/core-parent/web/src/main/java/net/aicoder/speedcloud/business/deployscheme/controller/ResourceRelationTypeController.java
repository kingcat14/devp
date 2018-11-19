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
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceRelationType;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeEditDto;
import net.aicoder.speedcloud.business.deployscheme.service.ResourceRelationTypeService;
import net.aicoder.speedcloud.business.deployscheme.valid.ResourceRelationTypeValidator;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceRelationTypeVO;
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
 * 管理资源关系类型
 * @author icode
 */
@Api(description = "资源关系类型", tags = "ResourceRelationType")
@RestController
@RequestMapping(value = "/speedcloud/deployscheme/resourcerelationtype")
public class ResourceRelationTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceRelationTypeController.class);


	@Autowired
	private ResourceRelationTypeService resourceRelationTypeService;



	@Autowired
	private ResourceRelationTypeValidator resourceRelationTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(resourceRelationTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增资源关系类型
	 * @param resourceRelationTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资源关系类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ResourceRelationTypeVO add(@RequestBody @Valid ResourceRelationTypeAddDto resourceRelationTypeAddDto){
		ResourceRelationType resourceRelationType = new ResourceRelationType();
		BeanUtils.copyProperties(resourceRelationTypeAddDto, resourceRelationType);

		resourceRelationTypeService.add(resourceRelationType);

		return  initViewProperty(resourceRelationType);
	}

	/**
	 * 删除资源关系类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资源关系类型", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete resourceRelationType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			resourceRelationTypeService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资源关系类型
	 * @param resourceRelationTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资源关系类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	ResourceRelationTypeVO update(@RequestBody @Valid ResourceRelationTypeEditDto resourceRelationTypeEditDto, @PathVariable Long id){
		ResourceRelationType resourceRelationType = new ResourceRelationType();
		BeanUtils.copyProperties(resourceRelationTypeEditDto, resourceRelationType);
		resourceRelationType.setId(id);
		resourceRelationTypeService.merge(resourceRelationType);

		ResourceRelationTypeVO vo = initViewProperty(resourceRelationType);
		return  vo;
	}

	/**
	 * 根据ID查询资源关系类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资源关系类型", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ResourceRelationTypeVO get(@PathVariable Long id) {

		ResourceRelationType resourceRelationType = resourceRelationTypeService.find(id);

		ResourceRelationTypeVO vo = initViewProperty(resourceRelationType);
		return vo;
	}

	/**
	 * 查询资源关系类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资源关系类型列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ResourceRelationTypeVO> list(@RequestBody PageSearchRequest<ResourceRelationTypeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<ResourceRelationType> page = resourceRelationTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ResourceRelationTypeVO> voList = new ArrayList<>();
		for(ResourceRelationType resourceRelationType : page.getContent()){
			voList.add(initViewProperty(resourceRelationType));
		}

		PageContent<ResourceRelationTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出资源关系类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出资源关系类型列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ResourceRelationTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ResourceRelationTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ResourceRelationTypeVO> content = this.list(pageSearchRequest);

        List<ResourceRelationTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ResourceRelationTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("icon" ,"图标");

        String title = new String("资源关系类型");
        String fileName = new String(("资源关系类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ResourceRelationTypeVO initViewProperty(ResourceRelationType resourceRelationType){

	    ResourceRelationTypeVO vo = new ResourceRelationTypeVO();
        BeanUtils.copyProperties(resourceRelationType, vo);


	    //初始化其他对象
        return vo;

	}


}

