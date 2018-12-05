package net.aicoder.speedcloud.business.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.jms.sender.SaaSMessageSender;
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
import net.aicoder.speedcloud.business.app.domain.AppBaseInfo;
import net.aicoder.speedcloud.business.app.domain.AppDevelopConfig;
import net.aicoder.speedcloud.business.app.domain.ApplicationType;
import net.aicoder.speedcloud.business.app.domain.CodeRepository;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoEditDto;
import net.aicoder.speedcloud.business.app.event.AppBaseInfoEvent;
import net.aicoder.speedcloud.business.app.event.AppBaseInfoEventTopic;
import net.aicoder.speedcloud.business.app.service.AppBaseInfoService;
import net.aicoder.speedcloud.business.app.service.AppDevelopConfigService;
import net.aicoder.speedcloud.business.app.service.ApplicationTypeService;
import net.aicoder.speedcloud.business.app.service.CodeRepositoryService;
import net.aicoder.speedcloud.business.app.valid.AppBaseInfoValidator;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;
import net.aicoder.speedcloud.business.app.vo.AppDevelopConfigVO;
import net.aicoder.speedcloud.business.app.vo.ApplicationTypeVO;
import net.aicoder.speedcloud.business.app.vo.CodeRepositoryVO;
import net.aicoder.speedcloud.business.project.domain.Project;
import net.aicoder.speedcloud.business.project.service.ProjectService;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理应用（系统）
 * @author icode
 */
@Api(description = "应用（系统）", tags = "AppBaseInfo")
@RestController
@RequestMapping(value = "/speedcloud/app/appbaseinfo")
public class AppBaseInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppBaseInfoController.class);


	@Autowired
	private AppBaseInfoService appBaseInfoService;

	@Autowired
	private CodeRepositoryService codeRepositoryService;

	@Autowired
	private AppDevelopConfigService appDevelopConfigService;

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ApplicationTypeService applicationTypeService;


	@Autowired
	private AppBaseInfoValidator appBaseInfoValidator;

	@Autowired
	private SaaSMessageSender saaSMessageSender;

	@Autowired
	ApplicationContext applicationContext;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(appBaseInfoValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增应用（系统）
	 * @param appBaseInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用（系统）", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "speedcloud.app.appbaseinfo.add", count = true)
	public AppBaseInfoVO add(@RequestBody @Valid AppBaseInfoAddDto appBaseInfoAddDto){
		AppBaseInfo appBaseInfo = new AppBaseInfo();
		BeanUtils.copyProperties(appBaseInfoAddDto, appBaseInfo);

		appBaseInfoService.add(appBaseInfo);

		AppBaseInfoVO vo = initViewProperty(appBaseInfo);

		saaSMessageSender.sendTopicAsync(AppBaseInfoEventTopic.CREATE, appBaseInfo.getTid(), vo);

		return  vo;
	}

	/**
	 * 删除应用（系统）,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用（系统）", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "speedcloud.app.appbaseinfo.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete appBaseInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			appBaseInfoService.delete(id);
		}

	}

	/**
	 * 更新应用（系统）
	 * @param appBaseInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改应用（系统）(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.app.appbaseinfo.update", count = true)
	public	AppBaseInfoVO update(@RequestBody @Valid AppBaseInfoEditDto appBaseInfoEditDto, @PathVariable String id){
		AppBaseInfo appBaseInfo = appBaseInfoService.find(id);
		BeanUtils.copyProperties(appBaseInfoEditDto, appBaseInfo);
		appBaseInfo.setId(id);
		appBaseInfoService.merge(appBaseInfo);

		AppBaseInfoVO vo = initViewProperty(appBaseInfo);
		saaSMessageSender.sendTopicAsync(AppBaseInfoEventTopic.UPDATE, appBaseInfo.getTid(), vo);
		applicationContext.publishEvent(new AppBaseInfoEvent(AppBaseInfoEventTopic.UPDATE, appBaseInfo.getTid(), vo));
		return  vo;
	}

	/**
	 * 根据ID查询应用（系统）
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询应用（系统）", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.app.appbaseinfo.get")
	public  AppBaseInfoVO get(@PathVariable String id) {

		AppBaseInfo appBaseInfo = appBaseInfoService.find(id);
		if(appBaseInfo == null){
			throw new ResourceNotFoundException("找不到指定的应用（系统），请检查ID");
		}
		AppBaseInfoVO vo = initViewProperty(appBaseInfo);

		CodeRepository codeRepository = codeRepositoryService.findByApp(vo.getId());
		if(codeRepository != null){
			CodeRepositoryVO codeRepositoryVO = new CodeRepositoryVO();
			BeanUtils.copyProperties(codeRepository, codeRepositoryVO);
			vo.setCodeRepositoryVO(codeRepositoryVO);
		}

		AppDevelopConfig developConfig = appDevelopConfigService.findByApp(vo.getId());
		if(developConfig != null){
			AppDevelopConfigVO developConfigVO = new AppDevelopConfigVO();
			BeanUtils.copyProperties(developConfig, developConfigVO);
			vo.setAppDevelopConfigVO(developConfigVO);
		}

		return vo;
	}

	/**
	 * 查询应用（系统）列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用（系统）列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "speedcloud.app.appbaseinfo.list")
	public PageContent<AppBaseInfoVO> list(@RequestBody PageSearchRequest<AppBaseInfoCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
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
     * 导出应用（系统）列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出应用（系统）列表", httpMethod = "POST")
    @RequestMapping(path="/export")
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

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("project" ,"所属项目");
        headMap.put("type" ,"应用类型");
        headMap.put("name" ,"名称");
        headMap.put("code" ,"代码");
        headMap.put("status" ,"状态");
        headMap.put("description" ,"描述");
        headMap.put("registTime" ,"注册时间");

        String title = new String("应用（系统）");
        String fileName = new String(("应用（系统）_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AppBaseInfoVO initViewProperty(AppBaseInfo appBaseInfo){

	    AppBaseInfoVO vo = new AppBaseInfoVO();
        BeanUtils.copyProperties(appBaseInfo, vo);


	    //初始化其他对象
	    initProjectPropertyGroup(vo, appBaseInfo);
	    initTypePropertyGroup(vo, appBaseInfo);
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
	private void initTypePropertyGroup(AppBaseInfoVO appBaseInfoVO, AppBaseInfo appBaseInfo){
	
		ApplicationType type = applicationTypeService.find(appBaseInfo.getType());
		if(type == null){
			return;
		}
		ApplicationTypeVO typeVO = new ApplicationTypeVO();
		BeanUtils.copyProperties(type, typeVO);

		appBaseInfoVO.setTypeVO(typeVO);

	}

}

