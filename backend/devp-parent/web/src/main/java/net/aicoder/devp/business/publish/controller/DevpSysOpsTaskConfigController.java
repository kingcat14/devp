package net.aicoder.devp.business.publish.controller;

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
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskConfig;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsTaskConfigService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsTaskConfigValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskConfigVO;

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
 * 管理配置文件设置
 * @author icode
 */
@Api(description = "配置文件设置", tags = "DevpSysOpsTaskConfig")
@RestController
@RequestMapping(value = "/publish/devpSysOpsTaskConfig")
public class DevpSysOpsTaskConfigController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskConfigController.class);


	@Autowired
	private DevpSysOpsTaskConfigService devpSysOpsTaskConfigService;


	@Autowired
	private DevpSysOpsTaskConfigValidator devpSysOpsTaskConfigValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsTaskConfigValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增配置文件设置
	 * @param devpSysOpsTaskConfigAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增配置文件设置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsTaskConfigVO add(@RequestBody @Valid DevpSysOpsTaskConfigAddDto devpSysOpsTaskConfigAddDto){
		DevpSysOpsTaskConfig devpSysOpsTaskConfig = new DevpSysOpsTaskConfig();
		BeanUtils.copyProperties(devpSysOpsTaskConfigAddDto, devpSysOpsTaskConfig);

		devpSysOpsTaskConfigService.add(devpSysOpsTaskConfig);

		return  initViewProperty(devpSysOpsTaskConfig);
	}

	/**
	 * 删除配置文件设置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除配置文件设置", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsTaskConfig :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsTaskConfigService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新配置文件设置
	 * @param devpSysOpsTaskConfigEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产配置文件设置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsTaskConfigVO update(@RequestBody @Valid DevpSysOpsTaskConfigEditDto devpSysOpsTaskConfigEditDto, @PathVariable Long id){
		DevpSysOpsTaskConfig devpSysOpsTaskConfig = new DevpSysOpsTaskConfig();
		BeanUtils.copyProperties(devpSysOpsTaskConfigEditDto, devpSysOpsTaskConfig);
		devpSysOpsTaskConfig.setId(id);
		devpSysOpsTaskConfigService.merge(devpSysOpsTaskConfig);

		DevpSysOpsTaskConfigVO vo = initViewProperty(devpSysOpsTaskConfig);
		return  vo;
	}

	/**
	 * 根据ID查询配置文件设置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询配置文件设置", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsTaskConfigVO get(@PathVariable Long id) {

		DevpSysOpsTaskConfig devpSysOpsTaskConfig = devpSysOpsTaskConfigService.find(id);

		DevpSysOpsTaskConfigVO vo = initViewProperty(devpSysOpsTaskConfig);
		return vo;
	}

	/**
	 * 查询配置文件设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询配置文件设置列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsTaskConfigVO> list(@RequestBody PageSearchRequest<DevpSysOpsTaskConfigCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<DevpSysOpsTaskConfig> page = devpSysOpsTaskConfigService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsTaskConfigVO> voList = new ArrayList<>();
		for(DevpSysOpsTaskConfig devpSysOpsTaskConfig : page.getContent()){
			voList.add(initViewProperty(devpSysOpsTaskConfig));
		}

		PageContent<DevpSysOpsTaskConfigVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出配置文件设置列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出配置文件设置列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsTaskConfigCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsTaskConfigCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsTaskConfigVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsTaskConfigVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsTaskConfigVO vo : voList){
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
            headMap.put("accessType" ,"访问方式");
            headMap.put("tplPath" ,"模板路径");
            headMap.put("tplFileName" ,"模板文件名");
            headMap.put("targetPath" ,"模板路径");
            headMap.put("targetFileName" ,"模板文件名");
            headMap.put("status" ,"状态");
            headMap.put("notes" ,"备注");
            headMap.put("prdRid" ,"产品编号");
            headMap.put("schemeRid" ,"部署方案编号");
            headMap.put("cmpRid" ,"组件编号");
            headMap.put("taskRid" ,"任务编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("配置文件设置");
        String fileName = new String(("配置文件设置_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsTaskConfigVO initViewProperty(DevpSysOpsTaskConfig devpSysOpsTaskConfig){

	    DevpSysOpsTaskConfigVO vo = new DevpSysOpsTaskConfigVO();
        BeanUtils.copyProperties(devpSysOpsTaskConfig, vo);


	    //初始化其他对象
        return vo;


	}


}
