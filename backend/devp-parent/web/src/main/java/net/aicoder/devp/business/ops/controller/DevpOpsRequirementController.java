package net.aicoder.devp.business.ops.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.domain.DevpOpsRequirement;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementEditDto;
import net.aicoder.devp.business.ops.service.DevpOpsRequirementService;
import net.aicoder.devp.business.ops.valid.DevpOpsRequirementValidator;
import net.aicoder.devp.business.ops.vo.DevpOpsRequirementVO;

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
 * 管理需求定义
 * @author icode
 */
@Api(description = "需求定义", tags = "DevpOpsRequirement")
@RestController
@RequestMapping(value = "/ops/devpOpsRequirement")
public class DevpOpsRequirementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsRequirementController.class);


	@Autowired
	private DevpOpsRequirementService devpOpsRequirementService;


	@Autowired
	private DevpOpsRequirementValidator devpOpsRequirementValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsRequirementValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增需求定义
	 * @param devpOpsRequirementAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增需求定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsRequirementVO add(@RequestBody @Valid DevpOpsRequirementAddDto devpOpsRequirementAddDto){
		DevpOpsRequirement devpOpsRequirement = new DevpOpsRequirement();
		BeanUtils.copyProperties(devpOpsRequirementAddDto, devpOpsRequirement);

		devpOpsRequirementService.add(devpOpsRequirement);

		return  initViewProperty(devpOpsRequirement);
	}

	/**
	 * 删除需求定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除需求定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsRequirement :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsRequirementService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新需求定义
	 * @param devpOpsRequirementEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产需求定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpOpsRequirementVO update(@RequestBody @Valid DevpOpsRequirementEditDto devpOpsRequirementEditDto, @PathVariable Long id){
		DevpOpsRequirement devpOpsRequirement = new DevpOpsRequirement();
		BeanUtils.copyProperties(devpOpsRequirementEditDto, devpOpsRequirement);
		devpOpsRequirement.setId(id);
		devpOpsRequirementService.merge(devpOpsRequirement);

		DevpOpsRequirementVO vo = initViewProperty(devpOpsRequirement);
		return  vo;
	}

	/**
	 * 根据ID查询需求定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询需求定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsRequirementVO get(@PathVariable Long id) {

		DevpOpsRequirement devpOpsRequirement = devpOpsRequirementService.find(id);

		DevpOpsRequirementVO vo = initViewProperty(devpOpsRequirement);
		return vo;
	}

	/**
	 * 查询需求定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询需求定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsRequirementVO> list(@RequestBody PageSearchRequest<DevpOpsRequirementCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<DevpOpsRequirement> page = devpOpsRequirementService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpOpsRequirementVO> voList = new ArrayList<>();
		for(DevpOpsRequirement devpOpsRequirement : page.getContent()){
			voList.add(initViewProperty(devpOpsRequirement));
		}

		PageContent<DevpOpsRequirementVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出需求定义列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出需求定义列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpOpsRequirementCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpOpsRequirementCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpOpsRequirementVO> content = this.list(pageSearchRequest);

        List<DevpOpsRequirementVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpOpsRequirementVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"元素类型");
            headMap.put("code" ,"需求代码");
            headMap.put("name" ,"需求名称");
            headMap.put("alias" ,"需求别名");
            headMap.put("description" ,"需求描述");
            headMap.put("nexusType" ,"关联记录类型");
            headMap.put("nexusRid" ,"关联记录编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("typeCode" ,"类型代码");
            headMap.put("typeName" ,"类型名称");
            headMap.put("content" ,"内容");
            headMap.put("hasAttachment" ,"是否有附件");
            headMap.put("stereotype" ,"构造型");
            headMap.put("scope" ,"访问控制范围");
            headMap.put("version" ,"版本");
            headMap.put("phase" ,"阶段");
            headMap.put("status" ,"状态");
            headMap.put("recordState" ,"记录状态");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("cmodifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("需求定义");
        String fileName = new String(("需求定义_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpOpsRequirementVO initViewProperty(DevpOpsRequirement devpOpsRequirement){

	    DevpOpsRequirementVO vo = new DevpOpsRequirementVO();
        BeanUtils.copyProperties(devpOpsRequirement, vo);


	    //初始化其他对象
        return vo;


	}


}
