package net.aicoder.speedcloud.business.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.app.domain.ApplicationType;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeAddDto;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeCondition;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeEditDto;
import net.aicoder.speedcloud.business.app.service.ApplicationTypeService;
import net.aicoder.speedcloud.business.app.valid.ApplicationTypeValidator;
import net.aicoder.speedcloud.business.app.vo.ApplicationTypeVO;
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
 * 管理应用类型
 * @author icode
 */
@Api(description = "应用类型", tags = "ApplicationType")
@RestController
@RequestMapping(value = "/speedcloud/app/applicationtype")
public class ApplicationTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTypeController.class);


	@Autowired
	private ApplicationTypeService applicationTypeService;



	@Autowired
	private ApplicationTypeValidator applicationTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(applicationTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增应用类型
	 * @param applicationTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "speedcloud.app.applicationtype.add", count = true)
	public ApplicationTypeVO add(@RequestBody @Valid ApplicationTypeAddDto applicationTypeAddDto){
		ApplicationType applicationType = new ApplicationType();
		BeanUtils.copyProperties(applicationTypeAddDto, applicationType);

		applicationTypeService.add(applicationType);

		return  initViewProperty(applicationType);
	}

	/**
	 * 删除应用类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用类型", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "speedcloud.app.applicationtype.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete applicationType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			applicationTypeService.delete(id);
		}

	}

	/**
	 * 更新应用类型
	 * @param applicationTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改应用类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.app.applicationtype.update", count = true)
	public	ApplicationTypeVO update(@RequestBody @Valid ApplicationTypeEditDto applicationTypeEditDto, @PathVariable String id){
		ApplicationType applicationType = applicationTypeService.find(id);
		BeanUtils.copyProperties(applicationTypeEditDto, applicationType);
		applicationType.setId(id);
		applicationTypeService.merge(applicationType);

		ApplicationTypeVO vo = initViewProperty(applicationType);
		return  vo;
	}

	/**
	 * 根据ID查询应用类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询应用类型", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.app.applicationtype.get")
	public  ApplicationTypeVO get(@PathVariable String id) {

		ApplicationType applicationType = applicationTypeService.find(id);
		if(applicationType == null){
			throw new ResourceNotFoundException("找不到指定的应用类型，请检查ID");
		}
		ApplicationTypeVO vo = initViewProperty(applicationType);
		return vo;
	}

	/**
	 * 查询应用类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用类型列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "speedcloud.app.applicationtype.list")
	public PageContent<ApplicationTypeVO> list(@RequestBody PageSearchRequest<ApplicationTypeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<ApplicationType> page = applicationTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ApplicationTypeVO> voList = new ArrayList<>();
		for(ApplicationType applicationType : page.getContent()){
			voList.add(initViewProperty(applicationType));
		}

		PageContent<ApplicationTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出应用类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出应用类型列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ApplicationTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ApplicationTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ApplicationTypeVO> content = this.list(pageSearchRequest);

        List<ApplicationTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ApplicationTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("idx" ,"排序");
        headMap.put("code" ,"代码");
        headMap.put("name" ,"名称");
        headMap.put("category" ,"种类");
        headMap.put("icon" ,"默认图标");

        String title = new String("应用类型");
        String fileName = new String(("应用类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ApplicationTypeVO initViewProperty(ApplicationType applicationType){

	    ApplicationTypeVO vo = new ApplicationTypeVO();
        BeanUtils.copyProperties(applicationType, vo);


	    //初始化其他对象
        return vo;

	}


}

