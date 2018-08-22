package net.aicoder.devp.business.deploy.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyScheme;
import net.aicoder.devp.business.deploy.dto.DevpSysDpySchemeAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpySchemeCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpySchemeEditDto;
import net.aicoder.devp.business.deploy.service.DevpSysDpySchemeService;
import net.aicoder.devp.business.deploy.valid.DevpSysDpySchemeValidator;
import net.aicoder.devp.business.deploy.vo.DevpSysDpySchemeVO;

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
 * 管理产品部署方案
 * @author icode
 */
@Api(description = "产品部署方案", tags = "DevpSysDpyScheme")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyScheme")
public class DevpSysDpySchemeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpySchemeController.class);


	@Autowired
	private DevpSysDpySchemeService devpSysDpySchemeService;


	@Autowired
	private DevpSysDpySchemeValidator devpSysDpySchemeValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpySchemeValidator);
	}

	/**
	 * 新增产品部署方案
	 * @param devpSysDpySchemeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品部署方案", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpySchemeVO add(@RequestBody @Valid DevpSysDpySchemeAddDto devpSysDpySchemeAddDto){
		DevpSysDpyScheme devpSysDpyScheme = new DevpSysDpyScheme();
		BeanUtils.copyProperties(devpSysDpySchemeAddDto, devpSysDpyScheme);

		devpSysDpySchemeService.add(devpSysDpyScheme);

		return  initViewProperty(devpSysDpyScheme);
	}

	/**
	 * 删除产品部署方案,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品部署方案", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyScheme :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpySchemeService.delete(Long.valueOf(id));
		}

	}

	/**
	 * 更新产品部署方案
	 * @param devpSysDpySchemeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品部署方案(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpySchemeVO update(@RequestBody @Valid DevpSysDpySchemeEditDto devpSysDpySchemeEditDto, @PathVariable Long id){
		DevpSysDpyScheme devpSysDpyScheme = new DevpSysDpyScheme();
		BeanUtils.copyProperties(devpSysDpySchemeEditDto, devpSysDpyScheme);
		devpSysDpyScheme.setId(id);
		devpSysDpySchemeService.merge(devpSysDpyScheme);

		DevpSysDpySchemeVO vo = initViewProperty(devpSysDpyScheme);
		return  vo;
	}

	/**
	 * 根据ID查询产品部署方案
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品部署方案", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpySchemeVO get(@PathVariable Long id) {

		DevpSysDpyScheme devpSysDpyScheme = devpSysDpySchemeService.find(id);

		DevpSysDpySchemeVO vo = initViewProperty(devpSysDpyScheme);
		return vo;
	}

	/**
	 * 查询产品部署方案列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品部署方案列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpySchemeVO> list(@RequestBody PageSearchRequest<DevpSysDpySchemeCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyScheme> page = devpSysDpySchemeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpySchemeVO> voList = new ArrayList<>();
		for(DevpSysDpyScheme devpSysDpyScheme : page.getContent()){
			voList.add(initViewProperty(devpSysDpyScheme));
		}

		PageContent<DevpSysDpySchemeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpSysDpySchemeVO initViewProperty(DevpSysDpyScheme devpSysDpyScheme){
	    DevpSysDpySchemeVO vo = new DevpSysDpySchemeVO();

        BeanUtils.copyProperties(devpSysDpyScheme, vo);

	    //初始化其他对象
        return vo;
	}


}
