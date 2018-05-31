package net.aicoder.devp.maintenance.business.product.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysElementVO;
import net.aicoder.devp.maintenance.business.product.sys.service.DevpSysElementRibbonService;
import net.aicoder.devp.maintenance.business.product.sys.valid.DevpSysElementValidator;


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
 * 管理系统元素
 * @author icode
 */
@Api(description = "系统元素", tags = "DevpSysElement")
@RestController
@RequestMapping(value = "/sys/devpSysElement")
public class DevpSysElementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElementController.class);


	@Autowired
	private DevpSysElementRibbonService devpSysElementRibbonService;

	@Autowired
	DevpSysElementValidator devpSysElementValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysElementValidator);
	}

	/**
	 * 新增系统元素
	 * @param devpSysElementAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysElementVO add(@RequestBody DevpSysElementAddDto devpSysElementAddDto){

		return  devpSysElementRibbonService.add(devpSysElementAddDto);
	}

	/**
	 * 删除系统元素,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysElement :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysElementRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素
	 * @param devpSysElementEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysElementVO update(@RequestBody DevpSysElementEditDto devpSysElementEditDto, @ApiParam(value = "要查询的系统元素id") @PathVariable Long id){

		DevpSysElementVO vo = devpSysElementRibbonService.merge(id, devpSysElementEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询系统元素
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysElementVO get(@ApiParam(value = "要查询的系统元素id") @PathVariable Long id) {

		DevpSysElementVO vo = devpSysElementRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询系统元素列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysElementVO> list(@RequestBody PageSearchRequest<DevpSysElementCondition> pageSearchRequest){


		PageContent<DevpSysElementVO> pageContent = devpSysElementRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
