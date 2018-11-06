package com.yunkang.saas.platform.manage.business.platform.resource.controller;


import com.yunkang.saas.bootstrap.application.business.resource.domain.Resource;
import com.yunkang.saas.bootstrap.application.business.resource.dto.ResourceAddDto;
import com.yunkang.saas.bootstrap.application.business.resource.dto.ResourceCondition;
import com.yunkang.saas.bootstrap.application.business.resource.dto.ResourceEditDto;
import com.yunkang.saas.bootstrap.application.business.resource.service.ResourceService;
import com.yunkang.saas.bootstrap.application.business.resource.vo.ResourceVO;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.manage.business.platform.resource.valid.ResourceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理资源
 * @author icode
 */
@RestController
@RequestMapping(value = "/platform/security/resource")
public class ResourceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);


	@Autowired
	private ResourceService resourceService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new ResourceValidator());
	}

	/**
	 * 新增资源
	 * @param resourceAddDto
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ResourceVO add(@RequestBody @Valid ResourceAddDto resourceAddDto){
		Resource resource = new Resource();
		BeanUtils.copyProperties(resourceAddDto, resource);

		resourceService.add(resource);


		return  initViewProperty(resource);
	}

	/**
	 * 删除资源,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete resource :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			resourceService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资源
	 * @param resourceEditDto
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public	ResourceVO update(@RequestBody @Valid ResourceEditDto resourceEditDto, @PathVariable Long id){
		Resource resource = new Resource();
		BeanUtils.copyProperties(resourceEditDto, resource);
		resource.setId(id);
		resourceService.merge(resource);

		ResourceVO vo = initViewProperty(resource);
		return  vo;
	}

	/**
	 * 根据ID查询资源
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public  ResourceVO get(@PathVariable Long id) {

		Resource resource = resourceService.find(id);

		ResourceVO vo = initViewProperty(resource);
		return vo;
	}

	/**
	 * 查询资源列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<ResourceVO> list(@RequestBody PageSearchRequest<ResourceCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<Resource> page = resourceService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ResourceVO> voList = new ArrayList<>();
		for(Resource resource : page.getContent()){
			voList.add(initViewProperty(resource));
		}

		PageContent<ResourceVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private ResourceVO initViewProperty(Resource resource){
	    ResourceVO vo = new ResourceVO();

        BeanUtils.copyProperties(resource, vo);

	    //初始化其他对象
        return vo;
	}




}
