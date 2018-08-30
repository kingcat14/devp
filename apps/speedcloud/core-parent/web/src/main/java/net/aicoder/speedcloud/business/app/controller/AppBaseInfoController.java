package net.aicoder.speedcloud.business.app.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.app.domain.AppBaseInfo;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoEditDto;
import net.aicoder.speedcloud.business.app.service.AppBaseInfoService;
import net.aicoder.speedcloud.business.app.valid.AppBaseInfoValidator;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;
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
 * 管理应用
 * @author icode
 */
@Api(description = "应用", tags = "AppBaseInfo")
@RestController
@RequestMapping(value = "/speedcloud/app/appbaseinfo")
public class AppBaseInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppBaseInfoController.class);


	@Autowired
	private AppBaseInfoService appBaseInfoService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private AppBaseInfoValidator appBaseInfoValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(appBaseInfoValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增应用
	 * @param appBaseInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AppBaseInfoVO add(@RequestBody @Valid AppBaseInfoAddDto appBaseInfoAddDto){
		AppBaseInfo appBaseInfo = new AppBaseInfo();
		BeanUtils.copyProperties(appBaseInfoAddDto, appBaseInfo);

		appBaseInfoService.add(appBaseInfo);

		return  initViewProperty(appBaseInfo);
	}

	/**
	 * 删除应用,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete appBaseInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			appBaseInfoService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新应用
	 * @param appBaseInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	AppBaseInfoVO update(@RequestBody @Valid AppBaseInfoEditDto appBaseInfoEditDto, @PathVariable Long id){
		AppBaseInfo appBaseInfo = new AppBaseInfo();
		BeanUtils.copyProperties(appBaseInfoEditDto, appBaseInfo);
		appBaseInfo.setId(id);
		appBaseInfoService.merge(appBaseInfo);

		AppBaseInfoVO vo = initViewProperty(appBaseInfo);
		return  vo;
	}

	/**
	 * 根据ID查询应用
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  AppBaseInfoVO get(@PathVariable Long id) {

		AppBaseInfo appBaseInfo = appBaseInfoService.find(id);

		AppBaseInfoVO vo = initViewProperty(appBaseInfo);
		return vo;
	}

	/**
	 * 查询应用列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<AppBaseInfoVO> list(@RequestBody PageSearchRequest<AppBaseInfoCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<AppBaseInfo> page = appBaseInfoService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AppBaseInfoVO> voList = new ArrayList<>();
		for(AppBaseInfo appBaseInfo : page.getContent()){
			voList.add(initViewProperty(appBaseInfo));
		}

		PageContent<AppBaseInfoVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出应用列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出应用列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(AppBaseInfoCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AppBaseInfoCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AppBaseInfoVO> content = this.list(pageSearchRequest);

        List<AppBaseInfoVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AppBaseInfoVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"名称");
            headMap.put("type" ,"应用类型");
            headMap.put("status" ,"状态");
            headMap.put("description" ,"描述");
            headMap.put("registTime" ,"注册时间");
            headMap.put("project" ,"所属项目");

        String title = new String("应用");
        String fileName = new String(("应用_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AppBaseInfoVO initViewProperty(AppBaseInfo appBaseInfo){

	    AppBaseInfoVO vo = new AppBaseInfoVO();
        BeanUtils.copyProperties(appBaseInfo, vo);


	    //初始化其他对象
	    initProjectPropertyGroup(vo, appBaseInfo);
        return vo;


	}


	private void initProjectPropertyGroup(AppBaseInfoVO appBaseInfoVO, AppBaseInfo appBaseInfo){
	
		Project project = projectService.find(appBaseInfo.getProject());
		if(project == null){
			return;
		}
		ProjectVO projectVO = new ProjectVO();
		BeanUtils.copyProperties(project, projectVO);

		appBaseInfoVO.setProjectVO(projectVO);

	}


}
