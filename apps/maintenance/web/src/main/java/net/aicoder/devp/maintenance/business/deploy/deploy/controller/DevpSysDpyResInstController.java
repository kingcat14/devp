package net.aicoder.devp.maintenance.business.deploy.deploy.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstEditDto;
import net.aicoder.devp.deploy.business.deploy.vo.DevpSysDpyResInstVO;
import net.aicoder.devp.maintenance.business.deploy.deploy.service.DevpSysDpyResInstRibbonService;
import net.aicoder.devp.maintenance.business.deploy.deploy.valid.DevpSysDpyResInstValidator;
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
 * 管理部署关联资源实例定义
 * @author icode
 */
@Api(description = "部署关联资源实例定义", tags = "DevpSysDpyResInst")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyResInst")
public class DevpSysDpyResInstController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResInstController.class);


	@Autowired
	private DevpSysDpyResInstRibbonService devpSysDpyResInstRibbonService;

	@Autowired
	DevpSysDpyResInstValidator devpSysDpyResInstValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResInstValidator);
	}

	/**
	 * 新增部署关联资源实例定义
	 * @param devpSysDpyResInstAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署关联资源实例定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResInstVO add(@RequestBody DevpSysDpyResInstAddDto devpSysDpyResInstAddDto){
		devpSysDpyResInstAddDto.setTid(SecurityUtil.getAccount().getTenantId());
		return  devpSysDpyResInstRibbonService.add(devpSysDpyResInstAddDto);
	}

	/**
	 * 删除部署关联资源实例定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署关联资源实例定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResInst :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResInstRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署关联资源实例定义
	 * @param devpSysDpyResInstEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署关联资源实例定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysDpyResInstVO update(@RequestBody DevpSysDpyResInstEditDto devpSysDpyResInstEditDto, @ApiParam(value = "要查询的部署关联资源实例定义id") @PathVariable Long id){

		DevpSysDpyResInstVO vo = devpSysDpyResInstRibbonService.merge(id, devpSysDpyResInstEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询部署关联资源实例定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署关联资源实例定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysDpyResInstVO get(@ApiParam(value = "要查询的部署关联资源实例定义id") @PathVariable Long id) {

		DevpSysDpyResInstVO vo = devpSysDpyResInstRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询部署关联资源实例定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署关联资源实例定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResInstVO> list(@RequestBody PageSearchRequest<DevpSysDpyResInstCondition> pageSearchRequest){


		PageContent<DevpSysDpyResInstVO> pageContent = devpSysDpyResInstRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
