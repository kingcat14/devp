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
import net.aicoder.devp.business.sys.domain.DevpSysElementInfo;
import net.aicoder.devp.business.sys.dto.DevpSysElementInfoCondition;
import net.aicoder.devp.business.sys.dto.DevpSysElementInfoAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysElementInfoEditDto;
import net.aicoder.devp.business.sys.service.DevpSysElementInfoService;
import net.aicoder.devp.business.sys.valid.DevpSysElementInfoValidator;
import net.aicoder.devp.business.sys.vo.DevpSysElementInfoVO;

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
@Api(description = "系统元素扩充信息", tags = "DevpSysElementInfo")
@RestController
@RequestMapping(value = "/sys/devpSysElementInfo")
public class DevpSysElementInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElementInfoController.class);


	@Autowired
	private DevpSysElementInfoService devpSysElementInfoService;


	@Autowired
	private DevpSysElementInfoValidator devpSysElementInfoValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysElementInfoValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增系统元素扩充信息
	 * @param devpSysElementInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素扩充信息", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysElementInfoVO add(@RequestBody @Valid DevpSysElementInfoAddDto devpSysElementInfoAddDto){
		DevpSysElementInfo devpSysElementInfo = new DevpSysElementInfo();
		BeanUtils.copyProperties(devpSysElementInfoAddDto, devpSysElementInfo);

		devpSysElementInfoService.add(devpSysElementInfo);

		return  initViewProperty(devpSysElementInfo);
	}

	/**
	 * 删除系统元素扩充信息,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素扩充信息", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysElementInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysElementInfoService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素扩充信息
	 * @param devpSysElementInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素扩充信息(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysElementInfoVO update(@RequestBody @Valid DevpSysElementInfoEditDto devpSysElementInfoEditDto, @PathVariable Long id){
		DevpSysElementInfo devpSysElementInfo = new DevpSysElementInfo();
		BeanUtils.copyProperties(devpSysElementInfoEditDto, devpSysElementInfo);
		devpSysElementInfo.setId(id);
		devpSysElementInfoService.merge(devpSysElementInfo);

		DevpSysElementInfoVO vo = initViewProperty(devpSysElementInfo);
		return  vo;
	}

	/**
	 * 根据ID查询系统元素扩充信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素扩充信息", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysElementInfoVO get(@PathVariable Long id) {

		DevpSysElementInfo devpSysElementInfo = devpSysElementInfoService.find(id);

		DevpSysElementInfoVO vo = initViewProperty(devpSysElementInfo);
		return vo;
	}

	/**
	 * 查询系统元素扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素扩充信息列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysElementInfoVO> list(@RequestBody PageSearchRequest<DevpSysElementInfoCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysElementInfo> page = devpSysElementInfoService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysElementInfoVO> voList = new ArrayList<>();
		for(DevpSysElementInfo devpSysElementInfo : page.getContent()){
			voList.add(initViewProperty(devpSysElementInfo));
		}

		PageContent<DevpSysElementInfoVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(DevpSysElementInfoCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysElementInfoCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysElementInfoVO> content = this.list(pageSearchRequest);

        List<DevpSysElementInfoVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysElementInfoVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("系统元素扩充信息");
        String fileName = new String(("系统元素扩充信息_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysElementInfoVO initViewProperty(DevpSysElementInfo devpSysElementInfo){

	    DevpSysElementInfoVO vo = new DevpSysElementInfoVO();
        BeanUtils.copyProperties(devpSysElementInfo, vo);


	    //初始化其他对象
        return vo;


	}


}
