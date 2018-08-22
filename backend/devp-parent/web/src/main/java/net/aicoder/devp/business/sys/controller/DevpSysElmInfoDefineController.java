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
import net.aicoder.devp.business.sys.domain.DevpSysElmInfoDefine;
import net.aicoder.devp.business.sys.dto.DevpSysElmInfoDefineCondition;
import net.aicoder.devp.business.sys.dto.DevpSysElmInfoDefineAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysElmInfoDefineEditDto;
import net.aicoder.devp.business.sys.service.DevpSysElmInfoDefineService;
import net.aicoder.devp.business.sys.valid.DevpSysElmInfoDefineValidator;
import net.aicoder.devp.business.sys.vo.DevpSysElmInfoDefineVO;

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
 * 管理系统元素扩充信息定义
 * @author icode
 */
@Api(description = "系统元素扩充信息定义", tags = "DevpSysElmInfoDefine")
@RestController
@RequestMapping(value = "/sys/devpSysElmInfoDefine")
public class DevpSysElmInfoDefineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInfoDefineController.class);


	@Autowired
	private DevpSysElmInfoDefineService devpSysElmInfoDefineService;


	@Autowired
	private DevpSysElmInfoDefineValidator devpSysElmInfoDefineValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysElmInfoDefineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增系统元素扩充信息定义
	 * @param devpSysElmInfoDefineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素扩充信息定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysElmInfoDefineVO add(@RequestBody @Valid DevpSysElmInfoDefineAddDto devpSysElmInfoDefineAddDto){
		DevpSysElmInfoDefine devpSysElmInfoDefine = new DevpSysElmInfoDefine();
		BeanUtils.copyProperties(devpSysElmInfoDefineAddDto, devpSysElmInfoDefine);

		devpSysElmInfoDefineService.add(devpSysElmInfoDefine);

		return  initViewProperty(devpSysElmInfoDefine);
	}

	/**
	 * 删除系统元素扩充信息定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素扩充信息定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysElmInfoDefine :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysElmInfoDefineService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素扩充信息定义
	 * @param devpSysElmInfoDefineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素扩充信息定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysElmInfoDefineVO update(@RequestBody @Valid DevpSysElmInfoDefineEditDto devpSysElmInfoDefineEditDto, @PathVariable Long id){
		DevpSysElmInfoDefine devpSysElmInfoDefine = new DevpSysElmInfoDefine();
		BeanUtils.copyProperties(devpSysElmInfoDefineEditDto, devpSysElmInfoDefine);
		devpSysElmInfoDefine.setId(id);
		devpSysElmInfoDefineService.merge(devpSysElmInfoDefine);

		DevpSysElmInfoDefineVO vo = initViewProperty(devpSysElmInfoDefine);
		return  vo;
	}

	/**
	 * 根据ID查询系统元素扩充信息定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素扩充信息定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysElmInfoDefineVO get(@PathVariable Long id) {

		DevpSysElmInfoDefine devpSysElmInfoDefine = devpSysElmInfoDefineService.find(id);

		DevpSysElmInfoDefineVO vo = initViewProperty(devpSysElmInfoDefine);
		return vo;
	}

	/**
	 * 查询系统元素扩充信息定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素扩充信息定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysElmInfoDefineVO> list(@RequestBody PageSearchRequest<DevpSysElmInfoDefineCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysElmInfoDefine> page = devpSysElmInfoDefineService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysElmInfoDefineVO> voList = new ArrayList<>();
		for(DevpSysElmInfoDefine devpSysElmInfoDefine : page.getContent()){
			voList.add(initViewProperty(devpSysElmInfoDefine));
		}

		PageContent<DevpSysElmInfoDefineVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出系统元素扩充信息定义列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出系统元素扩充信息定义列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysElmInfoDefineCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysElmInfoDefineCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysElmInfoDefineVO> content = this.list(pageSearchRequest);

        List<DevpSysElmInfoDefineVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysElmInfoDefineVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("系统元素扩充信息定义");
        String fileName = new String(("系统元素扩充信息定义_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysElmInfoDefineVO initViewProperty(DevpSysElmInfoDefine devpSysElmInfoDefine){

	    DevpSysElmInfoDefineVO vo = new DevpSysElmInfoDefineVO();
        BeanUtils.copyProperties(devpSysElmInfoDefine, vo);


	    //初始化其他对象
        return vo;


	}


}
