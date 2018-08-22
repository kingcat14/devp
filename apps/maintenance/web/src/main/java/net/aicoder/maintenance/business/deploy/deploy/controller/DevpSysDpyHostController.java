package net.aicoder.maintenance.business.deploy.deploy.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostEditDto;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyHostVO;
import net.aicoder.maintenance.business.deploy.deploy.service.DevpSysDpyHostRibbonService;
import net.aicoder.maintenance.business.deploy.deploy.valid.DevpSysDpyHostValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 管理部署主机节点
 * @author icode
 */
@Api(description = "部署主机节点", tags = "DevpSysDpyHost")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyHost")
public class DevpSysDpyHostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyHostController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private DevpSysDpyHostRibbonService devpSysDpyHostRibbonService;

	@Autowired
	DevpSysDpyHostValidator devpSysDpyHostValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyHostValidator);
	}

	/**
	 * 新增部署主机节点
	 * @param devpSysDpyHostAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署主机节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyHostVO add(@RequestBody DevpSysDpyHostAddDto devpSysDpyHostAddDto){
		devpSysDpyHostAddDto.setTid(securityUtil.getAccount().getTenantId());
		return  devpSysDpyHostRibbonService.add(devpSysDpyHostAddDto);
	}

	/**
	 * 删除部署主机节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署主机节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyHost :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyHostRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署主机节点
	 * @param devpSysDpyHostEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署主机节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysDpyHostVO update(@RequestBody DevpSysDpyHostEditDto devpSysDpyHostEditDto, @ApiParam(value = "要查询的部署主机节点id") @PathVariable Long id){

		DevpSysDpyHostVO vo = devpSysDpyHostRibbonService.merge(id, devpSysDpyHostEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询部署主机节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署主机节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysDpyHostVO get(@ApiParam(value = "要查询的部署主机节点id") @PathVariable Long id) {

		DevpSysDpyHostVO vo = devpSysDpyHostRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询部署主机节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署主机节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyHostVO> list(@RequestBody PageSearchRequest<DevpSysDpyHostCondition> pageSearchRequest){


		PageContent<DevpSysDpyHostVO> pageContent = devpSysDpyHostRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
