package net.aicoder.devp.maintenance.business.product.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysElmConnectorVO;
import net.aicoder.devp.maintenance.business.product.sys.service.DevpSysElmConnectorRibbonService;
import net.aicoder.devp.maintenance.business.product.sys.valid.DevpSysElmConnectorValidator;


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
 * 管理系统元素间关系
 * @author icode
 */
@Api(description = "系统元素间关系", tags = "DevpSysElmConnector")
@RestController
@RequestMapping(value = "/sys/devpSysElmConnector")
public class DevpSysElmConnectorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmConnectorController.class);


	@Autowired
	private DevpSysElmConnectorRibbonService devpSysElmConnectorRibbonService;

	@Autowired
	DevpSysElmConnectorValidator devpSysElmConnectorValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysElmConnectorValidator);
	}

	/**
	 * 新增系统元素间关系
	 * @param devpSysElmConnectorAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素间关系", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysElmConnectorVO add(@RequestBody DevpSysElmConnectorAddDto devpSysElmConnectorAddDto){

		return  devpSysElmConnectorRibbonService.add(devpSysElmConnectorAddDto);
	}

	/**
	 * 删除系统元素间关系,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素间关系", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysElmConnector :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysElmConnectorRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素间关系
	 * @param devpSysElmConnectorEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素间关系(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysElmConnectorVO update(@RequestBody DevpSysElmConnectorEditDto devpSysElmConnectorEditDto, @ApiParam(value = "要查询的系统元素间关系id") @PathVariable Long id){

		DevpSysElmConnectorVO vo = devpSysElmConnectorRibbonService.merge(id, devpSysElmConnectorEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询系统元素间关系
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素间关系", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysElmConnectorVO get(@ApiParam(value = "要查询的系统元素间关系id") @PathVariable Long id) {

		DevpSysElmConnectorVO vo = devpSysElmConnectorRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询系统元素间关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素间关系列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysElmConnectorVO> list(@RequestBody PageSearchRequest<DevpSysElmConnectorCondition> pageSearchRequest){


		PageContent<DevpSysElmConnectorVO> pageContent = devpSysElmConnectorRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
