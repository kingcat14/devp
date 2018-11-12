package net.aicoder.maintenance.business.deploy.ops.controller;


import com.yunkang.saas.bootstrap.common.business.attachment.domain.Attachment;
import com.yunkang.saas.bootstrap.common.business.attachment.service.AttachmentService;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.bootstrap.application.business.authorize.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.dto.DevpOpsAttachmentCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsAttachmentAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAttachmentEditDto;
import net.aicoder.devp.business.ops.vo.DevpOpsAttachmentVO;
import net.aicoder.maintenance.business.deploy.ops.service.DevpOpsAttachmentRibbonService;
import net.aicoder.maintenance.business.deploy.ops.valid.DevpOpsAttachmentValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

/**
 * 管理附件定义
 * @author icode
 */
@Api(description = "附件定义", tags = "DevpOpsAttachment")
@RestController
@RequestMapping(value = "/ops/devpOpsAttachment")
public class DevpOpsAttachmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAttachmentController.class);

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private DevpOpsAttachmentRibbonService devpOpsAttachmentRibbonService;

	@Autowired
	DevpOpsAttachmentValidator devpOpsAttachmentValidator;

	@Autowired
	private AttachmentService attachmentService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsAttachmentValidator);
	}

	/**
	 * 新增附件定义
	 * @param addDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增附件定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsAttachmentVO add(@RequestBody DevpOpsAttachmentAddDto addDto){

		Attachment attachment = attachmentService.find(Long.valueOf(addDto.getCode()));

		addDto.setName(attachment.getName());
		addDto.setTypeName(attachment.getType());

		addDto.setTid(securityUtil.getAccount().getTid());
		return  devpOpsAttachmentRibbonService.add(addDto);
	}

	/**
	 * 删除附件定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除附件定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsAttachment :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsAttachmentRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新附件定义
	 * @param devpOpsAttachmentEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产附件定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpOpsAttachmentVO update(@RequestBody DevpOpsAttachmentEditDto devpOpsAttachmentEditDto, @ApiParam(value = "要查询的附件定义id") @PathVariable Long id){

		DevpOpsAttachmentVO vo = devpOpsAttachmentRibbonService.merge(id, devpOpsAttachmentEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询附件定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询附件定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpOpsAttachmentVO get(@ApiParam(value = "要查询的附件定义id") @PathVariable Long id) {

		DevpOpsAttachmentVO vo = devpOpsAttachmentRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询附件定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询附件定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsAttachmentVO> list(@RequestBody PageSearchRequest<DevpOpsAttachmentCondition> pageSearchRequest){


		PageContent<DevpOpsAttachmentVO> pageContent = devpOpsAttachmentRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


}
