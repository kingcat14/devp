package net.aicoder.speedcloud.business.pipeline.jenkins.controller;

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
import net.aicoder.speedcloud.business.env.domain.AppEnvConfig;
import net.aicoder.speedcloud.business.env.service.AppEnvConfigService;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.business.pipeline.jenkins.domain.JenkinsAdapter;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterAddDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterCondition;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterEditDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.service.JenkinsAdapterService;
import net.aicoder.speedcloud.business.pipeline.jenkins.valid.JenkinsAdapterValidator;
import net.aicoder.speedcloud.business.pipeline.jenkins.vo.JenkinsAdapterVO;
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
 * 管理JenkinsAdapter
 * @author icode
 */
@Api(description = "JenkinsAdapter", tags = "JenkinsAdapter")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/jenkins/jenkinsadapter")
public class JenkinsAdapterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JenkinsAdapterController.class);


	@Autowired
	private JenkinsAdapterService jenkinsAdapterService;

	@Autowired
	private ProjectService projectService;
	@Autowired
	private AppEnvConfigService appEnvConfigService;


	@Autowired
	private JenkinsAdapterValidator jenkinsAdapterValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(jenkinsAdapterValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增JenkinsAdapter
	 * @param jenkinsAdapterAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增JenkinsAdapter", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.jenkins.jenkinsadapter.add", count = true)
	public JenkinsAdapterVO add(@RequestBody @Valid JenkinsAdapterAddDto jenkinsAdapterAddDto){
		JenkinsAdapter jenkinsAdapter = new JenkinsAdapter();
		BeanUtils.copyProperties(jenkinsAdapterAddDto, jenkinsAdapter);

		jenkinsAdapterService.add(jenkinsAdapter);

		return  initViewProperty(jenkinsAdapter);
	}

	/**
	 * 删除JenkinsAdapter,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除JenkinsAdapter", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.jenkins.jenkinsadapter.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete jenkinsAdapter :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			jenkinsAdapterService.delete(id);
		}

	}

	/**
	 * 更新JenkinsAdapter
	 * @param jenkinsAdapterEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改JenkinsAdapter(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.jenkins.jenkinsadapter.update", count = true)
	public	JenkinsAdapterVO update(@RequestBody @Valid JenkinsAdapterEditDto jenkinsAdapterEditDto, @PathVariable String id){
		JenkinsAdapter jenkinsAdapter = jenkinsAdapterService.find(id);
		BeanUtils.copyProperties(jenkinsAdapterEditDto, jenkinsAdapter);
		jenkinsAdapter.setId(id);
		jenkinsAdapterService.merge(jenkinsAdapter);

		JenkinsAdapterVO vo = initViewProperty(jenkinsAdapter);
		return  vo;
	}

	/**
	 * 根据ID查询JenkinsAdapter
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询JenkinsAdapter", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.jenkins.jenkinsadapter.get")
	public  JenkinsAdapterVO get(@PathVariable String id) {

		JenkinsAdapter jenkinsAdapter = jenkinsAdapterService.find(id);
		if(jenkinsAdapter == null){
			throw new ResourceNotFoundException("找不到指定的JenkinsAdapter，请检查ID");
		}
		JenkinsAdapterVO vo = initViewProperty(jenkinsAdapter);
		return vo;
	}

	/**
	 * 查询JenkinsAdapter列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询JenkinsAdapter列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "speedcloud.pipeline.jenkins.jenkinsadapter.list")
	public PageContent<JenkinsAdapterVO> list(@RequestBody PageSearchRequest<JenkinsAdapterCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<JenkinsAdapter> page = jenkinsAdapterService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<JenkinsAdapterVO> voList = new ArrayList<>();
		for(JenkinsAdapter jenkinsAdapter : page.getContent()){
			voList.add(initViewProperty(jenkinsAdapter));
		}

		PageContent<JenkinsAdapterVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出JenkinsAdapter列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出JenkinsAdapter列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(JenkinsAdapterCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<JenkinsAdapterCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<JenkinsAdapterVO> content = this.list(pageSearchRequest);

        List<JenkinsAdapterVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(JenkinsAdapterVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("project" ,"所属产品");
        headMap.put("env" ,"所属环境");
        headMap.put("port" ,"端口");
        headMap.put("host" ,"IP");

        String title = new String("JenkinsAdapter");
        String fileName = new String(("JenkinsAdapter_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private JenkinsAdapterVO initViewProperty(JenkinsAdapter jenkinsAdapter){

	    JenkinsAdapterVO vo = new JenkinsAdapterVO();
        BeanUtils.copyProperties(jenkinsAdapter, vo);


	    //初始化其他对象
	    initProjectPropertyGroup(vo, jenkinsAdapter);
	    initEnvPropertyGroup(vo, jenkinsAdapter);
        return vo;

	}

	private void initProjectPropertyGroup(JenkinsAdapterVO jenkinsAdapterVO, JenkinsAdapter jenkinsAdapter){
	
		Project project = projectService.find(jenkinsAdapter.getProject());
		if(project == null){
			return;
		}
		ProjectVO projectVO = new ProjectVO();
		BeanUtils.copyProperties(project, projectVO);

		jenkinsAdapterVO.setProjectVO(projectVO);

	}
	private void initEnvPropertyGroup(JenkinsAdapterVO jenkinsAdapterVO, JenkinsAdapter jenkinsAdapter){
	
		AppEnvConfig env = appEnvConfigService.find(jenkinsAdapter.getEnv());
		if(env == null){
			return;
		}
		AppEnvConfigVO envVO = new AppEnvConfigVO();
		BeanUtils.copyProperties(env, envVO);

		jenkinsAdapterVO.setEnvVO(envVO);

	}

}

