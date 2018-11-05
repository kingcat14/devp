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
import net.aicoder.devp.business.ops.domain.DevpOpsCiGroup;
import net.aicoder.devp.business.ops.dto.DevpOpsCiGroupCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsCiGroupAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsCiGroupEditDto;
import net.aicoder.devp.business.ops.service.DevpOpsCiGroupService;
import net.aicoder.devp.business.ops.valid.DevpOpsCiGroupValidator;
import net.aicoder.devp.business.ops.vo.DevpOpsCiGroupVO;

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
 * 管理资产项目分组
 * @author icode
 */
@Api(description = "资产项目分组", tags = "DevpOpsCiGroup")
@RestController
@RequestMapping(value = "/ops/devpOpsCiGroup")
public class DevpOpsCiGroupController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsCiGroupController.class);


	@Autowired
	private DevpOpsCiGroupService devpOpsCiGroupService;


	@Autowired
	private DevpOpsCiGroupValidator devpOpsCiGroupValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsCiGroupValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增资产项目分组
	 * @param devpOpsCiGroupAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资产项目分组", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsCiGroupVO add(@RequestBody @Valid DevpOpsCiGroupAddDto devpOpsCiGroupAddDto){
		DevpOpsCiGroup devpOpsCiGroup = new DevpOpsCiGroup();
		BeanUtils.copyProperties(devpOpsCiGroupAddDto, devpOpsCiGroup);

		devpOpsCiGroupService.add(devpOpsCiGroup);

		return  initViewProperty(devpOpsCiGroup);
	}

	/**
	 * 删除资产项目分组,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资产项目分组", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsCiGroup :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsCiGroupService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资产项目分组
	 * @param devpOpsCiGroupEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资产项目分组(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpOpsCiGroupVO update(@RequestBody @Valid DevpOpsCiGroupEditDto devpOpsCiGroupEditDto, @PathVariable Long id){
		DevpOpsCiGroup devpOpsCiGroup = new DevpOpsCiGroup();
		BeanUtils.copyProperties(devpOpsCiGroupEditDto, devpOpsCiGroup);
		devpOpsCiGroup.setId(id);
		devpOpsCiGroupService.merge(devpOpsCiGroup);

		DevpOpsCiGroupVO vo = initViewProperty(devpOpsCiGroup);
		return  vo;
	}

	/**
	 * 根据ID查询资产项目分组
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资产项目分组", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsCiGroupVO get(@PathVariable Long id) {

		DevpOpsCiGroup devpOpsCiGroup = devpOpsCiGroupService.find(id);

		DevpOpsCiGroupVO vo = initViewProperty(devpOpsCiGroup);
		return vo;
	}

	/**
	 * 查询资产项目分组列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资产项目分组列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsCiGroupVO> list(@RequestBody PageSearchRequest<DevpOpsCiGroupCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<DevpOpsCiGroup> page = devpOpsCiGroupService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpOpsCiGroupVO> voList = new ArrayList<>();
		for(DevpOpsCiGroup devpOpsCiGroup : page.getContent()){
			voList.add(initViewProperty(devpOpsCiGroup));
		}

		PageContent<DevpOpsCiGroupVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出资产项目分组列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出资产项目分组列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpOpsCiGroupCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpOpsCiGroupCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpOpsCiGroupVO> content = this.list(pageSearchRequest);

        List<DevpOpsCiGroupVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpOpsCiGroupVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"元素类型");
            headMap.put("name" ,"名称");
            headMap.put("code" ,"代码");
            headMap.put("alias" ,"别名");
            headMap.put("description" ,"描述");
            headMap.put("recordState" ,"记录状态");
            headMap.put("typeCode" ,"类型代码");
            headMap.put("typeName" ,"类型名称");
            headMap.put("groupRid" ,"分组记录编号");
            headMap.put("ciRid" ,"资产记录编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("parasCode" ,"参数定义标识");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("cmodifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("资产项目分组");
        String fileName = new String(("资产项目分组_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpOpsCiGroupVO initViewProperty(DevpOpsCiGroup devpOpsCiGroup){

	    DevpOpsCiGroupVO vo = new DevpOpsCiGroupVO();
        BeanUtils.copyProperties(devpOpsCiGroup, vo);


	    //初始化其他对象
        return vo;


	}


}
