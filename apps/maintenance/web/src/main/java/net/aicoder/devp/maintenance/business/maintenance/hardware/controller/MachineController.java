package net.aicoder.devp.maintenance.business.maintenance.hardware.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.maintenance.business.hardware.dto.MachineAddDto;
import net.aicoder.devp.maintenance.business.hardware.dto.MachineCondition;
import net.aicoder.devp.maintenance.business.hardware.dto.MachineEditDto;
import net.aicoder.devp.maintenance.business.hardware.vo.MachineVO;
import net.aicoder.devp.maintenance.business.maintenance.hardware.service.MachineRibbonService;
import net.aicoder.devp.maintenance.business.maintenance.hardware.valid.MachineValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 管理服务器
 * @author icode
 */
@Api(description = "服务器", tags = "Machine")
@RestController
@RequestMapping(value = "/hardware/machine")
public class MachineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MachineController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private MachineRibbonService machineRibbonService;

	@Autowired
	MachineValidator machineValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(machineValidator);
	}

	/**
	 * 新增服务器
	 * @param machineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增服务器", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public MachineVO add(@RequestBody MachineAddDto machineAddDto){
		machineAddDto.setTid(securityUtil.getAccount().getTenantId());

		return  machineRibbonService.add(machineAddDto);
	}

	/**
	 * 删除服务器,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除服务器", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete machine :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			machineRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新服务器
	 * @param machineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产服务器(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public MachineVO update(@RequestBody MachineEditDto machineEditDto, @ApiParam(value = "要查询的服务器id") @PathVariable Long id){
        machineEditDto.setTid(securityUtil.getAccount().getTenantId());
		MachineVO vo = machineRibbonService.merge(id, machineEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询服务器
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询服务器", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public MachineVO get(@ApiParam(value = "要查询的服务器id") @PathVariable Long id) {

		MachineVO vo = machineRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询服务器列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询服务器列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<MachineVO> list(@RequestBody PageSearchRequest<MachineCondition> pageSearchRequest){


		PageContent<MachineVO> pageContent = machineRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
