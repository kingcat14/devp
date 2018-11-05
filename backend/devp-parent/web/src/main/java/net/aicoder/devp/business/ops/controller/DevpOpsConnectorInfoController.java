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
import net.aicoder.devp.business.ops.domain.DevpOpsConnectorInfo;
import net.aicoder.devp.business.ops.dto.DevpOpsConnectorInfoCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsConnectorInfoAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsConnectorInfoEditDto;
import net.aicoder.devp.business.ops.service.DevpOpsConnectorInfoService;
import net.aicoder.devp.business.ops.valid.DevpOpsConnectorInfoValidator;
import net.aicoder.devp.business.ops.vo.DevpOpsConnectorInfoVO;

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
 * 管理运维元素连接扩充信息
 * @author icode
 */
@Api(description = "运维元素连接扩充信息", tags = "DevpOpsConnectorInfo")
@RestController
@RequestMapping(value = "/ops/devpOpsConnectorInfo")
public class DevpOpsConnectorInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsConnectorInfoController.class);


	@Autowired
	private DevpOpsConnectorInfoService devpOpsConnectorInfoService;


	@Autowired
	private DevpOpsConnectorInfoValidator devpOpsConnectorInfoValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsConnectorInfoValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运维元素连接扩充信息
	 * @param devpOpsConnectorInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运维元素连接扩充信息", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsConnectorInfoVO add(@RequestBody @Valid DevpOpsConnectorInfoAddDto devpOpsConnectorInfoAddDto){
		DevpOpsConnectorInfo devpOpsConnectorInfo = new DevpOpsConnectorInfo();
		BeanUtils.copyProperties(devpOpsConnectorInfoAddDto, devpOpsConnectorInfo);

		devpOpsConnectorInfoService.add(devpOpsConnectorInfo);

		return  initViewProperty(devpOpsConnectorInfo);
	}

	/**
	 * 删除运维元素连接扩充信息,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运维元素连接扩充信息", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsConnectorInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsConnectorInfoService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新运维元素连接扩充信息
	 * @param devpOpsConnectorInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运维元素连接扩充信息(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpOpsConnectorInfoVO update(@RequestBody @Valid DevpOpsConnectorInfoEditDto devpOpsConnectorInfoEditDto, @PathVariable Long id){
		DevpOpsConnectorInfo devpOpsConnectorInfo = new DevpOpsConnectorInfo();
		BeanUtils.copyProperties(devpOpsConnectorInfoEditDto, devpOpsConnectorInfo);
		devpOpsConnectorInfo.setId(id);
		devpOpsConnectorInfoService.merge(devpOpsConnectorInfo);

		DevpOpsConnectorInfoVO vo = initViewProperty(devpOpsConnectorInfo);
		return  vo;
	}

	/**
	 * 根据ID查询运维元素连接扩充信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运维元素连接扩充信息", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsConnectorInfoVO get(@PathVariable Long id) {

		DevpOpsConnectorInfo devpOpsConnectorInfo = devpOpsConnectorInfoService.find(id);

		DevpOpsConnectorInfoVO vo = initViewProperty(devpOpsConnectorInfo);
		return vo;
	}

	/**
	 * 查询运维元素连接扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运维元素连接扩充信息列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsConnectorInfoVO> list(@RequestBody PageSearchRequest<DevpOpsConnectorInfoCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<DevpOpsConnectorInfo> page = devpOpsConnectorInfoService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpOpsConnectorInfoVO> voList = new ArrayList<>();
		for(DevpOpsConnectorInfo devpOpsConnectorInfo : page.getContent()){
			voList.add(initViewProperty(devpOpsConnectorInfo));
		}

		PageContent<DevpOpsConnectorInfoVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出运维元素连接扩充信息列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出运维元素连接扩充信息列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpOpsConnectorInfoCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpOpsConnectorInfoCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpOpsConnectorInfoVO> content = this.list(pageSearchRequest);

        List<DevpOpsConnectorInfoVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpOpsConnectorInfoVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"元素类型");
            headMap.put("code" ,"扩展信息代码");
            headMap.put("name" ,"扩展信息名称");
            headMap.put("alias" ,"扩展信息别名");
            headMap.put("description" ,"扩展信息描述");
            headMap.put("recordState" ,"记录状态");
            headMap.put("contRid" ,"关联连接编号");
            headMap.put("srcElmRid" ,"来源元素编号");
            headMap.put("srcInstRid" ,"来源元素实例编号");
            headMap.put("destElmRid" ,"目的元素编号");
            headMap.put("destInstRid" ,"目的元素实例编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("typeCode" ,"设值方式代码");
            headMap.put("typeName" ,"设值方式名称");
            headMap.put("infoCode1" ,"扩展信息代码1");
            headMap.put("infoValue1" ,"扩展信息值1");
            headMap.put("infoCode2" ,"扩展信息代码2");
            headMap.put("infoValue2" ,"扩展信息值2");
            headMap.put("infoCode3" ,"扩展信息代码3");
            headMap.put("infoValue3" ,"扩展信息值3");
            headMap.put("infoCode4" ,"扩展信息代码4");
            headMap.put("infoValue4" ,"扩展信息值4");
            headMap.put("infoCode5" ,"扩展信息代码5");
            headMap.put("infoValue5" ,"扩展信息值5");
            headMap.put("notes" ,"备注");
            headMap.put("parasCode" ,"参数定义标识");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("运维元素连接扩充信息");
        String fileName = new String(("运维元素连接扩充信息_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpOpsConnectorInfoVO initViewProperty(DevpOpsConnectorInfo devpOpsConnectorInfo){

	    DevpOpsConnectorInfoVO vo = new DevpOpsConnectorInfoVO();
        BeanUtils.copyProperties(devpOpsConnectorInfo, vo);


	    //初始化其他对象
        return vo;


	}


}
