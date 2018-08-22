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
import net.aicoder.devp.business.sys.domain.DevpDevFunaModule;
import net.aicoder.devp.business.sys.dto.DevpDevFunaModuleCondition;
import net.aicoder.devp.business.sys.dto.DevpDevFunaModuleAddDto;
import net.aicoder.devp.business.sys.dto.DevpDevFunaModuleEditDto;
import net.aicoder.devp.business.sys.service.DevpDevFunaModuleService;
import net.aicoder.devp.business.sys.valid.DevpDevFunaModuleValidator;
import net.aicoder.devp.business.sys.vo.DevpDevFunaModuleVO;

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
 * 管理功能模块
 * @author icode
 */
@Api(description = "功能模块", tags = "DevpDevFunaModule")
@RestController
@RequestMapping(value = "/sys/devpDevFunaModule")
public class DevpDevFunaModuleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpDevFunaModuleController.class);


	@Autowired
	private DevpDevFunaModuleService devpDevFunaModuleService;


	@Autowired
	private DevpDevFunaModuleValidator devpDevFunaModuleValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpDevFunaModuleValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增功能模块
	 * @param devpDevFunaModuleAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增功能模块", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpDevFunaModuleVO add(@RequestBody @Valid DevpDevFunaModuleAddDto devpDevFunaModuleAddDto){
		DevpDevFunaModule devpDevFunaModule = new DevpDevFunaModule();
		BeanUtils.copyProperties(devpDevFunaModuleAddDto, devpDevFunaModule);

		devpDevFunaModuleService.add(devpDevFunaModule);

		return  initViewProperty(devpDevFunaModule);
	}

	/**
	 * 删除功能模块,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除功能模块", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpDevFunaModule :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpDevFunaModuleService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新功能模块
	 * @param devpDevFunaModuleEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产功能模块(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpDevFunaModuleVO update(@RequestBody @Valid DevpDevFunaModuleEditDto devpDevFunaModuleEditDto, @PathVariable Long id){
		DevpDevFunaModule devpDevFunaModule = new DevpDevFunaModule();
		BeanUtils.copyProperties(devpDevFunaModuleEditDto, devpDevFunaModule);
		devpDevFunaModule.setId(id);
		devpDevFunaModuleService.merge(devpDevFunaModule);

		DevpDevFunaModuleVO vo = initViewProperty(devpDevFunaModule);
		return  vo;
	}

	/**
	 * 根据ID查询功能模块
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询功能模块", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpDevFunaModuleVO get(@PathVariable Long id) {

		DevpDevFunaModule devpDevFunaModule = devpDevFunaModuleService.find(id);

		DevpDevFunaModuleVO vo = initViewProperty(devpDevFunaModule);
		return vo;
	}

	/**
	 * 查询功能模块列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询功能模块列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpDevFunaModuleVO> list(@RequestBody PageSearchRequest<DevpDevFunaModuleCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpDevFunaModule> page = devpDevFunaModuleService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpDevFunaModuleVO> voList = new ArrayList<>();
		for(DevpDevFunaModule devpDevFunaModule : page.getContent()){
			voList.add(initViewProperty(devpDevFunaModule));
		}

		PageContent<DevpDevFunaModuleVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出功能模块列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出功能模块列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpDevFunaModuleCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpDevFunaModuleCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpDevFunaModuleVO> content = this.list(pageSearchRequest);

        List<DevpDevFunaModuleVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpDevFunaModuleVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("功能模块");
        String fileName = new String(("功能模块_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpDevFunaModuleVO initViewProperty(DevpDevFunaModule devpDevFunaModule){

	    DevpDevFunaModuleVO vo = new DevpDevFunaModuleVO();
        BeanUtils.copyProperties(devpDevFunaModule, vo);


	    //初始化其他对象
        return vo;


	}


}
