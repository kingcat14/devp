package net.aicoder.devp.product.business.product.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.product.domain.DevpPrdPerson;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonEditDto;
import net.aicoder.devp.product.business.product.service.DevpPrdPersonRibbonService;
import net.aicoder.devp.product.business.product.valid.DevpPrdPersonValidator;
import net.aicoder.devp.product.business.product.vo.DevpPrdPersonVO;


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
 * 管理产品干系人
 * @author icode
 */
@Api(description = "产品干系人")
@RestController
@RequestMapping(value = "/product/devpPrdPerson")
public class DevpPrdPersonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPersonController.class);


	@Autowired
	private DevpPrdPersonRibbonService devpPrdPersonRibbonService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DevpPrdPersonValidator());
	}

	/**
	 * 新增产品干系人
	 * @param devpPrdPersonAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品干系人", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpPrdPersonVO add(@RequestBody @Valid DevpPrdPersonAddDto devpPrdPersonAddDto){

		devpPrdPersonRibbonService.add(devpPrdPersonAddDto);

		return  devpPrdPersonRibbonService.add(devpPrdPersonAddDto);
	}

	/**
	 * 删除产品干系人,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品干系人", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpPrdPerson :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpPrdPersonRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品干系人
	 * @param devpPrdPersonEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品干系人(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpPrdPersonVO update(@RequestBody @Valid DevpPrdPersonEditDto devpPrdPersonEditDto, @ApiParam(value = "要查询的产品干系人id") @PathVariable Long id){

		DevpPrdPersonVO vo = devpPrdPersonRibbonService.merge(id, devpPrdPersonEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询产品干系人
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品干系人", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpPrdPersonVO get(@ApiParam(value = "要查询的产品干系人id") @PathVariable Long id) {

		DevpPrdPersonVO vo = devpPrdPersonRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询产品干系人列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品干系人列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpPrdPersonVO> list(@RequestBody PageSearchRequest<DevpPrdPersonCondition> pageSearchRequest){


		PageContent<DevpPrdPersonVO> pageContent = devpPrdPersonRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
