package net.aicoder.devp.maintenance.business.maintenance.software.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.maintenance.business.maintenance.software.service.InfrastructuralSoftwareRibbonService;
import net.aicoder.devp.maintenance.business.maintenance.software.valid.InfrastructuralSoftwareValidator;
import net.aicoder.devp.maintenance.business.software.dto.InfrastructuralSoftwareAddDto;
import net.aicoder.devp.maintenance.business.software.dto.InfrastructuralSoftwareCondition;
import net.aicoder.devp.maintenance.business.software.dto.InfrastructuralSoftwareEditDto;
import net.aicoder.devp.maintenance.business.software.vo.InfrastructuralSoftwareVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 管理基础软件
 * @author icode
 */
@Api(description = "基础软件", tags = "InfrastructuralSoftware")
@RestController
@RequestMapping(value = "/software/infrastructuralSoftware")
public class InfrastructuralSoftwareController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfrastructuralSoftwareController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private InfrastructuralSoftwareRibbonService infrastructuralSoftwareRibbonService;

	@Autowired
	InfrastructuralSoftwareValidator infrastructuralSoftwareValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(infrastructuralSoftwareValidator);
	}

	/**
	 * 新增基础软件
	 * @param infrastructuralSoftwareAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增基础软件", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public InfrastructuralSoftwareVO add(@RequestBody InfrastructuralSoftwareAddDto infrastructuralSoftwareAddDto){
		infrastructuralSoftwareAddDto.setTid(securityUtil.getAccount().getTenantId());
		return  infrastructuralSoftwareRibbonService.add(infrastructuralSoftwareAddDto);
	}

	/**
	 * 删除基础软件,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除基础软件", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete infrastructuralSoftware :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			infrastructuralSoftwareRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新基础软件
	 * @param infrastructuralSoftwareEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产基础软件(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public InfrastructuralSoftwareVO update(@RequestBody InfrastructuralSoftwareEditDto infrastructuralSoftwareEditDto, @ApiParam(value = "要查询的基础软件id") @PathVariable Long id){
		infrastructuralSoftwareEditDto.setTid(securityUtil.getAccount().getTenantId());
		InfrastructuralSoftwareVO vo = infrastructuralSoftwareRibbonService.merge(id, infrastructuralSoftwareEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询基础软件
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询基础软件", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public InfrastructuralSoftwareVO get(@ApiParam(value = "要查询的基础软件id") @PathVariable Long id) {

		InfrastructuralSoftwareVO vo = infrastructuralSoftwareRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询基础软件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询基础软件列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<InfrastructuralSoftwareVO> list(@RequestBody PageSearchRequest<InfrastructuralSoftwareCondition> pageSearchRequest){


		PageContent<InfrastructuralSoftwareVO> pageContent = infrastructuralSoftwareRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
