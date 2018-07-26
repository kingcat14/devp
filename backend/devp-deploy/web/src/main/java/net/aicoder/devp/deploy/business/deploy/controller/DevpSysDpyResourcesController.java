package net.aicoder.devp.deploy.business.deploy.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyResources;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesEditDto;
import net.aicoder.devp.deploy.business.deploy.service.DevpSysDpyResourcesService;
import net.aicoder.devp.deploy.business.deploy.valid.DevpSysDpyResourcesValidator;
import net.aicoder.devp.deploy.business.deploy.vo.DevpSysDpyResourcesVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理部署关联资源定义
 * @author icode
 */
@Api(description = "部署关联资源定义", tags = "DevpSysDpyResources")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyResources")
public class DevpSysDpyResourcesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesController.class);


	@Autowired
	private DevpSysDpyResourcesService devpSysDpyResourcesService;


	@Autowired
	private DevpSysDpyResourcesValidator devpSysDpyResourcesValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResourcesValidator);
	}

	/**
	 * 新增部署关联资源定义
	 * @param devpSysDpyResourcesAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署关联资源定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResourcesVO add(@RequestBody @Valid DevpSysDpyResourcesAddDto devpSysDpyResourcesAddDto){
		DevpSysDpyResources devpSysDpyResources = new DevpSysDpyResources();
		BeanUtils.copyProperties(devpSysDpyResourcesAddDto, devpSysDpyResources);

		devpSysDpyResourcesService.add(devpSysDpyResources);

		return  initViewProperty(devpSysDpyResources);
	}

	/**
	 * 删除部署关联资源定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署关联资源定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResources :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResourcesService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署关联资源定义
	 * @param devpSysDpyResourcesEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署关联资源定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyResourcesVO update(@RequestBody @Valid DevpSysDpyResourcesEditDto devpSysDpyResourcesEditDto, @PathVariable Long id){
		DevpSysDpyResources devpSysDpyResources = new DevpSysDpyResources();
		BeanUtils.copyProperties(devpSysDpyResourcesEditDto, devpSysDpyResources);
		devpSysDpyResources.setId(id);
		devpSysDpyResourcesService.merge(devpSysDpyResources);

		DevpSysDpyResourcesVO vo = initViewProperty(devpSysDpyResources);
		return  vo;
	}

	/**
	 * 根据ID查询部署关联资源定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署关联资源定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResourcesVO get(@PathVariable Long id) {

		DevpSysDpyResources devpSysDpyResources = devpSysDpyResourcesService.find(id);

		DevpSysDpyResourcesVO vo = initViewProperty(devpSysDpyResources);
		return vo;
	}

	/**
	 * 查询部署关联资源定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署关联资源定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResourcesVO> list(@RequestBody PageSearchRequest<DevpSysDpyResourcesCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyResources> page = devpSysDpyResourcesService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyResourcesVO> voList = new ArrayList<>();
		for(DevpSysDpyResources devpSysDpyResources : page.getContent()){
			voList.add(initViewProperty(devpSysDpyResources));
		}

		PageContent<DevpSysDpyResourcesVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpSysDpyResourcesVO initViewProperty(DevpSysDpyResources devpSysDpyResources){
	    DevpSysDpyResourcesVO vo = new DevpSysDpyResourcesVO();

        BeanUtils.copyProperties(devpSysDpyResources, vo);

	    //初始化其他对象
        return vo;
	}


}
