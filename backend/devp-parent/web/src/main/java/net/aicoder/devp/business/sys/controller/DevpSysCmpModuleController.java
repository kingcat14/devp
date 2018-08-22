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
import net.aicoder.devp.business.sys.domain.DevpSysCmpModule;
import net.aicoder.devp.business.sys.dto.DevpSysCmpModuleCondition;
import net.aicoder.devp.business.sys.dto.DevpSysCmpModuleAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysCmpModuleEditDto;
import net.aicoder.devp.business.sys.service.DevpSysCmpModuleService;
import net.aicoder.devp.business.sys.valid.DevpSysCmpModuleValidator;
import net.aicoder.devp.business.sys.vo.DevpSysCmpModuleVO;

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
 * 管理组件对应模块
 * @author icode
 */
@Api(description = "组件对应模块", tags = "DevpSysCmpModule")
@RestController
@RequestMapping(value = "/sys/devpSysCmpModule")
public class DevpSysCmpModuleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysCmpModuleController.class);


	@Autowired
	private DevpSysCmpModuleService devpSysCmpModuleService;


	@Autowired
	private DevpSysCmpModuleValidator devpSysCmpModuleValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysCmpModuleValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件对应模块
	 * @param devpSysCmpModuleAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件对应模块", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysCmpModuleVO add(@RequestBody @Valid DevpSysCmpModuleAddDto devpSysCmpModuleAddDto){
		DevpSysCmpModule devpSysCmpModule = new DevpSysCmpModule();
		BeanUtils.copyProperties(devpSysCmpModuleAddDto, devpSysCmpModule);

		devpSysCmpModuleService.add(devpSysCmpModule);

		return  initViewProperty(devpSysCmpModule);
	}

	/**
	 * 删除组件对应模块,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件对应模块", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysCmpModule :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysCmpModuleService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新组件对应模块
	 * @param devpSysCmpModuleEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产组件对应模块(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysCmpModuleVO update(@RequestBody @Valid DevpSysCmpModuleEditDto devpSysCmpModuleEditDto, @PathVariable Long id){
		DevpSysCmpModule devpSysCmpModule = new DevpSysCmpModule();
		BeanUtils.copyProperties(devpSysCmpModuleEditDto, devpSysCmpModule);
		devpSysCmpModule.setId(id);
		devpSysCmpModuleService.merge(devpSysCmpModule);

		DevpSysCmpModuleVO vo = initViewProperty(devpSysCmpModule);
		return  vo;
	}

	/**
	 * 根据ID查询组件对应模块
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件对应模块", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysCmpModuleVO get(@PathVariable Long id) {

		DevpSysCmpModule devpSysCmpModule = devpSysCmpModuleService.find(id);

		DevpSysCmpModuleVO vo = initViewProperty(devpSysCmpModule);
		return vo;
	}

	/**
	 * 查询组件对应模块列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件对应模块列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysCmpModuleVO> list(@RequestBody PageSearchRequest<DevpSysCmpModuleCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysCmpModule> page = devpSysCmpModuleService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysCmpModuleVO> voList = new ArrayList<>();
		for(DevpSysCmpModule devpSysCmpModule : page.getContent()){
			voList.add(initViewProperty(devpSysCmpModule));
		}

		PageContent<DevpSysCmpModuleVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件对应模块列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件对应模块列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysCmpModuleCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysCmpModuleCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysCmpModuleVO> content = this.list(pageSearchRequest);

        List<DevpSysCmpModuleVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysCmpModuleVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("组件对应模块");
        String fileName = new String(("组件对应模块_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysCmpModuleVO initViewProperty(DevpSysCmpModule devpSysCmpModule){

	    DevpSysCmpModuleVO vo = new DevpSysCmpModuleVO();
        BeanUtils.copyProperties(devpSysCmpModule, vo);


	    //初始化其他对象
        return vo;


	}


}
