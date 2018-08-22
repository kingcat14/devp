package net.aicoder.devp.business.ops.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.domain.DevpOpsAttachment;
import net.aicoder.devp.business.ops.dto.DevpOpsAttachmentCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsAttachmentAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAttachmentEditDto;
import net.aicoder.devp.business.ops.service.DevpOpsAttachmentService;
import net.aicoder.devp.business.ops.valid.DevpOpsAttachmentValidator;
import net.aicoder.devp.business.ops.vo.DevpOpsAttachmentVO;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理附件
 * @author icode
 */
@Api(description = "附件", tags = "DevpOpsAttachment")
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
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增附件
	 * @param devpOpsAttachmentAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增附件", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsAttachmentVO add(@RequestBody @Valid DevpOpsAttachmentAddDto devpOpsAttachmentAddDto){
		DevpOpsAttachment devpOpsAttachment = new DevpOpsAttachment();
		BeanUtils.copyProperties(devpOpsAttachmentAddDto, devpOpsAttachment);

		devpOpsAttachmentService.add(devpOpsAttachment);

		return  initViewProperty(devpOpsAttachment);
	}

	/**
	 * 删除附件,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除附件", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsAttachment :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsAttachmentService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新附件
	 * @param devpOpsAttachmentEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产附件(修改全部字段,未传入置空)", httpMethod = "PUT")
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
	 * 根据ID查询附件
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询附件", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsAttachmentVO get(@PathVariable Long id) {

		DevpOpsAttachment devpOpsAttachment = devpOpsAttachmentService.find(id);

		DevpOpsAttachmentVO vo = initViewProperty(devpOpsAttachment);
		return vo;
	}

	/**
	 * 查询附件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询附件列表", httpMethod = "POST")
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

	/**
     * 导出附件列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出附件列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpOpsAttachmentCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpOpsAttachmentCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpOpsAttachmentVO> content = this.list(pageSearchRequest);

        List<DevpOpsAttachmentVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpOpsAttachmentVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("附件");
        String fileName = new String(("附件_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpOpsAttachmentVO initViewProperty(DevpOpsAttachment devpOpsAttachment){

	    DevpOpsAttachmentVO vo = new DevpOpsAttachmentVO();
        BeanUtils.copyProperties(devpOpsAttachment, vo);


	    //初始化其他对象
        return vo;


	}


}
