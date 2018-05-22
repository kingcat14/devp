package net.aicoder.devp.product.business.product.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.product.domain.DevpPrdLinePrd;
import net.aicoder.devp.product.business.product.dto.DevpPrdLinePrdCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdLinePrdAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdLinePrdEditDto;
import net.aicoder.devp.product.business.product.service.DevpPrdLinePrdRibbonService;
import net.aicoder.devp.product.business.product.valid.DevpPrdLinePrdValidator;
import net.aicoder.devp.product.business.product.vo.DevpPrdLinePrdVO;


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
 * 管理产品所属产品线定义
 * @author icode
 */
@Api(description = "产品所属产品线定义")
@RestController
@RequestMapping(value = "/product/devpPrdLinePrd")
public class DevpPrdLinePrdController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdLinePrdController.class);


	@Autowired
	private DevpPrdLinePrdRibbonService devpPrdLinePrdRibbonService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DevpPrdLinePrdValidator());
	}

	/**
	 * 新增产品所属产品线定义
	 * @param devpPrdLinePrdAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品所属产品线定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpPrdLinePrdVO add(@RequestBody @Valid DevpPrdLinePrdAddDto devpPrdLinePrdAddDto){

		devpPrdLinePrdRibbonService.add(devpPrdLinePrdAddDto);

		return  devpPrdLinePrdRibbonService.add(devpPrdLinePrdAddDto);
	}

	/**
	 * 删除产品所属产品线定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品所属产品线定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpPrdLinePrd :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpPrdLinePrdRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品所属产品线定义
	 * @param devpPrdLinePrdEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品所属产品线定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpPrdLinePrdVO update(@RequestBody @Valid DevpPrdLinePrdEditDto devpPrdLinePrdEditDto, @ApiParam(value = "要查询的产品所属产品线定义id") @PathVariable Long id){

		DevpPrdLinePrdVO vo = devpPrdLinePrdRibbonService.merge(id, devpPrdLinePrdEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询产品所属产品线定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品所属产品线定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpPrdLinePrdVO get(@ApiParam(value = "要查询的产品所属产品线定义id") @PathVariable Long id) {

		DevpPrdLinePrdVO vo = devpPrdLinePrdRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询产品所属产品线定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品所属产品线定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpPrdLinePrdVO> list(@RequestBody PageSearchRequest<DevpPrdLinePrdCondition> pageSearchRequest){


		PageContent<DevpPrdLinePrdVO> pageContent = devpPrdLinePrdRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
