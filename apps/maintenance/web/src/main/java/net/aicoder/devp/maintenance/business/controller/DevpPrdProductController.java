package net.aicoder.devp.maintenance.business.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.maintenance.business.service.DevpPrdProductRibbonService;
import net.aicoder.devp.product.business.product.dto.DevpPrdProductAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdProductCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdProductEditDto;
import net.aicoder.devp.product.business.product.valid.DevpPrdProductValidator;
import net.aicoder.devp.product.business.product.vo.DevpPrdProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 管理产品定义
 * @author icode
 */
@Api(description = "产品定义")
@RestController
@RequestMapping(value = "/product/devpPrdProduct")
public class DevpPrdProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdProductController.class);


	@Autowired
	private DevpPrdProductRibbonService devpPrdProductRibbonService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DevpPrdProductValidator());
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
