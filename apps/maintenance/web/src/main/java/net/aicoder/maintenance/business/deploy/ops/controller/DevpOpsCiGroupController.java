package net.aicoder.maintenance.business.deploy.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.dto.DevpOpsCiGroupAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsCiGroupCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsCiGroupEditDto;
import net.aicoder.devp.business.ops.vo.DevpOpsCiGroupVO;
import net.aicoder.maintenance.business.deploy.ops.service.DevpOpsCiGroupRibbonService;
import net.aicoder.maintenance.business.deploy.ops.valid.DevpOpsCiGroupValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 管理资产项目分组映射
 * @author icode
 */
@Api(description = "资产项目分组映射", tags = "DevpOpsCiGroup")
@RestController
@RequestMapping(value = "/ops/devpOpsCiGroup")
public class DevpOpsCiGroupController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsCiGroupController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private DevpOpsCiGroupRibbonService devpOpsCiGroupRibbonService;

	@Autowired
	DevpOpsCiGroupValidator devpOpsCiGroupValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsCiGroupValidator);
	}

	/**
	 * 新增资产项目分组映射
	 * @param devpOpsCiGroupAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资产项目分组映射", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsCiGroupVO add(@RequestBody DevpOpsCiGroupAddDto devpOpsCiGroupAddDto){
		devpOpsCiGroupAddDto.setTid(securityUtil.getAccount().getTenantId());
		return  devpOpsCiGroupRibbonService.add(devpOpsCiGroupAddDto);
	}

	/**
	 * 删除资产项目分组映射,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资产项目分组映射", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsCiGroup :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsCiGroupRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资产项目分组映射
	 * @param devpOpsCiGroupEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资产项目分组映射(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpOpsCiGroupVO update(@RequestBody DevpOpsCiGroupEditDto devpOpsCiGroupEditDto, @ApiParam(value = "要查询的资产项目分组映射id") @PathVariable Long id){

		DevpOpsCiGroupVO vo = devpOpsCiGroupRibbonService.merge(id, devpOpsCiGroupEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询资产项目分组映射
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资产项目分组映射", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpOpsCiGroupVO get(@ApiParam(value = "要查询的资产项目分组映射id") @PathVariable Long id) {

		DevpOpsCiGroupVO vo = devpOpsCiGroupRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询资产项目分组映射列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资产项目分组映射列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsCiGroupVO> list(@RequestBody PageSearchRequest<DevpOpsCiGroupCondition> pageSearchRequest){


		PageContent<DevpOpsCiGroupVO> pageContent = devpOpsCiGroupRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
