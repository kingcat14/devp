package net.aicoder.maintenance.business.deploy.deploy.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.bootstrap.application.business.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefEditDto;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyCmpRefVO;
import net.aicoder.maintenance.business.deploy.deploy.service.DevpSysDpyCmpRefRibbonService;
import net.aicoder.maintenance.business.deploy.deploy.valid.DevpSysDpyCmpRefValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 管理系统元素间关系
 * @author icode
 */
@Api(description = "系统元素间关系", tags = "DevpSysDpyCmpRef")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyCmpRef")
public class DevpSysDpyCmpRefController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyCmpRefController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private DevpSysDpyCmpRefRibbonService devpSysDpyCmpRefRibbonService;

	@Autowired
	DevpSysDpyCmpRefValidator devpSysDpyCmpRefValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyCmpRefValidator);
	}

	/**
	 * 新增系统元素间关系
	 * @param devpSysDpyCmpRefAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素间关系", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyCmpRefVO add(@RequestBody DevpSysDpyCmpRefAddDto devpSysDpyCmpRefAddDto){
		devpSysDpyCmpRefAddDto.setTid(securityUtil.getAccount().getTid());
		return  devpSysDpyCmpRefRibbonService.add(devpSysDpyCmpRefAddDto);
	}

	/**
	 * 删除系统元素间关系,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素间关系", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyCmpRef :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyCmpRefRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素间关系
	 * @param devpSysDpyCmpRefEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素间关系(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysDpyCmpRefVO update(@RequestBody DevpSysDpyCmpRefEditDto devpSysDpyCmpRefEditDto, @ApiParam(value = "要查询的系统元素间关系id") @PathVariable Long id){

		DevpSysDpyCmpRefVO vo = devpSysDpyCmpRefRibbonService.merge(id, devpSysDpyCmpRefEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询系统元素间关系
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素间关系", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysDpyCmpRefVO get(@ApiParam(value = "要查询的系统元素间关系id") @PathVariable Long id) {

		DevpSysDpyCmpRefVO vo = devpSysDpyCmpRefRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询系统元素间关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素间关系列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyCmpRefVO> list(@RequestBody PageSearchRequest<DevpSysDpyCmpRefCondition> pageSearchRequest){


		PageContent<DevpSysDpyCmpRefVO> pageContent = devpSysDpyCmpRefRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
