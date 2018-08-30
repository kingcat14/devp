package com.kingzoo.kingcat.project.icode4.business.security.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.project.icode4.business.security.dto.ResourceAddDto;
import com.kingzoo.kingcat.project.icode4.business.security.dto.ResourceDto;
import com.kingzoo.kingcat.project.icode4.business.security.dto.ResourceEditDto;
import com.kingzoo.kingcat.project.icode4.business.security.dto.ResourceSearchDto;
import com.kingzoo.kingcat.project.icode4.business.security.entity.Resource;
import com.kingzoo.kingcat.project.icode4.business.security.service.ResourceService;
import com.kingzoo.kingcat.project.icode4.business.security.valid.ResourceValidator;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import org.apache.commons.collections4.CollectionUtils;
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
 */
@RestController
@RequestMapping(value = "/security/resource")
public class ResourceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ResourceService resourceService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		if(webDataBinder.getTarget() instanceof ResourceSearchDto){
			return;
		}
		webDataBinder.replaceValidators(new ResourceValidator());

	}

	/**
	 * 新增资源
	 * @param resourceAddDto
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ResourceDto add(@RequestBody @Valid ResourceAddDto resourceAddDto){

		Resource resource = resourceService.add(resourceAddDto);

		return  initViewProperty(resource);
	}

	/**
	 * 删除资源
	 * @param id 记录ID
	 */
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable String id, @RequestParam(required = false) String handlerId){

	    LOGGER.debug("{} trye delete resource :{}", handlerId, id);
		resourceService.delete(id, handlerId);

	}

	/**
	 * 更新资源
	 * @param resourceEditDto
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public	ResourceDto update(@RequestBody @Valid ResourceEditDto resourceEditDto, @PathVariable String id){
		resourceEditDto.setId(id);
		Resource resource = resourceService.update(resourceEditDto);


		return  initViewProperty(resource);
	}

	/**
	 * 根据ID查询资源
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public  ResourceDto get(@PathVariable String id) {

		Resource resource = resourceService.find(id);

		return initViewProperty(resource);
	}

	/**
     * 查询资源列表
     * @param resourceSearchDto
     * @return
     */
    @PostMapping("/list")
    public PageContent<ResourceDto> list(@RequestBody ResourceSearchDto resourceSearchDto){

        Page<Resource> pageData = resourceService.search(resourceSearchDto);


        List<ResourceDto> resultList = new ArrayList<>();
        for(Resource resource : pageData.getContent()){
            resultList.add(this.initViewProperty(resource));
        }

        PageContent<ResourceDto> result = new PageContent<>(resultList, pageData.getTotalElements());
        
        return result;

    }


	/**
	 * Method getChildNodes.
	 * @param node String
	 * @param id int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(params = "method=getChildNodes", headers="Accept=application/xml, application/json")
	public @ResponseBody
	List<ResourceTreeNode> getChildNodes(
			@RequestParam(required=false,value="node") String node,
			@RequestParam(required=false,value="id",defaultValue = "-1") String id
	){

		ResourceSearchDto resourceSearchDto = new ResourceSearchDto();
		resourceSearchDto.setParentId(id);
		resourceSearchDto.setPage(0);
		resourceSearchDto.setPageSize(10000);

		Page<Resource> resourcePage = resourceService.search(resourceSearchDto);
		LOGGER.info(resourcePage.toString());

		List<ResourceTreeNode> resourceTreeNodes = new ArrayList<>();
		if(resourcePage!=null && CollectionUtils.isNotEmpty(resourcePage.getContent())){
			for(Resource resource : resourcePage.getContent()){
				resourceTreeNodes.add(new ResourceTreeNode(resource));
			}
		}

		return resourceTreeNodes;
	}

    private ResourceDto initViewProperty(Resource resource){
        ResourceDto dto = new ResourceDto();
        if(resource == null){
            return dto;
        }
        BeanUtils.copyProperties(resource, dto);

	    //初始化其他对象
        return dto;
	}

}