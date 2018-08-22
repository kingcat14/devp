package net.aicoder.maintenance.business.deploy.deploy.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostEditDto;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyResInstHostVO;
import net.aicoder.maintenance.business.deploy.deploy.service.DevpSysDpyResInstHostRibbonService;
import net.aicoder.maintenance.business.deploy.deploy.valid.DevpSysDpyResInstHostValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 管理资源实例部署主机节点
 * @author icode
 */
@Api(description = "资源实例部署主机节点", tags = "DevpSysDpyResInstHost")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyResInstHost")
public class DevpSysDpyResInstHostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResInstHostController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private DevpSysDpyResInstHostRibbonService devpSysDpyResInstHostRibbonService;

	@Autowired
	DevpSysDpyResInstHostValidator devpSysDpyResInstHostValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResInstHostValidator);
	}

	/**
	 * 新增资源实例部署主机节点
	 * @param devpSysDpyResInstHostAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资源实例部署主机节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResInstHostVO add(@RequestBody DevpSysDpyResInstHostAddDto devpSysDpyResInstHostAddDto){
		devpSysDpyResInstHostAddDto.setTid(securityUtil.getAccount().getTenantId());
		return  devpSysDpyResInstHostRibbonService.add(devpSysDpyResInstHostAddDto);
	}

	/**
	 * 删除资源实例部署主机节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资源实例部署主机节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResInstHost :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResInstHostRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资源实例部署主机节点
	 * @param devpSysDpyResInstHostEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资源实例部署主机节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysDpyResInstHostVO update(@RequestBody DevpSysDpyResInstHostEditDto devpSysDpyResInstHostEditDto, @ApiParam(value = "要查询的资源实例部署主机节点id") @PathVariable Long id){

		DevpSysDpyResInstHostVO vo = devpSysDpyResInstHostRibbonService.merge(id, devpSysDpyResInstHostEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询资源实例部署主机节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资源实例部署主机节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysDpyResInstHostVO get(@ApiParam(value = "要查询的资源实例部署主机节点id") @PathVariable Long id) {

		DevpSysDpyResInstHostVO vo = devpSysDpyResInstHostRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询资源实例部署主机节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资源实例部署主机节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResInstHostVO> list(@RequestBody PageSearchRequest<DevpSysDpyResInstHostCondition> pageSearchRequest){


		PageContent<DevpSysDpyResInstHostVO> pageContent = devpSysDpyResInstHostRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
