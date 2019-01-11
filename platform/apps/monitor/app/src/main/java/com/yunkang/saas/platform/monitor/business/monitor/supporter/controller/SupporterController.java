package com.yunkang.saas.platform.monitor.business.monitor.supporter.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.supporter.domain.Supporter;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterAddDto;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterCondition;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterEditDto;
import com.yunkang.saas.platform.monitor.business.supporter.service.SupporterService;
import com.yunkang.saas.platform.monitor.business.supporter.valid.SupporterValidator;
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
 * 管理运维人员
 * @author icode
 */
@Api(description = "运维人员", tags = "Supporter")
@RestController
@RequestMapping(value =  "/monitor/supporter/supporter")
public class SupporterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SupporterController.class);

	@Autowired
	private SupporterService supporterService;


	@Autowired
	private SupporterValidator supporterValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(supporterValidator);
	}

	/**
	 * 新增运维人员
	 * @param supporterAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运维人员", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
    @SaaSAnnotation
	public SupporterVO add(@RequestBody @Valid SupporterAddDto supporterAddDto){

		Supporter supporter = new Supporter();
		BeanUtils.copyProperties(supporterAddDto, supporter);
		supporterService.add(supporter);

		return  initViewProperty(supporter);
	}

	/**
	 * 删除运维人员,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运维人员", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete supporter :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			supporterService.delete(id);
		}

	}

	/**
	 * 更新运维人员
	 * @param supporterEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运维人员(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public	SupporterVO update(@RequestBody @Valid SupporterEditDto supporterEditDto, @PathVariable String id){
		Supporter supporter = new Supporter();
		BeanUtils.copyProperties(supporterEditDto, supporter);
		supporter.setId(id);
		supporterService.merge(supporter);

		SupporterVO vo = initViewProperty(supporter);
		return  vo;
	}

	/**
	 * 根据ID查询运维人员
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运维人员", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public  SupporterVO get(@PathVariable String id) {

		Supporter supporter = supporterService.find(id);
		if(supporter == null){
			throw new ResourceNotFoundException("找不到指定的运维人员，请检查ID");
		}
		SupporterVO vo = initViewProperty(supporter);
		return vo;
	}

	/**
	 * 查询运维人员列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运维人员列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@SaaSAnnotation(conditionClass = SupporterCondition.class)
	public PageContent<SupporterVO> list(@RequestBody PageSearchRequest<SupporterCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
		
		Page<Supporter> page = supporterService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<SupporterVO> voList = new ArrayList<>();
		for(Supporter supporter : page.getContent()){
			voList.add(initViewProperty(supporter));
		}

		PageContent<SupporterVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出运维人员列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出运维人员列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(SupporterCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<SupporterCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<SupporterVO> content = this.list(pageSearchRequest);

        List<SupporterVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(SupporterVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"姓名");
            headMap.put("mobile" ,"手机号码");
            headMap.put("email" ,"邮箱");

        String title = new String("运维人员");
        String fileName = new String(("运维人员_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private SupporterVO initViewProperty(Supporter supporter){

	    SupporterVO vo = new SupporterVO();
        BeanUtils.copyProperties(supporter, vo);


	    //初始化其他对象
        return vo;


	}


}
