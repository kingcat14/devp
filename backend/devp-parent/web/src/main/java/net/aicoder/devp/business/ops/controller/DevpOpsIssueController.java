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
import net.aicoder.devp.business.ops.domain.DevpOpsIssue;
import net.aicoder.devp.business.ops.dto.DevpOpsIssueCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsIssueAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsIssueEditDto;
import net.aicoder.devp.business.ops.service.DevpOpsIssueService;
import net.aicoder.devp.business.ops.valid.DevpOpsIssueValidator;
import net.aicoder.devp.business.ops.vo.DevpOpsIssueVO;

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
 * 管理问题记录
 * @author icode
 */
@Api(description = "问题记录", tags = "DevpOpsIssue")
@RestController
@RequestMapping(value = "/ops/devpOpsIssue")
public class DevpOpsIssueController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsIssueController.class);


	@Autowired
	private DevpOpsIssueService devpOpsIssueService;


	@Autowired
	private DevpOpsIssueValidator devpOpsIssueValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsIssueValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增问题记录
	 * @param devpOpsIssueAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增问题记录", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsIssueVO add(@RequestBody @Valid DevpOpsIssueAddDto devpOpsIssueAddDto){
		DevpOpsIssue devpOpsIssue = new DevpOpsIssue();
		BeanUtils.copyProperties(devpOpsIssueAddDto, devpOpsIssue);

		devpOpsIssueService.add(devpOpsIssue);

		return  initViewProperty(devpOpsIssue);
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
			devpOpsIssueService.delete(Long.parseLong(id));
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
	public	DevpOpsIssueVO update(@RequestBody @Valid DevpOpsIssueEditDto devpOpsIssueEditDto, @PathVariable Long id){
		DevpOpsIssue devpOpsIssue = new DevpOpsIssue();
		BeanUtils.copyProperties(devpOpsIssueEditDto, devpOpsIssue);
		devpOpsIssue.setId(id);
		devpOpsIssueService.merge(devpOpsIssue);

		DevpOpsIssueVO vo = initViewProperty(devpOpsIssue);
		return  vo;
	}

	/**
	 * 根据ID查询问题记录
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询问题记录", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsIssueVO get(@PathVariable Long id) {

		DevpOpsIssue devpOpsIssue = devpOpsIssueService.find(id);

		DevpOpsIssueVO vo = initViewProperty(devpOpsIssue);
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

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<DevpOpsIssue> page = devpOpsIssueService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpOpsIssueVO> voList = new ArrayList<>();
		for(DevpOpsIssue devpOpsIssue : page.getContent()){
			voList.add(initViewProperty(devpOpsIssue));
		}

		PageContent<DevpOpsIssueVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出问题记录列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出问题记录列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpOpsIssueCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpOpsIssueCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpOpsIssueVO> content = this.list(pageSearchRequest);

        List<DevpOpsIssueVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpOpsIssueVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"元素类型");
            headMap.put("code" ,"问题代码");
            headMap.put("name" ,"问题名称");
            headMap.put("alias" ,"问题别名");
            headMap.put("description" ,"问题描述");
            headMap.put("recordState" ,"记录状态");
            headMap.put("type" ,"问题类型");
            headMap.put("issue" ,"问题说明");
            headMap.put("reply" ,"问题回复");
            headMap.put("status" ,"处理状态");
            headMap.put("hasAttachment" ,"是否有附件");
            headMap.put("nexusType" ,"关联记录类型");
            headMap.put("nexusRid" ,"关联记录编号");
            headMap.put("nexusVersion" ,"关联记录版本");
            headMap.put("nexusPhase" ,"关联记录阶段");
            headMap.put("seq" ,"顺序号");
            headMap.put("parasCode" ,"参数定义标识");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");
            headMap.put("cmodifyUcode" ,"cmodify_ucode");

        String title = new String("问题记录");
        String fileName = new String(("问题记录_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpOpsIssueVO initViewProperty(DevpOpsIssue devpOpsIssue){

	    DevpOpsIssueVO vo = new DevpOpsIssueVO();
        BeanUtils.copyProperties(devpOpsIssue, vo);


	    //初始化其他对象
        return vo;


	}


}
