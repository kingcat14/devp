package com.yunkang.saas.platform.monitor.business.monitor.notification.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.notification.domain.NotificationType;
import com.yunkang.saas.platform.monitor.business.notification.dto.NotificationTypeAddDto;
import com.yunkang.saas.platform.monitor.business.notification.dto.NotificationTypeCondition;
import com.yunkang.saas.platform.monitor.business.notification.dto.NotificationTypeEditDto;
import com.yunkang.saas.platform.monitor.business.notification.service.NotificationTypeService;
import com.yunkang.saas.platform.monitor.business.notification.valid.NotificationTypeValidator;
import com.yunkang.saas.platform.monitor.business.notification.vo.NotificationTypeVO;
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
 * 管理通知方式
 * @author icode
 */
@Api(description = "通知方式", tags = "NotificationType")
@RestController
@RequestMapping(value =  "/monitor/notification/notificationtype")
public class NotificationTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationTypeController.class);

	@Autowired
	private NotificationTypeService notificationTypeService;


	@Autowired
	private NotificationTypeValidator notificationTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(notificationTypeValidator);
	}

	/**
	 * 新增通知方式
	 * @param notificationTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增通知方式", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
    @SaaSAnnotation
	public NotificationTypeVO add(@RequestBody @Valid NotificationTypeAddDto notificationTypeAddDto){

		NotificationType notificationType = new NotificationType();
		BeanUtils.copyProperties(notificationTypeAddDto, notificationType);
		notificationTypeService.add(notificationType);

		return  initViewProperty(notificationType);
	}

	/**
	 * 删除通知方式,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除通知方式", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete notificationType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			notificationTypeService.delete(id);
		}

	}

	/**
	 * 更新通知方式
	 * @param notificationTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产通知方式(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public	NotificationTypeVO update(@RequestBody @Valid NotificationTypeEditDto notificationTypeEditDto, @PathVariable String id){
		NotificationType notificationType = new NotificationType();
		BeanUtils.copyProperties(notificationTypeEditDto, notificationType);
		notificationType.setId(id);
		notificationTypeService.merge(notificationType);

		NotificationTypeVO vo = initViewProperty(notificationType);
		return  vo;
	}

	/**
	 * 根据ID查询通知方式
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询通知方式", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public  NotificationTypeVO get(@PathVariable String id) {

		NotificationType notificationType = notificationTypeService.find(id);
		if(notificationType == null){
			throw new ResourceNotFoundException("找不到指定的通知方式，请检查ID");
		}
		NotificationTypeVO vo = initViewProperty(notificationType);
		return vo;
	}

	/**
	 * 查询通知方式列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询通知方式列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@SaaSAnnotation(conditionClass = NotificationTypeCondition.class)
	public PageContent<NotificationTypeVO> list(@RequestBody PageSearchRequest<NotificationTypeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
		
		Page<NotificationType> page = notificationTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<NotificationTypeVO> voList = new ArrayList<>();
		for(NotificationType notificationType : page.getContent()){
			voList.add(initViewProperty(notificationType));
		}

		PageContent<NotificationTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出通知方式列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出通知方式列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(NotificationTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<NotificationTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<NotificationTypeVO> content = this.list(pageSearchRequest);

        List<NotificationTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(NotificationTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("code" ,"通知方式");

        String title = new String("通知方式");
        String fileName = new String(("通知方式_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private NotificationTypeVO initViewProperty(NotificationType notificationType){

	    NotificationTypeVO vo = new NotificationTypeVO();
        BeanUtils.copyProperties(notificationType, vo);


	    //初始化其他对象
        return vo;


	}


}
