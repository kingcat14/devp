package net.aicoder.devp.maintenance.business.maintenance.hardware.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.maintenance.business.hardware.dto.NetworkDeviceAddDto;
import net.aicoder.devp.maintenance.business.hardware.dto.NetworkDeviceCondition;
import net.aicoder.devp.maintenance.business.hardware.dto.NetworkDeviceEditDto;
import net.aicoder.devp.maintenance.business.hardware.vo.NetworkDeviceVO;
import net.aicoder.devp.maintenance.business.maintenance.hardware.service.NetworkDeviceRibbonService;
import net.aicoder.devp.maintenance.business.maintenance.hardware.valid.NetworkDeviceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 管理网络设备
 * @author icode
 */
@Api(description = "网络设备", tags = "NetworkDevice")
@RestController
@RequestMapping(value = "/hardware/networkDevice")
public class NetworkDeviceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NetworkDeviceController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private NetworkDeviceRibbonService networkDeviceRibbonService;

	@Autowired
	NetworkDeviceValidator networkDeviceValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(networkDeviceValidator);
	}

	/**
	 * 新增网络设备
	 * @param networkDeviceAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增网络设备", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public NetworkDeviceVO add(@RequestBody NetworkDeviceAddDto networkDeviceAddDto){
		networkDeviceAddDto.setTid(securityUtil.getAccount().getTenantId());
		return  networkDeviceRibbonService.add(networkDeviceAddDto);
	}

	/**
	 * 删除网络设备,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除网络设备", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete networkDevice :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			networkDeviceRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新网络设备
	 * @param networkDeviceEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产网络设备(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public NetworkDeviceVO update(@RequestBody NetworkDeviceEditDto networkDeviceEditDto, @ApiParam(value = "要查询的网络设备id") @PathVariable Long id){
		networkDeviceEditDto.setTid(securityUtil.getAccount().getTenantId());
		NetworkDeviceVO vo = networkDeviceRibbonService.merge(id, networkDeviceEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询网络设备
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询网络设备", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public NetworkDeviceVO get(@ApiParam(value = "要查询的网络设备id") @PathVariable Long id) {

		NetworkDeviceVO vo = networkDeviceRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询网络设备列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询网络设备列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<NetworkDeviceVO> list(@RequestBody PageSearchRequest<NetworkDeviceCondition> pageSearchRequest){


		PageContent<NetworkDeviceVO> pageContent = networkDeviceRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
