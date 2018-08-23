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
import net.aicoder.devp.business.sys.domain.DevpSysIde;
import net.aicoder.devp.business.sys.dto.DevpSysIdeCondition;
import net.aicoder.devp.business.sys.dto.DevpSysIdeAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysIdeEditDto;
import net.aicoder.devp.business.sys.service.DevpSysIdeService;
import net.aicoder.devp.business.sys.valid.DevpSysIdeValidator;
import net.aicoder.devp.business.sys.vo.DevpSysIdeVO;

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
 * 管理开发工程
 * @author icode
 */
@Api(description = "开发工程", tags = "DevpSysIde")
@RestController
@RequestMapping(value = "/sys/devpSysIde")
public class DevpSysIdeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysIdeController.class);


	@Autowired
	private DevpSysIdeService devpSysIdeService;


	@Autowired
	private DevpSysIdeValidator devpSysIdeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysIdeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增开发工程
	 * @param devpSysIdeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增开发工程", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysIdeVO add(@RequestBody @Valid DevpSysIdeAddDto devpSysIdeAddDto){
		DevpSysIde devpSysIde = new DevpSysIde();
		BeanUtils.copyProperties(devpSysIdeAddDto, devpSysIde);

		devpSysIdeService.add(devpSysIde);

		return  initViewProperty(devpSysIde);
	}

	/**
	 * 删除开发工程,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除开发工程", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysIde :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysIdeService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新开发工程
	 * @param devpSysIdeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产开发工程(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysIdeVO update(@RequestBody @Valid DevpSysIdeEditDto devpSysIdeEditDto, @PathVariable Long id){
		DevpSysIde devpSysIde = new DevpSysIde();
		BeanUtils.copyProperties(devpSysIdeEditDto, devpSysIde);
		devpSysIde.setId(id);
		devpSysIdeService.merge(devpSysIde);

		DevpSysIdeVO vo = initViewProperty(devpSysIde);
		return  vo;
	}

	/**
	 * 根据ID查询开发工程
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询开发工程", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysIdeVO get(@PathVariable Long id) {

		DevpSysIde devpSysIde = devpSysIdeService.find(id);

		DevpSysIdeVO vo = initViewProperty(devpSysIde);
		return vo;
	}

	/**
	 * 查询开发工程列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询开发工程列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysIdeVO> list(@RequestBody PageSearchRequest<DevpSysIdeCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysIde> page = devpSysIdeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysIdeVO> voList = new ArrayList<>();
		for(DevpSysIde devpSysIde : page.getContent()){
			voList.add(initViewProperty(devpSysIde));
		}

		PageContent<DevpSysIdeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出开发工程列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出开发工程列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysIdeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysIdeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysIdeVO> content = this.list(pageSearchRequest);

        List<DevpSysIdeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysIdeVO vo : voList){
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
            headMap.put("stereotype" ,"构造型");
            headMap.put("scope" ,"范围");
            headMap.put("version" ,"版本");
            headMap.put("phase" ,"阶段");
            headMap.put("status" ,"状态");
            headMap.put("notes" ,"备注");
            headMap.put("prdRid" ,"产品编号");
            headMap.put("parentRid" ,"父包编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("开发工程");
        String fileName = new String(("开发工程_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysIdeVO initViewProperty(DevpSysIde devpSysIde){

	    DevpSysIdeVO vo = new DevpSysIdeVO();
        BeanUtils.copyProperties(devpSysIde, vo);


	    //初始化其他对象
        return vo;


	}


}
