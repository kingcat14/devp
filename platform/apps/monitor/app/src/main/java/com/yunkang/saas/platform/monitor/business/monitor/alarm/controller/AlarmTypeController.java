package com.yunkang.saas.platform.monitor.business.monitor.alarm.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.alarm.domain.AlarmType;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmTypeAddDto;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmTypeCondition;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmTypeEditDto;
import com.yunkang.saas.platform.monitor.business.alarm.service.AlarmTypeService;
import com.yunkang.saas.platform.monitor.business.alarm.valid.AlarmTypeValidator;
import com.yunkang.saas.platform.monitor.business.alarm.vo.AlarmTypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理告警类型
 * @author icode
 */
@Api(description = "告警类型", tags = "AlarmType")
@RestController
@RequestMapping(value =  "/monitor/alarm/alarmtype")
public class AlarmTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlarmTypeController.class);

	@Autowired
	private AlarmTypeService alarmTypeService;


	@Autowired
	private AlarmTypeValidator alarmTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(alarmTypeValidator);
	}

	/**
	 * 新增告警类型
	 * @param alarmTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增告警类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
    @SaaSAnnotation
	public AlarmTypeVO add(@RequestBody @Valid AlarmTypeAddDto alarmTypeAddDto){

		AlarmType alarmType = new AlarmType();
		BeanUtils.copyProperties(alarmTypeAddDto, alarmType);
		alarmTypeService.add(alarmType);

		return  initViewProperty(alarmType);
	}

	/**
	 * 删除告警类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除告警类型", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete alarmType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			alarmTypeService.delete(id);
		}

	}

	/**
	 * 更新告警类型
	 * @param alarmTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产告警类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public	AlarmTypeVO update(@RequestBody @Valid AlarmTypeEditDto alarmTypeEditDto, @PathVariable String id){
		AlarmType alarmType = new AlarmType();
		BeanUtils.copyProperties(alarmTypeEditDto, alarmType);
		alarmType.setId(id);
		alarmTypeService.merge(alarmType);

		AlarmTypeVO vo = initViewProperty(alarmType);
		return  vo;
	}

	/**
	 * 根据ID查询告警类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询告警类型", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public  AlarmTypeVO get(@PathVariable String id) {

		AlarmType alarmType = alarmTypeService.find(id);
		if(alarmType == null){
			throw new ResourceNotFoundException("找不到指定的告警类型，请检查ID");
		}
		AlarmTypeVO vo = initViewProperty(alarmType);
		return vo;
	}

	/**
	 * 查询告警类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询告警类型列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@SaaSAnnotation(conditionClass = AlarmTypeCondition.class)
	public PageContent<AlarmTypeVO> list(@RequestBody PageSearchRequest<AlarmTypeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
		
		Page<AlarmType> page = alarmTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AlarmTypeVO> voList = new ArrayList<>();
		for(AlarmType alarmType : page.getContent()){
			voList.add(initViewProperty(alarmType));
		}

		PageContent<AlarmTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出告警类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出告警类型列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(AlarmTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AlarmTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AlarmTypeVO> content = this.list(pageSearchRequest);

        List<AlarmTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AlarmTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("告警类型");
        String fileName = new String(("告警类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AlarmTypeVO initViewProperty(AlarmType alarmType){

	    AlarmTypeVO vo = new AlarmTypeVO();
        BeanUtils.copyProperties(alarmType, vo);


	    //初始化其他对象
        return vo;


	}


}
