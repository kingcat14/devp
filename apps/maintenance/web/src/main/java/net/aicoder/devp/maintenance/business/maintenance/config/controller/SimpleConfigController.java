package net.aicoder.devp.maintenance.business.maintenance.config.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.maintenance.business.config.dto.SimpleConfigCondition;
import net.aicoder.devp.maintenance.business.config.dto.SimpleConfigAddDto;
import net.aicoder.devp.maintenance.business.config.dto.SimpleConfigEditDto;
import net.aicoder.devp.maintenance.business.config.vo.SimpleConfigVO;
import net.aicoder.devp.maintenance.business.maintenance.config.service.SimpleConfigRibbonService;
import net.aicoder.devp.maintenance.business.maintenance.config.valid.SimpleConfigValidator;
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
 * 管理通用配置
 * @author icode
 */
@Api(description = "通用配置", tags = "SimpleConfig")
@RestController
@RequestMapping(value = "/config/simpleConfig")
public class SimpleConfigController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleConfigController.class);


	@Autowired
	private SimpleConfigRibbonService simpleConfigRibbonService;

	@Autowired
	SimpleConfigValidator simpleConfigValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(simpleConfigValidator);
	}

	/**
	 * 新增通用配置
	 * @param simpleConfigAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增通用配置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public SimpleConfigVO add(@RequestBody SimpleConfigAddDto simpleConfigAddDto){
		simpleConfigAddDto.setTid(SecurityUtil.getAccount().getTenantId());
		return  simpleConfigRibbonService.add(simpleConfigAddDto);
	}

	/**
	 * 删除通用配置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除通用配置", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete simpleConfig :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			simpleConfigRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新通用配置
	 * @param simpleConfigEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产通用配置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public SimpleConfigVO update(@RequestBody SimpleConfigEditDto simpleConfigEditDto, @ApiParam(value = "要查询的通用配置id") @PathVariable Long id){

		SimpleConfigVO vo = simpleConfigRibbonService.merge(id, simpleConfigEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询通用配置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询通用配置", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public SimpleConfigVO get(@ApiParam(value = "要查询的通用配置id") @PathVariable Long id) {

		SimpleConfigVO vo = simpleConfigRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询通用配置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询通用配置列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<SimpleConfigVO> list(@RequestBody PageSearchRequest<SimpleConfigCondition> pageSearchRequest){


		PageContent<SimpleConfigVO> pageContent = simpleConfigRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
