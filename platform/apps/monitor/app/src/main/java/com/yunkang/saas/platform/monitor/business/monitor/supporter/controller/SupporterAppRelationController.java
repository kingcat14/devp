package com.yunkang.saas.platform.monitor.business.monitor.supporter.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import com.yunkang.saas.platform.monitor.business.app.service.ApplicationService;
import com.yunkang.saas.platform.monitor.business.app.vo.ApplicationVO;
import com.yunkang.saas.platform.monitor.business.supporter.domain.Supporter;
import com.yunkang.saas.platform.monitor.business.supporter.domain.SupporterAppRelation;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterAppRelationAddDto;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterAppRelationCondition;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterAppRelationEditDto;
import com.yunkang.saas.platform.monitor.business.supporter.service.SupporterAppRelationService;
import com.yunkang.saas.platform.monitor.business.supporter.service.SupporterService;
import com.yunkang.saas.platform.monitor.business.supporter.valid.SupporterAppRelationValidator;
import com.yunkang.saas.platform.monitor.business.supporter.vo.SupporterAppRelationVO;
import com.yunkang.saas.platform.monitor.business.supporter.vo.SupporterVO;
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
 * 管理支持应用
 * @author icode
 */
@Api(description = "支持应用", tags = "SupporterAppRelation")
@RestController
@RequestMapping(value =  "/monitor/supporter/supporterapprelation")
public class SupporterAppRelationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SupporterAppRelationController.class);

	@Autowired
	private SupporterAppRelationService supporterAppRelationService;

	@Autowired
	private SupporterService supporterService;
	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private SupporterAppRelationValidator supporterAppRelationValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(supporterAppRelationValidator);
	}

	/**
	 * 新增支持应用
	 * @param supporterAppRelationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增支持应用", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
    @SaaSAnnotation
	public SupporterAppRelationVO add(@RequestBody @Valid SupporterAppRelationAddDto supporterAppRelationAddDto){

		SupporterAppRelation supporterAppRelation = new SupporterAppRelation();
		BeanUtils.copyProperties(supporterAppRelationAddDto, supporterAppRelation);
		supporterAppRelationService.add(supporterAppRelation);

		return  initViewProperty(supporterAppRelation);
	}

	/**
	 * 删除支持应用,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除支持应用", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete supporterAppRelation :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			supporterAppRelationService.delete(id);
		}

	}

	/**
	 * 更新支持应用
	 * @param supporterAppRelationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产支持应用(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public	SupporterAppRelationVO update(@RequestBody @Valid SupporterAppRelationEditDto supporterAppRelationEditDto, @PathVariable String id){
		SupporterAppRelation supporterAppRelation = new SupporterAppRelation();
		BeanUtils.copyProperties(supporterAppRelationEditDto, supporterAppRelation);
		supporterAppRelation.setId(id);
		supporterAppRelationService.merge(supporterAppRelation);

		SupporterAppRelationVO vo = initViewProperty(supporterAppRelation);
		return  vo;
	}

	/**
	 * 根据ID查询支持应用
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询支持应用", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public  SupporterAppRelationVO get(@PathVariable String id) {

		SupporterAppRelation supporterAppRelation = supporterAppRelationService.find(id);
		if(supporterAppRelation == null){
			throw new ResourceNotFoundException("找不到指定的支持应用，请检查ID");
		}
		SupporterAppRelationVO vo = initViewProperty(supporterAppRelation);
		return vo;
	}

	/**
	 * 查询支持应用列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询支持应用列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@SaaSAnnotation(conditionClass = SupporterAppRelationCondition.class)
	public PageContent<SupporterAppRelationVO> list(@RequestBody PageSearchRequest<SupporterAppRelationCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
		
		Page<SupporterAppRelation> page = supporterAppRelationService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<SupporterAppRelationVO> voList = new ArrayList<>();
		for(SupporterAppRelation supporterAppRelation : page.getContent()){
			voList.add(initViewProperty(supporterAppRelation));
		}

		PageContent<SupporterAppRelationVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出支持应用列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出支持应用列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(SupporterAppRelationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<SupporterAppRelationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<SupporterAppRelationVO> content = this.list(pageSearchRequest);

        List<SupporterAppRelationVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(SupporterAppRelationVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("supporter" ,"运维人员");
            headMap.put("application" ,"支持程序");
            headMap.put("notificationType" ,"接收通知方式");

        String title = new String("支持应用");
        String fileName = new String(("支持应用_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private SupporterAppRelationVO initViewProperty(SupporterAppRelation supporterAppRelation){

	    SupporterAppRelationVO vo = new SupporterAppRelationVO();
        BeanUtils.copyProperties(supporterAppRelation, vo);


	    //初始化其他对象
	    initSupporterPropertyGroup(vo, supporterAppRelation);
	    initApplicationPropertyGroup(vo, supporterAppRelation);
        return vo;


	}

	private void initSupporterPropertyGroup(SupporterAppRelationVO supporterAppRelationVO, SupporterAppRelation supporterAppRelation){
	
		Supporter supporter = supporterService.find(supporterAppRelation.getSupporter());
		if(supporter == null){
			return;
		}
		SupporterVO supporterVO = new SupporterVO();
		BeanUtils.copyProperties(supporter, supporterVO);

		supporterAppRelationVO.setSupporterVO(supporterVO);

	}
	private void initApplicationPropertyGroup(SupporterAppRelationVO supporterAppRelationVO, SupporterAppRelation supporterAppRelation){
	
		Application application = applicationService.find(supporterAppRelation.getApplication());
		if(application == null){
			return;
		}
		ApplicationVO applicationVO = new ApplicationVO();
		BeanUtils.copyProperties(application, applicationVO);

		supporterAppRelationVO.setApplicationVO(applicationVO);

	}

}
