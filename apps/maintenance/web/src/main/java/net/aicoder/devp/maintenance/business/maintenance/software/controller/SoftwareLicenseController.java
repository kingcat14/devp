package net.aicoder.devp.maintenance.business.maintenance.software.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.maintenance.business.software.dto.SoftwareLicenseCondition;
import net.aicoder.devp.maintenance.business.software.dto.SoftwareLicenseAddDto;
import net.aicoder.devp.maintenance.business.software.dto.SoftwareLicenseEditDto;
import net.aicoder.devp.maintenance.business.software.vo.SoftwareLicenseVO;
import net.aicoder.devp.maintenance.business.maintenance.software.service.SoftwareLicenseRibbonService;
import net.aicoder.devp.maintenance.business.maintenance.software.valid.SoftwareLicenseValidator;


import net.aicoder.devp.security.business.security.service.SecurityUtil;
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
 * 管理服务许可
 * @author icode
 */
@Api(description = "服务许可", tags = "SoftwareLicense")
@RestController
@RequestMapping(value = "/software/softwareLicense")
public class SoftwareLicenseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SoftwareLicenseController.class);


	@Autowired
	private SoftwareLicenseRibbonService softwareLicenseRibbonService;

	@Autowired
	SoftwareLicenseValidator softwareLicenseValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(softwareLicenseValidator);
	}

	/**
	 * 新增服务许可
	 * @param softwareLicenseAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增服务许可", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public SoftwareLicenseVO add(@RequestBody SoftwareLicenseAddDto softwareLicenseAddDto){
		softwareLicenseAddDto.setTid(SecurityUtil.getAccount().getTenantId());
		return  softwareLicenseRibbonService.add(softwareLicenseAddDto);
	}

	/**
	 * 删除服务许可,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除服务许可", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete softwareLicense :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			softwareLicenseRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新服务许可
	 * @param softwareLicenseEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产服务许可(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public SoftwareLicenseVO update(@RequestBody SoftwareLicenseEditDto softwareLicenseEditDto, @ApiParam(value = "要查询的服务许可id") @PathVariable Long id){
		softwareLicenseEditDto.setTid(SecurityUtil.getAccount().getTenantId());
		SoftwareLicenseVO vo = softwareLicenseRibbonService.merge(id, softwareLicenseEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询服务许可
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询服务许可", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public SoftwareLicenseVO get(@ApiParam(value = "要查询的服务许可id") @PathVariable Long id) {

		SoftwareLicenseVO vo = softwareLicenseRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询服务许可列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询服务许可列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<SoftwareLicenseVO> list(@RequestBody PageSearchRequest<SoftwareLicenseCondition> pageSearchRequest){


		PageContent<SoftwareLicenseVO> pageContent = softwareLicenseRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
