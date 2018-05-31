package net.aicoder.devp.maintenance.business.product.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysDgmElementVO;
import net.aicoder.devp.maintenance.business.product.sys.service.DevpSysDgmElementRibbonService;
import net.aicoder.devp.maintenance.business.product.sys.valid.DevpSysDgmElementValidator;


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
 * 管理UML图包含元素
 * @author icode
 */
@Api(description = "UML图包含元素", tags = "DevpSysDgmElement")
@RestController
@RequestMapping(value = "/sys/devpSysDgmElement")
public class DevpSysDgmElementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDgmElementController.class);


	@Autowired
	private DevpSysDgmElementRibbonService devpSysDgmElementRibbonService;

	@Autowired
	DevpSysDgmElementValidator devpSysDgmElementValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDgmElementValidator);
	}

	/**
	 * 新增UML图包含元素
	 * @param devpSysDgmElementAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增UML图包含元素", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDgmElementVO add(@RequestBody DevpSysDgmElementAddDto devpSysDgmElementAddDto){

		return  devpSysDgmElementRibbonService.add(devpSysDgmElementAddDto);
	}

	/**
	 * 删除UML图包含元素,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除UML图包含元素", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDgmElement :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDgmElementRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新UML图包含元素
	 * @param devpSysDgmElementEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产UML图包含元素(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysDgmElementVO update(@RequestBody DevpSysDgmElementEditDto devpSysDgmElementEditDto, @ApiParam(value = "要查询的UML图包含元素id") @PathVariable Long id){

		DevpSysDgmElementVO vo = devpSysDgmElementRibbonService.merge(id, devpSysDgmElementEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询UML图包含元素
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询UML图包含元素", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysDgmElementVO get(@ApiParam(value = "要查询的UML图包含元素id") @PathVariable Long id) {

		DevpSysDgmElementVO vo = devpSysDgmElementRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询UML图包含元素列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询UML图包含元素列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDgmElementVO> list(@RequestBody PageSearchRequest<DevpSysDgmElementCondition> pageSearchRequest){


		PageContent<DevpSysDgmElementVO> pageContent = devpSysDgmElementRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
