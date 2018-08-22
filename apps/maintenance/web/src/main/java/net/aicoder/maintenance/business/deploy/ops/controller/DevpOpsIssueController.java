package net.aicoder.maintenance.business.deploy.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.business.application.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.dto.DevpOpsIssueCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsIssueAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsIssueEditDto;
import net.aicoder.devp.business.ops.vo.DevpOpsIssueVO;
import net.aicoder.maintenance.business.deploy.ops.service.DevpOpsIssueRibbonService;
import net.aicoder.maintenance.business.deploy.ops.valid.DevpOpsIssueValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

/**
 * 管理问题记录
 * @author icode
 */
@Api(description = "问题记录", tags = "DevpOpsIssue")
@RestController
@RequestMapping(value = "/ops/devpOpsIssue")
public class DevpOpsIssueController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsIssueController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private DevpOpsIssueRibbonService devpOpsIssueRibbonService;

	@Autowired
	DevpOpsIssueValidator devpOpsIssueValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsIssueValidator);
	}

	/**
	 * 新增问题记录
	 * @param devpOpsIssueAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增问题记录", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsIssueVO add(@RequestBody DevpOpsIssueAddDto devpOpsIssueAddDto){
		devpOpsIssueAddDto.setTid(securityUtil.getAccount().getTenantId());
		return  devpOpsIssueRibbonService.add(devpOpsIssueAddDto);
	}

	/**
	 * 删除问题记录,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除问题记录", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsIssue :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsIssueRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新问题记录
	 * @param devpOpsIssueEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产问题记录(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpOpsIssueVO update(@RequestBody DevpOpsIssueEditDto devpOpsIssueEditDto, @ApiParam(value = "要查询的问题记录id") @PathVariable Long id){

		DevpOpsIssueVO vo = devpOpsIssueRibbonService.merge(id, devpOpsIssueEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询问题记录
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询问题记录", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpOpsIssueVO get(@ApiParam(value = "要查询的问题记录id") @PathVariable Long id) {

		DevpOpsIssueVO vo = devpOpsIssueRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询问题记录列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询问题记录列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsIssueVO> list(@RequestBody PageSearchRequest<DevpOpsIssueCondition> pageSearchRequest){


		PageContent<DevpOpsIssueVO> pageContent = devpOpsIssueRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
