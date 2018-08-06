package net.aicoder.devp.maintenance.business.deploy.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsParasDefineCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsParasDefineAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsParasDefineEditDto;
import net.aicoder.devp.deploy.business.ops.vo.DevpOpsParasDefineVO;
import net.aicoder.devp.maintenance.business.deploy.ops.service.DevpOpsParasDefineRibbonService;
import net.aicoder.devp.maintenance.business.deploy.ops.valid.DevpOpsParasDefineValidator;
import com.yunkang.saas.security.local.business.service.SecurityUtil;

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
 * 管理运维元素扩充信息
 * @author icode
 */
@Api(description = "运维元素扩充信息", tags = "DevpOpsParasDefine")
@RestController
@RequestMapping(value = "/ops/devpOpsParasDefine")
public class DevpOpsParasDefineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsParasDefineController.class);


	@Autowired
	private DevpOpsParasDefineRibbonService devpOpsParasDefineRibbonService;

	@Autowired
	DevpOpsParasDefineValidator devpOpsParasDefineValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsParasDefineValidator);
	}

	/**
	 * 新增运维元素扩充信息
	 * @param devpOpsParasDefineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运维元素扩充信息", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsParasDefineVO add(@RequestBody DevpOpsParasDefineAddDto devpOpsParasDefineAddDto){
		devpOpsParasDefineAddDto.setTid(SecurityUtil.getAccount().getTenantId());
		return  devpOpsParasDefineRibbonService.add(devpOpsParasDefineAddDto);
	}

	/**
	 * 删除运维元素扩充信息,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运维元素扩充信息", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsParasDefine :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsParasDefineRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新运维元素扩充信息
	 * @param devpOpsParasDefineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运维元素扩充信息(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpOpsParasDefineVO update(@RequestBody DevpOpsParasDefineEditDto devpOpsParasDefineEditDto, @ApiParam(value = "要查询的运维元素扩充信息id") @PathVariable Long id){

		DevpOpsParasDefineVO vo = devpOpsParasDefineRibbonService.merge(id, devpOpsParasDefineEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询运维元素扩充信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运维元素扩充信息", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpOpsParasDefineVO get(@ApiParam(value = "要查询的运维元素扩充信息id") @PathVariable Long id) {

		DevpOpsParasDefineVO vo = devpOpsParasDefineRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询运维元素扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运维元素扩充信息列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsParasDefineVO> list(@RequestBody PageSearchRequest<DevpOpsParasDefineCondition> pageSearchRequest){


		PageContent<DevpOpsParasDefineVO> pageContent = devpOpsParasDefineRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
