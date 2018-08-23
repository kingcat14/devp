package net.aicoder.devp.business.sys.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.sys.domain.DevpSysAttachment;
import net.aicoder.devp.business.sys.dto.DevpSysAttachmentCondition;
import net.aicoder.devp.business.sys.dto.DevpSysAttachmentAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysAttachmentEditDto;
import net.aicoder.devp.business.sys.service.DevpSysAttachmentService;
import net.aicoder.devp.business.sys.valid.DevpSysAttachmentValidator;
import net.aicoder.devp.business.sys.vo.DevpSysAttachmentVO;

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
@Api(description = "附件", tags = "DevpSysAttachment")
@RestController
@RequestMapping(value = "/sys/devpSysAttachment")
public class DevpSysAttachmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysAttachmentController.class);


	@Autowired
	private DevpSysAttachmentService devpSysAttachmentService;


	@Autowired
	private DevpSysAttachmentValidator devpSysAttachmentValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysAttachmentValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增附件
	 * @param devpSysAttachmentAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增附件", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysAttachmentVO add(@RequestBody @Valid DevpSysAttachmentAddDto devpSysAttachmentAddDto){
		DevpSysAttachment devpSysAttachment = new DevpSysAttachment();
		BeanUtils.copyProperties(devpSysAttachmentAddDto, devpSysAttachment);

		devpSysAttachmentService.add(devpSysAttachment);

		return  initViewProperty(devpSysAttachment);
	}

	/**
	 * 删除附件,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除附件", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysAttachment :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysAttachmentService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新附件
	 * @param devpSysAttachmentEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产附件(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysAttachmentVO update(@RequestBody @Valid DevpSysAttachmentEditDto devpSysAttachmentEditDto, @PathVariable Long id){
		DevpSysAttachment devpSysAttachment = new DevpSysAttachment();
		BeanUtils.copyProperties(devpSysAttachmentEditDto, devpSysAttachment);
		devpSysAttachment.setId(id);
		devpSysAttachmentService.merge(devpSysAttachment);

		DevpSysAttachmentVO vo = initViewProperty(devpSysAttachment);
		return  vo;
	}

	/**
	 * 根据ID查询附件
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询附件", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysAttachmentVO get(@PathVariable Long id) {

		DevpSysAttachment devpSysAttachment = devpSysAttachmentService.find(id);

		DevpSysAttachmentVO vo = initViewProperty(devpSysAttachment);
		return vo;
	}

	/**
	 * 查询附件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询附件列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysAttachmentVO> list(@RequestBody PageSearchRequest<DevpSysAttachmentCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysAttachment> page = devpSysAttachmentService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysAttachmentVO> voList = new ArrayList<>();
		for(DevpSysAttachment devpSysAttachment : page.getContent()){
			voList.add(initViewProperty(devpSysAttachment));
		}

		PageContent<DevpSysAttachmentVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(DevpSysAttachmentCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysAttachmentCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysAttachmentVO> content = this.list(pageSearchRequest);

        List<DevpSysAttachmentVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysAttachmentVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"元素类型");
            headMap.put("name" ,"附件名称");
            headMap.put("code" ,"附件代码");
            headMap.put("alias" ,"附件别名");
            headMap.put("description" ,"附件描述");
            headMap.put("recordState" ,"记录状态");
            headMap.put("type" ,"类型");
            headMap.put("subType" ,"子类型");
            headMap.put("fileType" ,"文件类型");
            headMap.put("accessType" ,"访问方式");
            headMap.put("workspace" ,"工作空间");
            headMap.put("path" ,"访问路径");
            headMap.put("fileName" ,"访问地址");
            headMap.put("fileVersion" ,"附件版本");
            headMap.put("objRid" ,"关联记录编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("tplRid" ,"模板编号");
            headMap.put("fileCreater" ,"文件创建器");
            headMap.put("fileEditor" ,"文件编辑器");
            headMap.put("revisability" ,"是否可修改");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("cmodifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("附件");
        String fileName = new String(("附件_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysAttachmentVO initViewProperty(DevpSysAttachment devpSysAttachment){

	    DevpSysAttachmentVO vo = new DevpSysAttachmentVO();
        BeanUtils.copyProperties(devpSysAttachment, vo);


	    //初始化其他对象
        return vo;


	}


}
