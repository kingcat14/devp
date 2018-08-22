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
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipePlan;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsPipePlanService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsPipePlanValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsPipePlanVO;

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
 * 管理产品运维流水线执行计划
 * @author icode
 */
@Api(description = "产品运维流水线执行计划", tags = "DevpSysOpsPipePlan")
@RestController
@RequestMapping(value = "/publish/devpSysOpsPipePlan")
public class DevpSysOpsPipePlanController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipePlanController.class);


	@Autowired
	private DevpSysOpsPipePlanService devpSysOpsPipePlanService;


	@Autowired
	private DevpSysOpsPipePlanValidator devpSysOpsPipePlanValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsPipePlanValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增产品运维流水线执行计划
	 * @param devpSysOpsPipePlanAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品运维流水线执行计划", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsPipePlanVO add(@RequestBody @Valid DevpSysOpsPipePlanAddDto devpSysOpsPipePlanAddDto){
		DevpSysOpsPipePlan devpSysOpsPipePlan = new DevpSysOpsPipePlan();
		BeanUtils.copyProperties(devpSysOpsPipePlanAddDto, devpSysOpsPipePlan);

		devpSysOpsPipePlanService.add(devpSysOpsPipePlan);

		return  initViewProperty(devpSysOpsPipePlan);
	}

	/**
	 * 删除产品运维流水线执行计划,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品运维流水线执行计划", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsPipePlan :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsPipePlanService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品运维流水线执行计划
	 * @param devpSysOpsPipePlanEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品运维流水线执行计划(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsPipePlanVO update(@RequestBody @Valid DevpSysOpsPipePlanEditDto devpSysOpsPipePlanEditDto, @PathVariable Long id){
		DevpSysOpsPipePlan devpSysOpsPipePlan = new DevpSysOpsPipePlan();
		BeanUtils.copyProperties(devpSysOpsPipePlanEditDto, devpSysOpsPipePlan);
		devpSysOpsPipePlan.setId(id);
		devpSysOpsPipePlanService.merge(devpSysOpsPipePlan);

		DevpSysOpsPipePlanVO vo = initViewProperty(devpSysOpsPipePlan);
		return  vo;
	}

	/**
	 * 根据ID查询产品运维流水线执行计划
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品运维流水线执行计划", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsPipePlanVO get(@PathVariable Long id) {

		DevpSysOpsPipePlan devpSysOpsPipePlan = devpSysOpsPipePlanService.find(id);

		DevpSysOpsPipePlanVO vo = initViewProperty(devpSysOpsPipePlan);
		return vo;
	}

	/**
	 * 查询产品运维流水线执行计划列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品运维流水线执行计划列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsPipePlanVO> list(@RequestBody PageSearchRequest<DevpSysOpsPipePlanCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysOpsPipePlan> page = devpSysOpsPipePlanService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsPipePlanVO> voList = new ArrayList<>();
		for(DevpSysOpsPipePlan devpSysOpsPipePlan : page.getContent()){
			voList.add(initViewProperty(devpSysOpsPipePlan));
		}

		PageContent<DevpSysOpsPipePlanVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出产品运维流水线执行计划列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出产品运维流水线执行计划列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsPipePlanCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsPipePlanCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsPipePlanVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsPipePlanVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsPipePlanVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("产品运维流水线执行计划");
        String fileName = new String(("产品运维流水线执行计划_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsPipePlanVO initViewProperty(DevpSysOpsPipePlan devpSysOpsPipePlan){

	    DevpSysOpsPipePlanVO vo = new DevpSysOpsPipePlanVO();
        BeanUtils.copyProperties(devpSysOpsPipePlan, vo);


	    //初始化其他对象
        return vo;


	}


}
