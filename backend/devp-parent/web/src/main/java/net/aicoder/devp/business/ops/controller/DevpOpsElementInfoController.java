package net.aicoder.devp.business.ops.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.domain.DevpOpsElementInfo;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoEditDto;
import net.aicoder.devp.business.ops.service.DevpOpsElementInfoService;
import net.aicoder.devp.business.ops.valid.DevpOpsElementInfoValidator;
import net.aicoder.devp.business.ops.vo.DevpOpsElementInfoVO;

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
 * 管理运维元素扩充信息
 * @author icode
 */
@Api(description = "运维元素扩充信息", tags = "DevpOpsElementInfo")
@RestController
@RequestMapping(value = "/ops/devpOpsElementInfo")
public class DevpOpsElementInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsElementInfoController.class);


	@Autowired
	private DevpOpsElementInfoService devpOpsElementInfoService;


	@Autowired
	private DevpOpsElementInfoValidator devpOpsElementInfoValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsElementInfoValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运维元素扩充信息
	 * @param devpOpsElementInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运维元素扩充信息", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsElementInfoVO add(@RequestBody @Valid DevpOpsElementInfoAddDto devpOpsElementInfoAddDto){
		DevpOpsElementInfo devpOpsElementInfo = new DevpOpsElementInfo();
		BeanUtils.copyProperties(devpOpsElementInfoAddDto, devpOpsElementInfo);

		devpOpsElementInfoService.add(devpOpsElementInfo);

		return  initViewProperty(devpOpsElementInfo);
	}

	/**
	 * 删除运维元素扩充信息,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运维元素扩充信息", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsElementInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsElementInfoService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新运维元素扩充信息
	 * @param devpOpsElementInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运维元素扩充信息(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpOpsElementInfoVO update(@RequestBody @Valid DevpOpsElementInfoEditDto devpOpsElementInfoEditDto, @PathVariable Long id){
		DevpOpsElementInfo devpOpsElementInfo = new DevpOpsElementInfo();
		BeanUtils.copyProperties(devpOpsElementInfoEditDto, devpOpsElementInfo);
		devpOpsElementInfo.setId(id);
		devpOpsElementInfoService.merge(devpOpsElementInfo);

		DevpOpsElementInfoVO vo = initViewProperty(devpOpsElementInfo);
		return  vo;
	}

	/**
	 * 根据ID查询运维元素扩充信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运维元素扩充信息", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsElementInfoVO get(@PathVariable Long id) {

		DevpOpsElementInfo devpOpsElementInfo = devpOpsElementInfoService.find(id);

		DevpOpsElementInfoVO vo = initViewProperty(devpOpsElementInfo);
		return vo;
	}

	/**
	 * 查询运维元素扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运维元素扩充信息列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsElementInfoVO> list(@RequestBody PageSearchRequest<DevpOpsElementInfoCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpOpsElementInfo> page = devpOpsElementInfoService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpOpsElementInfoVO> voList = new ArrayList<>();
		for(DevpOpsElementInfo devpOpsElementInfo : page.getContent()){
			voList.add(initViewProperty(devpOpsElementInfo));
		}

		PageContent<DevpOpsElementInfoVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出运维元素扩充信息列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出运维元素扩充信息列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpOpsElementInfoCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpOpsElementInfoCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpOpsElementInfoVO> content = this.list(pageSearchRequest);

        List<DevpOpsElementInfoVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpOpsElementInfoVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("运维元素扩充信息");
        String fileName = new String(("运维元素扩充信息_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpOpsElementInfoVO initViewProperty(DevpOpsElementInfo devpOpsElementInfo){

	    DevpOpsElementInfoVO vo = new DevpOpsElementInfoVO();
        BeanUtils.copyProperties(devpOpsElementInfo, vo);


	    //初始化其他对象
        return vo;


	}


}
