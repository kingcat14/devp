package net.aicoder.devp.business.deploy.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyCmpRef;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefEditDto;
import net.aicoder.devp.business.deploy.service.DevpSysDpyCmpRefService;
import net.aicoder.devp.business.deploy.valid.DevpSysDpyCmpRefValidator;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyCmpRefVO;

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
 * 管理系统元素间关系
 * @author icode
 */
@Api(description = "系统元素间关系", tags = "DevpSysDpyCmpRef")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyCmpRef")
public class DevpSysDpyCmpRefController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyCmpRefController.class);


	@Autowired
	private DevpSysDpyCmpRefService devpSysDpyCmpRefService;


	@Autowired
	private DevpSysDpyCmpRefValidator devpSysDpyCmpRefValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyCmpRefValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增系统元素间关系
	 * @param devpSysDpyCmpRefAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素间关系", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyCmpRefVO add(@RequestBody @Valid DevpSysDpyCmpRefAddDto devpSysDpyCmpRefAddDto){
		DevpSysDpyCmpRef devpSysDpyCmpRef = new DevpSysDpyCmpRef();
		BeanUtils.copyProperties(devpSysDpyCmpRefAddDto, devpSysDpyCmpRef);

		devpSysDpyCmpRefService.add(devpSysDpyCmpRef);

		return  initViewProperty(devpSysDpyCmpRef);
	}

	/**
	 * 删除系统元素间关系,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素间关系", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyCmpRef :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyCmpRefService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素间关系
	 * @param devpSysDpyCmpRefEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素间关系(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyCmpRefVO update(@RequestBody @Valid DevpSysDpyCmpRefEditDto devpSysDpyCmpRefEditDto, @PathVariable Long id){
		DevpSysDpyCmpRef devpSysDpyCmpRef = new DevpSysDpyCmpRef();
		BeanUtils.copyProperties(devpSysDpyCmpRefEditDto, devpSysDpyCmpRef);
		devpSysDpyCmpRef.setId(id);
		devpSysDpyCmpRefService.merge(devpSysDpyCmpRef);

		DevpSysDpyCmpRefVO vo = initViewProperty(devpSysDpyCmpRef);
		return  vo;
	}

	/**
	 * 根据ID查询系统元素间关系
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素间关系", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyCmpRefVO get(@PathVariable Long id) {

		DevpSysDpyCmpRef devpSysDpyCmpRef = devpSysDpyCmpRefService.find(id);

		DevpSysDpyCmpRefVO vo = initViewProperty(devpSysDpyCmpRef);
		return vo;
	}

	/**
	 * 查询系统元素间关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素间关系列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyCmpRefVO> list(@RequestBody PageSearchRequest<DevpSysDpyCmpRefCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyCmpRef> page = devpSysDpyCmpRefService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyCmpRefVO> voList = new ArrayList<>();
		for(DevpSysDpyCmpRef devpSysDpyCmpRef : page.getContent()){
			voList.add(initViewProperty(devpSysDpyCmpRef));
		}

		PageContent<DevpSysDpyCmpRefVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出系统元素间关系列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出系统元素间关系列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysDpyCmpRefCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyCmpRefCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyCmpRefVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyCmpRefVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyCmpRefVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("系统元素间关系");
        String fileName = new String(("系统元素间关系_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysDpyCmpRefVO initViewProperty(DevpSysDpyCmpRef devpSysDpyCmpRef){

	    DevpSysDpyCmpRefVO vo = new DevpSysDpyCmpRefVO();
        BeanUtils.copyProperties(devpSysDpyCmpRef, vo);


	    //初始化其他对象
        return vo;


	}


}
