package net.aicoder.devp.maintenance.business.deploy.deploy.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeEditDto;
import net.aicoder.devp.deploy.business.deploy.vo.DevpSysDpyInstSchemeVO;
import net.aicoder.devp.maintenance.business.deploy.deploy.service.DevpSysDpyInstSchemeRibbonService;
import net.aicoder.devp.maintenance.business.deploy.deploy.valid.DevpSysDpyInstSchemeValidator;
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
 * 管理资源实例所属的部署方案
 * @author icode
 */
@Api(description = "资源实例所属的部署方案", tags = "DevpSysDpyInstScheme")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyInstScheme")
public class DevpSysDpyInstSchemeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyInstSchemeController.class);


	@Autowired
	private DevpSysDpyInstSchemeRibbonService devpSysDpyInstSchemeRibbonService;

	@Autowired
	DevpSysDpyInstSchemeValidator devpSysDpyInstSchemeValidator;

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
	public DevpSysDpyInstSchemeVO add(@RequestBody DevpSysDpyInstSchemeAddDto devpSysDpyInstSchemeAddDto){
		devpSysDpyInstSchemeAddDto.setTid(SecurityUtil.getAccount().getTenantId());
		return  devpSysDpyInstSchemeRibbonService.add(devpSysDpyInstSchemeAddDto);
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
			devpSysDpyInstSchemeRibbonService.delete(Long.parseLong(id));
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
	public DevpSysDpyInstSchemeVO update(@RequestBody DevpSysDpyInstSchemeEditDto devpSysDpyInstSchemeEditDto, @ApiParam(value = "要查询的资源实例所属的部署方案id") @PathVariable Long id){

		DevpSysDpyInstSchemeVO vo = devpSysDpyInstSchemeRibbonService.merge(id, devpSysDpyInstSchemeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询资源实例所属的部署方案
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资源实例所属的部署方案", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysDpyInstSchemeVO get(@ApiParam(value = "要查询的资源实例所属的部署方案id") @PathVariable Long id) {

		DevpSysDpyInstSchemeVO vo = devpSysDpyInstSchemeRibbonService.find(id);
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


		PageContent<DevpSysDpyInstSchemeVO> pageContent = devpSysDpyInstSchemeRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
