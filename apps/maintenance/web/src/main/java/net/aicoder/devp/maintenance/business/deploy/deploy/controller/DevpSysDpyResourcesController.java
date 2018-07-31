package net.aicoder.devp.maintenance.business.deploy.deploy.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesEditDto;
import net.aicoder.devp.deploy.business.deploy.vo.DevpSysDpyResourcesVO;
import net.aicoder.devp.maintenance.business.deploy.deploy.service.DevpSysDpyResourcesRibbonService;
import net.aicoder.devp.maintenance.business.deploy.deploy.valid.DevpSysDpyResourcesValidator;
import net.aicoder.devp.security.business.security.service.SecurityUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	private DevpSysDpyResourcesRibbonService devpSysDpyResourcesRibbonService;

	@Autowired
	DevpSysDpyResourcesValidator devpSysDpyResourcesValidator;

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
	public DevpSysDpyResourcesVO add(@RequestBody DevpSysDpyResourcesAddDto devpSysDpyResourcesAddDto){
		devpSysDpyResourcesAddDto.setTid(SecurityUtil.getAccount().getTenantId());
		return  devpSysDpyResourcesRibbonService.add(devpSysDpyResourcesAddDto);
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
			devpSysDpyResourcesRibbonService.delete(Long.parseLong(id));
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
	public DevpSysDpyResourcesVO update(@RequestBody DevpSysDpyResourcesEditDto devpSysDpyResourcesEditDto, @ApiParam(value = "要查询的部署关联资源定义id") @PathVariable Long id){

		DevpSysDpyResourcesVO vo = devpSysDpyResourcesRibbonService.merge(id, devpSysDpyResourcesEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询部署关联资源定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署关联资源定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysDpyResourcesVO get(@ApiParam(value = "要查询的部署关联资源定义id") @PathVariable Long id) {

		DevpSysDpyResourcesVO vo = devpSysDpyResourcesRibbonService.find(id);
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


		PageContent<DevpSysDpyResourcesVO> pageContent = devpSysDpyResourcesRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}