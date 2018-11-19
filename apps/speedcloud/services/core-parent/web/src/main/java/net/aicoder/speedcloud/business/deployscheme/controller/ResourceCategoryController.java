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
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceCategory;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryEditDto;
import net.aicoder.speedcloud.business.deployscheme.service.ResourceCategoryService;
import net.aicoder.speedcloud.business.deployscheme.valid.ResourceCategoryValidator;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceCategoryVO;
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
 * 管理部署资源类别
 * @author icode
 */
@Api(description = "部署资源类别", tags = "ResourceCategory")
@RestController
@RequestMapping(value = "/speedcloud/deployscheme/resourcecategory")
public class ResourceCategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCategoryController.class);


	@Autowired
	private ResourceCategoryService resourceCategoryService;



	@Autowired
	private ResourceCategoryValidator resourceCategoryValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(resourceCategoryValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署资源类别
	 * @param resourceCategoryAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署资源类别", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ResourceCategoryVO add(@RequestBody @Valid ResourceCategoryAddDto resourceCategoryAddDto){
		ResourceCategory resourceCategory = new ResourceCategory();
		BeanUtils.copyProperties(resourceCategoryAddDto, resourceCategory);

		resourceCategoryService.add(resourceCategory);

		return  initViewProperty(resourceCategory);
	}

	/**
	 * 删除部署资源类别,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署资源类别", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete resourceCategory :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			resourceCategoryService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署资源类别
	 * @param resourceCategoryEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署资源类别(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	ResourceCategoryVO update(@RequestBody @Valid ResourceCategoryEditDto resourceCategoryEditDto, @PathVariable Long id){
		ResourceCategory resourceCategory = new ResourceCategory();
		BeanUtils.copyProperties(resourceCategoryEditDto, resourceCategory);
		resourceCategory.setId(id);
		resourceCategoryService.merge(resourceCategory);

		ResourceCategoryVO vo = initViewProperty(resourceCategory);
		return  vo;
	}

	/**
	 * 根据ID查询部署资源类别
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署资源类别", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ResourceCategoryVO get(@PathVariable Long id) {

		ResourceCategory resourceCategory = resourceCategoryService.find(id);

		ResourceCategoryVO vo = initViewProperty(resourceCategory);
		return vo;
	}

	/**
	 * 查询部署资源类别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署资源类别列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ResourceCategoryVO> list(@RequestBody PageSearchRequest<ResourceCategoryCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<ResourceCategory> page = resourceCategoryService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ResourceCategoryVO> voList = new ArrayList<>();
		for(ResourceCategory resourceCategory : page.getContent()){
			voList.add(initViewProperty(resourceCategory));
		}

		PageContent<ResourceCategoryVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署资源类别列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署资源类别列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ResourceCategoryCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ResourceCategoryCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ResourceCategoryVO> content = this.list(pageSearchRequest);

        List<ResourceCategoryVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ResourceCategoryVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("icon" ,"图标");

        String title = new String("部署资源类别");
        String fileName = new String(("部署资源类别_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ResourceCategoryVO initViewProperty(ResourceCategory resourceCategory){

	    ResourceCategoryVO vo = new ResourceCategoryVO();
        BeanUtils.copyProperties(resourceCategory, vo);


	    //初始化其他对象
        return vo;

	}


}

