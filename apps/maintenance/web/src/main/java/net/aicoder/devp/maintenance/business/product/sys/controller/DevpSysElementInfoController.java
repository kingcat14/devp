package net.aicoder.devp.maintenance.business.product.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysElementInfoVO;
import net.aicoder.devp.maintenance.business.product.sys.service.DevpSysElementInfoRibbonService;
import net.aicoder.devp.maintenance.business.product.sys.valid.DevpSysElementInfoValidator;


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
@Api(description = "系统元素扩充信息", tags = "DevpSysElementInfo")
@RestController
@RequestMapping(value = "/sys/devpSysElementInfo")
public class DevpSysElementInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElementInfoController.class);


	@Autowired
	private DevpSysElementInfoRibbonService devpSysElementInfoRibbonService;

	@Autowired
	DevpSysElementInfoValidator devpSysElementInfoValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysElementInfoValidator);
	}

	/**
	 * 新增系统元素扩充信息
	 * @param devpSysElementInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素扩充信息", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysElementInfoVO add(@RequestBody DevpSysElementInfoAddDto devpSysElementInfoAddDto){

		return  devpSysElementInfoRibbonService.add(devpSysElementInfoAddDto);
	}

	/**
	 * 删除系统元素扩充信息,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素扩充信息", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysElementInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysElementInfoRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素扩充信息
	 * @param devpSysElementInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素扩充信息(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysElementInfoVO update(@RequestBody DevpSysElementInfoEditDto devpSysElementInfoEditDto, @ApiParam(value = "要查询的系统元素扩充信息id") @PathVariable Long id){

		DevpSysElementInfoVO vo = devpSysElementInfoRibbonService.merge(id, devpSysElementInfoEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询系统元素扩充信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素扩充信息", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysElementInfoVO get(@ApiParam(value = "要查询的系统元素扩充信息id") @PathVariable Long id) {

		DevpSysElementInfoVO vo = devpSysElementInfoRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询系统元素扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素扩充信息列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysElementInfoVO> list(@RequestBody PageSearchRequest<DevpSysElementInfoCondition> pageSearchRequest){


		PageContent<DevpSysElementInfoVO> pageContent = devpSysElementInfoRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
