package net.aicoder.speedcloud.console.business.speedcloud.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigCondition;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigEditDto;
import net.aicoder.speedcloud.business.app.vo.SecurityConfigVO;
import net.aicoder.speedcloud.console.business.speedcloud.app.service.SecurityConfigRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.app.valid.SecurityConfigValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理应用私密配置
 * @author icode
 */
@Api(description = "应用私密配置", tags = "SecurityConfig")
@RestController
@RequestMapping(value = "/speedcloud/app/securityconfig")
public class SecurityConfigController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfigController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private SecurityConfigRibbonService securityConfigRibbonService;

	@Autowired
	SecurityConfigValidator securityConfigValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(securityConfigValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增应用私密配置
	 * @param securityConfigAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增应用私密配置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	@SaaSAnnotation
	public SecurityConfigVO add(@RequestBody SecurityConfigAddDto securityConfigAddDto){
		return  securityConfigRibbonService.add(securityConfigAddDto);
	}

	/**
	 * 删除应用私密配置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除应用私密配置", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete securityConfig :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			securityConfigRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新应用私密配置
	 * @param securityConfigEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产应用私密配置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public SecurityConfigVO update(@RequestBody SecurityConfigEditDto securityConfigEditDto, @ApiParam(value = "要查询的应用私密配置id") @PathVariable Long id){

		SecurityConfigVO vo = securityConfigRibbonService.merge(id, securityConfigEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询应用私密配置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询应用私密配置", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public SecurityConfigVO get(@ApiParam(value = "要查询的应用私密配置id") @PathVariable Long id) {

		SecurityConfigVO vo = securityConfigRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询应用私密配置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询应用私密配置列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = SecurityConfigCondition.class)
	public PageContent<SecurityConfigVO> list(@RequestBody PageSearchRequest<SecurityConfigCondition> pageSearchRequest){

		PageContent<SecurityConfigVO> pageContent = securityConfigRibbonService.list(pageSearchRequest);
		for(SecurityConfigVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出应用私密配置列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出应用私密配置列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(SecurityConfigCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<SecurityConfigCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<SecurityConfigVO> content = this.list(pageSearchRequest);

        List<SecurityConfigVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(SecurityConfigVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("app" ,"应用");
            headMap.put("itemName" ,"配置名");
            headMap.put("itemValue" ,"配置值");

        String title = new String("应用私密配置");
        String fileName = new String(("应用私密配置_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private SecurityConfigVO initViewProperty( SecurityConfigVO vo){

	   


	   
        return vo;

	}


}
