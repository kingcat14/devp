package net.aicoder.devp.maintenance.business.product.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysElmInstanceVO;
import net.aicoder.devp.maintenance.business.product.sys.service.DevpSysElmInstanceRibbonService;
import net.aicoder.devp.maintenance.business.product.sys.valid.DevpSysElmInstanceValidator;


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
 * 管理系统元素实例
 * @author icode
 */
@Api(description = "系统元素实例", tags = "DevpSysElmInstance")
@RestController
@RequestMapping(value = "/sys/devpSysElmInstance")
public class DevpSysElmInstanceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInstanceController.class);


	@Autowired
	private DevpSysElmInstanceRibbonService devpSysElmInstanceRibbonService;

	@Autowired
	DevpSysElmInstanceValidator devpSysElmInstanceValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysElmInstanceValidator);
	}

	/**
	 * 新增系统元素实例
	 * @param devpSysElmInstanceAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素实例", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysElmInstanceVO add(@RequestBody DevpSysElmInstanceAddDto devpSysElmInstanceAddDto){

		return  devpSysElmInstanceRibbonService.add(devpSysElmInstanceAddDto);
	}

	/**
	 * 删除系统元素实例,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素实例", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysElmInstance :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysElmInstanceRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素实例
	 * @param devpSysElmInstanceEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素实例(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysElmInstanceVO update(@RequestBody DevpSysElmInstanceEditDto devpSysElmInstanceEditDto, @ApiParam(value = "要查询的系统元素实例id") @PathVariable Long id){

		DevpSysElmInstanceVO vo = devpSysElmInstanceRibbonService.merge(id, devpSysElmInstanceEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询系统元素实例
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素实例", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysElmInstanceVO get(@ApiParam(value = "要查询的系统元素实例id") @PathVariable Long id) {

		DevpSysElmInstanceVO vo = devpSysElmInstanceRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询系统元素实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素实例列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysElmInstanceVO> list(@RequestBody PageSearchRequest<DevpSysElmInstanceCondition> pageSearchRequest){


		PageContent<DevpSysElmInstanceVO> pageContent = devpSysElmInstanceRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
