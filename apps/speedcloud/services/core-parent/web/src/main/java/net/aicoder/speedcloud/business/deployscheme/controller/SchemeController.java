package net.aicoder.speedcloud.business.deployscheme.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.deployscheme.domain.Scheme;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeEditDto;
import net.aicoder.speedcloud.business.deployscheme.service.SchemeService;
import net.aicoder.speedcloud.business.deployscheme.valid.SchemeValidator;
import net.aicoder.speedcloud.business.deployscheme.vo.SchemeVO;
import net.aicoder.speedcloud.business.env.domain.AppEnvConfig;
import net.aicoder.speedcloud.business.env.service.AppEnvConfigService;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.business.project.domain.Project;
import net.aicoder.speedcloud.business.project.service.ProjectService;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
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
 * 管理部署方案
 * @author icode
 */
@Api(description = "部署方案", tags = "Scheme")
@RestController
@RequestMapping(value = "/speedcloud/deployscheme/scheme")
public class SchemeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchemeController.class);


	@Autowired
	private SchemeService schemeService;

	@Autowired
	private ProjectService projectService;
	@Autowired
	private AppEnvConfigService appEnvConfigService;


	@Autowired
	private SchemeValidator schemeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(schemeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署方案
	 * @param schemeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署方案", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public SchemeVO add(@RequestBody @Valid SchemeAddDto schemeAddDto){
		Scheme scheme = new Scheme();
		BeanUtils.copyProperties(schemeAddDto, scheme);

		schemeService.add(scheme);

		return  initViewProperty(scheme);
	}

	/**
	 * 删除部署方案,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署方案", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete scheme :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			schemeService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署方案
	 * @param schemeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署方案(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	SchemeVO update(@RequestBody @Valid SchemeEditDto schemeEditDto, @PathVariable Long id){
		Scheme scheme = new Scheme();
		BeanUtils.copyProperties(schemeEditDto, scheme);
		scheme.setId(id);
		schemeService.merge(scheme);

		SchemeVO vo = initViewProperty(scheme);
		return  vo;
	}

	/**
	 * 根据ID查询部署方案
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署方案", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  SchemeVO get(@PathVariable Long id) {

		Scheme scheme = schemeService.find(id);

		SchemeVO vo = initViewProperty(scheme);
		return vo;
	}

	/**
	 * 查询部署方案列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署方案列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<SchemeVO> list(@RequestBody PageSearchRequest<SchemeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<Scheme> page = schemeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<SchemeVO> voList = new ArrayList<>();
		for(Scheme scheme : page.getContent()){
			voList.add(initViewProperty(scheme));
		}

		PageContent<SchemeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署方案列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署方案列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(SchemeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<SchemeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<SchemeVO> content = this.list(pageSearchRequest);

        List<SchemeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(SchemeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"方案名称");
            headMap.put("code" ,"方案代码");
            headMap.put("alias" ,"方案别名");
            headMap.put("description" ,"方案描述");
            headMap.put("type" ,"方案类型");
            headMap.put("status" ,"已生效");
            headMap.put("notes" ,"备注");
            headMap.put("project" ,"所属项目（产品）");
            headMap.put("env" ,"所属环境");

        String title = new String("部署方案");
        String fileName = new String(("部署方案_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private SchemeVO initViewProperty(Scheme scheme){

	    SchemeVO vo = new SchemeVO();
        BeanUtils.copyProperties(scheme, vo);


	    //初始化其他对象
	    initProjectPropertyGroup(vo, scheme);
	    initEnvPropertyGroup(vo, scheme);
        return vo;

	}


	private void initProjectPropertyGroup(SchemeVO schemeVO, Scheme scheme){
	
		Project project = projectService.find(scheme.getProject());
		if(project == null){
			return;
		}
		ProjectVO projectVO = new ProjectVO();
		BeanUtils.copyProperties(project, projectVO);

		schemeVO.setProjectVO(projectVO);

	}


	private void initEnvPropertyGroup(SchemeVO schemeVO, Scheme scheme){
	
		AppEnvConfig env = appEnvConfigService.find(scheme.getEnv());
		if(env == null){
			return;
		}
		AppEnvConfigVO envVO = new AppEnvConfigVO();
		BeanUtils.copyProperties(env, envVO);

		schemeVO.setEnvVO(envVO);

	}


}

