package net.aicoder.devp.business.sys.controller;

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
import net.aicoder.devp.business.sys.domain.DevpSysOpsCmpTask;
import net.aicoder.devp.business.sys.dto.DevpSysOpsCmpTaskCondition;
import net.aicoder.devp.business.sys.dto.DevpSysOpsCmpTaskAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysOpsCmpTaskEditDto;
import net.aicoder.devp.business.sys.service.DevpSysOpsCmpTaskService;
import net.aicoder.devp.business.sys.valid.DevpSysOpsCmpTaskValidator;
import net.aicoder.devp.business.sys.vo.DevpSysOpsCmpTaskVO;

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
 * 管理组件任务
 * @author icode
 */
@Api(description = "组件任务", tags = "DevpSysOpsCmpTask")
@RestController
@RequestMapping(value = "/sys/devpSysOpsCmpTask")
public class DevpSysOpsCmpTaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsCmpTaskController.class);


	@Autowired
	private DevpSysOpsCmpTaskService devpSysOpsCmpTaskService;


	@Autowired
	private DevpSysOpsCmpTaskValidator devpSysOpsCmpTaskValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsCmpTaskValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件任务
	 * @param devpSysOpsCmpTaskAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件任务", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsCmpTaskVO add(@RequestBody @Valid DevpSysOpsCmpTaskAddDto devpSysOpsCmpTaskAddDto){
		DevpSysOpsCmpTask devpSysOpsCmpTask = new DevpSysOpsCmpTask();
		BeanUtils.copyProperties(devpSysOpsCmpTaskAddDto, devpSysOpsCmpTask);

		devpSysOpsCmpTaskService.add(devpSysOpsCmpTask);

		return  initViewProperty(devpSysOpsCmpTask);
	}

	/**
	 * 删除组件任务,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件任务", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsCmpTask :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsCmpTaskService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新组件任务
	 * @param devpSysOpsCmpTaskEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产组件任务(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsCmpTaskVO update(@RequestBody @Valid DevpSysOpsCmpTaskEditDto devpSysOpsCmpTaskEditDto, @PathVariable Long id){
		DevpSysOpsCmpTask devpSysOpsCmpTask = new DevpSysOpsCmpTask();
		BeanUtils.copyProperties(devpSysOpsCmpTaskEditDto, devpSysOpsCmpTask);
		devpSysOpsCmpTask.setId(id);
		devpSysOpsCmpTaskService.merge(devpSysOpsCmpTask);

		DevpSysOpsCmpTaskVO vo = initViewProperty(devpSysOpsCmpTask);
		return  vo;
	}

	/**
	 * 根据ID查询组件任务
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件任务", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsCmpTaskVO get(@PathVariable Long id) {

		DevpSysOpsCmpTask devpSysOpsCmpTask = devpSysOpsCmpTaskService.find(id);

		DevpSysOpsCmpTaskVO vo = initViewProperty(devpSysOpsCmpTask);
		return vo;
	}

	/**
	 * 查询组件任务列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件任务列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsCmpTaskVO> list(@RequestBody PageSearchRequest<DevpSysOpsCmpTaskCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<DevpSysOpsCmpTask> page = devpSysOpsCmpTaskService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsCmpTaskVO> voList = new ArrayList<>();
		for(DevpSysOpsCmpTask devpSysOpsCmpTask : page.getContent()){
			voList.add(initViewProperty(devpSysOpsCmpTask));
		}

		PageContent<DevpSysOpsCmpTaskVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件任务列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件任务列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsCmpTaskCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsCmpTaskCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsCmpTaskVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsCmpTaskVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsCmpTaskVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"元素类型");
            headMap.put("name" ,"系统元素名称");
            headMap.put("code" ,"系统元素代码");
            headMap.put("alias" ,"系统元素别名");
            headMap.put("description" ,"系统元素描述");
            headMap.put("recordState" ,"记录状态");
            headMap.put("type" ,"类型");
            headMap.put("subType" ,"子类型");
            headMap.put("notes" ,"备注");
            headMap.put("prdRid" ,"产品编号");
            headMap.put("schemeRid" ,"部署方案编号");
            headMap.put("cmpRid" ,"组件编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("组件任务");
        String fileName = new String(("组件任务_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsCmpTaskVO initViewProperty(DevpSysOpsCmpTask devpSysOpsCmpTask){

	    DevpSysOpsCmpTaskVO vo = new DevpSysOpsCmpTaskVO();
        BeanUtils.copyProperties(devpSysOpsCmpTask, vo);


	    //初始化其他对象
        return vo;


	}


}
