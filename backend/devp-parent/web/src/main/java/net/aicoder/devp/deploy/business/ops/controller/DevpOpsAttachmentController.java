package net.aicoder.devp.deploy.business.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsAttachment;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAttachmentCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAttachmentAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAttachmentEditDto;
import net.aicoder.devp.deploy.business.ops.service.DevpOpsAttachmentService;
import net.aicoder.devp.deploy.business.ops.valid.DevpOpsAttachmentValidator;
import net.aicoder.devp.deploy.business.ops.vo.DevpOpsAttachmentVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
	private DevpOpsAttachmentService devpOpsAttachmentService;


	@Autowired
	private DevpOpsAttachmentValidator devpOpsAttachmentValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsAttachmentValidator);
	}

	/**
	 * 新增附件定义
	 * @param devpOpsAttachmentAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增附件定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsAttachmentVO add(@RequestBody @Valid DevpOpsAttachmentAddDto devpOpsAttachmentAddDto){
		DevpOpsAttachment devpOpsAttachment = new DevpOpsAttachment();
		BeanUtils.copyProperties(devpOpsAttachmentAddDto, devpOpsAttachment);

		devpOpsAttachmentService.add(devpOpsAttachment);

		return  initViewProperty(devpOpsAttachment);
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
			devpOpsAttachmentService.delete(Long.parseLong(id));
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
	public	DevpOpsAttachmentVO update(@RequestBody @Valid DevpOpsAttachmentEditDto devpOpsAttachmentEditDto, @PathVariable Long id){
		DevpOpsAttachment devpOpsAttachment = new DevpOpsAttachment();
		BeanUtils.copyProperties(devpOpsAttachmentEditDto, devpOpsAttachment);
		devpOpsAttachment.setId(id);
		devpOpsAttachmentService.merge(devpOpsAttachment);

		DevpOpsAttachmentVO vo = initViewProperty(devpOpsAttachment);
		return  vo;
	}

	/**
	 * 根据ID查询附件定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询附件定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsAttachmentVO get(@PathVariable Long id) {

		DevpOpsAttachment devpOpsAttachment = devpOpsAttachmentService.find(id);

		DevpOpsAttachmentVO vo = initViewProperty(devpOpsAttachment);
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

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpOpsAttachment> page = devpOpsAttachmentService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpOpsAttachmentVO> voList = new ArrayList<>();
		for(DevpOpsAttachment devpOpsAttachment : page.getContent()){
			voList.add(initViewProperty(devpOpsAttachment));
		}

		PageContent<DevpOpsAttachmentVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpOpsAttachmentVO initViewProperty(DevpOpsAttachment devpOpsAttachment){
	    DevpOpsAttachmentVO vo = new DevpOpsAttachmentVO();

        BeanUtils.copyProperties(devpOpsAttachment, vo);

	    //初始化其他对象
        return vo;
	}


}
