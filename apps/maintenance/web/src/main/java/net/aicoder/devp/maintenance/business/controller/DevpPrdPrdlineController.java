package net.aicoder.devp.maintenance.business.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.maintenance.business.service.DevpPrdPrdlineRibbonService;
import net.aicoder.devp.product.business.product.dto.DevpPrdPrdlineAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdPrdlineCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdPrdlineEditDto;
import net.aicoder.devp.product.business.product.valid.DevpPrdPrdlineValidator;
import net.aicoder.devp.product.business.product.vo.DevpPrdPrdlineVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 管理产品线定义
 * @author icode
 */
@Api(description = "产品线定义")
@RestController
@RequestMapping(value = "/product/devpPrdPrdline")
public class DevpPrdPrdlineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPrdlineController.class);


	@Autowired
	private DevpPrdPrdlineRibbonService devpPrdPrdlineRibbonService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DevpPrdPrdlineValidator());
	}

	/**
	 * 新增产品线定义
	 * @param devpPrdPrdlineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品线定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpPrdPrdlineVO add(@RequestBody DevpPrdPrdlineAddDto devpPrdPrdlineAddDto){

		return  devpPrdPrdlineRibbonService.add(devpPrdPrdlineAddDto);
	}

	/**
	 * 删除产品线定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品线定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpPrdPrdline :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpPrdPrdlineRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品线定义
	 * @param devpPrdPrdlineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品线定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpPrdPrdlineVO update(@RequestBody DevpPrdPrdlineEditDto devpPrdPrdlineEditDto, @ApiParam(value = "要查询的产品线定义id") @PathVariable Long id){

		DevpPrdPrdlineVO vo = devpPrdPrdlineRibbonService.merge(id, devpPrdPrdlineEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询产品线定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品线定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpPrdPrdlineVO get(@ApiParam(value = "要查询的产品线定义id") @PathVariable Long id) {

		DevpPrdPrdlineVO vo = devpPrdPrdlineRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询产品线定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品线定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpPrdPrdlineVO> list(@RequestBody PageSearchRequest<DevpPrdPrdlineCondition> pageSearchRequest){


		PageContent<DevpPrdPrdlineVO> pageContent = devpPrdPrdlineRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
