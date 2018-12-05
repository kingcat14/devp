package net.aicoder.speedcloud.console.business.speedcloud.config.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeCondition;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeEditDto;
import net.aicoder.speedcloud.business.config.vo.CodeRepositoryTypeVO;
import net.aicoder.speedcloud.console.business.speedcloud.config.service.CodeRepositoryTypeRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.config.valid.CodeRepositoryTypeValidator;
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
 * 管理代码库类型
 * @author icode
 */
@Api(description = "代码库类型", tags = "CodeRepositoryType")
@RestController
@RequestMapping(value = "/speedcloud/config/coderepositorytype")
public class CodeRepositoryTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepositoryTypeController.class);
   
    @Autowired
	private CodeRepositoryTypeRibbonService codeRepositoryTypeRibbonService;

	@Autowired
	private CodeRepositoryTypeValidator codeRepositoryTypeValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(codeRepositoryTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增代码库类型
	 * @param codeRepositoryTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增代码库类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public CodeRepositoryTypeVO add(@RequestBody @Valid CodeRepositoryTypeAddDto codeRepositoryTypeAddDto){
	
		return  codeRepositoryTypeRibbonService.add(codeRepositoryTypeAddDto);
	}

	/**
	 * 删除代码库类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除代码库类型", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete codeRepositoryType :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			codeRepositoryTypeRibbonService.delete(id);
		}

	}

	/**
	 * 更新代码库类型
	 * @param codeRepositoryTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产代码库类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public CodeRepositoryTypeVO update(@RequestBody @Valid CodeRepositoryTypeEditDto codeRepositoryTypeEditDto, @ApiParam(value = "要查询的代码库类型id") @PathVariable String id){

		CodeRepositoryTypeVO vo = codeRepositoryTypeRibbonService.merge(id, codeRepositoryTypeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询代码库类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询代码库类型", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public CodeRepositoryTypeVO get(@ApiParam(value = "要查询的代码库类型id") @PathVariable String id) {

		CodeRepositoryTypeVO vo = codeRepositoryTypeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询代码库类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询代码库类型列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = CodeRepositoryTypeCondition.class)
	public PageContent<CodeRepositoryTypeVO> list(@RequestBody @Valid PageSearchRequest<CodeRepositoryTypeCondition> pageSearchRequest){

		PageContent<CodeRepositoryTypeVO> pageContent = codeRepositoryTypeRibbonService.list(pageSearchRequest);
		for(CodeRepositoryTypeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出代码库类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出代码库类型列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(CodeRepositoryTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<CodeRepositoryTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<CodeRepositoryTypeVO> content = this.list(pageSearchRequest);

        List<CodeRepositoryTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(CodeRepositoryTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("code" ,"代码");
            headMap.put("name" ,"名称");

        String title = new String("代码库类型");
        String fileName = new String(("代码库类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private CodeRepositoryTypeVO initViewProperty( CodeRepositoryTypeVO vo){


	   
        return vo;

	}
}
