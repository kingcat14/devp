package net.aicoder.speedcloud.console.business.speedcloud.env.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.env.dto.EnvMachineAddDto;
import net.aicoder.speedcloud.business.env.dto.EnvMachineCondition;
import net.aicoder.speedcloud.business.env.dto.EnvMachineEditDto;
import net.aicoder.speedcloud.business.env.vo.EnvMachineVO;
import net.aicoder.speedcloud.console.business.speedcloud.env.service.EnvMachineRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.env.valid.EnvMachineValidator;
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
 * 管理环境设备关联
 * @author icode
 */
@Api(description = "环境设备关联", tags = "EnvMachine")
@RestController
@RequestMapping(value = "/speedcloud/env/envmachine")
public class EnvMachineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvMachineController.class);
   
    @Autowired
	private EnvMachineRibbonService envMachineRibbonService;

	@Autowired
	private EnvMachineValidator envMachineValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(envMachineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增环境设备关联
	 * @param envMachineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增环境设备关联", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public EnvMachineVO add(@RequestBody @Valid EnvMachineAddDto envMachineAddDto){
	
		return  envMachineRibbonService.add(envMachineAddDto);
	}

	/**
	 * 删除环境设备关联,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除环境设备关联", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete envMachine :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			envMachineRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新环境设备关联
	 * @param envMachineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产环境设备关联(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public EnvMachineVO update(@RequestBody @Valid EnvMachineEditDto envMachineEditDto, @ApiParam(value = "要查询的环境设备关联id") @PathVariable Long id){

		EnvMachineVO vo = envMachineRibbonService.merge(id, envMachineEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询环境设备关联
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询环境设备关联", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public EnvMachineVO get(@ApiParam(value = "要查询的环境设备关联id") @PathVariable Long id) {

		EnvMachineVO vo = envMachineRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询环境设备关联列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询环境设备关联列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = EnvMachineCondition.class)
	public PageContent<EnvMachineVO> list(@RequestBody @Valid PageSearchRequest<EnvMachineCondition> pageSearchRequest){

		PageContent<EnvMachineVO> pageContent = envMachineRibbonService.list(pageSearchRequest);
		for(EnvMachineVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出环境设备关联列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出环境设备关联列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(EnvMachineCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<EnvMachineCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<EnvMachineVO> content = this.list(pageSearchRequest);

        List<EnvMachineVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(EnvMachineVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("evn" ,"环境");
            headMap.put("machine" ,"机器");

        String title = new String("环境设备关联");
        String fileName = new String(("环境设备关联_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private EnvMachineVO initViewProperty( EnvMachineVO vo){


	   
        return vo;

	}
}
