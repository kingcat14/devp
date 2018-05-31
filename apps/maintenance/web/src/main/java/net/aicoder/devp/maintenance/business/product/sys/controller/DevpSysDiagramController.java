package net.aicoder.devp.maintenance.business.product.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysDiagramVO;
import net.aicoder.devp.maintenance.business.product.sys.service.DevpSysDiagramRibbonService;
import net.aicoder.devp.maintenance.business.product.sys.valid.DevpSysDiagramValidator;


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
 * 管理UML图
 * @author icode
 */
@Api(description = "UML图", tags = "DevpSysDiagram")
@RestController
@RequestMapping(value = "/sys/devpSysDiagram")
public class DevpSysDiagramController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDiagramController.class);


	@Autowired
	private DevpSysDiagramRibbonService devpSysDiagramRibbonService;

	@Autowired
	DevpSysDiagramValidator devpSysDiagramValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDiagramValidator);
	}

	/**
	 * 新增UML图
	 * @param devpSysDiagramAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增UML图", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDiagramVO add(@RequestBody DevpSysDiagramAddDto devpSysDiagramAddDto){

		return  devpSysDiagramRibbonService.add(devpSysDiagramAddDto);
	}

	/**
	 * 删除UML图,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除UML图", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDiagram :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDiagramRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新UML图
	 * @param devpSysDiagramEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产UML图(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysDiagramVO update(@RequestBody DevpSysDiagramEditDto devpSysDiagramEditDto, @ApiParam(value = "要查询的UML图id") @PathVariable Long id){

		DevpSysDiagramVO vo = devpSysDiagramRibbonService.merge(id, devpSysDiagramEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询UML图
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询UML图", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysDiagramVO get(@ApiParam(value = "要查询的UML图id") @PathVariable Long id) {

		DevpSysDiagramVO vo = devpSysDiagramRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询UML图列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询UML图列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDiagramVO> list(@RequestBody PageSearchRequest<DevpSysDiagramCondition> pageSearchRequest){


		PageContent<DevpSysDiagramVO> pageContent = devpSysDiagramRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
