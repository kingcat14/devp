package net.aicoder.devp.maintenance.business.deploy.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetCmdbCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetCmdbAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetCmdbEditDto;
import net.aicoder.devp.deploy.business.ops.vo.DevpOpsAssetCmdbVO;
import net.aicoder.devp.maintenance.business.deploy.ops.service.DevpOpsAssetCmdbRibbonService;
import net.aicoder.devp.maintenance.business.deploy.ops.valid.DevpOpsAssetCmdbValidator;
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
 * 管理资产定义
 * @author icode
 */
@Api(description = "资产定义", tags = "DevpOpsAssetCmdb")
@RestController
@RequestMapping(value = "/ops/devpOpsAssetCmdb")
public class DevpOpsAssetCmdbController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAssetCmdbController.class);


	@Autowired
	private DevpOpsAssetCmdbRibbonService devpOpsAssetCmdbRibbonService;

	@Autowired
	DevpOpsAssetCmdbValidator devpOpsAssetCmdbValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsAssetCmdbValidator);
	}

	/**
	 * 新增资产定义
	 * @param devpOpsAssetCmdbAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资产定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsAssetCmdbVO add(@RequestBody DevpOpsAssetCmdbAddDto devpOpsAssetCmdbAddDto){
		devpOpsAssetCmdbAddDto.setTid(SecurityUtil.getAccount().getTenantId());
		return  devpOpsAssetCmdbRibbonService.add(devpOpsAssetCmdbAddDto);
	}

	/**
	 * 删除资产定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资产定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsAssetCmdb :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsAssetCmdbRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资产定义
	 * @param devpOpsAssetCmdbEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资产定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpOpsAssetCmdbVO update(@RequestBody DevpOpsAssetCmdbEditDto devpOpsAssetCmdbEditDto, @ApiParam(value = "要查询的资产定义id") @PathVariable Long id){

		DevpOpsAssetCmdbVO vo = devpOpsAssetCmdbRibbonService.merge(id, devpOpsAssetCmdbEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询资产定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资产定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpOpsAssetCmdbVO get(@ApiParam(value = "要查询的资产定义id") @PathVariable Long id) {

		DevpOpsAssetCmdbVO vo = devpOpsAssetCmdbRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询资产定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资产定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsAssetCmdbVO> list(@RequestBody PageSearchRequest<DevpOpsAssetCmdbCondition> pageSearchRequest){


		PageContent<DevpOpsAssetCmdbVO> pageContent = devpOpsAssetCmdbRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
