package net.aicoder.maintenance.business.deploy.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementEditDto;
import net.aicoder.devp.business.ops.vo.DevpOpsRequirementVO;
import net.aicoder.maintenance.business.deploy.ops.service.DevpOpsRequirementRibbonService;
import net.aicoder.maintenance.business.deploy.ops.valid.DevpOpsRequirementValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 管理需求定义
 * @author icode
 */
@Api(description = "需求定义", tags = "DevpOpsRequirement")
@RestController
@RequestMapping(value = "/ops/devpOpsRequirement")
public class DevpOpsRequirementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsRequirementController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private DevpOpsRequirementRibbonService devpOpsRequirementRibbonService;

	@Autowired
	DevpOpsRequirementValidator devpOpsRequirementValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsRequirementValidator);
	}

	/**
	 * 新增需求定义
	 * @param devpOpsRequirementAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增需求定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsRequirementVO add(@RequestBody DevpOpsRequirementAddDto devpOpsRequirementAddDto){
		devpOpsRequirementAddDto.setTid(securityUtil.getAccount().getTenantId());
		return  devpOpsRequirementRibbonService.add(devpOpsRequirementAddDto);
	}

	/**
	 * 删除需求定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除需求定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsRequirement :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsRequirementRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新需求定义
	 * @param devpOpsRequirementEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产需求定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpOpsRequirementVO update(@RequestBody DevpOpsRequirementEditDto devpOpsRequirementEditDto, @ApiParam(value = "要查询的需求定义id") @PathVariable Long id){

		DevpOpsRequirementVO vo = devpOpsRequirementRibbonService.merge(id, devpOpsRequirementEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询需求定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询需求定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpOpsRequirementVO get(@ApiParam(value = "要查询的需求定义id") @PathVariable Long id) {

		DevpOpsRequirementVO vo = devpOpsRequirementRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询需求定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询需求定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsRequirementVO> list(@RequestBody PageSearchRequest<DevpOpsRequirementCondition> pageSearchRequest){


		PageContent<DevpOpsRequirementVO> pageContent = devpOpsRequirementRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
