package net.aicoder.speedcloud.console.business.speedcloud.pipeline.jenkins.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterAddDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterCondition;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterEditDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.vo.JenkinsAdapterVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.jenkins.service.JenkinsAdapterRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.jenkins.valid.JenkinsAdapterValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private JenkinsAdapterRibbonService jenkinsAdapterRibbonService;

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
  	@SaaSAnnotation()
	public JenkinsAdapterVO add(@RequestBody @Valid JenkinsAdapterAddDto jenkinsAdapterAddDto){
	
		return  jenkinsAdapterRibbonService.add(jenkinsAdapterAddDto);
	}

	/**
	 * 删除JenkinsAdapter,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除JenkinsAdapter", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete jenkinsAdapter :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			jenkinsAdapterRibbonService.delete(id);
		}

	}

	/**
	 * 更新JenkinsAdapter
	 * @param jenkinsAdapterEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产JenkinsAdapter(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public JenkinsAdapterVO update(@RequestBody @Valid JenkinsAdapterEditDto jenkinsAdapterEditDto, @ApiParam(value = "要查询的JenkinsAdapterid") @PathVariable String id){

		JenkinsAdapterVO vo = jenkinsAdapterRibbonService.merge(id, jenkinsAdapterEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询JenkinsAdapter
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询JenkinsAdapter", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public JenkinsAdapterVO get(@ApiParam(value = "要查询的JenkinsAdapterid") @PathVariable String id) {

		JenkinsAdapterVO vo = jenkinsAdapterRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询JenkinsAdapter列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询JenkinsAdapter列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = JenkinsAdapterCondition.class)
	public PageContent<JenkinsAdapterVO> list(@RequestBody @Valid PageSearchRequest<JenkinsAdapterCondition> pageSearchRequest){

		PageContent<JenkinsAdapterVO> pageContent = jenkinsAdapterRibbonService.list(pageSearchRequest);
		for(JenkinsAdapterVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

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
    public void export(JenkinsAdapterCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

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

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("project" ,"所属产品");
            headMap.put("env" ,"所属环境");
            headMap.put("port" ,"端口");
            headMap.put("host" ,"IP");

        String title = new String("JenkinsAdapter");
        String fileName = new String(("JenkinsAdapter_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private JenkinsAdapterVO initViewProperty( JenkinsAdapterVO vo){


	   
        return vo;

	}
}
