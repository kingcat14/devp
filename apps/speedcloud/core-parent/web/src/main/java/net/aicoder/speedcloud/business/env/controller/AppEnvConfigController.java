package net.aicoder.speedcloud.business.env.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.env.domain.AppEnvConfig;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigCondition;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigAddDto;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigEditDto;
import net.aicoder.speedcloud.business.env.service.AppEnvConfigService;
import net.aicoder.speedcloud.business.env.valid.AppEnvConfigValidator;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.business.project.domain.Project;
import net.aicoder.speedcloud.business.project.service.ProjectService;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;


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
 * 管理应用环境
 * @author icode
 */
@Api(description = "应用环境", tags = "AppEnvConfig")
@RestController
@RequestMapping(value = "/speedcloud/env/appenvconfig")
public class AppEnvConfigController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppEnvConfigController.class);


	@Autowired
	private AppEnvConfigService appEnvConfigService;

	@Autowired
	private ProjectService projectService;


	@Autowired
	private AppEnvConfigValidator appEnvConfigValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(appEnvConfigValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增应用环境
	 * @param appEnvConfigAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用环境", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AppEnvConfigVO add(@RequestBody @Valid AppEnvConfigAddDto appEnvConfigAddDto){
		AppEnvConfig appEnvConfig = new AppEnvConfig();
		BeanUtils.copyProperties(appEnvConfigAddDto, appEnvConfig);

		appEnvConfigService.add(appEnvConfig);

		return  initViewProperty(appEnvConfig);
	}

	/**
	 * 删除应用环境,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用环境", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete appEnvConfig :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			appEnvConfigService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新应用环境
	 * @param appEnvConfigEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用环境(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	AppEnvConfigVO update(@RequestBody @Valid AppEnvConfigEditDto appEnvConfigEditDto, @PathVariable Long id){
		AppEnvConfig appEnvConfig = new AppEnvConfig();
		BeanUtils.copyProperties(appEnvConfigEditDto, appEnvConfig);
		appEnvConfig.setId(id);
		appEnvConfigService.merge(appEnvConfig);

		AppEnvConfigVO vo = initViewProperty(appEnvConfig);
		return  vo;
	}

	/**
	 * 根据ID查询应用环境
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用环境", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  AppEnvConfigVO get(@PathVariable Long id) {

		AppEnvConfig appEnvConfig = appEnvConfigService.find(id);

		AppEnvConfigVO vo = initViewProperty(appEnvConfig);
		return vo;
	}

	/**
	 * 查询应用环境列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用环境列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<AppEnvConfigVO> list(@RequestBody PageSearchRequest<AppEnvConfigCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<AppEnvConfig> page = appEnvConfigService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AppEnvConfigVO> voList = new ArrayList<>();
		for(AppEnvConfig appEnvConfig : page.getContent()){
			voList.add(initViewProperty(appEnvConfig));
		}

		PageContent<AppEnvConfigVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出应用环境列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出应用环境列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(AppEnvConfigCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AppEnvConfigCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AppEnvConfigVO> content = this.list(pageSearchRequest);

        List<AppEnvConfigVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AppEnvConfigVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"环境名称");
            headMap.put("level" ,"环境级别");
            headMap.put("project" ,"所属项目（产品）");

        String title = new String("应用环境");
        String fileName = new String(("应用环境_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AppEnvConfigVO initViewProperty(AppEnvConfig appEnvConfig){

	    AppEnvConfigVO vo = new AppEnvConfigVO();
        BeanUtils.copyProperties(appEnvConfig, vo);


	    //初始化其他对象
	    initProjectPropertyGroup(vo, appEnvConfig);
        return vo;

	}


	private void initProjectPropertyGroup(AppEnvConfigVO appEnvConfigVO, AppEnvConfig appEnvConfig){
	
		Project project = projectService.find(appEnvConfig.getProject());
		if(project == null){
			return;
		}
		ProjectVO projectVO = new ProjectVO();
		BeanUtils.copyProperties(project, projectVO);

		appEnvConfigVO.setProjectVO(projectVO);

	}


}

