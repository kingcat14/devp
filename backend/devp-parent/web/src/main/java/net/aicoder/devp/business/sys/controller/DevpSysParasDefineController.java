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
import net.aicoder.devp.business.sys.domain.DevpSysParasDefine;
import net.aicoder.devp.business.sys.dto.DevpSysParasDefineCondition;
import net.aicoder.devp.business.sys.dto.DevpSysParasDefineAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysParasDefineEditDto;
import net.aicoder.devp.business.sys.service.DevpSysParasDefineService;
import net.aicoder.devp.business.sys.valid.DevpSysParasDefineValidator;
import net.aicoder.devp.business.sys.vo.DevpSysParasDefineVO;

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
 * 管理系统元素扩充信息
 * @author icode
 */
@Api(description = "系统元素扩充信息", tags = "DevpSysParasDefine")
@RestController
@RequestMapping(value = "/sys/devpSysParasDefine")
public class DevpSysParasDefineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysParasDefineController.class);


	@Autowired
	private DevpSysParasDefineService devpSysParasDefineService;


	@Autowired
	private DevpSysParasDefineValidator devpSysParasDefineValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysParasDefineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增系统元素扩充信息
	 * @param devpSysParasDefineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素扩充信息", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysParasDefineVO add(@RequestBody @Valid DevpSysParasDefineAddDto devpSysParasDefineAddDto){
		DevpSysParasDefine devpSysParasDefine = new DevpSysParasDefine();
		BeanUtils.copyProperties(devpSysParasDefineAddDto, devpSysParasDefine);

		devpSysParasDefineService.add(devpSysParasDefine);

		return  initViewProperty(devpSysParasDefine);
	}

	/**
	 * 删除系统元素扩充信息,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素扩充信息", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysParasDefine :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysParasDefineService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素扩充信息
	 * @param devpSysParasDefineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素扩充信息(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysParasDefineVO update(@RequestBody @Valid DevpSysParasDefineEditDto devpSysParasDefineEditDto, @PathVariable Long id){
		DevpSysParasDefine devpSysParasDefine = new DevpSysParasDefine();
		BeanUtils.copyProperties(devpSysParasDefineEditDto, devpSysParasDefine);
		devpSysParasDefine.setId(id);
		devpSysParasDefineService.merge(devpSysParasDefine);

		DevpSysParasDefineVO vo = initViewProperty(devpSysParasDefine);
		return  vo;
	}

	/**
	 * 根据ID查询系统元素扩充信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素扩充信息", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysParasDefineVO get(@PathVariable Long id) {

		DevpSysParasDefine devpSysParasDefine = devpSysParasDefineService.find(id);

		DevpSysParasDefineVO vo = initViewProperty(devpSysParasDefine);
		return vo;
	}

	/**
	 * 查询系统元素扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素扩充信息列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysParasDefineVO> list(@RequestBody PageSearchRequest<DevpSysParasDefineCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<DevpSysParasDefine> page = devpSysParasDefineService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysParasDefineVO> voList = new ArrayList<>();
		for(DevpSysParasDefine devpSysParasDefine : page.getContent()){
			voList.add(initViewProperty(devpSysParasDefine));
		}

		PageContent<DevpSysParasDefineVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出系统元素扩充信息列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出系统元素扩充信息列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysParasDefineCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysParasDefineCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysParasDefineVO> content = this.list(pageSearchRequest);

        List<DevpSysParasDefineVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysParasDefineVO vo : voList){
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
            headMap.put("content" ,"内容");
            headMap.put("notes" ,"备注");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("系统元素扩充信息");
        String fileName = new String(("系统元素扩充信息_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysParasDefineVO initViewProperty(DevpSysParasDefine devpSysParasDefine){

	    DevpSysParasDefineVO vo = new DevpSysParasDefineVO();
        BeanUtils.copyProperties(devpSysParasDefine, vo);


	    //初始化其他对象
        return vo;


	}


}
