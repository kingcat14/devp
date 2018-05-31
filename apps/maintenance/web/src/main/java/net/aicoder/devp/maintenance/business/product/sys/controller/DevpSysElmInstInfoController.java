package net.aicoder.devp.maintenance.business.product.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysElmInstInfoVO;
import net.aicoder.devp.maintenance.business.product.sys.service.DevpSysElmInstInfoRibbonService;
import net.aicoder.devp.maintenance.business.product.sys.valid.DevpSysElmInstInfoValidator;


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
@Api(description = "系统元素实例", tags = "DevpSysElmInstInfo")
@RestController
@RequestMapping(value = "/sys/devpSysElmInstInfo")
public class DevpSysElmInstInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInstInfoController.class);


	@Autowired
	private DevpSysElmInstInfoRibbonService devpSysElmInstInfoRibbonService;

	@Autowired
	DevpSysElmInstInfoValidator devpSysElmInstInfoValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysElmInstInfoValidator);
	}

	/**
	 * 新增系统元素实例
	 * @param devpSysElmInstInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素实例", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysElmInstInfoVO add(@RequestBody DevpSysElmInstInfoAddDto devpSysElmInstInfoAddDto){

		return  devpSysElmInstInfoRibbonService.add(devpSysElmInstInfoAddDto);
	}

	/**
	 * 删除系统元素实例,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素实例", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysElmInstInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysElmInstInfoRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素实例
	 * @param devpSysElmInstInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素实例(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysElmInstInfoVO update(@RequestBody DevpSysElmInstInfoEditDto devpSysElmInstInfoEditDto, @ApiParam(value = "要查询的系统元素实例id") @PathVariable Long id){

		DevpSysElmInstInfoVO vo = devpSysElmInstInfoRibbonService.merge(id, devpSysElmInstInfoEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询系统元素实例
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素实例", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysElmInstInfoVO get(@ApiParam(value = "要查询的系统元素实例id") @PathVariable Long id) {

		DevpSysElmInstInfoVO vo = devpSysElmInstInfoRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询系统元素实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素实例列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysElmInstInfoVO> list(@RequestBody PageSearchRequest<DevpSysElmInstInfoCondition> pageSearchRequest){


		PageContent<DevpSysElmInstInfoVO> pageContent = devpSysElmInstInfoRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
