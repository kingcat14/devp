package net.aicoder.maintenance.business.product.product.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.product.dto.DevpPrdProductCondition;
import net.aicoder.devp.business.product.dto.DevpPrdProductAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdProductEditDto;
import net.aicoder.devp.business.product.vo.DevpPrdProductVO;
import net.aicoder.devp.business.sys.dto.DevpSysCmpCondition;
import net.aicoder.devp.business.sys.vo.DevpSysCmpVO;
import net.aicoder.maintenance.business.product.product.service.DevpPrdProductRibbonService;
import net.aicoder.maintenance.business.product.product.valid.DevpPrdProductValidator;
import net.aicoder.maintenance.business.product.sys.service.DevpSysCmpRibbonService;

import org.apache.commons.collections4.CollectionUtils;
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
import java.util.HashMap;
import java.util.List;

/**
 * 管理产品定义
 * @author icode
 */
@Api(description = "产品定义", tags = "DevpPrdProduct")
@RestController
@RequestMapping(value = "/product/devpPrdProduct")
public class DevpPrdProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdProductController.class);


	@Autowired
	private DevpPrdProductRibbonService devpPrdProductRibbonService;

	@Autowired
	DevpPrdProductValidator devpPrdProductValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpPrdProductValidator);
	}

	/**
	 * 新增产品定义
	 * @param devpPrdProductAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpPrdProductVO add(@RequestBody DevpPrdProductAddDto devpPrdProductAddDto){

		return  devpPrdProductRibbonService.add(devpPrdProductAddDto);
	}

	/**
	 * 删除产品定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpPrdProduct :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpPrdProductRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品定义
	 * @param devpPrdProductEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpPrdProductVO update(@RequestBody DevpPrdProductEditDto devpPrdProductEditDto, @ApiParam(value = "要查询的产品定义id") @PathVariable Long id){

		DevpPrdProductVO vo = devpPrdProductRibbonService.merge(id, devpPrdProductEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询产品定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpPrdProductVO get(@ApiParam(value = "要查询的产品定义id") @PathVariable Long id) {

		DevpPrdProductVO vo = devpPrdProductRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询产品定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpPrdProductVO> list(@RequestBody PageSearchRequest<DevpPrdProductCondition> pageSearchRequest){


		PageContent<DevpPrdProductVO> pageContent = devpPrdProductRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
