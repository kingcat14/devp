package net.aicoder.speedcloud.console.business.speedcloud.config.controller;

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
import net.aicoder.speedcloud.business.config.dto.DevelopTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.DevelopTypeCondition;
import net.aicoder.speedcloud.business.config.dto.DevelopTypeEditDto;
import net.aicoder.speedcloud.business.config.vo.DevelopTypeVO;
import net.aicoder.speedcloud.console.business.speedcloud.config.service.DevelopTypeRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.config.valid.DevelopTypeValidator;
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
 * 管理开发模式
 * @author icode
 */
@Api(description = "开发模式", tags = "DevelopType")
@RestController
@RequestMapping(value = "/speedcloud/config/developtype")
public class DevelopTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevelopTypeController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private DevelopTypeRibbonService developTypeRibbonService;

	@Autowired
	DevelopTypeValidator developTypeValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(developTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增开发模式
	 * @param developTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增开发模式", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	@SaaSAnnotation
	public DevelopTypeVO add(@RequestBody DevelopTypeAddDto developTypeAddDto){
		return  developTypeRibbonService.add(developTypeAddDto);
	}

	/**
	 * 删除开发模式,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除开发模式", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete developType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			developTypeRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新开发模式
	 * @param developTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产开发模式(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevelopTypeVO update(@RequestBody DevelopTypeEditDto developTypeEditDto, @ApiParam(value = "要查询的开发模式id") @PathVariable Long id){

		DevelopTypeVO vo = developTypeRibbonService.merge(id, developTypeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询开发模式
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询开发模式", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevelopTypeVO get(@ApiParam(value = "要查询的开发模式id") @PathVariable Long id) {

		DevelopTypeVO vo = developTypeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询开发模式列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询开发模式列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = DevelopTypeCondition.class)
	public PageContent<DevelopTypeVO> list(@RequestBody PageSearchRequest<DevelopTypeCondition> pageSearchRequest){

		PageContent<DevelopTypeVO> pageContent = developTypeRibbonService.list(pageSearchRequest);
		for(DevelopTypeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出开发模式列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出开发模式列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevelopTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DevelopTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevelopTypeVO> content = this.list(pageSearchRequest);

        List<DevelopTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevelopTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"名称");

        String title = new String("开发模式");
        String fileName = new String(("开发模式_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DevelopTypeVO initViewProperty( DevelopTypeVO vo){

	   


	   
        return vo;

	}


}
