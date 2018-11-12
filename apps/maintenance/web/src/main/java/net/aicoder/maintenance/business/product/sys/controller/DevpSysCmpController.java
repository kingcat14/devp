package net.aicoder.maintenance.business.product.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.bootstrap.application.business.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.sys.dto.DevpSysCmpAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysCmpCondition;
import net.aicoder.devp.business.sys.dto.DevpSysCmpEditDto;
import net.aicoder.devp.business.sys.vo.DevpSysCmpVO;
import net.aicoder.maintenance.business.product.sys.service.DevpSysCmpRibbonService;
import net.aicoder.maintenance.business.product.sys.valid.DevpSysCmpValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 管理系统组件
 * @author icode
 */
@Api(description = "系统组件", tags = "DevpSysCmp")
@RestController
@RequestMapping(value = "/sys/devpSysCmp")
public class DevpSysCmpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysCmpController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private DevpSysCmpRibbonService devpSysCmpRibbonService;

	@Autowired
	DevpSysCmpValidator devpSysCmpValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysCmpValidator);
	}

	/**
	 * 新增系统组件
	 * @param devpSysCmpAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统组件", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysCmpVO add(@RequestBody DevpSysCmpAddDto devpSysCmpAddDto){
		devpSysCmpAddDto.setTid(securityUtil.getAccount().getTid());
		return  devpSysCmpRibbonService.add(devpSysCmpAddDto);
	}


	/**
	 * 删除系统组件,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统组件", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysCmp :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysCmpRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统组件
	 * @param devpSysCmpEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统组件(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysCmpVO update(@RequestBody DevpSysCmpEditDto devpSysCmpEditDto, @ApiParam(value = "要查询的系统组件id") @PathVariable Long id){

		DevpSysCmpVO vo = devpSysCmpRibbonService.merge(id, devpSysCmpEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询系统组件
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统组件", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysCmpVO get(@ApiParam(value = "要查询的系统组件id") @PathVariable Long id) {

		DevpSysCmpVO vo = devpSysCmpRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询系统组件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统组件列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysCmpVO> list(@RequestBody PageSearchRequest<DevpSysCmpCondition> pageSearchRequest){


		PageContent<DevpSysCmpVO> pageContent = devpSysCmpRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
