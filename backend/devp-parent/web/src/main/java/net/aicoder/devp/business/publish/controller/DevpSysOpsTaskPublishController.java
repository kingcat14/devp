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
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskPublish;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsTaskPublishService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsTaskPublishValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskPublishVO;

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
 * 管理发布设置
 * @author icode
 */
@Api(description = "发布设置", tags = "DevpSysOpsTaskPublish")
@RestController
@RequestMapping(value = "/publish/devpSysOpsTaskPublish")
public class DevpSysOpsTaskPublishController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskPublishController.class);


	@Autowired
	private DevpSysOpsTaskPublishService devpSysOpsTaskPublishService;


	@Autowired
	private DevpSysOpsTaskPublishValidator devpSysOpsTaskPublishValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsTaskPublishValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增发布设置
	 * @param devpSysOpsTaskPublishAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增发布设置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsTaskPublishVO add(@RequestBody @Valid DevpSysOpsTaskPublishAddDto devpSysOpsTaskPublishAddDto){
		DevpSysOpsTaskPublish devpSysOpsTaskPublish = new DevpSysOpsTaskPublish();
		BeanUtils.copyProperties(devpSysOpsTaskPublishAddDto, devpSysOpsTaskPublish);

		devpSysOpsTaskPublishService.add(devpSysOpsTaskPublish);

		return  initViewProperty(devpSysOpsTaskPublish);
	}

	/**
	 * 删除发布设置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除发布设置", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsTaskPublish :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsTaskPublishService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新发布设置
	 * @param devpSysOpsTaskPublishEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产发布设置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsTaskPublishVO update(@RequestBody @Valid DevpSysOpsTaskPublishEditDto devpSysOpsTaskPublishEditDto, @PathVariable Long id){
		DevpSysOpsTaskPublish devpSysOpsTaskPublish = new DevpSysOpsTaskPublish();
		BeanUtils.copyProperties(devpSysOpsTaskPublishEditDto, devpSysOpsTaskPublish);
		devpSysOpsTaskPublish.setId(id);
		devpSysOpsTaskPublishService.merge(devpSysOpsTaskPublish);

		DevpSysOpsTaskPublishVO vo = initViewProperty(devpSysOpsTaskPublish);
		return  vo;
	}

	/**
	 * 根据ID查询发布设置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询发布设置", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsTaskPublishVO get(@PathVariable Long id) {

		DevpSysOpsTaskPublish devpSysOpsTaskPublish = devpSysOpsTaskPublishService.find(id);

		DevpSysOpsTaskPublishVO vo = initViewProperty(devpSysOpsTaskPublish);
		return vo;
	}

	/**
	 * 查询发布设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询发布设置列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsTaskPublishVO> list(@RequestBody PageSearchRequest<DevpSysOpsTaskPublishCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysOpsTaskPublish> page = devpSysOpsTaskPublishService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsTaskPublishVO> voList = new ArrayList<>();
		for(DevpSysOpsTaskPublish devpSysOpsTaskPublish : page.getContent()){
			voList.add(initViewProperty(devpSysOpsTaskPublish));
		}

		PageContent<DevpSysOpsTaskPublishVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出发布设置列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出发布设置列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsTaskPublishCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsTaskPublishCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsTaskPublishVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsTaskPublishVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsTaskPublishVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("发布设置");
        String fileName = new String(("发布设置_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsTaskPublishVO initViewProperty(DevpSysOpsTaskPublish devpSysOpsTaskPublish){

	    DevpSysOpsTaskPublishVO vo = new DevpSysOpsTaskPublishVO();
        BeanUtils.copyProperties(devpSysOpsTaskPublish, vo);


	    //初始化其他对象
        return vo;


	}


}
