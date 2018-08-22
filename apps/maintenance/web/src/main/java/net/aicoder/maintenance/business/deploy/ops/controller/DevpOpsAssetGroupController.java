package net.aicoder.maintenance.business.deploy.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetGroupCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetGroupAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetGroupEditDto;
import net.aicoder.devp.business.ops.vo.DevpOpsAssetGroupVO;
import net.aicoder.maintenance.business.deploy.ops.service.DevpOpsAssetGroupRibbonService;
import net.aicoder.maintenance.business.deploy.ops.valid.DevpOpsAssetGroupValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

/**
 * 管理资产分组
 * @author icode
 */
@Api(description = "资产分组", tags = "DevpOpsAssetGroup")
@RestController
@RequestMapping(value = "/ops/devpOpsAssetGroup")
public class DevpOpsAssetGroupController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAssetGroupController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private DevpOpsAssetGroupRibbonService devpOpsAssetGroupRibbonService;

	@Autowired
	DevpOpsAssetGroupValidator devpOpsAssetGroupValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsAssetGroupValidator);
	}

	/**
	 * 新增资产分组
	 * @param devpOpsAssetGroupAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资产分组", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsAssetGroupVO add(@RequestBody DevpOpsAssetGroupAddDto devpOpsAssetGroupAddDto){
		devpOpsAssetGroupAddDto.setTid(securityUtil.getAccount().getTenantId());
		return  devpOpsAssetGroupRibbonService.add(devpOpsAssetGroupAddDto);
	}

	/**
	 * 删除资产分组,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资产分组", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsAssetGroup :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsAssetGroupRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资产分组
	 * @param devpOpsAssetGroupEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资产分组(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpOpsAssetGroupVO update(@RequestBody DevpOpsAssetGroupEditDto devpOpsAssetGroupEditDto, @ApiParam(value = "要查询的资产分组id") @PathVariable Long id){

		DevpOpsAssetGroupVO vo = devpOpsAssetGroupRibbonService.merge(id, devpOpsAssetGroupEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询资产分组
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资产分组", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpOpsAssetGroupVO get(@ApiParam(value = "要查询的资产分组id") @PathVariable Long id) {

		DevpOpsAssetGroupVO vo = devpOpsAssetGroupRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询资产分组列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资产分组列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsAssetGroupVO> list(@RequestBody PageSearchRequest<DevpOpsAssetGroupCondition> pageSearchRequest){


		PageContent<DevpOpsAssetGroupVO> pageContent = devpOpsAssetGroupRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
