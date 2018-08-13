package net.aicoder.devp.maintenance.business.maintenance.software.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareCondition;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareAddDto;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareEditDto;
import net.aicoder.devp.maintenance.business.software.vo.BusinessSoftwareVO;
import net.aicoder.devp.maintenance.business.maintenance.software.service.BusinessSoftwareRibbonService;
import net.aicoder.devp.maintenance.business.maintenance.software.valid.BusinessSoftwareValidator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

/**
 * 管理应用软件
 * @author icode
 */
@Api(description = "应用软件", tags = "BusinessSoftware")
@RestController
@RequestMapping(value = "/software/businessSoftware")
public class BusinessSoftwareController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessSoftwareController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private BusinessSoftwareRibbonService businessSoftwareRibbonService;

	@Autowired
	BusinessSoftwareValidator businessSoftwareValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(businessSoftwareValidator);
	}

	/**
	 * 新增应用软件
	 * @param businessSoftwareAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用软件", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public BusinessSoftwareVO add(@RequestBody BusinessSoftwareAddDto businessSoftwareAddDto){
		businessSoftwareAddDto.setTid(securityUtil.getAccount().getTenantId());
		return  businessSoftwareRibbonService.add(businessSoftwareAddDto);
	}

	/**
	 * 删除应用软件,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用软件", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete businessSoftware :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			businessSoftwareRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新应用软件
	 * @param businessSoftwareEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用软件(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public BusinessSoftwareVO update(@RequestBody BusinessSoftwareEditDto businessSoftwareEditDto, @ApiParam(value = "要查询的应用软件id") @PathVariable Long id){
		businessSoftwareEditDto.setTid(securityUtil.getAccount().getTenantId());
		BusinessSoftwareVO vo = businessSoftwareRibbonService.merge(id, businessSoftwareEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询应用软件
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用软件", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public BusinessSoftwareVO get(@ApiParam(value = "要查询的应用软件id") @PathVariable Long id) {

		BusinessSoftwareVO vo = businessSoftwareRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询应用软件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用软件列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<BusinessSoftwareVO> list(@RequestBody PageSearchRequest<BusinessSoftwareCondition> pageSearchRequest){


		PageContent<BusinessSoftwareVO> pageContent = businessSoftwareRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
