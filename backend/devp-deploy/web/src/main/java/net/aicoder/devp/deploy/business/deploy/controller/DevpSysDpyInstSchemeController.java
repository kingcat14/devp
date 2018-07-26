package net.aicoder.devp.deploy.business.deploy.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyInstScheme;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeEditDto;
import net.aicoder.devp.deploy.business.deploy.service.DevpSysDpyInstSchemeService;
import net.aicoder.devp.deploy.business.deploy.valid.DevpSysDpyInstSchemeValidator;
import net.aicoder.devp.deploy.business.deploy.vo.DevpSysDpyInstSchemeVO;

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
 * 管理资源实例所属的部署方案
 * @author icode
 */
@Api(description = "资源实例所属的部署方案", tags = "DevpSysDpyInstScheme")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyInstScheme")
public class DevpSysDpyInstSchemeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyInstSchemeController.class);


	@Autowired
	private DevpSysDpyInstSchemeService devpSysDpyInstSchemeService;


	@Autowired
	private DevpSysDpyInstSchemeValidator devpSysDpyInstSchemeValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyInstSchemeValidator);
	}

	/**
	 * 新增资源实例所属的部署方案
	 * @param devpSysDpyInstSchemeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资源实例所属的部署方案", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyInstSchemeVO add(@RequestBody @Valid DevpSysDpyInstSchemeAddDto devpSysDpyInstSchemeAddDto){
		DevpSysDpyInstScheme devpSysDpyInstScheme = new DevpSysDpyInstScheme();
		BeanUtils.copyProperties(devpSysDpyInstSchemeAddDto, devpSysDpyInstScheme);

		devpSysDpyInstSchemeService.add(devpSysDpyInstScheme);

		return  initViewProperty(devpSysDpyInstScheme);
	}

	/**
	 * 删除资源实例所属的部署方案,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资源实例所属的部署方案", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyInstScheme :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyInstSchemeService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资源实例所属的部署方案
	 * @param devpSysDpyInstSchemeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资源实例所属的部署方案(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyInstSchemeVO update(@RequestBody @Valid DevpSysDpyInstSchemeEditDto devpSysDpyInstSchemeEditDto, @PathVariable Long id){
		DevpSysDpyInstScheme devpSysDpyInstScheme = new DevpSysDpyInstScheme();
		BeanUtils.copyProperties(devpSysDpyInstSchemeEditDto, devpSysDpyInstScheme);
		devpSysDpyInstScheme.setId(id);
		devpSysDpyInstSchemeService.merge(devpSysDpyInstScheme);

		DevpSysDpyInstSchemeVO vo = initViewProperty(devpSysDpyInstScheme);
		return  vo;
	}

	/**
	 * 根据ID查询资源实例所属的部署方案
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资源实例所属的部署方案", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyInstSchemeVO get(@PathVariable Long id) {

		DevpSysDpyInstScheme devpSysDpyInstScheme = devpSysDpyInstSchemeService.find(id);

		DevpSysDpyInstSchemeVO vo = initViewProperty(devpSysDpyInstScheme);
		return vo;
	}

	/**
	 * 查询资源实例所属的部署方案列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资源实例所属的部署方案列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyInstSchemeVO> list(@RequestBody PageSearchRequest<DevpSysDpyInstSchemeCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyInstScheme> page = devpSysDpyInstSchemeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyInstSchemeVO> voList = new ArrayList<>();
		for(DevpSysDpyInstScheme devpSysDpyInstScheme : page.getContent()){
			voList.add(initViewProperty(devpSysDpyInstScheme));
		}

		PageContent<DevpSysDpyInstSchemeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpSysDpyInstSchemeVO initViewProperty(DevpSysDpyInstScheme devpSysDpyInstScheme){
	    DevpSysDpyInstSchemeVO vo = new DevpSysDpyInstSchemeVO();

        BeanUtils.copyProperties(devpSysDpyInstScheme, vo);

	    //初始化其他对象
        return vo;
	}


}
