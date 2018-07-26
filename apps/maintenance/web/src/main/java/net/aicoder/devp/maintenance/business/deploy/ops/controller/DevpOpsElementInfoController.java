package net.aicoder.devp.maintenance.business.deploy.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsElementInfoCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsElementInfoAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsElementInfoEditDto;
import net.aicoder.devp.deploy.business.ops.vo.DevpOpsElementInfoVO;
import net.aicoder.devp.maintenance.business.deploy.ops.service.DevpOpsElementInfoRibbonService;
import net.aicoder.devp.maintenance.business.deploy.ops.valid.DevpOpsElementInfoValidator;
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
 * 管理系统元素扩充信息
 * @author icode
 */
@Api(description = "系统元素扩充信息", tags = "DevpOpsElementInfo")
@RestController
@RequestMapping(value = "/ops/devpOpsElementInfo")
public class DevpOpsElementInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsElementInfoController.class);


	@Autowired
	private DevpOpsElementInfoRibbonService devpOpsElementInfoRibbonService;

	@Autowired
	DevpOpsElementInfoValidator devpOpsElementInfoValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsElementInfoValidator);
	}

	/**
	 * 新增系统元素扩充信息
	 * @param devpOpsElementInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素扩充信息", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsElementInfoVO add(@RequestBody DevpOpsElementInfoAddDto devpOpsElementInfoAddDto){
		devpOpsElementInfoAddDto.setTid(SecurityUtil.getAccount().getTenantId());
		return  devpOpsElementInfoRibbonService.add(devpOpsElementInfoAddDto);
	}

	/**
	 * 删除系统元素扩充信息,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素扩充信息", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsElementInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsElementInfoRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素扩充信息
	 * @param devpOpsElementInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素扩充信息(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpOpsElementInfoVO update(@RequestBody DevpOpsElementInfoEditDto devpOpsElementInfoEditDto, @ApiParam(value = "要查询的系统元素扩充信息id") @PathVariable Long id){

		DevpOpsElementInfoVO vo = devpOpsElementInfoRibbonService.merge(id, devpOpsElementInfoEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询系统元素扩充信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素扩充信息", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpOpsElementInfoVO get(@ApiParam(value = "要查询的系统元素扩充信息id") @PathVariable Long id) {

		DevpOpsElementInfoVO vo = devpOpsElementInfoRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询系统元素扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素扩充信息列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsElementInfoVO> list(@RequestBody PageSearchRequest<DevpOpsElementInfoCondition> pageSearchRequest){


		PageContent<DevpOpsElementInfoVO> pageContent = devpOpsElementInfoRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
