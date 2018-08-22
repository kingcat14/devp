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
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeline;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsPipelineService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsPipelineValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsPipelineVO;

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
 * 管理产品运维流水线
 * @author icode
 */
@Api(description = "产品运维流水线", tags = "DevpSysOpsPipeline")
@RestController
@RequestMapping(value = "/publish/devpSysOpsPipeline")
public class DevpSysOpsPipelineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipelineController.class);


	@Autowired
	private DevpSysOpsPipelineService devpSysOpsPipelineService;


	@Autowired
	private DevpSysOpsPipelineValidator devpSysOpsPipelineValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsPipelineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增产品运维流水线
	 * @param devpSysOpsPipelineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品运维流水线", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsPipelineVO add(@RequestBody @Valid DevpSysOpsPipelineAddDto devpSysOpsPipelineAddDto){
		DevpSysOpsPipeline devpSysOpsPipeline = new DevpSysOpsPipeline();
		BeanUtils.copyProperties(devpSysOpsPipelineAddDto, devpSysOpsPipeline);

		devpSysOpsPipelineService.add(devpSysOpsPipeline);

		return  initViewProperty(devpSysOpsPipeline);
	}

	/**
	 * 删除产品运维流水线,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品运维流水线", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsPipeline :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsPipelineService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品运维流水线
	 * @param devpSysOpsPipelineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品运维流水线(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsPipelineVO update(@RequestBody @Valid DevpSysOpsPipelineEditDto devpSysOpsPipelineEditDto, @PathVariable Long id){
		DevpSysOpsPipeline devpSysOpsPipeline = new DevpSysOpsPipeline();
		BeanUtils.copyProperties(devpSysOpsPipelineEditDto, devpSysOpsPipeline);
		devpSysOpsPipeline.setId(id);
		devpSysOpsPipelineService.merge(devpSysOpsPipeline);

		DevpSysOpsPipelineVO vo = initViewProperty(devpSysOpsPipeline);
		return  vo;
	}

	/**
	 * 根据ID查询产品运维流水线
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品运维流水线", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsPipelineVO get(@PathVariable Long id) {

		DevpSysOpsPipeline devpSysOpsPipeline = devpSysOpsPipelineService.find(id);

		DevpSysOpsPipelineVO vo = initViewProperty(devpSysOpsPipeline);
		return vo;
	}

	/**
	 * 查询产品运维流水线列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品运维流水线列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsPipelineVO> list(@RequestBody PageSearchRequest<DevpSysOpsPipelineCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysOpsPipeline> page = devpSysOpsPipelineService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsPipelineVO> voList = new ArrayList<>();
		for(DevpSysOpsPipeline devpSysOpsPipeline : page.getContent()){
			voList.add(initViewProperty(devpSysOpsPipeline));
		}

		PageContent<DevpSysOpsPipelineVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出产品运维流水线列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出产品运维流水线列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsPipelineCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsPipelineCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsPipelineVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsPipelineVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsPipelineVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("产品运维流水线");
        String fileName = new String(("产品运维流水线_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsPipelineVO initViewProperty(DevpSysOpsPipeline devpSysOpsPipeline){

	    DevpSysOpsPipelineVO vo = new DevpSysOpsPipelineVO();
        BeanUtils.copyProperties(devpSysOpsPipeline, vo);


	    //初始化其他对象
        return vo;


	}


}
