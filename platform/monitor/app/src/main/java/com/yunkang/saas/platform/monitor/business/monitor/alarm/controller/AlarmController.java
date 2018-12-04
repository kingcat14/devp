package com.yunkang.saas.platform.monitor.business.monitor.alarm.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.alarm.domain.Alarm;
import com.yunkang.saas.platform.monitor.business.alarm.domain.AlarmType;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmAddDto;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmCondition;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmEditDto;
import com.yunkang.saas.platform.monitor.business.alarm.service.AlarmService;
import com.yunkang.saas.platform.monitor.business.alarm.service.AlarmTypeService;
import com.yunkang.saas.platform.monitor.business.alarm.valid.AlarmValidator;
import com.yunkang.saas.platform.monitor.business.alarm.vo.AlarmTypeVO;
import com.yunkang.saas.platform.monitor.business.alarm.vo.AlarmVO;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.app.vo.ApplicationVO;
import com.yunkang.saas.platform.monitor.business.indicator.domain.Indicator;
import com.yunkang.saas.platform.monitor.business.indicator.service.IndicatorService;
import com.yunkang.saas.platform.monitor.business.indicator.vo.IndicatorVO;
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
 * 管理告警
 * @author icode
 */
@Api(description = "告警", tags = "Alarm")
@RestController
@RequestMapping(value =  "/monitor/alarm/alarm")
public class AlarmController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlarmController.class);

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private AlarmTypeService alarmTypeService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private IndicatorService indicatorService;

	@Autowired
	private AlarmValidator alarmValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(alarmValidator);
	}

	/**
	 * 新增告警
	 * @param alarmAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增告警", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
    @SaaSAnnotation
	public AlarmVO add(@RequestBody @Valid AlarmAddDto alarmAddDto){

		Alarm alarm = new Alarm();
		BeanUtils.copyProperties(alarmAddDto, alarm);
		alarmService.add(alarm);

		return  initViewProperty(alarm);
	}

	/**
	 * 删除告警,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除告警", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete alarm :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			alarmService.delete(id);
		}

	}

	/**
	 * 更新告警
	 * @param alarmEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产告警(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public	AlarmVO update(@RequestBody @Valid AlarmEditDto alarmEditDto, @PathVariable String id){
		Alarm alarm = new Alarm();
		BeanUtils.copyProperties(alarmEditDto, alarm);
		alarm.setId(id);
		alarmService.merge(alarm);

		AlarmVO vo = initViewProperty(alarm);
		return  vo;
	}

	/**
	 * 根据ID查询告警
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询告警", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public  AlarmVO get(@PathVariable String id) {

		Alarm alarm = alarmService.find(id);
		if(alarm == null){
			throw new ResourceNotFoundException("找不到指定的告警，请检查ID");
		}
		AlarmVO vo = initViewProperty(alarm);
		return vo;
	}

	/**
	 * 查询告警列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询告警列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@SaaSAnnotation(conditionClass = AlarmCondition.class)
	public PageContent<AlarmVO> list(@RequestBody PageSearchRequest<AlarmCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
		
		Page<Alarm> page = alarmService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AlarmVO> voList = new ArrayList<>();
		for(Alarm alarm : page.getContent()){
			voList.add(initViewProperty(alarm));
		}

		PageContent<AlarmVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出告警列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出告警列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(AlarmCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AlarmCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AlarmVO> content = this.list(pageSearchRequest);

        List<AlarmVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AlarmVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("type" ,"类型");
            headMap.put("app" ,"程序");
            headMap.put("counter" ,"指标");
            headMap.put("value" ,"指标值");
            headMap.put("status" ,"状态");
            headMap.put("occurTime" ,"发生时间");
            headMap.put("lastOccurTime" ,"最后发生时间");
            headMap.put("disappearTime" ,"消失时间");
            headMap.put("occurCount" ,"发生次数");
            headMap.put("content" ,"内容");

        String title = new String("告警");
        String fileName = new String(("告警_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AlarmVO initViewProperty(Alarm alarm){

	    AlarmVO vo = new AlarmVO();
        BeanUtils.copyProperties(alarm, vo);


	    //初始化其他对象
	    initTypePropertyGroup(vo, alarm);
	    initAppPropertyGroup(vo, alarm);
	    initCounterPropertyGroup(vo, alarm);
        return vo;


	}

	private void initTypePropertyGroup(AlarmVO alarmVO, Alarm alarm){
	
		AlarmType type = alarmTypeService.find(alarm.getType());
		if(type == null){
			return;
		}
		AlarmTypeVO typeVO = new AlarmTypeVO();
		BeanUtils.copyProperties(type, typeVO);

		alarmVO.setTypeVO(typeVO);

	}
	private void initAppPropertyGroup(AlarmVO alarmVO, Alarm alarm){
	
		Application app = applicationService.find(alarm.getApp());
		if(app == null){
			return;
		}
		ApplicationVO appVO = new ApplicationVO();
		BeanUtils.copyProperties(app, appVO);

		alarmVO.setAppVO(appVO);

	}
	private void initCounterPropertyGroup(AlarmVO alarmVO, Alarm alarm){
	
		Indicator counter = indicatorService.find(alarm.getCounter());
		if(counter == null){
			return;
		}
		IndicatorVO counterVO = new IndicatorVO();
		BeanUtils.copyProperties(counter, counterVO);

		alarmVO.setCounterVO(counterVO);

	}

}
