package net.aicoder.devp.business.publish.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskCompile;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsTaskCompileService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsTaskCompileValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskCompileVO;

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
 * 管理编译设置
 * @author icode
 */
@Api(description = "编译设置", tags = "DevpSysOpsTaskCompile")
@RestController
@RequestMapping(value = "/publish/devpSysOpsTaskCompile")
public class DevpSysOpsTaskCompileController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskCompileController.class);


	@Autowired
	private DevpSysOpsTaskCompileService devpSysOpsTaskCompileService;


	@Autowired
	private DevpSysOpsTaskCompileValidator devpSysOpsTaskCompileValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsTaskCompileValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增编译设置
	 * @param devpSysOpsTaskCompileAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增编译设置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsTaskCompileVO add(@RequestBody @Valid DevpSysOpsTaskCompileAddDto devpSysOpsTaskCompileAddDto){
		DevpSysOpsTaskCompile devpSysOpsTaskCompile = new DevpSysOpsTaskCompile();
		BeanUtils.copyProperties(devpSysOpsTaskCompileAddDto, devpSysOpsTaskCompile);

		devpSysOpsTaskCompileService.add(devpSysOpsTaskCompile);

		return  initViewProperty(devpSysOpsTaskCompile);
	}

	/**
	 * 删除编译设置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除编译设置", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsTaskCompile :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsTaskCompileService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新编译设置
	 * @param devpSysOpsTaskCompileEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产编译设置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsTaskCompileVO update(@RequestBody @Valid DevpSysOpsTaskCompileEditDto devpSysOpsTaskCompileEditDto, @PathVariable Long id){
		DevpSysOpsTaskCompile devpSysOpsTaskCompile = new DevpSysOpsTaskCompile();
		BeanUtils.copyProperties(devpSysOpsTaskCompileEditDto, devpSysOpsTaskCompile);
		devpSysOpsTaskCompile.setId(id);
		devpSysOpsTaskCompileService.merge(devpSysOpsTaskCompile);

		DevpSysOpsTaskCompileVO vo = initViewProperty(devpSysOpsTaskCompile);
		return  vo;
	}

	/**
	 * 根据ID查询编译设置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询编译设置", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsTaskCompileVO get(@PathVariable Long id) {

		DevpSysOpsTaskCompile devpSysOpsTaskCompile = devpSysOpsTaskCompileService.find(id);

		DevpSysOpsTaskCompileVO vo = initViewProperty(devpSysOpsTaskCompile);
		return vo;
	}

	/**
	 * 查询编译设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询编译设置列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsTaskCompileVO> list(@RequestBody PageSearchRequest<DevpSysOpsTaskCompileCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysOpsTaskCompile> page = devpSysOpsTaskCompileService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsTaskCompileVO> voList = new ArrayList<>();
		for(DevpSysOpsTaskCompile devpSysOpsTaskCompile : page.getContent()){
			voList.add(initViewProperty(devpSysOpsTaskCompile));
		}

		PageContent<DevpSysOpsTaskCompileVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出编译设置列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出编译设置列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsTaskCompileCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsTaskCompileCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsTaskCompileVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsTaskCompileVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsTaskCompileVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("编译设置");
        String fileName = new String(("编译设置_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsTaskCompileVO initViewProperty(DevpSysOpsTaskCompile devpSysOpsTaskCompile){

	    DevpSysOpsTaskCompileVO vo = new DevpSysOpsTaskCompileVO();
        BeanUtils.copyProperties(devpSysOpsTaskCompile, vo);


	    //初始化其他对象
        return vo;


	}


}
