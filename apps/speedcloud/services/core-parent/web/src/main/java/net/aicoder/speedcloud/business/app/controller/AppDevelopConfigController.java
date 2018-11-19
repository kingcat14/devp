package net.aicoder.speedcloud.business.app.controller;

import com.alibaba.fastjson.JSONArray;
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
import net.aicoder.speedcloud.business.app.domain.CodeRepository;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigCondition;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigEditDto;
import net.aicoder.speedcloud.business.app.service.AppBaseInfoService;
import net.aicoder.speedcloud.business.app.service.AppDevelopConfigService;
import net.aicoder.speedcloud.business.app.service.CodeRepositoryService;
import net.aicoder.speedcloud.business.app.valid.AppDevelopConfigValidator;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;
import net.aicoder.speedcloud.business.app.vo.AppDevelopConfigVO;
import net.aicoder.speedcloud.business.app.vo.CodeRepositoryVO;
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
 * 管理应用开发配置
 * @author icode
 */
@Api(description = "应用开发配置", tags = "AppDevelopConfig")
@RestController
@RequestMapping(value = "/speedcloud/app/appdevelopconfig")
public class AppDevelopConfigController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppDevelopConfigController.class);


	@Autowired
	private AppDevelopConfigService appDevelopConfigService;

	@Autowired
	private AppBaseInfoService appBaseInfoService;
	@Autowired
	private CodeRepositoryService codeRepertoryService;

	@Autowired
	private AppDevelopConfigValidator appDevelopConfigValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(appDevelopConfigValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增应用开发配置
	 * @param appDevelopConfigAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用开发配置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public AppDevelopConfigVO add(@RequestBody @Valid AppDevelopConfigAddDto appDevelopConfigAddDto){
		AppDevelopConfig appDevelopConfig = new AppDevelopConfig();
		BeanUtils.copyProperties(appDevelopConfigAddDto, appDevelopConfig);

		appDevelopConfigService.add(appDevelopConfig);

		return  initViewProperty(appDevelopConfig);
	}

	/**
	 * 删除应用开发配置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用开发配置", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete appDevelopConfig :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			appDevelopConfigService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新应用开发配置
	 * @param appDevelopConfigEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用开发配置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	AppDevelopConfigVO update(@RequestBody @Valid AppDevelopConfigEditDto appDevelopConfigEditDto, @PathVariable Long id){
		AppDevelopConfig appDevelopConfig = new AppDevelopConfig();
		BeanUtils.copyProperties(appDevelopConfigEditDto, appDevelopConfig);
		appDevelopConfig.setId(id);
		appDevelopConfigService.merge(appDevelopConfig);

		AppDevelopConfigVO vo = initViewProperty(appDevelopConfig);
		return  vo;
	}

	/**
	 * 根据ID查询应用开发配置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用开发配置", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  AppDevelopConfigVO get(@PathVariable Long id) {

		AppDevelopConfig appDevelopConfig = appDevelopConfigService.find(id);

		AppDevelopConfigVO vo = initViewProperty(appDevelopConfig);
		return vo;
	}

	/**
	 * 查询应用开发配置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用开发配置列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<AppDevelopConfigVO> list(@RequestBody PageSearchRequest<AppDevelopConfigCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<AppDevelopConfig> page = appDevelopConfigService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AppDevelopConfigVO> voList = new ArrayList<>();
		for(AppDevelopConfig appDevelopConfig : page.getContent()){
			voList.add(initViewProperty(appDevelopConfig));
		}

		PageContent<AppDevelopConfigVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出应用开发配置列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出应用开发配置列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(AppDevelopConfigCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AppDevelopConfigCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AppDevelopConfigVO> content = this.list(pageSearchRequest);

        List<AppDevelopConfigVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AppDevelopConfigVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("app" ,"应用");
            headMap.put("code" ,"代码");
            headMap.put("testDatabase" ,"测试环境DB");
            headMap.put("testDomainName" ,"测试环境域名");
            headMap.put("productionDatabase" ,"生产环境DB");
            headMap.put("productionDomainName" ,"生产环境域名");

        String title = new String("应用开发配置");
        String fileName = new String(("应用开发配置_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AppDevelopConfigVO initViewProperty(AppDevelopConfig appDevelopConfig){

	    AppDevelopConfigVO vo = new AppDevelopConfigVO();
        BeanUtils.copyProperties(appDevelopConfig, vo);


	    //初始化其他对象
	    initAppPropertyGroup(vo, appDevelopConfig);
	    initCodePropertyGroup(vo, appDevelopConfig);
        return vo;


	}


	private void initAppPropertyGroup(AppDevelopConfigVO appDevelopConfigVO, AppDevelopConfig appDevelopConfig){
	
		AppBaseInfo app = appBaseInfoService.find(appDevelopConfig.getApp());
		if(app == null){
			return;
		}
		AppBaseInfoVO appVO = new AppBaseInfoVO();
		BeanUtils.copyProperties(app, appVO);

		appDevelopConfigVO.setAppVO(appVO);

	}


	private void initCodePropertyGroup(AppDevelopConfigVO appDevelopConfigVO, AppDevelopConfig appDevelopConfig){
	
		CodeRepository code = codeRepertoryService.find(appDevelopConfig.getCode());
		if(code == null){
			return;
		}
		CodeRepositoryVO codeVO = new CodeRepositoryVO();
		BeanUtils.copyProperties(code, codeVO);

		appDevelopConfigVO.setCodeVO(codeVO);

	}


}
